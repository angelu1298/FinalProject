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
					<h4 class="h4 mt15">아이디 찾기</h4>
					<div class="Agree_area">
						<p class="tit">
							회원님의 아이디는 아래와 같습니다.
						</p>
					
					<!-- 아이디 찾기 확인 -->
					
					<!--버튼-->
					<div class="btnL_area mt25">
						<input type="submit" value="로그인">
						<input type="reset" value="비밀번호 찾기">
					</div>
					<!--//버튼-->	
					
				</div>
				<!--본문-->
				</div>
				</div>
	</section>
	<!-- // container End -->
	
	<%@ include file="/inc/footer.jsp" %>