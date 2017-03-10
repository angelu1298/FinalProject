package com.burn.fat.member.join.dao;

import java.util.List;
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
	
	public MemberBean isMember(String mem_id) {
		
		
		return dao.isMember(mem_id);
	}
	
	public void updateMember(MemberBean member) {
		
		dao.updateMember(member);
	}
	
	public void deleteMember(MemberBean member) {
		
		
		dao.deleteMember(member);
	}
	
}
