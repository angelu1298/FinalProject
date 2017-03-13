package com.burn.fat.board.gboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.gboard.model.GbbsBean;
public interface GboardService {
	
	/*자료실 저장*/
	public void insertGbbs(GbbsBean gbbsbean) throws Exception;

	/* 자료실 총 게시물 수*/
	public int getGlistCount() throws Exception;
	
	 /* 자료실 목록과 페이징 */
	public List<GbbsBean> getGbbsGall(Map m) throws Exception ;

	/*번호를 기준으로 자료실 내용 가져오기 */
	public GbbsBean getGbbsCont(int num) throws Exception;
	public GbbsBean getGbbsContpage(int rownum) throws Exception;

	/*내용보기 할때만 조회수 증가*/
	public void gbbsHit(int num) throws Exception;
	
	/*내용보기 할때만 조회수 증가*/
	public void gbbsLike(int num) throws Exception;

	/*자료실 수정*/
	public void editGbbs(GbbsBean gbbsbean) throws Exception;

	/*자료실 삭제*/
	public void deleteGbbs(int gbbs_num) throws Exception;

	/*검색 결과 게시물 수*/
	public int getGlistCount(Map m) throws Exception;

	/*검색 결과 페이징 목록*/
	public List<GbbsBean> getGbbsGall2(Map m) throws Exception ;

	/*검색 결과 게시물 수*/
	public int getGlistCount2(Map m) throws Exception;

	/*답변글 저장*/
	public void gbbsReplyOk(GbbsBean gbbsbean) throws Exception;

	/*이름호출*/
	public String getGbbsName(int mnum) throws Exception;

	public String getGbbsAuthorName() throws Exception;

	public void changeGcommcnt(int gbbs_num);


}
