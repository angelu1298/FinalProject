package com.burn.fat.food.juice.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.food.juice.dao.JuiceServiceImpl;
import com.burn.fat.food.juice.model.JuiceBean;
import com.burn.fat.food.juice.model.RecipeBean; 

@Controller
public class JuiceAction {

	@Autowired
	private JuiceServiceImpl juiceService;  

	/*주스리스트*/
	@RequestMapping(value="/juiceMakeName.brn")
	public String juiceMakeName(){
		return "html_recipe/juiceMakeName"; 
	}

	/* 주스 삭제 모달 */
	@RequestMapping(value="/recipeDelete.brn")
	public String recipeDelete(){
		return "html_recipe/recipeDelete"; 
	}
	
	/* 주스리스트 삭제 */
	@RequestMapping(value="/recipeDeleteOk.brn")
	public ModelAndView recipeDeleteOk( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		
		int rcp_no = Integer.parseInt(request.getParameter("rcp_no"));
		RecipeBean recipebean = new RecipeBean();
		
		this.juiceService.deleteRecipe(rcp_no);//디비로 부터 레코드 삭제합니다.
		response.sendRedirect("recipeList.brn");
			
		return null;
	}
	
	/* 주스 1인분 오늘 식단에 추가하기 */
	@RequestMapping(value="/recipeAddToday.brn")
	public String recipeAddToday(){
		return "html_recipe/recipeAddToday"; 
	}
	
	/* 주스리스트  오늘의 식단에 추가 모달창 */
	@RequestMapping(value="/recipeAddTodayOk.brn")
	public ModelAndView recipeAddTodayOk( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");

		int rcp_no = Integer.parseInt(request.getParameter("rcp_no"));
		int rcp_cal = Integer.parseInt(request.getParameter("rcp_cal"));
		
		RecipeBean recipebean = new RecipeBean();
		
		Map m = new HashMap();
		m.put("rcp_no", rcp_no);
		m.put("rcp_cal", rcp_cal);
		
		this.juiceService.addtodayRecipe(m);//디비로 부터 레코드 삭제합니다.
		response.sendRedirect("recipeList.brn");
			
		return null;
	}

	/*주스만들기*/ 
	@RequestMapping(value="/juiceMake.brn")
	public ModelAndView juiceMake(HttpServletRequest request, HttpServletResponse response) throws Exception{

		// 리스트를 생성
		List<JuiceBean> JuiceFList = juiceService.JuiceFList();
		List<JuiceBean> JuiceVList = juiceService.JuiceVList();
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("JuiceFList", JuiceFList);
		model.addObject("JuiceVList", JuiceVList);
		 
		model.setViewName("html_recipe/juiceMake");
		return model;	
		
	}

	/*주스리스트*/
	@RequestMapping(value="/juiceMakeResult.brn", method=RequestMethod.POST)
	public void juiceMakeResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		
		int mem_no = Integer.parseInt((String)session.getAttribute("mem_no"));
		
		RecipeBean recipebean = new RecipeBean();

		String rcp_tt = request.getParameter("rcp_tt");
		String rcp_nt = request.getParameter("rcp_nt");
		float rcp_cal = Float.parseFloat(request.getParameter("rcp_cal"));
		float rcp_tan = Float.parseFloat(request.getParameter("rcp_tan"));
		float rcp_dan = Float.parseFloat(request.getParameter("rcp_dan"));
		float rcp_gee = Float.parseFloat(request.getParameter("rcp_gee"));
		float rcp_na  = Float.parseFloat(request.getParameter("rcp_na"));
		float rcp_col = Float.parseFloat(request.getParameter("rcp_col"));
		float rcp_poj = Float.parseFloat(request.getParameter("rcp_poj"));
		float rcp_trs = Float.parseFloat(request.getParameter("rcp_trs"));

		recipebean.setRcp_tt(rcp_tt);
		recipebean.setRcp_nt(rcp_nt);
		recipebean.setMem_no(mem_no);
		recipebean.setRcp_cal(rcp_cal);
		recipebean.setRcp_tan(rcp_tan);
		recipebean.setRcp_dan(rcp_dan);
		recipebean.setRcp_gee(rcp_gee);
		recipebean.setRcp_na(rcp_na);
		recipebean.setRcp_col(rcp_col);
		recipebean.setRcp_poj(rcp_poj);
		recipebean.setRcp_trs(rcp_trs);
 
		this.juiceService.makeRecipe(recipebean);
		response.sendRedirect("recipeList.brn");
		
	}
	
	
	@RequestMapping(value="/juiceMake02.brn", method=RequestMethod.GET)
	public ModelAndView juiceMake02(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		List<JuiceBean> Resultlist = new ArrayList<JuiceBean>();
		
		ModelAndView model = new ModelAndView();

		String[] frulist;
		String[] veglist ;
		int total = 0;

		if(request.getParameterValues("frulist")!=null){
			frulist = request.getParameterValues("frulist");
			for(int i=0; i<frulist.length; i++){
				Resultlist.add(juiceService.JuiceSelectList(frulist[i].trim()));
			}
			total += frulist.length;
		}

		if(request.getParameterValues("veglist")!=null){
			veglist = request.getParameterValues("veglist");
			for(int i=0; i<veglist.length; i++){
				Resultlist.add(juiceService.JuiceSelectList(veglist[i].trim()));
			}
			total += veglist.length;
		}
		
		model.addObject("total",total);
		model.addObject("Resultlist",Resultlist);
		model.setViewName("html_recipe/juiceMake02");
		return model;	
		
	}

	/* 레시피 목록 */
	@RequestMapping(value="/recipeList.brn")
	public ModelAndView recipeList(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="findname", defaultValue="") String findname) throws Exception{

 		HttpSession session = request.getSession();
		int rcount = juiceService.getRecipelistCount(); //총 리스트 수를 
				 		
		int mem_no = Integer.parseInt((String)session.getAttribute("mem_no"));
		findname = request.getParameter("findname");
		
		List<RecipeBean> recipeList = new ArrayList<RecipeBean>();

		Map m = new HashMap();
		m.put("mem_no", mem_no);
		m.put("findname", findname);
		
		recipeList = juiceService.getRecipeList(m);
		ModelAndView model = new ModelAndView("html_recipe/recipeList");

		model.addObject("rcount", rcount);
		model.addObject("recipeList", recipeList);
		
		return model;	
	}

}