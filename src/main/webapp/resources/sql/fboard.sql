drop table fboard;
drop table fcomment;

create table fboard(
<<<<<<< HEAD
	f_no		number constraint fb_fno_PK primary key,--±Û¹øÈ£
	mem_no		number,--ÀÛ¼ºÀÚ È¸¿ø ¹øÈ£
	f_sj		varchar2(100) not null,--±Û Á¦¸ñ
	f_ct		clob,--±Û ³»¿ë
	f_fl		varchar2(1024),--¾÷·Îµå ÆÄÀÏ °æ·Î
	f_rc		number default 0,--Á¶È¸¼ö
	f_dt		date default sysdate,--ÀÛ¼º½Ã°£
	f_lk		number	default 0, --ÃßÃµ¼ö
	f_lkno		varchar2(2048)--È¸¿ø¹øÈ£(ÃßÃµÀÎ)
);



select * from fboard;

/***** fboard Å×ÀÌºíÀÇ ½ÃÄö½º »ý¼º *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;

drop sequence fboard_seq; 

create table fcomment(
	fcomm_no		number constraint fcomm_fcno_pk primary key,--ÄÚ¸àÆ® ¹øÈ£
	mem_no			number,--ÀÛ¼ºÀÚ È¸¿ø¹øÈ£
	fcomm_ct		varchar2(600) not null,--ÄÚ¸àÆ® ³»¿ë
	fcomm_dt		date,--ÄÚ¸àÆ® ÀÛ¼º³¯Â¥
	fcomm_re_ref	number,--ÄÚ¸àÆ®´äº¯ÂüÁ¶±Û ¹øÈ£
	fcomm_re_lev	number,--ÄÚ¸àÆ® ´äº¯ ¼öÁØ
	fcomm_re_seq	number,--ÄÚ¸àÆ® ´äº¯ ¼ø¼­
	f_no			number  --ÂüÁ¶±Û ¹øÈ£
=======
	f_no		number constraint fb_fno_PK primary key,--ï¿½Û¹ï¿½È£
	mem_no		number,--ï¿½Û¼ï¿½ï¿½ï¿½ È¸ï¿½ï¿½ ï¿½ï¿½È£
	f_sj		varchar2(100) not null,--ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	f_ct		clob,--ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	f_fl		varchar2(1024),--ï¿½ï¿½ï¿½Îµï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½
	f_rc		number default 0,--ï¿½ï¿½È¸ï¿½ï¿½
	f_dt		date default sysdate,--ï¿½Û¼ï¿½ï¿½Ã°ï¿½
	f_lk		number	default 0, --ï¿½ï¿½Ãµï¿½ï¿½
	f_lkno		varchar2(2048)--È¸ï¿½ï¿½ï¿½ï¿½È£(ï¿½ï¿½Ãµï¿½ï¿½)
);



select * from fboard;

/***** fboard ï¿½ï¿½ï¿½Ìºï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;

drop sequence fboard_seq; 

create table fcomment(
	fcomm_no		number constraint fcomm_fcno_pk primary key,--ï¿½Ú¸ï¿½Æ® ï¿½ï¿½È£
	mem_no			number,--ï¿½Û¼ï¿½ï¿½ï¿½ È¸ï¿½ï¿½ï¿½ï¿½È£
	fcomm_ct		varchar2(600) not null,--ï¿½Ú¸ï¿½Æ® ï¿½ï¿½ï¿½ï¿½
	fcomm_dt		date,--ï¿½Ú¸ï¿½Æ® ï¿½Û¼ï¿½ï¿½ï¿½Â¥
	fcomm_re_ref	number,--ï¿½Ú¸ï¿½Æ®ï¿½äº¯ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È£
	fcomm_re_lev	number,--ï¿½Ú¸ï¿½Æ® ï¿½äº¯ ï¿½ï¿½ï¿½ï¿½
	fcomm_re_seq	number,--ï¿½Ú¸ï¿½Æ® ï¿½äº¯ ï¿½ï¿½ï¿½ï¿½
	f_no			number  --ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È£
>>>>>>> branch 'temp' of https://github.com/angelu1298/FinalProject
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