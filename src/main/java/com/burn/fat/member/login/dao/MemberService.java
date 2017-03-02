package com.burn.fat.member.login.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.model.MemberBean;

@Service("member")
public class MemberService {
	
	@Autowired
	private LoginDAO dao;

	public List<MemberBean> getIdList() {
		
		return dao.getIdList();
	}

	public MemberBean getMemberById(String check_id) {
		
		return dao.getMemberById(check_id);
	}
	
	
}
