
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>

		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent nobg">
			  <h4>문의메일 보내기</h4>
			  <form action="inquiry_ok.brn" method="post">   
			  	<div align="center"><!-- 메일 주소 -->
			      <input type="text" id="mail" name="mail" size="120" style="width:100%" placeholder="메일을 입력해주세요" class="form-control" >
			    </div>
			    <p> 
			    <div align="center"><!-- 제목 -->
			      <input type="text" id="title" name="title" size="120" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
			    </div>
			    <p>
			    <div align="center"><!-- 내용 --> 
			      <textarea id="content" name="content" cols="120" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
			    </div>
			    <p>
			   		<div class="btnL_area mt25">
							<input type="reset" value="다시 작성하기">
							<input type="submit" value="확인">
					</div>
			  </form>
		</div>
		
</section>
<!-- // container End -->
	
<%@ include file="/jsp/inc/footer.jsp" %>

