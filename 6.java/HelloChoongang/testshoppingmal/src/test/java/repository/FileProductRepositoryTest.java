package com.shopping.repository;

import com.shopping.model.Product;
import com.shopping.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FileProductRepository 통합 테스트")
class FileProductRepositoryTest {

    private FileProductRepository productRepository;
    // 테스트가 실제 사용할 파일 경로를 명시합니다.
    private static final String DATA_FILE = "data" + File.separator + "products.dat";

    @BeforeEach
    void setUp() {
        // 테스트 시작 전, 혹시 남아있을 파일을 깨끗하게 삭제합니다.
        cleanup();
        // 매번 새로운 Repository 객체를 생성하여 테스트 환경을 초기화합니다.
        // 이 생성자는 내부적으로 DATA_FILE 경로의 파일을 로드하려고 시도합니다.
        productRepository = new FileProductRepository();
    }

    @AfterEach
    void tearDown() {
        // 테스트 종료 후, 생성된 파일을 깨끗하게 삭제하여 다른 테스트에 영향을 주지 않도록 합니다.
        cleanup();
    }

    private void cleanup() {
        File testFile = new File(DATA_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    @DisplayName("성공: 신규 상품 저장 시 ID가 자동 생성되고 파일에 저장되어야 한다")
    void save_whenNewProduct_shouldGenerateIdAndSave() {
        // Arrange
        Product newProduct = new Product(null, "새 상품", ProductCategory.식품, 5000, 50, "신선 식품");

        // Act
        Product savedProduct = productRepository.save(newProduct);

        // Assert
        assertNotNull(savedProduct.getId());
        assertEquals("P001", savedProduct.getId());
        assertNotNull(savedProduct.getRegistrationDateTime());

        // 새로운 Repository 인스턴스를 만들어 파일에서 데이터를 다시 읽어오는지 확인합니다.
        FileProductRepository newRepositoryInstance = new FileProductRepository();
        Optional<Product> foundProduct = newRepositoryInstance.findById("P001");

        assertTrue(foundProduct.isPresent());
        assertEquals("새 상품", foundProduct.get().getName());
    }

    @Test
    @DisplayName("성공: 모든 상품 조회 시 ID 순으로 정렬된 리스트를 반환해야 한다")
    void findAll_shouldReturnAllProductsSortedById() {
        // Arrange
        productRepository.save(new Product(null, "상품2", ProductCategory.의류, 20000, 20, "옷"));
        productRepository.save(new Product(null, "상품1", ProductCategory.도서, 15000, 30, "책"));

        // Act
        List<Product> allProducts = productRepository.findAll();

        // Assert
        assertEquals(2, allProducts.size());
        assertEquals("P001", allProducts.get(0).getId()); // "상품1"이 먼저 나와야 함
        assertEquals("P002", allProducts.get(1).getId()); // "상품2"가 나중에 나와야 함
    }

    @Test
    @DisplayName("성공: 상품 ID로 삭제 시 해당 상품이 삭제되어야 한다")
    void deleteById_whenProductExists_shouldRemoveProduct() {
        // Arrange
        Product savedProduct = productRepository.save(new Product(null, "삭제될 상품", ProductCategory.기타, 1000, 1, ""));
        String id = savedProduct.getId();
        assertTrue(productRepository.findById(id).isPresent(), "삭제 전 상품이 존재해야 합니다.");

        // Act
        boolean isDeleted = productRepository.deleteById(id);

        // Assert
        assertTrue(isDeleted);
        assertFalse(productRepository.findById(id).isPresent(), "삭제 후 상품이 존재하지 않아야 합니다.");
    }

    @Test
    @DisplayName("성공: 베스트셀러 조회 시 판매량(salesCount) 내림차순으로 정렬되어야 한다")
    void findBestSellers_shouldReturnProductsSortedBySalesCountDesc() {
        // Arrange
        Product p1 = new Product(null, "상품1", ProductCategory.전자제품, 100, 10, "");
        p1.setSalesCount(50);
        productRepository.save(p1);

        Product p2 = new Product(null, "상품2", ProductCategory.전자제품, 100, 10, "");
        p2.setSalesCount(100);
        productRepository.save(p2);

        Product p3 = new Product(null, "상품3", ProductCategory.전자제품, 100, 10, "");
        p3.setSalesCount(20);
        productRepository.save(p3);

        // Act
        List<Product> bestSellers = productRepository.findBestSellers(2);

        // Assert
        assertEquals(2, bestSellers.size());
        assertEquals(100, bestSellers.get(0).getSalesCount()); // 상품2
        assertEquals(50, bestSellers.get(1).getSalesCount());  // 상품1
    }
}