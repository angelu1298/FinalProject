package com.burn.fat.park.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.park.model.ParkBean;

@Repository
public class ParkDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;
 
	public int getParklistCount() throws Exception{
		int parkcount=((Integer)sqlSession.selectOne("park_count")).intValue();
		return parkcount;
	}

	public List<ParkBean> getParkList(Map m) throws Exception {
		List<ParkBean> list = sqlSession.selectList("park_list", m);
	    return list;
	}

	public int getParkfindlistCount(Map mf) throws Exception {
		int parkfcount=((Integer)sqlSession.selectOne("park_fcount", mf)).intValue();
	    return parkfcount;
	} 
	
}