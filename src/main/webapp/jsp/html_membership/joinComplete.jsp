
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
<c:if test="${!empty sessionScope.mem_id }">
<script> 
	$(function(){
		parent.document.getElementById('talkframe').src = "http://127.0.0.1:3000?mem_id=${sessionScope.mem_id}";
	})
</script>
</c:if>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>

		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			<div class="login_Complete_area">
				<div class="login_Complete_box">
					<p><strong>${sessionScope.mem_id }</strong> 님
					<strong>Burnning Fat</strong> 회원가입에 성공하셨습니다!<br>
					앞으로 함께 열심히 다이어트를 해봐요!!</p>
				</div>
			</div>
		</div>
		
</section>
<!-- // container End -->
	
<%@ include file="/jsp/inc/footer.jsp" %>

