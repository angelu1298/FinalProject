--운동게시판

CREATE TABLE EBOARD(
E_NO NUMBER 				/* 글번호(PK) */
CONSTRAINT EBOARD_E_NO_PK PRIMARY KEY,
MEM_NO NUMBER, 				/* 작성자회원번호 (FK)*/
E_SJ VARCHAR2(100)			/* 글제목 */
CONSTRAINT EBOARD_E_SJ_NN NOT NULL,
E_CT CLOB					/* 글내용 */
CONSTRAINT EBOARD_E_CT_NN NOT NULL,
E_FL VARCHAR2(1024),		/* 업로드파일경로 */
--E_GL VARCHAR2(1024),		/* 업로드이미지경로 */
E_RC NUMBER DEFAULT 0,		/* 조회수 */
E_DT DATE DEFAULT SYSDATE,	/* 작성시간 */
E_LK NUMBER DEFAULT 0,		/* 추천수 */
E_LKNO VARCHAR2(2048)		/* 회원번호(추천인) */
);

--시퀀스
create sequence e_no_seq
 increment by 1 start with 1 nocache;
 
--뷰 생성
CREATE OR REPLACE VIEW MEM_NO_VIEW
AS
SELECT m.mem_id, e.E_NO, e.MEM_NO, e.E_SJ, e.E_CT, e.E_FL, e.E_RC, e.E_DT, e.E_LK, e.E_LKNO
FROM EBOARD E, MEMBER M
WHERE E.MEM_NO = M.MEM_NO;


 

--제약조건(외래키)
ALTER TABLE EBOARD
ADD CONSTRAINT EBOARD_MEM_NO_FK
FOREIGN KEY(MEM_NO) REFERENCES MEMBER(MEM_NO);

SELECT * FROM EBOARD;

insert into EBOARD
(mem_no, )
--코멘트

CREATE TABLE ECOMMENT(
ECOMM_NO NUMBER,					/* 코멘트번호 (PK) */
--CONSTRAINT ECOMMENT_ECOMM_NO_PK PRIMARY KEY
MEM_NO NUMBER,					/* 작성자회원번호(FK) */
ECOMM_CT VARCHAR2(600),			/* 코멘트내용 */
--CONSTRAINT ECOMMENT_ECOMM_CT_NN NOT NULL,
ECOMM_DT DATE DEFAULT SYSDATE,	/* 코멘트 작성날짜 */
ECOMM_RE_REF NUMBER,			/* 코멘트답변 참조글 번호*/
ECOMM_RE_LEV NUMBER,			/* 코멘트답변 수준 */
ECOMM_RE_SEQ NUMBER DEFAULT 0,	/* 코멘트답변 순서 */
E_NO NUMBER	,					/* 참조글번호 (FK)*/

ECOMM_RCT VARCHAR2(600),		/* 코멘트답변 내용 */
ECOMM_RE_DEL_NO NUMBER			/* 코멘트삭제 번호 */
);

select * from ecomment;

--시퀀스
create sequence e_comm_seq2
 increment by 1 start with 1 nocache;
 
create sequence e_comm_re_del
 increment by 1 start with 1 nocache;
 
--drop sequence e_comm_seq2;


drop table ecomment cascade constraint;
 
--제약조건(외래키)
ALTER TABLE ECOMMENT
ADD CONSTRAINT ECOMMENT_MEM_NO_FK
FOREIGN KEY(MEM_NO) REFERENCES MEMBER(MEM_NO);

ALTER TABLE ECOMMENT
ADD CONSTRAINT ECOMMENT_E_NO_FK
FOREIGN KEY(E_NO) REFERENCES EBOARD(E_NO);

SELECT * FROM ECOMMENT;


--회원정보(MEMBER)
CREATE TABLE MEMBER(
MEM_NO NUMBER(10) 					/* 회원번호(PK) */
CONSTRAINT MEMBER_MEM_NO_PK PRIMARY KEY,
MEM_ID VARCHAR2(50),				/* 아이디 */
MEM_PW VARCHAR2(50),					/* 비밀번호 */
--CONSTRAINT MEMBER_MEM_PW_NN NOT NULL,
MEM_NM VARCHAR2(100),				/*이름*/
--CONSTRAINT MEMBER_MEM_NM_NN NOT NULL,
MEM_SX NUMBER	,					/* 성별 (남:1, 여:2) */
--CONSTRAINT MEMBER_MEM_SX_NN NOT NULL,
MEM_BD DATE	,						/* 생년월일 */
--CONSTRAINT MEMBER_MEM_BD_NN NOT NULL,
MEM_HP VARCHAR2(12)		,			/* 핸드폰번호 */
--CONSTRAINT MEMBER_MEM_HP_NN NOT NULL,
MEM_TEL VARCHAR2(12),				/* 전화번호 */
MEM_ZC NUMBER(5),					/* 우편번호 */
MEM_ADD1 VARCHAR2(300),				/* 주소 */
MEM_ADD2 VARCHAR2(100),				/* 상세주소 */
MEM_MA VARCHAR2(100),				/* 이메일 */
--CONSTRAINT MEMBER_MEM_MA_NN NOT NULL,
MEM_H NUMBER(3,3),					/* 키 */
--CONSTRAINT MEMBER_MEM_H_NN NOT NULL,
MEM_W NUMBER(3,3)		,			/* 몸무게 */
--CONSTRAINT MEMBER_MEM_W_NN NOT NULL,
MEM_JD DATE	DEFAULT SYSDATE		,	/* 가입날짜 */
--CONSTRAINT MEMBER_MEM_JD_NN NOT NULL,
MEM_ST NUMBER DEFAULT 0	,			/* 가입상태 (가입:0, 탈퇴:1) */
--CONSTRAINT MEMBER_MEM_ST_NN NOT NULL,
MEM_DD DATE DEFAULT SYSDATE	,		/* 탈퇴날짜 */
--CONSTRAINT MEMBER_MEM_DD_NN NOT NULL,
MEM_RS1 VARCHAR2(300)	,			/* 탈퇴사유1 */
--CONSTRAINT MEMBER_MEM_RS1_NN NOT NULL,
MEM_RS2 VARCHAR2(300)				/* 탈퇴사유2 */
--CONSTRAINT MEMBER_MEM_RS2_NN NOT NULL
);

commit 

SELECT * FROM MEMBER;

insert into member
(MEM_ID, MEM_NO, MEM_PW, MEM_NM, MEM_SX)
values('testId', 2, 'testpw','테스트이름',2 );

insert into member
(MEM_ID, MEM_NO, MEM_PW, MEM_NM, MEM_SX)
values('testId2', 3, 'testpw','테스트이름2',2 );


DROP TABLE MEMBER CASCADE CONSTRAINT;






--bbs53

create table bbs6(
  bbs_num number(38) primary key
  , bbs_name varchar2(50) not null
  , bbs_pass varchar2(30) not null
  , bbs_subject varchar2(100) not null
  , bbs_content varchar2(4000) not null
  , bbs_file varchar2(100)
  , bbs_re_ref number /*글 그룹번호:답변글 */
  , bbs_re_lev number /*답변글 화면에 보이는 위치 */
  , bbs_re_seq number /*답변글 레벨 순서 */
  , bbs_readcount number /*조회수*/
  , bbs_date date /*등록날짜*/
);

select count(bbs_num) as 총개수 from bbs6;

create sequence bbs6_num_seq
                increment by 1 start with 1 nocache;
--노 캐쉬를 사용하면 캐쉬에 번호값을 저장하지 않는다.
--increment by 1 start 의미는 1부터 시작해서 1씩증가 
                
select * from bbs6;                