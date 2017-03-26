<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
//아이디 중복 체크
function id_check(){
	$("#idcheck_text").hide();//idcheck span 아이디 영역을 숨긴다.
	
	var mem_id = $("#inputid").val();
	alert(mem_id);
	
	//1. 입력 글자 길이 체크
	if($.trim($("#inputid").val()).length <6){
		var newtext='<font color="red">아이디는 6자 이상이어야 합니다.</font>';
		$("#idcheck_text").text('');
		$("#idcheck_text").show();
		$("#idcheck_text").html(newtext);  //span 아이디 영역에 경고 문자 추가
		$("#inputid").val("").focus();
		return false;
	};
	
	if($.trim($("#inputid").val()).length >12){
		var newtext='<font color="red">아이디는 12자 이하이어야 합니다.</font>';
		$("#idcheck_text").text('');
		$("#idcheck_text").show();
		$("#idcheck_text").html(newtext);  //span아이디 영역에 경고 문자추가
		$("#inputid").val("").focus();
		return false;
	};
	
	//입력 아이디 패턴 유효성 검사
	if(!(validate_userid(mem_id))){
		var newtext='<font color="red">아이디는 영문소문자, 숫자 조합만 가능합니다.</font>';
		$("#idcheck_text").text('');//문자 초기화
		$("#idcheck_text").show();//span 아이디 영역을 보이게 한다
		$("#idcheck_text").html(newtext);
		$("#inputid").val("").focus();
		return false;
	};
	
	//아이디 중복확인
	$.ajax({
		type:"POST",	
		url:"member_idcheck.brn",
		cache:false,
		data:{"mem_id" : mem_id},
		success : function(data){
			alert(data);
			if(data==1){
				//중복 아이디가 있으면
				var newtext='<font color="red">중복 아이디입니다.</font>';
				$("#idcheck_text").text('');
				$("#idcheck_text").show();
				$("#idcheck_text").html(newtext);
				$("#inputid").val('').focus();
				return false;
				
			}else{//중복 아이다가 없으면
				var newtext='<font color="blue">사용가능한 아이디입니다.</font>';
				$("#idcheck_text").text('');
				$("#idcheck_text").show();
				$("#idcheck_text").html(newtext);
				$("#inputpw").focus();
				
			}
		},
		error : function(){
			alert("data error"+mem_id);
		}
	});  //$.ajax
	
};

function validate_userid(mem_id){
	var pattern = /^[a-z0-9_]+$/;
	
	//test 메서드는 문자열 안에 패턴이 있는지 확인하여 있으면 true를 없으면 false를 반환
	var result = pattern.test(mem_id);
	  
	return result;
	
	
};

</script>
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
						<span class="on"><span class="num">2</span><span class="on_tit">개인 정보 입력</span></span>
						<span class="off">3</span>
					</div>
					<h4 class="h4 mt15">회원정보 입력</h4>
					<p class="tit_tip">
						 회원정보는 개인정보처리방침에 따라 안전하게 보호되며 회원님의 동의 없이 공개 또는 제3자에게 제공되지 않습니다.
					</p>
					<p class="tip_blue">
						 (<img src="./resources/img/bull/bull_mando.png" alt="*" />)표기는 필수입력사항으로 모두 입력해주세요.
					</p> 
					<form action="./JoinSubmit.brn" method="post" id="joinform">
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
									<input type="text" class="w200" id="inputid" name="inputid" >
									<input type="button" class="btn_w" value="중복확인" id="idcheck" onclick="id_check()">
									<span class="tip_info2">6~10자리 수 영문 또는 숫자 로만 가능</span>
									<div id="idcheck_text"></div>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">비밀번호</span>
								</th>
								<td>
									<input type="password" class="w200 " id="inputpw" name="inputpw" pattern=".{8,}">
									<span class="tip_info2">8~16 자리수 영문과 숫자를 조합</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando" >비밀번호 확인</span>
								</th>
								<td>
									<input type="password" class="w200 " id="checkpw" name="checkpw">
									<span class="tip_info2">비밀번호를 다시 한번 입력해주세요.</span>
								</td>
							</tr>
							<tr>
								<th>
									 <span class="mando">성명</span>
								</th>
								<td>
									<input type="text" class="w200" id="inputname" name="inputname">
								</td>
							</tr>
							<tr>
								<th>
									  <span class="mando">성별</span>
								</th>
								<td>
									 <input type="radio" name="gender" id="gender_m" class="gender" value="1"><label for="gender_m">남</label>
									 <span class="bttxt w050"></span>
									 <input type="radio" name="gender" id="gender_f" class="gender" value="2"><label for="gender_f" id="gender_f_label">여</label>
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
										<c:forEach var="year" begin="1" end="117" step="1">
										<option value="${2017-year+1 }">${2017-year+1 }</option> 
										</c:forEach>
									</select>
									<span class="bttxt">년</span> 
									<select id="birthmonth" name="birthmonth">
										<option value="">-선택-</option> 
										<c:forEach var="month" begin="1" end="12" step="1">
										<option value="${month }">${month }</option> 
										</c:forEach>
									</select >
									<span class="bttxt">월</span> 
									<select id="birthday" name="birthday">
										<option value="">-선택-</option> 
										<c:forEach var="day" begin="1" end="31" step="1">
										<option value="${day }">${day }</option> 
										</c:forEach>
									</select>
									<span class="bttxt">일</span> 
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">휴대폰번호</span>
								</th>
								<td>
									<select class="w100" name='mobileNo1' id='mobileNo1'>
										<option value="">-선택-</option>
										<option value="010">010</option>
						            	<option value="011">011</option>
						            	<option value="016">016</option>
						            	<option value="019">019</option>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100" id="middleph" name="middleph">
									<span class="bttxt">-</span>
									<input type="text" class="w100" id="lastph" name="lastph" >
								</td>
							</tr>
							<tr>
								<th>
									전화번호
								</th>
								<td>
									<select class="w100" name='mem_tel1' id='mem_tel1'>
										<option value="">-선택-</option>
										<option value="02">02</option>
										<option value="031">031</option>
										<option value="032">032</option>
										<option value="033">033</option>
										<option value="041">041</option>
										<option value="042">042</option>
										<option value="043">043</option>
										<option value="051">051</option>
										<option value="052">052</option>
										<option value="053">053</option>
										<option value="054">054</option>
										<option value="055">055</option>
										<option value="061">061</option>
										<option value="062">062</option>
										<option value="063">063</option>
										<option value="064">064</option>
									</select>
									<span class="bttxt">-</span>
									<input type="text" class="w100" name='mem_tel2' id='mem_tel2'>
									<span class="bttxt">-</span>
									<input type="text" class="w100" name='mem_tel3' id='mem_tel3'>
								</td>
							</tr>
							<tr>
								<th>
									 주소
								</th>
								<td>
									<p class="col">
										<input type="button" value="우편번호찾기" id="findzipcode" onclick="zipcode_find()"> 
										<input type="text" class="w100" id="zipcode" name="zipcode">
									</p>
									<p class="col">
										<input type="text" class="w400" id="address" name="address"><span class="tip_info2">기본주소</span>
									</p>
									<p class="col">
										<input type="text" class="w400" id="detailaddr" name="detailaddr">
										<span class="tip_info2">상세주소</span>
									</p> 
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">이메일</span>
								</th>
								<td>
									<input type="text" class="w150" id="email" name="email"><span class="bttxt">@</span>
									<input type="text" class="w120" id="domain" name="domain"><span class="bttxt"> </span>
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
									<input type="text" class="w060" maxlength="5" id="height" name="height">
									<span class="bttxt">cm</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="mando">몸무게</span>
								</th>
								<td>
									<input type="text" class="w060" maxlength="5" id="weight" name="weight">
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
				</form>
		</section>
	<!-- // container End -->
	
	<%@ include file="/jsp/inc/footer.jsp" %>
