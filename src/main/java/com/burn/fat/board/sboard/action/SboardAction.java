package com.burn.fat.board.sboard.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.sboard.dao.SboardService;
import com.burn.fat.board.sboard.model.SboardBean;
import com.oreilly.servlet.MultipartRequest;

@Controller("sboard")
public class SboardAction {
	
	@Autowired
	private SboardService service;
	private String saveFolder = "";
	
	@RequestMapping(value="/sboardWrite.brn")
	public String sboardwrite(){
		return "html_community/sboard/sboardWrite"; //html_community/sboard 폴더의 sboardWrite.jsp 뷰 페이지가 실행
	}
	
	/* 자료실 저장 */
	//  첨부파일 클릭할 경우 이미지 보고자 할  경우 : 자동 새로 고침 설정
	//(window-> Preferencs -> workspce -> 
	// Refresh using native hooks or polling 체크)
	//  하고 5초 정도 지난 뒤 확인하세요
	@RequestMapping(value="/sboardwrite_ok.nhn",
			method=RequestMethod.POST)
	public ModelAndView bbs_write_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		SboardBean bean=new SboardBean();		
		int fileSize=5*1024*1024; //이진파일 최대 업로드 크기	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		HttpSession session = request.getSession();
		
		String mem_id=(String) session.getAttribute("mem_id");
		String s_sj=multi.getParameter("s_sj").trim();
		String s_ct= multi.getParameter("s_ct").trim();
		
		File UpFile=multi.getFile("s_fl");
		if(UpFile != null){//첨부한 이진파일이 있다면
			String fileName=UpFile.getName();//이진파일명 저장
			System.out.println("fileName = " + fileName);
			Calendar c=Calendar.getInstance();
			int year=c.get(Calendar.YEAR);      //오늘 년도 구합니다.
			int month=c.get(Calendar.MONTH)+1;  //오늘 월 구합니다.
			int date=c.get(Calendar.DATE);      //오늘 일 구합니다.
			
            String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
            System.out.println("homedir = " + homedir);
            //upload폴더 아래에 파일 올린 날짜로 폴더 생성합니다.
            File path1=new File(homedir);
            if(!(path1.exists())){
            	path1.mkdir();//새로운 폴더를 생성
            }
            //난수를 구합니다.
            Random r=new Random();
            int random=r.nextInt(100000000);
            
            /****확장자 구하기 시작 ****/
			int index = fileName.lastIndexOf(".");
			//문자열에서 특정 문자열의 위치 값(index)를 반환한다.
			//indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			//lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			//(파일명에 점에 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			System.out.println("index = " +  index);
			
			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " +  fileExtension);
			/****확장자 구하기 끝 ***/
			//새로운 파일명을 저장
			String refileName="sboard"+year+month+date+random+"."+
					fileExtension;
			System.out.println("refileName = " + refileName);
			
			 //오라클 디비에 저장될 레코드 값
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            System.out.println("fileDBName = " + fileDBName);
           
            //파일명 변경합니다.
            UpFile.renameTo(new File(homedir+"/"+refileName));
            System.out.println("homedir / refileName  = " + homedir+"/"+
            refileName);
            
            if(fileExtension.equals("jpg") ||fileExtension.equals("png")||fileExtension.equals("gif") ){
            	bean.setS_gl(fileDBName);
            }else
            //바뀐 파일명으로  저장
            bean.setS_fl(fileDBName);
		}
		bean.setMem_id(mem_id);
		bean.setS_sj(s_sj);
		bean.setS_ct(s_ct);
		
		service.insertBbs(bean); //저장메서드 호출

		//자신이 쓴 글 세부 페이지로 보내는 방법 생각하기
		response.sendRedirect("sboardcont.brn");
		return null;
	}
	
	@RequestMapping("/sboardlist.brn")
	public ModelAndView sboardList(HttpServletRequest request, HttpServletResponse response)throws Exception{
	    
			int page = 1;
	 		int limit=20; // 목록보기 초기값
	 		HttpSession session = request.getSession();

	 		if(request.getParameter("page") != null){
						page=Integer.parseInt(request.getParameter("page"));
			}
	 		if(session.getAttribute("limit") != null){
	 			limit=(Integer) session.getAttribute("limit");
	 		}
	 		
	 		if(request.getParameter("limit") != null)
	 		{
	 			limit=Integer.parseInt(request.getParameter("limit"));
	 			session.setAttribute("limit", limit); //session에 limit를 저장하면 페이징처리시 쿼리스트링으로 limit값을 넘겨주지 않아도 된다.
	 		}
			int listcount=service.getListCount(); //총 리스트 수를 받아옴
					
			//총 페이지 수
			int maxpage = (listcount+limit-1)/limit;
					 		
			//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startpage = ((page-1) / 10) * 10 + 1;
					 		
			//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
			int endpage = startpage + 10 -1;
					   		
			if (endpage > maxpage) endpage= maxpage;
					   		
			if (endpage < page) page = endpage;  
			
			Map<String,Integer> m = new HashMap<String,Integer>();
			m.put("page", page);
			m.put("limit", limit);
			
					
			List<SboardBean> slist = service.getSboardList(m); 

				ModelAndView model=new ModelAndView("html_community/sboard/sboardList");
				model.addObject("page", page);
				model.addObject("limit", limit);
				model.addObject("maxpage", maxpage);
				model.addObject("startpage", startpage);
				model.addObject("endpage", endpage);
				model.addObject("listcount", listcount);
				model.addObject("slist", slist);
				return model;	
	}
	
	/* 자료실 내용보기, 수정폼, 삭제폼*/
	@RequestMapping(value="/sboardcont.brn")
	public ModelAndView sboardcont(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		int num=Integer.parseInt(request.getParameter("s_no"));
		
		int page=1;
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
			//parseInt()메서드로 정수형 숫자로 바꿔서 저장
		}
		String state=request.getParameter("state");//구분 필드		
		
		
		//번호를 기준으로 DB 내용을 가져옵니다.
		SboardBean bean=this.service.getSboardCont(num);
		
		ModelAndView contM=new ModelAndView();
		
		if(state.equals("cont")){//내용보기
			service.sboardHit(num);//조회수 증가
			contM.setViewName("html_community/sboard/sboardView");
			
			//글내용 중 엔터키 친부분을 다음줄로 개행 처리
			String s_ct=bean.getS_ct().replace("\n","<br/>");
			
			contM.addObject("s_ct",s_ct);
		}else if(state.equals("edit")){
			contM.setViewName("html_community/sboard/sboardEdit");
		}else if(state.equals("del")){//삭제일때
			contM.setViewName("html_community/sboard/sboard");
		}
		contM.addObject("sbean",bean);
		contM.addObject("page",page);
		return contM;
	}
	
	/* 자료실 수정 */
	@RequestMapping(value="/sboardedit_ok.brn",
			method=RequestMethod.POST)
	public ModelAndView sboardEdit_ok(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		SboardBean bean=new SboardBean();		
		int fileSize=5*1024*1024; //이진파일 최대 업로드 크기	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		HttpSession session = request.getSession();
		
		String mem_id=(String) session.getAttribute("mem_id");
		String s_sj=multi.getParameter("s_sj").trim();
		String s_ct= multi.getParameter("s_ct").trim();
		PrintWriter out=response.getWriter();
		
		
		int s_no=Integer.parseInt(multi.getParameter("s_no"));//번호
		int page=Integer.parseInt(multi.getParameter("page"));//쪽번호
		
		//디비로 부터 내용을 가져옴
		SboardBean bcont=this.service.getSboardCont(s_no);
		
		if(!bcont.getMem_id().equals(mem_id)){//작성자가 아니라면
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else{
		   File UpFile=multi.getFile("s_fl");
		   if(UpFile != null){//첨부한 이진파일이 있다면
			   String fileName=UpFile.getName();//첨부한 이진파일명 저장
			   File DelFile=new File(saveFolder+bcont.getS_fl());
			   if(DelFile.exists()) {
				   DelFile.delete();//기존 이진파일을 삭제
			   }
			   Calendar c=Calendar.getInstance();
			   int year=c.get(Calendar.YEAR);
			   int month=c.get(Calendar.MONTH)+1;
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
   			String refileName="bbs"+year+month+date+random+"."+
   					fileExtension;//새로운 파일명을 저장
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            
            UpFile.renameTo(new File(homedir+"/"+refileName));

            bean.setS_fl(fileDBName);
		   }
		   bean.setS_no(s_no);
		   bean.setMem_id(mem_id);
		   bean.setS_sj(s_sj);
		   bean.setS_ct(s_ct);
		   
		   this.service.editSboard(bean);//수정메서드 호출
		   
		 //get방식으로 3개의 파라미터 값이 넘어갑니다.
          response.sendRedirect(
        		"sboardView.brn?state=cont&page="+page+"&num="+s_no);
		}		
		return null;
	}
	
	/* 자료실 삭제 */
	@RequestMapping(value="/sboarddelete_ok.brn",method=RequestMethod.POST)
	public ModelAndView sboardDelete_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		int s_no=Integer.parseInt(request.getParameter("s_no"));
		int page=Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		String mem_id=(String) session.getAttribute("mem_id");
		
		//글번호에 해당하는 디비 내용을 가져옵니다.
		SboardBean bean=this.service.getSboardCont(s_no);
		
		//기존 파일명 가져옵니다.
		String fname=bean.getS_fl();
		
		if(!bean.getMem_id().equals(mem_id)){//글 작성자가 아니라면
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else{//비번이 같다면
			if(fname != null){//기존 이진파일이 존재한다면
				File file=new File(saveFolder+fname);
				file.delete();//서버 폴더로 부터 기존 이진파일 삭제합니다.
			}
			this.service.deleteSboard(s_no);//디비로 부터 레코드 삭제합니다.
			response.sendRedirect("sboardlist.brn?page="+page);
		}
		return null;
	}
	
	/* sever.xml 한글 처리 인코딩 설정 - get 방식 적용
	<Connector connectionTimeout="20000" port="8088" protocol="HTTP/1.1" 
		     redirectPort="8443" URIEncoding="UTF-8"/>*/
	/* 자료실 검색 목록 */
	@RequestMapping(value="/sboardfind_ok.nhn", method=RequestMethod.GET)
	public ModelAndView bbs_find_ok(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		    
		  	int page=1;
			int limit=20;
			
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
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("page", page);
			m.put("find_field", find_field);
			m.put("find_name", "%"+find_name+"%");			
			System.out.println(find_field);
			System.out.println(find_name);
			
			
			int listcount=this.service.getListCountSearch(m);
			System.out.println(listcount);
			
			
			//총 페이지 수
			int maxpage = (listcount+limit-1)/limit;
					 		
			//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startpage = ((page-1) / 10) * 10 + 1;
					 		
			//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
			int endpage = startpage + 10 -1;
					   		
			if (endpage > maxpage) endpage= maxpage;	
			 
			List<SboardBean> slist = service.getSboardListSearch(m); 
			
			ModelAndView model=new ModelAndView("bbs/bbs_find");
			model.addObject("find_name",find_name);
			model.addObject("find_field",find_field);			
			model.addObject("page", page);
			model.addObject("maxpage", maxpage);
			model.addObject("startpage", startpage);
			model.addObject("endpage", endpage);
			model.addObject("listcount", listcount);
			model.addObject("slist", slist);			
			
			return model;	
			//return null;
	}
	@RequestMapping(value="/find_order_date.nhn")
	public ModelAndView find_order_date(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			
			int page = 1;
			int limit =10;
			if(request.getParameter("page")!=null)
				page=Integer.parseInt(request.getParameter("page"));
			String orderdate = "";
			if(request.getParameter("orderdate")!=null){
				orderdate=request.getParameter("orderdate");
			}
				orderdate=request.getParameter("orderdate");
			String find_name=null;
			if(request.getParameter("find_name")!=null){
				find_name=request.getParameter("find_name").trim();
			}
			String find_field = null;
			if(request.getParameter("find_field")!=null){
				find_field = request.getParameter("find_field").trim();
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("page", page);
			m.put("find_field", find_field);
			m.put("find_name", "%"+find_name+"%");		
			
			int listcount=this.service.getListCountSearch(m);
			
			m.put("orderdate", orderdate);
			
			//총 페이지 수
			int maxpage = (listcount+limit-1)/limit;
					 		
			//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startpage = ((page-1) / 10) * 10 + 1;
					 		
			//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
			int endpage = startpage + 10 -1;
					   		
			if (endpage > maxpage) endpage= maxpage;	
			 
			List<SboardBean> slist = service.getSboardListSearch(m);
			
			ModelAndView model=new ModelAndView("bbs/bbs_find");
			model.addObject("find_name",find_name);
			model.addObject("find_field",find_field);			
			model.addObject("page", page);
			model.addObject("maxpage", maxpage);
			model.addObject("orderdate", orderdate);
			model.addObject("startpage", startpage);
			model.addObject("endpage", endpage);
			model.addObject("listcount", listcount);
			model.addObject("slist", slist);			
			
			return model;	
			
			
	}
	
}
