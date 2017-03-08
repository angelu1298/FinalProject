drop table fboard;
drop table fcomment;

create table fboard(
   f_no      number constraint fb_fno_PK primary key,--글번호
   mem_no      number references member(mem_no),--작성자 회원 번호
   f_sj      varchar2(100) not null,--글 제목
   f_ct      clob,--글 내용
   f_fl      varchar2(1024),--업로드 파일 경로
   f_rc      number default 0,--조회수
   f_dt      date default sysdate,--작성시간
   f_lk      number   default 0, --추천수
   f_lkno      varchar2(2048) default ',',--회원번호(추천인)
   fcomm_cnt      number default 0

);

drop view fboardlist;

create or replace view fboardlist
as
select f_no, m.mem_id,f_sj, f_ct, f_fl,f_rc,f_dt,f_lk,f_lkno,fcomm_cnt
from fboard f, member m 
where f.mem_no = m.mem_no

/***** fboard 테이블의 시퀀스 생성 *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;


create sequence member_seq 
increment by 1 start with 1 nocache;

insert into mem88(mem_no,mem_id)
values(mem88_seq.nextval, 'ss');

insert into mem88(mem_no,mem_id)
values(mem88_seq.nextval, 'ok');

drop table mem88;

create or replace view fboardlist
as
select f_no, m.mem_id,f_sj, f_ct, f_fl,f_rc,f_dt,f_lk,f_lkno,fcomm_cnt
from fboard f, member m 
where f.mem_no = m.mem_no;

/***** fboard 테이블의 시퀀스 생성 *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;

create sequence member_seq 
increment by 1 start with 1 nocache;

select * from fboard;




select * from FCOMMENT;
select * from fcommlist;

drop sequence fboard_seq; 
drop sequence fcomm_seq;

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

/***** fcomment 테이블의 시퀀스 생성 *****/
create sequence fcomm_seq 
increment by 1 start with 1 nocache;


create or replace view fcommlist
as
select fcomm_no, m.mem_id, fcomm_ct, fcomm_dt, 
	  fcomm_re_ref, fcomm_re_lev, fcomm_re_seq, f_no
from fcomment f, member m 
where f.mem_no = m.mem_no

drop view fcommlist;


alter table fboard 
add constraint fb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_s_no_FK foreign key (f_no) 
references fboard(f_no);