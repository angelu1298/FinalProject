<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<li><a href="./idFind.brn">아이디 찾기</a></li>
					<li><a href="./pwFind.brn">비밀번호 찾기</a></li>

				</ul>
			</li>
			
		</c:if>
		<c:if test="${!empty sessionScope.mem_id }" >
		
			
	
			<li><a href="./Modify.brn">회원정보수정</a></li>
			
			<li><a href="/fat/html_membership/memDrop.jsp">회원탈퇴</a></li>
			
		</c:if>
	</ul>

</nav>

<!-- // snb -->