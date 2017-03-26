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
						<span class="on"><span class="num">1</span><span class="on_tit">개인 정보</span></span>
						<span class="off">2</span>
					</div>
					<h4 class="h4 mt15">회원정보 확인</h4>
					<p class="tit_tip">
						 회원정보는 개인정보처리방침에 따라 안전하게 보호되며 회원님의 동의 없이 공개 또는 제3자에게 제공되지 않습니다.
					</p>
					<form action="./mem_edit.brn" method="post"  enctype="multipart/form-data">
					<!--회원정보입력란-->
									
						<div class="common_write_box">
							<table>
							<colgroup>
							<col width="148px">
							<col width="100%"> 
							</colgroup>
							<tbody>
							
							<tr>
								<th>
									<span class="mando">아이디</span>
								</th>
								<td>
									${member.mem_id }
								</td>
							</tr>
							<tr>
								<th>
									 <span class="mando">성명</span>
								</th>
								<td>
									${member.mem_nm }
								</td>
							</tr>
							<tr>
								<th>
									  <span class="mando">성별</span>
								</th>
								<td>
									 <c:if test="${member.mem_sx ==1 }">
									 <label for="gender_m">남</label>
									</c:if>
									
									<c:if test="${member.mem_sx ==2 }">
									 <label for="gender_f" id="gender_f_label">여</label>
									</c:if>
									
								</td>
							</tr>
							
							<tr>
								<th>
									 <span class="mando">생년월일</span>
								</th>
								<td>
									${member.mem_bd }
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">휴대폰번호</span>
								</th>
								<td>
									${member.mem_hp }
								</td>
							</tr>
							<tr>
								<th>
									전화번호
								</th>
								<td>
									${member.mem_tel }
								</td>
							</tr>
							<tr>
								<th>
									 주소
								</th>
								<td>
									<ul>                           
										<li class="mb5">${member.mem_zc }</li>
                          				<li class="mb5">${member.mem_add1 }</li>
                           				<li>${member.mem_add2 }</li>            
                           			</ul>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">이메일</span>
								</th>
								<td>
									${member.mem_ma }
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">키</span>
								</th>
								<td>
									${member.mem_h }
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">몸무게</span>
								</th>
								<td>
									${member.mem_w }
								</td>
							</tr>
							<tr>
								<th>
									워너비 사진
								</th>
								<td>
									<c:if test="${empty member.mem_wb }">
										<img src="./resources/upload/default.PNG" width="50px" height="50px">
									</c:if>
									<c:if test="${!empty member.mem_wb}">
										<img src="./resources/upload/${member.mem_wb }" alt="img"
										 onchange="prewb()" width="50px" height="50px"/>
										<span class="bttxt" id="thumbnail">이렇게 되길 기원합니다</span>
								</c:if>
								</td>
							</tr>
							</tbody>
						</table>
						<!--//회원정보입력란-->
						<!--버튼-->
						<div class="btnL_area mt25">
							<input type="reset" value="취소" onclick="location.href='./Main.brn' ">
							<input type="submit" value="수정하기">
						</div>
						<!--//버튼-->	
					</div>
					<!--//회원정보입력란-->
				</form>
		</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
