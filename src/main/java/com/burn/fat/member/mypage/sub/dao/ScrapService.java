package com.burn.fat.member.mypage.sub.dao;

import java.util.List;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.fboard.model.FboardBean;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.board.sboard.model.SboardBean;

public interface ScrapService {

	/*oboard */
		public List<ObsBean> getObsScrap(int mem_no) throws Exception ;

		public int getOscrapCount(int mem_no) throws Exception ;
		
	/*eboard */
		public List<EboardBean> getEbsScrap(int mem_no) throws Exception ;

		public int getEscrapCount(int mem_no) throws Exception ;
		
	/*gboard */
		public List<GbbsBean> getGbsScrap(int mem_no) throws Exception ;

		public int getGscrapCount(int mem_no) throws Exception ;
		
	/*fboard */
		public List<FboardBean> getFbsScrap(int mem_no) throws Exception ;

		public int getFscrapCount(int mem_no) throws Exception ;
		
	/*sboard */
		public List<SboardBean> getSbsScrap(int mem_no) throws Exception ;

		public int getSscrapCount(int mem_no) throws Exception ;
}
