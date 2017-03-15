<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../inc/subHeader.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/local.js"></script> 

	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu03.jsp" %>
		<script>
			$(function(){
				
				$(".modal_bg").load("parkMap.brn");
	
			  	$(".btn_showmap").click(function(){
			  		
					$(".modal_bg").html();
					$(".modal_bg").removeClass("off"); 
					$(".modal_bg").animate({"opacity":"1"});
					
					var loc01 = Number($(this).siblings(".location01").val());
					var loc02 = Number($(this).siblings(".location02").val());
					var loctit = $(this).parent().siblings(".tit").text();
	
					$(".google_map > h1 > strong").text(loctit);
					
					var map = null;
					var myLatLng = { lat:loc01, lng:loc02 };
					
					map = new google.maps.Map(document.getElementById("map"), {
						center: {lat: loc01, lng: loc02},		// 맵 중심 위,경도
						zoom: 18,									// zoom 상태
						scrollwheel: true							// 스크롤 가능 여부
					});
					
			        var marker = new google.maps.Marker({
			          map: map,
			          position: myLatLng,
			          title: loctit
			        });
			
				 })
			})
		</script>
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>운동</h3>
			<h4>집주변 공원찾기</h4>
			
			<div class="mapArea">
				<div class="fl">
				 <div class="srchbox">
				 
				 		<span class="rs_txt">
					 		<span>전국에는 총</span>
					 		<strong>${parkcount}</strong>
					 		<span>곳의 공원이 있습니다.</span><br/>
					 		<span>&lt;&lt;${findsido}<c:if test="${findgungu!=''}">/${findgungu}</c:if></span>
					 		<span><c:if test="${findname!=''}">${findname}</c:if>&gt;&gt;조회한 결과, </span><br/>
			 				<strong>총 ${parkfcount}</strong>
					 		<span>곳의 공원이 있습니다.</span>
				 		</span>
				 	
						<form method="get" action="parkList.brn">
							<div class="fl">
								<!-- 시도선택 / js로 input과 label값을 입력하게 만듦 -->
								<div class="selbox">
									<p><input type="text" placeholder="시,도 단위 선택" readonly id="sido_txt" name="findsido" class="local_choice" /></p>
									<div class="sellayer sido" style="display: none;"></div>
								</div>
								<!-- 군구선택 / js로 input과 label값을 입력하게 만듦 -->
								<div class="selbox">
									<p><input type="text" placeholder="군,구 단위 선택" readonly id="gungu_txt" name="findgungu" class="local_choice" /></p>
									<div class="sellayer gungu" style="display: none;"></div>
								</div>
								<!-- 검색어입력 -->
								<input type="text" name="findname" id="findname" class="srchname" placeholder="검색어를 입력하세요" />
								<span class="btn_submit"><input type="submit" value="공원 찾아보기" /></span>
							</div>
						
							<!-- 제출하기 버튼 -->	
						</form>
					</div>
					<!--//검색영역-->
				</div>
				
				<div class="fr">
					<div class="map normal">
						<a href="javascript:void(0)" title="서울특별시" class="Map_seoul">서울특별시</a>
						<a href="javascript:void(0)" title="경기도" class="Map_gyeonggi">경기도</a>
						<a href="javascript:void(0)" title="충청남도" class="Map_chungnam">충청남도</a>
						<a href="javascript:void(0)" title="충청북도" class="Map_chungbuk">충청북도</a>
						<a href="javascript:void(0)" title="대전광역시" class="Map_daejeon">대전광역시</a>
						<a href="javascript:void(0)" title="강원도" class="Map_gangwon">강원도</a>
						<a href="javascript:void(0)" title="인천광역시" class="Map_incheon">인천광역시</a>
						<a href="javascript:void(0)" title="전라북도" class="Map_jeonbuk">전라북도</a>
						<a href="javascript:void(0)" title="경상남도" class="Map_gyeongnam">경상남도</a>
						<a href="javascript:void(0)" title="광주광역시" class="Map_gwangju">광주광역시</a>
						<a href="javascript:void(0)" title="대구광역시" class="Map_daegu">대구광역시</a>
						<a href="javascript:void(0)" title="전라남도" class="Map_jeonnam">전라남도</a>
						<a href="javascript:void(0)" title="울산광역시" class="Map_ulsan">울산광역시</a>
						<a href="javascript:void(0)" title="경상북도" class="Map_gyeongbuk">경상북도</a>
						<a href="javascript:void(0)" title="제주도" class="Map_jeju">제주도</a>
						<a href="javascript:void(0)" title="부산광역시" class="Map_busan">부산광역시</a>
					</div>
				</div>
			</div>
			<!-- 리스트 -->
			<div class="commtable">	
				<table>
				<colgroup>
					<col width="60px" />
					<col width="40%" />
					<col width="120px" />
					<col width="60%" />
					<col width="60px" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>공원이름</th>
						<th>공원이름</th>
						<th>공원주소</th>
						<th>지도보기</th>
					</tr>
				</thead>
				<!--등록된 레시피가 있을경우-->
				<c:if test="${parkfcount != 0}">
				<tbody>
					<!-- 반복문 시작 li로 for문 돌아감 --> 
					<c:set var="num" value="${parkfcount-(page-1)*limit}" />
					
					<c:forEach var="p" items="${parkList}">
						<tr>
						 	<td>${num}</td>
						 	<td class="tit">${p.prk_nm}</td>
						 	<td>${p.prk_ct}</td>
						 	<td>${p.prk_add}</td>
						 	<td class="al_c ">
						 		<input type="button" value="지도보기" class="btn_showmap" />
							 	<input type="hidden" id="park_no_${p.prk_no}" value="${p.prk_no}" name="prk_no"/>
							 	<input type="hidden" id="park_l_${p.prk_no}"  value="${p.prk_l}"  name="park_l" class="location01" />
							 	<input type="hidden" id="park_h_${p.prk_no}"  value="${p.prk_h}"  name="park_h" class="location02" />
						 	</td>
						 </tr>
						<c:set var="num" value="${num-1}" />
					</c:forEach>	
					<!-- // 반복문 끝 -->
				</tbody>
				</c:if>
				<!--// 등록된 게시물이 있을경우-->	
	
				<!--등록된 게시물이 없을경우-->
				<c:if test="${parkfcount == 0}">
					<div class="nolist">검색결과가 없습니다.</div>
				</c:if>
				<!-- // 등록된 게시물이 없을경우-->
				</table>
			</div>
			<!--// 전체리스트-->
				
			<!-- 페이징 -->				
			<div class="paginate">
				<p>
					<c:if test="${page <=1 }">
						<span>&lt;&lt;</span>
					</c:if>
					
					<c:if test="${page > 1 }">
						<a href="parkList.brn?page=${page-1}&findsido=${param.findsido}&findgungu=${findgungu}">&lt;</a>
					</c:if>			
		
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${a}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="parkList.brn?page=${a}&findsido=${param.findsido}&findgungu=${findgungu}">${a}</a>
						</c:if>
					</c:forEach>			
						
						<c:if test="${page >= maxpage }">
							<span>&gt;</span>
						</c:if>
						<c:if test="${page < maxpage }">
							<a href="parkList.brn?page=${page+1}&findsido=${param.findsido}&findgungu=${findgungu}">&gt;&gt;</a>
						</c:if>
					</p>
				</div>
				<!-- // 페이징 -->			
			<!--검색영역 스크립트--> 
				
		</div>
		<!--서브컨텐츠 영역 END -->
		
	</sectio>
	<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
