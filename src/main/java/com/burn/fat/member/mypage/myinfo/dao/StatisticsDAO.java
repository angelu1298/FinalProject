package com.burn.fat.member.mypage.myinfo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("StatDAO")
public class StatisticsDAO {
	@Autowired
	SqlSessionTemplate sqlSession;

	public List<Double> getAverage(Map<String, Integer> map) {
		 return sqlSession.selectList("getEachAv",map);
	}

	public int getMemNum(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getMemNum",map);
	}

	public int getGoalNum(int i) {
		return sqlSession.selectOne("getGoalNum",i);
	}

	public int getEntireMemNum(int i) {

		return sqlSession.selectOne("entireMemNum",i);
	}
	
	
}
