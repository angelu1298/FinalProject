<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

	<%@ include file="/jsp/inc/subHeader.jsp" %>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/myinfo.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>마이페이지</h3>
			
			<h4>주간 일지</h4>
			<%@ include file="/jsp/html_mypage/week_list.jsp" %>
		
		</div>		
		<!-- 서브컨텐츠 영역 END -->
	
	</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %> 
