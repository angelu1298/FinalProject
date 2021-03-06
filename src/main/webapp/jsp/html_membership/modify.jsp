<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="./resources/js/member2.js"></script>


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
						<span class="on"><span class="num">2</span><span class="on_tit">개인 정보 수정</span></span>
					</div>
					<h4 class="h4 mt15">회원정보 수정</h4>
					<p class="tit_tip">
						 회원정보는 개인정보처리방침에 따라 안전하게 보호되며 회원님의 동의 없이 공개 또는 제3자에게 제공되지 않습니다.
					</p>
					<p class="tip_blue">
						 (<img src="./resources/img/bull/bull_mando.png" alt="*" />)표기는 필수입력사항으로 모두 입력해주세요.
					</p> 
					<form action="./mem_edit_ok.brn" method="post" id="modifyform">
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
									<span class="mando">비밀번호</span>
								</th>
								<td>
									<input type="text" class="w200 " id="inputpw" name="inputpw" pattern=".{8,}" value="${member.mem_pw }">
									<span class="tip_info2">8~16 자리수 영문과 숫자를 조합</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando" >비밀번호 확인</span>
								</th>
								<td>
									<input type="text" class="w200 " id="checkpw" name="checkpw" value="${member.mem_pw }">
									<span class="tip_info2">비밀번호를 다시 한번 입력해주세요.</span>
								</td>
							</tr>
							<tr>
								<th>
									 <span class="mando">성명</span>
								</th>
								<td>
									<input type="text" class="w200" id="inputname" name="inputname" value="${member.mem_nm }">
								</td>
							</tr>
							<tr>
								<th>
									  <span class="mando">성별</span>
								</th>
								<td>
									<c:if test="${member.mem_sx ==1 }">
									 <input type="radio" name="gender" id="gender_m" class="gender" value="1" checked="checked"><label for="gender_m">남</label>
									 <span class="bttxt w050"></span>
									 <input type="radio" name="gender" id="gender_f" class="gender" value="2"><label for="gender_f" id="gender_f_label">여</label>
									</c:if>
									
									<c:if test="${member.mem_sx ==2 }">
									 <input type="radio" name="gender" id="gender_m" class="gender" value="1"><label for="gender_m">남</label>
									 <span class="bttxt w050"></span>
									 <input type="radio" name="gender" id="gender_f" class="gender" value="2" checked="checked"><label for="gender_f" id="gender_f_label">여</label>
									</c:if>
									
								</td>
							</tr>
							
							<tr>
								<th>
									 <span class="mando">생년월일</span>
								</th>
								<td>
									<!-- 셀렉트박스는 커스텀 태그를 써야할듯? -->
									<select id="birthyear" name="birthyear">
										<option value="">-선택-</option> 
										<c:forEach var="yearmodify" begin="1" end="117" step="1">
										<option value="${2017-yearmodify+1 }" 
											<c:if test="${year == (2017-yearmodify+1 )}">
	                     						${'selected'}
	               							</c:if>
										>${2017-yearmodify+1 }</option> 
										</c:forEach>
									</select>
									<span class="bttxt">년</span> 
									<select id="birthmonth" name="birthmonth">
										<option value="">-선택-</option> 
										<c:forEach var="monthmodify" begin="1" end="12" step="1">
										<option value="${monthmodify }" 
											<c:if test="${monthmodify == month}">
	                     						${'selected'}
	               							</c:if>
										>${monthmodify }</option> 
										</c:forEach>
									</select >
									<span class="bttxt">월</span> 
									<select id="birthday" name="birthday">
										<option value="">-선택-</option> 
										<c:forEach var="daymodify" begin="1" end="31" step="1">
										<option value="${daymodify }" 
											<c:if test="${daymodify == day}">
	                     						${'selected'}
	               							</c:if>
										>${daymodify }</option> 
										</c:forEach>
									</select>
									<span class="bttxt">일</span> 
									<div id="birthadd"></div>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">휴대폰번호</span>
								</th>
								<td>
									<select class="w100" name='mobileNo1' id='mobileNo1'>
										<option value="010"
											<c:if test="${010 == hp1 }">
												${'selected' }
											</c:if>
										>010</option>
						            	<option value="011"
						            		<c:if test="${011 == hp1 }">
												${'selected' }
											</c:if>
						            	>011</option>
						            	<option value="016"
						            		<c:if test="${016 == hp1 }">
												${'selected' }
											</c:if>
						            	>016</option>
						            	<option value="019"
						            		<c:if test="${019 == hp1 }">
												${'selected' }
											</c:if>
						            	>019</option>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100" id="middleph" name="middleph" value="${hp2 }">
									<span class="bttxt">-</span>
									<input type="text" class="w100" id="lastph" name="lastph" value="${hp3 }">
								</td>
							</tr>
							<tr>
								<th>
									전화번호
								</th>
								<td>
								<%
								String[] tel={"02","031","032","033","041","042", "043","044",
		  						"051","052","053","054","055","061","062","063","064"};
								  request.setAttribute("tel",tel);
								 %><!-- 집전화 번호를 따로 관리해서 request 객체에 저장 -->
								 
									<select class="w100" name='mem_tel1' id='mem_tel1'>
										<c:forEach var="t" items="${tel}" >
								          <option value="${t}" 
								               <c:if test="${tel1 == t}">
								                     ${'selected'}
								               </c:if>
								           >${t}
								          </option>
										</c:forEach>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100" name='mem_tel2' id='mem_tel2' value="${tel2 }">
									<span class="bttxt">-</span>
									<input type="text" class="w100" name='mem_tel3' id='mem_tel3' value="${tel3 }">
								</td>
							</tr>
							<tr>
								<th>
									 주소
								</th>
								<td>
									<p class="col">
										<input type="button" value="우편번호찾기" id="findzipcode"> 
										<input type="text" class="w100" id="zipcode" name="zipcode" value="${member.mem_zc }">
									</p>
									<p class="col">
										<input type="text" class="w400" id="address" name="address" value="${member.mem_add1 }"><span class="tip_info2">기본주소</span>
									</p>
									<p class="col">
										<input type="text" class="w400" id="detailaddr" name="detailaddr" value="${member.mem_add2 }">
										<span class="tip_info2">상세주소</span>
									</p> 
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">이메일</span>
								</th>
								<td>
									<input type="text" class="w150" id="email" name="email" value="${mem_mailid}"><span class="bttxt">@</span>
									<input type="text" class="w120" id="domain" name="domain" value="${mem_maildomain}"><span class="bttxt"> </span>
									<select id="sel" name="sel">
										<option value="">-직접입력-</option>
						            	<option value="naver.com">naver.com</option>
						            	<option value="daum.net">daum.net</option>
						            	<option value="nate.com">nate.com</option>
						            	<option value="gmail.com">gmail.com</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">키</span>
								</th>
								<td>
									<input type="text" class="w060" maxlength="5" id="height" name="height" value="${member.mem_h }">
									<span class="bttxt">cm</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">몸무게</span>
								</th>
								<td>
									<input type="text" class="w060" maxlength="5" id="weight" name="weight" value="${member.mem_w }">
									<span class="bttxt">kg</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">워너비 사진</span>
								</th>
								<td>
									<c:if test="${empty wannabe }">
										<input type="file" id="wannabe" name="wannabe" accept=".jpeg,.gif,.jpg,.png">
										<img src="./resources/upload/default.PNG" width="50px" height="30px">
									</c:if>
									<c:if test="${!empty wannabe }">
										<input type="file" id="wannabe" name="wannabe" accept=".jpeg,.gif,.jpg,.png">
										<img src="./resources/upload${wannabe }" alt="img"/>
										<span class="bttxt" id="thumbnail">이렇게 되길 기원합니다</span>
								</c:if>
								</td>
							</tr>
							</tbody>
						</table>
						<!--//회원정보입력란-->
						<!--버튼-->
						<div class="btnL_area mt25">
							<input type="reset" value="다시 작성하기">
							<input type="submit" value="확인">
						</div>
						<!--//버튼-->	
					</div>
					<!--//회원정보입력란-->
				</form>
		</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
