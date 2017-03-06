package com.burn.fat.board.sboard.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class SboardBean {
	
	private int s_no;
	private String mem_id;
	private String s_sj;
	private String s_ct;
	private String s_fl;
	private int s_rc;
	private Date s_dt;
	private int s_lk;
	private String s_lkno;
	private int scomm_cnt;
	
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getS_sj() {
		return s_sj;
	}
	public void setS_sj(String s_sj) {
		this.s_sj = s_sj;
	}
	public String getS_ct() {
		return s_ct;
	}
	public void setS_ct(String s_ct) {
		this.s_ct = s_ct;
	}
	public String getS_fl() {
		return s_fl;
	}
	public void setS_fl(String s_fl) {
		this.s_fl = s_fl;
	}
	public int getS_rc() {
		return s_rc;
	}
	public void setS_rc(int s_rc) {
		this.s_rc = s_rc;
	}
	public Date getS_dt() {
		return s_dt;
	}
	public void setS_dt(Date s_dt) {
		this.s_dt = s_dt;
	}
	public int getS_lk() {
		return s_lk;
	}
	public void setS_lk(int s_lk) {
		this.s_lk = s_lk;
	}
	public String getS_lkno() {
		return s_lkno;
	}
	public void setS_lkno(String s_lkno) {
		this.s_lkno = s_lkno;
	}
	public int getScomm_cnt() {
		return scomm_cnt;
	}
	public void setScomm_cnt(int scomm_cnt) {
		this.scomm_cnt = scomm_cnt;
	}
	
	
}
