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
		int mem_no = ((Integer) session.getAttribute("mem_no")).intValue();
		Map<String,Object> map = new HashMap<String,Object>();
		
		//전체 회원의 한달 간 평점 평균 구하기
		//현재 해당 달의 첫날과 마지막날 값 그리고 현재 접속한 회원번호 맵에 넣는다
		Calendar cal = Calendar.getInstance();
		map.put("monthEarly",cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.getActualMinimum(Calendar.DATE));
		map.put("monthEnd",cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.getActualMaximum(Calendar.DATE));
		map.put("mem_no",mem_no);
		//데이터 베이스에서 회원전체 평점 평균을 가져온다
		List<Double> average = service.getAverage(map);
		Iterator<Double> itr = average.iterator();
		double avgsum = 0;
		
		//가져온 평균을 더한다. 
		while(itr.hasNext()){
			avgsum += itr.next();
		}
		
		//평점 입력한 전체 회원 수를 가져온다.
		int all = service.getMemNum(map);
		double avrgPerM = 0;
		//평균을 구한다.
		avrgPerM=avgsum / all;
		
		//나의 평점의 평균을 가져온다. 
		double myRate=service.getMyAverage(map);
		
		//현재 회원의 한달 섭취 칼로리를 각각 가져온다.
		System.out.println("회원 평균 합 avgsum : "+avgsum + " 전체 회원 수 "+all );
		
		ModelAndView model = new ModelAndView("html_mypage/statistics");
		model.addObject("myRate",myRate);
		model.addObject("avrgPerM",avrgPerM);
		return model;
	}

}
