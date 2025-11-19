package com.shopping.controller;

import com.shopping.model.Product;
import com.shopping.model.ProductCategory;
import com.shopping.service.ProductService;
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
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductController 단위 테스트")
class ProductControllerTest {

    @Mock
    private ProductService productService;

    // @InjectMocks는 @Mock으로 생성된 객체를 테스트 대상 객체에 자동으로 주입합니다.
    // 하지만 Scanner는 Mock 객체가 아니므로, 생성자를 통해 수동으로 주입해야 합니다.
    private ProductController productController;

    // 콘솔 출력을 캡처하기 위한 스트림
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        // System.out의 출력을 ByteArrayOutputStream으로 리디렉션
        System.setOut(new PrintStream(outContent));

        sampleProduct = new Product("P001", "테스트 상품", ProductCategory.전자제품, 10000, 10, "좋은 상품");
    }

    @AfterEach
    void tearDown() {
        // 테스트가 끝난 후 System.out과 System.in을 원래대로 복원
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * 사용자 입력을 시뮬레이션하는 헬퍼 메서드
     * @param data 시뮬레이션할 입력 문자열
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        // 새로운 Scanner를 생성하여 변경된 System.in을 사용하도록 함
        Scanner scanner = new Scanner(System.in);
        // 테스트 대상 컨트롤러에 Scanner 주입
        productController = new ProductController(productService, scanner);
    }

    @Test
    @DisplayName("상품 상세 정보 조회: 상품이 존재할 경우 상세 정보를 출력해야 한다")
    void viewProductDetail_whenProductExists_shouldPrintDetails() {
        // given: 사용자 입력으로 "P001"을 제공하고, 서비스는 해당 상품 정보를 반환하도록 설정
        String input = "P001\n";
        provideInput(input);
        when(productService.findProductById("P001")).thenReturn(Optional.of(sampleProduct));

        // when: 상품 상세 정보 조회 메서드 실행
        productController.viewProductDetail();

        // then: 콘솔 출력에 상품의 주요 정보가 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("ID: P001"));
        assertTrue(output.contains("이름: 테스트 상품"));
        assertTrue(output.contains("가격: 10,000원")); // 포맷팅된 문자열 확인

        // then: 서비스의 findProductById 메서드가 "P001" 인자와 함께 1번 호출되었는지 검증
        verify(productService, times(1)).findProductById("P001");
    }

    @Test
    @DisplayName("상품 상세 정보 조회: 상품이 없을 경우 '존재하지 않음' 메시지를 출력해야 한다")
    void viewProductDetail_whenProductDoesNotExist_shouldPrintNotFoundMessage() {
        // given: 사용자 입력으로 "P999"를 제공하고, 서비스는 비어있는 Optional을 반환하도록 설정
        String input = "P999\n";
        provideInput(input);
        when(productService.findProductById("P999")).thenReturn(Optional.empty());

        // when: 상품 상세 정보 조회 메서드 실행
        productController.viewProductDetail();

        // then: 콘솔 출력에 상품이 존재하지 않는다는 메시지가 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("해당 ID의 상품이 존재하지 않습니다."));
    }

    @Test
    @DisplayName("상품 추가: 유효한 정보 입력 시 상품 추가 후 성공 메시지를 출력해야 한다")
    void addProduct_withValidInput_shouldPrintSuccessMessage() {
        // given: 상품 추가에 필요한 모든 정보를 순서대로 입력
        String input = "P002\n새 상품\n식품\n5000\n100\n맛있는 식품\n";
        provideInput(input);
        // 서비스의 addProduct가 호출되면, 입력된 상품 정보를 그대로 반환하도록 설정
        when(productService.addProduct(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when: 상품 추가 메서드 실행
        productController.addProduct();

        // then: 콘솔 출력에 성공 메시지가 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("상품 '새 상품' (ID: P002) 이(가) 등록되었습니다."));

        // then: 서비스의 addProduct 메서드가 한 번 호출되었는지 검증
        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    @DisplayName("상품 삭제: 존재하는 상품 ID 입력 시 삭제 후 성공 메시지를 출력해야 한다")
    void deleteProduct_whenProductExists_shouldPrintSuccessMessage() {
        // given: 사용자 입력으로 "P001"을 제공하고, 서비스는 삭제 성공(true)을 반환하도록 설정
        String input = "P001\n";
        provideInput(input);
        when(productService.deleteProduct("P001")).thenReturn(true);

        // when: 상품 삭제 메서드 실행
        productController.deleteProduct();

        // then: 콘솔 출력에 삭제 성공 메시지가 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("ID P001 상품이 삭제되었습니다."));

        // then: 서비스의 deleteProduct 메서드가 "P001" 인자와 함께 1번 호출되었는지 검증
        verify(productService).deleteProduct("P001");
    }

    @Test
    @DisplayName("상품 삭제: 존재하지 않는 상품 ID 입력 시 '존재하지 않음' 메시지를 출력해야 한다")
    void deleteProduct_whenProductDoesNotExist_shouldPrintNotFoundMessage() {
        // given: 사용자 입력으로 "P999"를 제공하고, 서비스는 삭제 실패(false)를 반환하도록 설정
        String input = "P999\n";
        provideInput(input);
        when(productService.deleteProduct("P999")).thenReturn(false);

        // when: 상품 삭제 메서드 실행
        productController.deleteProduct();

        // then: 콘솔 출력에 상품이 존재하지 않는다는 메시지가 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("해당 ID의 상품이 존재하지 않습니다."));
    }
}