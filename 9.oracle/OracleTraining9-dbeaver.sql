--7장: 다중행 함수화 데이터 그룹화

-- 7-2: sum()함수
-- 특정 열의 모든 값을 더하는 함수
SELECT ENAME, SUM(SAL )	
FROM emp;		-- 그룹 함수는 일반 컬럼과 함께 사용 불가 (=> group by)

-- 7-3: null 값 처리
SELECT SUM(COMM )	--sum()함수는 null값을 자동으로 제외하고 계산함
FROM emp;

-- 7-4: distinct와 all 옵션
SELECT SUM(SAL ),
		SUM(ALL SAL),	-- ALL : 모든 값 포함하여 합계 (기본값)
		sum(DISTINCT SAL )	--distinct : 중복 제거 후 합계 (동일 급여는 한 번만)
FROM emp;

-- 7-5: count(*) - 테이블의 전체 행 개수 세기
SELECT COUNT(*)
FROM emp;

-- 7-6: 조건부 count(*)	-- 조건을 만족하는 행의 개수
SELECT COUNT(*)
FROM emp
WHERE DEPTNO = 30;

-- 7-7: count의 DISTINCT 옵션
SELECT COUNT(SAL ),		-- null 제외한 모든 급여 개수
		COUNT(ALL SAL ),
		COUNT(DISTINCT SAL ) -- 중복 제거한 급여 종류의 개수
FROM emp;

-- 7-8: count()와 null처리
SELECT COUNT(COMM )
FROM emp;

-- 7-9: null 명시적 제외
SELECT COUNT(COMM )
FROM emp
WHERE comm IS NOT NULL;

-- 7-10: max()함수
SELECT MAX(SAL )
FROM emp
WHERE deptno = 10;

-- 7-11: min()함수
SELECT Min(SAL )
FROM emp
WHERE deptno = 10;

-- 7-12: 날짜 데이터의 max()
SELECT MAX(HIREDATE  ) -- max()는 날짜에도 사용 가능 (가장 최근 날짜)
FROM emp
WHERE deptno = 20;

-- 7-14: avg()
SELECT AVG(SAL )
FROM emp
WHERE DEPTNO = 30;

-- 7-15: distinct avg()
SELECT AVG(DISTINCT SAL )	-- 중복 급여 제거 후 평균 계산
FROM emp
WHERE DEPTNO = 30;

/*
 * 시나리오 - 당신은 IT 컨설팅 회사의 데이터 분석가입니다.
 * 			CEO가 긴급 경영회의를 위해 인사이트를 요청했습니다.
 * 			숨겨진 패턴을 찾아보세요.
 */
-- 1. 우리 회사의 인건비 구조가 건전한가요?
-- 1-1. 현황 대시보드
SELECT 
	COUNT(*) AS "총 직원수",
	COUNT(distinct JOB ) AS "직책 종류",
	count(DISTINCT DEPTNO ) AS "운영부서 수",
	SUM(sal) AS "월 급여총액",		-- 월 인건비
	SUM(SAL )*12 AS "연간인건비",
	ROUND(AVG(SAL ), 2) AS "평균급여",
	MAX(SAL ) AS "최그급여",
	MIN(sal) AS "최저급여",
	MAX(sal) - MIN(SAL ) AS "급여격차",
	ROUND(STDDEV(SAL ), 2) AS "급여 편차"
FROM emp

-- 2. 우리 회사의 부서별 비용 구조를 분석하시오.
-- 2-1. 부서별 인건비 집중도
SELECT DEPTNO AS "부서번호",
		COUNT(*) AS "인원",
		SUM(SAL ) AS "부서 총 인건비",
		ROUND(AVG(SAL), 2) AS "부서평균급여",
		MAX(SAL) AS "부서최고급여",
		MIN(SAL) AS "부서최저급여",
		MAX(SAL) - MIN(SAL) AS "부서내격차"
FROM EMP
GROUP BY DEPTNO
ORDER BY "부서 총 인건비" DESC;

-- 7-17 : Group by 절 - 데이터의 그룹화
-- 부서별 평균 급여
SELECT DEPTNO , AVG(SAL )
FROM emp
GROUP BY deptno;	-- GROUP BY: 특정 컬럼 기준으로 그룹 생성 

-- 7-18: 다중 컬럼 GROUP BY
-- 부서별, 직무별 평균 급여
SELECT DEPTNO ,JOB ,AVG(SAL )
FROM emp
GROUP BY deptno, job	-- 부서와 직무 조합으로 그룹화
ORDER BY DEPTNO ,JOB ;

-- 7-19: GROUP BY 오류
SELECT ENAME ,DEPTNO ,JOB ,AVG(SAL )	-- ename이 GROUP BY에 없음
FROM emp
GROUP BY deptno, job	-- 부서와 직무 조합으로 그룹화
ORDER BY DEPTNO ,JOB ;

-- 7-20: HAVING 절 - 그룹 조건
SELECT DEPTNO ,JOB ,AVG(SAL )
FROM EMP
GROUP BY DEPTNO, JOB
HAVING AVG(SAL) >= 2000	-- 평균 급여 2000이상인 그룹만 표시
ORDER BY DEPTNO ,JOB ;

-- 7-21: WHERE에 집계 함수 사용 시 오류남
SELECT DEPTNO ,JOB ,AVG(SAL )
FROM EMP
WHERE AVG(SAL ) >= 2000	-- WHERE절에는 집계 함수 사용 불가 => 오류!
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO ,JOB ;

-- 7-23: WHERE과 HAVING 함께 사용 가능
SELECT DEPTNO ,JOB ,AVG(SAL )
FROM EMP
WHERE SAL <= 3000		-- WHERE: 개별 행 필터링 (그룹화 전)
GROUP BY DEPTNO, JOB	-- 급여 3000 이하인 직원들만 그룹
HAVING AVG(SAL) >= 2000	-- HAVING: 그룹 필터링 (그룹화 후)
ORDER BY DEPTNO ,JOB ;	-- 그룹 평균이 2000 이상인 것만 표시







