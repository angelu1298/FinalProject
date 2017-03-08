package com.burn.fat.board.sboard.action;

import java.io.PrintWriter;
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

import com.burn.fat.board.sboard.dao.SboardService;
import com.burn.fat.board.sboard.dao.ScommService;
import com.burn.fat.board.sboard.model.ScommBean;

@Controller("scomm")
public class ScommAction {
	
	@Autowired
	private ScommService service;
	@Autowired
	private SboardService sboardservice;
	
	@RequestMapping(value="/scommWrite_ok.brn",
			method=RequestMethod.POST)
	public void scommwrite_ok(
			HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value="s_no") int s_no) throws Exception{
		
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		
		String scomm_ct = request.getParameter("scomm_ct");
		ScommBean commbean = new ScommBean();
		commbean.setMem_id(mem_id);
		commbean.setScomm_ct(scomm_ct);
		commbean.setS_no(s_no);
		int result = service.insertComm(commbean);
		sboardservice.changeScommcnt(s_no);
		PrintWriter out = response.getWriter();
		out.print(result);
	
	}
	@RequestMapping(value="/scommList.brn")
	public ModelAndView scommList(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="s_no") int s_no) throws Exception{
		List<ScommBean> beanlist = new ArrayList<ScommBean>();
		ModelAndView model=new ModelAndView("board/sboard/scommList");
		beanlist = service.getCommList(s_no);
		model.addObject("beanlist",beanlist);
		
		return model;
	}
	
	@RequestMapping(value="/scommdelete_ok.brn", method=RequestMethod.POST)
	public void scommDelet(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="s_no") int s_no,@RequestParam(value="scomm_no") int scomm_no)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("scomm_no", scomm_no);
		map.put("s_no", s_no);
		ScommBean bean = new ScommBean();
		List<ScommBean> beanreflist = new ArrayList<ScommBean>();
		bean.setS_no(s_no);
		bean.setScomm_no(scomm_no);
		beanreflist=service.getCommentRef(bean);
		int result =0;
		if(beanreflist.size()>=2){
			result =service.deleteCommExistRep(map);
		}else{
			result =service.deleteComm(map);
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}
	
	@RequestMapping(value="/scommReply_ok.brn", method=RequestMethod.POST)
	public void scommReply(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="s_no") int s_no,@RequestParam(value="scomm_no") int scomm_no,
			@RequestParam(value="scomm_ct") String scomm_ct)throws Exception{
		ScommBean commbean = new ScommBean();
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		commbean.setMem_id(mem_id);
		commbean.setS_no(s_no);
		commbean.setScomm_no(scomm_no);
		commbean = service.getComment(commbean);
		int scomm_re_ref = commbean.getScomm_re_ref();
		int scomm_re_lev = commbean.getScomm_re_lev();
		int scomm_re_seq = commbean.getScomm_re_seq();
		scomm_re_lev = scomm_re_lev+1;
		scomm_re_seq =scomm_re_seq+1;
		commbean.setScomm_ct(scomm_ct);
		commbean.setScomm_re_ref(scomm_re_ref);
		commbean.setScomm_re_lev(scomm_re_lev);
		commbean.setScomm_re_seq(scomm_re_seq);
		service.insertCommRep(commbean);
	}
	
}