package com.shopping.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product 모델 클래스 테스트")
class ProductTest {

    @Test
    @DisplayName("성공: 생성자를 통해 Product 객체가 올바르게 생성되어야 한다")
    void product_creation_shouldSetAllFieldsCorrectly() {
        // Arrange
        String id = "P001";
        String name = "노트북";
        ProductCategory category = ProductCategory.전자제품;
        int price = 1500000;
        int stock = 10;
        String description = "고성능 노트북";

        // Act
        Product product = new Product(id, name, category, price, stock, description);

        // Assert
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(category, product.getCategory());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(description, product.getDescription());

        // 생성 시 등록일시와 판매량이 기본값으로 설정되는지 확인
        assertNotNull(product.getRegistrationDateTime());
        assertTrue(product.getRegistrationDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertEquals(0, product.getSalesCount());
    }
}