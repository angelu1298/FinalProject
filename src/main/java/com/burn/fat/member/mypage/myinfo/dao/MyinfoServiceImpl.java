package com.burn.fat.member.mypage.myinfo.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.model.MemberBean;

@Service("MyinfoService")
public class MyinfoServiceImpl implements MyinfoService {
    @Autowired
	private MyinfoDAOImpl myinfoDAO;

	@Override
    public MemberBean getMyinfo(int mem_no) throws Exception {
    	
    	return myinfoDAO.getMyinfo(mem_no);
    }
	
	/*member테이블에서 본인의 키 몸무게 가져오기*/
	public MemberBean getmemberinfo(int mem_no) throws Exception {
		return myinfoDAO.getmemberinfo(mem_no);
	}
	
		
	/*my_memo좌우명 update*/
	@Override
	public MemberBean memo_update(Map m) throws Exception{
		return myinfoDAO.memo_update(m);
		
	}
		
		
	/*my_memo좌우명 update*/
	@Override
	public MemberBean w_update(Map m) throws Exception{
		return myinfoDAO.w_update(m);
	}
		

	
}
