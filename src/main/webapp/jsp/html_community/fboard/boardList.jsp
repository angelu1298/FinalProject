<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/fat/resources/js/jquery.min.js"></script>
 <script src="./resources/js/list.js"></script>
    
    
 <script>
        $("#viewcount").val("${limit}").prop("selected", true);
 </script>
<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

	<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->
	<%@ include file="../../inc/leftMenu05.jsp"%>

	<!-- 서브컨텐츠 영역 START -->
	<div class="subContent">

		<h3>식품게시판</h3>

		<h4>목록</h4>

		<!-- form -->
		<form action="boardView.jsp" method="get">
			<p class="srch_result">
				<span class="rs_txt"><strong>${listcount}</strong>건이 검색되었습니다.</span>
			</p>
			<!--테이블-->
			<div class="common_list_box">
				<table cellpadding="0" cellspacing="0" border="1"
					summary="식품게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
					<caption>식품게시판</caption>
					<colgroup>
						<col width="50px">
						<col width="100%">
						<col width="100px">
						<col width="100px">
						<col width="50px">
						<col width="50px">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>조회</th>
							<th>추천</th>
						</tr>
					</thead>
					<tbody>
						<!--등록된 게시물이 없는경우-->
						<tr>
						<c:if test="${bbslist}=null">
						
							<td class="nolist" colspan="6">등록된 게시물이 없습니다.</td>
						</c:if>
						</tr>
						<!--등록된 게시물이 있는 경우-->
						
						<!-- 화면 출력 번호  변수 정의 -->
						<c:set var="num" value="${listcount-(page-1)*limit}" />

						<!-- 반복문 시작 -->
						<c:forEach var="b" items="${bbslist}">

							<tr align="center" valign="middle" bordercolor="#333333"
								onmouseover="this.style.backgroundColor='F8F8F8'"
								onmouseout="this.style.backgroundColor=''">
								<td height="23" class="ten">
									<!-- 번호 출력 부분 --> <c:out value="${num}" /> 
									<c:set var="num" value="${num-1}" />
								</td>

								<td class="ten">
									<div style="text-align: left">


										<!-- 제목 출력 부분 -->
										<a href="bbs_cont.brn?num=${b.f_no}&page=${page}&state=cont">
											${b.f_sj} </a>[${b.fcomm_cnt}]
											
										<%-- <c:set var="logtime" 
										value="${boardNoticeDTO.getNo_logtime().substring(0,10).replaceAll('-', '').trim() }"></c:set>
										<c:if test="${date-logtime < 1 }">
										&nbsp;<img src="../images/board/new.gif" align="absmiddle">
										</c:if> --%>
										<!-- 새글 표시 -->
									</div>
								</td>


								<td class="ten">
									<div>${b.mem_id}</div>
								</td>
								<td class="ten">
									<div>${b.f_dt}</div>
								</td>
								<td class="ten">
									<div>${b.f_rc}</div>
								</td>
								<td class="ten">
									<div>${b.f_lk}</div>
								</td>
							</tr>

						</c:forEach>
						<!-- 반복문 끝 -->
					</tbody>
				</table>
			</div>
			<!--테이블-->
			<!--페이징 -->
			<div class="paginate">
				<p>
				<c:if test="${page <=1 }">
					<a href="#none" class="pre" title="이전페이지">&lt;&lt;</a> 
				</c:if>
				<c:if test="${page > 1 }">
					<a href="bbs_list.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;&lt;</a>
				</c:if>	
				
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					<strong><span>${a}</span></strong>
				</c:if>
				<c:if test="${a != page }">
					<a href="bbs_list.brn?page=${a}&limit=${limit}" ><strong><span>${a}</span></strong></a>&nbsp;
				</c:if>
				</c:forEach>
					
				<c:if test="${page >= maxpage }">
					<a href="#none" class="next" title="다음페이지">&gt;</a>
				</c:if>
				<c:if test="${page < maxpage }">
					<a href="bbs_list.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
				</c:if>		
				</p>
			</div>
		</form>
			<!--//페이징 -->
			<!--검색영역-->
			<div class="borad_srch">
			<form method="get" action="bbs_find_ok.brn"
		  	onsubmit="return find_check()">
				<!--한줄-->
				<p class="col">
					<label for="srch_sel01" class="sc_txt">검색영역</label> 
					<select class="w180 mr10" name="find_field" id="find_field" >
						<option value="bbs_name">작성자</option>
		       			<option value="bbs_subject">글제목</option>
		       			<option value="bbs_content">글내용</option>
					</select> 
					<label for="srch_txt" class="dnone"></label>
					<input type="text" name="find_name" id="find_name" class="w280 mr10"/> 
					<input type="submit" class="btn_srch" value="검색" />
				</p>
			</form>
				<!--한줄-->
			</div>
			
			<!--//검색영역-->
			
			<!--버튼영역-->
			<div class="btnB_area"> 
				<div class="fr">
					<a href="bbs_write.brn" class="black">글쓰기</a>
					<select id="viewcount">
	   		 	 	 <option value="10">10줄 보기</option>
	   		 	 	 <option value="20">20줄 보기</option>
	   		 	 	 <option value="30">30줄 보기</option>
	   				</select>	   		
				</div>
			</div>
			<!--//버튼영역-->
			

		<!-- //form -->
	</div>
	<!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>
