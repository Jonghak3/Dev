-- 데이터베이스 생성 
create database if not exists sboot
character set utf8mb4 	-- MySQL의 표준 UTF-8 인코딩(이모지까지 지원)
collate utf8mb4_general_ci;	-- 대소문자 구분하지 않고 정렬/검색하는 일반 규칙char set

create database if not exists bookjpa;

create database if not exists thymeleaf;

SELECT * FROM librarydb;

USE librarydb;

-- 11-40: 자유게시판 (FREE)
INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('첫 방문 후기!', '오늘 처음 방문했는데 시설이 정말 좋네요. 자주 오고 싶습니다!', 1, 340, 45, 'ACTIVE', 'FREE', NOW() - INTERVAL 59 DAY, NOW() - INTERVAL 59 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('스터디 카페보다 훨씬 좋아요', '조용하고 쾌적해서 집중하기 정말 좋습니다.', 2, 520, 68, 'ACTIVE', 'FREE', NOW() - INTERVAL 58 DAY, NOW() - INTERVAL 58 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주말에도 사람이 많나요?', '주말에 방문하려고 하는데 자리가 있을까요?', 3, 280, 32, 'ACTIVE', 'FREE', NOW() - INTERVAL 57 DAY, NOW() - INTERVAL 57 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('커피 자판기 추가 요청', '2층에도 커피 자판기가 있으면 좋겠어요!', 1, 450, 55, 'ACTIVE', 'FREE', NOW() - INTERVAL 56 DAY, NOW() - INTERVAL 56 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('와이파이 속도가 빨라요', '인터넷 속도가 빨라서 온라인 강의 듣기 좋습니다.', 2, 380, 41, 'ACTIVE', 'FREE', NOW() - INTERVAL 55 DAY, NOW() - INTERVAL 55 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주차하기 편해요', '주차 공간이 넓어서 좋네요.', 3, 290, 28, 'ACTIVE', 'FREE', NOW() - INTERVAL 54 DAY, NOW() - INTERVAL 54 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('어린이 열람실 추천', '아이와 함께 오기 좋은 공간입니다.', 1, 510, 62, 'ACTIVE', 'FREE', NOW() - INTERVAL 53 DAY, NOW() - INTERVAL 53 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('스터디룸 예약 꿀팁', '스터디룸은 아침 일찍 예약하는 게 좋아요!', 2, 680, 89, 'ACTIVE', 'FREE', NOW() - INTERVAL 52 DAY, NOW() - INTERVAL 52 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('점심시간 혼잡도 어떤가요?', '평일 점심시간에 사람이 많이 있나요?', 3, 320, 35, 'ACTIVE', 'FREE', NOW() - INTERVAL 51 DAY, NOW() - INTERVAL 51 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('야간 개관 시간 연장 건의', '평일 야간 시간을 22시까지 연장해주시면 감사하겠습니다.', 1, 720, 95, 'ACTIVE', 'FREE', NOW() - INTERVAL 50 DAY, NOW() - INTERVAL 50 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('신간 소설 추천해주세요', '요즘 읽을만한 신간 소설 있으면 추천 부탁드려요!', 2, 430, 52, 'ACTIVE', 'FREE', NOW() - INTERVAL 49 DAY, NOW() - INTERVAL 49 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('독서 모임 만들고 싶어요', '독서 모임 같이 하실 분 계신가요?', 3, 560, 71, 'ACTIVE', 'FREE', NOW() - INTERVAL 48 DAY, NOW() - INTERVAL 48 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('오늘 날씨 좋네요', '창가 자리에서 책 읽기 딱 좋은 날씨입니다.', 1, 210, 25, 'ACTIVE', 'FREE', NOW() - INTERVAL 47 DAY, NOW() - INTERVAL 47 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('북카페 분위기 최고', '1층 북카페 분위기가 정말 좋아요!', 2, 490, 58, 'ACTIVE', 'FREE', NOW() - INTERVAL 46 DAY, NOW() - INTERVAL 46 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('열람실 온도가 적당해요', '에어컨 온도가 딱 좋네요. 쾌적합니다.', 3, 270, 30, 'ACTIVE', 'FREE', NOW() - INTERVAL 45 DAY, NOW() - INTERVAL 45 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주차비 무료인가요?', '주차비 관련해서 궁금합니다.', 1, 380, 42, 'ACTIVE', 'FREE', NOW() - INTERVAL 44 DAY, NOW() - INTERVAL 44 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('복사 서비스 가격', '복사/인쇄 서비스 이용 가능한가요?', 2, 320, 36, 'ACTIVE', 'FREE', NOW() - INTERVAL 43 DAY, NOW() - INTERVAL 43 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('오랜만에 왔는데 깨끗해요', '시설 관리가 정말 잘 되어있네요!', 3, 440, 53, 'ACTIVE', 'FREE', NOW() - INTERVAL 42 DAY, NOW() - INTERVAL 42 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('음료 반입 가능한가요?', '텀블러에 커피 담아와도 되나요?', 1, 510, 61, 'ACTIVE', 'FREE', NOW() - INTERVAL 41 DAY, NOW() - INTERVAL 41 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('좌석 예약 시스템 편해요', '모바일로 미리 예약할 수 있어서 좋습니다.', 2, 590, 73, 'ACTIVE', 'FREE', NOW() - INTERVAL 40 DAY, NOW() - INTERVAL 40 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('1층 카페 커피 맛있어요', '도서관 내 카페 커피가 의외로 맛있네요!', 3, 410, 48, 'ACTIVE', 'FREE', NOW() - INTERVAL 39 DAY, NOW() - INTERVAL 39 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('자습하기 정말 좋은 곳', '수험생에게 최고의 장소입니다.', 1, 670, 82, 'ACTIVE', 'FREE', NOW() - INTERVAL 38 DAY, NOW() - INTERVAL 38 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('휴게실이 따로 있나요?', '잠깐 쉴 수 있는 공간이 있는지 궁금해요.', 2, 290, 33, 'ACTIVE', 'FREE', NOW() - INTERVAL 37 DAY, NOW() - INTERVAL 37 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('전공 서적 추천 좀 해주세요', '컴퓨터공학 전공 서적 추천 부탁드립니다.', 3, 530, 64, 'ACTIVE', 'FREE', NOW() - INTERVAL 36 DAY, NOW() - INTERVAL 36 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('오늘도 만석이네요', '역시 시험기간이라 사람이 많네요.', 1, 350, 40, 'ACTIVE', 'FREE', NOW() - INTERVAL 35 DAY, NOW() - INTERVAL 35 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('조명이 눈에 편해요', '조명이 따뜻한 색이라 눈이 편합니다.', 2, 280, 31, 'ACTIVE', 'FREE', NOW() - INTERVAL 34 DAY, NOW() - INTERVAL 34 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('휴일에 개관하나요?', '공휴일에도 이용 가능한가요?', 3, 420, 49, 'ACTIVE', 'FREE', NOW() - INTERVAL 33 DAY, NOW() - INTERVAL 33 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('친구와 함께 공부하기 좋아요', '2인 책상이 많아서 좋습니다.', 1, 480, 56, 'ACTIVE', 'FREE', NOW() - INTERVAL 32 DAY, NOW() - INTERVAL 32 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('전자책 대여 서비스 굿!', '모바일 전자책 서비스 정말 편리해요!', 2, 620, 77, 'ACTIVE', 'FREE', NOW() - INTERVAL 31 DAY, NOW() - INTERVAL 31 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('직원분들 친절해요', '직원분들이 정말 친절하십니다. 감사합니다!', 3, 390, 44, 'ACTIVE', 'FREE', NOW() - INTERVAL 30 DAY, NOW() - INTERVAL 30 DAY);

-- 41-70: 질문답변 (QNA)
INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원가입은 어떻게 하나요?', '온라인 회원가입 절차가 궁금합니다.', 1, 450, 38, 'ACTIVE', 'QNA', NOW() - INTERVAL 58 DAY, NOW() - INTERVAL 58 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('대출 기간은 며칠인가요?', '일반 도서 대출 기간이 궁금해요.', 2, 520, 45, 'ACTIVE', 'QNA', NOW() - INTERVAL 57 DAY, NOW() - INTERVAL 57 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('연장 신청은 몇 번까지 가능한가요?', '대출 연장에 제한이 있나요?', 3, 380, 32, 'ACTIVE', 'QNA', NOW() - INTERVAL 56 DAY, NOW() - INTERVAL 56 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('분실 도서 처리 방법', '책을 분실했을 때 어떻게 처리하나요?', 1, 680, 52, 'ACTIVE', 'QNA', NOW() - INTERVAL 55 DAY, NOW() - INTERVAL 55 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원증 재발급 방법', '회원증을 잃어버렸는데 재발급 받을 수 있나요?', 2, 340, 28, 'ACTIVE', 'QNA', NOW() - INTERVAL 54 DAY, NOW() - INTERVAL 54 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('희망도서 신청 후 얼마나 걸리나요?', '신청한 도서는 언제쯤 들어오나요?', 3, 490, 41, 'ACTIVE', 'QNA', NOW() - INTERVAL 53 DAY, NOW() - INTERVAL 53 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('어린이 회원 가입 가능한가요?', '초등학생도 회원 가입이 가능한가요?', 1, 410, 35, 'ACTIVE', 'QNA', NOW() - INTERVAL 52 DAY, NOW() - INTERVAL 52 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('비회원도 열람실 이용 가능한가요?', '회원 가입 안 해도 열람실 사용할 수 있나요?', 2, 580, 48, 'ACTIVE', 'QNA', NOW() - INTERVAL 51 DAY, NOW() - INTERVAL 51 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('예약 도서 찾는 방법', '예약한 도서가 들어왔는지 어떻게 확인하나요?', 3, 320, 27, 'ACTIVE', 'QNA', NOW() - INTERVAL 50 DAY, NOW() - INTERVAL 50 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('스터디룸 예약 시간', '스터디룸은 몇 시간까지 이용 가능한가요?', 1, 540, 44, 'ACTIVE', 'QNA', NOW() - INTERVAL 49 DAY, NOW() - INTERVAL 49 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('도서 검색 방법', '원하는 책을 빨리 찾는 방법이 있나요?', 2, 290, 24, 'ACTIVE', 'QNA', NOW() - INTERVAL 48 DAY, NOW() - INTERVAL 48 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('전자책 대여 방법', '전자책은 어떻게 빌리나요?', 3, 470, 39, 'ACTIVE', 'QNA', NOW() - INTERVAL 47 DAY, NOW() - INTERVAL 47 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('회원 등급 상향 조건', 'SILVER 등급으로 올라가려면 어떻게 해야 하나요?', 1, 620, 51, 'ACTIVE', 'QNA', NOW() - INTERVAL 46 DAY, NOW() - INTERVAL 46 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('무인 반납함 사용법', '무인 반납함은 어떻게 사용하나요?', 2, 350, 29, 'ACTIVE', 'QNA', NOW() - INTERVAL 45 DAY, NOW() - INTERVAL 45 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주말 개관 시간 문의', '주말에는 몇 시부터 열리나요?', 3, 430, 36, 'ACTIVE', 'QNA', NOW() - INTERVAL 44 DAY, NOW() - INTERVAL 44 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('대출 중인 책 예약 방법', '이미 대출된 책을 예약할 수 있나요?', 1, 510, 42, 'ACTIVE', 'QNA', NOW() - INTERVAL 43 DAY, NOW() - INTERVAL 43 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('연체료 확인 방법', '내가 낸 연체료는 어디서 확인하나요?', 2, 390, 33, 'ACTIVE', 'QNA', NOW() - INTERVAL 42 DAY, NOW() - INTERVAL 42 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('외국 서적도 있나요?', '영어 원서를 빌릴 수 있나요?', 3, 280, 23, 'ACTIVE', 'QNA', NOW() - INTERVAL 41 DAY, NOW() - INTERVAL 41 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('복사 서비스 이용 방법', '복사는 어디서 할 수 있나요?', 1, 320, 26, 'ACTIVE', 'QNA', NOW() - INTERVAL 40 DAY, NOW() - INTERVAL 40 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('열람실 자리 배정', '열람실 좌석은 지정제인가요?', 2, 450, 37, 'ACTIVE', 'QNA', NOW() - INTERVAL 39 DAY, NOW() - INTERVAL 39 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('노트북 사용 가능 구역', '노트북은 어디서 사용할 수 있나요?', 3, 530, 44, 'ACTIVE', 'QNA', NOW() - INTERVAL 38 DAY, NOW() - INTERVAL 38 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('와이파이 비밀번호', '와이파이 접속 방법이 궁금해요.', 1, 410, 34, 'ACTIVE', 'QNA', NOW() - INTERVAL 37 DAY, NOW() - INTERVAL 37 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('그룹 스터디룸 인원 제한', '스터디룸은 몇 명까지 이용 가능한가요?', 2, 370, 31, 'ACTIVE', 'QNA', NOW() - INTERVAL 36 DAY, NOW() - INTERVAL 36 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('주차 시간 제한', '주차는 몇 시간까지 무료인가요?', 3, 490, 40, 'ACTIVE', 'QNA', NOW() - INTERVAL 35 DAY, NOW() - INTERVAL 35 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('신분증 필수인가요?', '신분증이 없어도 이용할 수 있나요?', 1, 310, 25, 'ACTIVE', 'QNA', NOW() - INTERVAL 34 DAY, NOW() - INTERVAL 34 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('음식물 반입 금지인가요?', '간단한 간식은 괜찮나요?', 2, 420, 35, 'ACTIVE', 'QNA', NOW() - INTERVAL 33 DAY, NOW() - INTERVAL 33 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('휴관일은 언제인가요?', '정기 휴관일이 있나요?', 3, 270, 22, 'ACTIVE', 'QNA', NOW() - INTERVAL 32 DAY, NOW() - INTERVAL 32 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('모바일 회원증 사용', '모바일 회원증으로도 대출이 가능한가요?', 1, 560, 46, 'ACTIVE', 'QNA', NOW() - INTERVAL 31 DAY, NOW() - INTERVAL 31 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('단체 견학 신청', '학교 단체로 방문하려면 어떻게 하나요?', 2, 340, 28, 'ACTIVE', 'QNA', NOW() - INTERVAL 30 DAY, NOW() - INTERVAL 30 DAY);

INSERT INTO board (title, content, author_id, view_count, like_count, status, category, created_at, updated_at) 
VALUES ('도서 기증 방법', '집에 있는 책을 기증하고 싶은데 가능한가요?', 3, 480, 39, 'ACTIVE', 'QNA', NOW() - INTERVAL 29 DAY, NOW() - INTERVAL 29 DAY);


