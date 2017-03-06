package com.burn.fat.member.join.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.join.dao.JoinDAO;
import com.burn.fat.member.model.MemberBean;

@Service("joinService")
public class JoinService {
	
	@Autowired
	private JoinDAO dao;

	public int joinMember(MemberBean member) {
		return dao.joinMember(member);
	}

	public void insertWannabe(Map<String, String> map) {
		dao.insertWannabe(map);
	}
}
