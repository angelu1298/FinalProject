package com.burn.fat.board.eboard.action;

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

import com.burn.fat.board.eboard.dao.EboardService;
import com.burn.fat.board.eboard.model.EboardBean;
import com.burn.fat.board.eboard.model.EcommBean;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class EboardAction {
	
	@Autowired
	public EboardService eboService;
	
	private String saveFolder="C:/Users/angel/git/FinalProject/src/main/webapp/resources/upload"; //�뙆�씪 ���옣�떆�궗 寃쎈줈
	/* �뒪�겕�옪 */
	@RequestMapping(value = "/eboardscrap.brn")
	public void sboardscrap(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "e_no", required = true) int e_no) throws Exception {

		HttpSession session = request.getSession();
		String mem_no = String.valueOf(session.getAttribute("mem_no"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("e_no", e_no);
		map.put("mem_no", mem_no);
		int check = 0;
		String e_lkno = this.eboService.checkscrap(e_no);
		if (e_lkno != null) {
			StringTokenizer token = new StringTokenizer(e_lkno, ",");
			while (token.hasMoreTokens()) {
				if (token.nextToken().equals(mem_no)) {
					check = 1;
				}
			}
			System.out.println("1." + e_lkno);
		} else {
			System.out.println("e_lkno is null");
		}
		int result = 0;
		if (check != 1) {
			result = this.eboService.likeCountUp(map);
			System.out.println(result);
		}
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	
	/* �옄猷뚯떎 �엯�젰�뤌 */
	@RequestMapping(value="/eboardWrite.brn")
	public String eboInsert(){
		return "html_community/eboard/eboardWrite";
	}
	
	
	/* �옄猷뚯떎 ���옣 */
	//泥⑤��뙆�씪 �겢由��븷 寃쎌슦 �씠誘몄� 蹂닿퀬�옄 �븷  寃쎌슦 : �옄�룞 �깉濡� 怨좎묠 �꽕�젙
	//(window-> Preferencs -> workspce -> 
	// Refresh using native hooks or polling 泥댄겕)
	//  �븯怨� 5珥� �젙�룄 吏��궃 �뮘 �솗�씤�븯�꽭�슂
	@RequestMapping(value="/ebo_write_ok.brn", method=RequestMethod.POST)
	public ModelAndView ebo_write_ok(
						HttpServletRequest request, 
						HttpServletResponse response) throws Exception{
		
		EboardBean ebobean=new EboardBean();
		int fileSize=5*1024*1024; //�씠吏꾪뙆�씪 理쒕� �뾽濡쒕뱶 �겕湲�	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String mem_id = (String)session.getAttribute("mem_id");
		if(mem_id==null){
			out.print("<script>alert('濡쒓렇�씤 �썑 �씠�슜�븷 �닔 �엳�뒿�땲�떎.');");
			out.print(" history.back()</script>");
			
			return null;
		}
		int result = 0;
		
		String e_sj=multi.getParameter("e_sj").trim();
		String e_ct=multi.getParameter("e_ct").trim();
		
		File UpFile=multi.getFile("e_fl");
		if(UpFile != null){//泥⑤��븳 �씠吏꾪뙆�씪�씠 �엳�떎硫�
			String fileName=UpFile.getName();//�씠吏꾪뙆�씪紐� ���옣

			Calendar c=Calendar.getInstance();
			int year=c.get(Calendar.YEAR);      //�삤�뒛 �뀈�룄 援ы빀�땲�떎.
			int month=c.get(Calendar.MONTH)+1;  //�삤�뒛 �썡 援ы빀�땲�떎.
			int date=c.get(Calendar.DATE);      //�삤�뒛 �씪 援ы빀�땲�떎.
			
            String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
            System.out.println("homedir = " + homedir);
            //upload�뤃�뜑 �븘�옒�뿉 �뙆�씪 �삱由� �궇吏쒕줈 �뤃�뜑 �깮�꽦�빀�땲�떎.
            File path1=new File(homedir);
            if(!(path1.exists())){
            	path1.mkdir();//�깉濡쒖슫 �뤃�뜑瑜� �깮�꽦
            }
            //�궃�닔瑜� 援ы빀�땲�떎.
            Random r=new Random();
            int random=r.nextInt(100000000);
            
            /****�솗�옣�옄 援ы븯湲� �떆�옉 ****/
			int index = fileName.lastIndexOf(".");
			//臾몄옄�뿴�뿉�꽌 �듅�젙 臾몄옄�뿴�쓽 �쐞移� 媛�(index)瑜� 諛섑솚�븳�떎.
			//indexOf媛� 泥섏쓬 諛쒓껄�릺�뒗 臾몄옄�뿴�뿉 ���븳 index瑜� 諛섑솚�븯�뒗 諛섎㈃,
			//lastIndexOf�뒗 留덉�留됱쑝濡� 諛쒓껄�릺�뒗 臾몄옄�뿴�쓽 index瑜� 諛섑솚�빀�땲�떎.
			//(�뙆�씪紐낆뿉 �젏�뿉 �뿬�윭媛� �엳�쓣 寃쎌슦 留� 留덉�留됱뿉 諛쒓껄�릺�뒗 臾몄옄�뿴�쓽 �쐞移섎�� 由ы꽩�빀�땲�떎.)
			System.out.println("index = " +  index);
			
			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " +  fileExtension);
			/****�솗�옣�옄 援ы븯湲� �걹 ***/
			//�깉濡쒖슫 �뙆�씪紐낆쓣 ���옣
			String refileName="eboard"+year+month+date+random+"."+
					fileExtension;
			System.out.println("refileName = " + refileName);
			
			 //�삤�씪�겢 �뵒鍮꾩뿉 ���옣�맆 �젅肄붾뱶 媛�
            String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
            System.out.println("fileDBName = " + fileDBName);
           
            //�뙆�씪紐� 蹂�寃쏀빀�땲�떎.
            UpFile.renameTo(new File(homedir+"/"+refileName));
            System.out.println("homedir / refileName  = " + homedir+"/"+
            refileName);
            
           
            ebobean.setE_fl(fileDBName);
		}
		
		int e_no = 1;
		
		result = this.eboService.getListCount();//珥� 寃뚯떆臾� �닔 
		
		if(result!=0){
			result = eboService.getNo(); //理쒕�媛� 踰덊샇 
			e_no = result + 1;
		}
		
		ebobean.setMem_id(mem_id);
		ebobean.setE_sj(e_sj);
		ebobean.setE_ct(e_ct);
		ebobean.setE_no(e_no);
		eboService.insertEbo(ebobean);
		
/*		mv.setViewName("html_community/eboardList");*/
		response.sendRedirect("ebo_list.brn");
		return null;
		
	}
		
	
	/* �옄猷뚯떎 紐⑸줉 */
	@RequestMapping(value="/ebo_list.brn")
	public ModelAndView ebo_list(
					HttpServletRequest request,
					HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		int page=1;
		int limit=10; //珥덇린媛�
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		
		//珥� 由ъ뒪�듃 �닔 諛쏄린
		int listcount = eboService.getListCount();
		
		//珥� �럹�씠吏� �닔
		int maxpage = (listcount + limit - 1)/limit;
		
		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� �떆�옉 �럹�씠吏� �닔(1, 11, 21 �벑...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� 留덉�留� �럹�씠吏� �닔(10, 20, 30 �벑 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
 		if (endpage < page) page = endpage;
 		
 		Map<String,Integer> m = new HashMap<String,Integer>();
 		m.put("page", page);
 		m.put("limit", limit);
 		//由ъ뒪�듃 諛쏄린
 		List<EboardBean> ebolist = eboService.getEboList(m);
 		
 		
 		ModelAndView model = new ModelAndView("html_community/eboard/eboardList");
 		model.addObject("page",page);
 		model.addObject("maxpage", maxpage);
 		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("ebolist", ebolist);
		model.addObject("limit", limit);
		
		return model;	
	}
	
	
	/* 紐뉗쨪蹂닿린 */
	@RequestMapping(value="/eboardList.brn", method=RequestMethod.POST)
	public ModelAndView eboardList(
					HttpServletRequest request,
					HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		
		int page=1;
		int limit=10; //珥덇린媛�
		
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("page="+page);
		
		//////////20以꾨낫湲� 10以꾨낫湲� 
		//�씠�쟾�뿉 �꽕�젙�맂 limit媛� �엳�뒗吏� 泥댄겕
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		
 		//蹂�寃쎈맂 limit媛� �엳�뒗吏� 泥댄겕
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		////////////
 		System.out.println("limit = " + limit);
 		
		
		//珥� 由ъ뒪�듃 �닔 諛쏄린
		int listcount = eboService.getListCount();
		
		//珥� �럹�씠吏� �닔
		int maxpage = (listcount + limit - 1)/limit;
		
		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� �떆�옉 �럹�씠吏� �닔(1, 11, 21 �벑...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� 留덉�留� �럹�씠吏� �닔(10, 20, 30 �벑 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
 		if (endpage < page) page = endpage;
 		
 		Map m = new HashMap();
 		m.put("page", page);
 		m.put("limit", limit);
 		//由ъ뒪�듃 諛쏄린
 		List<EboardBean> ebolist = eboService.getEboList(m);
 		
 		ModelAndView model = new ModelAndView("html_community/eboard/eboardList");
 		model.addObject("page",page);
 		model.addObject("maxpage", maxpage);
 		model.addObject("startpage", startpage);
		model.addObject("endpage", endpage);
		model.addObject("listcount", listcount);
		model.addObject("ebolist", ebolist);
		model.addObject("limit", limit);
		
		return model;	
	}
	
	
	
	/* �옄猷뚯떎 �궡�슜蹂닿린, �닔�젙�뤌, �떟蹂�湲��뤌, �궘�젣�뤌*/
	@RequestMapping(value="/eboardView.brn")
	public ModelAndView eboardView(
			@RequestParam(value="num", required=true) int num,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception{
	
		
		session=request.getSession();
		
		int page=1;
		if(request.getParameter("page") != null){
			page=Integer.parseInt(request.getParameter("page"));
			//parseInt()硫붿꽌�뱶濡� �젙�닔�삎 �닽�옄濡� 諛붽퓭�꽌 ���옣
		}
		
		ModelAndView mv = new ModelAndView();
		
		Map m = new HashMap();
		m.put("e_no", num);
		
		/*//肄붾찘�듃 遺덈윭�삤湲�
		List<EcommBean> ecommlist = this.eboService.getComm(num);
		mv.addObject("ecommlist",ecommlist);*/
		
		
		if(request.getParameter("elistcount") != null){
			int elistcount=Integer.parseInt(request.getParameter("elistcount"));
			mv.addObject("elistcount",elistcount);
		}
			
			
		if(request.getParameter("getcId") != null){
			String getcId=request.getParameter("getcId");
			mv.addObject("getcId",getcId);
		}
		
//		if(request.getParameter("getcommId") != null){
//			String getcommId=request.getParameter("getcommId");
//			mv.addObject("getcommId",getcommId);
//		}
	
		String state=request.getParameter("state");//援щ텇 �븘�뱶		
	
		
		
		
		/*	if(s_no!=1){*/
		EboardBean beanpre = this.eboService.getEboCont(num-1);
			mv.addObject("beanpre",beanpre);
	/*}*/
		
		EboardBean beannext = this.eboService.getEboCont(num+1);
		mv.addObject("beannext",beannext);
		
		EboardBean ebobean2=this.eboService.getEboCont(num);
		
		if(ebobean2==null){
			PrintWriter out =response.getWriter();
			out.print("<script>alert('議댁옱�븯吏� �븡�뒗 寃뚯떆臾쇱엯�땲�떎.'); history.back();</script>");
			return null;
		}else{
			
			if(state.equals("cont")){ //湲��궡�슜蹂닿린
				this.eboService.eboHit(num);//議고쉶�닔 利앷�
				
				/*//湲��궡�슜 以� �뿏�꽣�궎 移쒕�遺꾩쓣 �떎�쓬以꾨줈 媛쒗뻾 泥섎━
				String e_ct=ebobean.getE_ct().replace("\n","<br/>");
				
				mv.addObject("e_ct",e_ct);*/
				
				mv.setViewName("html_community/eboard/eboardView");
			}else if(state.equals("edit")){//�닔�젙�씪�뻹
				mv.setViewName("html_community/eboard/eboardEdit");
			}else if(state.equals("comm")){//肄붾찘�듃
				mv.setViewName("html_community/eboard/eboardView");
			}
			
			//醫뗭븘�슂
			if(request.getParameter("joayo") != null){
				int joayo=Integer.parseInt(request.getParameter("joayo"));
				mv.addObject("joayo",joayo);
			}
					
			//踰덊샇瑜� 湲곗��쑝濡� DB �궡�슜�쓣 媛��졇�샃�땲�떎.
			EboardBean ebobean=this.eboService.getEboCont(num);
			
			//湲��궡�슜 以� �뿏�꽣�궎 移쒕�遺꾩쓣 �떎�쓬以꾨줈 媛쒗뻾 泥섎━
			String e_ct=ebobean.getE_ct().replace("\n","<br/>");
			List<EcommBean> beanlist = eboService.getECommList(num);
			
			mv.addObject("ebobean", ebobean);
			mv.addObject("beanlist", beanlist);
			mv.addObject("e_ct",e_ct);
			
			mv.addObject("page", page);
			
			return mv;
		}
	}
	
	/* �옄猷뚯떎 �닔�젙 */
	@RequestMapping(value="/ebo_edit_ok.brn",
			method=RequestMethod.POST)
	public ModelAndView ebo_edit_ok(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		EboardBean ebobean=new EboardBean();
		response.setContentType("text/html;charset=UTF-8");
		int fileSize=5*1024*1024; //�씠吏꾪뙆�씪 理쒕� �뾽濡쒕뱶 �겕湲�	
		
		MultipartRequest multi=null;
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		HttpSession session = request.getSession();

		String mem_id = (String) session.getAttribute("mem_id");
		
		int e_no=Integer.parseInt(multi.getParameter("num"));
		int page=Integer.parseInt(multi.getParameter("page"));
		String e_sj=multi.getParameter("e_sj").trim();
		String e_ct=multi.getParameter("e_ct").trim();
		PrintWriter out=response.getWriter();
		
		//�뵒鍮꾨줈 遺��꽣 �궡�슜�쓣 媛��졇�샂
		EboardBean ebocont=this.eboService.getEboCont(e_no);
				
		File UpFile=multi.getFile("e_fl");
		   if(UpFile != null){//泥⑤��븳 �씠吏꾪뙆�씪�씠 �엳�떎硫�
			   String fileName=UpFile.getName();//泥⑤��븳 �씠吏꾪뙆�씪紐� ���옣
			   File DelFile=new File(saveFolder+ebocont.getE_fl());
			   if(DelFile.exists()) {
				   DelFile.delete();//湲곗〈 �씠吏꾪뙆�씪�쓣 �궘�젣
			   }
			   Calendar c=Calendar.getInstance();
			   int year=c.get(Calendar.YEAR);
			   int month=c.get(Calendar.MONTH)+1;
			   int date=c.get(Calendar.DATE);
			   
            String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
            File path1=new File(homedir);
            if(!(path1.exists())){
         	   path1.mkdir();//�깉濡쒖슫 �뤃�뜑瑜� �깮�꽦
            }
            Random r=new Random();
            int random=r.nextInt(100000000);
            
            /****�솗�옣�옄 援ы븯湲� �떆�옉 ****/
			int index = fileName.lastIndexOf(".");
			String fileExtension = fileName.substring(index + 1);
			/****�솗�옣�옄 援ы븯湲� �걹 ***/
			String refileName="eboard"+year+month+date+random+"."+
					fileExtension;//�깉濡쒖슫 �뙆�씪紐낆쓣 ���옣
         String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
         
         UpFile.renameTo(new File(homedir+"/"+refileName));

         ebobean.setE_fl(fileDBName);
		   }
		   
		ebobean.setE_no(e_no);
		ebobean.setMem_id(mem_id);
		ebobean.setE_sj(e_sj);
		ebobean.setE_ct(e_ct);
//		ebobean.setMem_id(mem_id);
		
		this.eboService.editEbo(ebobean);//�닔�젙硫붿꽌�뱶 �샇異�
		
		//get諛⑹떇�쑝濡� 3媛� �뙆�씪誘명꽣 �꽆�뼱媛�
		response.sendRedirect(
				"eboardView.brn?state=cont&page="+page+"&num="+e_no);
		
		return null;
		
	}
	
	/* �옄猷뚯떎 �궘�젣 */
	@RequestMapping(value="/ebo_delete_ok.brn")
	public ModelAndView ebo_delete_ok(
			@RequestParam("num") int e_no,
			@RequestParam("page") int page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		
		//湲�踰덊샇�뿉 �빐�떦�븯�뒗 �뵒鍮� �궡�슜�쓣 媛��졇�샃�땲�떎.
		EboardBean ebobean=this.eboService.getEboCont(e_no);
		
		//湲곗〈 �뙆�씪紐� 媛��졇�샃�땲�떎.
		String fname=ebobean.getE_fl();
		
		if(fname != null){//湲곗〈 �씠吏꾪뙆�씪�씠 議댁옱�븳�떎硫�
			File file=new File(saveFolder+fname);
			file.delete();//�꽌踰� �뤃�뜑濡� 遺��꽣 湲곗〈 �씠吏꾪뙆�씪 �궘�젣�빀�땲�떎.
		}
		
		this.eboService.deleteEbo(e_no);//�뵒鍮꾨줈 遺��꽣 �젅肄붾뱶 �궘�젣�빀�땲�떎.
		response.sendRedirect("ebo_list.brn?page="+page);
		
		return null;
	}
	
	
	/* �옄猷뚯떎 寃��깋 紐⑸줉 */
	@RequestMapping(value="/ebo_find_ok.brn", method=RequestMethod.GET)
	public ModelAndView ebo_find_ok(
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		HttpSession session = request.getSession();
		
		int page=1;
		int limit=10; //珥덇린媛�
		
		if(request.getParameter("page") != null){
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
		
		//////////20以꾨낫湲� 10以꾨낫湲� 
		//�씠�쟾�뿉 �꽕�젙�맂 limit媛� �엳�뒗吏� 泥댄겕
 		if(session.getAttribute("limit")!= null){
 			limit = (Integer)session.getAttribute("limit");
 		}
 		
 		//蹂�寃쎈맂 limit媛� �엳�뒗吏� 泥댄겕
 		if(request.getParameter("limit") != null){
 			limit=Integer.parseInt(request.getParameter("limit"));
 			session.setAttribute("limit", limit);
 		}
 		////////////
 		System.out.println("limit = " + limit);
 		
 		
		
 		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", page);
		m.put("limit", limit);
		m.put("find_field", find_field);
		m.put("find_name", "%"+find_name+"%");
		
		//珥� 由ъ뒪�듃 �닔 諛쏄린
		int listcount = this.eboService.getFindName(m);
		
		//珥� �럹�씠吏� �닔
		int maxpage = (listcount + limit - 1)/limit;
		
		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� �떆�옉 �럹�씠吏� �닔(1, 11, 21 �벑...)
 		int startpage = ((page-1) / 10) * 10 + 1;
 		
 		//�쁽�옱 �럹�씠吏��뿉 蹂댁뿬以� 留덉�留� �럹�씠吏� �닔(10, 20, 30 �벑 ...)
 		int endpage = startpage + 10 -1;
 		
 		if (endpage > maxpage) endpage= maxpage;
 		
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
		
		return model;	
		
	}
	
	/* 肄붾찘�듃 ���옣 */
	@RequestMapping(value="/ebo_comm.brn", method=RequestMethod.POST)
	public void ebo_comm(
			@RequestParam("e_no") int e_no,
			@RequestParam("ecomm_ct") String ecomm_ct,//湲��궡�슜 
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		System.err.println("肄붾찘�듃���옣�쑝濡� �삤�뒗媛��쑀");
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		
		EcommBean ecommbean = new EcommBean();
		ecommbean.setMem_id(mem_id);
		ecommbean.setEcomm_ct(ecomm_ct);
		ecommbean.setE_no(e_no);
		int result = this.eboService.insertEComm(ecommbean);
		System.err.println("result = "+result);
		this.eboService.changeEcommcnt(e_no);
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}
	@RequestMapping(value="/ecommList.brn")
	public ModelAndView scommList(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="e_no") int e_no) throws Exception{
		
		System.err.println("由ъ뒪�듃濡쒖삤�뒗媛�");
		List<EcommBean> beanlist = new ArrayList<EcommBean>();
		ModelAndView model=new ModelAndView("html_community/eboard/eboardComm");
		beanlist = this.eboService.getECommList(e_no);
		model.addObject("beanlist",beanlist);
		
		return model;
	}
	
	
	/* �떟湲����옣*/
	@RequestMapping(value="/ebo_reply.brn", method=RequestMethod.POST)
	public void ebo_reply(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("e_no") int e_no,
			@RequestParam("ecomm_no") int ecomm_no,
			@RequestParam("ecomm_ct") String ecomm_ct
			) throws Exception{
		
			System.err.println("�떟湲����옣�삤�뒗媛�..");
			EcommBean ecommb=new EcommBean();
			HttpSession session = request.getSession();
			String mem_id = (String) session.getAttribute("mem_id");
			
		  
		   ecommb.setMem_id(mem_id);
		   ecommb.setE_no(e_no);
		   ecommb.setEcomm_no(ecomm_no);
		   ecommb = this.eboService.getEComment(ecommb);
			int ecomm_re_ref = ecommb.getEcomm_re_ref();
			int ecomm_re_lev = ecommb.getEcomm_re_lev();
			int ecomm_re_seq = ecommb.getEcomm_re_seq();
			ecomm_re_lev = ecomm_re_lev+1;
			ecomm_re_seq =ecomm_re_seq+1;
			ecommb.setEcomm_ct(ecomm_ct);
			ecommb.setEcomm_re_ref(ecomm_re_ref);
			ecommb.setEcomm_re_lev(ecomm_re_lev);
			ecommb.setEcomm_re_seq(ecomm_re_seq);
			this.eboService.insertECommRep(ecommb);
			
		   
	}

	/* 肄붾찘�듃 �궘�젣*/
	@RequestMapping(value="/ebo_reply_del.brn")
	public void ebo_reply_del(
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
		 List<EcommBean> ecommreflist = new ArrayList<EcommBean>();
		 bean.setE_no(e_no);
		 bean.setEcomm_no(ecomm_no);
		 ecommreflist=this.eboService.getcommentref(bean);
		 
	      int result =0;
	      if(ecommreflist.size()>=2){ //肄붾찘�듃踰덊샇瑜� 李몄“�븯�뒗 �떟湲�踰덊샇媛� 肄붾찘�듃踰덊샇�옉 媛숈쑝硫�   2媛쒖씠�긽
	         result =this.eboService.deleteCommExistRep(m);
	      }else{
	         result =this.eboService.deleteComm(m);
	      }
	    PrintWriter out = response.getWriter();
	    out.print(result);
			
	}
	
	
	
}

