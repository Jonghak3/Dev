package com.shopping.controller;

import com.shopping.model.*;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;
import com.shopping.service.ReportService;
import com.shopping.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private ProductService productService;
    @Mock
    private OrderService orderService;
    @Mock
    private UserService userService;
    @Mock
    private ReportService reportService;

    @InjectMocks
    private AdminController adminController;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private Product sampleProduct;
    private Order sampleOrder;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        sampleProduct = new Product("P001", "Test Product", ProductCategory.전자제품, 10000, 10, "A test product.");
        sampleUser = new User("testUser", "password", "test@test.com", "Tester");
        sampleOrder = new Order("O001", "testUser", Collections.emptyList(), null, OrderStatus.PENDING);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        // Scanner를 사용하는 클래스는 테스트마다 새로 생성하여 입력 스트림을 리셋해야 합니다.
        adminController = new AdminController(userService, orderService, productService, reportService);
    }

    // --- 1. 상품 관리 테스트 ---

    @Test
    @DisplayName("상품 추가 성공")
    void addNewProduct_Success() {
        // Arrange: 1.상품관리 -> 1.신규상품등록 -> (정보입력) -> 0.돌아가기 -> 0.돌아가기
        String input = "1\n1\nP001\nTest Product\n10000\n10\n전자제품\nDesc\n0\n0\n";
        provideInput(input);
        // addProduct는 반환값이 없으므로 when은 필요 없음

        // Act
        adminController.showAdminMenu();

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("상품이 성공적으로 등록되었습니다."));
        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    @DisplayName("상품 정보 수정 성공")
    void updateProductInfo_Success() {
        // Arrange: 1.상품관리 -> 2.정보수정 -> P001 -> (정보입력) -> 0.돌아가기 -> 0.돌아가기
        String input = "1\n2\nP001\nNew Name\n20000\n5\n의류\n0\n0\n";
        provideInput(input);
        when(productService.findProductById("P001")).thenReturn(Optional.of(sampleProduct));

        // Act
        adminController.showAdminMenu();

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("상품 정보가 성공적으로 수정되었습니다."));
        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    @DisplayName("상품 삭제 성공")
    void deleteProduct_Success() {
        // Arrange: 1.상품관리 -> 3.상품삭제 -> P001 -> 0.돌아가기 -> 0.돌아가기
        String input = "1\n3\nP001\n0\n0\n";
        provideInput(input);
        when(productService.deleteProduct("P001")).thenReturn(true);

        // Act
        adminController.showAdminMenu();

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("상품이 성공적으로 삭제되었습니다."));
        verify(productService, times(1)).deleteProduct("P001");
    }

    // --- 2. 주문 관리 테스트 ---

    @Test
    @DisplayName("전체 주문 조회 성공 (주문 존재)")
    void displayAllOrders_Success() {
        // Arrange: 3.주문관리 -> 1.전체주문목록조회 -> 0.돌아가기 -> 0.돌아가기
        String input = "3\n1\n0\n0\n";
        provideInput(input);
        when(orderService.getAllOrders()).thenReturn(List.of(sampleOrder));

        // Act
        adminController.showAdminMenu();

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Order[id=O001"));
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    @DisplayName("주문 상태 변경 성공")
    void updateOrderStatus_Success() {
        // Arrange: 3.주문관리 -> 2.주문상태변경 -> O001 -> SHIPPING -> 0.돌아가기 -> 0.돌아가기
        String input = "3\n2\nO001\nSHIPPING\n0\n0\n";
        provideInput(input);
        when(orderService.findByOrderId("O001")).thenReturn(Optional.of(sampleOrder));
        // updateOrderStatus는 반환값이 없으므로 when은 필요 없음

        // Act
        adminController.showAdminMenu();

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("주문 상태가 성공적으로 변경되었습니다."));
        verify(orderService, times(1)).updateOrderStatus(eq("O001"), eq(OrderStatus.SHIPPING));
    }

    @Test
    @DisplayName("주문 통계 조회 성공")
    void displayOrderStatistics_Success() {
        // Arrange: 3.주문관리 -> 3.주문통계조회 -> (날짜, TopN 입력) -> 0.돌아가기 -> 0.돌아가기
        String input = "3\n3\n2023-01-01\n2023-01-31\n3\n0\n0\n";
        provideInput(input);
        when(reportService.salesByDate(any(), any())).thenReturn(150000);
        when(reportService.topProducts(3)).thenReturn(Map.of("P001", 50));
        when(reportService.orderCountByStatus()).thenReturn(Map.of(OrderStatus.DELIVERED, 5L));

        // Act
        adminController.showAdminMenu();

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("조회 기간 총 매출: 150,000원"));
        assertTrue(output.contains("P001): 50개 판매"));
        assertTrue(output.contains("배송 완료: 5건"));
        verify(reportService, times(1)).salesByDate(any(), any());
        verify(reportService, times(1)).topProducts(3);
        verify(reportService, times(1)).orderCountByStatus();
    }


    // --- 3. 사용자 관리 테스트 ---

    @Test
    @DisplayName("전체 사용자 조회 성공")
    void displayAllUsers_Success() {
        // Arrange: 2.사용자관리 -> 1.전체회원목록조회 -> 0.돌아가기 -> 0.돌아가기
        String input = "2\n1\n0\n0\n";
        provideInput(input);
        when(userService.getAllUsers()).thenReturn(List.of(sampleUser));

        // Act
        adminController.showAdminMenu();

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("User[id=testUser"));
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("사용자 삭제(강제 탈퇴) 성공")
    void deactivateUser_Success() {
        // Arrange: 2.사용자관리 -> 4.회원강제탈퇴 -> testUser -> 0.돌아가기 -> 0.돌아가기
        String input = "2\n4\ntestUser\n0\n0\n";
        provideInput(input);
        when(userService.deleteUser("testUser")).thenReturn(true);

        // Act
        adminController.showAdminMenu();

        // Assert
        assertTrue(outputStreamCaptor.toString().contains("회원이 성공적으로 탈퇴 처리되었습니다."));
        verify(userService, times(1)).deleteUser("testUser");
    }
}
