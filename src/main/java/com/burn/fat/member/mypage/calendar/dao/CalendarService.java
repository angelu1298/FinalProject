package com.burn.fat.member.mypage.calendar.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.member.mypage.calendar.model.CalendarBean;


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
	public String getE_kcal2(Map m) throws Exception;
		
}
