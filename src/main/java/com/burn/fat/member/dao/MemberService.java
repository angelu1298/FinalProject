package com.burn.fat.member.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.member.model.MemberBean;
public interface MemberService {
	
	/*아이디 검색*/
	public MemberBean findid1(Map m) throws Exception;
	
	/*아이디 검색*/
	public MemberBean findid2(Map m) throws Exception;
	
	
	/* 비번 검색 */
	public MemberBean findpwd(Map m) throws Exception ;


}
