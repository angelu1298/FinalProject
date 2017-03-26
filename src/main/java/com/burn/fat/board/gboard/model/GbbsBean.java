package com.burn.fat.board.gboard.model;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class GbbsBean {

	private int rownum;					//글번호 : ROWNUM 
	private int grnum;					//글번호 : ROWNUM 
	private int gbbs_num;				//글번호 : G_NO 
	private int gbbs_mem_no;			//회원번호 : MEM_NO 
	private String gbbs_userid;			//회원번호 : MEM_ID
	private String gbbs_author;			//회원번호 : MEM_NM
	private String gbbs_subject;		//글제목  : G_SJ 
	private String gbbs_content; 		//글내용   : G_CT 
	private String gbbs_file;			//이진파일명 : g_fl 
	private int gbbs_readcount;			//조회수 : G_RC 
	private Date gbbs_date; 			//글 작성시간  : G_DT 
	private int gbbs_like; 				//글 추천수  : G_LK  
	private String gbbs_likeno; 		//글 회원번호(추천인)  : G_LKNO 
	private String gbbs_state;			// 상태
	private int gbbs_comm;	 			// 추천수
	
	public int getGbbs_comm() {
		return gbbs_comm;
	}
	public void setGbbs_comm(int gbbs_comm) {
		this.gbbs_comm = gbbs_comm;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getGrnum() {
		return grnum;
	}
	public void setGrnum(int grnum) {
		this.grnum = grnum;
	}
	public String getGbbs_state() {
		return gbbs_state;
	}
	public void setGbbs_state(String gbbs_state) {
		this.gbbs_state = gbbs_state;
	}
	public int getGbbs_num() {
		return gbbs_num;
	}
	public void setGbbs_num(int gbbs_num) {
		this.gbbs_num = gbbs_num;
	}
	public int getGbbs_mem_no() {
		return gbbs_mem_no;
	}
	public void setGbbs_mem_no(int gbbs_mem_no) {
		this.gbbs_mem_no = gbbs_mem_no;
	}  
	public String getGbbs_subject() {
		return gbbs_subject;
	}
	public void setGbbs_subject(String gbbs_subject) {
		this.gbbs_subject = gbbs_subject;
	}
	public String getGbbs_content() {
		return gbbs_content;
	}
	public void setGbbs_content(String gbbs_content) {
		this.gbbs_content = gbbs_content;
	}
	public String getGbbs_file() {
		return gbbs_file;
	}
	public void setGbbs_file(String gbbs_file) {
		this.gbbs_file = gbbs_file;
	}
	public int getGbbs_readcount() {
		return gbbs_readcount;
	}
	public void setGbbs_readcount(int gbbs_readcount) {
		this.gbbs_readcount = gbbs_readcount;
	}
	public String getGbbs_userid() {
		return gbbs_userid;
	}
	public void setGbbs_userid(String gbbs_userid) {
		this.gbbs_userid = gbbs_userid;
	}
	public String getGbbs_author() {
		return gbbs_author;
	}
	public void setGbbs_author(String gbbs_author) {
		this.gbbs_author = gbbs_author;
	}
	public Date getGbbs_date() {
		return gbbs_date;
	}
	public void setGbbs_date(Date gbbs_date) {
		this.gbbs_date = gbbs_date;
	}
	public int getGbbs_like() {
		return gbbs_like;
	}
	public void setGbbs_like(int gbbs_like) {
		this.gbbs_like = gbbs_like;
	}
	public String getGbbs_likeno() {
		return gbbs_likeno;
	}
	public void setGbbs_likeno(String gbbs_likeno) {
		this.gbbs_likeno = gbbs_likeno;
	}
	
	 
}
