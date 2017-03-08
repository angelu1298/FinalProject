package com.burn.fat.board.sboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.sboard.model.ScommBean;

@Repository("scommDAO")
public class ScommDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public int insertComm(ScommBean commbean) {
		return sqlSession.insert("insertcomm",commbean);
	}
	public List<ScommBean> getCommList(int s_no) {
		return sqlSession.selectList("scommlist", s_no);
	}
	public int deleteComm(Map<String, Integer> map) {
		return sqlSession.delete("deletecomm",map);
	}
	public ScommBean getComment(ScommBean bean) {
		return sqlSession.selectOne("getcomment",bean);
	}
	public void insertCommRep(ScommBean commbean) {
		sqlSession.insert("insertcommreply",commbean);
	}
	public Integer getCommCnt(int s_no) {
		
		return sqlSession.selectOne("commlistcnt",s_no);
	}
	public ScommBean getComment(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getcommentbyno",map);
	}
	public List<ScommBean> getCommentRef(ScommBean bean) {
		return sqlSession.selectList("getcommentref",bean);
	}
	public int deleteCommExistRep(Map<String, Integer> map) {
		return sqlSession.update("deletecommexistrep",map);
	}

}
