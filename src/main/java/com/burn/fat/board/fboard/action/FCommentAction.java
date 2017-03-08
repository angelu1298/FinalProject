package com.burn.fat.board.fboard.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.burn.fat.board.fboard.model.FcommBean;

@Controller("fcom")
public class FCommentAction {
      @Autowired 
      private FcommService fcService;
      
      @Autowired
  	  private FboardService boardservice;
     
      //코멘트 리스트
      @RequestMapping(value="/listCmt.brn")
      public ModelAndView listCmt(
    		  HttpServletRequest request,
    		  HttpServletResponse response,
    		  @RequestParam(value="f_no") int f_no
    		  ) throws Exception {
    	  
    	  	ModelAndView mv=new ModelAndView();
    	  	
           	List<FcommBean> bean = fcService.getListCmt(f_no);
           	//참조글 번호를 getListCmt로 넘김
           	
           	
            mv.addObject("bean", bean);
            mv.setViewName("html_community/fboard/fcomment");
            return mv;
      }
     
      //코멘트 작성
      @RequestMapping(value="/createCmt.brn", method=RequestMethod.POST)
      public void createCmt(
    		  HttpServletRequest request,
    		  HttpServletResponse response,
    		  @RequestParam(value="f_no") int f_no
    		  ) throws Exception {
    	  
    	  HttpSession session = request.getSession();
    	  
    	  String fcomm_ct = request.getParameter("fcomm_ct").trim();
    	  String mem_id = (String)session.getAttribute("mem_id");
    	  

    	  FcommBean bean = new FcommBean();
    	  bean.setF_no(f_no);
    	  bean.setFcomm_ct(fcomm_ct);
    	  bean.setMem_id(mem_id);
    	  
          fcService.createCmt(bean);
          
          
      }
      
      //코멘트 삭제
      @RequestMapping(value="/deleteCmt.brn", method=RequestMethod.POST)
      public void deleteCmt(
    		  HttpServletRequest request,
    		  HttpServletResponse response,
    		  @RequestParam(value="f_no") int f_no,
    		  @RequestParam(value="fcomm_no") int fcomm_no
    		  ) throws Exception {
    	  
    	  
    	  Map m = new HashMap();
    	  m.put("f_no", f_no);
    	  m.put("fcomm_no", fcomm_no);
    	  
    	  
    	  FcommBean bean = new FcommBean();
    	  
    	  List<FcommBean> beanlist = new ArrayList<FcommBean>();
    	  bean.setF_no(f_no);
    	  bean.setFcomm_no(fcomm_no);
    	  
    	  beanlist = fcService.getCommentRef(bean);
    	  
    	  int result =0;
    	  
    	  if(beanlist.size() >=2){
    		  result = fcService.deleteCommExistRep(m);
    	  }else{
    		  result = fcService.deleteCmt(m);
    	  }
    	  
    	  
     
      }
      
      
      //코멘트 답변
    @RequestMapping(value="/fcommReply_ok.brn", method=RequestMethod.POST)
  	public void fcommReply(
  			HttpServletRequest request,
  			HttpServletResponse response,
  			@RequestParam(value="f_no") int f_no,
  			@RequestParam(value="fcomm_no") int fcomm_no,
  			@RequestParam(value="fcomm_ct") String fcomm_ct
  			)throws Exception{
    	
  		FcommBean bean = new FcommBean();
  		HttpSession session = request.getSession();
  		
  		String mem_id = (String) session.getAttribute("mem_id");
  		bean.setMem_id(mem_id);
  		
  		bean.setF_no(f_no);//글번호(파라미터로 받아옴)
  		bean.setFcomm_no(fcomm_no);//코멘트 번호
  		bean = fcService.getComment(bean);//댓글 가져오기
  		
  		int fcomm_re_ref = bean.getFcomm_re_ref();
  		int fcomm_re_lev = bean.getFcomm_re_lev();
  		int fcomm_re_seq = bean.getFcomm_re_seq();
  		
  		fcomm_re_lev = fcomm_re_lev+1;  //답변 수준 +1
  		fcomm_re_seq =fcomm_re_seq+1; //답변 순서+1
  		
  		bean.setFcomm_ct(fcomm_ct);//코멘트 내용
  		bean.setFcomm_re_ref(fcomm_re_ref);//참조글 번호
  		bean.setFcomm_re_lev(fcomm_re_lev);//답변 수준
  		bean.setFcomm_re_seq(fcomm_re_seq);//답변 순서
  		
  		fcService.insertCommRep(bean);  //대댓글 입력
  	}
      
}