package com.burn.fat.food.juice.dao;

import com.burn.fat.food.juice.model.JuiceBean;
import com.burn.fat.food.juice.model.RecipeBean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("juiceService")
public class JuiceServiceImpl {
	
    @Autowired
	private JuiceDAOImpl JuiceDAO;

	public List<JuiceBean> JuiceFList() throws Exception {
		return JuiceDAO.getJuiceFList();
	}
	public List<JuiceBean> JuiceVList() throws Exception {
		return JuiceDAO.getJuiceVList();
	} 
 
	public JuiceBean JuiceSelectList(String juc_tt)  throws Exception{
		return JuiceDAO.getJuiceSelectList(juc_tt);
	}
	
	public void makeRecipe(RecipeBean recipebean) throws Exception {
		JuiceDAO.makeRecipe(recipebean);
	}   

	public int getRecipelistCount(int mem_no) throws Exception {
		return JuiceDAO.getRecipelistCount(mem_no);
	}

	public List<RecipeBean> getRecipeList(Map m) throws Exception {
		return JuiceDAO.getRecipeList(m);
	}

	public void deleteRecipe(int rcp_no) throws Exception {
		JuiceDAO.deleteRecipe(rcp_no);		
	}

	public void addtodayRecipe(Map m) throws Exception {
		JuiceDAO.addtodayRecipe(m);		
	}
	 
}
