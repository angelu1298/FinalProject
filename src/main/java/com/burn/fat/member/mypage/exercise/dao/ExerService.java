package com.burn.fat.member.mypage.exercise.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.member.mypage.exercise.model.ExerBean;

public interface ExerService {

	/* 운동타입 구하기 */
	public List<ExerBean> getErc_ty() throws Exception;
	
	//운동 이름 구하기
	public List<ExerBean> getErc_nm(String erc_ty) throws Exception;
	
/*	//운동 10Kcal당 몇분, 100Kcal당 몇분 구하기
	public List<ExerBean> getErc_kcal(Map m) throws Exception;*/
	
	//운동 10Kcal당 몇분
	public int getErc_ten(Map m) throws Exception;
		
	//운동 100Kcal당 몇분 구하기
	public int getErc_hun(Map m) throws Exception;
		
		
}
