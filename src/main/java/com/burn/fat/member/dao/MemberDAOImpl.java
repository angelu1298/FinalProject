package com.burn.fat.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.model.MemberBean;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	
	/*1.아이디 검색*/
	public MemberBean findid1(Map m) throws Exception{
	     return (MemberBean)sqlsession.selectOne("id_find1",m);
		}
	
	/*2.아이디 검색*/
	public MemberBean findid2(Map m) throws Exception{
	     return (MemberBean)sqlsession.selectOne("id_find2",m);
		}



	/* 비번 검색 */
	public MemberBean findpwd(Map m) throws Exception{
	     return (MemberBean)sqlsession.selectOne("pwd_find",m);
		}

	
	
	
}
