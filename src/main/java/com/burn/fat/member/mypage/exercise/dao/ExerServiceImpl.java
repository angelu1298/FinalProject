package com.burn.fat.member.mypage.exercise.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.mypage.exercise.model.ExerBean;

@Service("exerService")
public class ExerServiceImpl implements ExerService{

		@Autowired
		public ExerDAO exerDAO;
		
	/* 운동타입 구하기 */
	public List<ExerBean> getErc_ty() throws Exception{
		return exerDAO.getErc_ty();
	}
	
	//운동 이름 구하기
	public List<ExerBean> getErc_nm(String erc_ty) throws Exception{
		return exerDAO.getErc_nm(erc_ty);
	}
	
/*	//운동 10Kcal당 몇분, 100Kcal당 몇분 구하기
	public List<ExerBean> getErc_kcal(Map m) throws Exception{
		return exerDAO.getErc_kcal(m);
	}*/
	
	//운동 10Kcal당 몇분
	public int getErc_ten(Map m) throws Exception{
		return exerDAO.getErc_ten(m);
	}
		
	//운동 100Kcal당 몇분 구하기
	public int getErc_hun(Map m) throws Exception{
		return exerDAO.getErc_hun(m);
	}
}
