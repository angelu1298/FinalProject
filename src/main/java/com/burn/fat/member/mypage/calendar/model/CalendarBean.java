package com.burn.fat.member.mypage.calendar.model;

import org.springframework.stereotype.Component;

@Component
public class CalendarBean {
	private String cal_date;  	//날짜 
	private String day;		/* 아침,점심,저녁 */
	private int mem_no;		//회원번호
	
	private double e_kcal;	/* 운동_칼로리 */
	private int cal_eval;	//평가 ( 하:1 중:2 상:3 )
	
	private String imsiY;	//임시저장 날짜 Y
	private String imsiM;	//임시저장 날짜 M 
	private String imsiD;	//임시저장 날짜 D

	private String y;
	private String m;
	private String d;
	
	private String exer_tt;		/* 운동제목 */
	private String cus_tt;		/* 음식제목 */
	private String grc_tt;		/* 식품제목 */
	private double cus_kcal;		/* 음식검색에서 계산된 칼로리 */
	private double grc_kcal;		/* 식품검색에서 계산된 칼로리 */
	
	public String getCal_date() {
		return cal_date;
	}
	public void setCal_date(String cal_date) {
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
	public double getE_kcal() {
		return e_kcal;
	}
	public void setE_kcal(double e_kcal) {
		this.e_kcal = e_kcal;
	}
	public int getCal_eval() {
		return cal_eval;
	}
	public void setCal_eval(int cal_eval) {
		this.cal_eval = cal_eval;
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
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getExer_tt() {
		return exer_tt;
	}
	public void setExer_tt(String exer_tt) {
		this.exer_tt = exer_tt;
	}
	public String getCus_tt() {
		return cus_tt;
	}
	public void setCus_tt(String cus_tt) {
		this.cus_tt = cus_tt;
	}
	public String getGrc_tt() {
		return grc_tt;
	}
	public void setGrc_tt(String grc_tt) {
		this.grc_tt = grc_tt;
	}
	public double getCus_kcal() {
		return cus_kcal;
	}
	public void setCus_kcal(double cus_kcal) {
		this.cus_kcal = cus_kcal;
	}
	public double getGrc_kcal() {
		return grc_kcal;
	}
	public void setGrc_kcal(double grc_kcal) {
		this.grc_kcal = grc_kcal;
	}
	

	
}