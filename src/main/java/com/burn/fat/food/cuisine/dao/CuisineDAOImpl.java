package com.burn.fat.food.cuisine.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.food.cuisine.model.CuisineBean;

@Repository
public class CuisineDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getCuisinelistCount() throws Exception{
		int cusinecount=((Integer)sqlSession.selectOne("cuisine_count")).intValue();
		return cusinecount;
	} 
	
	public int getCuisinefindlistCount(Map mf) throws Exception {
		int cuisine_fcount=((Integer)sqlSession.selectOne("cuisine_fcount", mf)).intValue();
	    return cuisine_fcount;
	} 

	public List<CuisineBean> getCuisineList(Map m) throws Exception {
		List<CuisineBean> list = sqlSession.selectList("cuisine_list", m);
	    return list;
	} 
	
	public void addtodayCuisine(Map m) {
		sqlSession.update("cuisine_add", m);		
	} 
	
}