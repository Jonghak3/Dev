-- 날짜 함수
-- 6-23: SYSDATE - 현재 날짜/시간
-- 날짜에 숫자를 더하거나 빼면 일(day) 단위로 계산
SELECT 
	sysdate AS now,
	sysdate -1 yesterday,
	sysdate +1 tomorrow
FROM dual;

-- 6-24: ADD_MONTHS() - 월 단위 날짜 계산
-- 정확한 월 계산이 필요할 때 사용 (윤년 등 자동 처리)
SELECT sysdate,
		ADD_MONTHS(sysdate, 3)	-- 3개월 후
FROM dual;

-- 6-25: 입사 10년차 날짜 계산
-- 120개월 = 10년
SELECT EMPNO ,ENAME ,HIREDATE ,
		ADD_MONTHS(HIREDATE ,120) AS work10years
FROM emp;	

-- 6-26: 근속 40년 이상 직원 찾기
-- 40년 = 480개월 
/*
 * 입사일+40년	  <	 현재날짜
 * ADD_MONTHS(HIREDATE ,480)	sysdate
 */
SELECT EMPNO ,ENAME ,HIREDATE , sysdate
FROM emp
WHERE ADD_MONTHS(HIREDATE ,480) <sysdate;	-- 입사 40년이 지난 직원

-- 6-27: MONTH_BETWEEN() - 두 날짜 사이의 개월 수
/*
 *MONTH_BETWEEN(날짜1, 날짜2)
 *	- 날짜1에서 날짜2를 뺀 개월 수를 반환
 *	- 날짜1이 미래면 양수, 과거면 음수
 */
SELECT 
	EMPNO ,ENAME ,HIREDATE ,SYSDATE,
	MONTHS_BETWEEN(HIREDATE ,SYSDATE) AS MONTH1,		--음수
	MONTHS_BETWEEN(SYSDATE, HIREDATE) AS MONTH2,			--양수(근속 개월)
	TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) AS MONTH3
FROM EMP;

-- 6-28: NEXT_DAY() : 다음 특정 요일 날짜
-- LAST_DAY() : 해당 월의 마지막 날
SELECT 
	SYSDATE,
	NEXT_DAY(SYSDATE, '월요일'),	-- 다음 월요일 날짜
	LAST_DAY(SYSDATE)			-- 이번 달 마지막 날
FROM DUAL;

-- 6-29 : 날짜 ROUND() : 반올림
-- ROUND(DATE, FORMAT) : 날짜를 지정된 단위로 반올림하는 함수
-- CC: 세기, YYYY: 년, Q: 분기, DDD: 일, HH: 시간
SELECT SYSDATE,		-- 현재 시스템 날짜/시간
		ROUND(sysdate, 'cc') AS format_cc,	-- 세기단위 반올림, 50년 기준으로 반올림
		ROUND(sysdate, 'yyyy') AS format_yyyy, -- 년 단위 반올림, 7월 1일 기준
		ROUND(sysdate, 'q') AS format_q,	-- 분기 단위 반올림, 각 분기 중간지점 기준
		ROUND(sysdate, 'ddd') AS format_ddd,	-- 일 단위 반올림, 12:00 기준
		ROUND(sysdate, 'hh') AS format_hh	-- 시간 단위 반올림, 30분 기준
FROM DUAL;

-- 6-30 : 날짜 TRUNC() : 버림
-- TRUNC(DATE, FORMAT) : 날짜를 지정된 단위로 버림하는 함수
-- CC: 세기, YYYY: 년, Q: 분기, DDD: 일, HH: 시간
SELECT SYSDATE,		-- 현재 시스템 날짜/시간
		TRUNC(sysdate, 'cc') AS format_cc,	-- 세기단위 버림, 50년 기준으로 버림
		TRUNC(sysdate, 'yyyy') AS format_yyyy, -- 년 단위 버림, 7월 1일 기준
		TRUNC(sysdate, 'q') AS format_q,	-- 분기 단위 버림, 각 분기 중간지점 기준
		TRUNC(sysdate, 'ddd') AS format_ddd,	-- 일 단위 버림, 12:00 기준
		ROUND(sysdate, 'hh') AS format_hh	-- 시간 단위 버림, 30분 기준
FROM DUAL;

-- 형 변환 함수 (Type Conversion Functions)

-- 6-31 : 자동 형 변환 (암시적 변환)
-- 숫자 + 문자(숫자형태) = 자동으로 숫자로 변환
SELECT EMPNO ,ENAME ,EMPNO + '500'	-- '500'이 숫자 500으로 자동 변환
FROM emp
WHERE ename = 'SCOTT';

-- 6-32 : 형 변환 오류
-- 숫자로 변환 불가능한 문자는 에러 발생
SELECT 'ABCD' + EMPNO ,EMPNO 	-- 실행 시 오류 발생!
FROM emp
WHERE ENAME = 'SCOTT';

-- 6-33 : TO_CHAR -- 날짜를 문자로 변환
-- 날짜 포맷 지정
SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS') AS 현재날짜시간
FROM DUAL;

-- 6-34 : TO_CHAR 날짜 포맷 종류
-- TO_CHAR(DATE, FORMAT) : 날짜를 지정된 형식의 문자열로 변환하는 함수
SELECT SYSDATE,		-- 현재 시스템 날짜/시간
	TO_CHAR(SYSDATE, 'MM') AS MM,		-- 월(숫자)을 2자리 숫자로 표시(01~12)
	TO_CHAR(SYSDATE, 'MON') AS MON,		-- 월(약어)을 3자리 약어로 표시 (한글: '8월')
	TO_CHAR(SYSDATE, 'MONTH') AS MONTH,	-- 월(전체)을 전체이름으로 표시 (공백으로 9자리 채움)
	TO_CHAR(SYSDATE, 'DD') AS DD,	-- 일을 2자리 숫자로 표시(01~31)
	TO_CHAR(SYSDATE, 'DY') AS DY,	-- 요일(약어)을 약어로 표시 (한글: '수')
	TO_CHAR(SYSDATE, 'DAY') AS DAY	-- 요일을 전체 이름으로 표시 (공백으로 9자리 채움)
FROM DUAL;

-- 6-37 : TO_CHAR 시간 포맷 종류
-- TO_CHAR()로 시간을 다양한 형식으로 표현 
-- HH24: 24시간제, HH12: 12시간제, AM/PM: 오전/오후
SELECT SYSDATE,
		TO_CHAR(SYSDATE, 'HH24:MI:SS') AS HH24MISS,	-- 14:30:45 => '14:30:45'
		TO_CHAR(SYSDATE, 'HH12:MI:SS AM') AS HH12MISS,
		TO_CHAR(SYSDATE, 'HH:MI:SS P.M.') AS HHMISS_PM
FROM DUAL;

-- 6-41 : TO_NUMBER() - 문자를 숫자로 명시적 변환 
-- TO_NUMBER(STRING, FORMAT) : 문자열을 지정된 형식에 맞춰 숫자로 변환
-- 포맷을 지정하여 변환
SELECT TO_NUMBER('1,300', '999,999') - TO_NUMBER('1,500', '999,999')
FROM DUAL;

-- 6-42 : TO_DATE() - 문자를 날짜로 변환
-- TO_DATE(STRING, FORMAT): 문자열을 지정된 형식에 맞춰 날짜로 변환
-- 다양한 날짜 형식을 처리 가능
SELECT TO_DATE('2025-08-13', 'YYYY-MM-DD') AS TODATE1,
	   TO_DATE('2025-08-13', 'YYYY/MM/DD') AS TODATE2
FROM DUAL;

-- 6-43 : TO_DATE()을 WHERE절에서 사용
SELECT *
FROM EMP
WHERE HIREDATE > TO_DATE('1981/06/01', 'YYYY/MM/DD');

-- 6-44 : YY, RR
-- YY : 현재 세기로 해석 (2000년대)
-- RR : 50년 기준으로 세기 결정 (00~49 : 2000년대, 50~99 : 1900년대)
-- 연도 2자리 사용 시 RR 사용 권장
SELECT TO_CHAR(TO_DATE('49/12/10', 'YY/MM/DD'), 'YYYY-MM-DD') AS YY_YEAR_49,
		-- YY 형식: 49 -> 현재 세기 (2000년대) + 49 = 2049년
		TO_CHAR(TO_DATE('49/12/10', 'RR/MM/DD'), 'YYYY-MM-DD') AS RR_YEAR_49,
		-- RR 형식: 49 -> 00~49 범위이므로 2000년대 => 2049년
		TO_CHAR(TO_DATE('50/12/10', 'YY/MM/DD'), 'YYYY-MM-DD') AS YY_YEAR_50,
		-- YY 형식: 50 -> 현재 세기 (2000년대) + 50 = 2050년
		TO_CHAR(TO_DATE('50/12/10', 'RR/MM/DD'), 'YYYY-MM-DD') AS RR_YEAR_50
		-- RR 형식: 50 -> 50~99 범위이므로 1900년대 => 1950년
FROM DUAL;

-- 입사 기념일 찾기
SELECT 
	ENAME ,
	HIREDATE ,
	TO_CHAR(HIREDATE , 'MM-DD') AS "입사_월일",
	-- 올해_입사기념일_다음_월요일
	NEXT_DAY(TO_DATE(TO_CHAR(SYSDATE, 'YYYY') || TO_CHAR(HIREDATE ,'-MM-DD')), '월요일')
	AS "올해_입사기념일_다음_월요일"
FROM EMP;

