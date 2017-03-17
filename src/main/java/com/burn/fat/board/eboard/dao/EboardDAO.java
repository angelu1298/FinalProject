package com.burn.fat.board.eboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.eboard.model.EcommBean;

@Repository
public class EboardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/* 스크랩 여부 확인 */
	public String checkscrap(int e_no) {
		return sqlSession.selectOne("Eboard.checkscrap", e_no);
	}

	/* 좋아요 증가 */
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("Eboard.likecountup", map);
	}


	/* 최대값 번호 구하기 */
	public int getNo() throws Exception{
		return sqlSession.selectOne("Eboard.ebo_getNo");
	}
	
	/* 자료실 저장 */
	public void insertEbo(EboardBean ebobean) throws Exception {
		sqlSession.insert("Eboard.ebo_insert", ebobean);
	}
	
	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception{
		int count = sqlSession.selectOne("Eboard.ebo_count");
		return count;
	}
	
	/* 자료실 목록과 페이징 */
	public List<EboardBean> getEboList(Map m) throws Exception {
		List<EboardBean> list = sqlSession.selectList("Eboard.ebo_list", m);
		return list;
	}
	
	/*번호를 기준으로 자료실 내용 가져오기 */
	public EboardBean getEboCont(int e_no) throws Exception{
		EboardBean bo = sqlSession.selectOne("Eboard.ebo_cont", e_no);
		return bo;
	}

	/*내용보기 할때만 조회수 증가*/
	public void eboHit(int num) throws Exception{
		sqlSession.update("Eboard.ebo_hit", num);
	}
	
	/*자료실 수정*/
	public void editEbo(EboardBean ebobean) throws Exception{
		sqlSession.update("Eboard.ebo_edit", ebobean);
	}

	/*자료실 삭제*/
	public void deleteEbo(int ebo_no) throws Exception{
		sqlSession.delete("Eboard.ebo_delete", ebo_no);
		sqlSession.update("Eboard.ebo_updateNo", ebo_no);
	}
	
	/* 글쓴이 회원 아이디 구해오기 */
	public String eboIdCk(String id) throws Exception{
		return sqlSession.selectOne("Eboard.mem_IdCk", id);
	}
	
	/* 글쓴이 회원 번호 구해오기 */
	public int eboNoCk(String id) throws Exception{
		return sqlSession.selectOne("Eboard.mem_NoCk", id);
	}
	
	
	/* 좋아요 업데이트 */
	public void setJoayo(Map m) throws Exception{
		sqlSession.update("Eboard.joayo_up",m);
	}
	
	/* 좋아요 뽑아오기 */
	public int getJoayo(int e_no) throws Exception{
		return sqlSession.selectOne("joayo_get",e_no);
	}
	
	/* 추천인 회원번호 저장 */
	public void setLkno(int mem_no) throws Exception{
		sqlSession.update("Eboard.setLkno", mem_no);
	}
	
	/*추천인 회원번호 불러오기*/
	public String getLkno(int e_no) throws Exception{
		return sqlSession.selectOne("Eboard.getLkno", e_no);
	}
	
	/*이름 검색 결과 게시물 수*/
	public int getFindName(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("Eboard.getFindName", m)).intValue();
		return count;
	}
	
	/*검색 결과 페이징 목록*/
	public List<EboardBean> getEboList3(Map m) throws Exception {
		List<EboardBean>  list = sqlSession.selectList("Eboard.getEboList3", m);
	    return list;
	}
	
	/*테이블에 있는 아이디 얻어오기*/
	public String findId() throws Exception{
		return sqlSession.selectOne("findId");
	}
	
//	코멘트 내용을 코멘트 테이블에 담는다
	public int insertEComm(EcommBean ecommb) throws Exception{
		return sqlSession.insert("Eboard.insertEComm",ecommb);
	}
	
	// 코멘트 총갯수 
	public int commCount(int e_no) throws Exception{
		return sqlSession.selectOne("Eboard.commCount",e_no);
	}
	
	
	public void changeEcommcnt(int e_no) {
		sqlSession.update("Eboard.changeEcommcnt",e_no);
	}
		
	
	
	// 코멘트목록 불러오기
	public EcommBean getEComment(EcommBean ecommb) throws Exception{
		EcommBean elist = sqlSession.selectOne("Eboard.getEComment", ecommb);
		return elist;
	}
	
	// 코멘트목록 불러오기
	public List<EcommBean> getECommList(int e_no) throws Exception{
		List<EcommBean> elist = sqlSession.selectList("Eboard.getECommList", e_no);
		return elist;
	}
	
	//코멘트아디
	public String getcId(int mem_no) throws Exception{
		return sqlSession.selectOne("getcId", mem_no);
	}
	
	/*답글 레벨 증가*/
	public void refEdit(EcommBean ecommb) throws Exception{
		sqlSession.update("Eboard.ecomm_Level",ecommb); 		
	}

	/*답글 저장*/
	public void insertECommRep(EcommBean ecommb) throws Exception{
		sqlSession.insert("Eboard.insertECommRep",ecommb);
	}
	
	
	/*코멘트 삭제*/
	public void deleteEcomm(Map m) throws Exception{
		sqlSession.delete("Eboard.deleteEcomm", m);
	}
	
	/*코멘트 답글 삭제
	public void deleteEcomm_Re(Map m) throws Exception{
		sqlSession.delete("deleteEcomm_Re", m);
	}*/
	
	// 코멘트의 답글 불러오기 
	public List<EcommBean> getComm_re(EcommBean ecommbean) throws Exception{
		return sqlSession.selectOne("Eboard.getComm_re", ecommbean);
	}
	
	// 코멘트에 달린 댓글 확인
	public List<EcommBean> getcommentref(EcommBean ecommbean) throws Exception{
		return sqlSession.selectList("Eboard.getcommentref", ecommbean);
	}
	
	//코멘트 삭제 코멘트 답글이 없는 경우
	public int deleteComm(Map<String, Integer> m) throws Exception{
		return sqlSession.delete("Eboard.deletecomm", m);
	}
	
	//현재 코멘트에 답글이 있는 경우 삭제된 코멘트 입니다
	public int deleteCommExistRep(Map<String, Integer> m) throws Exception{
		return sqlSession.update("Eboard.deletecommexistrep", m);
	}
	
	
	
}
