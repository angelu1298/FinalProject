<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%> 

<%
		response.setHeader("Pragma","No-cache"); //HTTP 1.0 
		response.setDateHeader ("Expires", 0); 
		response.setHeader ("Cache-Control", "no-cache"); 
	%>

<%--
		String result = "";	
		String outlogTime ="";
		try {
			result = "OFF";
			outlogTime = (String) session.getAttribute("s_loginTime"); 
			// 오브젝트 형으로 저장되기 때문에 캐스팅해주어야한당~
			
			if(!outlogTime.equals("")){
				result = " ON (로그인시간: " + outlogTime  + "로그인유지시간:" +  session.getMaxInactiveInterval() + ")";
			} else {
				result = " OFF ";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
--%> 

<nav class="gnb">

	<ul>
		<% if(true){ %> 
			<li><a href="/fat/html_membership/logout.jsp">LOGOUT</a></li>
		 	<li><a href="/fat/html_mypage/mypage_main.jsp">MYPAGE</a></li>
		<%} else { %>
		 	<li><a href="/fat/html_mypage/login.jsp?id=">LOGIN</a></li>
		<% } %>
	</ul> 
	
</nav>
