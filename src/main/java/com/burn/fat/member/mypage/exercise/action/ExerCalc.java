package com.burn.fat.member.mypage.exercise.action;

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
import com.burn.fat.member.mypage.exercise.dao.ExerService;
import com.burn.fat.member.mypage.exercise.model.ExerBean;

@Controller
public class ExerCalc {
	
	CalendarBean calendarBean;
	
	@Autowired
	public ExerService exerService;
	
	@Autowired
	public CalendarService calService;
	
	@RequestMapping(value="/mypage_main_weekly_please.brn")
	public ModelAndView mypage_main_weekly_please(
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
		return mv;
	}
	
	@RequestMapping(value="/mypage_main_weekly_start.brn")
	public ModelAndView week_list_start(
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
		return mv;
	}
	
	//맨처음 아침 로드 
	@RequestMapping(value="/goweekly_m_click.brn")
	public ModelAndView goweekly_m_click(
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			@RequestParam("wholeDay") String wholeDay,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		int mem_no = (Integer) session.getAttribute("mem_no");
		String cal_date = y+m+d;
		
		Map m3 = new HashMap();
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		
		m3.put("day", wholeDay);
		String e_kcal = this.calService.getE_kcal2(m3);
		
		ModelAndView mv = new ModelAndView("html_mypage/cal_morning");
		
		mv.addObject("m_e_kcal", e_kcal);
		
		return mv;
	}
	
	
	//맨처음 점심 로드 
		@RequestMapping(value="/goweekly_l_click.brn")
		public ModelAndView goweekly_l_click(
				@RequestParam("y") String y,
				@RequestParam("m") String m,
				@RequestParam("d") String d,
				@RequestParam("wholeDay") String wholeDay,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession session) throws Exception {
			
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
			
			Map m3 = new HashMap();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
			
			m3.put("day", wholeDay);
			String l_e_kcal = this.calService.getE_kcal2(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/cal_lunch");
			
			mv.addObject("l_e_kcal", l_e_kcal);
			
			return mv;
		}
	
		
		
		
	//맨처음 저녁 로드 
			@RequestMapping(value="/goweekly_d_click.brn")
			public ModelAndView goweekly_d_click(
					@RequestParam("y") String y,
					@RequestParam("m") String m,
					@RequestParam("d") String d,
					@RequestParam("wholeDay") String wholeDay,
					HttpServletRequest request,
					HttpServletResponse response,
					HttpSession session) throws Exception {
					
				int mem_no = (Integer) session.getAttribute("mem_no");
				String cal_date = y+m+d;
					
				Map m3 = new HashMap();
				m3.put("cal_date", cal_date);//해당 날짜
				m3.put("mem_no", mem_no);//회원번호
					
				m3.put("day", wholeDay);
				String d_e_kcal = this.calService.getE_kcal2(m3);
					
				ModelAndView mv = new ModelAndView("html_mypage/cal_dinner");
					
				mv.addObject("d_e_kcal", d_e_kcal);
				
				return mv;
			}	
		
	//눌렀을때
	@RequestMapping(value="/goweekly.brn")
	public ModelAndView goweekly(
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		int mem_no = (Integer) session.getAttribute("mem_no");
		String cal_date = y+m+d;
		
		Map m3 = new HashMap();
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
		
		if(request.getParameter("wholeDay")!=null){
			String wholeDay = request.getParameter("wholeDay");
			 
			/*if(wholeDay.equals("morning")){//아침로드
				m3.put("day", wholeDay);
				String m_e_kcal = this.calService.getE_kcal2(m3);
				mv.addObject("m_e_kcal",m_e_kcal);
				return mv;
				
			}else if(wholeDay.equals("lunch")){//점심로드
				m3.put("day", wholeDay);
				String l_e_kcal = this.calService.getE_kcal2(m3);
				mv.addObject("l_e_kcal",l_e_kcal);
				return mv;
				
			}else if(wholeDay.equals("dinner")){//저녁로드
				m3.put("day", wholeDay);
				String d_e_kcal = this.calService.getE_kcal2(m3);
				mv.addObject("d_e_kcal",d_e_kcal);
				return mv;
			}*/
			
		}
			
			List<CalendarBean> calendarList = this.calService.getE_kcal(m3);
			mv.addObject("y",y);
			mv.addObject("m",m);
			mv.addObject("d",d);
			mv.addObject("calendarList", calendarList);
			return mv;
		
	}
	
	//아침_운동_칼로리 저장
	@RequestMapping(value="/m_e_kcal_save.brn")
	public void m_e_kcal_save(
			@RequestParam("kcal") String kcal,
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		int mem_no = (Integer) session.getAttribute("mem_no");
//		System.out.println("아침운동칼로리저장wholeDay="+ wholeDay);
		
		System.err.println("---------------------아침운동칼로리저장wholeDay="+ wholeDay);
		int kcal2 = Integer.parseInt(kcal);
		
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map m2 = new HashMap();
		m2.put("kcal", kcal2);//칼로리
		m2.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m2.put("cal_date", cal_date);//해당 날짜
		m2.put("mem_no", mem_no);//회원번호
		
		this.calService.setE_kcal(m2);
		
		response.sendRedirect("goweekly.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		
	}
	
	//점심_운동_칼로리 저장
	@RequestMapping(value="/l_e_kcal_save.brn")
	public void l_e_kcal_save(
			@RequestParam("kcal") String kcal,
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		int mem_no = (Integer) session.getAttribute("mem_no");
		System.out.println("wholeDay="+ wholeDay);
		int kcal2 = Integer.parseInt(kcal);
		
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map m5 = new HashMap();
		m5.put("kcal", kcal2);//칼로리
		m5.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m5.put("cal_date", cal_date);//해당 날짜
		m5.put("mem_no", mem_no);//회원번호
		
		this.calService.setE_kcal(m5);
		
		response.sendRedirect("goweekly.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		
	}
	
	//저녁_운동_칼로리 저장
	@RequestMapping(value="/d_e_kcal_save.brn")
	public void d_e_kcal_save(
			@RequestParam("kcal") String kcal,
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		int mem_no = (Integer) session.getAttribute("mem_no");
		System.out.println("wholeDay="+ wholeDay);
		int kcal2 = Integer.parseInt(kcal);
		
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map m4 = new HashMap();
		m4.put("kcal", kcal2);//칼로리
		m4.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m4.put("cal_date", cal_date);//해당 날짜
		m4.put("mem_no", mem_no);//회원번호
		
		this.calService.setE_kcal(m4);
		
		response.sendRedirect("goweekly.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		
	}
	/*
	//운동칼로리 가져오기
	@RequestMapping(value="/e_kcal_get.brn")
	public ModelAndView e_kcal_get(
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		int mem_no = (Integer) session.getAttribute("mem_no");
		System.err.println("---------------------운동칼로리가져오기");
		
		String cal_date = y+m+d;
		
			
		Map m3 = new HashMap();
		m3.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		List<CalendarBean> calendarList = this.calService.getE_kcal(m3);
		
		ModelAndView mv = new ModelAndView("html_mypage/forwardStep");
		mv.addObject("y",y);
		mv.addObject("m",m);
		mv.addObject("d",d);
		mv.addObject("calendarList",calendarList);
		return mv;
		
	}
	*/
	
	@RequestMapping(value="/mypage_main_weekly.brn")
	public void week_list(
			@RequestParam("kcal") String kcal,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		String wholeDay = (String)session.getAttribute("wholeDay");
		
		String y = calendarBean.getImsiY();
		String m = calendarBean.getImsiM();
		String d = calendarBean.getImsiD();
		
		System.err.println("---------------------결과칼로리 어디로 가야할지 경로지정");
		if(wholeDay.equals("morning")){
			response.sendRedirect("m_e_kcal_save.brn?kcal="+kcal+"&wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		}else if(wholeDay.equals("lunch")){
			response.sendRedirect("l_e_kcal_save.brn?kcal="+kcal+"&wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		}else if(wholeDay.equals("dinner")){
			response.sendRedirect("d_e_kcal_save.brn?kcal="+kcal+"&wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d);
		}
		
	}
	
	@RequestMapping(value="/cal_exer_start.brn")
	public ModelAndView cal_exer(
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		int mem_no = (Integer)session.getAttribute("mem_no");
		System.err.println("y+m+d----------------- = " + y+m+d);
		session.setAttribute("wholeDay", wholeDay);
		
	/*	//날짜, day, mem_no저장
		Map m = new HashMap();
		m.put("day", wholeDay);
		m.put("cal_date", cal_date);
		m.put("mem_no", mem_no);
		
		this.calService.setDay_ok(m);*/
		
		calendarBean = new CalendarBean();
		calendarBean.setImsiY(y);
		calendarBean.setImsiM(m);
		calendarBean.setImsiD(d);
		
		ModelAndView mv = new ModelAndView("html_mypage/calculator/cal_exer_step1");
		//운동type들 가져오기 
		List<ExerBean> erc_ty = this.exerService.getErc_ty();
		mv.addObject("erc_ty", erc_ty);
		return mv;
		
	}
	
	@RequestMapping(value="/cal_exer_ok.brn")
	public ModelAndView cal_exercise_result(
			@RequestParam("erc_ty") String erc_ty,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		System.err.println("1");
		ModelAndView mv = new ModelAndView("html_mypage/calculator/cal_exer_step2");
		 List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
		
		 mv.addObject("erc_nm",erc_nm);
		 return mv;
	}
	
	@RequestMapping(value="/cal_exer_step2.brn")
	public ModelAndView cal_exer_step2(
			@RequestParam("erc_nm") String erc_nm,
			@RequestParam("gender") String gender,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		System.err.println("2");
		int time = Integer.parseInt(request.getParameter("time"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		
		int erc_sx = 1;
		
		if(gender.equals("1")){
			erc_sx = 1;
		}else if(gender.equals("2")){
			erc_sx = 2;
		}
		
		Map m = new HashMap();
		m.put("erc_nm", erc_nm);
		m.put("time", time);
		m.put("erc_sx", erc_sx);
		m.put("weight", weight);
		
		
		ModelAndView mv = new ModelAndView("html_mypage/calculator/cal_exer_step3");
		int erc_ten = this.exerService.getErc_ten(m);
	    int erc_hun = this.exerService.getErc_hun(m);
		 
		 mv.addObject("erc_ten", erc_ten);
		 mv.addObject("erc_hun", erc_hun);
		 mv.addObject("time",time);
		 return mv;
	}
	
	@RequestMapping(value="/cal_exer_step4.brn")
	public ModelAndView cal_exer_kcalMath(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
	
		System.err.println("3");

		
		int tenkcal = Integer.parseInt(request.getParameter("tenkcal"));
		int hunkcal = Integer.parseInt(request.getParameter("hunkcal"));
		double time = Double.parseDouble(request.getParameter("time"));
		
		System.err.println("tenkcal" + tenkcal);
		System.err.println("hunkcal" + hunkcal);
		
		double onekcal = 0;
		
		ModelAndView mv = new ModelAndView("html_mypage/calculator/cal_exer_step5");
		
		// 1분당 칼로리 = 10칼로리 / erc_ten
		// 10분당 칼로리 = 100칼로리 / erc_hun
		if(time < 10.0){ //내가 입력한 시간이 10분 이하이면 
			onekcal = (10.0 / tenkcal);
		}else if(time >= 10.0){
			onekcal = (100.0 / hunkcal);
		}
		
		double kcal = (onekcal * time);
		
		int kcal2 = (int)kcal;
		mv.addObject("kcal",kcal2);
		return mv;
	}
	
	/*@RequestMapping(value="/calculator_exercise_ok.brn")
	public ModelAndView calculator_exercise_ok(
			@RequestParam("erc_ty") String erc_ty,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		
		ModelAndView mv = new ModelAndView("html_calculator/cal_exercise");
		
		if(erc_ty.equals("걷기/달리기")){
			erc_ty = "걷기,달리기";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}else if(erc_ty.equals("구기운동")){
			erc_ty = "구기운동";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}else if(erc_ty.equals("댄스")){
			erc_ty = "댄스";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}else if(erc_ty.equals("생활체육")){
			erc_ty = "생활체육";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}else if(erc_ty.equals("수중운동")){
			erc_ty = "수중운동";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}else{
			erc_ty = "기타";
			//운동이름 받아오기
			List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
			mv.addObject("erc_nm", erc_nm);
			
		}
		
		return mv;
	}*/
}
