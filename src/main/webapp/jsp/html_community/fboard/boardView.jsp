<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/fat/resources/js/jquery.min.js"></script>
<script src="./resources/js/fboard.js"></script>



<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp"%>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>식품게시판</h3>
			
			<h4>보기</h4>
		
			<!--게시글-->
			<div class="Board_view">
				<input type="hidden" name="f_no" id="f_no" value="${bbsbean.f_no}" />
				<input type="hidden" name="page" id="page" value="${page}" />
				<!--LIKE-->
				<div class="likeArea">
					<ul> 
						<li><a href="#" class="btn_scrap" title="스크랩" onclick="f_scrap_ok()"><span>스크랩하기</span></a></li>
					</ul>
				</div>
				<!--//LIKE-->
					
				<!--제목-->
				<div class="board_tit">
					${bbsbean.f_sj}
				</div>
				<!--정보-->
				<div class="board_info">
					<dl class="dateinfo">
						<dt>작성일</dt>
						<dd>${bbsbean.f_dt}</dd>
					</dl>
					<dl class="clickinfo">
						<dt>조회수</dt>
						<dd>${bbsbean.f_rc}</dd>
					</dl>
					<dl class="likeinfo">
						<dt>추천수</dt>
						<dd>${bbsbean.f_lk}</dd>
					</dl>
					<dl class="writerinfo">
						<dt>작성자</dt>
						<dd>${bbsbean.mem_id}</dd>
					</dl>
				</div>
				<div class="Post_Area nobor">
					<!---->
					<div>
						${bbsbean.f_ct}
					</div>
					<!--//내용영역-->
				</div>
				<!--첨부파일-->
				<div class="file_Area">
					<ul class="addfile">
						<li class="pdf">
						 <a href="./resources/upload${bbsbean.f_fl}" target="_blank">${bbsbean.f_fl}</a> 
    					<!-- _blank target 속성은 새창에 띄워준다. -->
						</li>
					</ul>
				</div>
				<!--//첨부파일-->
			</div>
			<!--//게시글-->
			<!--버튼영역-->
			<div class="btnB_area">
				<div class="fl">
				<c:if test="${bbsbean.mem_id == sessionScope.mem_id }">
					
	   				<a href="#" class="white" onclick="delete_check()">삭제</a>
					<a href="bbs_cont.brn?num=${bbsbean.f_no}&page=${page}&state=edit" class="white">수정</a>
	   				
   				</c:if>
				</div>
				<div class="fr">
					<a href="bbs_list.brn" class="black">목록</a>
				</div>
			</div>
			<!--//버튼영역-->
			
<!-- 코멘트 영역 -->
<div class="commentList">
			<%@include file="fcomment.jsp" %>
</div>
<!-- 코멘트 영역 END -->
			
			<!-- 이전글, 다음글 -->
			<ul class="nextprev_box">
				<li>
					<dl class="next_box">
						<dt class="prne"><span class="noti_txt01">다음글</span></dt>
						
							
									
							<dd class="subject"><a href="bbs_cont.brn?num=${bbsbean.f_no+1}&state=cont">
								${bean2.f_sj}</a></dd>
							<dd class="prne_date">${bean2.f_dt}</dd>
				
					</dl>
				</li>
				<li>
					<dl class="prev_box">
						<dt class="prne"><span class="noti_txt01">이전글</span></dt>
							<dd class="subject"><a href="bbs_cont.brn?num=${bbsbean.f_no-1}&state=cont">
								${bean1.f_sj}</a></dd>
							<dd class="prne_date">${bean1.f_dt}</dd>
					</dl>
				</li>
			</ul>
			<!-- //이전글, 다음글 -->
			
			
		</div>
		<!--//게시글영역-->




	<!-- 서브컨텐츠 영역 END -->
<!-- 	</div> -->
</section>
<!-- // container End -->
<%@ include file="../../inc/footer.jsp"%>
