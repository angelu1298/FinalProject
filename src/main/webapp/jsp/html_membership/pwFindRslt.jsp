<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			
		<!--로그인 영역-->
		<div class="login_area">
					<!--회원가입 영역-->
					<div class="login_box">
					<div class="result">	
					<h4 class="h4">비밀번호 찾기</h4>
					
						<p class="tit">
							ID 회원님의 등록된 메일 주소로 임시 비밀번호를 전송하였습니다.
						</p>
						<p class="tit">
							등록된 메일 주소:
						</p>
						
						
						<ul>
							<li>▶ 임시 비밀번호는 회원 가입시의 이메일로 발송됩니다.</li>
							<li>▶ 기타 문의 사항은
							 <a href="#">Burning fat</a> 으로 문의해주세요.</li>
							
						</ul>
					
					<!-- 비밀번호 찾기 확인 -->
					
					<!--버튼-->
<<<<<<< HEAD
					<div class="btnL_area mt25">
						<input type="submit" value="확인">
						<input type="reset" value="취소">
=======
<!-- 					<div class="btnL_area mt25">
						<input type="submit" class="findBtn" value="확인">
						<input type="reset" class="findBtn" value="취소" />
					</div> -->
					
					
					<div class="btnL_area mt25 w500">
						<ul class="login_fot">
							<li><a href="join1.jsp">회원가입</a></li>
							<li><a href="./Login.brn">로그인</a></li>
						</ul>
>>>>>>> branch 'suhyun' of https://github.com/angelu1298/FinalProject
					</div>
					<!--//버튼-->	
					
				</div>
				<!--본문-->
				</div>
				</div>
	</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
