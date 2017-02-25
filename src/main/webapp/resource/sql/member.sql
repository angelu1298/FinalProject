create table member(   
	mem_no		number constraint member_memno_pk primary key,--ȸ�� ��ȣ
    mem_id      varchar2(50) ,
    mem_pw      varchar2(50),     
    mem_nm      varchar2(100),--�̸�
    mem_sx		number, --����(�� :1, �� :2)
    mem_bd		date, --�������
    mem_hp      varchar2(12),--�ڵ�����ȣ
    mem_tel		varchar2(12),--��ȭ��ȣ
    mem_zc      number(5),--�����ȣ
    mem_add1    varchar2(300),--�ּ�
    mem_add2	varchar2(100),--���ּ�
    mem_ma      varchar2(100),--�̸���
    mem_h		number(3,3),--Ű
    mem_w		number(3,3),--������
    mem_jd		date default sysdate,--���Գ�¥
    mem_st		number default 0, --���Ի���(����:0, Ż��:1)
    mem_dd		date default sysdate,--Ż��¥
    mem_rs1		varchar2(300),--Ż�����1
    mem_rs2		varchar2(300)  --Ż�����2
)

create table fboard(
	f_no		number constraint fb_fno_PK primary key,--�۹�ȣ
	mem_no		number,--�ۼ��� ȸ�� ��ȣ
	f_sj		varchar2(100) not null,--�� ����
	f_ct		clob not null,--�� ����
	f_fl		varchar2(1024),--���ε� ���� ���
	f_gl		varchar2(1024),--���ε� �̹��� ���
	f_rc		number default 0,--��ȸ��
	f_dt		date default sysdate--�ۼ��ð�
)

create table fcomment(
	fcomm_no		number constraint fcomm_fcno_pk primary key,
	mem_no			number,
	fcomm_ct		varchar2(600) not null,
	fcomm_dt		date,
	fcomm_re_ref	number,
	fcomm_re_lev	number,
	fcomm_re_seq	number,
	f_no			number
)

alter table fboard 
add constraint fb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_mem_no_FK foreign key (mem_no) 
references member(mem_no);

alter table fcomment 
add constraint fcomm_s_no_FK foreign key (s_no) 
references fboard(s_no);