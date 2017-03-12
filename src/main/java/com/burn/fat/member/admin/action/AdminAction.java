package com.burn.fat.member.admin.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Controller("Adminaction")
public class AdminAction {
	
	@Autowired
	private AdminService service;

	
	//회원관리 리스트 
	@RequestMapping(value="/memlist.brn")
	public ModelAndView mem_modify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 int page = 1;
         //int limit=10; // 한 화면에 출력할 레코드 갯수
         int limit=10;   //목록보기 초기값
         HttpSession session = request.getSession();
         
         if(request.getParameter("page") != null){
                 page=Integer.parseInt(request.getParameter("page"));
        }
         
         //추가
         //이전에 설정된 limit가 있는지 체크
         if(session.getAttribute("limit")!=null) {
            limit = (Integer)session.getAttribute("limit");
         }
         //변경된 limit가 있는지 체크
         if(request.getParameter("limit")!=null) {
            limit=Integer.parseInt(request.getParameter("limit"));
            session.setAttribute("limit", limit);
         }
         
         System.out.println("limit = " + limit);
         System.out.println("page = " + page);
         //여기까지 추가
         
        //총 리스트 수를 받아옴.               
        int listcount=this.service.getListCount(); //총 리스트 수를 받아옴
              
        //총 페이지 수
        int maxpage = (listcount+limit-1)/limit;
                     
        //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
        int startpage = ((page-1) / 10) * 10 + 1;
                     
        //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
        int endpage = startpage + 10 -1;
                       
        if (endpage > maxpage) endpage= maxpage;
                       
        if (endpage < page) page = endpage;  
        
        Map m = new HashMap();
        m.put("page", page);
        m.put("limit", limit);
        //리스트 받아옴
        List<MemberBean> member = service.getMemList(m);//page,limit를 보냄
     
        ModelAndView model=new ModelAndView("html_membership/memList");
      model.addObject("page", page);
      model.addObject("maxpage", maxpage);
      model.addObject("startpage", startpage);
      model.addObject("endpage", endpage);
      model.addObject("listcount", listcount);
      model.addObject("member", member);
      model.addObject("limit", limit);
      
      return model;      
	}
	
	//회원관리 뷰 
	@RequestMapping(value="/mem_cont.brn")
    public ModelAndView mem_cont(
          HttpServletRequest request,
          HttpServletResponse response,@RequestParam(value="num") int num
          ) throws Exception{
       
        
        int page=1;
        if(request.getParameter("page") != null){
           page=Integer.parseInt(request.getParameter("page"));
           //parseInt()메서드로 정수형 숫자로 바꿔서 저장
        }
        
        MemberBean member =this.service.getMemCont(num);
        
        ModelAndView model=new ModelAndView("html_membership/modifyView");
        
        model.addObject("member",member);
        
		return model;
	}
	
	//회원 검색 mem_find_ok.brn
	@RequestMapping(value="/mem_find_ok.brn", method=RequestMethod.GET)
    public ModelAndView mem_find_ok(
          HttpServletRequest request,
          HttpServletResponse response
          ) throws Exception{
           
          int page=1;
          int limit=10;
          
          if(request.getParameter("page")!=null){
             page=Integer.parseInt(request.getParameter("page"));
          }
          
          String find_name=null;         
          if(request.getParameter("find_name").trim() != null){
             find_name=request.getParameter("find_name").trim();             
          }
          
          String find_field=null;
          if(request.getParameter("find_field")!=null){
             find_field=request.getParameter("find_field").trim();
          }   
          
          Map m = new HashMap();
          m.put("page", page);
          m.put("find_field", find_field);   
          m.put("find_name", "%"+find_name+"%");   //%는 쿼리에서 포함된 문자열을 찾아라         

          
          
          int listcount=this.service.getListCount_find(m);
          System.out.println(listcount);
          
          
          //총 페이지 수
          int maxpage = (listcount+limit-1)/limit;
                       
          //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
          int startpage = ((page-1) / 10) * 10 + 1;
                       
          //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
          int endpage = startpage + 10 -1;
                         
          if (endpage > maxpage) endpage= maxpage;   
           
          List<MemberBean> member = service.getMemList_find(m); 
          
          ModelAndView model=new ModelAndView("html_membership/memList");
          model.addObject("find_name",find_name);
          model.addObject("find_field",find_field);         
          model.addObject("page", page);
          model.addObject("maxpage", maxpage);
          model.addObject("startpage", startpage);
          model.addObject("endpage", endpage);
          model.addObject("listcount", listcount);
          model.addObject("member", member);         
          
          return model;   
          //return null;
    }
	
	
	//회원관리 수정 폼
	@RequestMapping(value="/manage_edit.brn")
	public ModelAndView manage_edit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		response.setContentType("text/html;charset=UTF-8");
		
	    String id = request.getParameter("mem_id");  //회원 아이디
	    
	    MemberBean member = service.isMem(id);
	    
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
	
	
	//회원 관리 수정 
	@RequestMapping(value="/manage_edit_ok.brn", method=RequestMethod.POST)
	public ModelAndView manage_edit_ok(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="inputid")String mem_id
			,@RequestParam(value="inputpw")String pw, @RequestParam(value="inputname")String name, @RequestParam(value="gender")int gender
			,@RequestParam(value="birthyear")int year, @RequestParam(value="birthmonth")int month,@RequestParam(value="birthday")int day
			,@RequestParam(value="mobileNo1")String hp1
			,@RequestParam(value="middleph")String hp2, @RequestParam(value="lastph")String hp3,@RequestParam(value="zipcode")int zc
			,@RequestParam(value="address")String addr, @RequestParam(value="mem_tel1")String tel1, @RequestParam(value="mem_tel2")String tel2
			,@RequestParam(value="mem_tel3")String tel3
			,@RequestParam(value="detailaddr")String detailaddr, @RequestParam(value="email")String email, @RequestParam(value="domain")String domain
			,@RequestParam(value="height")double height, @RequestParam(value="weight")double weight) throws Exception{
		
		
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out=response.getWriter();
		
	    MemberBean member = service.isMem(mem_id);
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
	    
	  	this.service.manage_edit(member);
	  	
	  	out.print("<script>");
		out.print("alert('회원정보가 수정되었습니다!');");
	    out.print("</script>");
	    
	    ModelAndView mv = new ModelAndView("html_membership/memList");
	    
	    mv.addObject("member",member);
	    
	    return mv;
	}
	
	
	//회원관리 삭제
	@RequestMapping(value="/manage_delete_ok.brn")
	public String manage_delete_ok(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		PrintWriter out=response.getWriter();//출력 스트림 객체 생성
		String mem_id = request.getParameter("mem_id");
		
		int mem_st =Integer.parseInt(request.getParameter("mem_st"));
		
		out.print(mem_id);
		out.println(mem_st);
		
		if(mem_st ==0){
			this.service.manage_delete(mem_id);
			
			out.print("<script>");
			out.print("alert('회원 탈퇴되었습니다.');");
			out.print("</script>");
		}else{
			out.print("<script>");
			out.print("alert('이미 회원 탈퇴되었습니다.');");
			out.print("</script>");
		}
		
		
		
		
		return "html_membership/memList";
		
		
	}

}
