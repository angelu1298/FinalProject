drop table fboard;
drop table fcomment;

create table fboard(
	f_no		number constraint fb_fno_PK primary key,--�۹�ȣ
	mem_no		number,--�ۼ��� ȸ�� ��ȣ
	f_sj		varchar2(100) not null,--�� ����
	f_ct		clob,--�� ����
	f_fl		varchar2(1024),--���ε� ���� ���
	f_rc		number default 0,--��ȸ��
	f_dt		date default sysdate,--�ۼ��ð�
	f_lk		number	default 0, --��õ��
	f_lkno		varchar2(2048)--ȸ����ȣ(��õ��)
);



select * from fboard;

/***** fboard ���̺��� ������ ���� *****/
create sequence fboard_seq 
increment by 1 start with 1 nocache;

drop sequence fboard_seq; 

create table fcomment(
	fcomm_no		number constraint fcomm_fcno_pk primary key,--�ڸ�Ʈ ��ȣ
	mem_no			number,--�ۼ��� ȸ����ȣ
	fcomm_ct		varchar2(600) not null,--�ڸ�Ʈ ����
	fcomm_dt		date,--�ڸ�Ʈ �ۼ���¥
	fcomm_re_ref	number,--�ڸ�Ʈ�亯������ ��ȣ
	fcomm_re_lev	number,--�ڸ�Ʈ �亯 ����
	fcomm_re_seq	number,--�ڸ�Ʈ �亯 ����
	f_no			number  --������ ��ȣ
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