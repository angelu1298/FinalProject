package com.burn.fat.calc.exercise.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.calc.exercise.dao.ExerciseService;
import com.burn.fat.calc.exercise.model.ExerciseBean;

@Controller
public class Cal_exercise_Action {

	@Autowired
	private ExerciseService exerService;

	/* erc_ty � Ÿ�� �������� */
	@RequestMapping(value = "/bringtype.brn")
	public ModelAndView bringtype(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ExerciseBean> exerciselist = exerService.bringtype();

		ModelAndView mv = new ModelAndView("html_calculator/cal_exer");

		mv.addObject("exerciselist", exerciselist);

		return mv;

	}

	// erc_nm ��������
	@RequestMapping(value = "/bringname.brn")
	public ModelAndView bringname(@RequestParam("a") String name, @RequestParam("erc_sx") String erc_sx,
										HttpServletRequest request,
										HttpServletResponse response) throws Exception {


		System.out.println(name);

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

			System.out.println(exe_name);
			System.out.println(exe_kcal);
			System.out.println(erc_sx);
			
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
			
			System.out.println(exe_name);
			System.out.println(exe_kcal);
			System.out.println(erc_sx);
			System.out.println(erc_w);
			
			return mv;


		}

}