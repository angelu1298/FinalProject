<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- form -->
	<p class="srch_result">
		<span class="rs_txt">
			<strong>식품게시판 스크랩</strong>은
		 	<strong>${listcount}</strong>건이
			검색되었습니다.
		</span>
	</p>
		
	<div class="common_list_box">
		<table cellpadding="0" cellspacing="0" border="1"
				summary="운동게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
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
						<!--등록된 게시물이 없는경우-->
						<tr>
							<c:if test="${listcount==0}">
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
								<!-- <td> -->
									<!-- 번호 출력 부분 --> <c:out value="${num}" />  
									<c:set var="num" value="${num-1}" />
								</td>

						
								<td class="ten tit">
										<!-- 제목 출력 부분 -->
										<a href="bbs_cont.brn?num=${b.f_no}&page=${page}&state=cont">
											${b.f_sj} </a>
								</td>


								<td class="ten">
									<div>${b.mem_id}</div>
								</td>
								<td class="ten">
									<div>${b.f_dt}</div>
								</td>
						</c:forEach>
							</tr>

						<!-- 반복문 끝 -->
					</tbody>
				</table>
			</div>
			<!--테이블-->
			<!--페이징 -->
			<div class="paginate">
				<p>
					<a href="f_sc_view.brn?page=1" class="pre" title="맨앞">&lt;&lt;</a>
					
					<c:if test="${page <=1 }">
						<a href="#none" class="pre" title="이전페이지">&lt;&lt;</a> 
					</c:if>
					<c:if test="${page > 1 }">
						<a href="f_sc_view.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;&lt;</a>
					</c:if>	
					
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page }">
						<strong>${a}</strong>
					</c:if>
					<c:if test="${a != page }">
						<a href="f_sc_view.brn?page=${a}&limit=${limit}" >${a}</a>&nbsp;
					</c:if>
					</c:forEach>
						
					<c:if test="${page >= maxpage }">
						<a href="#none" class="next" title="다음페이지">&gt;</a>
					</c:if>
					<c:if test="${page < maxpage }">
						<a href="f_sc_view.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
					</c:if>		
					<a href="f_sc_view.brn?page=${maxpage}" class="pre" title="맨뒤">&gt;&gt;</a>
					
				</p>
			</div>
			<!--//페이징 -->
		<!-- //form -->
