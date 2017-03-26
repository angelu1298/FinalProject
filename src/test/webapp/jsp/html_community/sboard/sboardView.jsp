<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/boardHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>후기게시판</h3>
			
			<h4>상세보기</h4>
			<!--게시글-->
			<div class="Board_view">
				<input type="hidden" name="s_no" id="s_no" value="${sbean.s_no }" />
				<input type="hidden" name="page" id="page" value="${page }" />
				<!--LIKE-->
				<div class="likeArea">
					<ul> 
						<li><a href="#" class="btn_scrap" title="스크랩" onclick="scrap_ok()"><span>스크랩하기</span></a></li>
					</ul>
				</div>
				<!--//LIKE-->
						
				<!--제목-->
				<div class="board_tit">
					${sbean.s_sj }
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
						${sbean.s_ct }
					</div>
					<!--//내용영역-->
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<ul class="addfile">
					<c:if test="${!empty filenames }">
						<c:forEach items="${filenames }" var="filename">
						<li class="pdf"><a href="./resources/upload${filename}" 
      						target="_blank">${filename}</a></li>
      					</c:forEach>
      				</c:if>
					</ul>
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
				<c:if test="${sbean.mem_id==sessionScope.mem_id }">
					<a href="#" class="white" onclick="delete_check()">삭제</a>
					<a href="sboardcont.brn?state=edit&s_no=${sbean.s_no }&page=${page}" class="white">수정</a>
				</c:if>
				</div>
				<div class="fr">
					<a href="sboardList.brn" class="black">목록</a>
				</div>
			</div>
			<!--//버튼영역-->
			<!-- 이전글, 다음글 -->
			<ul class="nextprev_box">
				<li>
					<dl class="next_box">
						<dt class="prne"><span class="noti_txt01">다음글</span></dt>
						<c:if test="${!empty beannext }">
						<dd class="subject"><a href="sboardcont.brn?s_no=${beannext.s_no}&page=${page}&state=cont">${beannext.s_sj }</a></dd>
						<dd class="prne_date">${beannext.s_dt }</dd>
						</c:if>
						<c:if test="${empty beannext }">
							<dd class="subject">다음 글이 없습니다.</dd>
							<dd></dd>
						</c:if>
					</dl>
				</li>
				<li>
					<dl class="prev_box">
						<dt class="prne"><span class="noti_txt01">이전글</span></dt>
						<c:if test="${!empty beanpre }">
						<dd class="subject"><a href="sboardcont.brn?s_no=${beanpre.s_no}&page=${page}&state=cont">${beanpre.s_sj }</a></dd>
						<dd class="prne_date">${beanpre.s_dt }</dd>
						</c:if>
						<c:if test="${empty beanpre }">
							<dd class="subject">이전 글이 없습니다</dd>
							<dd></dd>
						</c:if>
					</dl>
				</li>
			</ul>
			<!-- //이전글, 다음글 -->
		<div class="commentList">
			<%@include file="scommList.jsp" %>
		</div>
		<!--//게시글영역-->
	</div>
	<!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>
