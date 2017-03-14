package com.burn.fat.member.mypage.myinfo.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.mypage.myinfo.dao.MyinfoService;
import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;

@Controller
public class MyinfoAction {

	@Autowired
	private MyinfoService myinfoService;
	//private String saveFolder = "C:/eeworkspace/OBOARD_RABBIT2/src/main/webapp/resources/upload"; // 파일
																									// 저장시킬
	
	/* 마이페이지 레프트단 초기화면 */
	@RequestMapping(value = "/my_view.brn")
	public ModelAndView my_view(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String mem_id = (String)session.getAttribute("mem_id");
		int mem_no = ((Integer)session.getAttribute("mem_no")).intValue();
		
		System.out.println("mem_id = "+mem_id);
		System.out.println("mem_no = "+mem_no);
		
		MyinfoBean myinfobean = this.myinfoService.getMyCont(mem_no);
		MyinfoBean myinfobean_memo = this.myinfoService.mymemo(mem_no);
		
		Calendar cal = Calendar.getInstance();
		   int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
		   int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

		   // 시작요일 확인
		   // - Calendar MONTH는 0-11까지임
		   cal.set(year, month, 1);
		   int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);
		   int calMonth = cal.get(Calendar.MONTH);
		   int calDate = cal.get(Calendar.DATE);
		   // 다음/이전월 계산
		   // - MONTH 계산시 표기월로 계산하기 때문에 +1을 한 상태에서 계산함
		   int prevYear = year;
		   int prevMonth = (month + 1) - 1;
		   int nextYear = year;
		   int nextMonth = (month  + 1) + 1;

		   // 1월인 경우 이전년 12월로 지정
		   if (prevMonth < 1) {
		      prevYear--;
		      prevMonth = 12;
		   }

		   // 12월인 경우 다음년 1월로 지정
		   if (nextMonth > 12) {
		      nextYear++;
		      nextMonth = 1;
		   }
     
			ModelAndView contM = new ModelAndView("html_mypage/mypage_main");
			int lastDay =cal.getActualMaximum(Calendar.DATE);
			System.out.println(lastDay+"  "+bgnWeek);
			contM.addObject("year",year);
			contM.addObject("month",month);
			contM.addObject("bgnWeek",bgnWeek);
			contM.addObject("prevYear",prevYear);
			contM.addObject("prevMonth",prevMonth);
			contM.addObject("nextYear",nextYear);
			contM.addObject("nextMonth",nextMonth);
			contM.addObject("lastDay",lastDay);
			contM.addObject("calMonth",calMonth);
			contM.addObject("calDate",calDate);
			contM.addObject("myinfobean",myinfobean);
			contM.addObject("myinfobean_memo",myinfobean_memo);
			
			return contM;
	}
/***************************************************************************	*/
	
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
