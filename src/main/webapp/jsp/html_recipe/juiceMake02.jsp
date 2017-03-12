<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.food.juice.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../inc/subHeader.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/juice.js"></script> 

	<script>
	
	$(function(){
		$(".btn_save").click(function(){
			$(".modal_bg").removeClass("off");
			$(".modal_bg").load("juiceMakeName.brn");
			$(".modal_bg").animate({ opacity: '1' }, 500);
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
			<h4>나의 주스 만들기</h4>
    		
    			<form action="juiceMakeResult.brn" method="post" id="recipeform">
    			
 				<div class="commtable">
						<p class="tip fr">총 <strong>${total}</strong> 종류의 과일 및 채소를 추가했습니다.</p>
						<table class="mt10">
						<colgroup>
							<col width="60%"/>
							<col width="40%"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
							<col width="50px"/>
						</colgroup>
						
						<thead>
							<tr>
								<th>과일 및 채소명</th>
								<th>1회 제공량</th>
								<th>칼로리</th>
								<th>탄수 화물</th>
								<th>지방</th>
								<th>단백질</th>
								<th>나트륨</th>
								<th>콜레스 테롤</th>
								<th>포화 지방산</th>
								<th>트랜스 지방산</th>
							</tr>
						</thead>
						
				    	<tbody>
							<c:forEach var="r" items="${Resultlist}">
							<tr>
								<th rowspan="2">${r.juc_tt}</th>
								<td>${r.juc_once}g기준</td>
								<td><fmt:formatNumber value="${r.juc_cal}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_tan}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_dan}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_gee}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_na}"  pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_col}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_poj}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_trs}" pattern="#0.00" /></td>
							</tr>
							<tr class="standard">
								<td>100g 기준</td>
								<td><fmt:formatNumber value="${r.juc_cal / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_tan / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_dan / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_gee / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_na  / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_col / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_poj / r.juc_once * 100}" pattern="#0.00" /></td>
								<td><fmt:formatNumber value="${r.juc_trs / r.juc_once * 100}" pattern="#0.00" /></td>
							</tr>
							</c:forEach>
						</tbody>
						
						<script>
						
							$(function(){

								var juc_once=0;
								var juc_cal=0;
								var juc_tan=0;
								var juc_dan=0;
								var juc_gee=0;
								var juc_na=0;
								var juc_col=0;
								var juc_poj=0;
								var juc_trs=0;

								$(".commtable table tbody tr.standard td:nth-child(1)").each(function(idx,item){
									juc_once += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(2)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_cal += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(3)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_tan += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(4)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_dan += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(5)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_gee += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(6)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_na  += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(7)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_col += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(8)").each(function(idx,item){
									var once = parseFloat( $(this).siblings("td:nth-child(2)").text());
									juc_poj += parseFloat($(this).text().trim())
								});
								
								$(".commtable table tbody tr.standard td:nth-child(9)").each(function(idx,item){
									var once = parseFloat($(this).siblings("td:nth-child(2)").text());
									juc_trs += parseFloat($(this).text().trim())
								});

								var total = "<tr><th>총 영양소</th>";
								total += "<td>" + juc_once + "g</td>";
								total += "<td>" + juc_cal.toFixed(2) + "</td>";
								total += "<td>" + juc_tan.toFixed(2) + "</td>";
								total += "<td>" + juc_dan.toFixed(2) + "</td>";
								total += "<td>" + juc_gee.toFixed(2) + "</td>";
								total += "<td>" + juc_na.toFixed(2)  + "</td>";
								total += "<td>" + juc_col.toFixed(2) + "</td>";
								total += "<td>" + juc_poj.toFixed(2) + "</td>";
								total += "<td>" + juc_trs.toFixed(2) + "</td>";
								total += "</tr>";

								var avg = "<tr><th>한 컵 기준</th>";
								avg += "<td>" + 200 + "g</td>";
								avg += "<td><input type=\"text\" name=\"rcp_cal\" value=\"" + (juc_cal/juc_once *200).toFixed(2) + "\" readOnly  id=\"juc_cal\" /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_tan\" value=\"" + (juc_tan/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_dan\" value=\"" + (juc_dan/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_gee\" value=\"" + (juc_gee/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_na\"  value=\"" + (juc_na/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_col\" value=\"" + (juc_col/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_poj\" value=\"" + (juc_poj/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "<td><input type=\"text\" name=\"rcp_trs\" value=\"" + (juc_trs/juc_once *200).toFixed(2) + "\" readOnly /></td>";
								avg += "</tr>";
								
								$(".commtable table tfoot").html(total + avg);

								$(".commtable table tbody tr td:nth-child(1)").each(function(idx,item){
									 
								});
								var a = $("#juc_cal").val();
								$(".resultbox .summary01 strong").html(a);
								
								if( a >= 200 ) {
									$(".summary02").html("<span>칼로리가 매우 높은 편입니다. 다시 만드시겠습니까?<a href=\"juiceMake.brn\">다시 만들러 가기</a></span>");
								} if( a >= 200 ) {
									$(".summary02").html("<span>칼로리가 높은 편입니다.</span>");
								} else if( a >= 150 || a < 200 ){
									$(".summary02").html("<span>칼로리가 적당한 편입니다.</span>")
								} else if( a >= 100 || a < 150 ){
									$(".summary02").html("<span>칼로리가 낮은 편입니다.</span>")
								} else {
									$(".summary02").html("<span>칼로리가 매우 낮은 편입니다. 조금 더 맛있게 만들어 드셔도 괜찮을거 같습니다.다시 만드시겠습니까?<a href=\"juiceMake.brn\">다시 만들러 가기</a></span>")
								}
							})	
						
						</script>
						
						<!-- 결론 -->
						<tfoot> 
							<tr><td colspan="10">결과를 출력해줍니다.</td></tr>
						</tfoot>
						<!-- // 결론 -->
						
					</table>
					
					<div class="resultbox">
						<p class="summary01">
							<input type="text" value="<c:forEach var="r" items="${Resultlist}"> ${r.juc_tt} </c:forEach>" name="rcp_nt" readOnly />
							이 포함된 나의 주스는 <strong></strong>칼로리입니다.
						</p>
						<p class="summary02"></p>
						<!-- 전송할 데이터를 모달창에서 값을 받아서 전달해주는 공간 -->
						<p class="titlebox"></p>
					</div>
				
					<p class="btn_Larea">
						<a href="#none" onclick="javascript:history.back();" >주스 다시 만들기</a>
						<input type="button" class="btn_save" value="내 주스 리스트에 저장하기" />
					</p>
				
				</div>
				
				</form>
			
		</div>
		<!-- 서브컨텐츠 영역 END -->
	</section>
	<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
