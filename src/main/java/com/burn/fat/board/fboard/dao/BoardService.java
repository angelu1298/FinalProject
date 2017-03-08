package com.burn.fat.board.fboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.fboard.model.BbsBean;
public interface BoardService {
	/*�ڷ�� ����*/
	public void insertBbs(BbsBean bbsbean) throws Exception;

	/* �ڷ�� �� �Խù� ��*/
	public int getListCount() throws Exception;
	
	/* �ڷ�� ��ϰ� ����¡ */
	public List<BbsBean> getBbsList(Map m) throws Exception ;

	/*��ȣ�� �������� �ڷ�� ���� �������� */
	public BbsBean getBbsCont(int num) throws Exception;

	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void bbsHit(int num) throws Exception;

	/*�ڷ�� ����*/
	public void editBbs(BbsBean bbsbean) throws Exception;

	/*�ڷ�� ����*/
	public void deleteBbs(int f_no) throws Exception;

	/*�˻� ��� �Խù� ��*/
	public int getListCount3(Map m) throws Exception;

	/*�˻� ��� ����¡ ���*/
	public List<BbsBean> getBbsList3(Map m) throws Exception ;


	/*��õ*/
	public int likeCountUp(Map<String, Object> map) throws Exception;

	/*��ũ��*/
	public String checkscrap(int f_no) throws Exception ;


	//�ڸ�Ʈ
	public void changeFcomment(int f_no) throws Exception;

}
