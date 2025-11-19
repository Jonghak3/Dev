package com.shopping.repository;

import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.model.ProductCategory;
import com.shopping.util.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FileCartRepository 통합 테스트")
class FileCartRepositoryTest {

    private FileCartRepository cartRepository;
    // Repository가 사용하는 실제 파일 경로 (Constants 클래스를 참조)
    private static final String CART_DATA_FILE = Constants.CART_DATA_FILE;

    @BeforeEach
    void setUp() {
        // 각 테스트 시작 전에 테스트 파일을 삭제하여 깨끗한 상태에서 시작
        cleanup();
        // 새로운 Repository 인스턴스 생성
        cartRepository = new FileCartRepository();
    }

    @AfterEach
    void tearDown() {
        // 각 테스트 종료 후에 생성된 테스트 파일을 삭제
        cleanup();
    }

    /**
     * 테스트 파일을 삭제하는 헬퍼 메서드
     */
    private void cleanup() {
        File file = new File(CART_DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        // 'data' 디렉토리가 없을 경우를 대비해 생성
        File dataDir = new File("data");
        if (!dataDir.exists()){
            dataDir.mkdirs();
        }
    }

    @Test
    @DisplayName("성공: 새 장바구니를 저장하고 사용자 ID로 다시 찾을 수 있어야 한다")
    void saveAndFindByUserId_shouldSaveAndFindNewCart() {
        // given: 'user1'의 새로운 장바구니 객체 생성
        Cart newCart = new Cart("user1");
        Product product = new Product("P001", "Test Product", ProductCategory.전자제품, 10000, 10, "desc");
        newCart.addProduct(product, 2);

        // when: 장바구니를 저장
        cartRepository.save(newCart);

        // then: 저장된 장바구니를 사용자 ID로 조회
        Optional<Cart> foundCartOpt = cartRepository.findByUserId("user1");
        assertTrue(foundCartOpt.isPresent(), "저장 후에는 장바구니가 조회되어야 합니다.");
        Cart foundCart = foundCartOpt.get();
        assertEquals("user1", foundCart.getUserId());
        assertEquals(20000, foundCart.getTotalPrice(), "장바구니 총액이 정확해야 합니다.");
    }

    @Test
    @DisplayName("성공: 사용자 ID로 장바구니를 삭제할 수 있어야 한다")
    void deleteByUserId_shouldRemoveCart() {
        // given: 'user3'의 장바구니를 미리 저장
        Cart cart = new Cart("user3");
        cartRepository.save(cart);
        assertTrue(cartRepository.findByUserId("user3").isPresent(), "삭제 전에는 장바구니가 존재해야 합니다.");

        // when: 해당 사용자의 장바구니를 삭제
        cartRepository.deleteByUserId("user3");

        // then: 삭제 후에는 더 이상 장바구니가 조회되지 않아야 함
        assertFalse(cartRepository.findByUserId("user3").isPresent());
    }

    @Test
    @DisplayName("성공(영속성): 데이터를 파일에 저장하고 새 Repository 인스턴스에서도 불러올 수 있어야 한다")
    void persistence_shouldLoadDataFromFileInNewInstance() {
        // given: 첫 번째 Repository 인스턴스로 'user4'의 장바구니를 저장
        Cart cart = new Cart("user4");
        cart.addProduct(new Product("P100", "Book", ProductCategory.도서, 25000, 1, ""), 3);
        cartRepository.save(cart);

        // when: 완전히 새로운 Repository 인스턴스를 생성
        FileCartRepository newRepositoryInstance = new FileCartRepository();

        // then: 새 인스턴스에서도 'user4'의 장바구니 데이터가 정상적으로 조회되어야 함
        Optional<Cart> foundCartOpt = newRepositoryInstance.findByUserId("user4");
        assertTrue(foundCartOpt.isPresent(), "새 인스턴스에서 파일로부터 데이터를 불러와야 합니다.");
        assertEquals(75000, foundCartOpt.get().getTotalPrice());
    }
}