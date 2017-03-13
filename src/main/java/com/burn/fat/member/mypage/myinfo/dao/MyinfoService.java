package com.burn.fat.member.mypage.myinfo.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;
public interface MyinfoService {

	/*=============================================================================*/
	/*mem_no를 기준으로 자료실 내용 가져오기*/
	public MyinfoBean getMyCont(int mem_no) throws Exception;
	
	/*mem_no를 기준으로 my_memo가져오기*/
	public MyinfoBean mymemo(int mem_no) throws Exception;

	/*mem_no를 기준으로 my_memo update 시키기*/
	public MyinfoBean my_update(MyinfoBean myinfobean) throws Exception;

	/*mem_no를 기준으로 goal_w update 시키기*/
	public MyinfoBean goal_w_update(MyinfoBean myinfobean) throws Exception;
}
