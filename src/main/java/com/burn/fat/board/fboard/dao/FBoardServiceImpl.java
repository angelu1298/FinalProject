package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.fboard.model.FbbsBean;

@Service("fboardService")
public class FBoardServiceImpl implements FBoardService {

	@Autowired
	private FbbsDAO fbbsDAO;

	@Override
	public void insertBbs(FbbsBean fbbsbean) throws Exception {
		fbbsDAO.insertBbs(fbbsbean);
	}

	@Override
	public int getListCount() throws Exception {
		return fbbsDAO.getListCount();
	}

	@Override
	public List<FbbsBean> getBbsList(Map m) throws Exception {
		return fbbsDAO.getBbsList(m);
	}

	
}
