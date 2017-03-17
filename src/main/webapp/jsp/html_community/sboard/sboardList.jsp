<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/inc/boardHeader.jsp"%>
<script>
		$(function(){
						
			$("#viewcount").val(${limit}).prop("selected", true);
			$('#viewcount').change(function(){
				$.ajax({
					data : {"limit" : $('#viewcount').val()},
					type : "get",
					success : function(data){
						$('form[name="search"]').submit();
					 },
					error : function(data, status){
						   				 
					 }
				})
			})
		})
</script>
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>후기게시판</h3>
			
		<%-- 	<!-- 베스트게시글 -->
			<%@ include file="bestSboard.jsp" %>
			 --%>
			<h4>목록</h4>
			<!-- form -->
			<form action="sboardView.brn"  method="post" onsubmit="find_check()">
			<p class="srch_result">
				<span class="rs_txt"><strong>${listcount }</strong>건이 검색되었습니다.</span>
			</p>
			<!--테이블-->
			<div class="common_list_box">
				<table cellpadding="0" cellspacing="0" border="1" summary="운동게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
					<caption>후기게시판</caption>
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
						<!-- 리스트에 뿌려질 글번호 세팅 -->
						<c:set var="num" value="${listcount-(page-1)*limit}"/> 	
						<c:if test="${!empty slist  }">
							<c:forEach items="${slist }" var="list">
								<tr>
									<td><c:out value="${num }" /><c:set var="num" value="${num-1}"/></td>
									<td><a href="sboardcont.brn?s_no=${list.s_no}&page=${page}&state=cont">${list.s_sj }</a>[${list.scomm_cnt }]</td>
									<td>${list.mem_id }</td>
									<td>${list.s_dt }</td>
									<td>${list.s_rc }</td>
									<td>${list.s_lk }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty slist}">
							<!--등록된 게시물이 없는경우-->
							<tr>
								<td class="nolist" colspan="6">등록된 게시물이 없습니다.</td>
							</tr>
							<!--등록된 게시물이 없는경우-->
						</c:if>
						</tbody>
				</table>
				</div>
				<!--테이블-->
				<!--페이징 -->
				<div class="paginate">
					<p>
						<c:if test="${page <=1 }">
							<span>&lt;&lt;</span>
							<span>&lt;</span>
						</c:if>
						
						<c:if test="${page > 1 }">
							<a href="sboardList.brn?page=${1}&limit=${limit}" class="pre" title="맨앞">&lt;&lt;</a>
							<a href="sboardList.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;</a>
						</c:if>		
						
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page }">
								<strong>${a}</strong>
							</c:if>
							<c:if test="${a != page }">
								<a href="sboardList.brn?page=${a}&limit=${limit}"><strong>${a}</strong></a>
							</c:if>
						</c:forEach>			
						<c:if test="${page < maxpage }">
							<a href="sboardList.brn?page=${page+1}&limit=${limit}">&gt;</a>
							<a href="sboardList.brn?page=${maxpage }&limit=${limit}" class="next" title="맨뒤">&gt;&gt;</a>
						</c:if>			
						<c:if test="${page >= maxpage }">
								<span>&gt;</span>
								<span>&gt;&gt;</span>
						</c:if>
					</p>
				</div>
			<div class="btnB_area">
				<div class="fr">
					<a href="./sboardWrite.brn" class="black">글쓰기</a>
				</div>
			</div>
			</form>
			<!-- //form -->
				<!--//페이징 -->	<!--검색영역-->
			<form method="get" action="sboardfind_ok.brn"
		  	onsubmit="return find_check()" name="search">
			<div class="borad_srch">
				<!--한줄-->
					<!--//검색영역-->
					<span class="fl ml20">
							<select id="viewcount fl" name="limit">
								<option value="20">20개보기</option>
								<option value="50">50개보기</option>
								<option value="100">100개보기</option>
							</select> 
					</span>
				<p class="col">
					<label for="srch_sel01" class="sc_txt">검색영역</label>
					<select class="w180 mr10" id="srch_sel01" name="find_field">
							<option value="">검색할 항목을 선택하세요</option>
							<option value="mem_id">작성자</option>
					       <option value="s_sj">글제목</option>
					       <option value="s_ct">글내용</option>
					</select>
					<label for="srch_txt" class="dnone"></label><input type="text" name="find_name" id="srch_txt" class="w280 mr10" placeholder="검색어를 입력하세요"/>
					<input type="submit" class="btn_srch" value="검색" />
				</p>
				<!--한줄-->
			</div>
			</form>
		</div>
		<!-- 서브컨텐츠 영역 END -->
		
	</section>
	<!-- // container End -->

<%@ include file="/jsp/inc/footer.jsp"%>
