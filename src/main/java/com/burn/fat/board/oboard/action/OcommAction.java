package com.burn.fat.board.oboard.action;

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

import com.burn.fat.board.oboard.dao.OcommService;
import com.burn.fat.board.oboard.model.OcommBean;
@Controller("ocomm")
public class OcommAction {
	
	@Autowired
	private OcommService oservice;
//	
//	@Autowired
//	private OBoardServiceImpl oboardservice;
	
	@RequestMapping(value="/ocommWrite_ok.brn",
			method=RequestMethod.POST)
	public void ocommWrite_ok(
			HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value="o_no") int o_no) throws Exception{
		
		
		System.err.println("여기 왓닝");
		
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		
		System.err.println(mem_id);
		
		String ocomm_ct = request.getParameter("ocomm_ct");
		OcommBean ocommbean = new OcommBean();
		ocommbean.setMem_id(mem_id);
		ocommbean.setOcomm_ct(ocomm_ct);
		ocommbean.setO_no(o_no);
		
		System.err.println("~!!!!!!!!!!!!!!!");
		
		System.err.println(ocomm_ct);
		System.err.println(o_no);
		
		int result = oservice.insertOComm(ocommbean);
		
		System.err.println("야result!!!!!!!!!!!!!!"+result);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	
	}
	@RequestMapping(value="/ocommList.brn")
	public ModelAndView ocommList(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="o_no") int o_no) throws Exception{
		List<OcommBean> beanlist = new ArrayList<OcommBean>();
		ModelAndView model=new ModelAndView("html_community/oboard/ocommList");
		beanlist = oservice.getOCommList(o_no);
		model.addObject("beanlist",beanlist);
		
		return model;
	}
	
	@RequestMapping(value="/ocommdelete_ok.brn", method=RequestMethod.POST)
	public void ocommDelete(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="o_no") int o_no,@RequestParam(value="ocomm_no") int ocomm_no)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ocomm_no", ocomm_no);
		map.put("o_no", o_no);
		OcommBean ocommbean = new OcommBean();
		List<OcommBean> beanreflist = new ArrayList<OcommBean>();
		ocommbean.setO_no(o_no);
		ocommbean.setOcomm_no(ocomm_no);
		beanreflist=oservice.getOCommentRef(ocommbean);
		int result =0;
		if(beanreflist.size()>=2){
			result =oservice.deleteOCommExistRep(map);
		}else{
			result =oservice.deleteOComm(map);
		}
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}
	
	@RequestMapping(value="/ocommReply_ok.brn", method=RequestMethod.POST)
	public void scommReply(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="o_no") int o_no,@RequestParam(value="ocomm_no") int ocomm_no,
			@RequestParam(value="ocomm_ct") String ocomm_ct)throws Exception{
		OcommBean commbean = new OcommBean();
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		commbean.setMem_id(mem_id);
		commbean.setO_no(o_no);
		commbean.setOcomm_no(ocomm_no);
		commbean = oservice.getOComment(commbean);
		int ocomm_re_ref = commbean.getOcomm_re_ref();
		int ocomm_re_lev = commbean.getOcomm_re_lev();
		int ocomm_re_seq = commbean.getOcomm_re_seq();
		ocomm_re_lev = ocomm_re_lev+1;
		ocomm_re_seq =ocomm_re_seq+1;
		commbean.setOcomm_ct(ocomm_ct);
		commbean.setOcomm_re_ref(ocomm_re_ref);
		commbean.setOcomm_re_lev(ocomm_re_lev);
		commbean.setOcomm_re_seq(ocomm_re_seq);
		oservice.insertOCommRep(commbean);
	}
	
}