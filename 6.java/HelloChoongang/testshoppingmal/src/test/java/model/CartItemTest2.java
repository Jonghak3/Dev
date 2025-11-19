package com.shopping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartItemTest2 {

    private Product product;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 실행될 설정 코드
        // 테스트용 Product 객체 생성
        product = new Product("P001", "Test Product", ProductCategory.전자제품, 1000, 10, "A test product");
        // 테스트용 CartItem 객체 생성 (수량 2개)
        cartItem = new CartItem(product, 2);
    }

    @Test
    @DisplayName("총 가격 계산 테스트")
    void getTotalPrice() {
        // 예상 결과: 1000원 * 2개 = 2000원
        assertEquals(2000, cartItem.getTotalPrice(), "총 가격이 정확하게 계산되어야 합니다.");
    }

    @Test
    @DisplayName("수량 추가 테스트")
    void addQuantity() {
        // 기존 수량 2개에 3개 추가
        cartItem.addQuantity(3);
        // 예상 결과: 5개
        assertEquals(5, cartItem.getQuantity(), "수량이 정확하게 추가되어야 합니다.");
        // 추가 후 총 가격 재계산 확인
        assertEquals(5000, cartItem.getTotalPrice(), "수량 추가 후 총 가격이 정확해야 합니다.");
    }

    @Test
    @DisplayName("수량 설정 테스트")
    void setQuantity() {
        // 수량을 10개로 설정
        cartItem.setQuantity(10);
        // 예상 결과: 10개
        assertEquals(10, cartItem.getQuantity(), "수량이 정확하게 설정되어야 합니다.");
        // 설정 후 총 가격 재계산 확인
        assertEquals(10000, cartItem.getTotalPrice(), "수량 설정 후 총 가격이 정확해야 합니다.");
    }

    @Test
    @DisplayName("객체 동일성 비교 테스트")
    void testEquals() {
        // 같은 상품을 가진 다른 CartItem 객체 생성
        CartItem sameItem = new CartItem(product, 5);
        // 다른 상품을 가진 CartItem 객체 생성
        Product anotherProduct = new Product("P002", "Another Product", ProductCategory.도서, 2000, 5, "Another test product");
        CartItem differentItem = new CartItem(anotherProduct, 2);

        // product가 같으면 수량이 달라도 equals는 true를 반환해야 함
        assertTrue(cartItem.equals(sameItem), "같은 상품을 가진 CartItem은 동일하다고 판단해야 합니다.");
        // product가 다르면 equals는 false를 반환해야 함
        assertFalse(cartItem.equals(differentItem), "다른 상품을 가진 CartItem은 다르다고 판단해야 합니다.");
    }
}
