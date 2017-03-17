package com.burn.fat.main.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(RequestProcessingTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("핸들러는들어왔나요???");
		
		if(session.getAttribute("mem_no")==null){ // 세션에 값이 없을경우,

			System.out.println("세션null에 걸렸나요");
				
			PrintWriter out =response.getWriter();
			out.print("<script>alert('로그인이 필요한 페이지입니다.');</script>");
			
			response.sendRedirect("./Login.brn");
			
			return false;
			
		} else { // 세션에 값이 있을 경우, 정상적으로 action을 수행한다.
			
			System.out.println("여기들어오니? 세션값? : " + (String)session.getAttribute("mem_no") );
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}

}