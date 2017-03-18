package com.burn.fat.member.mypage.exercise.action;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.admin.dao.AdminService;
import com.burn.fat.member.model.MemberBean;
import com.burn.fat.member.mypage.calendar.dao.CalendarService;
import com.burn.fat.member.mypage.calendar.model.CalendarBean;
import com.burn.fat.member.mypage.exercise.dao.ExerService;
import com.burn.fat.member.mypage.exercise.model.ExerBean;

@Controller
public class ExerCalc {
	
	CalendarBean calendarBean;
	ExerBean exerbean;
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private ExerService exerService;
	
	@Autowired
	private CalendarService calService;
	
	//주간달력으로 간다
	@RequestMapping(value="/goweekly.brn")
	public ModelAndView goweekly(
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
			
		int m2 = Integer.parseInt(m);
		int d2 = Integer.parseInt(d);
		int mem_no = (Integer) session.getAttribute("mem_no");
		String cal_date = y+m+d;
			
		Map<String, Object> m3 = new HashMap<String, Object>();
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main_weekly");
			
			List<CalendarBean> calendarList = this.calService.getE_kcal(m3);
			mv.addObject("y",y);
			mv.addObject("m",m2);
			mv.addObject("d",d2);
			mv.addObject("calendarList", calendarList);
			return mv;
			
			
		}
	
	/*--------------------------------------------------------------------------------------------------------*/
	//평가 저장
	@RequestMapping(value="/cal_eval.brn", method=RequestMethod.POST)
	public void cal_eval(
			@RequestParam("emo_eval") int emo_eval,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		PrintWriter out = response.getWriter();
		int mem_no = ((Integer)session.getAttribute("mem_no")).intValue();
		String cal_date = y+m+d;
		System.err.println("d-------------평가emo" + emo_eval);
		
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("emo_eval", emo_eval);
		m1.put("cal_date", cal_date);
		m1.put("y", y);
		m1.put("m", m);
		m1.put("d", d);
		m1.put("mem_no", mem_no);
		int result = 0;
		List<CalendarBean> calbean = this.calService.getEmo_eval(m1);
		
		if(calbean.isEmpty()){
			this.calService.setEmo_eval(m1);
			result=1;
		}else{
		//평가값 저장
			int check =0;
			for(int i = 0; i< calbean.size();i++){
				if(calbean.get(i).getCal_eval() !=0){
					check++;
				}
				
			}
			if(check!=0){
				this.calService.setEmo_eval(m1);
				result=1;
			}
		}
		
		out.print(result);
		
	}
	
	//평가 값 불러오기
		@RequestMapping(value="/getCal_eval.brn")
		public ModelAndView getCal_eval(
		@RequestParam("y") String y,
		@RequestParam("m") String m,
		@RequestParam("d") String d,
		HttpServletRequest request, 
		HttpServletResponse response,
		HttpSession session) throws Exception{
			
		int mem_no = (Integer) session.getAttribute("mem_no");
		String cal_date = y+m+d;
			
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("cal_date", cal_date);
		m1.put("mem_no", mem_no);
			
		//평가값 불러오기
		List<CalendarBean> calbean = this.calService.getEmo_eval(m1);
		List<CalendarBean> calendarList = this.calService.getE_kcal(m1);
		
		int cal_eval = calbean.get(0).getCal_eval();
		ModelAndView mv = new ModelAndView("html_mypage/mypage_main");
		mv.addObject("cal_eval", cal_eval);
		mv.addObject("cal_date", cal_date);
		mv.addObject("calendarList", calendarList);
		
		return mv;
			
	}
		
	
	/********************* 식품부분******************************************/			
	//맨처음 아침 로드 
		@RequestMapping(value="/goweekly_m_gro.brn")
		public ModelAndView goweekly_m_gro(
				@RequestParam("y") String y,
				@RequestParam("m") String m,
				@RequestParam("d") String d,
				@RequestParam("wholeDay") String wholeDay,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession session) throws Exception {
			
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
			
			m3.put("day", wholeDay);

			
			//아침 식품불러오기
			 CalendarBean grocery = this.calService.getGrocery(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("grocery",grocery);
			return mv;
		}
		
		
		//맨처음 점심 로드 
		@RequestMapping(value="/goweekly_l_gro.brn")
		public ModelAndView goweekly_l_gro(
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			@RequestParam("wholeDay") String wholeDay,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
				
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
				
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
				
			m3.put("day", wholeDay);


			//점심 식단불러오기
			 CalendarBean grocery = this.calService.getGrocery(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("grocery",grocery);
				
			return mv;
		}
		
			
		//맨처음 저녁 로드 
		@RequestMapping(value="/goweekly_d_gro.brn")
		public ModelAndView goweekly_d_gro(
				@RequestParam("y") String y,
				@RequestParam("m") String m,
				@RequestParam("d") String d,
				@RequestParam("wholeDay") String wholeDay,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession session) throws Exception {
						
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
						
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
						
			m3.put("day", wholeDay);
			
			//저녁 식단불러오기
			CalendarBean grocery = this.calService.getGrocery(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("grocery",grocery);
				
			return mv;
		}	
	
	/********************* 음식부분******************************************/			
	//맨처음 아침 로드 
		@RequestMapping(value="/goweekly_m_food.brn")
		public ModelAndView goweekly_m_food(
				@RequestParam("y") String y,
				@RequestParam("m") String m,
				@RequestParam("d") String d,
				@RequestParam("wholeDay") String wholeDay,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession session) throws Exception {
			
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
			System.err.println("아침식단cal_date = " + cal_date);
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
			
			m3.put("day", wholeDay);

			
			//아침 식단불러오기
			 CalendarBean cuisine = this.calService.getCuisine(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("cuisine",cuisine);
			return mv;
		}
		
		
		//맨처음 점심 로드 
		@RequestMapping(value="/goweekly_l_food.brn")
		public ModelAndView goweekly_l_food(
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			@RequestParam("wholeDay") String wholeDay,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
			
			System.err.println("점심로드로 오는가");
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
			System.err.println("점심cal_date = " +cal_date);
				
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
				
			m3.put("day", wholeDay);


			//점심 식단불러오기
			 CalendarBean cuisine = this.calService.getCuisine(m3);
			 
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("cuisine",cuisine);
				
			return mv;
		}
		
			
		//맨처음 저녁 로드 
		@RequestMapping(value="/goweekly_d_food.brn")
		public ModelAndView goweekly_d_food(
				@RequestParam("y") String y,
				@RequestParam("m") String m,
				@RequestParam("d") String d,
				@RequestParam("wholeDay") String wholeDay,
				HttpServletRequest request,
				HttpServletResponse response,
				HttpSession session) throws Exception {
						
			int mem_no = (Integer) session.getAttribute("mem_no");
			String cal_date = y+m+d;
						
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
						
			m3.put("day", wholeDay);
			
			//저녁 식단불러오기
			 CalendarBean cuisine = this.calService.getCuisine(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			mv.addObject("cuisine",cuisine);
				
			return mv;
		}	
			
		
		
	/********************* 운동부분 EXERCISE ******************************************/			
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
		
		Map<String, Object> m3 = new HashMap<String, Object>();
		m3.put("cal_date", cal_date);//해당 날짜
		m3.put("mem_no", mem_no);//회원번호
		
		m3.put("day", wholeDay);

		//운동칼로리 불러오기
		CalendarBean calendarBean = this.calService.getE_kcal2(m3);
		
		ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
		
		mv.addObject("calendarBean", calendarBean);
		
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
			
			Map<String, Object> m3 = new HashMap<String, Object>();
			m3.put("cal_date", cal_date);//해당 날짜
			m3.put("mem_no", mem_no);//회원번호
			
			m3.put("day", wholeDay);
			
			CalendarBean calendarBean = this.calService.getE_kcal2(m3);
			
			ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
			
			mv.addObject("calendarBean", calendarBean);
			
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
					
				Map<String, Object> m3 = new HashMap<String, Object>();
				m3.put("cal_date", cal_date);//해당 날짜
				m3.put("mem_no", mem_no);//회원번호
					
				m3.put("day", wholeDay);
				
				
				CalendarBean calendarBean = this.calService.getE_kcal2(m3);
				
				ModelAndView mv = new ModelAndView("html_mypage/exercise_load");
				
				mv.addObject("calendarBean", calendarBean);
				
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
		
		System.err.println("---------------------아침운동칼로리저장wholeDay="+ wholeDay);
		int kcal2 = Integer.parseInt(kcal);
		
		
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("kcal", kcal2);//칼로리
		m2.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m2.put("cal_date", cal_date);//해당 날짜
		m2.put("y", y);//해당 날짜
		m2.put("m", m);//해당 날짜
		m2.put("d", d);//해당 날짜
		m2.put("mem_no", mem_no);//회원번호
		
		String erc_nm = exerbean.getImsiEN();
		m2.put("exer_tt", erc_nm);//운동제목
		
		 CalendarBean ecalExist =this.calService.getE_kcal2(m2);
	      
	      //마이페이지에 식단 저장
	      if(ecalExist!=null){
	    	  if(ecalExist.getE_kcal()!=0)
	    		  this.calService.setE_kcalAdd(m2);
	    	  else
	    		  this.calService.setE_kcalUp(m2);
	      }else
	    	  this.calService.setE_kcal(m2);
		
		
		response.sendRedirect("goweekly.brn?y="+y+"&m="+m+"&d="+d);
		
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
		
		String erc_nm = exerbean.getImsiEN();
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map<String, Object> m5 = new HashMap<String, Object>();
		m5.put("kcal", kcal2);//칼로리
		m5.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m5.put("cal_date", cal_date);//해당 날짜
		m5.put("y", y);//해당 날짜
		m5.put("m", m);//해당 날짜
		m5.put("d", d);//해당 날짜
		m5.put("mem_no", mem_no);//회원번호
		m5.put("exer_tt", erc_nm);//운동제목
		
		CalendarBean ecalExist =this.calService.getE_kcal2(m5);
	      
		//마이페이지에 식단 저장
	      if(ecalExist!=null){
	    	  if(ecalExist.getE_kcal()!=0)
	    		  this.calService.setE_kcalAdd(m5);
	    	  else
	    		  this.calService.setE_kcalUp(m5);
	      }else
	    	  this.calService.setE_kcal(m5);
		
		
		response.sendRedirect("goweekly.brn?y="+y+"&m="+m+"&d="+d);		
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
		
		String erc_nm = exerbean.getImsiEN();
		String cal_date = y+m+d;
		//운동_칼로리_저장
		Map<String, Object> m4 = new HashMap<String, Object>();
		m4.put("kcal", kcal2);//칼로리
		m4.put("day", wholeDay);//아침인지 점심인지 저녁인지 
		m4.put("cal_date", cal_date);//해당 날짜
		m4.put("y", y);//해당 날짜
		m4.put("m", m);//해당 날짜
		m4.put("d", d);//해당 날짜
		m4.put("mem_no", mem_no);//회원번호
		m4.put("exer_tt", erc_nm);//운동제목
		
		CalendarBean ecalExist =this.calService.getE_kcal2(m4);
	      
		//마이페이지에 식단 저장
	      if(ecalExist!=null){
	    	  if(ecalExist.getE_kcal()!=0)
	    		  this.calService.setE_kcalAdd(m4);
	    	  else
	    		  this.calService.setE_kcalUp(m4);
	      }else
	    	  this.calService.setE_kcal(m4);
		
		
		response.sendRedirect("goweekly.brn?y="+y+"&m="+m+"&d="+d);		
	}
	
	//결과칼로리 어디로 가야할지 경로지정
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
	
	//마이페이지 - 운동계산기 시작
	@RequestMapping(value="/cal_exer_start.brn")
	public ModelAndView cal_exer(
			@RequestParam("wholeDay") String wholeDay,
			@RequestParam("y") String y,
			@RequestParam("m") String m,
			@RequestParam("d") String d,
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) throws Exception{
		
		System.err.println("y+m+d2222222222222222222222222222222222222----------------- = " + y+m+d);
		System.err.println("y+m+d뜨는가용ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ = " + y+m+d);
		int mem_no = (Integer)session.getAttribute("mem_no");
		System.err.println("y+m+d----------------- = " + y+m+d);
		session.setAttribute("wholeDay", wholeDay);
		
	/*	//날짜, day, mem_no저장
		Map<String, Object> m = new HashMap<String, Object>();
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
		HttpSession session = request.getSession();
		int mem_no = ((Integer)session.getAttribute("mem_no")).intValue();
		ModelAndView mv = new ModelAndView("html_mypage/calculator/cal_exer_step2");
		 List<ExerBean> erc_nm = this.exerService.getErc_nm(erc_ty);
		MemberBean membean = service.getMemCont(mem_no);
		
		mv.addObject("membean",membean);
		 mv.addObject("erc_nm",erc_nm);
		 return mv;
	}
	
	@RequestMapping(value="/cal_exer_step2.brn")
	public ModelAndView cal_exer_step2(
			@RequestParam("erc_nm") String erc_nm,
			@RequestParam("gender") String gender,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		System.err.println("erc_nm@@@@@@@@@@@@@@@@@@@@@@22 = " +erc_nm);
		exerbean = new ExerBean();
		exerbean.setImsiEN(erc_nm);
		
		
		int time = Integer.parseInt(request.getParameter("time"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		
		int erc_sx = 1;
		
		if(gender.equals("1")){
			erc_sx = 1;
		}else if(gender.equals("2")){
			erc_sx = 2;
		}
		
		Map<String, Object> m = new HashMap<String, Object>();
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
	
}
