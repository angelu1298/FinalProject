package com.burn.fat.member.mypage.myinfo.dao;
import java.util.Map;

import com.burn.fat.member.model.MemberBean;
public interface MyinfoService {

	/*처음 가져옴*/
	public MemberBean getMyinfo(int mem_no) throws Exception ;
	
	
	/*my_memo좌우명 update*/
	public MemberBean memo_update(Map m) throws Exception;
	
	/*goal_w목표몸무게 update*/
	public MemberBean w_update(Map m) throws Exception;
	
	/*member테이블에서 본인의 키 몸무게 가져오기*/
	public MemberBean getmemberinfo(int mem_no) throws Exception;
   
}
