package com.burn.fat.board.sboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.sboard.model.SboardBean;

@Repository
public class SboardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getListCount() {
		int listNum = 0;
		listNum = ((Integer)sqlSession.selectOne("sboardcount")).intValue();
		return listNum;
	}

	public List<SboardBean> getSboardList(Map<String, Integer> m) {
		List<SboardBean> list = sqlSession.selectList("sboardlist",m);
		return list;
	}

	public void insertSboard(SboardBean bean) {
		/*int mem_no = sqlSession.selectOne("getmem_no",bean.getMem_id());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("bean", bean);
		map.put("mem_no", mem_no);*/
		sqlSession.insert("insertSboard",bean);
	}

	public SboardBean getSboardCont(int num) {
		return (SboardBean) sqlSession.selectOne("sboardcont",num);
	}

	public void sboardHit(int num) {
		sqlSession.update("sboardhit",num);
		
	}

	public void editSboard(SboardBean bean) {
		sqlSession.update("sboardedit",bean);
	}

	public int deleteSboard(int s_no) {
		return sqlSession.delete("sboarddelete", s_no);
	}

	public int getListCountSearch(Map<String, Object> m) {
		int num = sqlSession.selectOne("searchcnt", m);
		return num;
	}

	public List<SboardBean> getSboardListSearch(Map<String, Object> m) {
		List<SboardBean> list = sqlSession.selectList("sboardlistsearch",m);
		return list;
	}


	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("likecountup", map);
	}

	public String checkscrap(int s_no) {
		
		return sqlSession.selectOne("checkscrap", s_no);
	}

	public void chageScommcnt(int s_no) {
		sqlSession.update("updatescommcnt",s_no);
	}

	public SboardBean getSboardContPreNext(int rnum) {
		return sqlSession.selectOne("sboardcontPreNext",rnum);
	}

	public int getRowNum(int s_no) {
		return sqlSession.selectOne("getrnum",s_no);
	}

	
	
	
}
