package com.burn.fat.park.dao;

import com.burn.fat.park.model.ParkBean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("parkService")
public class ParkServiceImpl {
	
    @Autowired
	private ParkDAOImpl ParkDAO;

	public int getParklistCount() throws Exception {
		return ParkDAO.getParklistCount();
	}

	public List<ParkBean> getParkList(Map m) throws Exception {
		return ParkDAO.getParkList(m);
	}

	public int getParkfindlistCount(Map mf) throws Exception {
		return ParkDAO.getParkfindlistCount(mf);
	}
	public String getParkAddr(int mem_no) throws Exception {
		return ParkDAO.getParkAddr(mem_no);
	}
	
}
