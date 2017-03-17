package com.burn.fat.board.gboard.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.gboard.dao.GboardService;
import com.burn.fat.board.gboard.dao.GcommService;
import com.burn.fat.board.gboard.image.Thumbnail;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.gboard.model.GcommBean;
import com.oreilly.servlet.MultipartRequest;// cos.jar 파일을 dependency에 추가해준다.
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class GbbsAction {


	// 생성자, 필드, 메소드에서 사용이 가능하다
	// 설정만해도 setter와 관계없이 해당하는 타입을 자동으로 등록해주기 때문에 편리하게 사용할수있다.
	@Autowired
	private GboardService gbbsService; 
	@Autowired
	private GcommService gcommservice;
	// DAO클래스가 바뀌면 해당하는 컨트롤러를 다 바꿔줘야하기 떄문에 ,
	// DAO클래스만 변경해서 사용할 수 있는 방법을 생각 => 다형성을 이용한다(Autowired)
	////private String saveFolder ="C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/myapp/resources/upload";
	private String saveFolder="C:/gitwork/FinalProject/src/main/webapp/resources/upload";

	/*자료실 입력폼*/
	@RequestMapping(value="/gbbs_write.brn")
	public String gbbs_write(){
		return "html_community/gboard/gbbs_write"; 
	}
	
	/* 경고창! */
	@RequestMapping(value="/alert_page.brn")
	public String alert_page(){
		return "html_community/gboard/alert_page"; 
	}
	/* 자료실 저장 */
	
	// 첨부파일을 클릭할 경우, 이미지 보고자할 경우, 자동 새로고침 설정 window-> prefernce -> workspace ->  Refresh using native hooks or polling  체크
	// 하고 5초 정도 지난 후 확인하세요.
	
	/* 자료실 목록 */
	@RequestMapping(value="/gbbs_write_ok.brn", method=RequestMethod.POST)
	public ModelAndView gbbs_write_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		GbbsBean gbbsbean = new GbbsBean();
		int sizeLimit = 50*1024*1024; // 이진파일 최대 업로드 
		MultipartRequest multi2 = null;
		multi2 = new MultipartRequest(request, saveFolder, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

		int gbbs_mem_no = Integer.parseInt(multi2.getParameter("gbbs_mem_no"));
		String gbbs_subject = multi2.getParameter("gbbs_subject").trim();
		String gbbs_content = multi2.getParameter("gbbs_content").trim();

		Enumeration formNames;
	
		try {
			
			formNames=multi2.getFileNames();  // 폼의 이름 반환

			String formName = "";
			String fileDBNames ="";
			
			while (formNames.hasMoreElements()) {

				formName=(String)formNames.nextElement();
				String fileName = multi2.getFilesystemName(formName); // 파일의 이름 얻기(업로드된 파일명)
				
				Calendar c = Calendar.getInstance();
					
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;
				int date = c.get(Calendar.DATE);

				String homedir = saveFolder + "/" + year + "-" + month + "-" + date ;  
				System.out.println("homedir = " + homedir);
	
				// upload 폴더 아래에 파일을 올린 날짜로 폴더 생성합니다.  
				File path1 = new File(homedir);
				
				if(!(path1.exists())){
					path1.mkdirs(); 	// 새로운 폴더를 생성
				}
				
				// 난수를 구합니다.
				Random r = new Random();
				int random = r.nextInt(100000000);
				
				/*확장자 구하기 시작*/
				
				int index = fileName.lastIndexOf(".");
				/* 문자열에서 특정 문자열의 위치값(index)를 반환합니다
				indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
				lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
				파일명에 점이 여러개 있을 경우에 맨 마지막에 발견되는 문자열의 위치를 리턴합니다. */
				
				System.out.println("index = " + index);
				
				String fileExtension = fileName.substring(index+1);
				System.out.println("file Extension = " + fileExtension );
				
				/* 확장자 구하기 끝 */
				// 새로운 파일명을 저장
				String refileName = "gbbs" + year + month + date + random + "." + fileExtension; 
				System.out.println("refileName : " + refileName);
				
				// 오라클 디비에 저장될 레코드 값
				String fileDBName = homedir + "/" + refileName; 
				String fileDBName_thumb = homedir + "/sm" + refileName; 
				System.out.println("오라클 디비에 저장될 레코드 값" + fileDBName);
				System.out.println("fileName : " + fileName);
				
				File f = new File(saveFolder+File.separator+fileName);  // 서버에 업로드된 파일객체 (디렉토리포함)
				f.renameTo(new File(fileDBName));
				long fileSize = f.length(); // 파일크기 (bytes)
				   
				fileDBNames += ";"  + year + "-" + month + "-" + date + "/" + refileName ;
				
				String orgFileName =  fileDBName;
				String thumbFileName = fileDBName_thumb;
				System.out.println("homedir:" + homedir);
				System.out.println("refileName:" + refileName);
				Thumbnail.createImage(orgFileName, thumbFileName, 5);
			}
			
			System.out.println(fileDBNames);
			gbbsbean.setGbbs_file(fileDBNames);
			
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		gbbsbean.setGbbs_mem_no(gbbs_mem_no);
		gbbsbean.setGbbs_subject(gbbs_subject);
		gbbsbean.setGbbs_content(gbbs_content);

		this.gbbsService.insertGbbs(gbbsbean);
		response.sendRedirect("gbbs_gall.brn");
		
		return null;
	
	}
	
	/* 자료실 목록 */
	@RequestMapping(value="/gbbs_gall.brn")
	public ModelAndView gbbs_gall(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="limit", defaultValue="20") int limit) throws Exception{

		int page = 1;
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

		int listcount = gbbsService.getGlistCount(); //총 리스트 수를 
		
		//총 페이지 수
		int maxpage = (listcount+limit-1)/limit;
				 		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page-1) / limit) * limit + 1;

		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + limit -1;
		if (endpage > maxpage) endpage= maxpage;
		if (endpage < page) page = endpage;  

		Map m = new HashMap();
		m.put("listcount", listcount);
		m.put("page", page);
		m.put("limit", limit);
		/* Gbbs.xml에서 parameterType 
		   Gbbs리스트가 map을 매개변수로 받아오게 만들어야한다 */

		// 리스트를 
		List<GbbsBean> Gbbsgall = gbbsService.getGbbsGall(m);
		ModelAndView model = new ModelAndView("html_community/gboard/gbbs_gall");
		
		model.addObject("page", page);
		model.addObject("maxpage", maxpage);
		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("Gbbsgall", Gbbsgall);
		model.addObject("limit", limit);
		
		System.out.println("listcount:"+listcount+"\tpage:"+page+"\tlimit:"+limit);
		return model;	
	
	}

	/* 자료실 내용보기, 수정폼, 답변글폼, 삭제폼*/
	@RequestMapping(value="/gbbs_cont.brn")
	//public ModelAndView gbbs_cont(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="num", required=true) int gbbs_num, @RequestParam(value="nnt", required=true) int nnt, @RequestParam(value="npv", required=true) int npv ) throws Exception{
	public ModelAndView gbbs_cont(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="num", required=true) int gbbs_num) throws Exception{

		int num = Integer.parseInt(request.getParameter("num")); 
		
		int page=1; 

		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));  //parseInt()메서드로 정수형 숫자로 바꿔서 저장
		}
		   
		String state = request.getParameter("state");  //구분 필드		

		if(state.equals("cont")){
			this.gbbsService.gbbsHit(num);  //조회수 
		}
		
		//번호를 기준으로 DB 내용을 가져옵니다.
		GbbsBean gbbsbean = this.gbbsService.getGbbsCont(num); 
		
		ModelAndView contM = new ModelAndView();
		
		contM.setViewName("html_community/gboard/gbbs_cont");
		
		if(state.equals("cont")){//내용보기
			
			contM.setViewName("html_community/gboard/gbbs_cont");  //글내용 중 엔터키 친부분을 다음줄로 개행 처리
			System.out.println( gbbsbean.getGbbs_content());
			String gbbs_cont = gbbsbean.getGbbs_content().replace("\n","<br/>");
			contM.addObject("gbbs_cont", gbbs_cont);
		
		} else if(state.equals("edit")){
			contM.setViewName("html_community/gboard/gbbs_edit");
		} else if(state.equals("del")){  //삭제일때
			contM.setViewName("html_community/gboard/gbbs_delete");
		} else if(state.equals("reply")){  //답변글
			contM.setViewName("html_community/gboard/gbbs_reply");
		} else if(state.equals("like")){  //좋아요
			contM.setViewName("html_community/gboard/gbbs_like_ok");
		}
		
		List<GcommBean> gbeanlist = new ArrayList<GcommBean>();
		gbeanlist = gcommservice.getCommList(gbbs_num); 
		
		if(request.getParameter("rownum")!=null){
 
			int rownum = Integer.parseInt(request.getParameter("rownum")); 
			int listcount = Integer.parseInt(request.getParameter("listcount")); 
			
			int gn = listcount-rownum+1;
			if(rownum!=listcount){
				GbbsBean gbbsbean_prev =  this.gbbsService.getGbbsContpage(gn-1); 
				contM.addObject("gbbsbean_prev", gbbsbean_prev); //다음글 	
			}
			
			if(rownum!=1){
				GbbsBean gbbsbean_next =  this.gbbsService.getGbbsContpage(gn+1); 
				contM.addObject("gbbsbean_next", gbbsbean_next); //이전글
			}
		}
		
		
		contM.addObject("gbbsbean", gbbsbean); 
		
		contM.addObject("page", page); 
		contM.addObject("beanlist",gbeanlist);   	 	// 댓글 갯수
		
		return contM;
	
	}
	
	/* 자료실 수정 */
	@RequestMapping(value="/gbbs_edit_ok.brn", method=RequestMethod.POST)
	public ModelAndView gbbs_edit_ok(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		GbbsBean gbbsbean = new GbbsBean();
		int sizeLimit = 50*1024*1024; // 이진파일 최대 업로드 
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		String fileDBNames_before = multi.getParameter("filename_before");
		String resultfileDBNames = fileDBNames_before ;
	
		try {

			int gbbs_num = Integer.parseInt(multi.getParameter("gbbs_num"));
			int gbbs_mem_no = Integer.parseInt(multi.getParameter("gbbs_mem_no"));
			String gbbs_subject = multi.getParameter("gbbs_subject").trim();
			String gbbs_content = multi.getParameter("gbbs_content").trim();
			String[] img_del = multi.getParameterValues("img_del"); 
			
			Enumeration formNames = multi.getFileNames();  // 폼의 이름 반환
			
			while (formNames.hasMoreElements()) {
				
				String formName = "";
				String fileDBNames ="";
				
				formName=(String)formNames.nextElement();
				String fileName = multi.getFilesystemName(formName); // 파일의 이름 얻기(업로드된 파일명)
				
				Calendar c = Calendar.getInstance();
					
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;
				int date = c.get(Calendar.DATE);

				String homedir = saveFolder + "/" + year + "-" + month + "-" + date ;  
				System.out.println("homedir = " + homedir);
	
				// upload 폴더 아래에 파일을 올린 날짜로 폴더 생성합니다.  
				File path1 = new File(homedir);
				
				if(!(path1.exists())){
					path1.mkdirs(); 	// 새로운 폴더를 생성
				}
				
				// 난수를 구합니다.
				Random r = new Random();
				int random = r.nextInt(100000000);
				
				System.out.println("fileName" + fileName);
				/*확장자 구하기 시작*/
				int index = fileName.lastIndexOf(".");
				/* 문자열에서 특정 문자열의 위치값(index)를 반환합니다
				indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
				lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
				파일명에 점이 여러개 있을 경우에 맨 마지막에 발견되는 문자열의 위치를 리턴합니다. */
				
				System.out.println("index = " + index);
				
				String fileExtension = fileName.substring(index+1);
				System.out.println("file Extension = " + fileExtension );
				
				/* 확장자 구하기 끝 */
				// 새로운 파일명을 저장
				String refileName = "gbbs" + year + month + date + random + "." + fileExtension; 
				System.out.println("refileName : " + refileName);
				
				// 오라클 디비에 저장될 레코드 값
				String fileDBName = homedir + "/" + refileName; 
				String fileDBName_thumb = homedir + "/sm" + refileName; 
				System.out.println("오라클 디비에 저장될 레코드 값" + fileDBName);
				System.out.println("fileName : " + fileName);
				
				File f = new File(saveFolder+File.separator+fileName);  // 서버에 업로드된 파일객체 (디렉토리포함)
				f.renameTo(new File(fileDBName));
				long fileSize = f.length(); // 파일크기 (bytes)
				   
				fileDBNames += ";"  + year + "-" + month + "-" + date + "/" + refileName ;
				
				String orgFileName =  fileDBName;
				String thumbFileName = fileDBName_thumb;
				System.out.println("homedir:" + homedir);
				System.out.println("refileName:" + refileName);
				Thumbnail.createImage(orgFileName, thumbFileName, 5);
				
				resultfileDBNames += fileDBNames;
				
			}
			
			// 원래파일 + 새로운파일
			if(img_del!=null){
				
				// 삭제할 파일을 replace로 하나씩 지워줍니다.
				for(int i=0; i<img_del.length; i++){
						
					System.out.println("지울파일" + img_del[i]);
					String delfileDBName = ";" + img_del[i].replaceAll("sm", "");
					System.out.println(delfileDBName);
					// 원래파일 + 새로운파일 - 지울파일
					System.out.println("resultfileDBNames 전 : " + resultfileDBNames); 
					// DB에서 지워줌.
					resultfileDBNames = resultfileDBNames.replace(delfileDBName, "");
					System.out.println("resultfileDBNames 후 : " + resultfileDBNames);
					
					System.out.println(saveFolder);
					String delfile = saveFolder+delfileDBName;
					System.out.println("삭제할 파일주소  : " + delfile);
					File fd = new File(delfileDBName);
					File fdsmall = new File(delfileDBName.replace("gbbs","smgbbs") );
					fd.delete();		// 파일삭제
					fdsmall.delete();	// 썸네일삭제
				
				}
			}
			
			gbbsbean.setGbbs_file(resultfileDBNames);
			gbbsbean.setGbbs_num(gbbs_num);
			gbbsbean.setGbbs_subject(gbbs_subject);
			gbbsbean.setGbbs_content(gbbs_content);
	
			this.gbbsService.editGbbs(gbbsbean);
			   
			 //get방식으로 3개의 파라미터 값이 넘어갑니다.
			response.sendRedirect("gbbs_gall.brn");
			//response.sendRedirect("gbbs_cont.brn?state=cont&page="+page+"&num="+gbbs_num);
					
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		return null; 
		   
	}
	
	/* 자료실 삭제 */
	@RequestMapping(value="/gbbs_delete_ok.brn",method=RequestMethod.POST)
	public ModelAndView gbbs_delete_ok( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int gbbs_num=Integer.parseInt(request.getParameter("num"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//글번호에 해당하는 디비 내용을 가져옵니다.
		GbbsBean gbbsbean=this.gbbsService.getGbbsCont(gbbs_num);
		
		//기존 파일명 가져옵니다.
		String fname = gbbsbean.getGbbs_file();
		
		if(fname != null){//기존 이진파일이 존재한다면
				File file=new File(saveFolder+fname);
				file.delete();//서버 폴더로 부터 기존 이진파일 삭제합니다.
		}
		
		this.gbbsService.deleteGbbs(gbbs_num);//디비로 부터 레코드 삭제합니다.
		response.sendRedirect("gbbs_gall.brn?page="+page);
			
		return null;
	}
	
	@RequestMapping(value="/gbbs_like_ok.brn")
	public ModelAndView gbbs_like_ok( HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		int gbbs_num=Integer.parseInt(request.getParameter("num"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//글번호에 해당하는 디비 내용을 가져옵니다.
		GbbsBean gbbsbean=this.gbbsService.getGbbsCont(gbbs_num);

		this.gbbsService.gbbsLike(gbbs_num);//디비로 부터 좋아요수를 추가합니다
		response.sendRedirect("gbbs_cont.brn?page=" + page + "&num=" + gbbs_num + "&state=cont");
		return null;
	}
	
	
	/* sever.xml 한글 처리 인코딩 설정 - get 방식 적용
	<Connector connectionTimeout="20000" port="8088" protocol="HTTP/1.1" 
		     redirectPort="8443" URIEncoding="UTF-8"/>*/
	/* 자료실 검색 목록 (검색을 눌렀을 경우 )*/
	@RequestMapping(value="/gbbs_find_ok.brn", method=RequestMethod.GET)
	   public ModelAndView gbbs_find_ok( HttpServletRequest request,  HttpServletResponse response, @RequestParam(value="limit", defaultValue="10") int limit ) throws Exception{

		  	int page=1;
			System.out.println("limit:" + limit );
			
			if(request.getParameter("page")!=null){
				page=Integer.parseInt(request.getParameter("page"));
			}
	         
	         String gfind_name=null;         
	         if(request.getParameter("gfind_name").trim() != null){
	            gfind_name=request.getParameter("gfind_name").trim();             
	         }

	         String gfind_field=null;
	         if(request.getParameter("gfind_field")!=null){
	            gfind_field=request.getParameter("gfind_field").trim();
	         }   
	         
	         Map m = new HashMap();
	         m.put("limit", limit);
	         m.put("page", page);
	         m.put("gfind_field", gfind_field);
	         m.put("gfind_name", "%"+gfind_name+"%");         
	         
	         System.out.println(gfind_field);
	         System.out.println(gfind_name);
	         
	         
	         int listcount = this.gbbsService.getGlistCount2(m);
	         System.out.println(listcount);
	         
	         //총 페이지 수
	         int maxpage = (listcount+limit-1)/limit;
	         //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
	         int startpage = ((page-1) / limit) * limit + 1;
	         //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
	         int endpage = startpage + limit -1;
	         if (endpage > maxpage) endpage= maxpage;  
	          
	         List<GbbsBean> gbbslist = gbbsService.getGbbsGall2(m); 
	         
	         ModelAndView model=new ModelAndView("html_community/gboard/gbbs_find");
	         model.addObject("gfind_name",gfind_name);
	         model.addObject("gfind_field",gfind_field);         
	         model.addObject("page", page);
	         model.addObject("limit", limit);
	         model.addObject("maxpage", maxpage);
	         model.addObject("startpage", startpage);
	         model.addObject("endpage", endpage);
	         model.addObject("listcount", listcount);
	         model.addObject("gbbslist", gbbslist);         
	         
	         return model;   
	         
	   }
	
	/* sorting 순서 */
	   @RequestMapping(value="/gfind_order_date.brn",
	         method=RequestMethod.GET)
	   public ModelAndView gfind_order_date(
				HttpServletRequest request, HttpServletResponse response, @RequestParam(value="limit", defaultValue="10") int limit ) throws Exception{
			    
		  int page=1;
			
		  if(request.getParameter("page")!=null){
			  page=Integer.parseInt(request.getParameter("page"));
		  }
		  
		  String gorderdate = "";
		  if(request.getParameter("gorderdate")!=null){
			  gorderdate=request.getParameter("gorderdate");
		  }
				
	      String gfind_name=null;         
	      if(request.getParameter("gfind_name").trim() != null){
	         gfind_name=request.getParameter("find_name").trim();             
	      }
	         
	      String gfind_field=null;
	      if(request.getParameter("gfind_field")!=null){
	         gfind_field=request.getParameter("gfind_field").trim();
	         //검색 필드 저장
	      }
	      //System.out.println(find_name);
	         
	      Map m = new HashMap();
	      m.put("page", page);
	      m.put("gfind_field", gfind_field);
	      m.put("gfind_name", "%" + gfind_name+"%");
	      
	      int listcount=this.gbbsService.getGlistCount2(m);
	      System.out.println("listcount="+listcount);
	      
	      if(request.getParameter("gorderdate")!=null){
	         gorderdate=request.getParameter("gorderdate");
	      }
	      
	      m.put("gorderdate", gorderdate);
	      List<GbbsBean> Gbbslist = gbbsService.getGbbsGall2(m);
	      
	      //총 페이지 수
	      int maxpage = (listcount+limit-1)/limit;
	                   
	      //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
	      int startpage = ((page-1) / limit) * limit + 1;
	                   
	      //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
	      int endpage = startpage + limit -1;
	                     
	      if (endpage > maxpage) endpage= maxpage;   
	       
	      ModelAndView model=new ModelAndView("html_community/gboard/gbbs_find");
	      
	      model.addObject("page", page);
	      model.addObject("maxpage", maxpage);
	      model.addObject("startpage", startpage);
	      model.addObject("endpage", endpage);
	      model.addObject("listcount", listcount);
	      model.addObject("gbbslist", Gbbslist);
	      model.addObject("gorderdate", gorderdate);
	      model.addObject("gfind_name", gfind_name);
	      model.addObject("gfind_field", gfind_field); 

	      return model;

	   }
 
}