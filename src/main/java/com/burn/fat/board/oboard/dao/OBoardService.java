package com.burn.fat.board.oboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.oboard.model.ObsBean;

public interface OBoardService {
	/*자료실 저장*/
	public void insertObs(ObsBean obsbean) throws Exception;

	/* 자료실 총 게시물 수*/
	public int getOlistCount() throws Exception;
	
	/* 자료실 목록과 페이징 */
	public List<ObsBean> getObsList(Map<String, Integer> m) throws Exception ;

	/*번호를 기준으로 자료실 내용 가져오기 */
	public ObsBean getObsCont(int num) throws Exception;

	/*내용보기 할때만 조회수 증가*/
	public void obsHit(int num) throws Exception;

	
	/*자료실 수정*/
	public void editObs(ObsBean obsbean) throws Exception;

	/*자료실 삭제*/
	public void deleteObs(int obs_num) throws Exception;

	/*검색 결과 게시물 수*/
	public int getOListCount3(Map m) throws Exception;

	/*검색 결과 페이징 목록*/
	public List<ObsBean> getObsList3(Map m) throws Exception ;

	//코멘트 등록
	public void insertObs_comm(ObsBean obsbean) throws Exception;	
	
	/*코멘트 리스트*/
	public List<ObsBean> getOclistCount(int num) throws Exception;

	/*코멘트 삭제*/
	public void deleteObs_comm(ObsBean obsbean) throws Exception;
	
	/*스크랩*/
	public String checkscrap(int o_no) throws Exception;

	/*스크랩 눌렀을 시 추천수 증가와 추가*/
	public int likeCountUp(Map<String, Object> map) throws Exception;
	
	/*=============================================================================*/
	
}
