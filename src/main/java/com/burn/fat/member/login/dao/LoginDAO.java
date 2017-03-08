package com.burn.fat.member.login.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.model.MemberBean;

@Repository
public class LoginDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<MemberBean> getIdList() {
		return sqlSession.selectList("idlist");
	}

	public MemberBean getMemberById(String check_id) {
		return sqlSession.selectOne("logincheck",check_id);
	}
}
