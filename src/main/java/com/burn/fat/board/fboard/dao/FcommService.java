package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.fboard.model.FcommBean;


@Service("fcomService")
public class FcommService {

	
	@Autowired
	private FcommDAOImpl dao;
	
	public int createCmt(FcommBean bean) throws Exception{
		return dao.createCmt(bean);
	}

	public List<FcommBean> getListCmt(int f_no) throws Exception {
		return dao.getListCmt(f_no);
	}

	public int deleteCmt(Map m) throws Exception {
		return dao.deleteCmt(m);
	}

	
	///
	public FcommBean getComment(FcommBean bean) {
		return dao.getComment(bean);
	}

	public void insertCommRep(FcommBean bean) {
		dao.insertCommRep(bean);
		
	}

	public int deleteCommExistRep(Map m) {
		return dao.deleteCommExistRep(m);
	}
	public List<FcommBean> getCommentRef(FcommBean bean) {
		
		return dao.getCommentRef(bean);
	}


	
}
