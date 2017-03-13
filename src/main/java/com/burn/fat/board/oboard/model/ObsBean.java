package com.burn.fat.board.oboard.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ObsBean {

	private int o_no;		/*자유게시판 글 번호*/
	private int mem_no;		/*자유게시판 작성자 회원번호*/
	private String o_sj;	/*자유게시판 글 제목*/
	private String o_ct;		/*자유게시판 글 내용*/
	private String o_fl;		/*자유게시판 업로드 파일 경우*/
	private String o_gl;		/*자유게시판 업로드 이미지 경로*/
	private int o_rc;		/*자유게시판 조회수*/
	private Date o_dt;	/*자유게시판 작성시간*/
	private int o_lk;		/*자유게시판 추천수*/
	private String o_lkno; /*자유게시판 추천인 회원번호*/
	private String mem_id;
	
	private int ocomm_no;
	private String ocomm_dt;
	private int ocomm_re_ref;
	private int ocomm_re_lev;
	private int ocomm_re_seq;
	private String ocomm_ct;
	
	private int mem_h;
	private int mem_w;
	private String my_memo;
	private int goal_w;
	
	public int getGoal_w() {
		return goal_w;
	}
	public void setGoal_w(int goal_w) {
		this.goal_w = goal_w;
	}
	public String getMy_memo() {
		return my_memo;
	}
	public void setMy_memo(String my_memo) {
		this.my_memo = my_memo;
	}
	public int getMem_h() {
		return mem_h;
	}
	public void setMem_h(int mem_h) {
		this.mem_h = mem_h;
	}
	public int getMem_w() {
		return mem_w;
	}
	public void setMem_w(int mem_w) {
		this.mem_w = mem_w;
	}
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
//	public String getO_gl() {
///		return o_gl;
//	}
//	public void setO_gl(String o_gl) {
//		this.o_gl = o_gl;
//	}
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
	
	
/*
	private int obs_num;
	private String obs_name;
	private String obs_pass;
	private String obs_subject; //글제목
	private String obs_content; //글내용
	private String obs_file; //이진파일명
	private int obs_re_ref; //글그룹번호
	private int obs_re_lev; //화면에 보이는 답변글 위치번호
	private int obs_re_seq; //답변글 레벨 순서
	private int obs_readcount; //조회수
	private String obs_date; //글 등록날짜
	
	public int getObs_num() {
		return bbs_num;
	}
	public void setBbs_num(int bbs_num) {
		this.bbs_num = bbs_num;
	}
	public String getBbs_name() {
		return bbs_name;
	}
	public void setBbs_name(String bbs_name) {
		this.bbs_name = bbs_name;
	}
	public String getBbs_pass() {
		return bbs_pass;
	}
	public void setBbs_pass(String bbs_pass) {
		this.bbs_pass = bbs_pass;
	}
	public String getBbs_subject() {
		return bbs_subject;
	}
	public void setBbs_subject(String bbs_subject) {
		this.bbs_subject = bbs_subject;
	}
	public String getBbs_content() {
		return bbs_content;
	}
	public void setBbs_content(String bbs_content) {
		this.bbs_content = bbs_content;
	}
	public String getBbs_file() {
		return bbs_file;
	}
	public void setObs_file(String bbs_file) {
		this.bbs_file = bbs_file;
	}
	public int getBbs_re_ref() {
		return bbs_re_ref;
	}
	public void setBbs_re_ref(int bbs_re_ref) {
		this.bbs_re_ref = bbs_re_ref;
	}
	public int getBbs_re_lev() {
		return bbs_re_lev;
	}
	public void setBbs_re_lev(int bbs_re_lev) {
		this.bbs_re_lev = bbs_re_lev;
	}
	public int getBbs_re_seq() {
		return bbs_re_seq;
	}
	public void setBbs_re_seq(int bbs_re_seq) {
		this.bbs_re_seq = bbs_re_seq;
	}
	public int getBbs_readcount() {
		return bbs_readcount;
	}
	public void setBbs_readcount(int bbs_readcount) {
		this.bbs_readcount = bbs_readcount;
	}
	public String getBbs_date() {
		return bbs_date;
	}
	public void setBbs_date(String bbs_date) {
		this.bbs_date = bbs_date.substring(0,10);;
	}	
*/
	
}
