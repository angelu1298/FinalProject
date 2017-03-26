package com.burn.fat.board.gboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.gboard.model.GbbsBean;

@Repository
public class GbbsDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*자료실 저장*/
	public int insertGbbs(GbbsBean gbbsbean) throws Exception{
		return sqlSession.insert("gbbs_insert", gbbsbean);		
	}

	/* 자료실 총 게시물 수*/
	public int getGlistCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("gbbs_count")).intValue();
		return count;
	}
 
	public String getGbbsAuthorName() throws Exception{
		String gbbs_author =sqlSession.selectOne("gbbs_author");
		return gbbs_author;
	}
	
	/* 자료실 목록과 페이징  */
	public List<GbbsBean> getGbbsList(int page) throws Exception {
		List<GbbsBean>  list = sqlSession.selectList("gbbs_gall", page);
	    return list;
	}
	
	/*자료실 목록과 페이징 : limit 추가한 부분! */ 
	public List<GbbsBean> getGbbsGall(Map m) throws Exception {
		List<GbbsBean> list = sqlSession.selectList("gbbs_gall", m);
	    return list;
	} 
	
	/*번호를 기준으로 자료실 내용 가져오기 */
	public GbbsBean getGbbsCont(int num) throws Exception{
		return (GbbsBean)sqlSession.selectOne("gbbs_cont", num);
	}

	/*내용보기 할때만 조회수 증가*/
	public void gbbsHit(int num) throws Exception{
		sqlSession.update("gbbs_hit", num);		
	}

	/*내용보기 할때만 좋아요 증가*/
	public void gbbsLike(int num) throws Exception{
		sqlSession.update("gbbs_like", num);		
	}

	/*자료실 수정*/
	public void editGbbs(GbbsBean gbbsbean) throws Exception{
		sqlSession.update("gbbs_edit", gbbsbean);		
	}

	/*자료실 삭제*/
	public void deleteGbbs(int gbbs_num) throws Exception{
		sqlSession.delete("gbbs_delete", gbbs_num);		
	}

	/*검색 결과 게시물 수*/
	public int getGlistCount(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("gbbsfind_cnt", m)).intValue();
		return count;
	}
 
	public List<GbbsBean> getGbbsGall2(Map m) throws Exception {
		List<GbbsBean> list = sqlSession.selectList("gbbs_find", m);
	    return list;
	}
	
	public int getGlistCount2(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("gbbsfind_cnt", m)).intValue();
		return count;
	}
 
	/*답변글 저장*/
	public void gbbsReplyOk(GbbsBean gbbsbean) throws Exception{
		sqlSession.insert("gbbs_reply",gbbsbean);
	}

	public String getGbbsName(int mnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public void chageGcommcnt(int gbbs_num) {
		sqlSession.update("updategcommcnt",gbbs_num);
	}

	public GbbsBean getGbbsContpage(int rownum) {
		return (GbbsBean)sqlSession.selectOne("gbbs_contpage", rownum);
	}

}