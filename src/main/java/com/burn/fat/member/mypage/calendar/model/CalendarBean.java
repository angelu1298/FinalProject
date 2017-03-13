package com.burn.fat.member.mypage.calendar.model;

import org.springframework.stereotype.Component;

@Component
public class CalendarBean {
	private int cal_date;  	//날짜 
	private String day;		/* 아침,점심,저녁 */
	private int mem_no;		//회원번호
	private int cal_eval;	//평가 ( 하:1 중:2 상:3 )
	
	private String e_kcal;	/* 운동_칼로리 */
	private String f_kcal;	/* 식단_칼로리*/
	private String t_kcal;	/* 총_칼로리 */
	private String imsiY;	//임시저장 날짜 Y
	private String imsiM;	//임시저장 날짜 M
	private String imsiD;	//임시저장 날짜 D
	
	public int getCal_date() {
		return cal_date;
	}
	public void setCal_date(int cal_date) {
		this.cal_date = cal_date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getCal_eval() {
		return cal_eval;
	}
	public void setCal_eval(int cal_eval) {
		this.cal_eval = cal_eval;
	}
	public String getE_kcal() {
		return e_kcal;
	}
	public void setE_kcal(String e_kcal) {
		this.e_kcal = e_kcal;
	}
	public String getF_kcal() {
		return f_kcal;
	}
	public void setF_kcal(String f_kcal) {
		this.f_kcal = f_kcal;
	}
	public String getT_kcal() {
		return t_kcal;
	}
	public void setT_kcal(String t_kcal) {
		this.t_kcal = t_kcal;
	}
	public String getImsiY() {
		return imsiY;
	}
	public void setImsiY(String imsiY) {
		this.imsiY = imsiY;
	}
	public String getImsiM() {
		return imsiM;
	}
	public void setImsiM(String imsiM) {
		this.imsiM = imsiM;
	}
	public String getImsiD() {
		return imsiD;
	}
	public void setImsiD(String imsiD) {
		this.imsiD = imsiD;
	}

	
}