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

	private String saveFolder="C:/apache-tomcat-8.0.42/webapps/FinalProject/resources/upload"; 

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

		ModelAndView model = new ModelAndView("html_membership/memView");

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
		}

		String find_name = null;
		if (request.getParameter("find_name").trim() != null) {
			find_name = request.getParameter("find_name").trim();
		}

		String find_field = null;
		if (request.getParameter("find_field") != null) {
			find_field = request.getParameter("find_field").trim();
		}

		Map m = new HashMap();
		m.put("page", page);
		m.put("find_field", find_field);
		m.put("find_name", "%" + find_name + "%"); // %는 쿼리에서 포함된 문자열을 찾아라

		int listcount = this.service.getListCount_find(m);

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<MemberBean> member = this.service.getMemList_find(m);

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
