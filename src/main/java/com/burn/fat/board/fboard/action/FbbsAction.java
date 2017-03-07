package com.burn.fat.board.fboard.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
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

import com.burn.fat.board.fboard.dao.FBoardService;
import com.burn.fat.board.fboard.model.FbbsBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class FbbsAction {

	@Autowired
	private FBoardService fbbsService;

	private String saveFolder = "C:/Users/김수현/git/FinalProject/src/main/webapp/resource/upload"; 
	//파일저장 경로
																								

	/* 자료실 입력폼 */
	@RequestMapping(value = "/boardWrite.brn")
	public String fbbs_write() {
		return "html_community/boarWrite"; // html_community폴더
	}
	
    @RequestMapping(value="/boardWrite_ok.brn",
          method=RequestMethod.POST)
    public ModelAndView bbs_write_ok(
          HttpServletRequest request,
          HttpServletResponse response) throws Exception{
       
       FbbsBean fbbsbean=new FbbsBean();      
       int fileSize=5*1024*1024; //이진파일 최대 업로드 크기   
       
       MultipartRequest multi=null;
       multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
       
       String f_sj = multi.getParameter("bbs_subject").trim();
       String f_ct = multi.getParameter("bbs_content");
       
       
       File UpFile=multi.getFile("bbs_file");
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
          String refileName="bbs"+year+month+date+random+"."+
                fileExtension;
          System.out.println("refileName = " + refileName);
          
           //오라클 디비에 저장될 레코드 값
             String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
             System.out.println("fileDBName = " + fileDBName);
            
             //파일명 변경합니다.
             UpFile.renameTo(new File(homedir+"/"+refileName));
             System.out.println("homedir / refileName  = " + homedir+"/"+
             refileName);
             
             //바뀐 파일명으로  저장
             fbbsbean.setF_fl(fileDBName);
       }
       fbbsbean.setF_sj(f_sj);
       fbbsbean.setF_ct(f_ct);
       
       this.fbbsService.insertBbs(fbbsbean); //저장메서드 호출
       
       response.sendRedirect("boardList.brn");
       return null;
    }
    
    /* 자료실 목록 */
    @RequestMapping(value="/boardList.brn")
    public ModelAndView bbs_list(HttpServletRequest request,
          HttpServletResponse response) throws Exception{
           
       int page = 1;
        //int limit=10; // 한 화면에 출력할 레코드 갯수
        int limit=3;   //목록보기 초기값
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
       int listcount=this.fbbsService.getListCount(); //총 리스트 수를 받아옴
             
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
       List<FbbsBean> bbslist = fbbsService.getBbsList(m);//page,limit를 보냄
    /*
       request.setAttribute("page", page);   //현재 페이지 수 
       request.setAttribute("maxpage", page);   //현재 페이지 수 
       request.setAttribute("startpage", page);   //현재 페이지 수 
       request.setAttribute("endpage", page);   //현재 페이지 수 
       request.setAttribute("listcount", page);   //현재 페이지 수 
       request.setAttribute("bbslist", page);   //현재 페이지 수 
       request.setAttribute("list", page);   //현재 페이지 수 
    */
     ModelAndView model=new ModelAndView("html_community/boarList");
     model.addObject("page", page);
     model.addObject("maxpage", maxpage);
     model.addObject("startpage", startpage);
     model.addObject("endpage", endpage);
     model.addObject("listcount", listcount);
     model.addObject("bbslist", bbslist);
     model.addObject("limit", limit);
     
     return model;      
       
    }
    
 
   
 }
