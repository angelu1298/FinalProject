package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import com.burn.fat.board.fboard.model.FbbsBean;

public interface FBoardService {

	/*�ڷ�� ����*/
	public void insertBbs(FbbsBean fbbsbean) throws Exception;

	/* �ڷ�� �� �Խù� ��*/
	public int getListCount() throws Exception;
	
	/* �ڷ�� ��ϰ� ����¡ */
	public List<FbbsBean> getBbsList(Map m) throws Exception ;


}
