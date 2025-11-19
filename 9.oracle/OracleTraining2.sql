-- INSERT, UPDATE, DELETE

-- 10-1. 테이블 복사 (구조와 데이터 모두)
CREATE TABLE DEPT_TEMP      -- 새 테이블 생성 (CTAS-CREATE TABLE AS SELECT)
    AS SELECT * FROM DEPT;  -- DEPT 테이블의 모든 데이터와 구조를 복사
    
-- 10-2. 복사된 테이블 확인 
select *from dept_temp;

-- 10-3. INSERT - 컬럼명 명시
INSERT INTO dept_temp (deptno, dname, loc)  -- 삽입할 컬럼명 명시
VALUES (50, 'DATABASE', 'SEOUL');           -- 각 컬럼에 대응하는 값    
select *from dept_temp;

-- 10-4. INSERT - 전체 컬럼 (컬럼명 생략)
INSERT INTO dept_temP               -- 컬럼명 생략 시 모든 컬럼에 값 제공
VALUES (60, 'NETWORK', 'BUSAN');    -- 테이블 정의 순서대로 값 입력, 모든 컬럼에 값 제공해야함. 
select *from dept_temp;

-- 10-5. INSERT - NULL 값 명시적 입력
INSERT INTO dept_temp(deptno, dname, loc)
VALUES (70, 'WEB', NULL);       -- LOC컬럼에 NULL저장
select *from dept_temp;

-- 10-6. INSERT - 빈 문자열 입력
INSERT INTO dept_temp(deptno, dname, loc)
VALUES (80, 'MOBILE', '');      -- 빈 문자열이 NULL로 저장됨 확인
select *from dept_temp;

-- 10-7. INSERT - 컬럼 생략 (묵시적 NULL) 
INSERT INTO dept_temp(deptno, loc)  -- DNAME 컬럼 생략
VALUES (90, 'INCHEON');             -- 생략된 컬럼은 자동으로 NULL

select *from dept_temp;

-- 10-8. 테이블 구조만 복사 (데이터 제외)
CREATE TABLE EMP_TEMP       -- 새 테이블 생성
    AS SELECT *             -- 모든 컬럼 선택 
        FROM EMP            -- EMP 테이블에서 
        WHERE 1 <> 1;       -- 항상 FALSE 조건 = 데이터는 복사 안됨
                            -- 구조만 복사하는 트릭
SELECT * FROM emp_temp;

-- 10-9. INSERT - 날짜 문자열 
INSERT INTO emp_temp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (9999, '홍길동', 'PRESIDENT', NULL, '2025/01/01', 5000, 1000, 10);
SELECT * FROM EMP_TEMP;

-- 10-10. INSERT - 다른 날짜 형식 
INSERT INTO emp_temp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (1111, '성춘향', 'MANAGER', 9999, '2025-01-01', 4000, NULL, 20);
SELECT * FROM EMP_TEMP;

-- 10-11. INSERT - 날짜 형식 오류 발생
INSERT INTO emp_temp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (2111, '이순신', 'MANAGER', 9999, '07/01/2001', 4000, NULL, 20);
SELECT * FROM EMP_TEMP;

-- 10-12. INSERT - TO_DATE() 함수 사용
INSERT INTO emp_temp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (2111, '이순신', 'MANAGER', 9999, TO_DATE('07/01/2001', 'DD/MM/YYYY'), 4000, NULL, 20);
SELECT * FROM EMP_TEMP;

-- 10-13. INSERT - SYSDATE 사용
INSERT INTO emp_temp(empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES(3111, '심청이', 'MANAGER', 9999, SYSDATE, 4000, NULL, 30);

SELECT * FROM EMP_TEMP;

-- 10-14. INSERT - 서브쿼리 활용
INSERT INTO emp_temp(empno, ename, job, mgr, hiredate, sal, comm, deptno)
SELECT e.empno, E.ENAME, E.JOB, E.MGR, E.HIREDATE, E.SAL, E.COMM, e.deptno
FROM EMP E, SALGRADE S      -- EMP와 SALGRADE 조인
WHERE e.sal BETWEEN S.LOSAL AND s.hisal -- 급여 범위 조건
AND s.grade = 1;                        -- 급여 등급 1

SELECT * FROM EMP_TEMP;

--==============================
-- 10-15. UPDATE를 위한 테이블 준비
CREATE TABLE DEPT_TEMP2
    AS SELECT * FROM DEPT;
    
SELECT * FROM dept_temp2;

-- 10-16. UPDATE - 조건없이 전체 수정
UPDATE dept_temp2           -- dept_temp2 테이블 수정
    SET loc = 'SEOUL';      -- 모든 행의 LOC를 SEOUL로 변경
                            -- WHERE 절이 없으면 전체 행 수정 (주의!!)
SELECT * FROM dept_temp2;

-- 10-17. ROLLBACK - 변경취소
ROLLBACK;                   -- 이전 COMMIT 시점으로 되돌림 
                            -- UPDATE 작업 취소
SELECT * FROM dept_temp2;    

-- 10-18 UPDATE - 특정 행만 수정
/*
 * DEPT_TEMP2 테이블에서 40번 부서의 정보를 수정하시오
 *      - 부서명을 'DATABASE'로 변경,
 *      - 위치를 'SEOUL'로 변경,
 *      - WHERE를 사용하여 특정 행만 수정하는 방법
 */
UPDATE dept_temp2
SET dname = 'DATABASE',
    LOC = 'SEOUL'
WHERE deptno = 40;

SELECT * FROM dept_temp2;

-- 10-19. UPDATE - 서브쿼리로 여러 컬럼 수정
/*
    detp_temp2 테이블의 40번 부서 정보를 DEPT 테이블의 40번 부서 정보로 복원하시오
        - SET절에 (컬럼1, 컬럼2) = (서브쿼리) 
        - 다른 테이블의 데이터를 참조하여 수정하는 방법
*/
UPDATE dept_temp2
SET (dname, loc) = (SELECT dname, loc
                        FROM dept
                        WHERE dept.deptno = 40
                    )
WHERE deptno = 40;

SELECT * FROM dept_temp2;

-- 10-21 . WHERE절에 서브쿼리
/*
    DEPT_TEMP2 테이블에서 부서명이 'OPERATIONS'인 부서의 위치를 'SEOUL'로 변경하시오. 
        - WHERE절에 서브쿼리를 사용하여 수정할 행 결정
        - 부서명으로 부서번호를 찾아 해당 부서 수정
*/
UPDATE dept_temp2
SET loc = 'SEOUL'
WHERE deptno = (SELECT deptno
                FROM dept_temp2
                WHERE dept_temp2.dname = 'OPERATIONS'
                );
SELECT * FROM dept_temp2;

--===================================
-- 10-22. DELETE를 위한 테이블 준비
/*
    DELETE 실행을 위한 EMP_TEMP2테이블을 생성하시오
        - CTAS (CREATE TABLE AS SELECT)
*/
CREATE TABLE EMP_TEMP2
AS SELECT * FROM EMP;

SELECT * FROM EMP_temp2;

-- 10-23. DELETE - 조건부 삭제 
/*
    EMP_TEMP2 테이블에서 직급(JOB)이 'MANAGER'인 직원들을 삭제하시오. 
        - WHERE절을 이용한 특정 조건의 행 삭제
*/
DELETE FROM emp_temp2
WHERE job = 'MANAGER';
SELECT * FROM emp_temp2;

-- 10-24. DELETE - 복잡한 서브쿼리 조건  
/*
    EMP_TEMP2테이블에서 30번 부서 직원 중 급여등급이 3등급인 직원을 삭제하시오. 
        - 서브쿼리와 조인을 사용한 복잡한 삭제 조건
        - IN 연산자와 서브쿼리를 사용한 DELETE 
        - 여러 테이블의 정보를 참조하여 삭제 대상 결정  
*/
DELETE FROM emp_temp2
WHERE EMPNO IN (SELECT E.EMPNO 
                    FROM EMP_TEMP2 E, SALGRADE S
                    WHERE E.SAL BETWEEN S.LOSAL AND s.hisal
                    AND s.grade = 3
                    AND DEPTNO = 30
                );
SELECT * FROM EMP_temp2;

-- 10-25. DELETE - 전체 삭제
/*
    EMP_TEMP2 테이블의 모든 데이터 삭제하시오
        - WHERE 절 없이 테이블의 모든 행 삭제
        - DELETE vs TRUNCATE 
            - DELETE: ROLLBACK 가능, 느림
            - TRUNCATE: ROLLBACK 불가, 빠름
*/
DELETE FROM emp_temp2;

SELECT * FROM EMP_temp2;










