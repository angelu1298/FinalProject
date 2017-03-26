package com.burn.fat.food.grocery.dao;

import com.burn.fat.food.grocery.model.GroceryBean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groceryService")
public class GroceryServiceImpl {
	
    @Autowired
	private GroceryDAOImpl GroceryDAO;
 
	public int getGrocerylistCount() throws Exception {
		return GroceryDAO.getGrocerylistCount();
	}
	public List<GroceryBean> getGroceryList(Map m) throws Exception {
		return GroceryDAO.getGroceryList(m);
	}
	/*
	public void addtodayGrocery(int cus_no) throws Exception {
		GroceryDAO.addtodayGrocery(cus_no);		
	}*/
	public int getGroceryfindlistCount(Map mf) throws Exception {
		return GroceryDAO.getGroceryfindlistCount(mf);
	}
	 
}
