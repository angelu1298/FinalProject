<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="/jsp/inc/mainHeader.jsp" %>
<c:if test="${!empty sessionScope.mem_id }">
<script> 
	$(function(){
		parent.document.getElementById('talkframe').src = "http://127.0.0.1:3000?mem_id=${sessionScope.mem_id}";
	})
</script>
</c:if>
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="main_container">
		
	</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
