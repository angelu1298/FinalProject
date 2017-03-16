package com.burn.fat.food.juice.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.food.juice.model.JuiceBean;
import com.burn.fat.food.juice.model.RecipeBean;

@Repository
public class JuiceDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 과일 리스트
	public List<JuiceBean> getJuiceFList() throws Exception {
		List<JuiceBean> list = sqlSession.selectList("juice_flist");
	    return list;
	}
	
	// 채소 리스트
	public List<JuiceBean> getJuiceVList() throws Exception {
		List<JuiceBean> list = sqlSession.selectList("juice_vlist");
	    return list;
	}

	public JuiceBean getJuiceSelectList(String juc_tt) throws Exception {
		return (JuiceBean)sqlSession.selectOne("select_juice", juc_tt);
	}

	public int makeRecipe(RecipeBean recipebean) throws Exception{
		return sqlSession.insert("insert_recipe", recipebean);		
	}

	public int getRecipelistCount(int mem_no) throws Exception{
		int rcount=((Integer)sqlSession.selectOne("recipe_count",mem_no)).intValue();
		return rcount;
	}

	public List<RecipeBean> getRecipeList(Map m) throws Exception {
		List<RecipeBean> list = sqlSession.selectList("recipe_list", m);
	    return list;
	}

	public void deleteRecipe(int rcp_no) throws Exception {
		sqlSession.delete("recipe_delete", rcp_no);		
		
	}

	public void addtodayRecipe(Map m) {
		sqlSession.update("recipe_add", m);		
	} 
	
}