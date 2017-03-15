<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/boardHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/boardHeader.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>후기게시판</h3>
			
			<h4>보기</h4>
		
			<!--게시글-->
			<div class="Board_view">
				<form action="sboardedit_ok.brn" method="post" encType="multipart/form-data">		
				<!--제목-->
				<div class="board_tit">
					<input type="text" id="s_sj" name="s_sj" value="${sbean.s_sj }" />
					<input type="hidden" id="s_no" name="s_no" value="${sbean.s_no }"/>
					<input type="hidden" id="page" name="page" value="${page }"/>
				</div>
				<!--정보-->
				<div class="board_info">
					<dl class="dateinfo">
						<dt>작성일</dt>
						<dd>${sbean.s_dt }</dd>
					</dl>
					<dl class="clickinfo">
						<dt>조회수</dt>
						<dd>${sbean.s_rc }</dd>
					</dl>
					<dl class="likeinfo">
						<dt>추천수</dt>
						<dd>${sbean.s_lk }</dd>
					</dl>
					<dl class="writerinfo">
						<dt>작성자</dt>
						<dd>${sbean.mem_id }</dd>
					</dl>
				</div>
				<div class="Post_Area nobor">
					<!---->
					<div>
						<textarea cols="50" rows="50" name="s_ct" id="s_ct" >${sbean.s_ct }</textarea>
					</div>
					<!--//내용영역-->
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<h6><strong>첨부파일</strong><input type="file" name="s_fl" id="s_fl" value="파일추가"></h6>
					<ul class="addfile">
						<li class="pdf"><a href="#">${sbean.s_fl }</a><input type="button" class="btn_del"></li>
					</ul>
				</div>
				<!--//첨부파일-->
				<!--버튼영역-->
				<div class="btnB_area">
					<div class="fl">
						<a href="./sboardList.brn?page=${page }" class="white">취소</a>
						<a href="./sboardList.brn?page=${page }" class="white">목록</a>
					</div>
					<div class="fr">
						<input type="submit" class="black"  value="수정"/>
					</div>
				</div>
				<!--//버튼영역--> 
			</form>
			</div>
			<!--//게시글-->
		</div>
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	</div>
</section>
<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
