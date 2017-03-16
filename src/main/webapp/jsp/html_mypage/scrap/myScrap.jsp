<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../inc/subHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->

	<!-- 서브컨텐츠 영역 START -->
	
		<h3>마이페이지</h3>
	
		<h4>Scrap</h4>
		<input type="button" value="자유게시판" id="obo_scrap"/>
		<input type="button" value="운동게시판" id="ebo_scrap"/>
		<input type="button" value="음식게시판" id="fbo_scrap"/>
		<input type="button" value="후기게시판" id="sbo_scrap"/>
		<input type="button" value="이미지게시판" id="gbo_scrap"/>
		
		<script>
			$(function(){
				$("#obo_scrap").click(function() {
					alert("oboard");
					$("#scrapview").load("./o_sc_view.brn");
				});
				
				$("#ebo_scrap").click(function() {
					alert("eboard");
					$("#scrapview").load("./e_sc_view.brn");
					<%-- <%@ include file="../../html_mypage/scrap/e_myScrap.jsp"%> --%>
				});
				

				$("#fbo_scrap").click(function() {
					alert("fbo_scrap");
					//$("#scrapview").load("./e_sc_view.brn");
					<%-- <%@ include file="../../html_mypage/scrap/e_myScrap.jsp"%> --%>
				});
				
				$("#sbo_scrap").click(function() {
					alert("sbo_scrap");
					//$("#scrapview").load("./e_sc_view.brn");
					<%-- <%@ include file="../../html_mypage/scrap/e_myScrap.jsp"%> --%>
				});
				
				$("#gbo_scrap").click(function() {
					alert("gbo_scrap");
					$("#scrapview").load("./g_sc_view.brn");
					<%-- <%@ include file="../../html_mypage/scrap/e_myScrap.jsp"%> --%>
				});
			})
		</script>
		
		<div id="scrapview">
		
		</div>
		
<%-- 
		<%@ include file="../../html_mypage/scrap/f_myScrap.jsp"%>
	
		<%@ include file="../../html_mypage/scrap/s_myScrap.jsp"%>
		<%@ include file="../../html_mypage/scrap/g_myScrap.jsp"%>
 --%>
	
	<!-- 서브컨텐츠 영역 END -->

<!-- // container End -->
<%@ include file="../../inc/footer.jsp"%>