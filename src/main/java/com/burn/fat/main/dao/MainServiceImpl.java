package com.burn.fat.main.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.board.sboard.model.SboardBean;
import com.burn.fat.board.fboard.model.FboardBean;
import com.burn.fat.board.eboard.model.EboardBean;

@Service("mainService")
public class MainServiceImpl{
	
    @Autowired
	private MainDAOImpl mainDAO;

	public List<GbbsBean> getGList() throws Exception {
		return mainDAO.getGList();
	}

	public List<ObsBean> getOList() throws Exception {
		return mainDAO.getOList();
	}

	public List<SboardBean> getSList() throws Exception {
		return mainDAO.getSList();
	}

	public List<EboardBean> getEList() throws Exception {
		return mainDAO.getEList();
	}

	public List<FboardBean> getFList() throws Exception {
		return mainDAO.getFList();
	}
 
 
}
