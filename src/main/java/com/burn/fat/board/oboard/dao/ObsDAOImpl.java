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

	/* �ڷ�� ���� */
	public int insertObs(ObsBean obsbean) throws Exception {
		return sqlSession.insert("obs_insert", obsbean);
	}

	/* �ڸ�Ʈ ���� */
	public void insertObs_comm(ObsBean obsbean) throws Exception {
		int re_rev = obsbean.getOcomm_re_lev();

		if (re_rev == 0) {
			sqlSession.insert("obs_comm_insert", obsbean);
		} else {
			sqlSession.insert("obs_sub_comm_insert", obsbean);
		}
	}

	/* �ڸ�Ʈ ���� */
	public void deleteObs_comm(ObsBean obsbean) throws Exception {
		sqlSession.update("obs_comm_delete", obsbean);
	}

	/* �ڷ�� �� �Խù� �� */
	public int getOlistCount() throws Exception {
		int count = ((Integer) sqlSession.selectOne("obs_count")).intValue();
		return count;
	}

	/* �ڷ�� ��ϰ� ����¡ */
	public List<ObsBean> getObsList(Map<String, Integer> m) throws Exception {
		List<ObsBean> list = sqlSession.selectList("obs_list", m);
		return list;
	}

	/* ��ȣ�� �������� �ڷ�� ���� �������� */
	public ObsBean getObsCont(int num) throws Exception {
		return (ObsBean) sqlSession.selectOne("obs_cont", num);
	}

	/* ���뺸�� �Ҷ��� ��ȸ�� ���� */
	public void obsHit(int num) throws Exception {
		sqlSession.update("obs_hit", num);
	}

	/* ���ƿ� ��ư ������ �� ��ȸ�� ���� */
	public void obsLike(int num) throws Exception {
		sqlSession.update("obs_like", num);
	}

	/* �ڷ�� ���� */
	public void editObs(ObsBean obsbean) throws Exception {
		sqlSession.update("obs_edit", obsbean);
	}

	/* �ڷ�� ���� */
	public void deleteObs(int obs_num) throws Exception {
		sqlSession.delete("obs_delete", obs_num);
	}

	/* �˻� ��� �Խù� �� */ // ������������ �ؿ�ó�������� Map�� �Ἥ 57������ó�� ������
	// public int getListCount3(String find_name,String find_field) throws
	// SQLException{
	public int getOListCount3(Map m) throws Exception {
		int count = 0;
		count = ((Integer) sqlSession.selectOne("obsfind_cnt", m)).intValue();
		return count;
	}

	/* �˻� ��� ����¡ ��� */
	public List<ObsBean> getObsList3(Map m) throws Exception {
		List<ObsBean> list = sqlSession.selectList("obs_find", m);
		return list;
	}

	/* �ڸ�Ʈ ����Ʈ */
	public List<ObsBean> getOclistCount(int num) {
		List<ObsBean> list = sqlSession.selectList("obscom_list", num);
		return list;
	}

	/* ��ũ�� üũ */
	public String checkscrap(int o_no) {

		return sqlSession.selectOne("o_checkscrap", o_no);
	}

	/* ��ȸ�� ����, �÷� �߰� */
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("o_likecountup", map);
	}
	/* eboard 스크랩 갯수*/
	   public int getEscrapCount(int mem_no) throws Exception {
	      int count = ((Integer) sqlSession.selectOne("e_scrap_count",mem_no)).intValue();
	      return count;
	   }

}
