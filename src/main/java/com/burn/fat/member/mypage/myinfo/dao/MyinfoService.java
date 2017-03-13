package com.burn.fat.member.mypage.myinfo.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;
public interface MyinfoService {

	/*=============================================================================*/
	/*mem_no�� �������� �ڷ�� ���� ��������*/
	public MyinfoBean getMyCont(int mem_no) throws Exception;
	
	/*mem_no�� �������� my_memo��������*/
	public MyinfoBean mymemo(int mem_no) throws Exception;

	/*mem_no�� �������� my_memo update ��Ű��*/
	public MyinfoBean my_update(MyinfoBean myinfobean) throws Exception;

	/*mem_no�� �������� goal_w update ��Ű��*/
	public MyinfoBean goal_w_update(MyinfoBean myinfobean) throws Exception;
}
