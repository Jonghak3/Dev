package com.shopping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartTest2 {

    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 실행될 설정 코드
        // 테스트용 Cart 객체 생성
        cart = new Cart("testUser");
        // 테스트용 Product 객체 생성
        product1 = new Product("P001", "Laptop", ProductCategory.전자제품, 1500000, 10, "High-end laptop");
        product2 = new Product("P002", "Book", ProductCategory.도서, 25000, 50, "A great book");
    }

    @Test
    @DisplayName("카트에 새 상품 추가 테스트")
    void addProduct_NewProduct() {
        cart.addProduct(product1, 1);
        // 카트에 상품이 1개 있는지 확인
        assertEquals(1, cart.getItems().size(), "카트에 새 상품이 추가되어야 합니다.");
        // 추가된 상품의 수량이 맞는지 확인
        assertEquals(1, cart.getItems().get("P001").getQuantity(), "추가된 상품의 수량이 정확해야 합니다.");
    }

    @Test
    @DisplayName("카트에 기존 상품 추가 테스트 (수량 증가)")
    void addProduct_ExistingProduct() {
        // 먼저 상품 1개를 추가
        cart.addProduct(product1, 1);
        // 같은 상품을 2개 더 추가
        cart.addProduct(product1, 2);
        // 카트의 상품 종류는 여전히 1개여야 함
        assertEquals(1, cart.getItems().size(), "기존 상품 추가 시 상품 종류가 늘어나면 안 됩니다.");
        // 해당 상품의 총 수량이 3개인지 확인
        assertEquals(3, cart.getItems().get("P001").getQuantity(), "기존 상품 추가 시 수량이 합산되어야 합니다.");
    }

    @Test
    @DisplayName("카트에서 상품 제거 테스트")
    void removeProduct() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 5);
        // P001 상품 제거
        cart.removeProduct("P001");
        // P001 상품이 없는지 확인
        assertFalse(cart.getItems().containsKey("P001"), "상품이 카트에서 제거되어야 합니다.");
        // 나머지 상품은 그대로 있는지 확인
        assertTrue(cart.getItems().containsKey("P002"), "제거되지 않은 상품은 카트에 남아있어야 합니다.");
        // 카트의 상품 종류가 1개인지 확인
        assertEquals(1, cart.getItems().size(), "상품 제거 후 카트의 상품 수가 정확해야 합니다.");
    }

    @Test
    @DisplayName("카트 총 가격 계산 테스트")
    void getTotalPrice() {
        // 상품1 (1,500,000원) 1개 추가
        cart.addProduct(product1, 1);
        // 상품2 (25,000원) 2개 추가
        cart.addProduct(product2, 2);
        // 예상 총액: 1,500,000 + (25,000 * 2) = 1,550,000원
        assertEquals(1550000, cart.getTotalPrice(), "카트의 총 가격이 정확하게 계산되어야 합니다.");
    }

    @Test
    @DisplayName("카트 비우기 테스트")
    void clear() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        // 카트 비우기
        cart.clear();
        // 카트가 비어있는지 확인
        assertTrue(cart.getItems().isEmpty(), "clear() 메소드 실행 후 카트는 비어있어야 합니다.");
        // 카트 총액이 0원인지 확인
        assertEquals(0, cart.getTotalPrice(), "비워진 카트의 총 가격은 0이어야 합니다.");
    }
}
