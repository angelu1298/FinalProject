package com.burn.fat.member.login.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.login.dao.MemberService;
import com.burn.fat.member.model.MemberBean;

@Controller("Login")
public class LoginAction {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/Login.brn")
	public String login(){
		return "html_membership/login";
	
	}
	
	@RequestMapping(value="/Login_ok.brn", method=RequestMethod.POST)
	public ModelAndView login_ok(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value="check_id") String check_id, @RequestParam(value="check_pass") String check_pass,HttpSession session) throws Exception {
		MemberBean member = service.getMemberById(check_id);
		session= request.getSession();
		ModelAndView model = new ModelAndView("main/mainpage");
		PrintWriter out = response.getWriter();
			if(member==null){
				out.print("<script>alert('등록되지 않은 회원입니다.');history.go(-1)</script>");
				return null;
			}else{
				if(check_pass.equals(member.getMem_pw())){
					session.setAttribute("mem_id", check_id);
					session.setAttribute("mem_no", member.getMem_no());
				}else{
					out.print("<script>alert('비밀번호가 일치하지 않습니다.');history.go(-1)</script>");
					return null;
				}
			}
		
		return model;
	}
	
	@RequestMapping("/Logout.brn")
	public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		session= request.getSession();
		session.removeAttribute("mem_id");
		//임의로 설정
		return "html_membership/login";
		/*return "../index";*/
	}
	
	@RequestMapping("/IdFind.brn")
	public String idFind(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		return "html_membership/idFind";
	}
	@RequestMapping("/PwFind.brn")
	public String pwFind(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		return "html_membership/pwFind";
	}
	
}
