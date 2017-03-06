package com.burn.fat.board.sboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.sboard.model.ScommBean;

@Service("scommService")
public class ScommService {
	
	@Autowired
	private ScommDAO dao;
	
	public int insertComm(ScommBean commbean) {
		return dao.insertComm(commbean);
	}

	public List<ScommBean> getCommList(int s_no) {
		return dao.getCommList(s_no);
	}

	public int deleteComm(Map<String, Integer> map) {
		return dao.deleteComm(map);
	}

	public ScommBean getComment(ScommBean bean) {
		return dao.getComment(bean);
	}

	public void insertCommRep(ScommBean commbean) {
		dao.insertCommRep(commbean);
		
	}


	public Integer getCommCnt(int s_no) {
		
		return dao.getCommCnt(s_no);
	}

	public ScommBean getComment(Map<String, Integer> map) {
		return dao.getComment(map);
	}

	public List<ScommBean> getCommentRef(ScommBean bean) {
		
		return dao.getCommentRef(bean);
	}

	public int deleteCommExistRep(Map<String, Integer> map) {
		return dao.deleteCommExistRep(map);
	}

}
