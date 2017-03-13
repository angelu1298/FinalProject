<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../inc/subHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu_log.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>운동게시판</h3>
			
			<h4>보기</h4>
		
			<!--게시글-->
			<div class="Board_view">
				<!--LIKE-->
				<div class="likeArea">
			<ul> 
				<li><input type="button" value="스크랩" class="btn_scrap"
			  	onclick="location='ebo_like.brn?e_no=${ebobean.e_no}'" />
			  	<c:if test="${joayo != null}">
			  		${joayo}
			  	</c:if>
			  	<c:if test="${joayo == null}">
			  		${ebobean.e_lk}
			  	</c:if>
			  	</li>
<!-- 			<li><a href="/" class="btn_scrap" title="스크랩" id="joayo"><span>스크랩하기</span></a></li> -->
			</ul>
				</div>
				<!--//LIKE-->
				<!-- 번호 -->
				<%-- <div>
					${ebobean.e_no}
				</div> --%>
				<!--제목-->
				<div class="board_tit">
					${ebobean.e_sj}
				</div>
				<!--정보-->
				<div class="board_info">
					<dl class="dateinfo">
						<dt>작성일</dt>
						<dd>${ebobean.e_dt}</dd>
					</dl>
					<dl class="clickinfo">
						<dt>조회수</dt>
						<dd>${ebobean.e_rc}</dd>
					</dl>
					<!--  -->
					<dl class="likeinfo">
						<dt>추천수</dt>
						<dd>${ebobean.e_lk}</dd>
					</dl>
					<dl class="writerinfo">
						<dt>작성자</dt>
						<dd>${mem_id}</dd>
					</dl>
					<dl class="numinfo">
						<dt>번호</dt>
						<dd>${ebobean.e_no}</dd>
					</dl>
					<!--  -->
				</div>
				<div class="Post_Area nobor">
					<!---->
					<div>
						${e_ct}
					</div>
					<!--//내용영역-->
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<ul class="addfile">
						<li class="pdf"><a href="./resources/upload${ebobean.e_fl}" target="_blank">
						${ebobean.e_fl}</a></li>
					</ul>
				</div>
				<!--//첨부파일-->
<%-- 				<%@ include file="../inc/eboardComm.jsp" %> --%>
			</div>
			<!--//게시글-->
			
			<!--버튼영역-->
		<div class="btnB_area">
<%-- 		<% String id = (String)session.getAttribute("id");%> --%>
		<c:if test="${sessionScope.id == mem_id}">
			<div class="fl">
			 <input type="button" value="삭제" class="white"
			   onclick="location='ebo_delete_ok.brn?num=${ebobean.e_no}&page=${page}'" />
			 <input type="button" value="수정" class="white"
			   onclick="location='eboardView.brn?num=${ebobean.e_no}&page=${page}&state=edit'" />
			</div>
		</c:if>
			 <div class="fr">
			 <input type="button" value="목록" class="black"
			   onclick="location='ebo_list.brn?page=${page}'" />
			</div>
		</div>
			<!-- 
			버튼영역
			<div class="btnB_area">
				<div class="fl">
					<a href="boardList.jsp" class="white">삭제</a>
					<a href="boardWrite.jsp" class="white">수정</a>
				</div>
				<div class="fr">
					<a href="boardList.jsp" class="black">목록</a>
				</div>
			</div> -->
			<!--//버튼영역-->
			
			<!-- 코멘트영역 -->
	<%@ include file="eboardComm.jsp" %>
			<!-- //코멘트영역 -->
			
			<!-- 이전글, 다음글 -->
			<ul class="nextprev_box">
				<li>
					<dl class="next_box">
						<dt class="prne"><span class="noti_txt01">다음글</span></dt>
						<dd class="subject"><a href="eboardView.brn?num=${ebobean.e_no+1}&page=${page}&state=cont">${beannext.e_sj }</a></dd>
						<dd class="prne_date">${beannext.e_dt}</dd>
					</dl>
				</li>
				<li>
					<dl class="prev_box">
						<dt class="prne"><span class="noti_txt01">이전글</span></dt>
						<dd class="subject"><a href="eboardView.brn?num=${ebobean.e_no-1}&page=${page}&state=cont">${beanpre.e_sj }</a></dd>
						<dd class="prne_date">${beanpre.e_dt}</dd>
					</dl>
				</li>
			</ul>
			<!-- //이전글, 다음글 -->
<!-- 		</div> -->
		<!--//게시글영역-->
	<!-- 서브컨텐츠 영역 END -->
	</div>
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>