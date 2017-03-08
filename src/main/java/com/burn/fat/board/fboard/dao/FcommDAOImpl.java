package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.fboard.model.FcommBean;


@Repository
public class FcommDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public List<FcommBean> getListCmt(int f_no) throws Exception {
		List<FcommBean> list =sqlSession.selectList("fcommlist", f_no);
		
        return list;
   }

    public int createCmt(FcommBean bean) throws Exception{
    	return sqlSession.insert("insertcomm", bean);
   }

    public int deleteCmt(Map m) throws Exception{
    	return sqlSession.delete("deletecomm", m);
   }
    ///////

    
    public FcommBean getComment(FcommBean bean) {
		return sqlSession.selectOne("getcomment",bean);
	}
	public void insertCommRep(FcommBean bean) {
		sqlSession.insert("insertcommreply",bean);
	}
	
	public int deleteCommExistRep(Map m) {
		return sqlSession.update("deletecommexistrep",m);
	}
	
	public List<FcommBean> getCommentRef(FcommBean bean) {
		return sqlSession.selectList("getcommentref",bean);
	}
}
