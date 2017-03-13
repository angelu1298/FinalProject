package com.burn.fat.board.eboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.eboard.model.EcommBean;

@Service("eboService")
public class EboardServiceImpl implements EboardService {

	@Autowired
	public EboardDAO eboDAO;
	
	/* 최대값 번호 구하기 */
	public int getNo() throws Exception{
		return eboDAO.getNo();
	}
	
	/*자료실 저장*/
	public void insertEbo(EboardBean eboBean) throws Exception{
		eboDAO.insertEbo(eboBean);
	}
	
	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception{
		return eboDAO.getListCount();
	}
	
	/* 자료실 목록과 페이징 */
	public List<EboardBean> getEboList(Map m) throws Exception{
		return eboDAO.getEboList(m);
	}
	
	/*번호를 기준으로 자료실 내용 가져오기 */
	public EboardBean getEboCont(int num) throws Exception{
		return eboDAO.getEboCont(num);
	}

	/*내용보기 할때만 조회수 증가*/
	public void eboHit(int num) throws Exception{
		eboDAO.eboHit(num);
	}
	
	/*자료실 수정*/
	public void editEbo(EboardBean ebobean) throws Exception{
		eboDAO.editEbo(ebobean);
	}

	/*자료실 삭제*/
	public void deleteEbo(int ebo_no) throws Exception{
		eboDAO.deleteEbo(ebo_no);
	}
	
	
	/* 글쓴이 회원 아이디 구해오기 */
	public String eboIdCk(String id) throws Exception{
		return eboDAO.eboIdCk(id);
	}
	
	/* 글쓴이 회원 번호 구해오기 */
	public int eboNoCk(String id) throws Exception{
		return eboDAO.eboNoCk(id);
	}
	
	/* 좋아요 */
	public void setJoayo(Map m) throws Exception{
		eboDAO.setJoayo(m);
	}
	
	/* 좋아요 뽑아오기 */
	public int getJoayo(int e_no) throws Exception{
		return eboDAO.getJoayo(e_no);
	}
	
	/* 추천인 회원번호 저장 */
	public void setLkno(int mem_no) throws Exception{
		eboDAO.setLkno(mem_no);
	}
	
	/*추천인 회원번호 불러오기*/
	public String getLkno(int e_no) throws Exception{
		return eboDAO.getLkno(e_no);
	}
	
	/*검색 결과 게시물 수*/
	public int getFindName(Map m) throws Exception{
		return eboDAO.getFindName(m);
	}
	
	/*검색 결과 페이징 목록*/
	public List<EboardBean> getEboList3(Map m) throws Exception {
		return eboDAO.getEboList3(m);
	}
	
	/*테이블에 있는 아이디 얻어오기*/
	public String findId() throws Exception{
		return eboDAO.findId();
	}
	
//	코멘트 내용을 코멘트 테이블에 담는다
	public void setComm(Map m) throws Exception{
		eboDAO.setComm(m);
	}
	
	// 코멘트 총갯수 
	public int commCount(int e_no) throws Exception{
		return eboDAO.commCount(e_no);	
	}
	
	// 코멘트목록 불러오기
	public List<EcommBean> getComm(int e_no) throws Exception{
		return eboDAO.getComm(e_no);
	}
	
	//코멘트아디
	public String getcId(int mem_no) throws Exception{
		return eboDAO.getcId(mem_no);
	}
	
	/*답글 레벨 증가*/
	public void refEdit(EcommBean ecommb) throws Exception{
		eboDAO.refEdit(ecommb);
	}

	/*답글 저장*/
	public void ecommReply(EcommBean ecommb) throws Exception{
		eboDAO.ecommReply(ecommb);
	}
	
	
	/*코멘트 삭제*/
	public void deleteEcomm(Map m) throws Exception{
		eboDAO.deleteEcomm(m);
	}
	
	/*코멘트 답글 삭제
	public void deleteEcomm_Re(Map m) throws Exception{
		eboDAO.deleteEcomm_Re(m);
	}*/
	
	// 코멘트 불러오기 
	public List<EcommBean> getComm_re(Map<String, Integer> map) throws Exception{
		return eboDAO.getComm_re(map);
	}
			
	// 코멘트에 달린 댓글 확인
	public List<EcommBean> getcommentref(EcommBean ecommbean) throws Exception{
		return eboDAO.getcommentref(ecommbean);
	}
			
	//코멘트 삭제 코멘트 댓글이 없는 경우
	public int deleteComm(Map<String, Integer> m) throws Exception{
		return eboDAO.deleteComm(m);
	}
			
	//현재 코멘트에 댓글이 있는 경우 삭제된 코멘트 입니다
	public int deleteCommExistRep(Map<String, Integer> m) throws Exception{
		return eboDAO.deleteCommExistRep(m);
	}
	
	
}
