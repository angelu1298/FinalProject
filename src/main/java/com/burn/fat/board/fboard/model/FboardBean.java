package com.burn.fat.board.fboard.model;

import org.springframework.stereotype.Component;

@Component
public class FboardBean {

	private int f_no;
	private String mem_id;
	private String f_sj; //글제목
	private String f_ct; //글내용
	private String f_fl; //이진파일명
	private int f_rc; //조회수
	private String f_dt; //글 등록날짜
	private int f_lk; //추천수
	private String f_lkno; // 회원번호
	private int scomm_cnt; //코멘트
	
	
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getF_sj() {
		return f_sj;
	}
	public void setF_sj(String f_sj) {
		this.f_sj = f_sj;
	}
	public String getF_ct() {
		return f_ct;
	}
	public void setF_ct(String f_ct) {
		this.f_ct = f_ct;
	}
	public String getF_fl() {
		return f_fl;
	}
	public void setF_fl(String f_fl) {
		this.f_fl = f_fl;
	}
	public int getF_rc() {
		return f_rc;
	}
	public void setF_rc(int f_rc) {
		this.f_rc = f_rc;
	}
	public String getF_dt() {
		return f_dt;
	}
	public void setF_dt(String f_dt) {
		this.f_dt = f_dt;
	}
	public int getF_lk() {
		return f_lk;
	}
	public void setF_lk(int f_lk) {
		this.f_lk = f_lk;
	}
	public String getF_lkno() {
		return f_lkno;
	}
	public void setF_lkno(String f_lkno) {
		this.f_lkno = f_lkno;
	}
	public int getScomm_cnt() {
		return scomm_cnt;
	}
	public void setScomm_cnt(int scomm_cnt) {
		this.scomm_cnt = scomm_cnt;
	}
	
	
	
	
}
