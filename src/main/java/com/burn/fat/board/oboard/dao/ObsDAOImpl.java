package com.burn.fat.board.oboard.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.oboard.model.ObsBean;

@Repository
public class ObsDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/* 자료실 저장 */
	public void insertObs(ObsBean obsbean) throws Exception {
		sqlSession.insert("obs_insert", obsbean);
	}

	/* 코멘트 저장 */
	public void insertObs_comm(ObsBean obsbean) throws Exception {
		int re_rev = obsbean.getOcomm_re_lev();

		if (re_rev == 0) {
			sqlSession.insert("obs_comm_insert", obsbean);
		} else {
			sqlSession.insert("obs_sub_comm_insert", obsbean);
		}
	}

	/* 코멘트 삭제 */
	public void deleteObs_comm(ObsBean obsbean) throws Exception {
		sqlSession.update("obs_comm_delete", obsbean);
	}

	/* 자료실 총 게시물 수 */
	public int getOlistCount() throws Exception {
		int count = ((Integer) sqlSession.selectOne("obs_count")).intValue();
		return count;
	}

	/* 자료실 목록과 페이징 */
	public List<ObsBean> getObsList(Map<String, Integer> m) throws Exception {
		List<ObsBean> list = sqlSession.selectList("obs_list", m);
		return list;
	}

	/* 번호를 기준으로 자료실 내용 가져오기 */
	public ObsBean getObsCont(int num) throws Exception {
		return (ObsBean) sqlSession.selectOne("obs_cont", num);
	}

	/* 내용보기 할때만 조회수 증가 */
	public void obsHit(int num) throws Exception {
		sqlSession.update("obs_hit", num);
	}

	/* 좋아요 버튼 눌렀을 시 조회수 증가 */
	public void obsLike(int num) throws Exception {
		sqlSession.update("obs_like", num);
	}

	/* 자료실 수정 */
	public void editObs(ObsBean obsbean) throws Exception {
		sqlSession.update("obs_edit", obsbean);
	}

	/* 자료실 삭제 */
	public void deleteObs(int obs_num) throws Exception {
		sqlSession.delete("obs_delete", obs_num);
	}

	/* 검색 결과 게시물 수 */ // 예전같았으면 밑에처럼이지만 Map을 써서 57번라인처럼 보낸다
	// public int getListCount3(String find_name,String find_field) throws
	// SQLException{
	public int getOListCount3(Map m) throws Exception {
		int count = 0;
		count = ((Integer) sqlSession.selectOne("obsfind_cnt", m)).intValue();
		return count;
	}

	/* 검색 결과 페이징 목록 */
	public List<ObsBean> getObsList3(Map m) throws Exception {
		List<ObsBean> list = sqlSession.selectList("obs_find", m);
		return list;
	}

	/* 코멘트 리스트 */
	public List<ObsBean> getOclistCount(int num) {
		List<ObsBean> list = sqlSession.selectList("obscom_list", num);
		return list;
	}

	/* 스크랩 체크 */
	public String checkscrap(int o_no) {

		return sqlSession.selectOne("checkscrap", o_no);
	}

	/* 조회수 증가, 컬럼 추가 */
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("likecountup", map);
	}

}
