<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  snb -->
<nav class="snb">

	<!-- 페이지 제목 -->
	<h2 class="subtit">Membership</h2>

	<ul>

		<c:if test="${empty sessionScope.mem_id }">
		
			<li><a href="./Login.brn">로그인</a></li>
			<li><a href="./Join.brn">회원가입</a></li>
			<li><a href="#none">아이디,비밀번호 찾기</a>
				<ul>
					<li><a href="./IdFind.brn">아이디 찾기</a></li>
					<li><a href="./PwFind.brn">비밀번호 찾기</a></li>
				</ul>
			</li>
			
		</c:if>
		<c:if test="${!empty sessionScope.mem_id }" >
		
			
	
			<li><a href="./Modify.brn">회원정보수정</a></li>
			
			<li><a href="/fat/html_membership/memDrop.jsp">회원탈퇴</a></li>
			
		</c:if>
=======

<!--  snb -->
<nav class="snb">

	<!-- 페이지 제목 -->
	<h2 class="subtit">Membership</h2>

	<ul>

		<%if(false){%>
		
			<li><a href="/fat/html_membership/login.jsp">로그인</a></li>
			
		<%} else {%>
		
			<li><a href="/fat/html_membership/join1.jsp">회원가입</a></li>
			
			<li><a href="#none">아이디,비밀번호 찾기</a>
				<ul>
					<li><a href="/fat/html_membership/idFind.jsp">아이디 찾기</a></li>
					<li><a href="/fat/html_membership/pwFind.jsp">비밀번호 찾기</a></li>
				</ul>
			</li>
	
			<li><a href="/fat/html_membership/join2.jsp">회원정보수정</a></li>
			
			<li><a href="/fat/html_membership/memDrop.jsp">회원탈퇴</a></li>
			
		<%} %>

>>>>>>> branch 'suhyun' of https://github.com/angelu1298/FinalProject
	</ul>

</nav>

<!-- // snb -->