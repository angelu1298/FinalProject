package com.burn.fat.board.gboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.gboard.model.GcommBean;

@Service("gcommService")
public class GcommService {
	
	@Autowired
	private GcommDAO gcommdao;
	
	public int insertComm(GcommBean commbean) {
		return gcommdao.insertComm(commbean);
	}

	public List<GcommBean> getCommList(int gbbs_num) {
		return gcommdao.getCommList(gbbs_num);
	}
	public int deleteComm(Map<String, Integer> map) {
		return gcommdao.deleteComm(map);
	}

	public GcommBean getComment(GcommBean bean) {
		return gcommdao.getComment(bean);
	}

	public void insertCommRep(GcommBean commbean) {
		gcommdao.insertCommRep(commbean);
	}

	public Integer getCommCnt(int gbbs_num) {
		return gcommdao.getCommCnt(gbbs_num);
	}

	public GcommBean getComment(Map<String, Integer> map) {
		return gcommdao.getComment(map);
	}

	public List<GcommBean> getCommentRef(GcommBean bean) {
		return gcommdao.getCommentRef(bean);
	}

	public int deleteCommExistRep(Map<String, Integer> map) {
		return gcommdao.deleteCommExistRep(map);
	}
 


}
