<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./resources/js/jquery.min.js"></script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">
			<!--회원가입 영역-->
			<div class="login_box">
					<div class="result">
					<h4 class="h4">아이디 찾기</h4>			
						<p class="tit">
							<span>회원님의 아이디는 아래와 같습니다.</span>
							<strong>${mem_id}</strong>	
						</p>
						<p class="tip">
							기타 문의 사항은<a href="./inquiry.brn">Burning fat</a> 으로 문의해주세요.
						</p>
						
					<!-- 아이디 찾기 확인 -->
					
					<!--버튼-->
					<div class="btnL_area mt50">
						<ul class="login_fot">
							<li><a href="./pwFind.brn">비밀번호 찾기</a></li>
							<li><a href="./Join.brn">회원가입</a></li>
							<li><a href="./Login.brn">로그인</a></li>
						</ul>
					</div>
					<!--//버튼-->	
					
				</div>
				<!--본문-->
				</div>
				</div>
	</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
