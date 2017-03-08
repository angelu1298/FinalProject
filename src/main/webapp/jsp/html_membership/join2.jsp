<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
				<div class="con_con">
					<!--회원가입 영역-->
					<div class="Step">
						<span class="off">1</span>
						<span class="on"><span class="num">2</span><span class="on_tit">개인 정보 입력</span></span>
						<span class="off">2</span>
					</div>
					<h4 class="h4 mt15">회원정보 입력</h4>
					<p class="tit_tip">
						 회원정보는 개인정보처리방침에 따라 안전하게 보호되며 회원님의 동의 없이 공개 또는 제3자에게 제공되지 않습니다.
					</p>
					<p class="tip_blue">
						 (<img src="../img/bull/bull_mando.png" alt="*">)표기는 필수입력사항으로 모두 입력해주세요.
					</p> 
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
									<input type="text" class="w170">
									<input type="button" class="btn_w" value="중복확인">
									<span class="tip_info2">6~10자리 수, 영문 또는 숫자 로만 가능</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">비밀번호</span>
								</th>
								<td>
									<input type="text" class="w170 ">
									<span class="tip_info2">8~16 자리 수, 영문과 숫자를 조합</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">비밀번호 확인</span>
								</th>
								<td>
									<input type="text" class="w170 ">
									<span class="tip_info2">비밀번호를 다시 한번 입력해주세요.</span>
								</td>
							</tr>
							<tr>
								<th>
									 <span class="mando">성명</span>
								</th>
								<td>
									<input type="text" class="w170">
								</td>
							</tr>
							<tr>
								<th>
									  <span class="mando">성별</span>
								</th>
								<td>
									 <input type="radio" name="gender" id="gender_m" checked="checked"><label>남</label>
									 <span class="bttxt w050"></span>
									 <input type="radio" name="gender" id="gender_f"><label>여</label>
								</td>
							</tr>
							
							<tr>
								<th>
									 <span class="mando">생년월일</span>
								</th>
								<td>
									<!-- 셀렉트박스는 커스텀 태그를 써야할듯? -->
									<select>
										<option>2016</option> 
									</select>
									<span class="bttxt">년</span> 
									<select>
										<option>12</option> 
									</select>
									<span class="bttxt">월</span> 
									<select>
										<option>31</option> 
									</select>
									<span class="bttxt">일</span> 
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">휴대폰번호</span>
								</th>
								<td>
									<select class="w100">
										<option>010</option>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100">
									<span class="bttxt">-</span>
									<input type="text" class="w100">
								</td>
							</tr>
							<tr>
								<th>
									전화번호
								</th>
								<td>
									<select class="w100">
										<option>02</option>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100">
									<span class="bttxt">-</span>
									<input type="text" class="w100">
								</td>
							</tr>
							<tr>
								<th>
									 주소
								</th>
								<td>
									<p class="col">
										<input type="button" value="우편번호찾기" > 
										<input type="text" class="w100">
									</p>
									<p class="col">
										<input type="text" class="w400"><span class="tip_info2">기본주소</span>
									</p>
									<p class="col">
										<input type="text" class="w400">
										<span class="tip_info2">상세주소</span>
									</p> 
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">이메일</span>
								</th>
								<td>
									<input type="text" class="w150"><span class="bttxt">@</span>
									<input type="text" class="w120"><span class="bttxt"> </span>
									<select>
										<option>-직접입력-</option>
										<option>gmail.com</option>
										<option>naver.com</option>
										<option>hanmail.com</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">키</span>
								</th>
								<td>
									<input type="text" class="w060" maxlength="5">
									<span class="bttxt">cm</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">몸무게</span>
								</th>
								<td>
									<input type="text" class="w060" maxlength="5">
									<span class="bttxt">kg</span>
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
		</section>
	<!-- // container End -->
	
	<%@ include file="../inc/footer.jsp" %>