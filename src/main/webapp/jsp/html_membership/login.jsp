<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ include file="/jsp/inc/logHeader.jsp"%>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">
		<form action="./Login_ok.brn" method="post">

			<div class="login_box">
				<ul class="login_inp">
					<li><input type="text" id="check_id" name="check_id" placeholder="아이디"></li>
					<li><input type="password" id="check_pass" name="check_pass" placeholder="비밀번호"></li>
				</ul>
				<input type="submit" class="logBtn" value="로그인">
			</div>

			<ul class="login_fot">
				<li><a href="idFind.jsp">아이디 찾기</a></li>
				<li><a href="pwFind.jsp">비밀번호 찾기</a></li>
				<li><a href="join1.jsp">회원가입</a></li>
			</ul>

		</form>
	</div>
	<!--//로그인 영역-->
	</div>
	<!-- 서브컨텐츠 영역 END -->

</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
=======
<%@ include file="../inc/logHeader.jsp"%>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">
		<form action="" method="get">

			<div class="login_box">
				<ul class="login_inp">
					<li><input type="text" placeholder="아이디"></li>
					<li><input type="password" placeholder="비밀번호"></li>
				</ul>
				<input type="submit" href="#" class="logBtn" value="로그인">
			</div>

			<ul class="login_fot">
				<li><a href="./IdFind.brn">아이디 찾기</a></li>
				<li><a href="./PwFind.brn">비밀번호 찾기</a></li>
				<li><a href="join1.jsp">회원가입</a></li>
			</ul>

		</form>
	</div>
	<!--//로그인 영역-->
	</div>
	<!-- 서브컨텐츠 영역 END -->

</section>
<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
>>>>>>> branch 'suhyun' of https://github.com/angelu1298/FinalProject
