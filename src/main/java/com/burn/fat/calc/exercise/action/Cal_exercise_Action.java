package com.burn.fat.calc.exercise.action;

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

import com.burn.fat.calc.exercise.dao.ExerciseService;
import com.burn.fat.calc.exercise.model.ExerciseBean;
import com.burn.fat.member.login.dao.MemberService;
import com.burn.fat.member.model.MemberBean;

@Controller
public class Cal_exercise_Action {

	@Autowired
	private ExerciseService exerService;

	@Autowired
	private MemberService memberService;

	/* erc_ty � Ÿ�� �������� */
	@RequestMapping(value = "/bringtype.brn")
	public ModelAndView bringtype(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ExerciseBean> exerciselist = exerService.bringtype();

		ModelAndView mv = new ModelAndView("html_calculator/cal_exer");
		HttpSession session = request.getSession();
		String mem_id= "";
		MemberBean member = null;
		if(session.getAttribute("mem_id") !=null){
			mem_id= (String) session.getAttribute("mem_id");
			member=	memberService.getMemberById(mem_id);
			mv.addObject("member", member);
		}
		mv.addObject("exerciselist", exerciselist);

		return mv;

	}

	// erc_nm ��������
	@RequestMapping(value = "/bringname.brn")
	public ModelAndView bringname(@RequestParam("a") String name, @RequestParam("erc_sx") String erc_sx,
										HttpServletRequest request,
										HttpServletResponse response) throws Exception {



		List<ExerciseBean> exerciselist = exerService.bringname(name);

		ModelAndView mv = new ModelAndView("html_calculator/cal_exer_result_nm");

		mv.addObject("exerciselist", exerciselist);
		mv.addObject("erc_sx",erc_sx);
		return mv;

	}

	//kcal ��ư Ŭ���� �ڷ� 
	@RequestMapping(value = "/bringtime.brn")
		public ModelAndView bringtime(@RequestParam("exe_name") String exe_name, 
					@RequestParam("exe_kcal") int exe_kcal,@RequestParam("erc_sx") String erc_sx,
					@RequestParam("erc_w") String erc_w,
				HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			
			Map m = new HashMap();
			m.put("exe_name", exe_name);
			m.put("erc_sx", erc_sx);
			m.put("erc_w", erc_w);
			
			List<ExerciseBean> exerciselist = exerService.bringtime(m);

			ModelAndView mv = new ModelAndView("html_calculator/cal_exer_result_nm2");
			
			mv.addObject("exerciselist", exerciselist);
			mv.addObject("exe_name", exe_name);
			mv.addObject("exe_kcal", exe_kcal);
			mv.addObject("erc_sx", erc_sx);
			mv.addObject("erc_w", erc_w);
			
			return mv;


		}

}