package com.burn.fat.board.fboard.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FbbsBean {

	private int f_no;
	private int mem_no;//작성자 회원 번호
	private String f_sj;//글제목
	private String f_ct;//글내용(Clob)
	private String f_fl;//업로드파일경로
	private int f_rc;//조회수
	private Date f_dt;//작성시간
	private int f_lk;//추천수
	private String f_lkno;//회원번호(추천인)
	
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
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
	public Date getF_dt() {
		return f_dt;
	}
	public void setF_dt(Date f_dt) {
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
	
	
	
}
