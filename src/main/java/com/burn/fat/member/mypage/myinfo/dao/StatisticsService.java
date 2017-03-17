package com.burn.fat.member.mypage.myinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statService")
public class StatisticsService {

	@Autowired
	StatisticsDAO dao;
	
	public List<Double> getAverage(Map<String,Object> map) {
		
		return dao.getAverage(map);
		
	}

	public int getMemNum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getMemNum(map);
	}

	public int getGoalNum(int i) {
	
		return dao.getGoalNum(i);
	}

	public int getEntireMemNum(int i) {
		return dao.getEntireMemNum(i);
	}

	public double getMyAverage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getMyAverage(map);
	}

}
