<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
	
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
				<div class="con_con">
					<!--회원가입 영역-->
					<div class="Step">
						<span class="off">1</span>
						<span class="off">2</span>
						<span class="on"><span class="num">3</span><span class="on_tit">워너비 사진 입력</span></span>
					</div>
					<h4 class="h4 mt15">회원정보 입력</h4>
					<p class="tit_tip">
						 회원정보는 개인정보처리방침에 따라 안전하게 보호되며 회원님의 동의 없이 공개 또는 제3자에게 제공되지 않습니다.
					</p>
					<p class="tip_blue">
						 (<img src="./resources/img/bull/bull_mando.png" alt="*" />)표기는 필수입력사항으로 모두 입력해주세요.
					</p> 
					<form action="./Thumbnail.brn" method="post" id="joinformThum" encType="multipart/form-data">
					<!--회원정보입력란-->
									
						<div class="common_write_box">
						<table>
							<colgroup>
							<col width="148px">
							<col width="100%"> 
							</colgroup>
							<tr>
								<th>
									<span class="mando">워너비사진</span>
								</th>
								<td>
								<c:if test="${empty wannabe }">
									<input type="file" id="wannabe" name="wannabe" accept=".jpeg,.gif,.jpg,.png">
								</c:if>
								<c:if test="${!empty wannabe }">
									<input type="file" id="wannabe" name="wannabe" accept=".jpeg,.gif,.jpg,.png">
									<img src="./resources/upload${wannabe }" alt="img"/>
									<span class="bttxt" id="thumbnail">이렇게 되길 기원합니다</span>
								</c:if>
								</td>			
							</tr>
						</table>
						<!--//회원정보입력란-->
						<!--버튼-->
						<div class="btnL_area mt25">
							<input type="reset" value="다시선택">
							<input type="submit" id="finishjoin" value="회원가입완료">
						</div>
						<!--//버튼-->	
					</div>
					<!--//회원정보입력란-->
				</form>
		</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
    