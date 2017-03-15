create table my_info(
MEM_NO NUMBER(10) PRIMARY KEY,
MEM_ID VARCHAR2(50) NOT NULL, 
MY_MEMO VARCHAR2(1024)
);

ALTER TABLE my_info ADD CONSTRAINT MY_INFO_MEM_NO_FK FOREIGN KEY 
      (MEM_NO) REFERENCES MEMBER(MEM_NO);
      
insert into my_info 
values(1,'admin','올 여름은 비키니!');

drop table my_info cascade constraint;