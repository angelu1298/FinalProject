drop table member;

select * from member;

create table member(   
	mem_no		number constraint member_memno_pk primary key,--ȸ�� ��ȣ
    mem_id      varchar2(50) ,
    mem_pw      varchar2(50),     
    mem_nm      varchar2(100),--�̸�
    mem_sx		number, --����(�� :1, �� :2)
    mem_bd		date, --�������
    mem_hp      varchar2(50),--�ڵ�����ȣ
    mem_tel		varchar2(12),--��ȭ��ȣ
    mem_zc      number(5),--�����ȣ
    mem_add1    varchar2(300),--�ּ�
    mem_add2	varchar2(100),--���ּ�
    mem_ma      varchar2(100),--�̸���
    mem_h		number(6,3),--Ű
    mem_w		number(6,3),--������
    mem_jd		date default sysdate,--���Գ�¥
    mem_wb		varchar2(1024), --워너비사진 경로
    mem_st		number default 0, --���Ի���(����:0, Ż��:1)
    mem_dd		date default sysdate,--Ż��¥
    mem_rs1		varchar2(300),--Ż�����1
    mem_rs2		varchar2(300)  --Ż�����2
)
create sequence mem_no_seq start with 1;
drop table member cascade constraint;
drop sequence mem_no_seq;

delete from member where mem_no=2;
select * from member;

/***** member ���̺��� join_code ������ ���� *****/
create sequence member_joincode_seq 
increment by 1 start with 1 nocache;

drop sequence member_joincode_seq; 


alter table fboard 
add constraint fb_mem_no_FK foreign key (mem_no) 
references member(mem_no);

insert into member(mem_no,mem_id,mem_pw, mem_nm,mem_bd,mem_hp,mem_ma,mem_jd)
values(member_joincode_seq.nextval,'ss','1111','홍길동','19880909','01077778888',
'ssheln@naver.com',sysdate);

create table zipcode (
  no number PRIMARY KEY
  ,ZIPCODE varchar2(7)
  ,sido varchar2(10) 
  ,gugun varchar2(20) 
  ,dong varchar2(50) 
  ,bunji varchar2(50) 
);

select * from zipcode;