package com.shopping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CartItem 모델 클래스 테스트")
class CartItemTest {

    private Product sampleProduct;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        // 모든 테스트에서 사용할 샘플 상품 객체 생성
        sampleProduct = new Product("P001", "테스트 상품", ProductCategory.전자제품, 10000, 10, "설명");
        // 상품 2개를 담은 CartItem 생성
        cartItem = new CartItem(sampleProduct, 2);
    }

    @Test
    @DisplayName("성공: 소계(getTotalPrice)가 '가격 * 수량'으로 정확히 계산되어야 한다")
    void getTotalPrice_shouldCalculateCorrectly() {
        // given: 상품 가격 10000, 수량 2
        // when: getTotalPrice 호출
        double totalPrice = cartItem.getTotalPrice();
        // then: 결과는 20000.0 이어야 함
        assertEquals(20000.0, totalPrice);
    }

    @Test
    @DisplayName("성공: addQuantity 호출 시 수량이 정확히 증가해야 한다")
    void addQuantity_shouldIncreaseQuantity() {
        // given: 초기 수량 2
        // when: 수량 3 추가
        cartItem.addQuantity(3);
        // then: 총 수량은 5가 되어야 함
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    @DisplayName("성공: setQuantity 호출 시 수량이 지정된 값으로 변경되어야 한다")
    void setQuantity_shouldChangeQuantity() {
        // given: 초기 수량 2
        // when: 수량을 10으로 변경
        cartItem.setQuantity(10);
        // then: 총 수량은 10이 되어야 함
        assertEquals(10, cartItem.getQuantity());
        assertEquals(100000.0, cartItem.getTotalPrice(), "수량 변경 후 총액도 변경되어야 합니다.");
    }

    @Test
    @DisplayName("성공: equals 메서드는 상품이 같으면 수량이 달라도 true를 반환해야 한다")
    void equals_shouldReturnTrueForSameProductRegardlessOfQuantity() {
        // given: 같은 상품, 다른 수량을 가진 두 CartItem
        CartItem item1 = new CartItem(sampleProduct, 1);
        CartItem item2 = new CartItem(sampleProduct, 5);

        // when & then: 두 아이템은 같다고 판단되어야 함
        assertTrue(item1.equals(item2));
        assertEquals(item1, item2);
    }

    @Test
    @DisplayName("성공: equals 메서드는 상품이 다르면 false를 반환해야 한다")
    void equals_shouldReturnFalseForDifferentProduct() {
        // given: 다른 상품을 가진 두 CartItem
        Product anotherProduct = new Product("P002", "다른 상품", ProductCategory.식품, 5000, 5, "");
        CartItem item1 = new CartItem(sampleProduct, 2);
        CartItem item2 = new CartItem(anotherProduct, 2);

        // when & then: 두 아이템은 다르다고 판단되어야 함
        assertFalse(item1.equals(item2));
        assertNotEquals(item1, item2);
    }
}