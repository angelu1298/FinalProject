drop table member;

select * from member;

create table member(   
	mem_no		number constraint member_memno_pk primary key,--회원 번호
    mem_id      varchar2(50) ,
    mem_pw      varchar2(50),     
    mem_nm      varchar2(100),--이름
    mem_sx		number, --성별(남 :1, 여 :2)
    mem_bd		date, --생년월일
    mem_hp      varchar2(12),--핸드폰번호
    mem_tel		varchar2(12),--전화번호
    mem_zc      number(5),--우편번호
    mem_add1    varchar2(300),--주소
    mem_add2	varchar2(100),--상세주소
    mem_ma      varchar2(100),--이메일
    mem_h		number(3,3),--키
    mem_w		number(3,3),--몸무게
    mem_jd		date default sysdate,--가입날짜
    mem_st		number default 0, --가입상태(가입:0, 탈퇴:1)
    mem_dd		date default sysdate,--탈퇴날짜
    mem_rs1		varchar2(300),--탈퇴사유1
    mem_rs2		varchar2(300)  --탈퇴사유2
);

/***** member 테이블의 join_code 시퀀스 생성 *****/
create sequence member_joincode_seq 
increment by 1 start with 1 nocache;

drop sequence member_joincode_seq; 

/*insert into member (join_code,join_id,join_pwd,join_name,join_zip1,
join_zip2,join_addr1,join_addr2,join_tel,join_phone,join_email,join_regdate,
join_state)
values(join_member_joincode_seq.nextval,'aaaaa',
'77777','홍길동','745','850','서울시 마포구 대흥동','서래마을',
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