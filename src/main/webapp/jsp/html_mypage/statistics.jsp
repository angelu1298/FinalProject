<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/subHeader.jsp" %>
<style>
    .graph { 
        position: relative; /* IE is dumb */
        width: 200px; 
        border: 1px solid #B1D632; 
        padding: 2px; 
		font-size:11px;
		font-family:tahoma;
		margin-bottom:3px;
    }
    .graph .bar { 
        display: block;
        position: relative;
        background: #B1D632; 
        text-align: center; 
        color: #333; 
        height: 2em; 
        line-height: 2em;            
    }
    .graph .bar span { position: absolute; left: 1em; }
</style>

	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<script>
			$(function(){
				$(".side_myinfo").load("my_info.brn");
			})
		</script>
		
		<div class="side_myinfo">
			
		</div>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			<div class="stat">
				<p><strong>회원 통계</strong></p>
				<div>Burn Fat 회원들의 이번 한 달 간의 평가 평균입니다.</div>
				<div class="graph">
					<c:if test="${!empty avrgPerM }">
					<!-- 100점 만점 환산 평가 점수 -->
					   <strong class="bar" style="width: ${avrgPerM/3*100 }%;">${avrgPerM }</strong>
					</c:if>
					<c:if test="${empty avrgPerM }">
						데이타가 없어여.... ㅠㅠ
					</c:if>
				</div>
				<div>${sessionScope.mem_id } 회원님의 이번 한 달 간의 평가 평균입니다.</div>
				<div class="graph">
					<c:if test="${!empty myRate }">
					   <strong class="bar" style="width: ${myRate/3*100 }%;">${myRate }</strong>
					</c:if>
					<c:if test="${empty myRate }">
						아직 평가를 한번도 안하셨군요!!
					</c:if>
				</div>
			</div>
			
		
		
		
		
		</div>		
		<!-- 서브컨텐츠 영역 END -->
	
	</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>
    