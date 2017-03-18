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
   RCP_NO NUMBER(5) constraint RCP_NO_PK primary key,   --번호   NUMBER   5   NN   PK
   RCP_TT VARCHAR2(200) not null,   --레시피 이름   VARCHAR2   200   NN   
   RCP_NT VARCHAR2(200) not null,   --재료   VARCHAR2   200   NN   
   MEM_NO NUMBER(5),             --레시피 작성자   NUMBER   5   NN   FK      
   RCP_CAL NUMBER(6,3) not null,   --열량   NUMBER   6,3   NN         
   RCP_TAN NUMBER(6,3),   --탄수화물   NUMBER   6,3            
   RCP_DAN NUMBER(6,3),   --단백질   NUMBER   6,3            
   RCP_GEE NUMBER(6,3),   --지방   NUMBER   6,3            
   RCP_NA NUMBER(6,3),   --나트륨   NUMBER   6,3            
   RCP_COL NUMBER(6,3),   --콜레스테롤   NUMBER   6,3            
   RCP_POJ NUMBER(6,3),   --포화지방산   NUMBER   6,3            
   RCP_TRS NUMBER(6,3)   --트랜스지방   NUMBER   6,3               
   RCP_STATE NUMBER(1)   -- 삭제여부
);
alter table RECIPE 
add constraint rec_mem_no_FK foreign key (mem_no) 
references member(mem_no);


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


alter table gboard 
drop constraint gb_mem_no_FK ;



