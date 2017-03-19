package com.burn.fat.board.oboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.oboard.model.ObsBean;

@Service("OboardService")
public class OBoardServiceImpl implements OBoardService {
    @Autowired
	private ObsDAOImpl obsDAO;

	@Override
	public int insertObs(ObsBean obsbean) throws Exception {
		return obsDAO.insertObs(obsbean);
	}
	
	@Override
	public void deleteObs_comm(ObsBean obsbean) throws Exception {
		obsDAO.deleteObs_comm(obsbean);
	}
	
	@Override
	public String checkscrap(int o_no) {
		return obsDAO.checkscrap(o_no);
	}

	
	@Override
	public void insertObs_comm(ObsBean obsbean) throws Exception {
		obsDAO.insertObs_comm(obsbean);
	}

	@Override
	public int getOlistCount() throws Exception {
		return obsDAO.getOlistCount();
	}
	
	@Override
	public List<ObsBean> getOclistCount(int num) throws Exception {
		return obsDAO.getOclistCount(num);
	}

	@Override
	public List<ObsBean> getObsList(Map<String, Integer> m) throws Exception {
		List<ObsBean> list = obsDAO.getObsList(m);
		return list;
	}

	@Override
	public ObsBean getObsCont(int num) throws Exception {
		return obsDAO.getObsCont(num);
	}
	
	
	@Override
	public int likeCountUp(Map<String, Object> map) {
		return obsDAO.likeCountUp(map);
	}
	

	@Override
	public void obsHit(int num) throws Exception {
        obsDAO.obsHit(num);		
	}

	@Override
	public void editObs(ObsBean obsbean) throws Exception {
         obsDAO.editObs(obsbean);		
	}

	@Override
	public void deleteObs(int obs_num) throws Exception {
         obsDAO.deleteObs(obs_num);		
	}

	@Override
	public int getOListCount3(Map m) throws Exception {
		return obsDAO.getOListCount3(m);
	}

	@Override
	public List<ObsBean> getObsList3(Map m) throws Exception {
		return obsDAO.getObsList3(m);
	}

	
}
