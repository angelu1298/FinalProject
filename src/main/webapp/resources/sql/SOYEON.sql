create sequence g_no_seq
start with 1;
create sequence gcomm_no_seq
start with 1;
create sequence seq_grc_no
start with 1;
create sequence seq_cus_no
start with 1;
create sequence rcp_no_seq
start with 1;

select * from gboard;
select * from gboardlist;
select * from gcomment;

create table GROCERY(
	GRC_NO   NUMBER(5) constraint GRC_NO_PK primary key,--��ȣ	NUMBER	5	NN	PK
	GRC_TT   VARCHAR2(500) not null,--	�ķ�ǰ��	VARCHAR2	200	NN	
	GRC_CT   VARCHAR2(500),--	�ķ�ǰ��	VARCHAR2	200	NN	
	GRC_ONCE NUMBER(10,5) not null, --	1ȸ������	NUMBER	6,3	NN	
	GRC_CAL  NUMBER(10,5) not null ,--	����	NUMBER	6,3	NN	
	GRC_TAN  NUMBER(10,5), --	ź��ȭ��	NUMBER	6,3		
	GRC_DAN  NUMBER(10,5), --	�ܹ���	NUMBER	6,3		
	GRC_GEE  NUMBER(10,5), --	����	NUMBER	6,3		
	GRC_SWT  NUMBER(10,5),--����	NUMBER	6,3		
	GRC_NA   NUMBER(10,5),--	��Ʈ��	NUMBER	6,3		
	GRC_COL  NUMBER(10,5), --	�ݷ����׷�	NUMBER	6,3		
	GRC_POJ  NUMBER(10,5), --	��ȭ�����	NUMBER	6,3		
	GRC_TRS  NUMBER(10,5) --	Ʈ��������	NUMBER	6,3		
);

create table CUISINE (
	CUS_NO NUMBER(5) constraint CUS_NO_PK primary key,--��ȣ	NUMBER	5	NN	PK
	CUS_TT VARCHAR2(500) not null,--�ķ�ǰ��	VARCHAR2	200	NN	
	CUS_ONCE NUMBER(10,5) not null,--1ȸ������	NUMBER	6,3	NN	
	CUS_CAL NUMBER(10,5) not null,--����	NUMBER	6,3	NN	
	CUS_TAN NUMBER(10,5),--ź��ȭ��	NUMBER	6,3		
	CUS_DAN NUMBER(10,5),--�ܹ���	NUMBER	6,3		
	CUS_GEE NUMBER(10,5),--����	NUMBER	6,3		
	CUS_SWT NUMBER(10,5),--����	NUMBER	6,3		
	CUS_NA NUMBER(10,5),--��Ʈ��	NUMBER	6,3		
	CUS_COL NUMBER(10,5),--�ݷ����׷�	NUMBER	6,3		
	CUS_POJ NUMBER(10,5),--��ȭ�����	NUMBER	6,3		
	CUS_TRS NUMBER(10,5)--Ʈ��������	NUMBER	6,3		
);

create table JUICE(
	JUC_NO NUMBER(5) constraint JUC_NO_PK primary key,
	JUC_CT NUMBER(1) not null, --����:1, ä��:2
	JUC_TT VARCHAR2(200) not null,	
	JUC_ONCE NUMBER(6,3) not null, 	--1ȸ������	NUMBER	6,3	NN			
	JUC_CAL NUMBER(6,3) not null,	--����	NUMBER	6,3	NN			
	JUC_TAN NUMBER(6,3),	--ź��ȭ��	NUMBER	6,3				
	JUC_DAN NUMBER(6,3),	--�ܹ���	NUMBER	6,3				
	JUC_GEE NUMBER(6,3),	--����	NUMBER	6,3				
	JUC_NA NUMBER(6,3),	--��Ʈ��	NUMBER	6,3				
	JUC_COL NUMBER(6,3),	--�ݷ����׷�	NUMBER	6,3				
	JUC_POJ NUMBER(6,3),	--��ȭ�����	NUMBER	6,3				
	JUC_TRS NUMBER(6,3)	--Ʈ��������	NUMBER	6,3				
);


create table RECIPE (
	RCP_NO NUMBER(5) constraint RCP_NO_PK primary key,	--��ȣ	NUMBER	5	NN	PK
	RCP_TT VARCHAR2(200) not null,	--������ �̸�	VARCHAR2	200	NN	
	RCP_NT VARCHAR2(200) not null,	--���	VARCHAR2	200	NN	
	MEM_NO NUMBER(5), 				--������ �ۼ���	NUMBER	5	NN	FK		
	RCP_CAL NUMBER(6,3) not null,	--����	NUMBER	6,3	NN			
	RCP_TAN NUMBER(6,3),	--ź��ȭ��	NUMBER	6,3				
	RCP_DAN NUMBER(6,3),	--�ܹ���	NUMBER	6,3				
	RCP_GEE NUMBER(6,3),	--����	NUMBER	6,3				
	RCP_NA NUMBER(6,3),	--��Ʈ��	NUMBER	6,3				
	RCP_COL NUMBER(6,3),	--�ݷ����׷�	NUMBER	6,3				
	RCP_POJ NUMBER(6,3),	--��ȭ�����	NUMBER	6,3				
	RCP_TRS NUMBER(6,3)	--Ʈ��������	NUMBER	6,3			
);
alter table RECIPE 
add constraint rec_mem_no_FK foreign key (mem_no) 
references member(mem_no);

insert into juice values(1,1,'���',100,49,13.1,0.2,0.1,16,0,0.04,0);
insert into juice values(2,1,'��纣��',100,57,14.5,	0.7,	0.3,	1,	0,	0,	0);
insert into juice values(3,1,'����',100	46,12.2,0.5,	0.1,	0,	0,	0.02,	0);
insert into juice values(4,1,'����',30,9.6,2.01,0.39,	0.12,	0.6,	0,	0,	0);
insert into juice values(5,1,'Ű��',100,64,	14.8,	0.8,	1,	1.257,	0,	0.14,	0);
insert into juice values(6,1,'�ڸ�',100,31,	7.6,	0.7,	0.1,	1,	0,	0,	0);
insert into juice values(7,1,'�ٳ���',100,	80,	21.2,	1,	0,	1,	0,	0,0);

insert into juice values(21,	2,	'����',	70,	30.1,	5.11,	3.5,	0.42,	30.1,	0,	0,	0);
insert into juice values(22,	2,	'������',	70,	18.2,	3.85,	1.26,	0.14,	105,	0,	0,	0);

create table park (
	prk_no  	number constraint prk_no_PK primary key,
	prk_se  	varchar2(100)	not null,
	prk_nm  	varchar2(100)	not null,
	prk_ct  	varchar2(100)	not null,
	prk_add  	varchar2(100)	not null,
	prk_l  		number(10,5)	not null,
	prk_h 		number(10,5)	not null,
	prk_sido 	varchar2(100)	not null,
	prk_gungu  	varchar2(100)	not null
)

create table gboard(
	g_no			 number constraint gb_sno_PK primary key,
	mem_no			 number,
	g_sj			 varchar2(100)	not null,	
	g_ct			 clob	 not null,
	g_fl			 varchar2(1024),
	g_rc			 number default 0,
	g_dt			 date default sysdate,
	g_lk			 number	 default 0,
	g_lkno			 varchar2(2048) default ',',
	gcomm_cnt		 number default 0
);

create table gcomment(
	gcomm_no		 number constraint gcomm_scno_pk primary key,
	mem_no			 number,
	gcomm_ct		 varchar2(600) not null,
	gcomm_dt		 date,
	gcomm_re_ref	 number default 0,
	gcomm_re_lev	 number default 0,
	gcomm_re_seq	 number default 0,
	g_no			 number
);

alter table gboard 
add constraint gb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table gcomment 
add constraint gcomm_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table gcomment 
add constraint gcomm_g_no_FK foreign key (g_no) 
references gboard(g_no);

create or replace view glist
as 
select * 
from gboard 
order by g_dt desc;

create or replace view gcommlist
as
select gcomm_no, m.mem_id, gcomm_ct, gcomm_dt, gcomm_re_ref, gcomm_re_lev, gcomm_re_seq, g_no gbbs_num
from gcomment g, member m 
where g.mem_no = g.mem_no;




drop table gboard cascade constraint;
drop table gcomment cascade constraint;
drop sequence g_no_seq;
drop sequence gcomm_no_seq;
 
DROP TABLE RECIPE;
drop table GROCERY;
drop table CUISINE;
DROP TABLE JUICE;

















 insert into member values (1,'jinsso','1234','���ҿ�',2,sysdate,'01077777777','01000000000',43434,'���ּҤ�������������','������������',
 'jsy9101@gmail.com',0,0,sysdate,0,sysdate,'fsafasdfas','asdfasdfasd'
 );

alter table gboard 
drop constraint gb_mem_no_FK ;



