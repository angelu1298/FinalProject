<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../inc/subHeader.jsp"%>
 
	<script>
	
		$(function(){
			/* 해당 리스트 삭제모달창 불러오기  */
			$(".btn_add_today").click(function(){
				$(".modal_bg").removeClass("off");
				$(".modal_bg").animate({"opacity":"1"});
				/*hidden으로 숨겨놓은 레시피 번호를 삭제 모달에 get방식으로 일러준다.*/
					
				var cus_no  = $(this).siblings(".cus_no").val();
				var cus_cal = $(this).siblings(".cus_cal").val();
				var cus_tt = encodeURI(encodeURIComponent($(this).parent("dt").children("strong").text());
				$(".modal_bg").load("cuisineAddToday.brn?cus_no=" + cus_no + "&cus_tt=" + cus_tt +  "&cus_cal=" + cus_cal); 
				
			})
			 	
			/* 자세히보기 열기  */
			$(".btn_view_large").click(function(){
				$(".food_list ul li").removeClass("on");
				$(this).parents("li").addClass("on");
			})
			
			/* 자세히보기 닫기  */
			$(".btn_cls").click(function(){
				$(this).parents("li").removeClass("on");
			})
			
		})
		
	</script>
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu02.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>FOOD BALANCE</h3>
			<h4>음식 영양소</h4>
				
			<p class="srch_result">
				<!-- 리스트 갯수 -->
				<span class="rs_txt">전체<strong>${cuisinefcount}</strong> / ${cuisinecount} 건의 검색결과가 있습니다.</span>
			</p>
				
			<!--검색영역 form -->
			<div class="borad_srch">
			
				<!-- form -->
				<form method="get" action="cuisineList.brn">
					<!--한줄-->
					<p class="col fr mr20"> 
						<label for="srch_sel01" class="sc_txt">검색영역</label> 
						<input type="text" name="findname" id="findname" class="w280 mr10" placeholder="검색어를 입력하세요"/>
						<input type="submit" class="btn_srch" value="검색" />
					</p>
					<!--한줄-->
						<input type="hidden" name="y" value=${y }>
						<input type="hidden" name="m" value=${m }>
						<input type="hidden" name="d" value=${d }>
				</form>
				<!-- //form -->
				
			</div>
			<!--//검색영역-->
			<!-- 리스트 -->
			<h5 class="tittip">
				모든 식품의 칼로리 정보를 갖고 있지 않으니, 유사한 식품으로 검색하시기 바랍니다
			</h5>
			<div class="food_list mt20">	
				<!--등록된 레시피가 있을경우-->
				<c:if test="${cuisinefcount != 0}">
				<ul>
					<!-- 반복문 시작 li로 for문 돌아감 --> 
					<c:forEach var="c" items="${cuisineList}">
					<li>
						<dl>
							<dt>
								<strong>${c.cus_tt}</strong>
								<span>1회 제공량 ${c.cus_once}g 기준 <br/>${c.cus_cal}칼로리(kcal)입니다.</span> 
								
								<% if(request.getParameter("wholeDay") != null) {%>
								<p>
									<input type="button" class="btn_view_large" value="자세히보기"> 
								</p>
								<input type="button" class="btn_add_today" value="오늘 식단에 추가하기 "> 
								<%} else {%>
								<p class="st2">
									<input type="button" class="btn_view_large redbtn" value="자세히보기"> 
								</p>
								<% } %>								

								<input type="hidden" class="cus_no"   value="${c.cus_no}">
								<input type="hidden" class="cus_cal"  value="${c.cus_cal}">
								<input type="hidden" class="cus_once" value="${c.cus_once}">
							</dt>
							<!-- 자세히보기 버튼을 클릭했을 떄만 보여지는 부분. -->
							<dd>
								<ol>
									<li><span>탄수화물</span>${c.cus_tan}g</li>
									<li><span>단백질</span>${c.cus_dan}g</li>
									<li><span>지방</span>${c.cus_gee}g</li>
									<li><span>당</span>${c.cus_swt}g</li>
									<li><span>나트륨</span>${c.cus_na}mg</li>
									<li><span>콜레스테롤</span>${c.cus_col}mg</li>
									<li><span>포화지방산</span>${c.cus_poj}g</li>
									<li><span>트랜스지방산</span>${c.cus_trs}g</li>
								</ol>	
								<!-- 클릭시, dd영역에  display none값을 갖고있는 li의 클래스가 추가된다. -->
								<input type="button" class="btn_cls" value="x"/>
							</dd>
						</dl>
					</li> 
					</c:forEach>
					<!-- // 반복문 끝 -->
				</ul>
				</c:if>
				<!--// 등록된 게시물이 있을경우-->	
	
				<!--등록된 게시물이 없을경우-->
				<c:if test="${cuisinefcount == 0}">
					<div class="nolist">
						<strong>등록된 식품명 및 요리명이 없습니다.</strong>
						<p class="tip">모든 식품의 칼로리 및 영양소 정보를 갖고 있지 않으니, 유사한 식품으로 검색하시기 바랍니다</p>
					</div>
				</c:if>
				<!-- // 등록된 게시물이 없을경우-->
			</div>
			<!--// 전체리스트-->
			<p class="stxt"><a href="http://www.foodsafetykorea.go.kr" target="_blank">[출처] 식품의약품안전처 식품안전나라</a></p>
			<!-- 페이징 -->				
			<div class="paginate">
				<p>
					<c:if test="${page <=1 }">
						<span>&lt;&lt;</span>
					</c:if>
					
					<c:if test="${page > 1 }">
						<a href="cuisineList.brn?page=${page-1}&findname=${param.findname}">&lt;</a>
					</c:if>			
		
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${a}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="cuisineList.brn?page=${a}&findname=${param.findname}">${a}</a>
						</c:if>
					</c:forEach>			
						
					<c:if test="${page >= maxpage }">
						<span>&gt;</span>
					</c:if>
					<c:if test="${page < maxpage }">
						<a href="cuisineList.brn?page=${page+1}&findname=${param.findname}">&gt;&gt;</a>
					</c:if>
				</p>
			</div>
			<!-- // 페이징 -->			
				
		</div>
		<!--서브컨텐츠 영역 END -->
		
	</sectio>
	<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
