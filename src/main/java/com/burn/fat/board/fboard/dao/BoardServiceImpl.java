package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burn.fat.board.fboard.model.BbsBean;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
	private BbsDAOImpl bbsDAO;

	@Override
	public void insertBbs(BbsBean bbsbean) throws Exception {
		bbsDAO.insertBbs(bbsbean);
	}

	@Override
	public int getListCount() throws Exception {
		return bbsDAO.getListCount();
	}

	@Override
	public List<BbsBean> getBbsList(Map m) throws Exception {
		return bbsDAO.getBbsList(m);
	}

	@Override
	public BbsBean getBbsCont(int num) throws Exception {
		return bbsDAO.getBbsCont(num);
	}

	@Override
	public void bbsHit(int num) throws Exception {
        bbsDAO.bbsHit(num);		
	}

	@Override
	public void editBbs(BbsBean bbsbean) throws Exception {
         bbsDAO.editBbs(bbsbean);		
	}

	@Override
	public void deleteBbs(int f_no) throws Exception {
         bbsDAO.deleteBbs(f_no);		
	}

	@Override
	public int getListCount3(Map m) throws Exception {
		return bbsDAO.getListCount3(m);
	}

	@Override
	public List<BbsBean> getBbsList3(Map m) throws Exception {
		return bbsDAO.getBbsList3(m);
	}

	//ÃßÃµ
	public int likeCountUp(Map<String, Object> map) {
		return bbsDAO.likeCountUp(map);
	}

	//½ºÅ©·¦
	public String checkscrap(int f_no) {
		return bbsDAO.checkscrap(f_no);
	}


	//ÄÚ¸àÆ®
	public void changeFcomment(int f_no) {
		bbsDAO.changeFcomment(f_no);
	}

	
}
