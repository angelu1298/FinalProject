package com.burn.fat.board.gboard.dao;
import java.util.List;
import java.util.Map;

import com.burn.fat.board.gboard.model.GbbsBean;
public interface GboardService {
	
	/*�ڷ�� ����*/
	public void insertGbbs(GbbsBean gbbsbean) throws Exception;

	/* �ڷ�� �� �Խù� ��*/
	public int getGlistCount() throws Exception;
	
	 /* �ڷ�� ��ϰ� ����¡ */
	public List<GbbsBean> getGbbsGall(Map m) throws Exception ;

	/*��ȣ�� �������� �ڷ�� ���� �������� */
	public GbbsBean getGbbsCont(int num) throws Exception;
	public GbbsBean getGbbsContpage(int rownum) throws Exception;

	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void gbbsHit(int num) throws Exception;
	
	/*���뺸�� �Ҷ��� ��ȸ�� ����*/
	public void gbbsLike(int num) throws Exception;

	/*�ڷ�� ����*/
	public void editGbbs(GbbsBean gbbsbean) throws Exception;

	/*�ڷ�� ����*/
	public void deleteGbbs(int gbbs_num) throws Exception;

	/*�˻� ��� �Խù� ��*/
	public int getGlistCount(Map m) throws Exception;

	/*�˻� ��� ����¡ ���*/
	public List<GbbsBean> getGbbsGall2(Map m) throws Exception ;

	/*�˻� ��� �Խù� ��*/
	public int getGlistCount2(Map m) throws Exception;

	/*�亯�� ����*/
	public void gbbsReplyOk(GbbsBean gbbsbean) throws Exception;

	/*�̸�ȣ��*/
	public String getGbbsName(int mnum) throws Exception;

	public String getGbbsAuthorName() throws Exception;

	public void changeGcommcnt(int gbbs_num);


}
