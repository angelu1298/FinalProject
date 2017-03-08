package com.burn.fat.board.fboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.fboard.model.FboardBean;
public interface FboardService {
	public void insertBbs(FboardBean bbsbean) throws Exception;

	public int getListCount() throws Exception;
	
	public List<FboardBean> getBbsList(Map m) throws Exception ;

	public FboardBean getBbsCont(int num) throws Exception;

	public void bbsHit(int num) throws Exception;

	public void editBbs(FboardBean bbsbean) throws Exception;

	public void deleteBbs(int f_no) throws Exception;

	public int getListCount3(Map m) throws Exception;

	public List<FboardBean> getBbsList3(Map m) throws Exception ;


	public int likeCountUp(Map<String, Object> map) throws Exception;

	public String checkscrap(int f_no) throws Exception ;


	public void changeFcomment(int f_no) throws Exception;

}
