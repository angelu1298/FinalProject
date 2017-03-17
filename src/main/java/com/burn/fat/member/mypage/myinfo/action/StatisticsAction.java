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

import com.burn.fat.member.mypage.myinfo.dao.StatisticsService;

@Controller("StatisticsController")
public class StatisticsAction {
	
	@Autowired
	StatisticsService service;
	
	@RequestMapping("/Statistics.brn")
	public ModelAndView stat(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		Map<String,Integer> map = new HashMap<String,Integer>();
		Calendar cal = Calendar.getInstance();
		map.put("monthEarly",cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.getActualMinimum(Calendar.DATE));
		map.put("monthEnd",cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.getActualMaximum(Calendar.DATE));
		List<Double> average = service.getAverage(map);
		Iterator<Double> itr = average.iterator();
		double avgsum = 0;
		System.out.println(itr.next());
		while(itr.hasNext()){
			avgsum += itr.next();
		}
		int all = service.getMemNum(map);
		double avrgPerM = 0;
		avrgPerM=avgsum / all;
		ModelAndView model = new ModelAndView("html_mypage/statistics");
		model.addObject("avrgPerM",avrgPerM);
		return model;
	}

}
