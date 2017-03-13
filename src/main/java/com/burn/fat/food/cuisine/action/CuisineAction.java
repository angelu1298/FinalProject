package com.burn.fat.food.cuisine.action;

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

import com.burn.fat.food.cuisine.dao.CuisineServiceImpl;
import com.burn.fat.food.cuisine.model.CuisineBean;
import com.burn.fat.food.juice.model.RecipeBean;

@Controller
public class CuisineAction {

	@Autowired
	private CuisineServiceImpl cuisineService;  

	/* CUISINE 오늘 식단에 추가하기 */
	@RequestMapping(value="/cuisineAddToday.brn")
	public String cuisineAddToday(){
		return "html_food/cuisineAddToday"; 
	}

	/* CUISINE 오늘의 식단에 추가 모달창 */
	@RequestMapping(value="/cuisineAddTodayOk.brn")
	public ModelAndView cuisineAddTodayOk( HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="quantity", defaultValue="1") float quantity ) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		
		CuisineBean cuisinebean = new CuisineBean();
		
		int cus_no = Integer.parseInt(request.getParameter("cus_no"));
		int cus_cal =Integer.parseInt(request.getParameter("cus_cal"));
		
		Map m = new HashMap();
		
		m.put("cus_no", cus_no);
		m.put("cus_cal", cus_cal);
		m.put("quantity", quantity);
		
		//디비로 부터 레코드 삭제합니다.
		this.cuisineService.addtodayCuisine(m);
		response.sendRedirect("cuisineList.brn");

		return null;
	}
	
	/* CUISINE 목록 */
	@RequestMapping(value="/cuisineList.brn")
	public ModelAndView cuisineList(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="limit", defaultValue="50") int limit
			, @RequestParam(value="findname", defaultValue="") String findname ) throws Exception{

		
 		HttpSession session = request.getSession();
				 	
		findname = request.getParameter("findname");
		
		List<CuisineBean> cuisineList = new ArrayList<CuisineBean>();
		
		int page = 1;
		
 		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
 		
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 			//session에 limit를 저장하면 페이징처리시 쿼리스트링으로 limit값을 넘겨주지 않아도 된다.
 		} 
 		 
		Map mf = new HashMap();
		// 검색어	
		mf .put("findname", findname);

		int cuisinecount = cuisineService.getCuisinelistCount(); //총 리스트 수를  
		int cuisinefcount = cuisineService.getCuisinefindlistCount(mf); //총 리스트 수를  
		
		//총 페이지 수
		int maxpage = (cuisinefcount + limit-1) / limit;
				 		
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
		m.put("cuisinecount", cuisinecount);
		m.put("cuisinefcount", cuisinefcount);
		
		cuisineList = cuisineService.getCuisineList(m);
		ModelAndView model = new ModelAndView("html_food/cuisineList");
		
		model.addObject("cuisineList", cuisineList);
		model.addObject("cuisinefcount", cuisinefcount);
		model.addObject("cuisinecount", cuisinecount);
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("limit", limit);
		
		return model;
		
	}

}