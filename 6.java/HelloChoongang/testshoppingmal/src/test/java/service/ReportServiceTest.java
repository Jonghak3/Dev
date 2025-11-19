package com.shopping.service;

import com.shopping.model.Order;
import com.shopping.model.OrderItem;
import com.shopping.model.OrderStatus;
import com.shopping.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private OrderRepository orderRepo;

    @InjectMocks
    private ReportService reportService;

    private List<Order> allOrders;

    @BeforeEach
    void setUp() {
        // 테스트에 사용할 다양한 상태와 날짜를 가진 주문 데이터 목록을 설정합니다.
        Order order1 = new Order("order-001", "user1",
                List.of(new OrderItem("p1", "Laptop", 1500000, 1)),
                LocalDateTime.of(2023, 10, 25, 10, 0), OrderStatus.DELIVERED);

        Order order2 = new Order("order-002", "user2",
                List.of(new OrderItem("p2", "Mouse", 50000, 2), new OrderItem("p1", "Laptop", 1500000, 1)),
                LocalDateTime.of(2023, 10, 26, 11, 0), OrderStatus.CONFIRMED);

        Order order3 = new Order("order-003", "user1",
                List.of(new OrderItem("p3", "Keyboard", 120000, 1)),
                LocalDateTime.of(2023, 10, 27, 12, 0), OrderStatus.PENDING);

        Order order4 = new Order("order-004", "user3",
                List.of(new OrderItem("p2", "Mouse", 50000, 1)),
                LocalDateTime.of(2023, 10, 28, 14, 0), OrderStatus.CANCELLED);

        Order order5 = new Order("order-005", "user2",
                List.of(new OrderItem("p1", "Laptop", 1500000, 1)),
                LocalDateTime.of(2023, 11, 1, 15, 0), OrderStatus.SHIPPING);

        allOrders = List.of(order1, order2, order3, order4, order5);
    }

    @Test
    @DisplayName("기간별 매출 조회 - 성공")
    void salesByDate_Success() {
        // given: 특정 기간 내의 주문 목록을 반환하도록 Mock 객체 설정
        LocalDate from = LocalDate.of(2023, 10, 25);
        LocalDate to = LocalDate.of(2023, 10, 31);
        List<Order> ordersInDateRange = List.of(allOrders.get(0), allOrders.get(1), allOrders.get(2), allOrders.get(3));
        when(orderRepo.findByDateRange(from, to)).thenReturn(ordersInDateRange);

        // when: 기간별 매출을 조회
        int totalSales = reportService.salesByDate(from, to);

        // then: PENDING, CANCELLED 상태를 제외한 주문들의 총액이 맞는지 검증
        // order1 (1,500,000) + order2 (1,600,000) = 3,100,000
        assertEquals(3100000, totalSales);
    }

    @Test
    @DisplayName("Top N 상품 조회 - 성공")
    void topProducts_Success() {
        // given: 모든 주문 목록을 반환하도록 Mock 객체 설정
        when(orderRepo.findAll()).thenReturn(allOrders);

        // when: 판매량 상위 2개 상품을 조회
        Map<String, Integer> top2Products = reportService.topProducts(2);

        // then: 판매량 순서와 수량이 정확한지 검증
        // p1: 1(order1) + 1(order2) + 1(order5) = 3개
        // p2: 2(order2) = 2개
        // p3: 0개 (PENDING 상태이므로 집계 제외)
        assertNotNull(top2Products);
        assertEquals(2, top2Products.size());

        // 순서가 보장되어야 함 (LinkedHashMap)
        Object[] keys = top2Products.keySet().toArray();
        assertEquals("p1", keys[0]);
        assertEquals("p2", keys[1]);

        assertEquals(3, top2Products.get("p1"));
        assertEquals(2, top2Products.get("p2"));
    }

    @Test
    @DisplayName("주문 상태별 건수 조회 - 성공")
    void orderCountByStatus_Success() {
        // given: 모든 주문 목록을 반환하도록 Mock 객체 설정
        when(orderRepo.findAll()).thenReturn(allOrders);

        // when: 상태별 주문 건수를 조회
        Map<OrderStatus, Long> counts = reportService.orderCountByStatus();

        // then: 각 상태별 건수가 정확한지 검증
        assertNotNull(counts);
        assertEquals(1, counts.get(OrderStatus.DELIVERED));
        assertEquals(1, counts.get(OrderStatus.CONFIRMED));
        assertEquals(1, counts.get(OrderStatus.PENDING));
        assertEquals(1, counts.get(OrderStatus.CANCELLED));
        assertEquals(1, counts.get(OrderStatus.SHIPPING));
        assertNull(counts.get(null)); // null 상태는 없어야 함
    }
}
