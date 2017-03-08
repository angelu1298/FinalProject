package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.fboard.model.FboardBean;

@Repository
public class FboardDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertBbs(FboardBean bbsbean) throws Exception{
		sqlSession.insert("bbs_insert",bbsbean);		
	}

	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("bbs_count")).intValue();
		return count;
	}
	
	public List<FboardBean> getBbsList(Map m) throws Exception {//page,limit
		List<FboardBean>  list = sqlSession.selectList("bbs_list",m);
	    return list;
	}

	
	
	public FboardBean getBbsCont(int num) throws Exception{
		return (FboardBean)sqlSession.selectOne("bbs_cont", num);
	}

	public void bbsHit(int num) throws Exception{
		sqlSession.update("bbs_hit",num);		
	}

	public void editBbs(FboardBean bbsbean) throws Exception{
		sqlSession.update("bbs_edit",bbsbean);		
	}

	public void deleteBbs(int f_no) throws Exception{
		sqlSession.delete("bbs_delete",f_no);		
	}

//	public int getListCount3(String find_name,String find_field) throws SQLException{
	public int getListCount3(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("bbsfind_cnt", m)).intValue();
		return count;
	}

	public List<FboardBean> getBbsList3(Map m) throws Exception {
		List<FboardBean>  list = sqlSession.selectList("bbs_find", m);
	    return list;
	}
	
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("likecountup", map);
	}

	public String checkscrap(int f_no) {
		
		return sqlSession.selectOne("checkscrap", f_no);
	}

	public void changeFcomment(int f_no) {
		sqlSession.update("updatefcommcnt",f_no);
	}

}











