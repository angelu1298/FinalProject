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
				/*hidden으로 숨겨놓은 레시피 번호를 삭제 모달에 get방식으로 일러준다.*/
				var num = $(this).next().val();
				var tit = $(this).parents("dt").children("strong").text();
				$(".modal_bg").load("recipeAddToday.brn?rcp_no=" + num + "&rcp_tt=" + tit); 
			})
			
			/* 해당 리스트 삭제모달창 불러오기  */
			$(".btn_del_rec").click(function(){
				$(".modal_bg").removeClass("off");
				/*hidden으로 숨겨놓은 레시피 번호를 삭제 모달에 get방식으로 일러준다.*/
				var num = $(this).next().val();
				var tit = $(this).parents("dt").children("strong").text();
				$(".modal_bg").load("recipeDelete.brn?rcp_no=" + num + "&rcp_tt=" + tit); 
			})
			/* 자세히보기 열기  */
			$(".btn_view").click(function(){
				$(".recipe_list ul li").removeClass("on");
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
		<%@ include file="../inc/leftMenu01.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>나의 주스 만들기</h3>
			<h4>나의 주스 리스트</h4>
				
			<p class="srch_result">
				<!-- 리스트 갯수 -->
				<span class="rs_txt">전체<strong>${rcount}</strong>건 나의 레시피가 있습니다.</span>
			</p>
				
			<!--검색영역 form -->
			<div class="borad_srch">
			
				<!-- form -->
				<form method="get" action="recipeList.brn">
					<!--한줄-->
					<p class="col fr mr20"> 
						<label for="srch_sel01" class="sc_txt">검색영역</label> 
						<input type="text" name="findname" id="findname" class="w280 mr10" placeholder="검색어를 입력하세요"/>
						<input type="submit" class="btn_srch" value="검색" />
					</p>
					<!--한줄-->
				</form>
				<!-- //form -->
				
			</div>
			<!--//검색영역-->
			<!-- 리스트 -->
			<div class="food_list">	
				<!--등록된 레시피가 있을경우-->
				<c:if test="${rcount != 0}">
				<ul>
					<!-- 반복문 시작 li로 for문 돌아감 --> 
					<c:forEach var="r" items="${recipeList}">
					<li>
						
						<dl>
							<dt>
								<strong>${r.rcp_tt}</strong>
								<span>${r.rcp_nt}등으로 만들어진 ${r.rcp_cal}칼로리의 주스입니다.</span> 
								<p>
									<input type="button" class="btn_view" value="자세히보기"> 
									<input type="button" class="btn_del_rec" value="삭제">
									<input type="hidden" value="${r.rcp_no}">
								</p>
								<input type="button" class="btn_add_today" value="오늘 식단에 추가하기 "> 
							</dt>
							<!-- 자세히보기 버튼을 클릭했을 떄만 보여지는 부분. -->
							<dd>
								<ol>
									<li><span>탄수화물</span>${r.rcp_tan}g</li>
									<li><span>단백질</span>${r.rcp_dan}g</li>
									<li><span>지방</span>${r.rcp_gee}g</li>
									<li><span>나트륨</span>${r.rcp_na}mg</li>
									<li><span>콜레스테롤</span>${r.rcp_col}mg</li>
									<li><span>포화지방산</span>${r.rcp_poj}g</li>
									<li><span>트랜스지방산</span>${r.rcp_trs}g</li>
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
				<c:if test="${rcount == 0}">
					<div class="nolist">등록된 레시피가 없습니다.</div>
				</c:if>
				<!-- // 등록된 게시물이 없을경우-->
			</div>
			<!--// 전체리스트-->
				
			<!--검색영역 스크립트--> 
				
		</div>
		<!--서브컨텐츠 영역 END -->
		
	</sectio>
	<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
