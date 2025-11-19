-- DML : INSERT, UPDATE, DELETE

-- 테이블 생성
CREATE TABLE EMP_WORK AS SELECT * FROM EMP;
CREATE TABLE DEPT_WORK AS SELECT * FROM DEPT;
CREATE TABLE SALGRADE_WORK AS SELECT * FROM salgrade;

-- 1. 신입사원 입사 처리 
/*
    2025년 신입사원 3명 입사했습니다.
    이순신(개발팀), 신사임당(마케팅팀), 손흥민(개발팀) 
*/
DESC EMP_WORK;
SELECT EMPNO FROM emp_work;
SELECT MAX(EMPNO) FROM emp_work;    -- 현재 최대 사번

SELECT DEPTNO, DNAME FROM dept_work;

ALTER TABLE EMP_WORK
MODIFY ENAME VARCHAR2(30);

INSERT INTO emp_work (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES (8000, '이순신', 'ANALYST', 7902, SYSDATE, 2000, NULL, 20);

INSERT INTO emp_work (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES (8001, '신사임당', 'SALESMAN', 7844, SYSDATE, 2000, NULL, 30);

INSERT INTO emp_work (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)
VALUES (8002, '손흥민', 'ANALYST', 7902, SYSDATE, 2000, NULL, 20);

SELECT * FROM emp_work WHERE EMPNO >= 7935;

-- 2. 부서 통폐합 
/*
    30번 부서(SALES)와 40번 부서(OPERATIONS)를 통합하여 
    35번 부서(SALES_OP)로 만들고, 직원들을 이동시키시오. 
*/

-- STEP1. 새 부서 생성하기
DESC DEPT_WORK;
SELECT * FROM dept_work;
SELECT * FROM emp_work;
INSERT INTO dept_work (DEPTNO, dname, loc)
VALUES (35, 'SALES_OP', 'SEOUL');

-- STEP2. 직원들 부서 이동
UPDATE emp_work
SET deptno = 35 
WHERE deptno IN (30, 40);

-- STEP3. 기존 부서 삭제하기
DELETE FROM dept_work
WHERE deptno IN (30, 40);































