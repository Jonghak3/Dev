package com.shopping.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductCategory 열거형 테스트")
class ProductCategoryTest {

    @Test
    @DisplayName("성공: 유효한 문자열로 Enum 상수를 찾을 수 있어야 한다")
    void fromString_withValidName_shouldReturnCorrectEnum() {
        // Act & Assert
        assertEquals(ProductCategory.전자제품, ProductCategory.fromString("전자제품"));
        assertEquals(ProductCategory.의류, ProductCategory.fromString("의류"));
    }

    @Test
    @DisplayName("실패: 유효하지 않은 문자열로 Enum 찾기 시도 시 IllegalArgumentException을 던져야 한다")
    void fromString_withInvalidName_shouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            ProductCategory.fromString("가구");
        });
    }

    @Test
    @DisplayName("성공: 전체 카테고리 이름을 포함하는 문자열을 반환해야 한다")
    void getCategoryNames_shouldReturnAllNamesJoinedByComma() {
        // Act
        String categoryNames = ProductCategory.getCategoryNames();

        // Assert
        assertTrue(categoryNames.contains("전자제품"));
        assertTrue(categoryNames.contains("의류"));
        assertTrue(categoryNames.contains("식품"));
        assertTrue(categoryNames.contains("도서"));
        assertTrue(categoryNames.contains("기타"));
        assertEquals("전자제품, 의류, 식품, 도서, 기타", categoryNames);
    }
}