package com.burn.fat.member.mypage.calendar.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.mypage.calendar.model.CalendarBean;

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
	public String getE_kcal2(Map m) throws Exception{
		return calendarDAO.getE_kcal2(m);
	}
	
   
}
