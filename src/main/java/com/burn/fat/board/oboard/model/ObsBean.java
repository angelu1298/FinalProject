package com.burn.fat.board.oboard.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ObsBean {

	private int o_no;		/*�����Խ��� �� ��ȣ*/
	private int mem_no;		/*�����Խ��� �ۼ��� ȸ����ȣ*/
	private String o_sj;	/*�����Խ��� �� ����*/
	private String o_ct;		/*�����Խ��� �� ����*/
	private String o_fl;		/*�����Խ��� ���ε� ���� ���*/
	private String o_gl;		/*�����Խ��� ���ε� �̹��� ���*/
	private int o_rc;		/*�����Խ��� ��ȸ��*/
	private Date o_dt;	/*�����Խ��� �ۼ��ð�*/
	private int o_lk;		/*�����Խ��� ��õ��*/
	private String o_lkno; /*�����Խ��� ��õ�� ȸ����ȣ*/
	private String mem_id;
	
	private int ocomm_no;
	private String ocomm_dt;
	private int ocomm_re_ref;
	private int ocomm_re_lev;
	private int ocomm_re_seq;
	private String ocomm_ct;
	
	

	public int getOcomm_no() {
		return ocomm_no;
	}
	public void setOcomm_no(int ocomm_no) {
		this.ocomm_no = ocomm_no;
	}
	public String getOcomm_dt() {
		return ocomm_dt;
	}
	public void setOcomm_dt(String ocomm_dt) {
		this.ocomm_dt = ocomm_dt;
	}
	public int getOcomm_re_ref() {
		return ocomm_re_ref;
	}
	public void setOcomm_re_ref(int ocomm_re_ref) {
		this.ocomm_re_ref = ocomm_re_ref;
	}
	public int getOcomm_re_lev() {
		return ocomm_re_lev;
	}
	public void setOcomm_re_lev(int ocomm_re_lev) {
		this.ocomm_re_lev = ocomm_re_lev;
	}
	public int getOcomm_re_seq() {
		return ocomm_re_seq;
	}
	public void setOcomm_re_seq(int ocomm_re_seq) {
		this.ocomm_re_seq = ocomm_re_seq;
	}
	public String getOcomm_ct() {
		return ocomm_ct;
	}
	public void setOcomm_ct(String ocomm_ct) {
		this.ocomm_ct = ocomm_ct;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getO_sj() {
		return o_sj;
	}
	public void setO_sj(String o_sj) {
		this.o_sj = o_sj;
	}
	public String getO_ct() {
		return o_ct;
	}
	public void setO_ct(String o_ct) {
		this.o_ct = o_ct;
	}
	public String getO_fl() {
		return o_fl;
	}
	public void setO_fl(String o_fl) {
		this.o_fl = o_fl;
	}
	public String getO_gl() {
		return o_gl;
	}

	public void setO_gl(String o_gl) {
		this.o_gl = o_gl;
	}
	public int getO_rc() {
		return o_rc;
	}
	public void setO_rc(int o_rc) {
		this.o_rc = o_rc;
	}
	public Date getO_dt() {
		return o_dt;
	}
	public void setO_dt(Date o_dt) {
		this.o_dt = o_dt;
	}
	public int getO_lk() {
		return o_lk;
	}
	public void setO_lk(int o_lk) {
		this.o_lk = o_lk;
	}
	public String getO_lkno() {
		return o_lkno;
	}
	public void setO_lkno(String o_lkno) {
		this.o_lkno = o_lkno;
	}
	

	
}
