package com.burn.fat.member.mypage.calendar.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.mypage.calendar.dao.CalendarService;
import com.burn.fat.member.mypage.calendar.model.CalendarBean;

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
		
		 Calendar cal = Calendar.getInstance();
		 cal.set(y,m-1,d);
		
		  
		 int endday = cal.getActualMaximum(Calendar.DATE);
		 
		 
		 d=d+7;
		 
		 
		if( d > endday ){
			m = m+1;
			d = d-endday;
		}
		int mem_no = (Integer) session.getAttribute("mem_no");
		String cal_date = String.valueOf(y+m+d);
		
		Map<String, Object> m3 = new HashMap<String, Object>();
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		
		List<CalendarBean> calendarList = this.calendarService.getE_kcal(m3);
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
		mv.addObject("y",y);
		mv.addObject("m",m);
		mv.addObject("d",d);
		mv.addObject("calendarList",calendarList);
		
		return mv;
	}
	
	@RequestMapping(value = "/week_minus.brn")	//이번주
	   public ModelAndView week_minus(@RequestParam("y") int y, @RequestParam("m") int m, @RequestParam("d") int d,
	         HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

	      Calendar cal = Calendar.getInstance();

	      cal.set(y, (m-1)-1 , d);
	     
	      int endday = cal.getActualMaximum(Calendar.DATE);
	      

	      d=d-7;
	      
	      if (d < 1) {
	         m = m - 1;
	         d = endday + d;

	      }

	      int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = String.valueOf(y+m+d);
			
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
			
			List<CalendarBean> calendarList = this.calendarService.getE_kcal(m3);
			ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
			mv.addObject("y",y);
			mv.addObject("m",m);
			mv.addObject("d",d);
			mv.addObject("calendarList",calendarList);

	      return mv;
	}
	
}
