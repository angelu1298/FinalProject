package com.burn.fat.main.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.board.sboard.model.SboardBean;
import com.burn.fat.board.fboard.model.FboardBean;
import com.burn.fat.board.eboard.model.EboardBean;


@Repository
public class MainDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<GbbsBean> getGList() throws Exception {
		List<GbbsBean> glist = sqlSession.selectList("g_list");
	    return glist;
	}
	public List<ObsBean> getOList() throws Exception {
		List<ObsBean> olist = sqlSession.selectList("o_list");
	    return olist;
	}
	public List<FboardBean> getFList() throws Exception {
		List<FboardBean> flist = sqlSession.selectList("f_list");
	    return flist;
	}
	public List<EboardBean> getEList() throws Exception {
		List<EboardBean> elist = sqlSession.selectList("e_list");
	    return elist;
	}
	public List<SboardBean> getSList() throws Exception {
		List<SboardBean> slist = sqlSession.selectList("s_list");
	    return slist;
	}
	 
}