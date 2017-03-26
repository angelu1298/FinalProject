package com.burn.fat.board.oboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.oboard.model.ObsBean;

public interface OBoardService {
	/*�ڷ�� ����*/
	public int insertObs(ObsBean obsbean) throws Exception;

	/* �ڷ�� �� �Խù� ��*/
	public int getOlistCount() throws Exception;
	
	/* �ڷ�� ��ϰ� ����¡ */
	public List<ObsBean> getObsList(Map<String, Integer> m) throws Exception ;

	/*��ȣ�� �������� �ڷ�� ���� �������� */
	public ObsBean getObsCont(int num) throws Exception;

	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void obsHit(int num) throws Exception;

	
	/*�ڷ�� ����*/
	public void editObs(ObsBean obsbean) throws Exception;

	/*�ڷ�� ����*/
	public void deleteObs(int obs_num) throws Exception;

	/*�˻� ��� �Խù� ��*/
	public int getOListCount3(Map m) throws Exception;

	/*�˻� ��� ����¡ ���*/
	public List<ObsBean> getObsList3(Map m) throws Exception ;

	//�ڸ�Ʈ ���
	public void insertObs_comm(ObsBean obsbean) throws Exception;	
	
	/*�ڸ�Ʈ ����Ʈ*/
	public List<ObsBean> getOclistCount(int num) throws Exception;

	/*�ڸ�Ʈ ����*/
	public void deleteObs_comm(ObsBean obsbean) throws Exception;
	
	/*��ũ��*/
	public String checkscrap(int o_no) throws Exception;

	/*��ũ�� ������ �� ��õ�� ������ �߰�*/
	public int likeCountUp(Map<String, Object> map) throws Exception;
	
	/*=============================================================================*/
	
}
