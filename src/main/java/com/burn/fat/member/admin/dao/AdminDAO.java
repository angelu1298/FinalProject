package com.burn.fat.member.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.member.model.MemberBean;


@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	//관리자 회원 수 
	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("memCount")).intValue();
		return count;
	}
	
	//관리자 회원 리스트
	public List<MemberBean> getMemList(Map m) throws Exception {//page,limit
		List<MemberBean>  list = sqlSession.selectList("getMemList",m);
	    return list;
	}
	//관리자 회원 뷰
	public MemberBean getMemCont(int num) throws Exception{
		
		return (MemberBean)sqlSession.selectOne("getMemCont",num);
	}
	//관리자 회원 검색 수
	public int getListCount_find(Map m) throws Exception {
		int count=0;
		count=((Integer)sqlSession.selectOne("getListCountfind",m)).intValue();
		return count;
	}
	//관리자 회원 검색
	public List<MemberBean> getMemList_find(Map m) throws Exception{
		List<MemberBean> list =sqlSession.selectList("getMemListfind",m);
		
		return list;
	}

	//관리자 회원 삭제
	public void manage_delete(String mem_id) throws Exception {
		sqlSession.update("manage_delete",mem_id);
	}

	//관리자 회원 수정 폼
	public MemberBean isMem(String mem_id) {
		return sqlSession.selectOne("isMem",mem_id);
	}

	//관리자 회원 수정 
	public void manage_edit(MemberBean member) {
		sqlSession.update("manage_edit",member);
		
	}
	

}
