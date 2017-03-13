package com.burn.fat.member.join.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.member.join.dao.JoinService;
import com.burn.fat.member.model.MemberBean;
import com.oreilly.servlet.MultipartRequest;

@Controller("Joinaction")
public class JoinAction {
	
	@Autowired
	private JoinService service;
	private String saveFolder = "C:/gitwork/FinalProject/src/main/webapp/resources/upload";

	
	//회원가입 1단계로 이동(가입동의)
	@RequestMapping(value="/Join.brn")
	public String join(){
		return "html_membership/join1";
	}
	//회원가입페이지로 이동
	@RequestMapping(value="/JoinGetInfo.brn", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, HttpServletResponse response ){
		if(request.getParameter("checkall")==null)
			return "html_membership/join1";
		return "html_membership/join2";
	}
	//워너비 사진 제외 회원가입완료
	@RequestMapping(value="/JoinSubmit.brn", method=RequestMethod.POST)
	public String joinSubmit(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="inputid")String id
			,@RequestParam(value="inputpw")String pw, @RequestParam(value="inputname")String name, @RequestParam(value="gender")int gender
			,@RequestParam(value="birthyear")int year, @RequestParam(value="birthmonth")int month,@RequestParam(value="birthday")int day
			,@RequestParam(value="mobileNo1")String hp1
			,@RequestParam(value="middleph")String hp2, @RequestParam(value="lastph")String hp3,@RequestParam(value="zipcode")int zc
			,@RequestParam(value="address")String addr, @RequestParam(value="mem_tel1")String tel1, @RequestParam(value="mem_tel2")String tel2
			,@RequestParam(value="mem_tel3")String tel3
			,@RequestParam(value="detailaddr")String detailaddr, @RequestParam(value="email")String email, @RequestParam(value="domain")String domain
			,@RequestParam(value="height")double height, @RequestParam(value="weight")double weight) throws Exception{
		MemberBean member = new MemberBean();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(year+"-"+month+"-"+day);
		Date sqldate = new Date(date.getTime());
		
		String hp = hp1+"-"+hp2+"-"+hp3;
		String tel=tel1+"-"+tel2+"-"+tel3;
		HttpSession session = request.getSession();
		session.setAttribute("mem_id", id);
		
		member.setMem_id(id);	member.setMem_sx(gender);	member.setMem_bd(sqldate);
		member.setMem_pw(pw);	member.setMem_hp(hp); 	member.setMem_zc(zc);	member.setMem_add1(addr);
		member.setMem_nm(name);	member.setMem_add2(detailaddr);		member.setMem_ma(email+"@"+domain);	member.setMem_h(height);
		member.setMem_w(weight); member.setMem_tel(tel);
		int mem_no = service.joinMember(member);
		session.setAttribute("mem_no", mem_no);
		
		return "html_membership/join3";
	}
	/*@RequestMapping(value="/PutThum.brn")
	public String seeThum(){
		return "html_membership/join3";
	}*/
	//워너비사진 완료
	@RequestMapping(value="/Thumbnail.brn", method=RequestMethod.POST)
	public ModelAndView thumbnailReturn(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		int fileSize = 5 * 1024 * 1024; // 이진파일 최대 업로드 크기
		String fileDBName = "";
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		File thumbnail = multi.getFile("wannabe");
		String filename = thumbnail.getName();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
		int date = c.get(Calendar.DATE); // 오늘 일 구합니다.

		String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
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
		refileName = "wannabe" + year + month + date + random + "." + fileExtension;
		// 오라클 디비에 저장될 레코드 값
		fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		// 파일명 변경합니다.
		thumbnail.renameTo(new File(homedir + "/" + refileName));
		
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("mem_wb", fileDBName);
		map.put("mem_id", (String)session.getAttribute("mem_id") );
		service.insertWannabe(map);
		
		ModelAndView model = new ModelAndView("html_membership/join3");
		model.addObject("wannabe",fileDBName);
		
		return model;
	} 
	
	//가입 완료 메시지
	@RequestMapping(value="/JoinComplete.brn")
	public String joinComplete(){
		return "html_membership/joinComplete";
	}
	
	@RequestMapping(value="/FindZipCode.brn")
	public String findZipCode(){
		return "html_membership/zipcode";
	}
	
	//ID중복 검사 ajax함수로 처리 부분
	@RequestMapping(value="/member_idcheck.brn")
	public void member_idcheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PrintWriter out=response.getWriter();
		String mem_id =request.getParameter("mem_id");
		
		int result =this.service.checkId(mem_id);
		
		out.println(result);
	}
	
	//회원정보 수정 이동 
	@RequestMapping(value="/mem_modify.brn")
	public ModelAndView mem_modify(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		PrintWriter out=response.getWriter();//출력 스트림 객체 생성
		session=request.getSession();//세션 객체 생성
		
		String mem_id=(String)session.getAttribute("mem_id");
		if(mem_id==null){//세션 아이디가 없는 경우
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='Login.brn'");
			out.println("</script>");
		}else{
			MemberBean member=this.service.isMember(mem_id);
					
			ModelAndView mv=new ModelAndView("html_membership/modifyView");
				
			mv.addObject("member",member);
				
			return mv;
			
		}
		
		return null;
	}
	
	
	
	//회원정보 수정 폼
	@RequestMapping(value="/mem_edit.brn")
	public ModelAndView mem_edit(HttpServletRequest request, HttpServletResponse response,HttpSession session
			) throws Exception{
		
		PrintWriter out= response.getWriter();
		session=request.getSession();
		
		//아이디 키값의 세션 아이디를 구함
		String mem_id=(String)session.getAttribute("mem_id");
		
		if(mem_id == null){  //세션 아이디 값이 없는 경우
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='Login.brn'");
			out.println("</script>");
			
		}else{
			
			MemberBean member = service.isMember(mem_id);
			
			String mem_hp = member.getMem_hp();
			
			//핸드폰 번호 저장
			StringTokenizer st02=new StringTokenizer(mem_hp,"-");
	    	String hp1=st02.nextToken();//첫번째 자리
	    	String hp2=st02.nextToken(); //두번째 자리
	    	String hp3=st02.nextToken();//세번째 자리 
	    
	    	String mem_tel =member.getMem_tel();
	    	
	    	//집 번화 번호 저장
	    	StringTokenizer st01=new StringTokenizer(mem_tel,"-");
	    	String tel1=st01.nextToken();//첫번째(국번 집전화번호 저장)
	    	String tel2=st01.nextToken(); //두번째(가운데 자리)
	    	String tel3=st01.nextToken();//세번째(뒷 자리)
	    	
			
	    	String mem_ma =member.getMem_ma();
	    	
	    	//이메일 저장
	    	//두번째 @를 기준으로 문자열을  파싱해 줍니다.
	    	StringTokenizer st03=new StringTokenizer(mem_ma,"@");
	    	String mem_mailid=st03.nextToken();
	    	String mem_maildomain=st03.nextToken();
	    	
	    	
	    	
	    	//Date to String
	    	
	    	String mem_bd = member.getMem_bd().toString();

	    	//생년월일 저장
	    	StringTokenizer st04=new StringTokenizer(mem_bd,"-");
	    	String year=st04.nextToken();//첫번째
	    	String month=st04.nextToken(); //두번째
	    	String day=st04.nextToken();//세번째

	    	
					
			ModelAndView mv = new ModelAndView("html_membership/modify");
			
			mv.addObject("member",member);
			mv.addObject("mem_mailid",mem_mailid);
			mv.addObject("mem_maildomain",mem_maildomain);
			mv.addObject("hp1",hp1);
			mv.addObject("hp2",hp2);
			mv.addObject("hp3",hp3);
			mv.addObject("tel1",tel1);
			mv.addObject("tel2",tel2);
			mv.addObject("tel3",tel3);
			mv.addObject("year",year);
			mv.addObject("month",month);
			mv.addObject("day",day);
			
			
			return mv;
			
		}
		return null;	
	}
	
	
	//회원정보 수정
	@RequestMapping(value="/mem_edit_ok.brn", method=RequestMethod.POST)
	public ModelAndView mem_edit_ok(HttpServletRequest request, HttpServletResponse response,HttpSession session  
			,@RequestParam(value="inputpw")String pw, @RequestParam(value="inputname")String name, @RequestParam(value="gender")int gender
			,@RequestParam(value="birthyear")int year, @RequestParam(value="birthmonth")int month,@RequestParam(value="birthday")int day
			,@RequestParam(value="mobileNo1")String hp1
			,@RequestParam(value="middleph")String hp2, @RequestParam(value="lastph")String hp3,@RequestParam(value="zipcode")int zc
			,@RequestParam(value="address")String addr, @RequestParam(value="mem_tel1")String tel1, @RequestParam(value="mem_tel2")String tel2
			,@RequestParam(value="mem_tel3")String tel3
			,@RequestParam(value="detailaddr")String detailaddr, @RequestParam(value="email")String email, @RequestParam(value="domain")String domain
			,@RequestParam(value="height")double height, @RequestParam(value="weight")double weight) throws Exception{
		
		PrintWriter out= response.getWriter();
		session=request.getSession();//세션 객체를 만듬
		
		//아이디 키값의 세션 아이디를 구함
		String mem_id=(String)session.getAttribute("mem_id");
		
		if(mem_id == null){  //세션 아이디 값이 없는 경우
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='Login.brn'");
			out.println("</script>");
			
		}else{	
			
			MemberBean member = service.isMember(mem_id);
			//디비로부터 회원정보 가져옴

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = format.parse(year+"-"+month+"-"+day);
			Date sqldate = new Date(date.getTime());
			String tel=tel1+"-"+tel2+"-"+tel3;
			String hp = hp1+"-"+hp2+"-"+hp3;
			
			
			
			member.setMem_id(mem_id);
			member.setMem_pw(pw);	
			member.setMem_sx(gender);	
			member.setMem_bd(sqldate);
			member.setMem_hp(hp); 	
			member.setMem_zc(zc);	
			member.setMem_add1(addr);
			member.setMem_nm(name);	
			member.setMem_add2(detailaddr);		
			member.setMem_ma(email+"@"+domain);	
			member.setMem_h(height);
			member.setMem_w(weight); 
			member.setMem_tel(tel);
			
			this.service.updateMember(member); //수정 메서드
			
			ModelAndView mv = new ModelAndView("html_membership/modifyView");
			mv.addObject("member",member);
			
			
			return mv;
			
		}
		return null;	
		
	}
	
	//워너비 사진 수정
	@RequestMapping(value="/Thumbnail_edit_ok.brn", method=RequestMethod.POST)
	public ModelAndView thumbnail_edit_ok(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		String rootPath = request.getSession().getServletContext().getRealPath("");
		
		String thumbTmpPath ="/upload/";
		String tmpFileName="";
		try {
			//임시 디렉토리가 없다면 생성
			File thumbDir = new File(rootPath+thumbTmpPath);
			
			if(!thumbDir.exists()){  //임시 디렉토리가 존재하지 않는다면
				thumbDir.mkdirs();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
		
	
	} 
	
	
	//회원정보 삭제 폼
	@RequestMapping(value="/mem_drop.brn")
	public ModelAndView mem_drop(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		PrintWriter out=response.getWriter();//출력 스트림 객체 생성
		session=request.getSession();//세션 객체 생성
		
		String mem_id=(String)session.getAttribute("mem_id");
		if(mem_id==null){//세션 아이디가 없는 경우
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='Login.brn'");
			out.println("</script>");
		}else{
			MemberBean member=this.service.isMember(mem_id);
					
			ModelAndView mv=new ModelAndView("html_membership/memDrop");
				
			mv.addObject("member",member);
				
			return mv;
			
		}
		
		return null;
		
	}
	
	//1단계 회원정보 삭제(아이디, 비번 확인)
	@RequestMapping(value="/mem_drop_ok.brn", method=RequestMethod.POST)
	public ModelAndView mem_drop_ok(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		PrintWriter out=response.getWriter();//출력 스트림 객체 생성
		session=request.getSession();//세션 객체 생성
		
		String mem_id=(String)session.getAttribute("mem_id");
		String mem_pw= request.getParameter("inputpw").trim();

		MemberBean isMember=this.service.isMember(mem_id);

		if(!isMember.getMem_id().equals(mem_id)){
			out.println("<script>");
			out.println("alert('아이디가 다릅니다!')");
			out.println("location='Login.brn'");
			out.println("</script>");
		
		}else{

			if(!isMember.getMem_pw().equals(mem_pw)){
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
			    out.println("</script>");	
			    
			}else{ //비번이 같은 경우
				
				
				ModelAndView mv=new ModelAndView("html_membership/memDrop2");
				
				mv.addObject("member",isMember);
				
				return mv;
			}
		}
		
		return null;
		
		
	}
	
	//2단계 회원정보 삭제(탈퇴 사유)
		@RequestMapping(value="/mem_drop_ok2.brn")
		public ModelAndView mem_drop_ok2(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
			
			PrintWriter out=response.getWriter();//출력 스트림 객체 생성
			
			String mem_id=(String)session.getAttribute("mem_id");
			
			
				
				
			String mem_rs1=request.getParameter("agree").trim();
			
			System.out.println(mem_rs1);
			
			String mem_rs2="";
			
			
			if(request.getParameter("rs_ect").trim() == null){
				
				mem_rs2= null;
			}else{
				
				mem_rs2= request.getParameter("rs_ect").trim(); 
			}

			
			System.out.println(mem_rs2);

			MemberBean isMember=this.service.isMember(mem_id);
			
			
			isMember.setMem_st(1);
			isMember.setMem_rs1(mem_rs1);
			isMember.setMem_rs2(mem_rs2);
			
			this.service.deleteMember(isMember);   //삭제 메서드 호출
			
			session.removeAttribute("mem_id");
			
			out.println("<script>");
			out.println("alert('회원 탈퇴되었습니다.')");
			out.println("location='Login.brn'");
			out.println("</script>");
			
			return null;
			
		}
		
		
		 //문의 메일 폼
		   @RequestMapping(value="/inquiry.brn")
		   public String inquiry(){
			   return "html_membership/inquiry";
		   }
		   
		   
		   //문의 메일 보내기
		   @RequestMapping(value="/inquiry_ok.brn",method=RequestMethod.POST)
		   public ModelAndView inquiry(
		         HttpServletRequest request,
		         HttpServletResponse response) throws Exception{
		      
		      
		       response.setContentType("text/html;charset=UTF-8");
		       PrintWriter out=response.getWriter();
		       ModelAndView mv = new ModelAndView("html_membership/login");

		    	  try{
		    		  
		    	  String mem_ma = request.getParameter("mail");

		    		  // 메일 관련 정보  ->SMTP 서버 주소를 지정합니다.(네이버인 경우)
		           String host = "smtp.naver.com";
		           
		           
		           //관리자의 메일 발신 전용 계정 메일.....
		           final String username = "ssheln";  //네이버 아이디     
		           int port=465;
		           final String password = "qweqwe123";   //네이버 이메일 비밀번호
		           

		           
		           // 이메일 내용
		           String title = request.getParameter("title");
		           String content = request.getParameter("content");
		            
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
		           
		           Session session1 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		               String un=username;
		               String pw=password;
		               protected PasswordAuthentication getPasswordAuthentication() {
		                   return new PasswordAuthentication(un, pw);
		               }
		           });
		           session1.setDebug(true); //for debug
		              
		           Message mimeMessage = new MimeMessage(session1);
		           
		           mimeMessage.setFrom(new InternetAddress("ssheln@naver.com"));
		           //발신자 셋팅, 보내는 사람의 이메일 주소를 한번 더 입력.. 이메일 풀 주소를 다 작성
		           
		           mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mem_ma));
		           //수신자 셋팅
		           
		           mimeMessage.setSubject(title);
		           //제목 셋팅
		           mimeMessage.setText(content);
		           //내용 셋팅
		           Transport.send(mimeMessage);

		           out.println("<script>");
			       out.println("alert('문의사항 메일 보내기에 성공하였습니다.')");
			       out.println("</script>");
			        
			      return mv;

			        } catch (Exception e) {
			        	e.printStackTrace();
			        	out.println("<script>");
						out.println("alert('문의사항 메일 보내기에 성공2하였습니다.')");
						out.println("history.go(-1)");  //오류 발생 시 뒤로 돌아감
						out.println("</script>");
					}
		         
		    	  return null;
		   }

		
		
		
}







