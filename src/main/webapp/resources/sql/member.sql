drop table member;
delete from member;

select * from member;

create table member(   
	mem_no		number constraint member_memno_pk primary key,--회원 번호
    mem_id      varchar2(50),
    mem_pw      varchar2(50),     
    mem_nm      varchar2(100),--이름
    mem_sx		number, --성별(남 :1, 여 :2)
    mem_bd		date, --생년월일
    mem_hp      varchar2(100),--핸드폰번호
    mem_tel		varchar2(100),--전화번호
    mem_zc      number(20),--우편번호
    mem_add1    varchar2(300),--주소
    mem_add2	varchar2(100),--상세주소
    mem_ma      varchar2(300),--이메일
    mem_h		number(3),--키
    mem_w		number(3),--몸무게
    mem_jd		date default sysdate,--가입날짜
    mem_wb		varchar2(1024),  --워너비 사진
    mem_st		number default 0, --가입상태(가입:0, 탈퇴:1)
    mem_dd		date default sysdate,--탈퇴날짜
    mem_rs1		varchar2(300) default null,--탈퇴사유1
    mem_rs2		varchar2(300) default null, --탈퇴사유2
    MY_MEMO 	VARCHAR2(1024) default '한마디 적어주세요',
    GOAL_W 		NUMBER(5) default 0   
);




/***** member 테이블의 join_code 시퀀스 생성 *****/
create sequence mem_no_seq 
increment by 1 start with 1 nocache;

drop sequence member_joincode_seq; 




