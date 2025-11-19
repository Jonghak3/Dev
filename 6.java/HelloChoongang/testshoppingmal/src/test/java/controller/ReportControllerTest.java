package com.shopping.controller;

import com.shopping.model.OrderStatus;
import com.shopping.service.ReportService;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReportController 단위 테스트")
class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    // 콘솔 I/O를 제어하기 위한 스트림
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        // System.out의 출력을 ByteArrayOutputStream으로 리디렉션
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // 테스트가 끝난 후 System.out과 System.in을 원래대로 복원
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * 사용자 입력을 시뮬레이션하는 헬퍼 메서드
     * @param data 시뮬레이션할 입력 문자열 (각 라인은 \n으로 구분)
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    // --- 개별 메소드 테스트 (기존과 동일) ---

    @Test
    @DisplayName("기간별 매출 조회: 서비스가 반환한 매출액을 정확한 형식으로 출력해야 한다")
    void printSalesByDate_shouldPrintFormattedTotalSales() {
        // given
        LocalDate from = LocalDate.of(2025, 8, 1);
        LocalDate to = LocalDate.of(2025, 8, 22);
        when(reportService.salesByDate(from, to)).thenReturn(150000);

        // when
        reportController.printSalesByDate(from, to);

        // then
        String output = outContent.toString();
        assertTrue(output.contains("[매출 통계] 2025-08-01 ~ 2025-08-22 : 총 매출 = 150,000원"));
        verify(reportService).salesByDate(from, to);
    }

    // --- 메뉴 기능 테스트 (신규 추가) ---

    @Test
    @DisplayName("메뉴 - 기간별 매출 조회: 메뉴 선택부터 날짜 입력, 결과 출력까지의 흐름을 테스트한다")
    void ReportServiceSearchMenu_whenSelectSalesByDate_shouldPromptAndPrintResult() {
        // given: 메뉴 '1' 선택 -> 시작 날짜 -> 종료 날짜 -> 메뉴 '0' 선택(종료) 순으로 입력 시뮬레이션
        String input = "1\n2025-08-01\n2025-08-22\n0\n";
        provideInput(input);

        LocalDate from = LocalDate.of(2025, 8, 1);
        LocalDate to = LocalDate.of(2025, 8, 22);
        when(reportService.salesByDate(from, to)).thenReturn(50000);

        // when: 메뉴 실행
        reportController.ReportServiceSearchMenu();

        // then: 출력된 내용에 메뉴, 프롬프트, 최종 결과가 모두 포함되어 있는지 확인
        String output = outContent.toString();
        assertTrue(output.contains("1. 특정 기간 매출 조회")); // 메뉴 표시 확인
        assertTrue(output.contains("시작날짜 (yyyy-MM-dd) :")); // 프롬프트 확인
        assertTrue(output.contains("총 매출 = 50,000원")); // 결과 확인
        assertTrue(output.contains("메뉴를 종료하고 이전 화면으로 돌아갑니다.")); // 종료 메시지 확인

        // then: 서비스가 정확한 인자로 호출되었는지 검증
        verify(reportService).salesByDate(eq(from), eq(to));
    }

    @Test
    @DisplayName("메뉴 - 인기 상품 조회: 메뉴 '2' 선택 시 TOP N 입력받아 결과를 출력한다")
    void ReportServiceSearchMenu_whenSelectTopProducts_shouldPromptAndPrintResult() {
        // given: 메뉴 '2' -> 숫자 '3' -> 메뉴 '0' 순으로 입력
        String input = "2\n3\n0\n";
        provideInput(input);

        Map<String, Integer> topProducts = new LinkedHashMap<>();
        topProducts.put("P001", 50);
        when(reportService.topProducts(3)).thenReturn(topProducts);

        // when
        reportController.ReportServiceSearchMenu();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("조회할 TOP N 숫자 입력 :")); // 프롬프트 확인
        assertTrue(output.contains("[인기 상품 TOP 3]")); // 결과 헤더 확인
        assertTrue(output.contains("상품ID: P001, 판매수량: 50")); // 결과 내용 확인

        verify(reportService).topProducts(3);
    }

    @Test
    @DisplayName("메뉴 - 잘못된 날짜 형식 입력: 오류 메시지를 출력해야 한다")
    void ReportServiceSearchMenu_whenInvalidDateFormat_shouldShowErrorMessage() {
        // given: 메뉴 '1' -> 잘못된 날짜 형식 -> 메뉴 '0' 순으로 입력
        String input = "1\nnot-a-date\n0\n";
        provideInput(input);

        // when
        reportController.ReportServiceSearchMenu();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("⚠️ 날짜 형식이 올바르지 않습니다."));
    }

    @Test
    @DisplayName("메뉴 - 잘못된 메뉴 번호 입력: 오류 메시지를 출력해야 한다")
    void ReportServiceSearchMenu_whenInvalidMenuSelection_shouldShowErrorMessage() {
        // given: 잘못된 메뉴 '9' -> 종료 '0' 순으로 입력
        String input = "9\n0\n";
        provideInput(input);

        // when
        reportController.ReportServiceSearchMenu();

        // then
        String output = outContent.toString();
        assertTrue(output.contains("⚠️ 잘못된 선택입니다. 다시 입력해 주세요."));
    }
}