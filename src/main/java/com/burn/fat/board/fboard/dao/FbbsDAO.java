package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.fboard.model.FbbsBean;

@Repository
public class FbbsDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/*자료실 저장*/
	public void insertBbs(FbbsBean fbbsbean) throws Exception{
		sqlSession.insert("fbbs_insert",fbbsbean);		
	}

	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("fbbs_count")).intValue();
		return count;
	}
	
	/* 자료실 목록과 페이징 */
	public List<FbbsBean> getBbsList(Map m) throws Exception {//page,limit
		List<FbbsBean>  list = sqlSession.selectList("fbbs_list",m);
	    return list;
	}

	
	

}