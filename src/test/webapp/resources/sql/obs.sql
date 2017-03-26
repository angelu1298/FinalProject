
drop table oboard cascade constraint;

create table OBOARD(
O_NO NUMBER PRIMARY KEY,		/*자유게시판 글 번호*/
MEM_NO NUMBER(10),		/*자유게시판 작성자 회원번호*/
O_SJ VARCHAR2(100) NOT NULL,	/*자유게시판 글 제목*/
O_CT CLOB NOT NULL,		/*자유게시판 글 내용*/
O_FL VARCHAR2(1024),		/*자유게시판 업로드 파일 경우*/
O_GL VARCHAR2(1024),		/*자유게시판 업로드 이미지 경로*/
O_RC NUMBER DEFAULT 0,		/*자유게시판 조회수*/
O_DT DATE DEFAULT SYSDATE,	/*자유게시판 작성시간*/
O_LK NUMBER DEFAULT 0,		/*자유게시판 추천수*/
O_LKNO VARCHAR2(2048),		/*자유게시판 추천인 회원번호*/
MEM_ID VARCHAR2(50)		/*자유게시판 글 작성자 아이디*/
);


select * from oboard;
commit

CREATE TABLE OCOMMENT(
OCOMM_NO NUMBER PRIMARY KEY,	/*자유게시판의 코멘트번호*/
MEM_NO NUMBER NOT NULL,		/*자유게시판의 작성자 회원번호*/
MEM_ID VARCHAR2(50) NOT NULL, 
OCOMM_CT VARCHAR2(600) NOT NULL,/*자유게시판의 코멘트 내용*/
OCOMM_DT DATE DEFAULT SYSDATE,	/*자유게시판의 작성날짜*/
OCOMM_RE_REF NUMBER,		/*자유게시판의 코멘트 답변 참조글 번호*/
OCOMM_RE_LEV NUMBER,		/*자유게시판의 코멘트 답변 수준*/
OCOMM_RE_SEQ NUMBER,		/*자유게시판의 코멘트 답변 순서*/
O_NO NUMBER			/*자유게시판의 참조글 번호*/
);


create sequence O_NO_SEQ
                increment by 1 start with 1 nocache;
--노 캐쉬를 사용하면 캐쉬에 번호값을 저장하지 않는다.
--increment by 1 start 의미는 1부터 시작해서 1씩증가 
  
  
--OBOARD 테이블에 MEM_NO를 MEMBER 테이블의 MEM_NO로 외래키 설정 쿼리

ALTER TABLE OBOARD ADD CONSTRAINT OBOARD_MEM_NO_FK FOREIGN KEY 
		(MEM_NO) REFERENCES MEMBER(MEM_NO);

--OCOMMENT 테이블에 MEM_NO를 MEMBER 테이블의 MEM_NO로 외래키 설정 쿼리
ALTER TABLE OCOMMENT ADD CONSTRAINT OCOMMENT_MEM_NO_FK FOREIGN KEY 
		(MEM_NO) REFERENCES MEMBER(MEM_NO);

--OCOMMENT 테이블에 O_NO를 OBOARD 테이블의 O_NO로 외래키 설정 쿼리━
ALTER TABLE OCOMMENT ADD CONSTRAINT OCOMENT_O_NO_FK FOREIGN KEY 
		(O_NO) REFERENCES OBOARD(O_NO);
                


