package com.burn.fat.member.join.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.model.MemberBean;

@Repository
public class JoinDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int joinMember(MemberBean member) {
		return sqlSession.insert("memberinsert",member);
	}

	public void insertWannabe(Map<String, String> map) {
		sqlSession.update("wannabeinsert", map);
	}
	
	public MemberBean isMember(String mem_id) {
		
		return sqlSession.selectOne("isMember", mem_id);
	}
	
	public void updateMember(MemberBean member) {
		
		sqlSession.update("updateMember", member);
	}
	
	public void deleteMember(MemberBean member) {
		
		sqlSession.update("deleteMember", member);
	}
	
}
