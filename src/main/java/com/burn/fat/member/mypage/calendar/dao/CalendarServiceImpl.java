package com.burn.fat.member.mypage.calendar.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.mypage.calendar.model.CalendarBean;
import com.burn.fat.member.mypage.exercise.model.ExerBean;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
    @Autowired
	private CalendarDAOImpl calendarDAO;

	/* 운동_칼로리 저장 */
	public void setE_kcal(Map m) throws Exception{
		calendarDAO.setE_kcal(m);
	}
	
	/* 식단_칼로리 저장 */
	public void setF_kcal(Map m) throws Exception{
		calendarDAO.setF_kcal(m);
	}
	
	/* 총_칼로리 저장 */
	public void setT_kcal(Map m) throws Exception{
		calendarDAO.setT_kcal(m);
	}
    
	/* 운동_칼로리 가져오기 */
	public List<CalendarBean> getE_kcal(Map m) throws Exception{
		return calendarDAO.getE_kcal(m);
		
	}
	
	/* 운동_칼로리 가져오기2 */
	public List<CalendarBean> getE_kcal2(Map m) throws Exception{
		return calendarDAO.getE_kcal2(m);
	}
	
	/* 평가 값 저장하기 */
	public void setEmo_eval(Map m) throws Exception{
		calendarDAO.setEmo_eval(m);
	}
	
	/* 평가 값 불러오기 */
	public int getEmo_eval(Map m) throws Exception{
		return calendarDAO.getEmo_eval(m);
	}
	
	/* 월간 : 평가 불러오기 */
	public List<CalendarBean> getTotal(int mem_no) throws Exception{
		return calendarDAO.getTotal(mem_no);
	}
   
	/* 월간 : 평가 날짜불러오기 */
	public String getCal_date(Map m) throws Exception{
		return calendarDAO.getCal_date(m);
	}
	
	/* 식단 저장 */
	public void setCuisine(Map m) throws Exception{
		calendarDAO.setCuisine(m);
	}
	
	/* 식단 불러오기 */
	public  List<CalendarBean> getCuisine(Map m) throws Exception{
		return calendarDAO.getCuisine(m);
	}
	
	/* 식품 저장 */
	public void setGrocery(Map m) throws Exception{
		calendarDAO.setGrocery(m);
	}
	
	/* 식품 불러오기 */
	public  List<CalendarBean> getGrocery(Map m) throws Exception{
		return calendarDAO.getGrocery(m);
	}

	@Override
	public void setGroceryIn(Map m2) {
		calendarDAO.setGroceryIn(m2);
	}

	@Override
	public void setCuisineIn(Map m2) {
		calendarDAO.setCuisineIn(m2);
		
	}
}
