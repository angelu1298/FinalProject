package com.burn.fat.board.gboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.gboard.model.GcommBean;

@Repository("gcommDAO")
public class GcommDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public int insertComm(GcommBean commbean) {
		return sqlSession.insert("insertcomm",commbean);
	}
	public List<GcommBean> getCommList(int gbbs_num) {
		return sqlSession.selectList("gcommlist", gbbs_num);
	}
	public int deleteComm(Map<String, Integer> map) {
		return sqlSession.delete("deletecomm",map);
	}
	public GcommBean getComment(GcommBean commbean) {
		return sqlSession.selectOne("getcomment",commbean);
	}
	public void insertCommRep(GcommBean commbean) {
		sqlSession.insert("insertcommreply",commbean);
	}
	public Integer getCommCnt(int gbbs_num) {
		return sqlSession.selectOne("commlistcnt",gbbs_num);
	}
	public GcommBean getComment(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getcommentbyno",map);
	}
	public List<GcommBean> getCommentRef(GcommBean commbean) {
		return sqlSession.selectList("getcommentref",commbean);
	}
	public int deleteCommExistRep(Map<String, Integer> map) {
		return sqlSession.update("deletecommexistrep",map);
	}

}
