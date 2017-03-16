package com.burn.fat.member.mypage.sub.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.gboard.model.GbbsBean;
import com.burn.fat.board.oboard.model.ObsBean;
import com.burn.fat.member.mypage.sub.dao.ScrapService;

@Controller
public class ScrapAction {

	@Autowired
	private ScrapService scrapservice;
	
	@RequestMapping(value = "/sc_view.brn")
	public ModelAndView sc_view(HttpServletRequest request, 
									HttpServletResponse response, 
									HttpSession session) throws Exception {

	
		
		ModelAndView mv = new ModelAndView("html_mypage/mypage_scrap");

		return mv;
	}

		
/*oboard 스크랩*/
			@RequestMapping(value = "/o_sc_view.brn")
			public ModelAndView o_scrap(HttpServletRequest request, 
											HttpServletResponse response, 
											HttpSession session) throws Exception {
		
				//String mem_id = (String)session.getAttribute("mem_id");
				int mem_no = (Integer)session.getAttribute("mem_no");
				
				//System.out.println(mem_id);
				System.out.println(mem_no);
				
				int page = 1;
				int limit = 10;
		
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
		
				if (session.getAttribute("limit") != null) {
					limit = (Integer) session.getAttribute("limit");
				}
		
				if (request.getParameter("limit") != null) {
					limit = Integer.parseInt(request.getParameter("limit"));
					session.setAttribute("limit", limit); 
				}
		
				System.out.println("limit = " + limit);
				System.out.println("page = " + page);
				
				int listcount = scrapservice.getOscrapCount(mem_no); //oboard게시판에서 스크랩한 게시글 수
		
				int maxpage = (listcount + limit - 1) / limit;
		
				int startpage = ((page - 1) / 10) * 10 + 1;
		
				int endpage = startpage + 10 - 1;
		
				if (endpage > maxpage)
					endpage = maxpage;
		
				if (endpage < page)
					page = endpage;
				
				Map m = new HashMap();
				m.put("page", page);
				m.put("limit", limit);
				
				List<ObsBean> obslist = scrapservice.getObsScrap(mem_no);
				
				ModelAndView mv = new ModelAndView("html_mypage/scrap/o_myScrap");
				
				mv.addObject("page", page);
				mv.addObject("maxpage", maxpage);
				mv.addObject("startpage", startpage);
				mv.addObject("endpage", endpage);
				mv.addObject("listcount", listcount);
				mv.addObject("obslist", obslist);
				mv.addObject("limit", limit);
				mv.addObject("mem_no",mem_no);
				
				return mv;
			}
		
/*eboard 스크랩*/
			@RequestMapping(value = "/e_sc_view.brn")
			public ModelAndView e_scrap(HttpServletRequest request, 
											HttpServletResponse response, 
											HttpSession session) throws Exception {
		
				//String mem_id = (String)session.getAttribute("mem_id");
				int mem_no = (Integer)session.getAttribute("mem_no");
				
				//System.out.println(mem_id);
				System.out.println(mem_no);
				
				int page = 1;
				int limit = 10;
		
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
		
				if (session.getAttribute("limit") != null) {
					limit = (Integer) session.getAttribute("limit");
				}
		
				if (request.getParameter("limit") != null) {
					limit = Integer.parseInt(request.getParameter("limit"));
					session.setAttribute("limit", limit); 
				}
		
				System.out.println("limit = " + limit);
				System.out.println("page = " + page);
				
				int listcount = scrapservice.getEscrapCount(mem_no); //eboard게시판에서 스크랩한 게시글 수
		
				int maxpage = (listcount + limit - 1) / limit;
		
				int startpage = ((page - 1) / 10) * 10 + 1;
		
				int endpage = startpage + 10 - 1;
		
				if (endpage > maxpage)
					endpage = maxpage;
		
				if (endpage < page)
					page = endpage;
				
				Map m = new HashMap();
				m.put("page", page);
				m.put("limit", limit);
				
				List<EboardBean> ebslist = scrapservice.getEbsScrap(mem_no);
				
				ModelAndView mv = new ModelAndView("html_mypage/scrap/e_myScrap");
				
				mv.addObject("page", page);
				mv.addObject("maxpage", maxpage);
				mv.addObject("startpage", startpage);
				mv.addObject("endpage", endpage);
				mv.addObject("listcount", listcount);
				mv.addObject("ebolist", ebslist);
				mv.addObject("limit", limit);
				mv.addObject("mem_no",mem_no);
				
				return mv;
			}
			
			/*eboard 스크랩*/
			@RequestMapping(value = "/g_sc_view.brn")
			public ModelAndView g_scrap(HttpServletRequest request, 
											HttpServletResponse response, 
											HttpSession session) throws Exception {
		
				//String mem_id = (String)session.getAttribute("mem_id");
				int mem_no = (Integer)session.getAttribute("mem_no");
				
				//System.out.println(mem_id);
				System.out.println(mem_no);
				
				int page = 1;
				int limit = 10;
		
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
		
				if (session.getAttribute("limit") != null) {
					limit = (Integer) session.getAttribute("limit");
				}
		
				if (request.getParameter("limit") != null) {
					limit = Integer.parseInt(request.getParameter("limit"));
					session.setAttribute("limit", limit); 
				}
		
				System.out.println("limit = " + limit);
				System.out.println("page = " + page);
				
				int listcount = scrapservice.getGscrapCount(mem_no); //eboard게시판에서 스크랩한 게시글 수
		
				int maxpage = (listcount + limit - 1) / limit;
		
				int startpage = ((page - 1) / 10) * 10 + 1;
		
				int endpage = startpage + 10 - 1;
		
				if (endpage > maxpage)
					endpage = maxpage;
		
				if (endpage < page)
					page = endpage;
				
				Map m = new HashMap();
				m.put("page", page);
				m.put("limit", limit);

				List<GbbsBean> gbslist = scrapservice.getGbsScrap(mem_no);
				System.out.println(gbslist);
				ModelAndView mv = new ModelAndView("html_mypage/scrap/g_myScrap");
				
				mv.addObject("page", page);
				mv.addObject("maxpage", maxpage);
				mv.addObject("startpage", startpage);
				mv.addObject("endpage", endpage);
				mv.addObject("listcount", listcount);
				mv.addObject("Gbbsgall", gbslist);
				mv.addObject("limit", limit);
				mv.addObject("mem_no",mem_no);
				
				return mv;
			}
			
		
}
