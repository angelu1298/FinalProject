package com.burn.fat.member.mypage.sub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.fboard.model.FboardBean;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.board.sboard.model.SboardBean;

@Service("Scrapervice")
public class ScrapServiceImpl implements ScrapService {
    @Autowired
	private ScrapDAOImpl scrapDAO;

    /*oboard 스크랩 목록 보여주기*/
	    @Override
	    public List<ObsBean> getObsScrap(int mem_no) throws Exception {
	    	
	    	return scrapDAO.getObsScrap(mem_no);
	    }
    /*oboard 스크랩 갯수*/
	    @Override
	    public int getOscrapCount(int mem_no) throws Exception {
	    	return scrapDAO.getOscrapCount(mem_no);
	    }
    
    
    /*eboard 스크랩 목록 보여주기*/
	    @Override
	    public List<EboardBean> getEbsScrap(int mem_no) throws Exception {
	    	
	    	return scrapDAO.getEbsScrap(mem_no);
	    }
    /*eboard 스크랩 갯수*/
    @Override
	    public int getEscrapCount(int mem_no) throws Exception {
	    	return scrapDAO.getEscrapCount(mem_no);
	    }
    
	
	 /*gboard 스크랩 목록 보여주기*/
    @Override
    public List<GbbsBean> getGbsScrap(int mem_no) throws Exception {
    	
    	return scrapDAO.getGbsScrap(mem_no);
    }
	/*gboard 스크랩 갯수*/
	@Override
	    public int getGscrapCount(int mem_no) throws Exception {
	    	return scrapDAO.getGscrapCount(mem_no);
	    }
	
	
	 /*fboard 스크랩 목록 보여주기*/
    @Override
    public List<FboardBean> getFbsScrap(int mem_no) throws Exception {
    	
    	return scrapDAO.getFbsScrap(mem_no);
    }
	/*fboard 스크랩 갯수*/
	@Override
	    public int getFscrapCount(int mem_no) throws Exception {
	    	return scrapDAO.getFscrapCount(mem_no);
	    }
	   
	
	
	 /*sboard 스크랩 목록 보여주기*/
    @Override
    public List<SboardBean> getSbsScrap(int mem_no) throws Exception {
    	
    	return scrapDAO.getSbsScrap(mem_no);
    }
	/*fboard 스크랩 갯수*/
	@Override
	    public int getSscrapCount(int mem_no) throws Exception {
	    	return scrapDAO.getSscrapCount(mem_no);
	    }
   
	
}
