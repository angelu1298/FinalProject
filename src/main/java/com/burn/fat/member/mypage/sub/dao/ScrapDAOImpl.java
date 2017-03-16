package com.burn.fat.member.mypage.sub.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;

@Repository
public class ScrapDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/* oboard 스크랩*/
	public List<ObsBean> getObsScrap(int mem_no) throws Exception {
		List<ObsBean> list = sqlSession.selectList("obs_scrap", mem_no);
		return list;
	}
	
	/* oboard 스크랩 갯수*/
	public int getOscrapCount(int mem_no) throws Exception {
		System.err.println("oooooboard");
		int count = ((Integer) sqlSession.selectOne("o_scrap_count",mem_no)).intValue();
		System.out.println(count);
		return count;
	}
	
	

	/* eboard 스크랩*/
	public List<EboardBean> getEbsScrap(int mem_no) throws Exception {
		List<EboardBean> list = sqlSession.selectList("ebs_scrap", mem_no);
		return list;
	}
	
	/* eboard 스크랩 갯수*/
	public int getEscrapCount(int mem_no) throws Exception {
		System.err.println("eeeee");
		int count = ((Integer) sqlSession.selectOne("g_scrap_count",mem_no)).intValue();
		System.out.println(count);
		return count;
	}

	
	/* gboard 스크랩*/
	public List<GbbsBean> getGbsScrap(int mem_no) throws Exception {
		List<GbbsBean> list = sqlSession.selectList("gbs_scrap", mem_no);
		return list;
	}
	
	/* gboard 스크랩 갯수*/
	public int getGscrapCount(int mem_no) throws Exception {
		System.err.println("eeeee");
		int count = ((Integer) sqlSession.selectOne("g_scrap_count",mem_no)).intValue();
		System.out.println(count);
		return count;
	}


}











