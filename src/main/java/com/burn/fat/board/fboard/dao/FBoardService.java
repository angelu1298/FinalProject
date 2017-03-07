package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.board.fboard.model.FbbsBean;

public interface FBoardService {

	/*자료실 저장*/
	public void insertBbs(FbbsBean fbbsbean) throws Exception;

	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception;
	
	/* 자료실 목록과 페이징 */
	public List<FbbsBean> getBbsList(Map m) throws Exception ;


}
