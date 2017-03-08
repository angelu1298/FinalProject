package com.burn.fat.board.fboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.fboard.model.BbsBean;
public interface BoardService {
	/*자료실 저장*/
	public void insertBbs(BbsBean bbsbean) throws Exception;

	/* 자료실 총 게시물 수*/
	public int getListCount() throws Exception;
	
	/* 자료실 목록과 페이징 */
	public List<BbsBean> getBbsList(Map m) throws Exception ;

	/*번호를 기준으로 자료실 내용 가져오기 */
	public BbsBean getBbsCont(int num) throws Exception;

	/*내용보기 할때만 조회수 증가*/
	public void bbsHit(int num) throws Exception;

	/*자료실 수정*/
	public void editBbs(BbsBean bbsbean) throws Exception;

	/*자료실 삭제*/
	public void deleteBbs(int f_no) throws Exception;

	/*검색 결과 게시물 수*/
	public int getListCount3(Map m) throws Exception;

	/*검색 결과 페이징 목록*/
	public List<BbsBean> getBbsList3(Map m) throws Exception ;


	/*추천*/
	public int likeCountUp(Map<String, Object> map) throws Exception;

	/*스크랩*/
	public String checkscrap(int f_no) throws Exception ;


	//코멘트
	public void changeFcomment(int f_no) throws Exception;

}
