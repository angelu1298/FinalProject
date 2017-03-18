package com.burn.fat.member.mypage.calendar.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.mypage.calendar.dao.CalendarService;

@Controller
public class CalendarAction {

	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(value="/week_plus.brn")	//다음주
	public ModelAndView a(
			@RequestParam("y") int y,
			@RequestParam("m") int m,
			@RequestParam("d") int d,
			HttpServletRequest request,
								HttpServletResponse response,
								HttpSession session) throws Exception {
		
		 System.out.println("넘어온 월 : " + m);
		 Calendar cal = Calendar.getInstance();
		 cal.set(y,m-1,d);
		
		 System.out.println("계산할 월 : " + (m-1));//3
		 System.out.println("날짜 = " +  d);
		  
		 int endday = cal.getActualMaximum(Calendar.DATE);
		 
		 System.out.println("마지막 일:"+endday);//
		 
		 d=d+7;
		 
		 System.out.println("다음주 일 = " + d);
		 
		if( d > endday ){
			m = m+1;
			d = d-endday;
		}

		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
		mv.addObject("y",y);
		mv.addObject("m",m);
		mv.addObject("d",d);
		
		return mv;
	}
	
	@RequestMapping(value = "/week_minus.brn")	//이번주
	   public ModelAndView week_minus(@RequestParam("y") int y, @RequestParam("m") int m, @RequestParam("d") int d,
	         HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

	      Calendar cal = Calendar.getInstance();

	      cal.set(y, (m-1)-1 , d);
	     
	      int endday = cal.getActualMaximum(Calendar.DATE);
	      
	      System.out.println(endday);

	      System.out.println("전달의 마지막 일:" + endday);

	      d=d-7;
	      
	      if (d < 1) {
	         m = m - 1;
	         d = endday + d;

	         System.out.println("d:" + d);
	      }

	      ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
	      mv.addObject("y", y);
	      mv.addObject("m", m);
	      mv.addObject("d", d);

	      return mv;
	}
	
}
