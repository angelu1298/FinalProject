<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">

			<div class="message_box">
		
				<h4>LOGOUT</h4>
				<h5>정상적으로 로그아웃 되었습니다.</h5>
				
				<ul>
					<li><a href="login.jsp">로그인하기</a></li>
					<li><a href="/">메인으로 가기</a></li>
				</ul>
		
			</div>

	</div>
	<!--//로그인 영역-->
	</div>
	<!-- 서브컨텐츠 영역 END -->

</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
