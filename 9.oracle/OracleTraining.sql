-- desc : 테이블 컬럼명, 데이터타입, NULL 허용여부 등 구조 확인
desc emp;           -- 사원 정보 테이블

select * from emp; -- 모든 컬럼(*), 모든 행 조회

desc dept;          -- 부서 정보 테이블
select * from dept;

desc salgrade;      -- 급여 등급 테이블
select * from salgrade;

--2. 기본 SELECT 문법
--특정 컬럼만 선택 조회
SELECT 


select deptno from emp;
select distinct deptno from emp;

select job, deptno
from emp;

select * from dept;

select ename, sal, sal*12+comm as annsal, comm from emp;
select ename, sal, sal*12+comm as "annsal", comm from emp;
select ename, sal, sal*12+comm annsal, comm from emp;


select * from emp
order by sal;

select * from emp
order by empno;

select * from emp
order by sal desc;

select * from emp
order by empno desc;

-- emp 테이블의 전체 열을 부서 번호(오름차순)와 급여(내림차순)로 정렬하기
select * from emp
order by deptno asc, sal desc;
