 CREATE TABLE CALENDAR 
   (	CAL_DATE VARCHAR2(50 BYTE), 
	DAY VARCHAR2(500 BYTE), 
	MEM_NO NUMBER, 
	E_KCAL VARCHAR2(500 BYTE) DEFAULT NULL, 
	CAL_EVAL NUMBER DEFAULT 0, 
	Y VARCHAR2(50 BYTE), 
	M VARCHAR2(50 BYTE), 
	D VARCHAR2(50 BYTE), 
	EXER_TT VARCHAR2(1000 BYTE), 
	CUS_TT VARCHAR2(500 BYTE), 
	GRC_TT VARCHAR2(500 BYTE), 
	CUS_KCAL NUMBER(10,5) DEFAULT 0, 
	GRC_KCAL NUMBER(10,5) DEFAULT 0
   ) 

select * from calendar;

select * from calendar
		where mem_no = 1;
		
 delete calendar;
drop table calendar;

-- view
create or replace view top
as
select cu.cus_tt
from cuisine cu, calendar ca
where ca.cus_no = cu.cus_no



--/* 아침 */
--m_e_kcal number,	/* 아침_운동_칼로리 */
--m_f_kcal number,	/* 아침_식단_칼로리*/
--m_t_kcal number,	/* 아침_총_칼로리 */
--/* 점심 */
--l_e_kcal number,	/* 점심_운동_칼로리 */
--l_f_kcal number,	/* 점심_식단_칼로리*/
--l_t_kcal number,	/* 점심_총_칼로리 */
--/* 저녁 */
--d_e_kcal number,	/* 저녁_운동_칼로리 */
--d_f_kcal number,	/* 저녁_식단_칼로리*/
--d_t_kcal number	/* 저녁_총_칼로리 */