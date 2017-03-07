package com.burn.fat.member.action;

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

import com.burn.fat.member.dao.MemberService;
import com.burn.fat.member.model.MemberBean;
import com.burn.fat.member.model.ZipcodeBean;
import com.burn.fat.member.model.ZipcodeBean2;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class MemberAction {

	@Autowired  //@Autowired은 생성자나 메서드, 멤버변수 위에 모두 사용할 수 있습니다.
	//- @Autowired : 타입을 기준으로 빈을 찾아 주입하는 애노테이션
	//- @Resource : 이름을 기준으로 빈을 찾아 주입하는 애노테이션
	private MemberService memberService;
	private String saveFolder="C:/Users/user1/workspace/spring7_mvc_member/src/main/webapp/resources/upload";

	//ID중복 검사 ajax 함수로 처리 부분
	@RequestMapping(value="/member_idcheck.nhn")
	public void member_idcheck(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = response.getWriter();
		String id=request.getParameter("memid");
		
		int re=memberService.checkMemberId(id);
		
		out.print(re);
	}
	
	/*로그인 폼 뷰*/
	@RequestMapping(value="/member_login.nhn")
	public String member_login(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return "member/member_login";
		//member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
	/*  servlet-contex.xml에서 지정했기 때문에 아래의 문장을 생략 가능합니다.
	 *   <view-controller path="/pwd_find.nhn" 
	 *                    view-name="member/pwd_find"/>  
	 */
	/*비번찾기 폼*/
	/*@RequestMapping(value="/pwd_find.nhn")
	public String pwd_find(){
		return "member/pwd_find";
		//member 폴더의 pwd_find.jsp 뷰 페이지 실행
	}*/
	
	/*회원가입 폼*/
	@RequestMapping(value="/member_join.nhn")
	public String member_join(){
		return "member/member_join";
		//member 폴더의 member_join.jsp 뷰 페이지 실행
	}
	
	/*우편검색 폼*/
	@RequestMapping(value="/zipcode_find.nhn")
	public String zipcode_find(){
		return "member/zipcode_find";
		//member 폴더의 zipcode_find.jsp 뷰 페이지 실행
	}
	
	/*우편번호 DB로 부터 검색*/
	@RequestMapping(value="/zipcode_find_ok.nhn", method=RequestMethod.POST)
	public ModelAndView zicode_find_ok(@RequestParam String dong, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(dong);
		
		List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();
		
		//동을 기준으로 주소를 검색해서 컬렉션에 저장합니다.
		zipcodeList=this.memberService.findZipcode("%"+dong+"%");
		
		List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();
		//zipcodeBean 1, 2과 나누어짐(2는 자바빈과 동일, 1은 보여지는 화면 출력값과 동일)
		
		for(int i=0; i<zipcodeList.size();i++){
			ZipcodeBean2 zipcode_addr=zipcodeList.get(i);
			
			String zipcode=zipcode_addr.getZipcode();  //우편번호 저장
			String sido=zipcode_addr.getSido();  //서울시,경기도 저장
			String gugun=zipcode_addr.getGugun();//구, 군
			String dong2=zipcode_addr.getDong();//동
			String addr=sido+" "+gugun+" "+dong2;  //서울시 서초구 서초동
			
			ZipcodeBean zip=new ZipcodeBean();
			zip.setZipcode(zipcode);
			zip.setAddr(addr);
			
			//컬렉션에 주소를 저장합니다.
			zipcodeList2.add(zip);
		}
		
		ModelAndView zipcodeM= new ModelAndView("member/zipcode_find");
		zipcodeM.addObject("zipcodelist",zipcodeList2);
		
		zipcodeM.addObject("dong",dong);
		
		return zipcodeM;
	}
	
	
	/*비번찾기 완료 */
	@RequestMapping(value="/pwd_find_ok.nhn",method=RequestMethod.POST)
	public ModelAndView pwd_find_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
		String id=request.getParameter("id").trim();
		String name=request.getParameter("name").trim();
		
		Map pm=new HashMap();
		//컬렉션 Map에 키와 값  저장합니다.
		pm.put("id",id);
		pm.put("name",name);
		
		MemberBean member=this.memberService.findpwd(pm);
		
		if(member == null){//회원 아이디와 이름이 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디와 이름이 맞지 않습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		}else{
			ModelAndView pwdM=new ModelAndView("member/pwd_find");
			pwdM.addObject("pwdok",member.getMem_pw());
			return pwdM;
		}
		return null;
	}
	
	/*회원 가입 저장 */
	@RequestMapping(value="/member_join_ok.nhn",method=RequestMethod.POST)
	public ModelAndView member_join_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		MemberBean m=new MemberBean();
		
		int fileSize=5*1024*1024; //이진파일 업로드 최대 크기
		MultipartRequest multi=null;
		
		//첫번째 전달인자는 사용자 폼에서 입력한 정보를 서버로 가져오는 것
		//두번째 전달인자는 서버 이진파일 업로드 경로
		//세번째 전달인자는 업로드 최대 파일 크기
		//네번째 전달인자는 언어코딩 타입
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		String join_id=multi.getParameter("join_id").trim();//회원아이디
		String join_pwd=multi.getParameter("join_pwd1").trim();//회원비번
		String join_name=multi.getParameter("join_name").trim();//회원이름
		
		String join_zip1=multi.getParameter("join_zip1").trim();
		String join_zip2=multi.getParameter("join_zip2").trim();
		
		String join_addr1=multi.getParameter("join_addr1").trim();
		String join_addr2=multi.getParameter("join_addr2").trim();
		
		String join_tel1=multi.getParameter("join_tel1").trim();
		String join_tel2=multi.getParameter("join_tel2").trim();
		String join_tel3=multi.getParameter("join_tel3").trim();
		String join_tel=join_tel1+"-"+join_tel2+"-"+join_tel3;
		
		String join_phone1=multi.getParameter("join_phone1").trim();
		String join_phone2=multi.getParameter("join_phone2").trim();
		String join_phone3=multi.getParameter("join_phone3").trim();
        String join_phone=join_phone1+"-"+join_phone2+"-"+join_phone3;
        
        //메일 아이디
        String join_mailid=multi.getParameter("join_mailid").trim();
        
        //메일 도메인
        String join_maildomain=multi.getParameter("join_maildomain").trim();
        
        String join_email=join_mailid+"@"+join_maildomain;//메일 주소
        
        File UpFile=multi.getFile("join_profile");//첨부한 이진파일 가져옴

        if(UpFile != null){//첨부한 이진파일이 있다면
        	String fileName=UpFile.getName();//이진파일명 저장
        	Calendar c=Calendar.getInstance();//추상클래스로서 년월일 시분초 반환
        	int year=c.get(Calendar.YEAR);//년도값
        	int month=c.get(Calendar.MONTH)+1;//월값.+1을 한 이유는 1월이 0
        	int date=c.get(Calendar.DATE);
        	
            String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//새로운 폴더 저장
            File path1=new File(homedir);
            if(!path1.exists()){//새로운 폴더가 존재하지않으면
            	path1.mkdir();//새로운 폴더를 생성
            }
            
            Random r=new Random();
            int random=r.nextInt(100000000);//1억사이의 정수형 난수를 발생
            
            /****확장자 구하기 시작 ****/
			int index = fileName.lastIndexOf(".");
			//lastIndexOf("문자")는  String클래스의 메서드로 해당문자
			//를 문자열 끝 즉 우측에서 헤아려 문자의 위치번호를 반환한다.
			String fileExtension = fileName.substring(index + 1);
			//파일의 확장자를 구한다.
			/****확장자 구하기 끝 ***/
			 String refileName="MEMBER"+year+month+date+random+"."+
					fileExtension;//새로운 파일명을 저장
             String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
           
           //오라클 디비에 저장될 이름 변경
           UpFile.renameTo(new File(homedir+"/"+refileName));
           
           //변경될 파일명으로 저장
           m.setJoin_profile(fileDBName);
        }
           m.setJoin_id(join_id);      m.setJoin_pwd(join_pwd);
           m.setJoin_name(join_name);
           
           m.setJoin_zip1(join_zip1);  m.setJoin_zip2(join_zip2);
           
           m.setJoin_addr1(join_addr1); m.setJoin_addr2(join_addr2);
           
           m.setJoin_tel(join_tel);
           m.setJoin_phone(join_phone);
           m.setJoin_email(join_email);
           
           this.memberService.insertMember(m);
           
           //로그인 페이지로 이동
           response.sendRedirect("member_login.nhn");
          
		return null;
	}
	
	/*로그인 인증  */
	@RequestMapping(value="/member_login_ok.nhn",
			        method=RequestMethod.POST)
	public ModelAndView member_login_ok(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session)
					throws Exception{
		
		//HttpSession 클래스는 세션객체를 생성해서 로그인 인증 처리를 하기 위해서입니다.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//출력스트림 객체 생성
		session=request.getSession();//세션 객체 생성
		
		String id=request.getParameter("id").trim();
		String pwd=request.getParameter("pwd").trim();
		
		MemberBean m=this.memberService.userCheck(id);
		
		if(m==null){//등록되지 않은 회원일때
			out.println("<script>");
			out.println("alert('등록되지 않은 회원입니다!')");
			out.println("history.back()");
			out.println("</script>");
		}else{//등록된 회원일때
			if(m.getJoin_pwd().equals(pwd)){//비번이 같을때
				session.setAttribute("id",id);
				
				String join_name=m.getJoin_name();
				String join_profile=m.getJoin_profile();
				session.setAttribute("join_name",join_name);
				session.setAttribute("join_profile",join_profile);
				
				//jsp폴더의 view.jsp로 이동
				ModelAndView loginM=new ModelAndView("view");
				return loginM;
			}else{//비번이 다를때
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.go(-1)");
				out.println("</script>");
			}
		}
		return null;
	}
	
	/*회원정보 수정 폼 */
	@RequestMapping(value="/member_edit.nhn")
	public ModelAndView member_edit(HttpServletRequest request,
			HttpServletResponse response,HttpSession session)
					throws Exception{
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out=response.getWriter();
		    session=request.getSession();
		    
		    //아이디 키값의 세션아이디를 구함
		    String id=(String)session.getAttribute("id");
		   
		    
		    if(id==null){//세션 아이디 값이 없는 경우
		    	out.println("<script>");
		    	out.println("alert('다시 로그인 해주세요!')");
		    	out.println("location='member_login.nhn'");
		    	out.println("</script>");
		    }else{
		    	MemberBean editm=this.memberService.userCheck(id);
		    	
		    	String join_tel=editm.getJoin_tel();
		    	//java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		    	//두번째 -를 기준으로 문자열을 파싱해 줍니다.
		    	//집 전화번호 저장
		    	StringTokenizer st01=new StringTokenizer(join_tel,"-");
		    	String join_tel1=st01.nextToken();//첫번째(국번 집전화번호 저장)
		    	String join_tel2=st01.nextToken(); //두번째(가운데 자리)
		    	String join_tel3=st01.nextToken();//세번째(뒷 자리)
		    	
		    	String join_phone=editm.getJoin_phone();
		    	//java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		    	//두번째 -를 기준으로 문자열을  파싱해 줍니다.
		    	//휴대폰 번호 저장
		    	StringTokenizer st02=new StringTokenizer(join_phone,"-");
		    	String join_phone1=st02.nextToken();//첫번째 자리
		    	String join_phone2=st02.nextToken(); //두번째 자리
		    	String join_phone3=st02.nextToken();//세번째 자리 
		    	
		    	String join_email=editm.getJoin_email();
		    	//java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		    	//두번째 @를 기준으로 문자열을  파싱해 줍니다.
		    	StringTokenizer st03=new StringTokenizer(join_email,"@");
		    	String join_mailid=st03.nextToken();
		    	String join_maildomain=st03.nextToken();
		    			    	
		    	ModelAndView m=new ModelAndView("member/member_edit");
		    	m.addObject("editm",editm);
		    	m.addObject("join_tel1",join_tel1);
		    	m.addObject("join_tel2",join_tel2);
		    	m.addObject("join_tel3",join_tel3);
		    	m.addObject("join_phone1",join_phone1);
		    	m.addObject("join_phone2",join_phone2);
		    	m.addObject("join_phone3",join_phone3);
		    	m.addObject("join_mailid",join_mailid);
		    	m.addObject("join_maildomain",join_maildomain);
		    	
		    	return m;
		    }
			return null;
	}
	
	/*회원정보 수정 */
	@RequestMapping(value="/member_edit_ok.nhn",method=RequestMethod.POST)
	public ModelAndView member_edit_ok(
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();//세션객체를 만듬
		MemberBean member=new MemberBean();
		
		int fileSize=5*1024*1024;
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		String id=(String)session.getAttribute("id");
		//세션아이디값을 취득
		if(id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='member_login.nhn'");
			out.println("</script>");
		}else{
			String join_pwd=multi.getParameter("join_pwd1").trim();
			String join_name=multi.getParameter("join_name").trim();
			String join_zip1=multi.getParameter("join_zip1").trim();
			String join_zip2=multi.getParameter("join_zip2").trim();
			String join_addr1=multi.getParameter("join_addr1").trim();
			String join_addr2=multi.getParameter("join_addr2").trim();
			String join_tel1=multi.getParameter("join_tel1").trim();
			String join_tel2=multi.getParameter("join_tel2").trim();
			String join_tel3=multi.getParameter("join_tel3").trim();
			String join_tel=join_tel1+"-"+join_tel2+"-"+join_tel3;
			String join_phone1=multi.getParameter("join_phone1").trim();
			String join_phone2=multi.getParameter("join_phone2").trim();
			String join_phone3=multi.getParameter("join_phone3").trim();
			String join_phone=join_phone1+"-"+join_phone2+"-"+join_phone3;
			String join_mailid=multi.getParameter("join_mailid").trim();
		    String join_maildomain=multi.getParameter("join_maildomain").trim();
		    String join_email=join_mailid+"@"+join_maildomain;
		    
		    //아이디를 기준으로 디비로 부터 회원정보를 가져옵니다.
		    MemberBean editm=this.memberService.userCheck(id);
		   
		    File UpFile=multi.getFile("join_profile");//첨부한 이진파일을 가져옴
		    if(UpFile != null){
		    	String fileName=UpFile.getName();//이진파일명 저장
		    	File DelFile=new File(saveFolder+editm.getJoin_profile());
		    	if(DelFile.exists()){//기존이진파일이 존재하면
		    		DelFile.delete();//기존 이진파일명을 삭제
		    	}
		    	Calendar c=Calendar.getInstance();
		    	int year=c.get(Calendar.YEAR);
		    	int month=c.get(Calendar.MONTH)+1;//+1을 한 이유는 1월이 0으로 반환
		    	int date=c.get(Calendar.DATE);
		    	String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
                File path1=new File(homedir);
                if(!(path1.exists())){
                	path1.mkdir();//새로운 폴더를 생성
                }
                Random r=new Random();
                int random=r.nextInt(100000000);
                
                /****확장자 구하기 시작 ****/
    			int index = fileName.lastIndexOf(".");
    			String fileExtension = fileName.substring(index + 1);
    			/****확장자 구하기 끝 ***/
    			
    			String refileName="MEMBER"+year+month+date+random+"."+
    					fileExtension;//새로운 파일명을 저장
    			String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
                UpFile.renameTo(new File(homedir+"/"+refileName));
    			
                //파일명 저장
                member.setJoin_profile(fileDBName);
                System.out.println("바뀐 파일명은 = " + member.getJoin_profile());
		    }
		    member.setJoin_id(id);
		    member.setJoin_pwd(join_pwd);
		    member.setJoin_name(join_name);
		    member.setJoin_zip1(join_zip1);
		    member.setJoin_zip2(join_zip2);
		    member.setJoin_addr1(join_addr1);
		    member.setJoin_addr2(join_addr2);
		    member.setJoin_tel(join_tel);
		    member.setJoin_phone(join_phone);
		    member.setJoin_email(join_email);
		    
		    this.memberService.updateMember(member);//수정 메서드 호출
		    
		  //이미지 폴더가 바로 적용 안됩니다.
		    ModelAndView mv = new ModelAndView("view");
		    mv.addObject("join_name", member.getJoin_name() );
		    mv.addObject("join_profile", member.getJoin_profile() );
		    mv.addObject("state", "edit");
		    return mv;
		}
		return null;
	}
	
	/*회원정보 삭제 폼 */
	@RequestMapping(value="/member_del.nhn")
	public ModelAndView member_del(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//출력 스트림 객체 생성
		session=request.getSession();//세션 객체 생성
		
		String id=(String)session.getAttribute("id");
		if(id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='member_login.nhn'");
			out.println("</script>");
		}else{
			MemberBean deleteM=this.memberService.userCheck(id);
			
			ModelAndView dm=new ModelAndView("member/member_del");
			dm.addObject("d_id",id);
			dm.addObject("d_name",deleteM.getJoin_name());
			return dm;
		}
		return null;
	}
	
	/*회원정보 삭제 완료 */
	@RequestMapping(value="/member_del_ok.nhn",method=RequestMethod.POST)
	public ModelAndView member_del_ok(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		session=request.getSession();
		
		String id=(String)session.getAttribute("id");
		if(id==null){
			out.println("<script>");
			out.println("alert('다시 로그인 해주세요!')");
			out.println("location='member_login.nhn'");
			out.println("</script>");
		}else{
			String pass=request.getParameter("pwd").trim();
			String del_cont=request.getParameter("del_cont").trim();
			
			MemberBean member=this.memberService.userCheck(id);
			
			if(!member.getJoin_pwd().equals(pass)){
				out.println("<script>");
				out.println("alert('비번이 다릅니다!')");
				out.println("history.back()");
			    out.println("</script>");	
			}else{//비번이 같은 경우
				String fname=member.getJoin_profile();
				
				if(fname != null){//기존 이진파일이 존재하면
					File delFile=new File(saveFolder+fname);
					delFile.delete();//기존 이진파일을 삭제
				}
			 MemberBean delm=new MemberBean();
			 delm.setJoin_id(id);
			 delm.setJoin_delcont(del_cont);
			 
			 this.memberService.deleteMember(delm);//삭제 메서드 호출
			 
			 session.invalidate();//세션만료
			 
			 response.sendRedirect("member_login.nhn");
			}
		}
		return null;
	}
}


