package com.shopping.service;

import com.shopping.model.Product;
import com.shopping.model.ProductCategory;
import com.shopping.repository.FileProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService 단위 테스트")
class ProductServiceTest {

    @Mock
    private FileProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product("P001", "테스트 상품", ProductCategory.전자제품, 10000, 10, "테스트 설명");
    }

    @Test
    @DisplayName("성공: 유효한 상품 추가 시 repository의 save가 호출되어야 한다")
    void addProduct_whenValidProduct_shouldCallRepositorySave() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // Act
        Product savedProduct = productService.addProduct(sampleProduct);

        // Assert
        assertNotNull(savedProduct);
        assertEquals("테스트 상품", savedProduct.getName());
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    @DisplayName("실패: 상품명이 null이면 IllegalArgumentException을 던져야 한다")
    void addProduct_whenNameIsNull_shouldThrowException() {
        // Arrange
        sampleProduct.setName(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(sampleProduct);
        });
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("실패: 가격이 범위를 벗어나면 IllegalArgumentException을 던져야 한다")
    void addProduct_whenPriceIsOutOfRange_shouldThrowException() {
        // Arrange
        sampleProduct.setPrice(-1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(sampleProduct);
        });
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("성공: 존재하는 상품 업데이트 시 repository의 findById와 save가 호출되어야 한다")
    void updateProduct_whenProductExists_shouldCallRepositoryMethods() {
        // Arrange
        when(productRepository.findById("P001")).thenReturn(Optional.of(sampleProduct));
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // Act
        Product updatedProduct = productService.updateProduct(sampleProduct);

        // Assert
        assertNotNull(updatedProduct);
        verify(productRepository, times(1)).findById("P001");
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    @DisplayName("실패: 존재하지 않는 상품 업데이트 시 IllegalArgumentException을 던져야 한다")
    void updateProduct_whenProductDoesNotExist_shouldThrowException() {
        // Arrange
        when(productRepository.findById("P001")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.updateProduct(sampleProduct);
        });
        verify(productRepository, times(1)).findById("P001");
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("성공: 재고 추가 시 상품 재고가 정상적으로 증가해야 한다")
    void addStock_whenProductExists_shouldIncreaseStock() {
        // Arrange
        when(productRepository.findById("P001")).thenReturn(Optional.of(sampleProduct));
        int initialStock = sampleProduct.getStock();
        int quantityToAdd = 5;

        // Act
        productService.addStock("P001", quantityToAdd);

        // Assert
        assertEquals(initialStock + quantityToAdd, sampleProduct.getStock());
        verify(productRepository, times(1)).save(sampleProduct);
    }

    @Test
    @DisplayName("실패: 추가할 재고 수량이 0 이하면 IllegalArgumentException을 던져야 한다")
    void addStock_whenQuantityIsZeroOrLess_shouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            productService.addStock("P001", 0);
        });
        verify(productRepository, never()).findById(anyString());
        verify(productRepository, never()).save(any(Product.class));
    }
}