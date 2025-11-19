package com.shopping.controller;

import com.shopping.controller.CartController;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.model.ProductCategory;
import com.shopping.repository.CartRepository;
import com.shopping.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * CartController에 대한 단위 테스트 클래스.
 * Mockito를 사용하여 Repository 의존성을 격리하고 테스트합니다.
 */
@ExtendWith(MockitoExtension.class) // JUnit 5에서 Mockito 어노테이션을 사용하기 위해 필요합니다.
class CartControllerTest {

    // @Mock: Mockito가 가짜(Mock) 객체를 생성합니다. 실제 DB나 파일에 접근하지 않습니다.
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    // @InjectMocks: @Mock으로 생성된 가짜 객체들을 이 객체에 자동으로 주입합니다.
    @InjectMocks
    private CartController cartController;

    // System.out 출력을 캡처하기 위한 필드
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private final String TEST_USER_ID = "testUser";
    private final String TEST_USER_ID1 = "testUser1";
    private final String TEST_USER_ID2 = "testUser2";
    private Product sampleProduct1;
    private Product sampleProduct2;
    private Product sampleProduct3;
    private Product sampleProduct4;
    private Product sampleProduct5;

    /**
     * @BeforeEach: 각 테스트 메소드가 실행되기 전에 항상 실행됩니다.
     * 테스트 환경을 초기화하는 데 사용됩니다.
     */
    @BeforeEach
    public void setUp() {
        // System.out의 출력을 ByteArrayOutputStream으로 리디렉션하여 캡처할 수 있도록 설정
        System.setOut(new PrintStream(outputStreamCaptor));

        // 테스트용 상품 객체 생성
        sampleProduct1 = new Product("P001", "테스트 상품1", ProductCategory.전자제품, 1000000, 10, "좋은 상품");
        sampleProduct2 = new Product("P002", "테스트 상품2", ProductCategory.의류, 5000, 1, "나쁜 상품");
        sampleProduct3 = new Product("P003", "테스트 상품3", ProductCategory.식품, 1000, 100, "보통 상품");
        sampleProduct4 = new Product("P004", "테스트 상품4", ProductCategory.도서, 30000, 50, "도서 상품");
        sampleProduct5 = new Product("P005", "테스트 상품5", ProductCategory.기타, 500000, 500, "기타 상품");
        // [수정/추가 가이드]
        // 모든 테스트는 여기서 생성된 sampleProduct를 공통으로 사용할 수 있습니다.
        // 만약 다른 상품이 필요하다면, 각 테스트 메소드 내에서 지역 변수로 새로 생성하여 사용하면 됩니다.
    }

    /**
     * @AfterEach: 각 테스트 메소드가 실행된 후에 항상 실행됩니다.
     * 테스트 환경을 원래 상태로 복원합니다.
     */
    @AfterEach
    public void tearDown() {
        // System.out과 System.in을 원래 스트림으로 복원
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);

        // CartController는 내부적으로 새로운 Scanner를 생성하므로,
        // 테스트 대상 객체를 여기서 다시 만들어주거나 Scanner를 외부에서 주입받도록 리팩토링해야 합니다.
        // 여기서는 테스트의 편의를 위해 다시 생성하겠습니다.
        cartController = new CartController(productRepository, cartRepository);
    }

    @Test
    @DisplayName("상품 추가 성공: 존재하는 상품을 장바구니에 추가")
    void addProductToCart_Success() {
        // Arrange (준비)
        // [수정/추가 가이드] 1. 시나리오에 맞는 사용자 입력을 시뮬레이션합니다.
        // 메뉴 선택, 상품 ID, 수량 등을 `\n`으로 구분하여 문자열로 만듭니다.
        String input = "1\nP001\n2\n0\n";

        provideInput(input);

        // [수정/추가 가이드] 2. Mock 객체의 행동을 시나리오에 맞게 정의합니다.
        // 예를 들어, "findById가 호출되면 어떤 값을 반환할 것인가?"를 설정합니다.
        when(productRepository.findById("P001")).thenReturn(Optional.of(sampleProduct1));
        when(cartRepository.findByUserId(TEST_USER_ID)).thenReturn(Optional.of(new Cart(TEST_USER_ID)));

        // Act (실행)
        // [수정/추가 가이드]
        // 이 부분은 보통 수정할 필요 없이 그대로 `runCartMenu`를 호출합니다.
        cartController.runCartMenu(TEST_USER_ID);

        // Assert (검증)
        // [수정/추가 가이드] 3. 실행 결과가 예상과 일치하는지 확인합니다.
        String output = outputStreamCaptor.toString();
        // 3-1. 예상되는 출력 메시지가 포함되어 있는지 확인합니다.
        assertTrue(output.contains("'테스트 상품1' 상품 2개를 장바구니에 추가했습니다."));
        // 3-2. 특정 메소드가 예상대로 호출되었는지 (또는 호출되지 않았는지) 검증합니다.
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    @DisplayName("상품 추가 실패: 존재하지 않는 상품 ID 입력")
    void addProductToCart_ProductNotFound() {
        // Arrange
        String input = "1\nP999\n0\n"; // 1(추가), P999(없는 상품ID), 0(종료)
        provideInput(input);

        // productRepository.findById("P999")가 호출되면, 비어있는 Optional을 반환하도록 설정
        when(productRepository.findById("P999")).thenReturn(Optional.empty());

        // Act
        cartController.runCartMenu(TEST_USER_ID);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("해당 ID의 상품을 찾을 수 없습니다."));
        // cartRepository.save()가 호출되지 않았는지 검증
        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    @DisplayName("장바구니 보기: 비어있는 장바구니")
    void viewCart_Empty() {
        // Arrange
        String input = "2\n0\n"; // 2(보기), 0(종료)
        provideInput(input);

        // cartRepository.findByUserId가 호출되면 비어있는 Optional 반환하도록 설정
        when(cartRepository.findByUserId(TEST_USER_ID)).thenReturn(Optional.empty());

        // Act
        cartController.runCartMenu(TEST_USER_ID);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("장바구니가 비어있습니다."));
    }

    @Test
    @DisplayName("장바구니 비우기 성공")
    void clearCart_Success() {
        // Arrange
        String input = "4\n0\n"; // 4(비우기), 0(종료)
        provideInput(input);

        // 비어있지 않은 장바구니를 생성
        Cart cart = new Cart(TEST_USER_ID);
        cart.addProduct(sampleProduct1, 1);

        // cartRepository가 비어있지 않은 장바구니를 반환하도록 설정
        when(cartRepository.findByUserId(TEST_USER_ID)).thenReturn(Optional.of(cart));

        // Act
        cartController.runCartMenu(TEST_USER_ID);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("장바구니를 모두 비웠습니다."));
        // cartRepository.save()가 1번 호출되었는지 검증
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    // ==========================================================================================
    // [수정/추가 가이드]
    // 아래와 같이 새로운 테스트 케이스를 위한 메소드를 추가할 수 있습니다.
    // 기존 테스트 메소드를 하나 복사하고, `@DisplayName`과 메소드명, 그리고 내부 로직을
    // 새로운 시나리오에 맞게 수정하여 작성하는 것이 가장 편리합니다.
    //
    // 추천하는 추가 테스트 시나리오:
    // 1. 장바구니 보기 (상품이 들어있는 경우)
    // 2. 상품 삭제 성공
    // 3. 상품 삭제 실패 (장바구니에 없는 상품 ID를 입력한 경우)
    // 4. 장바구니 비우기 (이미 비어있는 경우)
    // 5. 잘못된 메뉴 번호를 입력한 경우
    // ==========================================================================================

    @Test
    @DisplayName("상품 삭제 성공: 장바구니에 있는 상품을 삭제")
    void removeProductFromCart_Success() {
        // Arrange (준비)
        // 1. 사용자 입력 시뮬레이션: "3(삭제) -> P001(상품ID) -> 0(종료)"
        String input = "3\nP001\n0\n";
        provideInput(input);

        // 2. Mock 객체 행동 정의: P001 상품이 담긴 장바구니를 반환하도록 설정
        Cart cart = new Cart(TEST_USER_ID);
        cart.addProduct(sampleProduct1, 2); // '테스트 상품' 2개가 담겨있다고 가정
        when(cartRepository.findByUserId(TEST_USER_ID)).thenReturn(Optional.of(cart));

        // Act (실행)
        cartController.runCartMenu(TEST_USER_ID);

        // Assert (검증)
        String output = outputStreamCaptor.toString();
        // 3-1. 성공 메시지 확인
        assertTrue(output.contains("상품을 장바구니에서 삭제했습니다."));
        // 3-2. cartRepository.save()가 호출되었는지 확인
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    @DisplayName("상품 삭제 실패: 장바구니에 없는 상품")
    void removeProductFromCart_ProductNotInCart() {
        // Arrange
        String input = "3\nP002\n0\n"; // P002 상품 삭제 시도
        provideInput(input);

        // P001 상품만 들어있는 장바구니 준비
        Cart cart = new Cart(TEST_USER_ID);
        cart.addProduct(sampleProduct1, 1);
        when(cartRepository.findByUserId(TEST_USER_ID)).thenReturn(Optional.of(cart));

        // Act
        cartController.runCartMenu(TEST_USER_ID);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("장바구니에 해당 상품이 없습니다."));
        // 상품이 없으므로 save는 호출되면 안 됨
        verify(cartRepository, never()).save(any(Cart.class));
    }
}
