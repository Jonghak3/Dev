-- 뷰 (VIEW)
-- 13-14. 뷰 생성 권한 부여 (SYSTEM 계정에서 실행)

-- 13-15. 뷰 생성
-- 20번 부서 직원 정보만 선택적으로 보여주는 논리적 테이블 생성
CREATE VIEW VW_EMP20 
AS (SELECT empno, ename, JOB, deptno
    FROM EMP
    WHERE deptno = 20);     -- 20번 부서만 필터링
    
-- 13-16, 17. 생성된 뷰 확인
SELECT * FROM vw_emp20;

-- 13-18. 뷰 삭제
DROP VIEW VW_EMP20;

-- 13-20. ROWNUM 기본 사용
SELECT ROWNUM, E.* 
FROM EMP E;

-- 13-21. ROWNUM과 ORDER BY 사용 시 이유
SELECT ROWNUM, E.*      -- ROWNUM이 먼저 부여된 후 정렬되므로 순서가 뒤섞임 
FROM EMP E
ORDER BY SAL DESC;

-- 13-22. 서브쿼리를 사용해서 정렬 후 ROWNUM 부여
SELECT ROWNUM, E.*
FROM (
        SELECT *
        FROM EMP
        ORDER BY SAL DESC
    ) E;    -- 서브쿼리에서 먼저 정렬
    
-- 13-23. WITH 절을 사용한 ROWNUM 부여
-- WITH 절 : 임시 결과 집합을 정의 (가독성 향상)
WITH E AS (
            SELECT * 
            FROM EMP
            ORDER BY SAL DESC
            ) -- 임시 테이블 E 정의
SELECT ROWNUM, E.* 
FROM E;

-- 13-24. TOP-N 쿼리 (상위 3명)
-- ROWNUM을 사용한 상위 N개 레코드 추출
SELECT ROWNUM, E.* 
FROM (
    SELECT * 
    FROM EMP
    ORDER BY SAL DESC
    ) E
where rownum <= 3;

---- SEQEUNCE (시퀀스)
-- 13-26: SEQEUNCE 생성 권한 부여 (SYSTEM 계정에서 실행) 
-- GRANT CREATE SEQEUNCE TO SCOTT;

-- 13-27. 테이블 구조 복사
--DROP TABLE DEPT_SEQUENCE;
CREATE TABLE DEPT_SEQUENCE
AS SELECT * 
    FROM DEPT
    WHERE 1 <> 1;   -- 거짓 조건 : 구조만 복사
    
-- 13-28. 시퀀스 생성
--DROP SEQUENCE SEQ_DEPT_SEQUENCE;
CREATE SEQUENCE SEQ_DEPT_SEQUENCE
    INCREMENT BY 10         -- 10씩 증가
    START WITH 10           -- 시작 값 10 
    MAXVALUE 1000             -- 최대값 1000
    MINVALUE 0              -- 최소값 0 
    NOCYCLE                 -- 최대값 도달 시 순환하지 않음 
    CACHE 2;                -- 메모리에 2개씩 미리 생성
    
-- 13-29. 시퀀스 정보 확인
SELECT * FROM user_sequences;

-- 13-30. 시퀀스를 사용한 데이터 입력
-- NEXTVAL : 다음 시퀀스 값 반환 (값 증가함)
INSERT INTO DEPT_SEQUENCE (deptno, dname, loc)
VALUES (seq_dept_sequence.nextval, 'DATABASE', 'SEOUL');

SELECT * FROM dept_sequence;

-- 13-31. 현재 시퀀스 값 확인
-- CURRVAL: 현재 시퀀스 값 반환
SELECT SEQ_DEPT_SEQUENCE.C
URRVAL FROM dual;

-- 13-32. 추가 데이터 입력
INSERT INTO DEPT_SEQUENCE (deptno, dname, loc)
VALUES (seq_dept_sequence.nextval, 'DATABASE', 'SEOUL');

SELECT * FROM dept_sequence;

-- 13-33. 시퀀스 수정
-- ALTER SEQUENCE: 시퀀스 속성 변경 (START WITH는 변경 불가)
 






















