-- join lab

/*
 * - Oracle 전통 방식 : where절에 조인조건과 필터 조건 함께
 * - ANSI 표준 방식 : ON절에 조인조건, WHERE절에 필터조건 분리
 * - 외부 조인 표기 : Oracle (+) vs LEFT/RIGHT JOIN
 * - 자체 조인 
 * - 비등가 조인
 */

-- 1. DALLAS에 근무하는 모든 사원의 사번, 이름, 직무, 부서명을 조회하시오

-- ORCLE 전통방식(방언)
SELECT EMPNO ,ENAME ,JOB ,DEPT.DNAME 
FROM EMP, DEPT
WHERE DEPT.DEPTNO = EMP.DEPTNO
AND DEPT.LOC = 'DALLAS';

-- ANSI 표준 방식
SELECT EMPNO ,ENAME ,JOB ,D.DNAME 
FROM EMP e  JOIN DEPT d  ON E.DEPTNO  = D.DEPTNO
WHERE D.LOC = 'DALLAS';

-- 2. 급여 등급별 사원 현황
/*
 * 급여등급이 3등급인 사원들의 이름, 급여, 부서명을 조회하시오.
 * 힌트 : 3개 테이블 조인 필요
 */

-- Oracle 전통방식(방언)
SELECT e.ENAME ,e.SAL ,d.DNAME
FROM EMP e ,DEPT d , SALGRADE s 		-- 3개 테이블 조인
WHERE e.DEPTNO = d.DEPTNO				-- 조인조건1: emp-dept
AND e.SAL BETWEEN s.LOSAL  AND s.HISAL 	-- 조인조건2: 급여가 등급범위 내 (비등가 조인)
AND s.GRADE = 3						-- 필터조건: 3등급만
ORDER BY e.sal;

-- ANSI 표준 방식
SELECT e.ENAME ,e.SAL ,d.DNAME 
FROM EMP e JOIN DEPT d  ON e.DEPTNO = d.DEPTNO -- 첫번째 조인: emp-dept
JOIN SALGRADE s ON e.SAL BETWEEN s.LOSAL AND s.HISAL -- 두번째 조인: 급여범위
WHERE s.GRADE = 3									-- 3등급만 필터링
ORDER BY e.SAL ;


-- SUBQUERT LAB
/* 3.
 * 평균 급여보다 많이 받으면서 MANAGER 직급인 직원의 
 * 사번, 이름, 직급, 급여를 조회하시오.
 */

SELECT AVG(SAL)
FROM EMP

SELECT EMPNO ,ENAME ,JOB ,SAL 
FROM EMP
WHERE SAL > (SELECT AVG(SAL)
FROM EMP)
AND JOB = 'MANAGER'

/*
 * 4. ANY 연산자 사용
 * 
 * SALESNAM 직급 중 누구보다라도 급여를 많이 받는 다른 직급의 직원들을 조회하시오.
 */
SELECT EMPNO ,ENAME ,JOB ,SAL 
FROM EMP
WHERE SAL > ANY (
				SELECT SAL
				FROM EMP 
				WHERE JOB = 'SALESMAN'
				)
AND JOB != 'SALESMAN'
ORDER BY SAL;











