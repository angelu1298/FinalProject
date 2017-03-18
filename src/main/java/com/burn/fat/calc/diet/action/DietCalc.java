package com.burn.fat.calc.diet.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.calc.diet.model.DietCalcBean;

@Controller("diet")
public class DietCalc {
	DietCalcBean dietbean;
	
	@RequestMapping(value="/cal_diet.brn")
	public String start(){
		return "html_calculator/cal_diet";
				
	}
	
	@RequestMapping(value="/cal_diet_result.brn")
	public ModelAndView dietResult(){
		
		dietbean.getReduceKg();
		dietbean.getStandard();
		dietbean.getDailyKcal();
		dietbean.getReduceKcal();
		dietbean.getReduceExec();
		dietbean.getReduceMeal();
		dietbean.getMealKcal();
		dietbean.getExec_percent();
		dietbean.getMeal_percent();
		dietbean.getGoalDay();
		
		ModelAndView model=new ModelAndView("html_calculator/cal_diet_result");
		model.addObject("dietbean",dietbean);
		
		return model;
		
	}

	
	@RequestMapping(value="/calculator_diet_ok.brn", method=RequestMethod.POST)
	public void calculator( 
			@RequestParam("now_weight") int now_weight,
			@RequestParam("goal_weight") int goal_weight,
			//목표날짜
			@RequestParam("goalDay") int goalDay,
			@RequestParam("select_per") int select_per,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		/* (ex)
		하루 활동량 35, 신장 175cm 남성
		표준 체중 : (175 - 100) X 0.9 = 67.5kg
		하루 섭취 권장 칼로리 : 67.5 X 35 = 2,362kcal */

		//감량해야할 kg
		double reduceKg = now_weight - goal_weight;
		
		//표준 체중
		double standard = (now_weight - 100) * 0.9;
		
		//하루 섭취 권장 칼로리(일일 필요 칼로리)
		double dailyKcal  = standard * 35;
		
		//감량해야할 kcal = (7700 / 목표날짜) * 감량해야할 kg
		double reduceKcal = Math.round((7700 / goalDay) * reduceKg);
		
		//select_per
		
		//운동% : result[0] , 식사% : result[1]
		double[] percent = choose_per(select_per);
		
		//운동으로 빼야할 칼로리 = 감량해야할 kcal * 운동%
		double reduceExec = reduceKcal * percent[0];
		
		//식사로 빼야할 칼로리 = 감량해야할 kcal * 식사%
		double reduceMeal = reduceKcal * percent[1];
		
		//하루 섭취 식사 칼로리 
		double mealKcal = dailyKcal - reduceMeal;
		
		int exec_percent = (int)(percent[0] * 100);
		int meal_percent = (int)(percent[1] * 100);
		
		dietbean = new DietCalcBean();
		dietbean.setReduceKg(reduceKg);
		dietbean.setStandard(standard);
		dietbean.setDailyKcal(dailyKcal);
		dietbean.setReduceKcal(reduceKcal);
		dietbean.setReduceExec(reduceExec);
		dietbean.setReduceMeal(reduceMeal);
		dietbean.setMealKcal(mealKcal);
		dietbean.setExec_percent(exec_percent);
		dietbean.setMeal_percent(meal_percent);
		dietbean.setGoalDay(goalDay);
		
		
	}
	
		//운동 % , 식사 %
		public double[] choose_per(int select_per){
			
			double exec = 0;
			double meal = 0;
			
			if(select_per == 100){
				exec = 1;
				meal = 0;
			}else if(select_per == 80){
				exec = 0.8;
				meal = 0.2;
			}else if(select_per == 60){
				exec = 0.6;
				meal = 0.4;
			}else if(select_per == 50){
				exec = 0.5;
				meal = 0.5;
			}else if(select_per == 40){
				exec = 0.4;
				meal = 0.6;
			}else{ //운동 20%, 식사 80%
				exec = 0.2;
				meal = 0.8;
			}
			
			double[] percent = {exec, meal};
			return percent;
		}
		
}
