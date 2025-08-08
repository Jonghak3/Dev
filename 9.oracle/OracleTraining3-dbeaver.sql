-- 5-17'
SELECT *
FROM emp 
WHERE sal >= 2000
AND sal <=3000;

-- 5-18
SELECT *
FROM emp 
WHERE sal BETWEEN 2000 AND 3000;

-- 5-19 sal가 2000 ~ 3000 사이 이외의 값 데이터만 출력하시오
SELECT *
FROM emp 
WHERE sal not BETWEEN 2000 AND 3000
ORDER BY sal desc;

-- 5-20 대문자 S로 시작하는 ENAME 열을 조회하시오.
SELECT *
FROM emp
WHERE ename LIKE 'S%';

-- 5-21 사원 이름의 두번째 글자가 L인 데이터를 조회하시오.
SELECT *
FROM emp
WHERE ename LIKE '_L%';

--5-22 사원 이름의 AM포함하는 데이터를 조회하시오.
SELECT *
FROM emp
WHERE ename LIKE '%AM%';

-- 5-23 사원 이름의 AM 미포함하는 데이터를 조회하시오.
SELECT *
FROM emp
WHERE ename not LIKE '%AM%';

-- UNION 
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10
UNION 
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 20;

--
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10
UNION 
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10;

-- all
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10
UNION ALL 
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10;

-- 차집합 minus
SELECT empno, ename, sal, deptno
FROM emp 
minus 
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10;

-- 교집합 intersect
SELECT empno, ename, sal, deptno
FROM emp 
INTERSECT  
SELECT empno, ename, sal, deptno
FROM emp 
WHERE deptno = 10;

-- 이름이 'M'으로 시작하거나 'N'으로 끝나는
-- 직원의 사번, 이름, 입사일을 조회하시오.정렬은 이름
SELECT EMPNO ,ENAME ,HIREDATE 
FROM EMP 
WHERE ENAME LIKE 'M%'
	OR ENAME LIKE '%N';

-- 직무가 'SALESMAN'이 아닌 직원 중에서
-- 급여가 1500이상 3000 이하인 직원의 
-- 이름, 직무, 급여, 연봉을 조회하시오.
SELECT ENAME, JOB, SAL , SAL*12+NVL(COMM, 0) AS "연봉" 
FROM EMP 
WHERE SAL BETWEEN 1500 AND 3000
AND JOB != 'SALESMAN'
ORDER BY SAL;

-- 관리자(MGR)가 있는 직원 중 급여가 2000 이상인 직원의 
-- 이름, 관리자번호, 급여를 조회하고,
-- 관리자번호 순으로 정렬하시오
SELECT ENAME ,MGR ,SAL 
FROM EMP
WHERE MGR IS NOT NULL
AND SAL >=2000
ORDER BY MGR, EMP.ENAME ;

-- 부서번호 10과 30의 직원 중 급여가 1500 이상인
-- 직원을 UNION을 사용하여 조회하시오.
-- 사번, 이름, 급여, 부서번호를 출력하고, 부서번호, 급여기준으로 내림차순 정렬하시오
SELECT EMPNO ,ENAME ,SAL ,DEPTNO 
FROM EMP
WHERE DEPTNO = 10
AND SAL >= 1500
UNION 
SELECT EMPNO ,ENAME ,SAL ,DEPTNO 
FROM EMP
WHERE DEPTNO  = 30
AND SAL >= 1500
ORDER BY DEPTNO ,SAL DESC;