package com.burn.fat.board.fboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.burn.fat.board.fboard.model.FboardBean;

@Repository
public class FboardDAOImpl {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/*�ڷ�� ����*/
	public void insertBbs(FboardBean FboardBean) throws Exception{
		sqlSession.insert("bbs_insert",FboardBean);		
	}

	/* �ڷ�� �� �Խù� ��*/
	public int getListCount() throws Exception{
		int count=((Integer)sqlSession.selectOne("bbs_count")).intValue();
		return count;
	}
	
	/* �ڷ�� ��ϰ� ����¡ */
	public List<FboardBean> getBbsList(Map m) throws Exception {//page,limit
		List<FboardBean>  list = sqlSession.selectList("bbs_list",m);
	    return list;
	}

	
	
	/*��ȣ�� �������� �ڷ�� ���� �������� */
	public FboardBean getBbsCont(int num) throws Exception{
		return (FboardBean)sqlSession.selectOne("bbs_cont", num);
	}

	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void bbsHit(int num) throws Exception{
		sqlSession.update("bbs_hit",num);		
	}

	/*�ڷ�� ����*/
	public void editBbs(FboardBean FboardBean) throws Exception{
		sqlSession.update("bbs_edit",FboardBean);		
	}

	/*�ڷ�� ����*/
	public void deleteBbs(int f_no) throws Exception{
		sqlSession.delete("bbs_delete",f_no);		
	}

	/*�˻� ��� �Խù� ��*/
//	public int getListCount3(String find_name,String find_field) throws SQLException{
	public int getListCount3(Map m) throws Exception{
		int count=0;
		count=((Integer)sqlSession.selectOne("bbsfind_cnt", m)).intValue();
		return count;
	}

	/*�˻� ��� ����¡ ���*/
	public List<FboardBean> getBbsList3(Map m) throws Exception {
		List<FboardBean>  list = sqlSession.selectList("bbs_find", m);
	    return list;
	}
	
	//��õ
	public int likeCountUp(Map<String, Object> map) {
		return sqlSession.update("likecountup", map);
	}

	//��ũ��
	public String checkscrap(int f_no) {
		
		return sqlSession.selectOne("checkscrap", f_no);
	}

	//�ڸ�Ʈ
	public void changeFcomment(int f_no) {
		sqlSession.update("updatefcommcnt",f_no);
	}

}











