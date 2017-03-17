package com.burn.fat.board.oboard.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.oboard.dao.OBoardService;
import com.burn.fat.board.oboard.dao.OcommService;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.board.oboard.model.OcommBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class ObsAction {

	@Autowired
	private OBoardService obsService;
	@Autowired
	private OcommService ocommService;
	private String saveFolder="C:/git/FinalProject/src/main/webapp/resources/upload";
	
	@RequestMapping(value = "/obs_write.brn")
	public String obs_write() {

		return "html_community/oboard/boardWrite";
	}
	
	

	/* 자료실 저장 */
  	//  첨부파일 클릭할 경우 이미지 보고자 할  경우 : 자동 새로 고침 설정
  	//(window-> Preferencs -> workspce -> 
  	// Refresh using native hooks or polling 체크)
  	//  하고 5초 정도 지난 뒤 확인하세요
	@RequestMapping(value = "/obs_scrap.brn")
	public void oboardscrap(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "o_no", required = true) int o_no) throws Exception {

		HttpSession session = request.getSession();
		String mem_no = String.valueOf(session.getAttribute("mem_no"));
//	mem_no = "1";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("o_no", o_no);
		map.put("mem_no", mem_no);
		int check = 0;
		String o_lkno = obsService.checkscrap(o_no);
		if (o_lkno != null) {
			StringTokenizer token = new StringTokenizer(o_lkno, ",");
			while (token.hasMoreTokens()) {
				if (token.nextToken().equals(mem_no)) {
					check = 1;
				}
			}
			System.out.println("1." + o_lkno);
		} else {
			System.out.println("o_lkno is null");
		}
		int result = 0;
		if (check != 1) {
			result = obsService.likeCountUp(map);
			System.out.println(result);
		}
		PrintWriter out = response.getWriter();
		out.print(result);
	
		//response.sendRedirect("obs_view.brn?num=" + o_no + "&state=cont");
		
	}

	/*글쓰기 저장*/
	@RequestMapping(value = "/obs_write_ok.brn", method = RequestMethod.POST)
	public ModelAndView obs_write_ok(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		System.out.println("ddogod");

		ObsBean obsbean = new ObsBean();
		int fileSize = 5 * 1024 * 1024; // �������� �ִ� ���ε� ũ��

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");

		int mem_no = (Integer) session.getAttribute("mem_no");
		String o_sj = multi.getParameter("o_sj").trim();
		String o_ct = multi.getParameter("o_ct").trim();
		String mem_id = (String) session.getAttribute("mem_id");

		System.out.println("mem_id = " + mem_id);
		File UpFile = multi.getFile("o_fl");
		if (UpFile != null) {
			String fileName = UpFile.getName();
			System.out.println("fileName = " + fileName);
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1; 
			int date = c.get(Calendar.DATE); 

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
			System.out.println("homedir = " + homedir);
		
			File path1 = new File(homedir);
			if (!(path1.exists())) {
				path1.mkdir();// ���ο� ������ ����
			}
			// ������ ���մϴ�.
			Random r = new Random();
			int random = r.nextInt(100000000);

			/**** Ȯ���� ���ϱ� ���� ****/
			int index = fileName.lastIndexOf(".");
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			String refileName = "obs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			UpFile.renameTo(new File(homedir + "/" + refileName));
			System.out.println("homedir / refileName  = " + homedir + "/" + refileName);

			obsbean.setO_fl(fileDBName);
		}

		obsbean.setMem_no(mem_no);
		// obsbean.setObs_pass(obs_pass);
		obsbean.setO_sj(o_sj);
		obsbean.setO_ct(o_ct);
		obsbean.setMem_id(mem_id);

		this.obsService.insertObs(obsbean); // ����޼��� ȣ��

		response.sendRedirect("obs_list.brn?o_no=" + obsbean.getO_no() + "&state=cont");
		return null;

	}

	/* 게시글 목록 보기*/
	@RequestMapping(value = "/obs_list.brn")
	public ModelAndView obs_list(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "limit", defaultValue = "20") int limit) throws Exception {

		String mem_id = (String) session.getAttribute("mem_id");

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		if (session.getAttribute("limit") != null) {
			limit = (Integer) session.getAttribute("limit");
		}

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit); 
		}

		System.out.println("limit = " + limit);
		System.out.println("page = " + page);

		int listcount = obsService.getOlistCount(); // �� ����Ʈ ���� �޾ƿ�

		int maxpage = (listcount + limit - 1) / limit;

		int startpage = ((page - 1) / 10) * 10 + 1;

		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		if (endpage < page)
			page = endpage;

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("page", page);
		m.put("limit", limit);

		List<ObsBean> obslist = obsService.getObsList(m);
		ModelAndView model = new ModelAndView("html_community/oboard/boardList");
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("obslist", obslist);
		model.addObject("limit", limit);
		model.addObject("mem_id", mem_id);
		return model;
	}

	
	@RequestMapping(value = "/obs_view.brn")
	public ModelAndView obs_cont(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String state = request.getParameter("state");

		
		if (state.equals("cont")) {
			this.obsService.obsHit(num);
		}

		ObsBean obsbean = this.obsService.getObsCont(num);
		ObsBean obsbean_p = this.obsService.getObsCont(num + 1);
		ObsBean obsbean_m = this.obsService.getObsCont(num - 1);

		System.out.println("111" + obsbean);
		System.out.println("222" + obsbean_p);
		System.out.println("333" + obsbean_m);

		List<ObsBean> obsCommentList = obsService.getOclistCount(num);
		List<OcommBean> ocommbeanlist = new ArrayList<OcommBean>();
		ocommbeanlist = ocommService.getOCommList(num);
		
		ModelAndView contM = new ModelAndView();

		contM.addObject("obsbean_m", obsbean_m);// ����
		contM.addObject("obsbean_p", obsbean_p);// ����

		if (state.equals("cont")) {
			// contM.setViewName("html_community/boardView?");
			contM.setViewName("html_community/oboard/boardView");

			String obs_cont = obsbean.getO_ct().replace("\n", "<br/>");

			contM.addObject("obs_cont", obs_cont);
		} else if (state.equals("mod")) {
			contM.setViewName("html_community/oboard/boardEdit");
		} 
		contM.addObject("obsbean", obsbean);
		contM.addObject("obsCommentList", obsCommentList);
		contM.addObject("page", page);
		contM.addObject("beanlist",ocommbeanlist);
		return contM;
	}

	/* �ۻ��� */
	@RequestMapping(value = "/obs_delete.brn")
	public ModelAndView obs_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int o_no = Integer.parseInt(request.getParameter("num")); // �۹�ȣ

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		this.obsService.deleteObs(o_no);
		response.sendRedirect("obs_list.brn?page=" + page);

		return null;
	}

	/* 글쓰기 수정 */
	@RequestMapping(value = "/obs_edit_ok.brn", method = RequestMethod.POST)
	public ModelAndView obs_edit_ok(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		System.out.println("obs_edit");

		ObsBean obsbean = new ObsBean();
		int fileSize = 5 * 1024 * 1024; 

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");

		int mem_no = (Integer) session.getAttribute("mem_no");
		;
		String o_sj = multi.getParameter("o_sj").trim();
		String o_ct = multi.getParameter("o_ct").trim();
		String mem_id = (String) session.getAttribute("mem_id");
		int o_rc = Integer.parseInt(multi.getParameter("o_rc"));// ��ȸ��
		int o_no = Integer.parseInt(multi.getParameter("num"));// ��ȣ
		int page = Integer.parseInt(multi.getParameter("page"));// �ʹ�ȣ

		System.out.println("mem_id = " + mem_id);

		File UpFile = multi.getFile("o_fl");
		if (UpFile != null) {// ÷���� ���� �ִٸ�
			String fileName = UpFile.getName();// �������ϸ� ����
			System.out.println("fileName = " + fileName);
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); // ���� �⵵ ���մϴ�.
			int month = c.get(Calendar.MONTH) + 1; // ���� �� ���մϴ�.
			int date = c.get(Calendar.DATE); // ���� �� ���մϴ�.

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
			System.out.println("homedir = " + homedir);
			// upload���� �Ʒ��� ���� �ø� ��¥�� ���� �����մϴ�.
			File path1 = new File(homedir);
			if (!(path1.exists())) {
				path1.mkdir();// ���ο� ������ ����
			}

			Random r = new Random();
			int random = r.nextInt(100000000);

			int index = fileName.lastIndexOf(".");
			
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);

			String refileName = "obs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			UpFile.renameTo(new File(homedir + "/" + refileName));
			System.out.println("homedir / refileName  = " + homedir + "/" + refileName);

			obsbean.setO_fl(fileDBName);
		}
		obsbean.setO_no(o_no);
		obsbean.setMem_no(mem_no);
		obsbean.setO_sj(o_sj);
		obsbean.setO_ct(o_ct);
		obsbean.setMem_id(mem_id);
		obsbean.setO_rc(o_rc-1);

		System.out.println("*****************************");
		System.out.println("o_sj=" + o_sj);
		System.out.println("o_ct=" + o_ct);
		System.out.println("*****************************");

		this.obsService.editObs(obsbean);// �����޼��� ȣ��

		System.out.println("*****************************");
		System.out.println("update success");
		System.out.println("*****************************");

		response.sendRedirect("obs_view.brn?state=cont&page=" + page + "&num=" + o_no);
		return null;
	}

	/* 글 삭제 */
	@RequestMapping(value = "/obs_delete_ok.brn", method = RequestMethod.POST)
	public ModelAndView obs_delete_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int o_no = Integer.parseInt(request.getParameter("o_no"));
		int page = Integer.parseInt(request.getParameter("page"));
		// String pwd=request.getParameter("pwd").trim();

		ObsBean obsbean = this.obsService.getObsCont(o_no);

		String fname = obsbean.getO_fl();
		/*
		 * if(!obsbean.getObs_pass().equals(pwd)){//����� ���� �ʴٸ�
		 * out.println("<script>"); out.println("alert('����� �ٸ��ϴ�!')");
		 * out.println("history.back()"); out.println("</script>"); }else{//�����
		 * ���ٸ� if(fname != null){//���� ���������� �����Ѵٸ� File file=new
		 * File(saveFolder+fname); file.delete();//���� ������ ���� ���� �������� �����մϴ�. }
		 */
		this.obsService.deleteObs(o_no);// ���� ���� ���ڵ� �����մϴ�.
		response.sendRedirect("obs_list.brn?page=" + page);
		// }
		return null;
	}

	/*자료실 검색 목록*/
	@RequestMapping(value = "/obs_find_ok.brn", method = RequestMethod.GET)
	public ModelAndView obs_find_ok(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "limit", defaultValue = "20") int limit) throws Exception {
		System.out.println(limit);

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String find_name = null;
		if (request.getParameter("find_name").trim() != null) {
			find_name = request.getParameter("find_name").trim();
		}

		String find_field = null;
		if (request.getParameter("find_field") != null) {
			find_field = request.getParameter("find_field").trim();
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", page);
		m.put("limit", limit);
		m.put("find_field", find_field);
		m.put("find_name", "%" + find_name + "%"); // %�� �������� ���Ե� ���ڿ��� ã�ƶ�
		System.out.println(find_field);
		System.out.println(find_name);

		int listcount = this.obsService.getOListCount3(m);
		System.out.println(listcount);

		// �� ������ ��
		int maxpage = (listcount + limit - 1) / limit;

		// ���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = ((page - 1) / 10) * 10 + 1;

		// ���� �������� ������ ������ ������ ��(10, 20, 30 ��...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<ObsBean> obslist = obsService.getObsList3(m);

		ModelAndView model = new ModelAndView("/html_community/oboard/boardList");

		model.addObject("limit", limit);
		model.addObject("find_name", find_name);
		model.addObject("find_field", find_field);
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("obslist", obslist);

		return model;
		// return null;
	}

	
}
