<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			<h3>후기게시판</h3>
			
			<h4>글쓰기</h4>
		
			<form action="sboardwrite_ok.brn" method="post" enctype="multipart/form-data" id="sboardwrite">
			<!--게시글-->
			<div class="Board_write"> 
				<!--제목-->
				<div class="board_tit">
					<input type="text" name="s_sj" id="s_sj" placeholder="제목을 입력해주세요."/>
				</div>
				<!--정보-->
				<div class="Editor_Area"> 
				<textarea cols="100%" rows="20%" name="s_ct" id="s_ct"></textarea>
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong><input type="file" name="s_fl" id="s_fl" value="파일추가"></h6>
					<ul class="addfile">
						<li class="pdf"><a href="#">thumnail.png</a><input type="button" class="btn_del"></li>
						<li class="pdf"><a href="#">thumnail.png</a><input type="button" class="btn_del"></li>
						<li class="pdf"><a href="#">thumnail.png</<input type="button" class="btn_del"></li>	
					</ul>
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
					<a href="#" onclick="javascript:history.go(-1);" class="white">취소</a>
					<a href="sboardList.brn" class="white">목록</a>
				</div>
				<div class="fr">
					<a href="#" class="black" onclick="write_check()">등록</a>
				</div>
			</div>
			<!--//버튼영역--> 
			</form>
		</div>
		
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	</div>
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>