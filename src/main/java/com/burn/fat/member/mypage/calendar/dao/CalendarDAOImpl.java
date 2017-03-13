package com.burn.fat.member.mypage.calendar.dao;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.mypage.calendar.model.CalendarBean;



@Repository
public class CalendarDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/* 운동_칼로리 저장 */
	public void setE_kcal(Map m) throws Exception{
		sqlSession.insert("setE_kcal",m);
	}
	
	/* 식단_칼로리 저장 */
	public void setF_kcal(Map m) throws Exception{
		sqlSession.insert("setF_kcal",m);
	}
	
	/* 총_칼로리 저장 */
	public void setT_kcal(Map m) throws Exception{
		sqlSession.insert("setT_kcal",m);
	}
	
	/* 운동_칼로리 가져오기 */
	public List<CalendarBean> getE_kcal(Map m) throws Exception{
		return sqlSession.selectList("getE_kcal", m);
	}
	
	/* 운동_칼로리 가져오기2 */
	public String getE_kcal2(Map m) throws Exception{
		return sqlSession.selectOne("getE_kcal2", m);
	}
	
	
	/* 날짜, day, mem_no 저장 
	public void setDay_ok(Map m) throws Exception{
		sqlSession.insert("setDay_ok", m);
	}*/
}











