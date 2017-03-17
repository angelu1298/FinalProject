package com.burn.fat.food.grocery.action;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.burn.fat.food.grocery.dao.GroceryServiceImpl;
import com.burn.fat.food.grocery.model.GroceryBean;
import com.burn.fat.member.mypage.calendar.dao.CalendarService;
import com.burn.fat.member.mypage.calendar.model.CalendarBean;

@Controller
public class GroceryAction {

   CalendarBean calendarBean;
   
   @Autowired
   private GroceryServiceImpl groceryService;  

   @Autowired
   public CalendarService calService;
   
 
   /* 주스 1인분 오늘 식단에 추가하기 */
   @RequestMapping(value="/groceryAddToday.brn")
   public String groceryAddToday(){
      return "html_food/groceryAddToday"; 
   }
   
   /* GROCERY 오늘의 식단에 추가 모달창 */
   @RequestMapping(value="/groceryAddTodayOk.brn")
   public ModelAndView groceryAddTodayOk(
            @RequestParam("grc_kcal") int grc_kcal, 
            @RequestParam("grc_tt") String grc_tt, 
            @RequestParam("wholeDay") String wholeDay,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws Exception{
      
      response.setContentType("text/html;charset=UTF-8");
      
      int mem_no = (Integer)session.getAttribute("mem_no");
      
      Map m2 = new HashMap();
      
      System.err.println("wholeDay = " + wholeDay);
      System.err.println("grc_kcal = " + grc_kcal);
      System.err.println("grc_tt = " + grc_tt);
      System.err.println("mem_no = " + mem_no);
      
      String[] grc_ttSp =grc_tt.split(",");
      grc_tt = grc_ttSp[0];

      m2.put("day", wholeDay);
      m2.put("grc_kcal", grc_kcal);
      m2.put("grc_tt", grc_tt);
      m2.put("mem_no", mem_no);
      
      String y = calendarBean.getImsiY();
      String m = calendarBean.getImsiM();
      String d = calendarBean.getImsiD();
      
      String cal_date = y+m+d;
      m2.put("cal_date", cal_date);
      System.err.println("cal_date = " + cal_date);
      
      List<CalendarBean> ecalExist =this.calService.getE_kcal2(m2);
      
      //마이페이지에 식단 저장
      if(!ecalExist.isEmpty()){
    	  if(ecalExist.get(0).getGrc_kcal()!=0)
    		  this.calService.setGroceryAdd(m2);
    	  else
    		  this.calService.setGrocery(m2);
      }else
    	  this.calService.setGroceryIn(m2);
      
      
      response.sendRedirect("goweekly.brn?y="+y+"&m="+m+"&d="+d);
      
      return null;
   }
   
   /* GROCERY 목록 */
   @RequestMapping(value="/groceryList.brn")
   public ModelAndView groceryList(HttpServletRequest request, HttpServletResponse response
         , @RequestParam(value="limit", defaultValue="50") int limit
         , @RequestParam(value="findname", defaultValue="") String findname ) throws Exception{

      
       HttpSession session = request.getSession();
      findname = request.getParameter("findname");
      List<GroceryBean> groceryList = new ArrayList<GroceryBean>();
      
      int page = 1;
      
      /*****************날짜저장*********************/
      calendarBean = new CalendarBean();
      
      if(request.getParameter("y") != null){
         String y = request.getParameter("y");
         calendarBean.setImsiY(y);
         
      }
      
      if(request.getParameter("m") != null){
         String m = request.getParameter("m");
         calendarBean.setImsiM(m);
      }
      
      if(request.getParameter("d") != null){
         String d = request.getParameter("d");
         calendarBean.setImsiD(d);
      }
      
      /********************************************/
      
       if(request.getParameter("page") != null){
         page=Integer.parseInt(request.getParameter("page"));
      }
       
       if(request.getParameter("limit") != null){
          limit=Integer.parseInt(request.getParameter("limit"));
          session.setAttribute("limit", limit); //session에 limit를 저장하면 페이징처리시 쿼리스트링으로 limit값을 넘겨주지 않아도 된다.
       } 
        
      Map mf = new HashMap();
      // 검색어   
      mf .put("findname", findname);

      int grocerycount = groceryService.getGrocerylistCount(); //총 리스트 수를  
      int groceryfcount = groceryService.getGroceryfindlistCount(mf); //총 리스트 수를  
      
      //총 페이지 수
      int maxpage = (groceryfcount + limit-1) / limit;
                   
      //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
      int startpage = ((page-1) / limit) * limit + 1;

      //현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
      int endpage = startpage + 10 - 1;
      if (endpage > maxpage) endpage = maxpage;
      if (endpage < page) page = endpage;  

      Map m = new HashMap();
      
      // 페이징처리
      m.put("page", page);
      m.put("limit", limit);  
      // 검색어   
      m.put("findname", findname);

      // 공원갯수
      m.put("grocerycount", grocerycount);
      m.put("groceryfcount", groceryfcount);
      
      groceryList = groceryService.getGroceryList(m);
      ModelAndView model = new ModelAndView("html_food/groceryList");
      
      model.addObject("groceryList", groceryList);
      model.addObject("groceryfcount", groceryfcount);
      model.addObject("grocerycount", grocerycount);
      model.addObject("page", page);
      model.addObject("maxpage", maxpage);
      model.addObject("startpage", startpage);
      model.addObject("endpage", endpage);
      model.addObject("limit", limit);
      
      return model;
      
   }

}