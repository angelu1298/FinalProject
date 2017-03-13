package com.burn.fat.food.cuisine.dao;

import com.burn.fat.food.cuisine.model.CuisineBean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("cuisineService")
public class CuisineServiceImpl {
	
    @Autowired
	private CuisineDAOImpl CuisineDAO;
 
	public int getCuisinelistCount() throws Exception {
		return CuisineDAO.getCuisinelistCount();
	}
	
	public List<CuisineBean> getCuisineList(Map m) throws Exception {
		return CuisineDAO.getCuisineList(m);
	}
	
	public void addtodayCuisine(Map m) throws Exception {
		CuisineDAO.addtodayCuisine(m);		
	}
	
	public int getCuisinefindlistCount(Map mf) throws Exception {
		return CuisineDAO.getCuisinefindlistCount(mf);
	} 
	 
}
