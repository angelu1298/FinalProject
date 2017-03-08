<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

	</ul>

</nav>

<!-- // snb -->