package com.burn.fat.member.mypage.myinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.mypage.myinfo.model.MyinfoBean;
//import com.burn.fat.model.ObsBean;

@Service("MyinfoService")
public class MyinfoServiceImpl implements MyinfoService {
    @Autowired
	private MyinfoDAOImpl myinfoDAO;


	/*****************************************************************8*/
	  /*���������� id�� ������*/
		@Override
	    public MyinfoBean getMyCont(int mem_no) throws Exception {
	    	return myinfoDAO.getMyCont(mem_no);
	    }
		
		/*���������� memo ��������*/
		@Override
		public MyinfoBean mymemo(int mem_no) throws Exception{
			return myinfoDAO.mymemo(mem_no);
		}
		
		/*���������� memo update */
		@Override
		public MyinfoBean my_update(MyinfoBean myinfobean) throws Exception{
			return myinfoDAO.my_update(myinfobean);
		}
		
		public MyinfoBean goal_w_update(MyinfoBean myinfobean) throws Exception{
			return myinfoDAO.goal_w_update(myinfobean);
		}

	
}
