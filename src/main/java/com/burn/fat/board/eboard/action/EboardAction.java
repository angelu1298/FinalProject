package com.burn.fat.board.eboard.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.eboard.dao.EboardService;
import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.eboard.model.EcommBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class EboardAction {
	
	@Autowired
	public EboardService eboService;
	
	private String saveFolder="D:/neonjava/EBoard/src/main/webapp/resources/upload";
	
	/* 자료실 입력폼 */
	@RequestMapping(value="/eboardWrite.brn")
	public String eboInsert(){
		return "html_community/eboard/eboardWrite";
	}
	
	/* 글 수정폼 */
	/*@RequestMapping(value="eboardEdit.brn")
	public String eboUpdate(){
		return "html_community/eboardEdit";
	}*/
	
	/* 자료실 저장 */
	@RequestMapping(value="/ebo_write_ok.brn", method=RequestMethod.POST)
	public ModelAndView ebo_write_ok(
						HttpServletRequest request, 
						HttpServletResponse response) throws Exception{
		
		EboardBean ebobean=new EboardBean();
		int fileSize=5*1024*1024; //이진파일 최대 업로드 크기	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String id = (String)session.getAttribute("id");
		
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		int result = 0;
		
		String e_sj=multi.getParameter("e_sj").trim();
		String e_ct=multi.getParameter("e_ct").trim();
		
		File UpFile=multi.getFile("e_fl");
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
			String refileName="eboard"+year+month+date+random+"."+
					fileExtension;
			System.out.println("refileName = " + refileName);
			
			 //오라클 디비에 저장될 레코드 값
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            System.out.println("fileDBName = " + fileDBName);
           
            //파일명 변경합니다.
            UpFile.renameTo(new File(homedir+"/"+refileName));
            System.out.println("homedir / refileName  = " + homedir+"/"+
            refileName);
            
           
            ebobean.setE_fl(fileDBName);
		}
		
		int e_no = 1;
		
		result = this.eboService.getListCount();//총 게시물 수 
		
		if(result!=0){
			result = eboService.getNo(); //최대값 번호 
			e_no = result + 1;
		}
		
		
		ebobean.setMem_no(mem_no);
		ebobean.setE_sj(e_sj);
		ebobean.setE_ct(e_ct);
		ebobean.setE_no(e_no);
		eboService.insertEbo(ebobean);
		
/*		mv.setViewName("html_community/eboardList");*/
		response.sendRedirect("ebo_list.brn");
		return null;
		
	}
		
	
	/* 자료실 목록 */
	@RequestMapping(value="/ebo_list.brn")
	public ModelAndView ebo_list(
					HttpServletRequest request,
					HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		String mem_id = this.eboService.eboIdCk(id);//회원아이디 받아오기
		
		int page=1;
		int limit=10; //초기값
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("page="+page);
		
		//////////20줄보기 10줄보기 
		//이전에 설정된 limit가 있는지 체크
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		
 		//변경된 limit가 있는지 체크
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		////////////
 		System.out.println("limit = " + limit);
 		
		
		//총 리스트 수 받기
		int listcount = eboService.getListCount();
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1)/limit;
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
 		if (endpage < page) page = endpage;
 		
 		Map m = new HashMap();
 		m.put("page", page);
 		m.put("limit", limit);
 		//리스트 받기
 		List<EboardBean> ebolist = eboService.getEboList(m);
 		
 		
 		ModelAndView model = new ModelAndView("html_community/eboard/eboardList");
 		model.addObject("page",page);
 		model.addObject("maxpage", maxpage);
 		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("ebolist", ebolist);
		model.addObject("limit", limit);
		model.addObject("mem_no",mem_no);
		model.addObject("mem_id",mem_id);
		
		return model;	
	}
	
	
	/* 몇줄보기 */
	@RequestMapping(value="/eboardList.brn", method=RequestMethod.POST)
	public ModelAndView eboardList(
					HttpServletRequest request,
					HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		String mem_id = this.eboService.eboIdCk(id);//회원아이디 받아오기
		
		int page=1;
		int limit=10; //초기값
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("page="+page);
		
		//////////20줄보기 10줄보기 
		//이전에 설정된 limit가 있는지 체크
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		
 		//변경된 limit가 있는지 체크
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		////////////
 		System.out.println("limit = " + limit);
 		
		
		//총 리스트 수 받기
		int listcount = eboService.getListCount();
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1)/limit;
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
 		if (endpage < page) page = endpage;
 		
 		Map m = new HashMap();
 		m.put("page", page);
 		m.put("limit", limit);
 		//리스트 받기
 		List<EboardBean> ebolist = eboService.getEboList(m);
 		
 		ModelAndView model = new ModelAndView("html_community/eboard/eboardList");
 		model.addObject("page",page);
 		model.addObject("maxpage", maxpage);
 		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("ebolist", ebolist);
		model.addObject("limit", limit);
		model.addObject("mem_no",mem_no);
		model.addObject("mem_id",mem_id);
		
		return model;	
	}
	
	
	
	
	
	
	
	
	/* 자료실 내용보기, 수정폼, 답변글폼, 삭제폼*/
	@RequestMapping(value="/eboardView.brn")
	public ModelAndView eboardView(
			@RequestParam(value="num", required=true) int num,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception{
	
		
//		int num=Integer.parseInt(request.getParameter("num"));
		
		session=request.getSession();
		String id = (String)session.getAttribute("id");
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		String mem_id = this.eboService.eboIdCk(id);//회원아이디 받아오기
		
		int page=1;
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
			//parseInt()메서드로 정수형 숫자로 바꿔서 저장
		}
		
		ModelAndView mv = new ModelAndView();
		
		Map m = new HashMap();
		m.put("e_no", num);
		
		//코멘트 불러오기
		List<EcommBean> ecommlist = this.eboService.getComm(num);
		mv.addObject("ecommlist",ecommlist);
		
//		//코멘트의 답글불러오기
//		int ecomm_no=Integer.parseInt(request.getParameter("ecomm_no"));
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("e_no", num);
//		map.put("ecomm_no",ecomm_no);
//		List<EcommBean> getEcomm_re = this.eboService.getComm_re(map);
//		mv.addObject(getEcomm_re);
//		///////////////////////////////////
		
		if(request.getParameter("elistcount") != null){
			int elistcount=Integer.parseInt(request.getParameter("elistcount"));
			mv.addObject("elistcount",elistcount);
		}
			
			
		if(request.getParameter("getcId") != null){
			String getcId=request.getParameter("getcId");
			mv.addObject("getcId",getcId);
		}
		
		if(request.getParameter("getcommId") != null){
			String getcommId=request.getParameter("getcommId");
			mv.addObject("getcommId",getcommId);
		}
	
		String state=request.getParameter("state");//구분 필드		
	
		
		//번호를 기준으로 DB 내용을 가져옵니다.
		EboardBean ebobean=this.eboService.getEboCont(num);
		
		/*	if(s_no!=1){*/
		EboardBean beanpre = this.eboService.getEboCont(num-1);
			mv.addObject("beanpre",beanpre);
	/*}*/
		
		EboardBean beannext = this.eboService.getEboCont(num+1);
		mv.addObject("beannext",beannext);
		
		if(ebobean==null){
			PrintWriter out =response.getWriter();
			out.print("<script>alert('존재하지 않는 게시물입니다.'); history.back();</script>");
			return null;
		}else{
		
		if(state.equals("cont")){ //글내용보기
			this.eboService.eboHit(num);//조회수 증가
			mv.setViewName("html_community/eboardView");
			
			//글내용 중 엔터키 친부분을 다음줄로 개행 처리
			String e_ct=ebobean.getE_ct().replace("\n","<br/>");
			
			mv.addObject("e_ct",e_ct);
		}else if(state.equals("edit")){//수정일떄
			mv.setViewName("html_community/eboardEdit");
		}else if(state.equals("comm")){//코멘트
			mv.setViewName("html_community/eboardView");
		}
		
		//좋아요
		if(request.getParameter("joayo") != null){
			int joayo=Integer.parseInt(request.getParameter("joayo"));
			mv.addObject("joayo",joayo);
		}
				
		mv.addObject("ebobean", ebobean);
		mv.addObject("page", page);
		mv.addObject("mem_no",mem_no);
		mv.addObject("mem_id",mem_id);
		mv.addObject("id",id);
		
		return mv;
		}
	}
	
	/* 자료실 수정 */
	@RequestMapping(value="/ebo_edit_ok.brn",
			method=RequestMethod.POST)
	public ModelAndView ebo_edit_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		EboardBean ebobean=new EboardBean();
		response.setContentType("text/html;charset=UTF-8");
		int fileSize=5*1024*1024; //이진파일 최대 업로드 크기	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
//		HttpSession session = request.getSession();
//		String mem_id=(String) session.getAttribute("mem_id");
		
		int e_no=Integer.parseInt(multi.getParameter("num"));
		int page=Integer.parseInt(multi.getParameter("page"));
		String e_sj=multi.getParameter("e_sj").trim();
		String e_ct=multi.getParameter("e_ct").trim();
		PrintWriter out=response.getWriter();
		
		//디비로 부터 내용을 가져옴
		EboardBean ebocont=this.eboService.getEboCont(e_no);
				
		File UpFile=multi.getFile("e_fl");
		   if(UpFile != null){//첨부한 이진파일이 있다면
			   String fileName=UpFile.getName();//첨부한 이진파일명 저장
			   File DelFile=new File(saveFolder+ebocont.getE_fl());
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
			String refileName="eboard"+year+month+date+random+"."+
					fileExtension;//새로운 파일명을 저장
         String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
         
         UpFile.renameTo(new File(homedir+"/"+refileName));

         ebobean.setE_fl(fileDBName);
		   }
		   
		ebobean.setE_no(e_no);
		ebobean.setE_sj(e_sj);
		ebobean.setE_ct(e_ct);
//		ebobean.setMem_id(mem_id);
		
		this.eboService.editEbo(ebobean);//수정메서드 호출
		
		//get방식으로 3개 파라미터 넘어감
		response.sendRedirect(
				"eboardView.brn?state=cont&page="+page+"&num="+e_no);
		
		return null;
		
	}
	
	/* 자료실 삭제 */
	@RequestMapping(value="/ebo_delete_ok.brn")
	public ModelAndView ebo_delete_ok(
			@RequestParam("num") int e_no,
			@RequestParam("page") int page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		
		//글번호에 해당하는 디비 내용을 가져옵니다.
		EboardBean ebobean=this.eboService.getEboCont(e_no);
		
		//기존 파일명 가져옵니다.
		String fname=ebobean.getE_fl();
		
		if(fname != null){//기존 이진파일이 존재한다면
			File file=new File(saveFolder+fname);
			file.delete();//서버 폴더로 부터 기존 이진파일 삭제합니다.
		}
		
		this.eboService.deleteEbo(e_no);//디비로 부터 레코드 삭제합니다.
		response.sendRedirect("ebo_list.brn?page="+page);
		
		return null;
	}
	
	/* 좋아요 */
	@RequestMapping(value="/ebo_like.brn")
	public ModelAndView ebo_like(
			@RequestParam("e_no") int e_no,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception{
		
		session=request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("e_no = " +e_no);
		int mem_no = this.eboService.eboNoCk(id);
		
		System.err.println("mem_no="+mem_no);
		/*로그인한 회원번호를 불러와서 
		추천인 회원번호에 저장하고
		추천인 회원번호 중에 글쓴이 회원번호가 있다면 좋아요 업데이트 취소 */
		
		/*추천인 회원번호 불러오기*/
//		if(this.eboService.getLkno(e_no) != null){
			String e_lkno = this.eboService.getLkno(e_no);
			System.err.println("e_lkno="+e_lkno);
		
//			String mem_no2 =  mem_no + "";
			String mem_no2 =  String.valueOf(mem_no);
	
//		if(mem_no2 != e_lkno){//로그인한 사람의 회원번호와 해당글의 추천인번호가 일치하지 않으면 추천수 증가
			
			System.err.println("오는가요");
			Map m = new HashMap();
			m.put("e_no", e_no);
			m.put("mem_no", mem_no);
			
			/*추천인 회원번호에 저장과 좋아요증가*/
			this.eboService.setJoayo(m);
			
			//해당 글 좋아요 업데이트
//			this.eboService.setJoayo(m);
			
//			}else{
//				return null;
//			}
//		}else //조회수 비증가 
			
		
		
		/*if(mem_no == e_lkno){//일치하면 추천수 변동없음
			return null;
		}*/
		
		
		//좋아요 뽑아오기
		int joayo = this.eboService.getJoayo(e_no);
		
		response.sendRedirect("eboardView.brn?state=cont&num="+e_no+"&joayo="+joayo);
	
		return null;
	}
	
	/* 자료실 검색 목록 */
	@RequestMapping(value="/ebo_find_ok.brn", method=RequestMethod.GET)
	public ModelAndView ebo_find_ok(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		String mem_id = this.eboService.eboIdCk(id);//회원아이디 받아오기
//		String findId = this.eboService.findId();
		
		
		int page=1;
		int limit=10; //초기값
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("page="+page);
		
		//////////20줄보기 10줄보기 
		//이전에 설정된 limit가 있는지 체크
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		
 		//변경된 limit가 있는지 체크
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		////////////
 		System.out.println("limit = " + limit);
 		
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
		m.put("find_name", "%"+find_name+"%");
		m.put("limit", limit);
//		m.put("findId", findId);
		
		//총 리스트 수 받기
		int listcount = this.eboService.getFindName(m);
		System.err.println("listcount테스트 = " + listcount);
		
		//총 페이지 수
		int maxpage = (listcount + limit - 1)/limit;
		
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
// 		if (endpage < page) page = endpage;
 		
/* 		Map m2 = new HashMap();
 		m2.put("page", page);
 		m2.put("limit", limit);
 		//리스트 받기
*/ 		
 		List<EboardBean> ebolist = eboService.getEboList3(m);
 		
 		
 		ModelAndView model = new ModelAndView("html_community/eboard/eboardFind");
 		
 		model.addObject("find_name",find_name);
		model.addObject("find_field",find_field);	
 		model.addObject("page",page);
 		model.addObject("maxpage", maxpage);
 		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("ebolist", ebolist);
		model.addObject("limit", limit);
		model.addObject("mem_no",mem_no);
		model.addObject("mem_id",mem_id);
		
		return model;	
		
	}
	
	/* 코멘트 저장 */
	@RequestMapping(value="/ebo_comm.brn", method=RequestMethod.POST)
	public ModelAndView ebo_comm(
			@RequestParam("e_no") int e_no,
			@RequestParam("ecomm") String ecomm,//글내용 
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		int mem_no = this.eboService.eboNoCk(id);//회원번호 받아오기
		System.err.println("--------------------1-------------------------");
		Map m = new HashMap();
		m.put("ecomm", ecomm);//글내용
		m.put("e_no", e_no);
		m.put("mem_no", mem_no);
		
		String getcommId = this.eboService.getcId(mem_no);//회원아이디 받아오기
		System.err.println("--------------------2-------------------------");
//		코멘트 내용을 코멘트 테이블에 담는다
		this.eboService.setComm(m);
		System.err.println("--------------------3-------------------------");
		
		
//		commCount총리스트수
		int elistcount = this.eboService.commCount(e_no);
//		EcommBean ecomm_re = this.eboService.getComm_re(e_no, ecomm_no);
		
		System.err.println("elistcount = " + elistcount);
		System.err.println("--------------------4-------------------------");
		/*ModelAndView mv = new ModelAndView();
		mv.addObject("elistcount",elistcount);
		mv.addObject("ecommlist",ecommlist);
		mv.addObject("getcId",getcId);*/
		
		response.sendRedirect("eboardView.brn?elistcount="+elistcount+"&num="+e_no+"&getcommId="+getcommId+"&state=comm");
		
		return null;
	}
	
	/*@RequestMapping(value="/ecommList.brn")
	public ModelAndView ecommList(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="e_no") int e_no) throws Exception{
		List<ScommBean> beanlist = new ArrayList<ScommBean>();
		ModelAndView model=new ModelAndView("sboard/scommList");
		beanlist = this.eboService.getComm(e_no);
		model.addObject("beanlist",beanlist);
		
		return model;
	}*/
	
	/* 답글저장*/
	@RequestMapping(value="/ebo_reply.brn", method=RequestMethod.POST)
	public ModelAndView ebo_reply(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		   int e_no=Integer.parseInt(request.getParameter("e_no"));
		   int ecomm_no=Integer.parseInt(request.getParameter("ecomm_no"));
		   int ecomm_re_ref=Integer.parseInt(request.getParameter("ecomm_re_ref"));
		   int ecomm_re_lev=Integer.parseInt(request.getParameter("ecomm_re_lev"));
		   int ecomm_re_seq=Integer.parseInt(request.getParameter("ecomm_re_seq"));
		   
		   String ecomm_rct=request.getParameter("ecommRe_ct").trim();

		   EcommBean ecommb=new EcommBean();
		   
		   HttpSession session = request.getSession();
		   String mem_id = (String) session.getAttribute("id");
		   int mem_no = this.eboService.eboNoCk(mem_id);//회원번호 받아오기
		   
		   String getcId = this.eboService.getcId(mem_no);//회원아이디 받아오기
//		   
		   ecommb.setEcomm_re_ref(ecomm_re_ref); 
		   ecommb.setEcomm_re_seq(ecomm_re_seq);
		   
		   this.eboService.refEdit(ecommb);
		   
		   ecomm_re_lev=ecomm_re_lev+1;
		   ecomm_re_seq=ecomm_re_seq+1; //1씩증가
		   
		   ecommb.setEcomm_rct(ecomm_rct); ecommb.setE_no(e_no); ecommb.setEcomm_rct(ecomm_rct);
		   ecommb.setEcomm_re_lev(ecomm_re_lev); ecommb.setEcomm_re_seq(ecomm_re_seq);
        		   
		   this.eboService.ecommReply(ecommb); //저장 메서드 호출
		   											//
		   response.sendRedirect("eboardView.brn?&num="+e_no+"&getcId="+getcId+"&ecomm_no="+ecomm_no+"&state=comm"); //해당 쪽번호 목록으로 이동
		   
		   return null;
	}

	/* 코멘트 삭제*/
	@RequestMapping(value="/ebo_reply_del.brn")
	public ModelAndView ebo_reply_del(
//			@RequestParam("ecomm_re_del_no") int ecomm_re_del_no,
			@RequestParam("ecomm_no") int ecomm_no,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		 int e_no=Integer.parseInt(request.getParameter("e_no"));
		
		 Map<String, Integer> m = new HashMap<String, Integer>();
	      m.put("ecomm_no", ecomm_no);
	      m.put("e_no", e_no);
	      
		 EcommBean bean = new EcommBean();
		 List<EcommBean> ecommlist = new ArrayList<EcommBean>();
		 bean.setE_no(e_no);
		 bean.setEcomm_no(ecomm_no);
		 ecommlist=this.eboService.getcommentref(bean);
		 
	      int result =0;
	      
	      if(ecommlist.size()>=2){ //코멘트번호를 참조하는 답글번호가 코멘트번호랑 같으면   2개이상
	         result =this.eboService.deleteCommExistRep(m);
	      }else{
	         result =this.eboService.deleteComm(m);
	      }
		 
//		 m.put("ecomm_re_del_no",ecomm_re_del_no);
		 
//		//코멘트 삭제
//		this.eboService.deleteEcomm(m);//디비로 부터 레코드 삭제합니다.
		
		response.sendRedirect("eboardView.brn?state=comm&num="+e_no);
		
		return null;
			
	}
	
	
	
}

