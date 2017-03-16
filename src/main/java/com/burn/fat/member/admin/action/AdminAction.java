package com.burn.fat.member.admin.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import com.burn.fat.member.admin.dao.AdminService;
import com.burn.fat.member.model.MemberBean;
import com.oreilly.servlet.MultipartRequest;

@Controller("Adminaction")
public class AdminAction {

	@Autowired
	private AdminService service;

	private String saveFolder = "C:/gitwork/FinalProject/src/main/webapp/resources/upload";

	// 회원관리 리스트
	@RequestMapping(value = "/memlist.brn")
	public ModelAndView mem_modify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = 1;
		// int limit=10; // 한 화면에 출력할 레코드 갯수
		int limit = 10; // 목록보기 초기값
		HttpSession session = request.getSession();

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 추가
		// 이전에 설정된 limit가 있는지 체크
		if (session.getAttribute("limit") != null) {
			limit = (Integer) session.getAttribute("limit");
		}
		// 변경된 limit가 있는지 체크
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
		}

		System.out.println("limit = " + limit);
		System.out.println("page = " + page);
		// 여기까지 추가

		// 총 리스트 수를 받아옴.
		int listcount = this.service.getListCount(); // 총 리스트 수를 받아옴

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		if (endpage < page)
			page = endpage;

		Map m = new HashMap();
		m.put("page", page);
		m.put("limit", limit);
		// 리스트 받아옴
		List<MemberBean> member = service.getMemList(m);// page,limit를 보냄

		ModelAndView model = new ModelAndView("html_membership/memList");
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("member", member);
		model.addObject("limit", limit);

		return model;
	}

	// 회원관리 뷰
	@RequestMapping(value = "/mem_cont.brn")
	public ModelAndView mem_cont(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "num") int num) throws Exception {

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			// parseInt()메서드로 정수형 숫자로 바꿔서 저장
		}

		MemberBean member = this.service.getMemCont(num);

		ModelAndView model = new ModelAndView("html_membership/modifyView");

		model.addObject("member", member);

		return model;
	}

	// 회원 검색 mem_find_ok.brn
	@RequestMapping(value = "/mem_find_ok.brn", method = RequestMethod.GET)
	public ModelAndView mem_find_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page);
		}

		String find_name = null;
		if (request.getParameter("find_name").trim() != null) {
			find_name = request.getParameter("find_name").trim();
			System.out.println(find_name);
		}

		String find_field = null;
		if (request.getParameter("find_field") != null) {
			find_field = request.getParameter("find_field").trim();
			System.out.println(find_field);
		}

		Map m = new HashMap();
		m.put("page", page);
		m.put("find_field", find_field);
		m.put("find_name", "%" + find_name + "%"); // %는 쿼리에서 포함된 문자열을 찾아라

		int listcount = this.service.getListCount_find(m);
		System.out.println("listcount는?" + listcount);

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<MemberBean> member = this.service.getMemList_find(m);
		System.out.println(member);

		ModelAndView model = new ModelAndView("html_membership/memList");
		model.addObject("find_name", find_name);
		model.addObject("find_field", find_field);
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("member", member);

		return model;
		// return null;
	}

	// 회원관리 수정 폼
	@RequestMapping(value = "/manage_edit.brn")
	public ModelAndView manage_edit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter(); // 출력 스트림 객체 생성
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("mem_id"); // 회원 아이디

		MemberBean member = service.isMem(id);
		System.out.println("id는???" + id);

		if (member != null) {

			String mem_hp = member.getMem_hp();
			// 핸드폰 번호 저장
			StringTokenizer st02 = new StringTokenizer(mem_hp, "-");
			String hp1 = st02.nextToken();// 첫번째 자리
			String hp2 = st02.nextToken(); // 두번째 자리
			String hp3 = st02.nextToken();// 세번째 자리

			String mem_tel = member.getMem_tel();

			// 집 번화 번호 저장
			StringTokenizer st01 = new StringTokenizer(mem_tel, "-");
			String tel1 = st01.nextToken();// 첫번째(국번 집전화번호 저장)
			String tel2 = st01.nextToken(); // 두번째(가운데 자리)
			String tel3 = st01.nextToken();// 세번째(뒷 자리)

			String mem_ma = member.getMem_ma();

			// 이메일 저장
			// 두번째 @를 기준으로 문자열을 파싱해 줍니다.
			StringTokenizer st03 = new StringTokenizer(mem_ma, "@");
			String mem_mailid = st03.nextToken();
			String mem_maildomain = st03.nextToken();

			// Date to String

			String mem_bd = member.getMem_bd().toString();

			// 생년월일 저장
			StringTokenizer st04 = new StringTokenizer(mem_bd, "-");
			String year = st04.nextToken();// 첫번째
			String month = st04.nextToken(); // 두번째
			String day = st04.nextToken();// 세번째

			ModelAndView mv = new ModelAndView("html_membership/manageModify");

			mv.addObject("member", member);
			mv.addObject("mem_mailid", mem_mailid);
			mv.addObject("mem_maildomain", mem_maildomain);
			mv.addObject("hp1", hp1);
			mv.addObject("hp2", hp2);
			mv.addObject("hp3", hp3);
			mv.addObject("tel1", tel1);
			mv.addObject("tel2", tel2);
			mv.addObject("tel3", tel3);
			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);

			return mv;
		} else {

			out.print("<script>");
			out.print("history.go(-1);");
			out.print("alert('이미 탈퇴된 회원입니다..');");
			out.print("</script>");
		}
		return null;

	}

	// 회원 관리 수정
	@RequestMapping(value = "/manage_edit_ok.brn", method = RequestMethod.POST)
	public void manage_edit_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int fileSize = 5 * 1024 * 1024; // 이진파일 최대 업로드 크기
		String fileDBName = "";
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");

		String mem_id = multi.getParameter("inputid").trim();

		MemberBean member = service.isMem(mem_id);
		// 디비로부터 회원정보 가져옴

		String pw = multi.getParameter("inputpw").trim();
		String name = multi.getParameter("inputname").trim();
		int gender = Integer.parseInt(multi.getParameter("gender"));
		int year = Integer.parseInt(multi.getParameter("birthyear"));
		int month = Integer.parseInt(multi.getParameter("birthmonth"));
		int day = Integer.parseInt(multi.getParameter("birthday"));
		String hp1 = multi.getParameter("mobileNo1").trim();
		String hp2 = multi.getParameter("middleph").trim();
		String hp3 = multi.getParameter("lastph").trim();
		int zc = Integer.parseInt(multi.getParameter("zipcode"));
		String addr = multi.getParameter("address").trim();
		String tel1 = multi.getParameter("mem_tel1").trim();
		String tel2 = multi.getParameter("mem_tel2").trim();
		String tel3 = multi.getParameter("mem_tel3").trim();
		String detailaddr = multi.getParameter("detailaddr").trim();
		String email = multi.getParameter("email").trim();
		String domain = multi.getParameter("domain").trim();

		double height = Double.parseDouble(multi.getParameter("height"));
		double weight = Double.parseDouble(multi.getParameter("weight"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(year + "-" + month + "-" + day);
		Date sqldate = new Date(date.getTime());
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		String hp = hp1 + "-" + hp2 + "-" + hp3;

		File thumbnail = multi.getFile("wannabe"); // 첨부한 파일을 가져옴

		if (thumbnail != null) {
			String filename = thumbnail.getName(); // 이진파일명 저장
			File DelFile = new File(saveFolder + member.getMem_wb());
			if (DelFile.exists()) { // 기존 파일이 존재하면
				DelFile.delete(); // 기존 파일명 삭제
			}

			Calendar c = Calendar.getInstance();
			int year1 = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
			int month1 = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
			int date1 = c.get(Calendar.DATE); // 오늘 일 구합니다.

			String homedir = saveFolder + "/" + year1 + "-" + month1 + "-" + date1;
			// upload폴더 아래에 파일 올린 날짜로 폴더 생성합니다.
			File path1 = new File(homedir);
			if (!(path1.exists())) {
				path1.mkdir();// 새로운 폴더를 생성
			}
			// 난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);

			/**** 확장자 구하기 시작 ****/
			int index = 0;
			String fileExtension = "";
			String refileName = "";

			index = filename.lastIndexOf(".");
			// 문자열에서 특정 문자열의 위치 값(index)를 반환한다.
			// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			// (파일명에 점에 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			fileExtension = filename.substring(index + 1);
			/**** 확장자 구하기 끝 ***/
			// 새로운 파일명을 저장
			refileName = "wannabe" + year1 + month1 + date1 + random + "." + fileExtension;
			// 오라클 디비에 저장될 레코드 값
			fileDBName = "/" + year1 + "-" + month1 + "-" + date1 + "/" + refileName;
			// 파일명 변경합니다.
			thumbnail.renameTo(new File(homedir + "/" + refileName));

			member.setMem_wb(fileDBName); // 파일명 저장

		}

		member.setMem_pw(pw);
		member.setMem_sx(gender);
		member.setMem_bd(sqldate);
		member.setMem_hp(hp);
		member.setMem_zc(zc);
		member.setMem_add1(addr);
		member.setMem_nm(name);
		member.setMem_add2(detailaddr);
		member.setMem_ma(email + "@" + domain);
		member.setMem_h(height);
		member.setMem_w(weight);
		member.setMem_tel(tel);

		this.service.manage_edit(member);

		response.sendRedirect("./memlist.brn");
	}

	// 회원관리 삭제
	@RequestMapping(value = "/manage_delete_ok.brn")
	public void manage_delete_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter(); // 출력 스트림 객체 생성
		String mem_id = request.getParameter("mem_id");

		MemberBean member = service.isMem(mem_id);

		if (member != null) {
			this.service.manage_delete(mem_id);

			out.print("<script>");
			out.print("history.go(-1);");
			out.print("alert('탈퇴되었습니다.');");
			out.print("</script>");
			response.sendRedirect("./memlist.brn");

		}else{

			out.print("<script>");
			out.print("history.go(-1);");
			out.print("alert('이미 탈퇴된 회원입니다..');");
			out.print("</script>");
		}
	}

}
