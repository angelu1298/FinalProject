package com.burn.fat.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.model.MemberBean;

@Service("memService")
public class MemberServiceImpl implements MemberService {
	
    @Autowired
	private MemberDAOImpl memDAO;


	@Override
	public MemberBean findpwd(Map pm) throws Exception {
		return memDAO.findpwd(pm);
	}
	
	


	@Override
	public MemberBean findid1(Map m) throws Exception {
		return memDAO.findid1(m);
	}
	
	@Override
	public MemberBean findid2(Map m) throws Exception {
		return memDAO.findid2(m);
	}

	
	
}
