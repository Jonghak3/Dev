package com.shopping.service;

import com.shopping.model.*;
import com.shopping.repository.FileOrderRepository;
import com.shopping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

// Mockito 확장 기능을 JUnit 5 테스트에서 사용하도록 설정합니다.
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    // @Mock: Mockito가 가짜(Mock) 객체를 생성하여 주입해줍니다.
    @Mock
    private OrderRepository orderRepo;

    @Mock
    private OrderService.ProductRepository productRepo;

    // FileOrderRepository는 OrderService의 생성자에서만 필요하므로 Mock 객체로 생성합니다.
    @Mock
    private FileOrderRepository fileOrderRepo;

    // @InjectMocks: @Mock으로 생성된 가짜 객체들을 테스트 대상인 OrderService에 주입합니다.
    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private final String TEST_USER_ID = "user123";
    private final String OTHER_USER_ID = "other999";
    private final String ADMIN_USER_ID = "admin";
    private final String TEST_ORDER_ID = "order-001";

    @BeforeEach
    void setUp() {
        // 각 테스트가 실행되기 전에 공통적으로 사용할 테스트용 Order 객체를 설정합니다.
        List<OrderItem> items = List.of(new OrderItem("prod-1", "Test Product", 10000, 2));
        testOrder = new Order(TEST_ORDER_ID, TEST_USER_ID, items, null, OrderStatus.PENDING);
    }

    @Test
    @DisplayName("주문 생성 - 성공")
    void placeOrder_Success() {
        // given: 주문할 아이템 리스트 준비
        List<OrderItem> itemsToOrder = List.of(new OrderItem("prod-1", "New Laptop", 1500000, 1));

        // when: 사용자가 주문을 생성
        Order createdOrder = orderService.placeOrder(TEST_USER_ID, itemsToOrder, Role.USER);

        // then: 주문이 성공적으로 생성되고, save 메서드가 1번 호출되었는지 검증
        assertNotNull(createdOrder);
        assertEquals(TEST_USER_ID, createdOrder.getUserId());
        assertEquals(1, createdOrder.getItems().size());
        assertEquals(OrderStatus.PENDING, createdOrder.getStatus());
        verify(orderRepo, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("주문 조회 - 본인 주문 조회 성공")
    void getOrder_Success_AsOwner() {
        // given: Repository가 특정 ID로 주문을 찾아 반환하도록 설정
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when: 주문자가 자신의 주문을 조회
        Order foundOrder = orderService.getOrder(TEST_ORDER_ID, TEST_USER_ID, Role.USER, null);

        // then: 주문 정보가 일치하는지 확인
        assertEquals(TEST_ORDER_ID, foundOrder.getOrderId());
        assertEquals(TEST_USER_ID, foundOrder.getUserId());
    }

    @Test
    @DisplayName("주문 조회 - 관리자가 다른 사용자 주문 조회 성공")
    void getOrder_Success_AsAdmin() {
        // given: Repository가 특정 ID로 주문을 찾아 반환하도록 설정
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when: 관리자가 다른 사람의 주문을 조회
        Order foundOrder = orderService.getOrder(TEST_ORDER_ID, ADMIN_USER_ID, Role.ADMIN, null);

        // then: 주문 정보가 정상적으로 조회되는지 확인
        assertEquals(TEST_ORDER_ID, foundOrder.getOrderId());
    }

    @Test
    @DisplayName("주문 조회 - 다른 사용자가 조회 시 실패 (SecurityException)")
    void getOrder_Fail_NotOwner() {
        // given: Repository가 특정 ID로 주문을 찾아 반환하도록 설정
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when & then: 다른 사용자가 주문 조회를 시도할 때 SecurityException이 발생하는지 검증
        assertThrows(SecurityException.class, () -> {
            orderService.getOrder(TEST_ORDER_ID, OTHER_USER_ID, Role.USER, null);
        });
    }

    @Test
    @DisplayName("주문 확정 - 재고가 충분할 경우 성공")
    void confirmOrder_Success_WithEnoughStock() {
        // given: 주문을 PENDING 상태로 설정하고, 재고가 충분하다고 설정
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));
        when(productRepo.hasStock("prod-1", 2)).thenReturn(true);

        // when: 주문을 확정
        orderService.confirmOrder(TEST_ORDER_ID, TEST_USER_ID, Role.USER, null);

        // then: 주문 상태가 CONFIRMED로 변경되고, 재고 감소 로직이 호출되었는지 검증
        assertEquals(OrderStatus.CONFIRMED, testOrder.getStatus());
        verify(productRepo, times(1)).decreaseStock("prod-1", 2);
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    @DisplayName("주문 확정 - 재고 부족 시 실패 (IllegalStateException)")
    void confirmOrder_Fail_NotEnoughStock() {
        // given: 주문을 PENDING 상태로 설정하고, 재고가 부족하다고 설정
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));
        when(productRepo.hasStock("prod-1", 2)).thenReturn(false);

        // when & then: 재고가 부족한 상태에서 주문 확정을 시도할 때 IllegalStateException이 발생하는지 검증
        assertThrows(IllegalStateException.class, () -> {
            orderService.confirmOrder(TEST_ORDER_ID, TEST_USER_ID, Role.USER, null);
        });
    }

    @Test
    @DisplayName("주문 취소 - 사용자가 PENDING 상태의 주문을 취소")
    void cancelOrder_Success_UserCancelsPendingOrder() {
        // given: PENDING 상태의 주문 준비
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when: 사용자가 주문을 취소
        orderService.cancelOrder(TEST_ORDER_ID, TEST_USER_ID, Role.USER);

        // then: 주문 상태가 CANCELLED로 변경되고, 재고 복구 로직은 호출되지 않았는지 검증
        assertEquals(OrderStatus.CANCELLED, testOrder.getStatus());
        verify(productRepo, never()).increaseStock(anyString(), anyInt());
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    @DisplayName("주문 취소 - 관리자가 CONFIRMED 상태의 주문을 취소 (재고 복구)")
    void cancelOrder_Success_AdminCancelsConfirmedOrder() {
        // given: CONFIRMED 상태의 주문 준비
        testOrder.changeStatus(OrderStatus.CONFIRMED);
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when: 관리자가 확정된 주문을 취소
        orderService.cancelOrder(TEST_ORDER_ID, ADMIN_USER_ID, Role.ADMIN);

        // then: 주문 상태가 CANCELLED로 변경되고, 재고 복구 로직이 호출되었는지 검증
        assertEquals(OrderStatus.CANCELLED, testOrder.getStatus());
        verify(productRepo, times(1)).increaseStock("prod-1", 2);
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    @DisplayName("주문 배송 - 관리자가 배송 시작 성공")
    void shipOrder_Success_AsAdmin() {
        // given: CONFIRMED 상태의 주문 준비
        testOrder.changeStatus(OrderStatus.CONFIRMED);
        when(orderRepo.findById(TEST_ORDER_ID)).thenReturn(Optional.of(testOrder));

        // when: 관리자가 배송 시작 처리
        orderService.shipOrder(TEST_ORDER_ID, ADMIN_USER_ID, Role.ADMIN);

        // then: 주문 상태가 SHIPPING으로 변경되었는지 검증
        assertEquals(OrderStatus.SHIPPING, testOrder.getStatus());
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    @DisplayName("주문 배송 - 일반 사용자가 배송 시작 시 실패 (SecurityException)")
    void shipOrder_Fail_AsUser() {
        // given: CONFIRMED 상태의 주문 준비
        testOrder.changeStatus(OrderStatus.CONFIRMED);

        // when & then: 일반 사용자가 배송 시작을 시도할 때 SecurityException이 발생하는지 검증
        // findById는 호출되지 않아야 함 (권한 체크가 먼저이므로)
        assertThrows(SecurityException.class, () -> {
            orderService.shipOrder(TEST_ORDER_ID, TEST_USER_ID, Role.USER);
        });
    }
}
