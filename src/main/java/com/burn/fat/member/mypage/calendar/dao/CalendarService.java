package com.burn.fat.member.mypage.calendar.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.member.mypage.calendar.model.CalendarBean;
import com.burn.fat.member.mypage.exercise.model.ExerBean;


public interface CalendarService {

	/* 운동_칼로리 저장 */
	public void setE_kcal(Map m) throws Exception;
	
	/* 식단_칼로리 저장 */
	public void setF_kcal(Map m) throws Exception;
	
	/* 총_칼로리 저장 */
	public void setT_kcal(Map m) throws Exception;
	
	/* 운동_칼로리 가져오기 */
	public List<CalendarBean> getE_kcal(Map m) throws Exception;
	
	/* 운동_칼로리 가져오기2 */
	public List<CalendarBean> getE_kcal2(Map m) throws Exception;
	
	/* 평가 값 저장하기 */
	public void setEmo_eval(Map m) throws Exception;
		
	/* 평가 값 불러오기 */
	public int getEmo_eval(Map m) throws Exception;
	
	/* 월간 : 평가 불러오기 */
	public List<CalendarBean> getTotal(int mem_no) throws Exception;
	
	/* 월간 : 평가 날짜불러오기 */
	public String getCal_date(Map m) throws Exception;
	
	/* 식단 저장 */
	public void setCuisine(Map m) throws Exception;
	
	/* 식단 불러오기 */
	public  List<CalendarBean> getCuisine(Map m) throws Exception;
	
	/* 식품 저장 */
	public void setGrocery(Map m) throws Exception;
	
	/* 식품 불러오기 */
	public  List<CalendarBean> getGrocery(Map m) throws Exception;

	public void setGroceryIn(Map m2);

	public void setCuisineIn(Map m2);
	
}
