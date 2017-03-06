<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>

		${sessionScope.mem_id }님 불타는 지방이에 회원가입에 성공하셨습니다!<br>
		앞으로 함께 열심히 다이어트를 해봐요!!
		
</section>
<!-- // container End -->
	
<%@ include file="/jsp/inc/footer.jsp" %>