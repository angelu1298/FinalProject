package com.burn.fat.member.mypage.myinfo.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.model.MemberBean;
import com.burn.fat.member.mypage.calendar.dao.CalendarService;
import com.burn.fat.member.mypage.calendar.model.CalendarBean;
import com.burn.fat.member.mypage.myinfo.dao.MyinfoService;
import com.burn.fat.member.mypage.myinfo.dao.StatisticsService;

@Controller
public class MyinfoAction {

	@Autowired
	private MyinfoService myinfoService;
	
	 @Autowired
	private CalendarService calendarService;
	 
	 @Autowired
	StatisticsService service;

	/* 마이페이지 레프트단 초기화면 */
	@RequestMapping(value = "/my_view.brn")
	public ModelAndView my_view(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
	
		int mem_no = ((Integer)session.getAttribute("mem_no")).intValue();
		
		
		MemberBean myinfobean = this.myinfoService.getMyinfo(mem_no);
		
		ModelAndView contM = new ModelAndView("html_mypage/mypage_main");

	
		Calendar cal = Calendar.getInstance();
		   int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
		   int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

		   // 시작요일 확인
		   // - Calendar MONTH는 0-11까지임
		   cal.set(year, month, 1);
		   int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);
		   int calMonth = cal.get(Calendar.MONTH);
		   int calDate = cal.get(Calendar.DATE);
		   int lastDay =cal.getActualMaximum(Calendar.DATE);
		   cal.set(year, month,lastDay);
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
	         //평가가져올때 이용할 calendarList
	         List<CalendarBean> calendarList = this.calendarService.getTotal(mem_no);
			int lastDate =cal.get(Calendar.DAY_OF_WEEK);
		
			contM.addObject("year",year);
			contM.addObject("month",month);
			contM.addObject("bgnWeek",bgnWeek);
			contM.addObject("prevYear",prevYear);
			contM.addObject("prevMonth",prevMonth);
			contM.addObject("nextYear",nextYear);
			contM.addObject("nextMonth",nextMonth);
			contM.addObject("lastDay",lastDay);
			contM.addObject("lastDate",lastDate);
			contM.addObject("calMonth",calMonth);
			contM.addObject("calDate",calDate);
			contM.addObject("myinfobean",myinfobean);
			 //평가가져올때 이용할 calendarList
	         contM.addObject("calendarList",calendarList);
			
			return contM;
		
	}
/***************************************************************************	*/
	/* 마이페이지 레프트단 초기화면 */
	@RequestMapping(value = "/my_info.brn")
	public ModelAndView my_info(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String mem_id = (String)session.getAttribute("mem_id");
		int mem_no = ((Integer)session.getAttribute("mem_no")).intValue();
		
		System.out.println("mem_id 마이인포= "+mem_id);
		System.out.println("mem_no 마이인포= "+mem_no);
		
		MemberBean myinfobean = this.myinfoService.getMyinfo(mem_no);
		
		System.out.println("여기는?1-------" + myinfobean);
		ModelAndView contM = new ModelAndView("inc/myinfo");

		System.out.println("여기는?2");
	     
        Map<String,Object> map = new HashMap<String,Object>();
 		Calendar cal2 = Calendar.getInstance();
 		map.put("monthEarly",cal2.get(Calendar.YEAR)*10000+(cal2.get(Calendar.MONTH)+1)*100+cal2.getActualMinimum(Calendar.DATE));
 		map.put("monthEnd",cal2.get(Calendar.YEAR)*10000+(cal2.get(Calendar.MONTH)+1)*100+cal2.getActualMaximum(Calendar.DATE));
 		List<Double> average = service.getAverage(map);
 		Iterator<Double> itr = average.iterator();
 		double avgsum = 0;
 		while(itr.hasNext()){
 			avgsum += itr.next();
 		}
 		int all = service.getMemNum(map);
 		double avrgPerM = 0;
 		if(all!=0){
 			avrgPerM=((double)avgsum) / all;
 		}
		System.out.println("회원 평균 합 avgsum : "+avgsum + " 전체 회원 수 "+all );
		System.out.println(avrgPerM);
		
		contM.addObject("myinfobean", myinfobean);
		contM.addObject("avrgPerM",avrgPerM);
		
		return contM;
	}
	
	@RequestMapping(value = "/memo_edit.brn")
	public ModelAndView memo_edit(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		int mem_no = (Integer)session.getAttribute("mem_no");            
		String my_memo = request.getParameter("my_memo");
		
		System.out.println("mem_no = "+mem_no);
		System.out.println("my_memo = "+my_memo);
		
		/*수정*/
		//MemberBean myinfobean = new MemberBean();
		
		Map m = new HashMap();

		m.put("mem_no", mem_no);
		m.put("my_memo", my_memo);
				
		this.myinfoService.memo_update(m);// 수정메서드 호출

		System.out.println("*****************************");
		System.out.println("memo_update update success");
		System.out.println("*****************************");

		response.sendRedirect("my_view.brn");
	
		return null;
	}
	

	@RequestMapping(value = "/goalw_edit.brn")
	public ModelAndView goalw_edit(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		System.out.println("몸무게 수정");
		
		int mem_no = (Integer)session.getAttribute("mem_no");            
		int goal_w = Integer.parseInt(request.getParameter("goal_w"));
		
		
		System.out.println("mem_no = "+mem_no);
		System.out.println("goal_w = "+goal_w);
		
		/*수정*/
		/*MemberBean myinfobean = new MemberBean();*/
		
		/*myinfobean.setMem_no(mem_no);
		myinfobean.setGoal_w(goal_w);
		ge*/
		
		Map m = new HashMap();

		m.put("mem_no", mem_no);
		m.put("goal_w", goal_w);
		
		this.myinfoService.w_update(m);// 수정메서드 호출

		System.out.println("*****************************");
		System.out.println("w_update update success");
		System.out.println("*****************************");

		response.sendRedirect("my_view.brn");
	
			
		return null;
		
	}
	
	
}
