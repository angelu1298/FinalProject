<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			
		<!--로그인 영역-->
		<div class="con_con">
					<!--회원가입 영역-->
					<div class="Step">
						<span class="off">1</span>
						<span class="on"><span class="num">2</span><span class="on_tit">회원탈퇴 사유</span></span>
						<span class="off">3</span>
					</div>
					<form action="#" method="get">
					<h4 class="h4 mt15">회원탈퇴 사유</h4>
					<div class="Agree_area">
						<p class="tit">
							남겨 주신 정보를 바탕으로 보다 나은 서비스를 제공해 드리기 위해 더욱 노력 하겠습니다. 
						</p>
						<!--회원탈퇴 사유 -->
						<div class="drop_check1">
						<ul class="drop_check2">
							<li><input type="checkbox" id="agree01"><label for="agree01">시스템 장애(속도저하, 잦은 에러 등)</label></li>
							<li><input type="checkbox" id="agree02"><label for="agree02">서비스 이용 불필요</label></li>
							<li><input type="checkbox" id="agree03"><label for="agree03">유사 서비스로의 이전</label></li>
							<li><input type="checkbox" id="agree04"><label for="agree04">이용할 만한 서비스 부족</label></li>
							<li><input type="checkbox" id="agree05"><label for="agree05">기타</label></li>
							<li><textarea rows="5" cols="70" style="border:1px solid black;"></textarea>
						</ul>
						
						
					</div>
					<!--버튼-->
					<div class="btnL_area mt25">
						<input type="submit" value="확인">
						<input type="reset" value="취소">
					</div>
					<!--//버튼-->	
					<!--//회원가입 영역-->
					</form>
				</div>
				<!--본문-->
	</section>
	<!-- // container End -->
	
	<%@ include file="/inc/footer.jsp" %>