create table calendar(
cal_date varchar2(50),
day varchar2(500),			/* 아침,점심,저녁 */
mem_no number,

e_kcal varchar2(500),		/* 운동_칼로리 */
cal_eval number,		/* 하:1 중:2 상:3*/

y varchar2(50), 					/* 년도 */
m varchar2(50), 					/* 달    */
d varchar2(50), 					/* 일   */

exer_tt varchar2(1000),		/* 운동제목 */
cus_tt varchar2(500),		/* 음식제목 */
grc_tt varchar2(500),		/* 식품제목 */
cus_kcal number(10,5),		/* 음식검색에서 계산된 칼로리 */
grc_kcal number(10,5)		/* 식품검색에서 계산된 칼로리 */
);


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