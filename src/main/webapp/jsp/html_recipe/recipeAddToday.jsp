<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>

		<script src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
					
		<script>
			function dataCtrl($scope){
				$scope.rcp_tt;
				$scope.rcp_cal = ${param.rcp_cal};
				$scope.day;
				$scope.quantity = 1.0;
			}
		</script>
		
		<script>
			$(function(){
				$(".col strong input").val($(".rcp_cal").val()); 
				$(".btn_submit").click(function(){
					$("#addok").submit();
				});
				$(".btn_close").click(function(){
					$(".modal_bg").html();
					$(".modal_bg").addClass("off");
					$(".modal_bg").animate({"opacity":"0"});
				})
			})
		</script>
			
		<%
			request.setCharacterEncoding("UTF-8");
			String rcp_tt = request.getParameter("rcp_tt");
			String decodeResult = URLDecoder.decode(rcp_tt, "UTF-8");
		%>
		
		<div class="modal_area size_small">
		<h1>오늘의 섭취 목록에 추가<span class="btn_close">x</span></h1>
		
		<div class="layer_content" ng-controller="dataCtrl">
			<h2 class="mt20"><strong class="txtred">&lt;<%=decodeResult %> &gt;</strong>을(를)<br/>오늘 식단에 추가하시겠습니까?</h2>
			<!-- form -->
			<form method="get" action="cuisineAddTodayOk.brn" class="style_corfrim" id="addok">
			 	
	 			<p class="modal_btn_area">
					<input type="submit" value="추가하기" class="btn_submit" />
					<input type="reset" value="취소" class="btn_close" />
				</p>
				
			</form>
			<!--// form -->
			
		</div> 
	</div>
	
	