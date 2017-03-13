package com.burn.fat.member.mypage.myinfo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;
//import com.burn.fat.model.ObsBean;

@Repository
public class MyinfoDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*=================================================*/
	/*���̵� �������� mypage���� ��������*/
	public MyinfoBean getMyCont(int mem_no) throws Exception{
		return (MyinfoBean)sqlSession.selectOne("my_id",mem_no);
		 
	}
	
	/*mem_no�� �������� my_memo ��������*/
	public MyinfoBean mymemo(int mem_no) throws Exception {
		return (MyinfoBean)sqlSession.selectOne("my_memo",mem_no);
	}

	/*mem_no�� �������� my_memo update*/
	public MyinfoBean my_update(MyinfoBean myinfobean) {
		return (MyinfoBean)sqlSession.selectOne("my_update",myinfobean);
	}
	
	/*mem_no�� ��������  goal_w update*/
	public MyinfoBean goal_w_update(MyinfoBean myinfobean) throws Exception{
		return (MyinfoBean)sqlSession.selectOne("my_w_update",myinfobean);
	}
	

}











