package com.burn.fat.board.gboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.gboard.model.GbbsBean;

@Service("gboardService")
public class GboardServiceImpl implements GboardService {
	
    @Autowired
	private GbbsDAOImpl gbbsDAO;

	@Override
	public void insertGbbs(GbbsBean gbbsbean) throws Exception {
		gbbsDAO.insertGbbs(gbbsbean);
	}

	@Override
	public int getGlistCount() throws Exception {
		return gbbsDAO.getGlistCount();
	}

	@Override
	public List<GbbsBean> getGbbsGall(Map m) throws Exception {
		return gbbsDAO.getGbbsGall(m);
	}

	@Override
	public GbbsBean getGbbsCont(int num) throws Exception {
		return gbbsDAO.getGbbsCont(num);
	}

	@Override
	public int getGlistCount(Map m) throws Exception {
		return gbbsDAO.getGlistCount(m);
	}
	
	@Override
	public void gbbsHit(int num) throws Exception {
        gbbsDAO.gbbsHit(num);		
	}

	@Override
	public void gbbsLike(int num) throws Exception {
        gbbsDAO.gbbsLike(num);		
	}

	@Override
	public void editGbbs(GbbsBean gbbsbean) throws Exception {
         gbbsDAO.editGbbs(gbbsbean);		
	}

	@Override
	public void deleteGbbs(int gbbs_num) throws Exception {
         gbbsDAO.deleteGbbs(gbbs_num);		
	}

	@Override
	public int getGlistCount2(Map m) throws Exception {
		return gbbsDAO.getGlistCount2(m);
	}

	@Override
	public List<GbbsBean> getGbbsGall2(Map m) throws Exception {
		return gbbsDAO.getGbbsGall2(m);
	} 

	@Override
	public void gbbsReplyOk(GbbsBean gbbsbean) throws Exception {
        gbbsDAO.gbbsReplyOk(gbbsbean);		
	}

	@Override
	public String getGbbsName(int mnum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGbbsAuthorName() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void changeGcommcnt(int gbbs_num) {
		gbbsDAO.chageGcommcnt(gbbs_num);
	}

	@Override
	public GbbsBean getGbbsContpage(int rownum) {
		return gbbsDAO.getGbbsContpage(rownum);
	}
 
}
