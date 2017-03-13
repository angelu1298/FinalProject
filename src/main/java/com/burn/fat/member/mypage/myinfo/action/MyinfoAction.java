package com.burn.fat.member.mypage.myinfo.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.mypage.myinfo.dao.MyinfoService;
import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;

@Controller
public class MyinfoAction {

	@Autowired
	private MyinfoService myinfoService;
	//private String saveFolder = "C:/eeworkspace/OBOARD_RABBIT2/src/main/webapp/resources/upload"; // 파일
																									// 저장시킬
	
	
/***************************************************************************	*/
	/* 마이페이지 레프트단 초기화면 */
	@RequestMapping(value = "/my_view.brn")
	public ModelAndView my_view(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String mem_id = (String)session.getAttribute("mem_id");
		int mem_no = (Integer)session.getAttribute("mem_no");
		
		System.out.println("mem_id = "+mem_id);
		System.out.println("mem_no = "+mem_no);
		
		MyinfoBean myinfobean = this.myinfoService.getMyCont(mem_no);
		MyinfoBean myinfobean_memo = this.myinfoService.mymemo(mem_no);
		
		ModelAndView contM = new ModelAndView("html_mypage/mypage_main");
		
		contM.addObject("myinfobean",myinfobean);
		contM.addObject("myinfobean_memo",myinfobean_memo);
		
		return contM;
	}
	
	@RequestMapping(value = "/memo_edit.brn")
	public ModelAndView memo_edit(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		int mem_no = (Integer)session.getAttribute("mem_no");            
		String my_memo = request.getParameter("my_memo");
		
		System.out.println("mem_no = "+mem_no);
		System.out.println("my_memo = "+my_memo);
		
		MyinfoBean myinfobean = new MyinfoBean();
		
		myinfobean.setMem_no(mem_no);
		myinfobean.setMy_memo(my_memo);
				
		this.myinfoService.my_update(myinfobean);// 수정메서드 호출

		System.out.println("*****************************");
		System.out.println("update success");
		System.out.println("*****************************");

		response.sendRedirect("my_view.brn");
	
			
		return null;
		
	}
	

	@RequestMapping(value = "/goalw_edit.brn")
	public ModelAndView goalw_edit(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		int mem_no = (Integer)session.getAttribute("mem_no");            
		int goal_w = Integer.parseInt(request.getParameter("goal_w"));
		
		System.out.println("mem_no = "+mem_no);
		System.out.println("goal_w = "+goal_w);
		
		MyinfoBean myinfobean = new MyinfoBean();
		
		myinfobean.setMem_no(mem_no);
		myinfobean.setGoal_w(goal_w);
				
		this.myinfoService.goal_w_update(myinfobean);// 수정메서드 호출

		System.out.println("*****************************");
		System.out.println("update success");
		System.out.println("*****************************");

		response.sendRedirect("my_view.brn");
	
			
		return null;
		
	}
	
	
}
