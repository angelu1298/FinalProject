<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./resources/js/jquery.min.js"></script>

<script>
 function checkpw(){
	 if($.trim($("#mem_id").val())==""){
		 alert("아이디를 입력하세요!");
		 $("#mem_id").val("").focus();
		 return false;
	 }
	 if($.trim($("#mem_nm").val())==""){
		 alert("이름을 입력하세요!");
		 $("#mem_nm").val("").focus();
		 return false;
	 }
	 if($.trim($("#mem_maid").val())==""){
		 alert("메일을 입력하세요!");
		 $("#mem_maid").val("").focus();
		 return false;
	 }
 }
 
 function domain_list() {
		/*리스트에서 직접입력을 선택했을때*/
		 if($("#mail_list").val()=="0") // 직접입력
		 {
			//@뒤의 도메인입력란을 공백처리
		    $("#mem_madomain").val("");
		    
		    //@뒤의 도메인입력란을 쓰기 가능
		    $("#mem_madomain").attr("readOnly",false);
		    
		    //도메인입력란으로 입력대기상태
		    $("#mem_madomain").focus();
		}
		 else { //리스트목록을 선택했을때
			 /* num변수에는 해당리스트 목록번호가 저장되어 있습니다.
			  * 해당리스트 번호의 option value값이 도메인입력란에 선택됩니다.
			  * options속성은 select객체의 속성으로서 해당리스트 번호의 value값을 가져온다
			*/
		      $("#mem_madomain").val($("#mail_list").val());
		      $("#mem_madomain").attr("readOnly",true);
		 }
	 }
</script>

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
						<li class="right">이메일주소로 찾기</li> 
					</ul>
				</div>
				<!--//탭-->
				
			<form action="./pwd_find_ok.brn" method="post" class="findForm01" onsubmit="return checkpw()">
				<div class="login_box">
					<ul class="find_inp">
						<li>
							<strong>이름</strong>
							<input type="text" class="w300" placeholder="이름" id="mem_nm" name="mem_nm">
						</li>
						<li>
							<strong>아이디</strong>
							<input type="text" class="w300" placeholder="아이디" id="mem_id" name="mem_id">
						</li>
					
						<li>	
						<strong>이메일주소</strong>
								<input type="text" class="w120" id="mem_maid" name="mem_maid">
								<span class="bttxt">@</span>
									
								<input name="mem_madomain" id="mem_madomain" 
             					 class="input_box" readonly />
									<!--readonly는  쓰기, 수정이 불가능하고 읽기만 가능합니다. -->
							       <select name="mail_list" id="mail_list" onchange="domain_list()">
							        <option value="">=이메일선택=</option>
							        <option value="daum.net">daum.net</option>
							        <option value="nate.com">nate.com</option>
							        <option value="naver.com">naver.com</option>
							        <option value="gmail.com">gmail.com</option>
							        <option value="0">직접입력</option>
							      </select> 
						</li>
					</ul>
					<input type="submit" class="findBtn" value="비밀번호 찾기">
					 <input type="reset"  value="취소" class="findBtn" 
          				 onclick="$('#mem_nm').focus();" />
				</div>
	
			</form>
			
			
		<!--//로그인 영역-->
		</div>
		<!-- 서브컨텐츠 영역 END -->

		</div>
</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
