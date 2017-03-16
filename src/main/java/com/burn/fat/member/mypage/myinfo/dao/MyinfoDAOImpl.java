package com.burn.fat.member.mypage.myinfo.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.model.MemberBean;

@Repository
public class MyinfoDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*처음 값 가져옴*/
    public MemberBean getMyinfo(int mem_no) throws Exception {
    	return sqlSession.selectOne("getmyinfo", mem_no);
    }
    
    /*member 테이블에서 본인의 키, 몸무게 가져오기*/
	public MemberBean getmemberinfo(int mem_no) throws Exception {
		return (MemberBean) sqlSession.selectOne("getmember", mem_no);
	}
    
    /*좌우명  업데이트*/
    public MemberBean memo_update(Map m) throws Exception{
		return sqlSession.selectOne("memo_update",m);
		
	}
	
     /*목표몸무게 업데이트*/
	public MemberBean w_update(Map m) throws Exception{
		return sqlSession.selectOne("w_update",m);
	}
	
}











