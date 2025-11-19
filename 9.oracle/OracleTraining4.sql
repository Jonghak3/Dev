-- 오라클 트랜잭션
-- COMMIT, ROLLBCAK, TCL

-- 11-1. 실습용 테이블 생성
CREATE TABLE DEPT_TCL
AS SELECT * FROM DEPT;

SELECT * FROM dept_tcl;

-- 11-2. DML 작업 수행
/*
    DEPT_TCL 테이블에 여러 DML 작업 수행하시오 (COMMIT 하지 말 것)
        - INSERT : 50번 부서 추가 (DATABASE, SEOUL) 
        - UPDATE : 40번 부서의 위치를 BUSAN으로 변경
        - DELETE : RESEARCH 부서 삭제 
        
        - 트랜잭션이 자동으로 시작됨
        - 아직 메모리에만 존재하고 DB에 반영되지 않음
*/
INSERT INTO dept_tcl VALUES (50, 'DATABASE', 'SEOUL');
UPDATE dept_tcl SET loc = 'BUSAN' WHERE deptno = 40;
DELETE FROM dept_tcl WHERE dname = 'RESEARCH';
SELECT * FROM dept_tcl;

-- 11-3. ROLLBACK - 변경사항 취소
/*
    - ROLLBACK 명령어 사용
    - 트랜잭션 시작 시점으로 데이터 복구
    - INSERT, UPDATE, DELETE가 모두 취소됨
*/
ROLLBACK;

-- 11-4. 새로운 트랜잭션 시작
INSERT INTO dept_tcl VALUES (50, 'NETWORK', 'SEOUL');
UPDATE dept_tcl SET loc = 'BUSAN' WHERE deptno = 20;
DELETE FROM dept_tcl WHERE DEPTNO = 40;
SELECT * FROM dept_tcl;

-- 11-5 : COMMIT - 변경사항 영구 저장 
/*
    - COMMIT 명령어로 트랜잭션 완료 
    - 변경사항이 DB에 영구 저장됨
    - COMMIT 후에는 ROLLBACK 불가능함
    - ROLLBACK을 시도해도 데이터가 유지됨
*/
COMMIT;
SELECT * FROM dept_tcl;
ROLLBACK;

-- 11-6. 다중 세션과 격리성
-- 세션 1
SELECT * FROM dept_tcl;

-- 11-7. 세션 1에서 DELETE (COMMIT 전) 
/*
    - 세션 1. 50번 부서 삭제 (COMMIT 전)
    - 세션 1. 삭제 결과 확인 (50번 부서 없음)
    - 세션 2. 조회 시 50번 부서가 여전히 보임
*/
DELETE FROM dept_tcl WHERE DEPTNO = 50;
SELECT * FROM dept_tcl;

-- 11-8. COMMIT으로 다른 세션에 반영
/*
    세션 1의 변경사항을 COMMIT하여 세션 2에 반영되는지 확인하시오
        - COMMIT 후 변경상항이 모든 세션에 반영됨
*/

-- 세션1
COMMIT;
SELECT * FROM dept_tcl;

-- 11-9. 일관성 확인 (SELECT)
/*
    두 세션에서 동일한 데이터를 조회하여 읽기 일관성 확인 (Consistency)
*/ 
SELECT * FROM dept_tcl;

-- 11-10. 세션 1에서 UPDATE (COMMIT 전) 
/*
    - 세션 1. 30번 부서의 위치를 SEOUL로 변경
    - 세션 1. 변경 확인
    - 세션 2. 아직 원래 값이 보임
    - COMMIT 전까지는 다른 세션에 영향 없음.
*/ 
UPDATE dept_tcl SET LOC = 'SEOUL' WHERE DEPTNO = 30;
SELECT * FROM dept_tcl;

-- 11-11. 두 세션이 동일한 행을 수정할 때 발생하는 락 대기 상황 발생함
/*
    - 세션1. 30번 부서를 수정 중 (COMMIT 안함)
    - 세션2. 동일한 30번 부서 수정 시도
    - 세션2가 대기 상태가 됨 (행 레벨 락)
        - 세션2는 세션1이 COMMIT/ROLLBACK 할 때까지 대기함
*/

-- 11-12. 세션1 COMMIT으로 락 해제
-- 세션1
COMMIT;

-- 11-13. 모든 변경사항 확인
/*
    - 세션1. 최종 데이터 확인
    - 세션2. 최종 데이터 확인
*/
SELECT * FROM dept_tcl;















