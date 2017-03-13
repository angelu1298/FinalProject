package com.burn.fat.food.grocery.action;

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

import com.burn.fat.food.grocery.dao.GroceryServiceImpl;
import com.burn.fat.food.grocery.model.GroceryBean;

@Controller
public class GroceryAction {

	@Autowired
	private GroceryServiceImpl groceryService;  

 
	/* 주스 1인분 오늘 식단에 추가하기 */
	@RequestMapping(value="/groceryAddToday.brn")
	public String groceryAddToday(){
		return "html_food/groceryAddToday"; 
	}
	
	/* 주스리스트  오늘의 식단에 추가 모달창 */
	/*@RequestMapping(value="/groceryAddTodayOk.brn")
	public ModelAndView groceryAddTodayOk( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		
		int grc_no = Integer.parseInt(request.getParameter("grc_no"));
		GroceryBean grocerybean = new GroceryBean();
		
		this.groceryService.addtodayGrocery(grc_no);//디비로 부터 레코드 삭제합니다.
		response.sendRedirect("groceryList.brn");
			
		return null;
	}*/
	/* 공원 목록 */
	@RequestMapping(value="/groceryList.brn")
	public ModelAndView groceryList(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="limit", defaultValue="50") int limit
			, @RequestParam(value="findname", defaultValue="") String findname ) throws Exception{

		
 		HttpSession session = request.getSession();
		findname = request.getParameter("findname");
		List<GroceryBean> groceryList = new ArrayList<GroceryBean>();
		
		int page = 1;
		
 		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
 		
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit); //session에 limit를 저장하면 페이징처리시 쿼리스트링으로 limit값을 넘겨주지 않아도 된다.
 		} 
 		 
		Map mf = new HashMap();
		// 검색어	
		mf .put("findname", findname);

		int grocerycount = groceryService.getGrocerylistCount(); //총 리스트 수를  
		int groceryfcount = groceryService.getGroceryfindlistCount(mf); //총 리스트 수를  
		
		//총 페이지 수
		int maxpage = (groceryfcount + limit-1) / limit;
				 		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page-1) / limit) * limit + 1;

		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) endpage = maxpage;
		if (endpage < page) page = endpage;  

		Map m = new HashMap();
		
		// 페이징처리
		m.put("page", page);
		m.put("limit", limit);  
		// 검색어	
		m.put("findname", findname);

		// 공원갯수
		m.put("grocerycount", grocerycount);
		m.put("groceryfcount", groceryfcount);
		
		groceryList = groceryService.getGroceryList(m);
		ModelAndView model = new ModelAndView("html_food/groceryList");
		
		model.addObject("groceryList", groceryList);
		model.addObject("groceryfcount", groceryfcount);
		model.addObject("grocerycount", grocerycount);
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("limit", limit);
		
		return model;
		
	}

}