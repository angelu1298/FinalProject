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

	/*�ڷ�� ����*/
	public int insertGbbs(GbbsBean gbbsbean) throws Exception{
		return sqlSession.insert("gbbs_insert", gbbsbean);		
	}

	/* �ڷ�� �� �Խù� ��*/
	public int getGlistCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("gbbs_count")).intValue();
		return count;
	}
 
	public String getGbbsAuthorName() throws Exception{
		String gbbs_author =sqlSession.selectOne("gbbs_author");
		return gbbs_author;
	}
	
	/* �ڷ�� ��ϰ� ����¡  */
	public List<GbbsBean> getGbbsList(int page) throws Exception {
		List<GbbsBean>  list = sqlSession.selectList("gbbs_gall", page);
	    return list;
	}
	
	/*�ڷ�� ��ϰ� ����¡ : limit �߰��� �κ�! */ 
	public List<GbbsBean> getGbbsGall(Map m) throws Exception {
		List<GbbsBean> list = sqlSession.selectList("gbbs_gall", m);
	    return list;
	} 
	
	/*��ȣ�� �������� �ڷ�� ���� �������� */
	public GbbsBean getGbbsCont(int num) throws Exception{
		return (GbbsBean)sqlSession.selectOne("gbbs_cont", num);
	}

	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void gbbsHit(int num) throws Exception{
		sqlSession.update("gbbs_hit", num);		
	}

	/*���뺸�� �Ҷ��� ���ƿ� ����*/
	public void gbbsLike(int num) throws Exception{
		sqlSession.update("gbbs_like", num);		
	}

	/*�ڷ�� ����*/
	public void editGbbs(GbbsBean gbbsbean) throws Exception{
		sqlSession.update("gbbs_edit", gbbsbean);		
	}

	/*�ڷ�� ����*/
	public void deleteGbbs(int gbbs_num) throws Exception{
		sqlSession.delete("gbbs_delete", gbbs_num);		
	}

	/*�˻� ��� �Խù� ��*/
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
 
	/*�亯�� ����*/
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