package com.burn.fat.food.grocery.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.food.grocery.model.GroceryBean;

@Repository
public class GroceryDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getGrocerylistCount() throws Exception{
		int grocerycount=((Integer)sqlSession.selectOne("grocery_count")).intValue();
		return grocerycount;
	} 
	
	public int getGroceryfindlistCount(Map mf) throws Exception {
		int grocery_fcount=((Integer)sqlSession.selectOne("grocery_fcount", mf)).intValue();
	    return grocery_fcount;
	} 

	public List<GroceryBean> getGroceryList(Map m) throws Exception {
		List<GroceryBean> list = sqlSession.selectList("grocery_list", m);
	    return list;
	}
	
	/*
	public void addtodayGrocery(int cus_no) {
		sqlSession.update("grocery_add", cus_no);		
	}
	*/
	
}