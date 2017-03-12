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
	
	public MemberBean isMember(String mem_id) {
		
		
		return dao.isMember(mem_id);
	}
	
	//회원 정보 수정
	public void updateMember(MemberBean member) {
		
		dao.updateMember(member);
	}
	
	//회원 삭제
	public void deleteMember(MemberBean member) {
		
		
		dao.deleteMember(member);
	}
	
	//아이디 중복 체크
	public int checkId(String mem_id) throws Exception{
		return dao.checkId(mem_id);
	}
}
