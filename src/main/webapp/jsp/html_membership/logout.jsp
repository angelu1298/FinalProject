<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="log_container">
<script>
	parent.document.getElementById('talkframe').src = 'http://127.0.0.1:3000';
</script>
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">
		
			<h4 class="ml50">LOGOUT</h4>
			<div class="login_box">
			
				<h5>정상적으로 로그아웃 되었습니다.</h5>
				
				<div class="btnS_area mt20">
					<a href="./Login.brn">로그인하기</a>
					<a href="./Main.brn">메인으로 가기</a>
				</div>
				
			</div>
			
		</div>
	<!--//로그인 영역-->
	</div>
	<!-- 서브컨텐츠 영역 END -->

</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
