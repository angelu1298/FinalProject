package com.burn.fat.board.eboard.dao;


import java.util.List;
import java.util.Map;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.eboard.model.EcommBean;

public interface EboardService {

	/* 최대값 번호 구하기 */
	public int getNo() throws Exception;
		
	/*자료실 저장*/
	public void insertEbo(EboardBean eboBean) throws Exception;
	
	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception;
	
	/* 자료실 목록과 페이징 */
	public List<EboardBean> getEboList(Map m) throws Exception;
	
	/*번호를 기준으로 자료실 내용 가져오기 */
	public EboardBean getEboCont(int num) throws Exception;

	/*내용보기 할때만 조회수 증가*/
	public void eboHit(int num) throws Exception;
	
	/*자료실 수정*/
	public void editEbo(EboardBean ebobean) throws Exception;

	/*자료실 삭제*/
	public void deleteEbo(int ebo_no) throws Exception;
	
	/* 글쓴이 회원 아이디 구해오기 */
	public String eboIdCk(String id) throws Exception;
	
	/* 글쓴이 회원 번호 구해오기 */
	public int eboNoCk(String id) throws Exception;
	
	/* 좋아요 */
	public void setJoayo(Map m) throws Exception;
	
	/* 좋아요 뽑아오기 */
	public int getJoayo(int e_no) throws Exception;
	
	/* 추천인 회원번호 저장 */
	public void setLkno(int mem_no) throws Exception;
	
	/*추천인 회원번호 불러오기*/
	public String getLkno(int e_no) throws Exception;
	
	/*검색 결과 게시물 수*/
	public int getFindName(Map m) throws Exception;
	
	/*검색 결과 페이징 목록*/
	public List<EboardBean> getEboList3(Map m) throws Exception;
	
	/*테이블에 있는 아이디 얻어오기*/
	public String findId() throws Exception;
	
//	코멘트 내용을 코멘트 테이블에 담는다
	public void setComm(Map m) throws Exception;
	
	// 코멘트 총갯수 
	public int commCount(int e_no) throws Exception;
	
	// 코멘트목록 불러오기
	public List<EcommBean> getComm(int e_no) throws Exception;
	
	//코멘트아디
	public String getcId(int mem_no) throws Exception;
	
	
	/*답글 레벨 증가*/
	public void refEdit(EcommBean ecommb) throws Exception;

	/*답글 저장*/
	public void ecommReply(EcommBean ecommb) throws Exception;
	
	/*코멘트 삭제*/
	public void deleteEcomm(Map m) throws Exception;
	
	/*코멘트 답글 삭제
	public void deleteEcomm_Re(Map m) throws Exception;*/
	
	// 코멘트 불러오기 
	public List<EcommBean> getComm_re(Map<String, Integer> map) throws Exception;
		
	// 코멘트에 달린 댓글 확인
	public List<EcommBean> getcommentref(EcommBean ecommbean) throws Exception;
		
	//코멘트 삭제 코멘트 댓글이 없는 경우
	public int deleteComm(Map<String, Integer> m) throws Exception;
		
	//현재 코멘트에 댓글이 있는 경우 삭제된 코멘트 입니다
	public int deleteCommExistRep(Map<String, Integer> m) throws Exception;
	
	
}
