package com.burn.fat.board.oboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.oboard.model.OcommBean;

@Repository
public class OcommDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public int insertOComm(OcommBean ocommbean) {
		return sqlSession.insert("o_insertcomm",ocommbean);
	}
	public List<OcommBean> getOCommList(int o_no) {
		return sqlSession.selectList("o_commlist", o_no);
	}
	public int deleteOComm(Map<String, Integer> map) {
		return sqlSession.delete("o_deletecomm",map);
	}
	public OcommBean getOComment(OcommBean ocommbean) {
		return sqlSession.selectOne("o_getcomment",ocommbean);
	}
	public void insertOCommRep(OcommBean ocommbean) {
		sqlSession.insert("o_insertcommreply",ocommbean);
	}
	public Integer getOCommCnt(int o_no) {
		
		return sqlSession.selectOne("o_commlistcnt",o_no);
	}
	public OcommBean getOComment(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("o_getcommentbyno",map);
	}
	public List<OcommBean> getOCommentRef(OcommBean ocommbean) {
		return sqlSession.selectList("o_getcommentref",ocommbean);
	}
	public int deleteOCommExistRep(Map<String, Integer> map) {
		return sqlSession.update("o_deletecommexistrep",map);
	}

}
