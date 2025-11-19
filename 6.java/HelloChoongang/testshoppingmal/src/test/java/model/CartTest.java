package com.shopping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart 모델 클래스 테스트")
class CartTest {

    private Cart cart;
    private Product productA;
    private Product productB;

    @BeforeEach
    void setUp() {
        // 각 테스트를 위한 새로운 장바구니와 상품들 생성
        cart = new Cart("testUser");
        productA = new Product("P001", "노트북", ProductCategory.전자제품, 1500000, 10, "");
        productB = new Product("P002", "마우스", ProductCategory.전자제품, 50000, 20, "");
    }

    @Test
    @DisplayName("성공: 새 상품 추가 시 장바구니에 새 아이템이 생성되어야 한다")
    void addProduct_whenNewProduct_shouldCreateNewItem() {
        // when: 상품 A 1개를 장바구니에 추가
        cart.addProduct(productA, 1);

        // then: 장바구니에 아이템 종류는 1개, 상품 A의 수량은 1이어야 함
        assertEquals(1, cart.getItems().size());
        assertEquals(1, cart.getItems().get("P001").getQuantity());
    }

    @Test
    @DisplayName("성공: 이미 담긴 상품 추가 시 기존 아이템의 수량이 증가해야 한다")
    void addProduct_whenExistingProduct_shouldIncreaseQuantity() {
        // given: 상품 A 1개를 미리 추가
        cart.addProduct(productA, 1);

        // when: 상품 A 2개를 더 추가
        cart.addProduct(productA, 2);

        // then: 장바구니 아이템 종류는 여전히 1개, 상품 A의 총 수량은 3이어야 함
        assertEquals(1, cart.getItems().size());
        assertEquals(3, cart.getItems().get("P001").getQuantity());
    }

    @Test
    @DisplayName("성공: removeProduct 호출 시 해당 상품이 장바구니에서 제거되어야 한다")
    void removeProduct_shouldRemoveTheItem() {
        // given: 상품 A와 B를 장바구니에 추가
        cart.addProduct(productA, 1);
        cart.addProduct(productB, 2);
        assertEquals(2, cart.getItems().size());

        // when: 상품 A를 제거
        cart.removeProduct("P001");

        // then: 장바구니 아이템 종류는 1개로 줄고, 상품 A는 더 이상 없어야 함
        assertEquals(1, cart.getItems().size());
        assertFalse(cart.getItems().containsKey("P001"));
    }

    @Test
    @DisplayName("성공: getTotalPrice가 모든 상품의 총액을 정확히 계산해야 한다")
    void getTotalPrice_shouldCalculateCorrectTotal() {
        // given: 상품 A(1,500,000원) 1개, 상품 B(50,000원) 2개 추가
        cart.addProduct(productA, 1);
        cart.addProduct(productB, 2);

        // when: getTotalPrice 호출
        int totalPrice = cart.getTotalPrice();

        // then: 총액은 1,500,000 + (50,000 * 2) = 1,600,000원 이어야 함
        assertEquals(1600000, totalPrice);
    }

    @Test
    @DisplayName("성공: clear 호출 시 장바구니의 모든 아이템이 삭제되어야 한다")
    void clear_shouldRemoveAllItems() {
        // given: 장바구니에 상품 추가
        cart.addProduct(productA, 1);
        cart.addProduct(productB, 2);
        assertFalse(cart.getItems().isEmpty());

        // when: clear 호출
        cart.clear();

        // then: 장바구니가 비어있고, 총액은 0이어야 함
        assertTrue(cart.getItems().isEmpty());
        assertEquals(0, cart.getTotalPrice());
    }
}