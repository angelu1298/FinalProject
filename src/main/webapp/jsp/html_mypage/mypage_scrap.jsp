<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.burn.fat.board.gboard.model.*"%>
<%@ page import="com.burn.fat.board.sboard.model.*"%>
<%@ page import="com.burn.fat.board.eboard.model.*"%>
<%@ page import="com.burn.fat.board.fboard.model.*"%>
<%@ page import="com.burn.fat.board.oboard.model.*"%> 


	<%@ include file="../inc/subHeader.jsp" %>

	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%-- <%@ include file="../inc/myinfo.jsp" %> --%>
		<script>
			$(function(){
				$(".side_myinfo").load("my_info.brn");
			})
		</script>
		
		<div class="side_myinfo">
			
		</div>
		
		<div class="subContent">
		<!-- 서브컨텐츠 영역 START -->
			
		<%@ include file="../html_mypage/scrap/myScrap.jsp" %>
		
		<!-- 서브컨텐츠 영역 END -->
		</div>
	</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>
