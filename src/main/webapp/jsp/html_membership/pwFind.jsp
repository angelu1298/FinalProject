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
	
				<!--탭-->
				<div class="tabarea">
					<ul class="tabmenu">
						<li class="choice">휴대전화 번호로  찾기</li>
						<li class="right">이메일주소로 찾기</li> 
					</ul>
				</div>
				<!--//탭-->
				
			<form action="pwFindRslt.jsp" method="get" class="findForm01">
				<div class="login_box">
					<ul class="find_inp">
						<li>
							<strong>아이디</strong>
							<input type="text" class="w300" placeholder="아이디">
						</li>
						<li>	
							<strong>휴대전화번호</strong>
							<input type="text" class="w090" placeholder="">
							<span class="bttxt">-</span>
							<input type="text" class="w090" placeholder="">
							<span class="bttxt" >-</span>
							<input type="text" class="w090" placeholder="">
						</li>
						<li>	
							<strong>이메일주소</strong>
							<input type="text" class="w300" placeholder="ooooo@email.co.kr의 형식으로 작성해주세요">
						</li>
					</ul>
					<input type="submit" href="#" class="findBtn" value="비밀번호찾기">
				</div>
	
			</form>
			
			<form action="pwFindRslt.jsp" method="get" class="findForm02 off">
				<div class="login_box">
					<ul class="find_inp">
						<li>
							<strong>아이디</strong>
							<input type="text" class="w300" placeholder="아이디">
						</li>
						<li>	
							<strong>휴대전화번호</strong>
							<input type="text" class="w090" placeholder="">
							<span class="bttxt">-</span>
							<input type="text" class="w090" placeholder="">
							<span class="bttxt" >-</span>
							<input type="text" class="w090" placeholder="">
						</li>
						<li>	
							<strong>이메일주소</strong>
							<input type="text" class="w300" placeholder="ooooo@email.co.kr의 형식으로 작성해주세요">
						</li>
					</ul>
					<input type="submit" href="#" class="findBtn" value="비밀번호찾기">
				</div>
	
			</form>
			
		</div>
		<!--//로그인 영역-->
		</div>
		<!-- 서브컨텐츠 영역 END -->

		</div>
</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>