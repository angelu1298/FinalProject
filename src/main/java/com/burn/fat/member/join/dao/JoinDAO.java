package com.burn.fat.member.join.dao;

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
	
	//회원 정보 수정
	public void updateMember(MemberBean member) {
		
		sqlSession.update("updateMember", member);
	}
	
	//회원 삭제
	public void deleteMember(MemberBean member) {
		
		sqlSession.update("deleteMember", member);
	}
	
	//아이디 중복 체크
	public int checkId(String mem_id){
		int result =-1;
		
		try{
			MemberBean member=(MemberBean) sqlSession.selectOne("checkId", mem_id);
			
			if(member != null){
				result = 1;
			}
		}catch(Exception e){
			
		}
		return result;
	}
	
}
