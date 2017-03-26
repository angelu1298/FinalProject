package com.burn.fat.board.sboard.model;

import java.sql.Date;

public class ScommBean {
	private int scomm_no;
	private String mem_id;
	private String scomm_ct;
	private Date scomm_dt;
	private int scomm_re_ref;
	private int scomm_re_lev;
	private int scomm_re_seq;
	private int s_no;
	
	public int getScomm_no() {
		return scomm_no;
	}
	public void setScomm_no(int scomm_no) {
		this.scomm_no = scomm_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getScomm_ct() {
		return scomm_ct;
	}
	public void setScomm_ct(String scomm_ct) {
		this.scomm_ct = scomm_ct;
	}
	public Date getScomm_dt() {
		return scomm_dt;
	}
	public void setScomm_dt(Date scomm_dt) {
		this.scomm_dt = scomm_dt;
	}
	public int getScomm_re_ref() {
		return scomm_re_ref;
	}
	public void setScomm_re_ref(int scomm_re_ref) {
		this.scomm_re_ref = scomm_re_ref;
	}
	public int getScomm_re_lev() {
		return scomm_re_lev;
	}
	public void setScomm_re_lev(int scomm_re_lev) {
		this.scomm_re_lev = scomm_re_lev;
	}
	public int getScomm_re_seq() {
		return scomm_re_seq;
	}
	public void setScomm_re_seq(int scomm_re_seq) {
		this.scomm_re_seq = scomm_re_seq;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
}
