<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			
		<!--로그인 영역-->
		<div class="con_con">
					<!--회원가입 영역-->
					<h4 class="h4 mt15">비밀번호 찾기</h4>
					
					<div class="Agree_area">
						<p class="tit">
							ID 회원님의 등록된 메일 주소로 임시 비밀번호를 전송하였습니다.
						</p>
						<p>
							임시 비밀번호를 확인 후 다시 로그인해주세요.
						</p>
						<p class="tit">
							등록된 메일 주소:${member.mem_ma }
						</p>
						
						
						<ul>
							<li>▶ 임시 비밀번호는 회원 가입시의 이메일로 발송됩니다.</li>
							<li>▶ 기타 문의 사항은
							 <a href="#">Burning fat</a> 으로 문의해주세요.</li>
							
						</ul>
					
					<!-- 비밀번호 찾기 확인 -->
					
					<!--버튼-->
<!-- 					<div class="btnL_area mt25">
						<input type="submit" class="findBtn" value="확인">
						<input type="reset" class="findBtn" value="취소" />
					</div> -->
					
					
					<div class="btnL_area mt25">
						<ul class="login_fot">
							<li><a href="join1.jsp">회원가입</a></li>
							<li><a href="./Login.brn">로그인</a></li>
						</ul>
					</div>
					
					<!--//버튼-->	
					
				</div>
				<!--본문-->

				</div>
				</div>
	</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>