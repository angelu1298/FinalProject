drop table member;

select * from member;

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
);

/***** member ���̺��� join_code ������ ���� *****/
create sequence member_joincode_seq 
increment by 1 start with 1 nocache;

drop sequence member_joincode_seq; 

/*insert into member (join_code,join_id,join_pwd,join_name,join_zip1,
join_zip2,join_addr1,join_addr2,join_tel,join_phone,join_email,join_regdate,
join_state)
values(join_member_joincode_seq.nextval,'aaaaa',
'77777','ȫ�浿','745','850','����� ������ ���ﵿ','��������',
'02-7777-7777','010-9999-9999','hong@naver.com',sysdate,1);*/

create table zipcode (
  no number PRIMARY KEY
  ,ZIPCODE varchar2(7)
  ,sido varchar2(10) 
  ,gugun varchar2(20) 
  ,dong varchar2(50) 
  ,bunji varchar2(50) 
);

select * from zipcode;