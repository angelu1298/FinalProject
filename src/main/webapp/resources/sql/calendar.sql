create table calendar(
cal_date varchar2(500),
day varchar2(500),	/* 아침,점심,저녁 */
mem_no number,
cal_eval number, /* 하:1 중:2 상:3*/

e_kcal varchar2(500),	/* 운동_칼로리 */
f_kcal varchar2(500),	/* 식단_칼로리*/
t_kcal varchar2(500)	/* 총_칼로리 */
);


select * from calendar;

 delete calendar;
drop table calendar;

insert into calendar
(cal_date, day, mem_no, e_kcal)
values(201731,'morning',1,500)

insert into calendar
(cal_date, day, mem_no, e_kcal)
values(201731,'lunch',1,600)

insert into calendar
(cal_date, day, mem_no, e_kcal)
values(201735,'morning',1,700)

insert into calendar
(cal_date, day, mem_no, e_kcal)
values(201736,'morning',1,700)

insert into calendar
(cal_date, day, mem_no, e_kcal)
values(201737,'morning',1,700)


insert into calendar
(cal_date, day, mem_no, e_kcal)
values(2017312,'morning',1,1200)

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