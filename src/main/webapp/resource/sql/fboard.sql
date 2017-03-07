drop table fboard;
drop table fcomment;

create table fboard(
	f_no		number constraint fb_fno_PK primary key,--글번호
	mem_no		number,--작성자 회원 번호
	f_sj		varchar2(100) not null,--글 제목
	f_ct		clob,--글 내용
	f_fl		varchar2(1024),--업로드 파일 경로
	f_rc		number default 0,--조회수
	f_dt		date default sysdate,--작성시간
	f_lk		number	default 0, --추천수
	f_lkno		varchar2(2048)--회원번호(추천인)
);



select * from fboard;

/***** fboard 테이블의 시퀀스 생성 *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;

drop sequence fboard_seq; 

create table fcomment(
	fcomm_no		number constraint fcomm_fcno_pk primary key,--코멘트 번호
	mem_no			number,--작성자 회원번호
	fcomm_ct		varchar2(600) not null,--코멘트 내용
	fcomm_dt		date,--코멘트 작성날짜
	fcomm_re_ref	number,--코멘트답변참조글 번호
	fcomm_re_lev	number,--코멘트 답변 수준
	fcomm_re_seq	number,--코멘트 답변 순서
	f_no			number  --참조글 번호
);

alter table fboard 
add constraint fb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_s_no_FK foreign key (f_no) 
references fboard(f_no);