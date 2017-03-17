package com.burn.fat.park.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

import com.burn.fat.park.dao.ParkServiceImpl;
import com.burn.fat.park.model.ParkBean;

@Controller
public class ParkAction {

	@Autowired
	private ParkServiceImpl parkService;  

	/* 공원 지도 */
	@RequestMapping(value="/parkMap.brn")
	public String parkMap(){
		return "html_workout/parkMap"; 
	}
	
	/* 공원 목록 */
	@RequestMapping(value="/parkList.brn")
	public ModelAndView parkList(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="limit", defaultValue="20") int limit
			, @RequestParam(value="findsido", defaultValue="") String findsido
			, @RequestParam(value="findgungu", defaultValue="") String findgungu
			, @RequestParam(value="findname", defaultValue="") String findname ) throws Exception{


		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");

		String mem_sido = "서울특별시"; 
		String mem_gungu = "강남구"; 
		
		if( session.getAttribute("mem_no") != null){
			
			int mem_no = (Integer)session.getAttribute("mem_no");
			
			String mem_addr = parkService.getParkAddr(mem_no); //총 리스트 수를  
	
			System.out.println("주소  : " + mem_addr);
			String[] memjuso = mem_addr.split(" ");
				
			mem_sido  = memjuso[0];
			mem_gungu = memjuso[1]; 
			
		}

		findname = request.getParameter("findname");
		List<ParkBean> parkList = new ArrayList<ParkBean>();
		
		int page = 1;
		
 		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
 		
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit); //session에 limit를 저장하면 페이징처리시 쿼리스트링으로 limit값을 넘겨주지 않아도 된다.
 		} 
 		
 		
 		if(findsido.equals("")){

 	 		if(findgungu.equals("")){
 	 			// 시도 둘다 선택 안한경우, 회원의 지역에 있는 공원을 검색
	 			findsido = mem_sido;
	 			findgungu = mem_gungu;
 	 		} else {
 	 			// 시도만 선택했을경우.
 	 		}
 		} 

		Map mf = new HashMap();
		// 검색값으로 찾은 시,군구
		mf .put("findsido", findsido);
		mf .put("findgungu", findgungu);
		// 검색어	
		mf .put("findname", findname);

		
		int parkcount = parkService.getParklistCount(); //총 리스트 수를  
		int parkfcount = parkService.getParkfindlistCount(mf); //총 리스트 수를  
		
		//총 페이지 수
		int maxpage = (parkfcount + limit-1) / limit;
				 		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page-1) / limit) * limit + 1;

		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 -1;
		if (endpage > maxpage) endpage = maxpage;
		if (endpage < page) page = endpage;  

		Map m = new HashMap();
		// 페이징처리
		m.put("page", page);
		m.put("limit", limit); 
		// 검색값으로 찾은 시,군구
		m.put("findsido", findsido);
		m.put("findgungu", findgungu);
		// 검색어	
		m.put("findname", findname);

		// 공원갯수
		m.put("parkcount", parkcount);
		m.put("parkfcount", parkfcount);
		
		parkList = parkService.getParkList(m);
		ModelAndView model = new ModelAndView("html_workout/parkList");

		model.addObject("findsido", findsido);
		model.addObject("findgungu", findgungu);
		
		model.addObject("parkList", parkList);
		model.addObject("parkfcount", parkfcount);
		model.addObject("parkcount", parkcount);
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("limit", limit);
		
		return model;
		
	}

}