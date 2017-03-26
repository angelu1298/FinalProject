package com.burn.fat.board.oboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.oboard.model.OcommBean;

@Service("ocommservice")
public class OcommService {
	
	@Autowired
	private OcommDAO odao;
	
	public int insertOComm(OcommBean commbean) {
		return odao.insertOComm(commbean);
	}

	public List<OcommBean> getOCommList(int o_no) {
		return odao.getOCommList(o_no);
	}

	public int deleteOComm(Map<String, Integer> map) {
		return odao.deleteOComm(map);
	}

	public OcommBean getOComment(OcommBean commbean) {
		return odao.getOComment(commbean);
	}

	public void insertOCommRep(OcommBean commbean) {
		odao.insertOCommRep(commbean);
		
	}


	public Integer getOCommCnt(int o_no) {
		
		return odao.getOCommCnt(o_no);
	}

	public OcommBean getOComment(Map<String, Integer> map) {
		return odao.getOComment(map);
	}

	public List<OcommBean> getOCommentRef(OcommBean commbean) {
		
		return odao.getOCommentRef(commbean);
	}

	public int deleteOCommExistRep(Map<String, Integer> map) {
		return odao.deleteOCommExistRep(map);
	}

}
