package com.burn.fat.board.eboard.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class EboardBean {
	private int e_no;			//글번호
	private int mem_no;			//작성자회원번호
	private String e_sj;		//글제목
	private String e_ct;		//글내용
	private String e_fl;		//업로드파일경로
	private String e_gl;		//업로드이미지경로
	private int e_rc;			//조회수
	private Date e_dt;		//작성시간
	private int e_lk;			//추천수
	private String e_lkno;		//추천인 회원번호
	
	public int getE_no() {
		return e_no;
	}
	public void setE_no(int e_no) {
		this.e_no = e_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getE_sj() {
		return e_sj;
	}
	public void setE_sj(String e_sj) {
		this.e_sj = e_sj;
	}
	public String getE_ct() {
		return e_ct;
	}
	public void setE_ct(String e_ct) {
		this.e_ct = e_ct;
	}
	public String getE_fl() {
		return e_fl;
	}
	public void setE_fl(String e_fl) {
		this.e_fl = e_fl;
	}
	public String getE_gl() {
		return e_gl;
	}
	public void setE_gl(String e_gl) {
		this.e_gl = e_gl;
	}
	public int getE_rc() {
		return e_rc;
	}
	public void setE_rc(int e_rc) {
		this.e_rc = e_rc;
	}
	public Date getE_dt() {
		return e_dt;
	}
	public void setE_dt(Date e_dt) {
		this.e_dt = e_dt;
	}
	public int getE_lk() {
		return e_lk;
	}
	public void setE_lk(int e_lk) {
		this.e_lk = e_lk;
	}
	public String getE_lkno() {
		return e_lkno;
	}
	public void setE_lkno(String e_lkno) {
		this.e_lkno = e_lkno;
	}
	
	
}
