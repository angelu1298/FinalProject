<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.board.gboard.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%@ include file="../../inc/boardHeader.jsp"%>
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		 
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>갤러리</h3>
			<h4>상세보기</h4>
		
			<!-- 전체리스트-->
			<form method="post" action="gbbs_write_ok.brn" enctype="multipart/form-data" id="gboardwrite">
			
				<!-- 게시글 -->
				<div class="Board_write"> 
				
					<!-- 제목 -->
					<div class="board_tit">
						<input type="text" name="gbbs_subject" id="gbbs_subject" size="40" />
					</div>						 
					<!--// 제목--> 
					
					<div class="Editor_area"> 
						<p class="tip">500자 이내로 입력해주세요<span><strong>0</strong>/500자</span></p>
						<textarea name="gbbs_content" id="gbbs_content" maxlength="500" cols="100%" rows="10"></textarea>
					</div>	
					
					<div class="file_Area_img">
						<h6>	
							<strong>첨부파일추가</strong>
							<input class="btn_add" type="button" value="파일추가" />
						</h6>
						<p class="tip01">jpg, png, gif 형식의 파일만 첨부해 주시기 바랍니다. 첨부된 파일의 용량은 총 50MB 이하여야 합니다.</p>			
						<ul id="fileview">
							<p class="nolist">첨부된 이미지 파일이 없습니다.</p>
						</ul>
					</div>
				
				</div>
				<!--//게시글-->
			
				<!--버튼영역-->
				<div class="btnB_area">
					<div class="fl">
						<input type="reset" value="취소" class="input_button" onclick="location.href='gbbs_gall.brn'" />
					</div>
					<div class="fr">
						<input type="button" value="등록" class="input_button" id="gboardwritebtn" />
					</div>
				</div>
				<!--//버튼영역--> 
	 
			</form>
			
		</div>
		<!-- 서브컨텐츠 영역 END -->
	</section>
	<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>