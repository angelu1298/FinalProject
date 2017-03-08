package com.burn.fat.board.fboard.model;

import java.sql.Date;
public class FcommBean {
	private int fcomm_no;  //코멘트 번호 
	private String mem_id; //작성자 회원번호
	private String fcomm_ct;//코멘트 내용
	private Date fcomm_dt;//코멘트 날짜
	private int fcomm_re_ref;//코멘트 참조글 번호
	private int fcomm_re_lev;//코멘트 수준
	private int fcomm_re_seq;//코멘트 순서
	private int f_no;//참조글 번호
	
	
	public int getFcomm_no() {
		return fcomm_no;
	}
	public void setFcomm_no(int fcomm_no) {
		this.fcomm_no = fcomm_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFcomm_ct() {
		return fcomm_ct;
	}
	public void setFcomm_ct(String fcomm_ct) {
		this.fcomm_ct = fcomm_ct;
	}
	public Date getFcomm_dt() {
		return fcomm_dt;
	}
	public void setFcomm_dt(Date fcomm_dt) {
		this.fcomm_dt = fcomm_dt;
	}
	public int getFcomm_re_ref() {
		return fcomm_re_ref;
	}
	public void setFcomm_re_ref(int fcomm_re_ref) {
		this.fcomm_re_ref = fcomm_re_ref;
	}
	public int getFcomm_re_lev() {
		return fcomm_re_lev;
	}
	public void setFcomm_re_lev(int fcomm_re_lev) {
		this.fcomm_re_lev = fcomm_re_lev;
	}
	public int getFcomm_re_seq() {
		return fcomm_re_seq;
	}
	public void setFcomm_re_seq(int fcomm_re_seq) {
		this.fcomm_re_seq = fcomm_re_seq;
	}
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
	}
}
