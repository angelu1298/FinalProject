package com.burn.fat.member.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.member.model.MemberBean;

@Service("adminService")
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	public int getListCount() throws Exception{
		return dao.getListCount();
	}

	public List<MemberBean> getMemList(Map m) throws Exception{
		return dao.getMemList(m);
	}

	public MemberBean getMemCont(int num) throws Exception{
		
		return dao.getMemCont(num);
	}

	public int getListCount_find(Map m) throws Exception {

		return dao.getListCount_find(m);
	}

	public List<MemberBean> getMemList_find(Map m) throws Exception {
		return dao.getMemList_find(m);
	}

	//관리자 회원 삭제
	public void manage_delete(String mem_id) throws Exception{
		dao.manage_delete(mem_id);
		
	}
	//관리자 회원 수정 폼
	public MemberBean isMem(String mem_id) throws Exception{
		return dao.isMem(mem_id);
	}

	//관리자 회원 수정
	public void manage_edit(MemberBean member) {
		dao.manage_edit(member);
		
	}

}
