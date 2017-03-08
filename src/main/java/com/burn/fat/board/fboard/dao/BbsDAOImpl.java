package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.fboard.model.BbsBean;

@Repository
public class BbsDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*자료실 저장*/
	public void insertBbs(BbsBean bbsbean) throws Exception{
		sqlSession.insert("bbs_insert",bbsbean);		
	}

	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("bbs_count")).intValue();
		return count;
	}
	
	/* 자료실 목록과 페이징 */
	public List<BbsBean> getBbsList(Map m) throws Exception {//page,limit
		List<BbsBean>  list = sqlSession.selectList("bbs_list",m);
	    return list;
	}

	
	
	/*번호를 기준으로 자료실 내용 가져오기 */
	public BbsBean getBbsCont(int num) throws Exception{
		return (BbsBean)sqlSession.selectOne("bbs_cont", num);
	}

	/*내용보기 할때만 조회수 증가*/
	public void bbsHit(int num) throws Exception{
		sqlSession.update("bbs_hit",num);		
	}

	/*자료실 수정*/
	public void editBbs(BbsBean bbsbean) throws Exception{
		sqlSession.update("bbs_edit",bbsbean);		
	}

	/*자료실 삭제*/
	public void deleteBbs(int f_no) throws Exception{
		sqlSession.delete("bbs_delete",f_no);		
	}

	/*검색 결과 게시물 수*/
//	public int getListCount3(String find_name,String find_field) throws SQLException{
	public int getListCount3(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("bbsfind_cnt", m)).intValue();
		return count;
	}

	/*검색 결과 페이징 목록*/
	public List<BbsBean> getBbsList3(Map m) throws Exception {
		List<BbsBean>  list = sqlSession.selectList("bbs_find", m);
	    return list;
	}
	
	//추천
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("likecountup", map);
	}

	//스크랩
	public String checkscrap(int f_no) {
		
		return sqlSession.selectOne("checkscrap", f_no);
	}

	//코멘트
	public void changeFcomment(int f_no) {
		sqlSession.update("updatefcommcnt",f_no);
	}

}











