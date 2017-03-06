<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			
		<!--로그인 영역-->
		<div class="con_con">
					<!--회원가입 영역-->
					<div class="Step">
						<span class="on"><span class="num">1</span><span class="on_tit">회원가입 동의</span></span>
						<span class="off">2</span>
						<span class="off">3</span>
					</div>
					<form action="./JoinGetInfo.brn" method="post" id="agreement">
					<h4 class="h4 mt15">회원약관 동의</h4>
					<div class="Agree_area">
						<p class="tit">
							 회원가입약관
						</p>
						<!--회원가입약관 -->
						<div class="agree_cont">
							<div class="cont">
								 
							</div>
							<div class="checkarea">
								<input type="checkbox" id="agree01" value="agree01"><label for="agree01">위 고유식별정보 수집에 동의합니다.</label>
							</div>
						</div>
						<!--//회원가입약관 -->
						<p class="tit mt30">
							 개인정보 수집 및 이용에 대한 안내
						</p>
						<!--개인정보 수집 및 이용에 대한 안내-->
						<div class="agree_cont">
							<div class="cont">
							
							</div>
							<div class="checkarea">
								<input type="checkbox" id="agree02" value="agree02"><label for="agree02">위 개인정보 수집 및 이용에 동의합니다.</label>
							</div>
						</div>
						<!--//개인정보 수집 및 이용에 대한 안내-->
						<!--전체동의-->
						<div class="checkall">
							<input type="checkbox" name="checkall" id="checkall"><label for="checkall">전체 동의합니다.</label>
						</div>
						<!--//전체동의-->
					</div>
					<!--버튼-->
					<div class="btnL_area mt25">
						<input type="submit" value="동의">
						<input type="reset" value="동의하지 않습니다">
					</div>
					<!--//버튼-->	
					<!--//회원가입 영역-->
					</form>
				</div>
				<!--본문-->
	</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>