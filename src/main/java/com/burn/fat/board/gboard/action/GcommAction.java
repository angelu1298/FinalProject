package com.burn.fat.board.gboard.action;

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

import com.burn.fat.board.gboard.dao.GboardService;
import com.burn.fat.board.gboard.dao.GcommService;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.gboard.model.GcommBean;

@Controller("gcomm")
public class GcommAction {
	
	@Autowired
	private GcommService service;
	@Autowired
	private GboardService gboardservice;
	
	@RequestMapping(value="/gcomm_write_ok.brn", method=RequestMethod.POST)
	public void gcomm_write_ok(	HttpServletRequest request,	HttpServletResponse response,
			@RequestParam(value="gbbs_num") int gbbs_num
		) throws Exception{
		
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");
		
		String gcomm_ct = request.getParameter("gcomm_ct");
		GcommBean gcommbean = new GcommBean();
		gcommbean.setMem_id(mem_id);
		gcommbean.setGcomm_ct(gcomm_ct);
		gcommbean.setGbbs_num(gbbs_num);
		int result = service.insertComm(gcommbean);
		gboardservice.changeGcommcnt(gbbs_num);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	@RequestMapping(value="/gcomm_list.brn")
	public ModelAndView gcomm_list(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="gbbs_num") int gbbs_num) throws Exception{
		List<GcommBean> beanlist = new ArrayList<GcommBean>();
		ModelAndView model=new ModelAndView("gbbs/gcomm_list");
		beanlist = service.getCommList(gbbs_num);
		model.addObject("beanlist", beanlist);
		return model;
	}
	
	@RequestMapping(value="/gcomm_delete_ok.brn", method=RequestMethod.POST)
	public void gcommDelet(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="gbbs_num") int gbbs_num,@RequestParam(value="gcomm_no") int gcomm_no)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("gcomm_no", gcomm_no);
		map.put("gbbs_num", gbbs_num);
		GcommBean bean = new GcommBean();
		List<GcommBean> beanreflist = new ArrayList<GcommBean>();
		bean.setGbbs_num(gbbs_num);
		bean.setGcomm_no(gcomm_no);
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

	@RequestMapping(value="/gcomm_reply_ok.brn", method=RequestMethod.POST)
	public void gcomm_reply_ok(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="gbbs_num") int gbbs_num, @RequestParam(value="gcomm_no") int gcomm_no,
			@RequestParam(value="gcomm_re_ct") String gcomm_re_ct)throws Exception{

		GcommBean commbean = new GcommBean();
		HttpSession session = request.getSession();
		String mem_id = (String) session.getAttribute("mem_id");

		commbean.setMem_id(mem_id);
		commbean.setGbbs_num(gbbs_num);
		commbean.setGcomm_no(gcomm_no);
		commbean = service.getComment(commbean);
		
		int gcomm_re_ref = commbean.getGcomm_re_ref();
		int gcomm_re_lev = commbean.getGcomm_re_lev();
		int gcomm_re_seq = commbean.getGcomm_re_seq();

		gcomm_re_lev = gcomm_re_lev+1;
		gcomm_re_seq = gcomm_re_seq+1;

		System.out.println(gcomm_re_lev + " / " + gcomm_re_seq);
		
		commbean.setGcomm_ct(gcomm_re_ct);
		commbean.setGcomm_re_ref(gcomm_re_ref);
		commbean.setGcomm_re_lev(gcomm_re_lev);
		commbean.setGcomm_re_seq(gcomm_re_seq);

		service.insertCommRep(commbean);
		
	}
	
}