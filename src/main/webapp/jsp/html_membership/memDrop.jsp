<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/jsp/inc/logHeader.jsp"%>
<script>
$(function(){
	$('#memdrop').submit(function(){
		if($.trim($('#inputpw').val())==''){
			$('#inputpw').css({'color':'red'});
			$('#inputpw').val('비밀번호를 입력하세요');
			return false;
		}
		if($.trim($('#inputpw').val())!=$.trim($('#checkpw').val())){
			$('#checkpw').css({'color':'red'});
			$('#checkpw').val('비밀번호가 일치하지 않습니다');
			return false;
		}
		
	})
})
</script>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			
		<!--로그인 영역-->
		<div class="con_con">
					<!--회원탈퇴 영역-->
					<div class="Step">
						<span class="on"><span class="num">1</span><span class="on_tit">회원탈퇴</span></span>
						<span class="off">2</span>
						<span class="off">3</span>
					</div>
					<form action="mem_drop_ok.brn" method="post" id="memdrop">
					<h4 class="h4 mt15">회원탈퇴</h4>
					<div class="Agree_area">
						<p class="tit">
							 회원 탈퇴를 진행하기 위해 본인 확인이 필요합니다.
						</p>
						
						<div class="common_write_box">
							<table>
							<colgroup>
							<col width="148px">
							<col width="100%"> 
							</colgroup>
							<tbody>
							
							<tr>
								<th>
									아이디
								</th>
								<td>
									${member.mem_id }
								</td>
							</tr>
							<tr>
								<th>
									비밀번호
								</th>
								<td>
									<input type="password" class="w200 " id="inputpw" name="inputpw" pattern=".{8,}">
								</td>
							</tr>
							<tr>
								<th>
									비밀번호 확인
								</th>
								<td>
									<input type="password" class="w200 " id="checkpw" name="checkpw">
									<span class="tip_info2">비밀번호를 다시 한번 입력해주세요.</span>
								</td>
							</tr>
						</tbody>
						</table>
						</div>
						
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
