<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	
	<%@ page session='true'%>
	<%@ page import="java.util.Calendar" %>
	<%
		Calendar c = Calendar.getInstance();
		int hour=c.get(Calendar.HOUR_OF_DAY);
		int minute=c.get(Calendar.MINUTE);
		int second=c.get(Calendar.SECOND);
	%> 
	
<<<<<<< HEAD
	<%@ include file="/jsp/inc/logHeader.jsp" %>
=======
	<%@ include file="../inc/logHeader.jsp" %>
>>>>>>> branch 'suhyun' of https://github.com/angelu1298/FinalProject
	
	<div class="log_container">
		
		<h1>MAC</h1>
		<div class="login_box">
		
			<h2>MAC cosmetic Intranet </h2>
			<form action="/jspexam/main_login.jsp" method="post" >
			
				<input type="hidden" name="loginTime" value="<%=hour%>시 <%=minute%>분  <%=second%>초"/>
				
				<ul>
					<li><strong>USERNAME</strong><input type="text" name="id" autocomplete="off" /></li>
					<li><strong>PASSWORD</strong><input type="password" name="passwd" autocomplete="off" /></li>
				</ul>
				
				<p class="btnarea"><input type="submit" value="LOGIN" class="logbtn"/></p>
			
			</form>
			
		</div>
		
	</div>
	 