  package com.burn.fat.board.fboard.action;

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

import com.burn.fat.board.fboard.dao.FboardService;
import com.burn.fat.board.fboard.dao.FcommService;
import com.burn.fat.board.fboard.model.FboardBean;
import com.burn.fat.board.fboard.model.FcommBean;
import com.oreilly.servlet.MultipartRequest;

  @Controller("fboardaction")
  public class FboardAction {

     @Autowired
     private FboardService bbsService;
     
     @Autowired
    private FcommService fcService;
     
     ////private String saveFolder ="C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/myhome7/resources/upload";
      //private String saveFolder="c:/sts2/spring6_mvc_bbs/src/main/webapp/resources/upload";
     private String saveFolder = "C:/Users/angel/git/FinalProject/src/main/webapp/resources/upload";
     

    /*자료실 입력폼*/
    @RequestMapping(value="/bbs_write.brn")
    public String bbs_write(){
       return "html_community/fboard/boardWrite"; //bbs 폴더의 bbs_write.jsp 뷰 페이지가 실행
    }
     
     /* 자료실 저장 */
     //  첨부파일 클릭할 경우 이미지 보고자 할  경우 : 자동 새로 고침 설정
     //(window-> Preferencs -> workspce -> 
     // Refresh using native hooks or polling 체크)
     //  하고 5초 정도 지난 뒤 확인하세요
     @RequestMapping(value="/bbs_write_ok.brn",
           method=RequestMethod.POST)
     public ModelAndView bbs_write_ok(
           HttpServletRequest request,
           HttpServletResponse response) throws Exception{
        
        FboardBean bbsbean=new FboardBean();      
        int fileSize=5*1024*1024; //이진파일 최대 업로드 크기   
        
        PrintWriter out=response.getWriter();
        
        MultipartRequest multi=null;
        multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
        
        /*String bbs_name=multi.getParameter("bbs_name").trim();
        String bbs_pass=multi.getParameter("bbs_pass").trim();*/
        
        //세션 선언 후 세션 id가져오기
        HttpSession session = request.getSession();
        
        String mem_id = (String)session.getAttribute("mem_id");//세션에 저장된 mem_id를 mem_id로 저장
        
        if(mem_id==null){
         out.print("<script>alert('로그인 후 이용할 수 있습니다.');");
         out.print(" history.back()</script>");
         
         return null;
      }
        
        
        String f_sj=multi.getParameter("f_sj").trim();
        String f_ct=multi.getParameter("f_ct").trim();
        
        File UpFile=multi.getFile("f_fl");
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
           String refileName="bbs"+year+month+date+random+"."+fileExtension;
           System.out.println("refileName = " + refileName);
           
            //오라클 디비에 저장될 레코드 값
              String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
              System.out.println("fileDBName = " + fileDBName);
             
              //파일명 변경합니다.
              UpFile.renameTo(new File(homedir+"/"+refileName));
              System.out.println("homedir / refileName  = " + homedir+"/"+
              refileName);
              
              //바뀐 파일명으로  저장
              bbsbean.setF_fl(fileDBName);
        }
       /* bbsbean.setBbs_name(bbs_name);
        bbsbean.setBbs_pass(bbs_pass);*/
        
        bbsbean.setMem_id(mem_id);
        
        bbsbean.setF_sj(f_sj);
        bbsbean.setF_ct(f_ct);
        
        this.bbsService.insertBbs(bbsbean); //저장메서드 호출
        
        response.sendRedirect("./bbs_list.brn");
        return null;
     }
     
     /* 자료실 목록 */
     @RequestMapping(value="/bbs_list.brn")
     public ModelAndView bbs_list(HttpServletRequest request,
           HttpServletResponse response) throws Exception{
            
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
        int listcount=this.bbsService.getListCount(); //총 리스트 수를 받아옴
              
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
        List<FboardBean> bbslist = bbsService.getBbsList(m);//page,limit를 보냄
     
        ModelAndView model=new ModelAndView("html_community/fboard/boardList");
      model.addObject("page", page);
      model.addObject("maxpage", maxpage);
      model.addObject("startpage", startpage);
      model.addObject("endpage", endpage);
      model.addObject("listcount", listcount);
      model.addObject("bbslist", bbslist);
      model.addObject("limit", limit);
      
      return model;      
       
     }
     
     /* 자료실 내용보기, 수정폼, 답변글폼, 삭제폼*/
     @RequestMapping(value="/bbs_cont.brn")
     public ModelAndView bbs_cont(
           HttpServletRequest request,
           HttpServletResponse response,@RequestParam(value="num") int num
           ) throws Exception{
        
        
        
        PrintWriter out=response.getWriter();
        
        int page=1;
        if(request.getParameter("page") != null){
           page=Integer.parseInt(request.getParameter("page"));
           //parseInt()메서드로 정수형 숫자로 바꿔서 저장
        }
        
        String state=request.getParameter("state");//구분 필드      
        ModelAndView contM=new ModelAndView();
        
        if(state.equals("cont")){
           this.bbsService.bbsHit(num);//조회수 증가
           
           
           
        }
        
        //번호를 기준으로 DB 내용을 가져옵니다.
        FboardBean bbsbean=this.bbsService.getBbsCont(num);
        
        FboardBean bean1 = bbsService.getBbsCont(num-1);
        contM.addObject("bean1",bean1);

        FboardBean bean2 = bbsService.getBbsCont(num+1);
        contM.addObject("bean2",bean2);   
        
        
       
       if(bbsbean==null){
          out.println("<script>");
            out.println("alert('게시판 글이 없습니다.')");
            out.println("history.back()");
            out.println("</script>");

          return null;
       }else{
       
        
        if(state.equals("cont")){//내용보기
           contM.setViewName("html_community/fboard/boardView");
           
          //글내용 중 엔터키 친부분을 다음줄로 개행 처리
           String f_ct=bbsbean.getF_ct().replace("\n","<br/>");
           
          contM.addObject("bbs_cont",f_ct);
        }else if(state.equals("edit")){
           contM.setViewName("html_community/fboard/boardModify");//수정일때
        }/*else if(state.equals("del")){//삭제일때
           contM.setViewName("html_community/boardList");//삭제일때
   
        }else if(state.equals("reply")){//답변글
           contM.setViewName("bbs/bbs_reply");
        }*/
        
        
       List<FcommBean> bean = new ArrayList<FcommBean>();
       
        if(fcService.getListCmt(num)!=null)
        {
      bean= fcService.getListCmt(num);
      
      contM.addObject("bean",bean);
        }
        
        contM.addObject("bbsbean",bbsbean);
        contM.addObject("page",page);
        return contM;
      }
       
       
     }
     
     /* 자료실 수정 */
     @RequestMapping(value="/bbs_edit_ok.brn",
           method=RequestMethod.POST)
     public ModelAndView bbs_edit_ok(HttpServletRequest request,
           HttpServletResponse response) throws Exception{
        
        FboardBean bbsbean=new FboardBean();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        int fileSize=5*1024*1024; //이진파일 최대 업로드 크기
        
        MultipartRequest multi=null;//이진파일 받아오는 객체
        multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
        
        int f_no=Integer.parseInt(multi.getParameter("f_no"));//번호
        int page=Integer.parseInt(multi.getParameter("page"));//쪽번호
        /*String bbs_name=multi.getParameter("bbs_name").trim(); //이름
        String bbs_pass=multi.getParameter("bbs_pass").trim();//비번*/
        String f_sj=multi.getParameter("f_sj").trim();//제목
        String f_ct=multi.getParameter("f_ct").trim();//내용
        
        //디비로 부터 내용을 가져옴
        FboardBean bcont=this.bbsService.getBbsCont(f_no);
        
      //세션 선언 후 세션 id가져오기
        HttpSession session = request.getSession();
        
        String mem_id = (String)session.getAttribute("mem_id");//세션에 저장된 mem_id를 mem_id로 저장
        
        if(mem_id==null){
         out.print("<script>alert('로그인 후 이용할 수 있습니다.');");
         out.print(" history.back()</script>");
         
         return null;
      }
        
        
        if(!bcont.getMem_id().equals(mem_id)){//해당 회원이 아니라면
           out.println("<script>");
           out.println("alert('수정 권한이 없습니다.')");
           out.println("history.back()");
           out.println("</script>");
        }else{
           File UpFile=multi.getFile("bbs_file");
           if(UpFile != null){//첨부한 이진파일이 있다면
              String fileName=UpFile.getName();//첨부한 이진파일명 저장
              File DelFile=new File(saveFolder+bcont.getF_fl());
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

              bbsbean.setF_fl(fileDBName);
           }
           bbsbean.setF_no(f_no);
           bbsbean.setMem_id(mem_id);
           
          /* bbsbean.setBbs_name(bbs_name);*/
           bbsbean.setF_sj(f_sj);
           bbsbean.setF_ct(f_ct);
           
           this.bbsService.editBbs(bbsbean);//수정메서드 호출
           
         //get방식으로 3개의 파라미터 값이 넘어갑니다.
            response.sendRedirect(
                "bbs_cont.brn?state=cont&page="+page+"&num="+f_no);
        }      
        return null;
     }
     
     /* 자료실 삭제 */
     @RequestMapping(value="/bbs_delete_ok.brn",method=RequestMethod.POST)
     public ModelAndView bbs_delete_ok(
           HttpServletRequest request,
           HttpServletResponse response) throws Exception{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        int f_no=Integer.parseInt(request.getParameter("f_no"));
        int page=Integer.parseInt(request.getParameter("page"));
       /* String pwd=request.getParameter("pwd").trim();*/
        
        //글번호에 해당하는 디비 내용을 가져옵니다.
        FboardBean bbsbean=this.bbsService.getBbsCont(f_no);
        
        //기존 파일명 가져옵니다.
        String fname=bbsbean.getF_fl();
        
        //세션 선언 후 세션 id가져오기
        HttpSession session = request.getSession();
        
        String mem_id = (String)session.getAttribute("mem_id");//세션에 저장된 mem_id를 mem_id로 저장
        
  
        if(!bbsbean.getMem_id().equals(mem_id)){//해당 회원이 아니라면
           out.println("<script>");
           out.println("alert('삭제 권한이 없습니다.')");
           out.println("history.back()");
           out.println("</script>");
        }else{//비번이 같다면
           if(fname != null){//기존 이진파일이 존재한다면
              File file=new File(saveFolder+fname);
              file.delete();//서버 폴더로 부터 기존 이진파일 삭제합니다.
           }
           this.bbsService.deleteBbs(f_no);//디비로 부터 레코드 삭제합니다.
           response.sendRedirect("bbs_list.brn?page="+page);

           
        }
        return null;
     }
     
     /* sever.xml 한글 처리 인코딩 설정 - get 방식 적용
     <Connector connectionTimeout="20000" port="8088" protocol="HTTP/1.1" 
             redirectPort="8443" URIEncoding="UTF-8"/>*/
     /* 자료실 검색 목록 */
     @RequestMapping(value="/bbs_find_ok.brn", method=RequestMethod.GET)
     public ModelAndView bbs_find_ok(
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
           System.out.println(find_field);
           System.out.println(find_name);
           
           
           int listcount=this.bbsService.getListCount3(m);
           System.out.println(listcount);
           
           
           //총 페이지 수
           int maxpage = (listcount+limit-1)/limit;
                        
           //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
           int startpage = ((page-1) / 10) * 10 + 1;
                        
           //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
           int endpage = startpage + 10 -1;
                          
           if (endpage > maxpage) endpage= maxpage;   
            
           List<FboardBean> bbslist = bbsService.getBbsList3(m); 
           
           ModelAndView model=new ModelAndView("html_community/fboard/boardList");
           model.addObject("find_name",find_name);
           model.addObject("find_field",find_field);         
           model.addObject("page", page);
           model.addObject("maxpage", maxpage);
           model.addObject("startpage", startpage);
           model.addObject("endpage", endpage);
           model.addObject("listcount", listcount);
           model.addObject("bbslist", bbslist);         
           
           return model;   
           //return null;
     }
     
     //스크랩
    @RequestMapping(value="/fboardscrap.brn")
    public void sboardscrap(HttpServletRequest request,
          HttpServletResponse response, @RequestParam(value="f_no", required=true) int f_no)throws Exception{
       
       HttpSession session = request.getSession();
       
       String mem_no = (String) session.getAttribute("mem_no");
       mem_no="2";
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("f_no", f_no);
       map.put("mem_no", mem_no);
       int check=0;
       String f_lkno = bbsService.checkscrap(f_no);
       if(f_lkno!=null){
          StringTokenizer token = new StringTokenizer(f_lkno, ",");
          while(token.hasMoreTokens()){
             if(token.nextToken().equals(mem_no)){
                check=1;
             }
          }
          System.out.println("1."+f_lkno);
       }else{
          System.out.println("f_lkno is null");
       }
       int result = 0;
       if(check !=1 ){
          result = bbsService.likeCountUp(map);
          System.out.println(result);
       }
       
    }
  }

