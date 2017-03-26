 --calendar
 CREATE TABLE CALENDAR (	
 	CAL_DATE 	VARCHAR2(50 BYTE), 
	DAY 		VARCHAR2(500 BYTE), 
	MEM_NO 		NUMBER, 
	E_KCAL 		NUMBER DEFAULT 0, 
	CAL_EVAL 	NUMBER DEFAULT 0, 
	Y 			VARCHAR2(50 BYTE), 
	M 			VARCHAR2(50 BYTE), 
	D 			VARCHAR2(50 BYTE), 
	EXER_TT 	VARCHAR2(1000 BYTE), 
	CUS_TT 		VARCHAR2(500 BYTE), 
	GRC_TT 		VARCHAR2(500 BYTE), 
	CUS_KCAL 	NUMBER(10,5) DEFAULT 0, 
	GRC_KCAL 	NUMBER(10,5) DEFAULT 0
   ) 

-- view
create or replace view top
as
select cu.cus_tt
from cuisine cu, calendar ca
where ca.cus_no = cu.cus_no

create or replace view calendardistinct
as 
select distinct CAL_DATE, cal_eval, mem_no
from calendar