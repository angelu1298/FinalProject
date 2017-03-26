<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- form -->
	<p class="srch_result">
		<span class="rs_txt">
			<strong>운동게시판 스크랩</strong>은 
			<strong>${listcount}</strong>건이
			검색되었습니다.
		</span>
	</p>
			<!--테이블-->
			<div class="common_list_box">
				<table cellpadding="0" cellspacing="0" border="1" summary="운동게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
					<caption>운동게시판</caption>
					<colgroup>
						<col width="50px">
						<col width="100%"> 
						<col width="100px">
						<col width="100px">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
						</tr>
						</thead>
				<tbody>
					<!-- 반복문 시작 -->
						<!--등록된 게시물이 없는경우-->
						<c:if test="${listcount == 0}">
						<tr>
								<td class="nolist" colspan="6">등록된 게시물이 없습니다.</td>
						</tr>
						</c:if>
						<!--등록된 게시물이 있는 경우-->
						<c:if test="${listcount != 0}">
						
						<!-- 화면 출력 번호  변수 정의 -->
						<c:set var="num" value="${listcount-(page-1)*limit}" />

						<c:forEach var="e" items="${ebolist}">
							<tr>
								<td><c:out value="${num }" /><c:set var="num" value="${num-1}"/>	</td>
								<td class="tit"><!-- 제목 출력 부분 -->	
									<a href="eboardView.brn?num=${e.e_no}&page=${page}&state=cont">
										${e.e_sj}
									</a>
								</td>
								<td><!-- 글쓴이 -->
									<div>${mem_id}</div>
								</td>
								<td><!-- 작성일 -->
									<div>${e.e_dt}</div>
								</td>
							</tr>
							</c:forEach>
							</c:if>
				</tbody>
				</table>
				</div>
				<!--테이블-->
				
				<!--페이징 -->
				<div class="paginate">
				<p>
					<a href="e_sc_view.brn?page=1" class="pre" title="맨앞">&lt;&lt;</a>
					<c:if test="${page <=1}">
						<a href="#none" class="pre" title="이전페이지">&lt;</a>
					</c:if>
					<c:if test="${page > 1}">
						<a href="e_sc_view.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;</a>
					</c:if>
					<!-- 페이지가 1,2,3 이 있음 현재페이지가 2 라고 치면 
						1은 2랑 다르니까 1에는 링크가 걸리고 2는 2랑 같으니까 링크가 안걸려야함 현재페이지니까 -->
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${a}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="e_sc_view.brn.brn?page=${a}&limit=${limit}" >${a}</a>&nbsp;
						</c:if>
					</c:forEach>
					<!--  -->
					<c:if test="${page >= maxpage }">
						<a href="#none" class="next" title="다음페이지">&gt;</a>
					</c:if>
					<c:if test="${page < maxpage }">
						<a href="e_sc_view.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
					</c:if>	
					<a href="e_sc_view.brn?page=${maxpage}" class="pre" title="맨뒤">&gt;&gt;</a>
				</p>
					
				</div>
