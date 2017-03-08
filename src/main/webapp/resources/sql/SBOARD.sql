create table SBOARD(
	s_no			number	constraint sb_sno_PK primary key,
	mem_no			number,
	s_sj			varchar2(100)	not null,	
	s_ct			clob	not null,
	s_fl			varchar2(1024),
	s_gl			varchar2(1024),
	s_rc			number	default 0,
	s_dt			date default sysdate,
	s_lk			number	default 0,
	S_lkno			varchar2(2048)
);

create table scomment(
	scomm_no		number constraint scomm_scno_pk primary key,
	mem_no			number,
	scomm_ct		varchar2(600) not null,
	scomm_dt		date,
	scomm_re_ref	number,
	scomm_re_lev	number,
	scomm_re_seq	number,
	s_no			number
);

create sequence s_no_seq 
increment by 1 start with 1 nocache;

create sequence scomm_no_seq 
increment by 1 start with 1 nocache;


alter table sboard 
add constraint sb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table scomment 
add constraint scomm_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table scomment 
add constraint scomm_s_no_FK foreign key (s_no) 
references sboard(s_no);


create or replace view sboardlist
as
select s_no, m.mem_id, s_sj, s_ct, s_fl, s_rc, s_dt, s_lk, s_lkno, scomm_cnt
from sboard s, member m 
where s.mem_no = m.mem_no;

create or replace view scommlist
as
select scomm_no, m.mem_id, scomm_ct, scomm_dt, scomm_re_ref, scomm_re_lev, scomm_re_seq, s_no
from scomment s, member m 
where s.mem_no = m.mem_no;


   select  rnum, s_no, mem_id,   s_sj, 
		   s_ct, s_fl,      s_rc,
		   s_dt,  s_lk,   S_lkno , scomm_cnt 
		from 
		  (select rownum rnum, s_no, mem_id,   s_sj, 
		   s_ct, s_fl,      s_rc,
		   s_dt,  s_lk,   S_lkno , scomm_cnt
		   from  
	  	      (select * from sboardlist 
	  	       order by s_no desc))
	  where rnum = 4;
