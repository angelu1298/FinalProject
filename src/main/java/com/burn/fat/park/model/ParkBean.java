package com.burn.fat.park.model;
import org.springframework.stereotype.Component;

@Component
public class ParkBean { 

	private int prk_no;			// 공원번호
	private String prk_se;		// 공원 관리번호
	private String prk_nm; 		// 공원이름
	private String prk_ct; 		// 공원 분류
	private String prk_add;		// 공원 주소
	private float prk_l; 		// 공원 위도
	private float prk_h; 		// 공원 경도
	private String prk_sido; 	// 공원 시도
	private String prk_gungu;	// 공원 군구
	 
	public int getPrk_no() {
		return prk_no;
	}
	public void setPrk_no(int prk_no) {
		this.prk_no = prk_no;
	}
	public String getPrk_se() {
		return prk_se;
	}
	public void setPrk_se(String prk_se) {
		this.prk_se = prk_se;
	}
	public String getPrk_nm() {
		return prk_nm;
	}
	public void setPrk_nm(String prk_nm) {
		this.prk_nm = prk_nm;
	}
	public String getPrk_ct() {
		return prk_ct;
	}
	public void setPrk_ct(String prk_ct) {
		this.prk_ct = prk_ct;
	}
	public String getPrk_add() {
		return prk_add;
	}
	public void setPrk_add(String prk_add) {
		this.prk_add = prk_add;
	}
	public float getPrk_l() {
		return prk_l;
	}
	public void setPrk_l(float prk_l) {
		this.prk_l = prk_l;
	}
	public float getPrk_h() {
		return prk_h;
	}
	public void setPrk_h(float prk_h) {
		this.prk_h = prk_h;
	}
	public String getPrk_sido() {
		return prk_sido;
	}
	public void setPrk_sido(String prk_sido) {
		this.prk_sido = prk_sido;
	}
	public String getPrk_gungu() {
		return prk_gungu;
	}
	public void setPrk_gungu(String prk_gungu) {
		this.prk_gungu = prk_gungu;
	}
	 
}
