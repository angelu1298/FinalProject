package com.burn.fat.member.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.internet.*;
import javax.mail.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.dao.MemberService;
import com.burn.fat.member.model.MemberBean;

@Controller("member")
public class MemberAction {

	//- @Autowired : 타입을 기준으로 빈을 찾아 주입하는 애노테이션
	//- @Resource : 이름을 기준으로 빈을 찾아 주입하는 애노테이션
	@Autowired  //@Autowired은 생성자나 메서드, 멤버변수 위에 모두 사용할 수 있습니다.
	private MemberService memberService;
	


	
	/*1. 아이디찾기(핸드폰)*/
	@RequestMapping(value="/id_find_ok1.brn",method=RequestMethod.POST)
	public ModelAndView id_find_ok1(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        String mem_nm=request.getParameter("mem_nm").trim();
        
        //핸드폰
		String mem_hp1=request.getParameter("mem_hp1").trim();
		String mem_hp2=request.getParameter("mem_hp2").trim();
		String mem_hp3=request.getParameter("mem_hp3").trim();
		
		/*String mem_hp=mem_hp1+"-"+mem_hp2+"-"+mem_hp3;*/
		String mem_hp=mem_hp1+mem_hp2+mem_hp3;
		
		//생년월일
		String mem_bd1=request.getParameter("mem_bd1").trim();
		String mem_bd2=request.getParameter("mem_bd2").trim();
		String mem_bd3=request.getParameter("mem_bd3").trim();
		
		
		
		if(mem_bd2.length() < 2){
			mem_bd2 = "0"+request.getParameter("mem_bd2").trim();
		}
		
		if(mem_bd3.length() < 2){
			mem_bd3 = "0"+request.getParameter("mem_bd3").trim();
		}
		
		
		
		
        String mem_bd=mem_bd1+"-"+mem_bd2+"-"+mem_bd3;
		
		
		Map<String, String> m=new HashMap<String,String>();
		//컬렉션 Map에 키와 값  저장합니다.
		m.put("mem_nm",mem_nm);
		m.put("mem_hp",mem_hp);
		m.put("mem_bd",mem_bd);
		//이름, 핸드폰, 생년월일 저장
		
		MemberBean member=this.memberService.findid1(m);
		
		if(member == null){//회원 아이디와 정보가 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디와 정보가 맞지 않습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		}else{
			
			ModelAndView mv=new ModelAndView("html_membership/idFindRslt");
			//html_membership/idFind.jsp로 이동
			
			mv.addObject("mem_id",member.getMem_id());
			return mv;
		}
		
		
				return null;
		
	}
	
	
	/*2. 아이디찾기(이메일)*/
	@RequestMapping(value="/id_find_ok2.brn",method=RequestMethod.POST)
	public ModelAndView id_find_ok2(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        String mem_nm=request.getParameter("mem_nm").trim();
        

		
		//생년월일
		String mem_bd1=request.getParameter("mem_bd1").trim();
		String mem_bd2=request.getParameter("mem_bd2").trim();
		String mem_bd3=request.getParameter("mem_bd3").trim();
		
		
		
		if(mem_bd2.length() < 2){
			mem_bd2 = "0"+request.getParameter("mem_bd2").trim();
		}
		
		if(mem_bd3.length() < 2){
			mem_bd3 = "0"+request.getParameter("mem_bd3").trim();
		}
		
		
		
		
        String mem_bd=mem_bd1+"-"+mem_bd2+"-"+mem_bd3;
        
        
        //이메일 아이디
      	String mem_maid=request.getParameter("mem_maid").trim();
      		
      	//이메일 도메인
      	String mem_madomain=request.getParameter("mem_madomain").trim();
      		
        String mem_ma=mem_maid+"@"+mem_madomain;//이메일 주소
        
        
        
        System.out.println("mem_maid= "+mem_maid);
		System.out.println("mem_madomain= "+mem_madomain);
		
		Map<String, String> m=new HashMap<String,String>();
		//컬렉션 Map에 키와 값  저장합니다.
		m.put("mem_nm",mem_nm);
		m.put("mem_ma",mem_ma);
		m.put("mem_bd",mem_bd);
		//이름, 핸드폰, 생년월일 저장
		
		MemberBean member=this.memberService.findid2(m);
		
		if(member == null){//회원 아이디와 정보가 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디와 정보가 맞지 않습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		}else{
			
			ModelAndView mv=new ModelAndView("html_membership/idFindRslt");
			//html_membership/idFind.jsp로 이동
			
			mv.addObject("mem_id",member.getMem_id());
			return mv;
		}
		
		
				return null;
		
	}
	
	
	
	
	/*비번찾기 */
	@RequestMapping(value="/pwd_find_ok.brn",method=RequestMethod.POST)
	public ModelAndView pwd_find_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        String mem_nm=request.getParameter("mem_nm").trim();
		String mem_id=request.getParameter("mem_id").trim();
		
		
		//이메일 아이디
		String mem_maid=request.getParameter("mem_maid").trim();
		
		//이메일 도메인
		String mem_madomain=request.getParameter("mem_madomain").trim();
		
        String mem_ma=mem_maid+"@"+mem_madomain;//이메일 주소
		
		
		Map<String,String> m=new HashMap<String,String>();
		//컬렉션 Map에 키와 값  저장합니다.
		m.put("mem_nm",mem_nm);
		m.put("mem_id",mem_id);
		m.put("mem_ma",mem_ma);
		//이름, 아이디, 이메일 저장
		
		
		MemberBean member=this.memberService.findpwd(m);
		
		if(member == null){//회원 아이디와 정보가 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디와 정보가 맞지 않습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
			
		}else{  //회원 아이디와 정보가 맞는 경우
			
			try {
			
			ModelAndView mv=new ModelAndView("html_membership/pwFindRslt");
			//html_membership/pwFind.jsp로 이동
			
			mv.addObject("member",member);
			
			
			
			// 메일 관련 정보  ->SMTP 서버 주소를 지정합니다.(네이버인 경우)
	        String host = "smtp.naver.com";
	        
	        
	        //관리자의 메일 발신 전용 계정 메일.....
	        final String username = "ssheln";  //네이버 아이디     
	        int port=465;
	        final String password = "qwe123";   //네이버 이메일 비밀번호(실패/성공)
	        
	        
	        //임시비밀번호 생성
	        char[] charSet = new char[]{'0','1','2','3','4','5','6','7','8','9',
	        		'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
	        		'O','P','K','R','U','V','W','X','Y','Z'
	        };
	        
	        StringBuilder sb = new StringBuilder("");
	        Random rn = new Random();
	        
	        for( int i = 0 ; i < 9; i++ ){
	            sb.append( charSet[ rn.nextInt( charSet.length ) ] );  
	            
	        }
	        
	        String randomNum = sb.toString();
			
	        
	        
	        // 이메일 내용
	        String subject = "Burning fat 임시 비밀번호 발송";
	        String body = "Burning fat 홈페이지 임시 비밀번호 발송입니다.&nbsp; 임시 비밀번호는 "+randomNum+" 입니다."
	        		+ "&nbsp; 다시 로그인해주세요.";
	         
	        //html파일이 오는 경우
	        
	        
	        //서버 정보를 Properties 객체에 저장합니다.
	        Properties props = System.getProperties();
	          
	          
	        //SMTP 서버 정보 설정
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.ssl.trust", host);
	           
	      //저장한 Properties 객체의 값으로 세션의 인스턴스를 생성합니다.
	      //public static Session getDefaultInstance(Properties props)
	        
				
			
	        
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            String un=username;
	            String pw=password;
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(un, pw);
	            }
	        });
	        session.setDebug(true); //for debug
	           
	        Message mimeMessage = new MimeMessage(session);
	        
	        mimeMessage.setFrom(new InternetAddress("ssheln@naver.com"));
	        //발신자 셋팅, 보내는 사람의 이메일 주소를 한번 더 입력.. 이메일 풀 주소를 다 작성
	        
	        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mem_ma));
	        //수신자 셋팅
	        
	        mimeMessage.setSubject(subject);
	        //제목 셋팅
	        mimeMessage.setText(body);
	        //내용 셋팅
	        Transport.send(mimeMessage);
	        
	        
	        out.println("<script>");
	        out.println("alert('임시 비밀번호 메일 보내기에 성공하였습니다.')");
	        out.println("</script>");
	        
	        return mv;

	        } catch (Exception e) {
	        	e.printStackTrace();
	        	out.println("<script>");
				out.println("alert('임시 비밀번호 메일 보내기에 실패하였습니다.')");
				out.println("history.go(-1)");  //오류 발생 시 뒤로 돌아감
				out.println("</script>");
			}
	        
	        
	        
	        
		
		}
		return null;
	}

	


	
}


