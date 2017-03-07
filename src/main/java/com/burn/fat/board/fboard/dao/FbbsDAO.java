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

	/*�ڷ�� ����*/
	public void insertBbs(FbbsBean fbbsbean) throws Exception{
		sqlSession.insert("fbbs_insert",fbbsbean);		
	}

	/* �ڷ�� �� �Խù� ��*/
	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("fbbs_count")).intValue();
		return count;
	}
	
	/* �ڷ�� ��ϰ� ����¡ */
	public List<FbbsBean> getBbsList(Map m) throws Exception {//page,limit
		List<FbbsBean>  list = sqlSession.selectList("fbbs_list",m);
	    return list;
	}

	
	

}