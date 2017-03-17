<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p class="srch_result">
	<span class="rs_txt">
		<strong>후기게시판 스크랩</strong>은 
		<strong>${listcount}</strong>건이
		검색되었습니다.
	</span>
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
		<!-- 리스트에 뿌려질 글번호 세팅 -->
		<c:set var="num" value="${listcount-(page-1)*limit}"/> 	
			<c:if test="${listcount > 0 }">
				<c:forEach items="${slist}" var="list">
					<tr>
						<td><c:out value="${num }" /><c:set var="num" value="${num-1}"/>	</td>
						<td><a href="sboardcont.brn?s_no=${list.s_no}&page=${page}&state=cont">${list.s_sj }</a>[ ${list.scomm_cnt }]</td>
						<td>${list.mem_id }</td>
						<td>${list.s_dt }</td>
					</tr>
				</c:forEach>
			</c:if>
			<!--등록된 게시물이 없는경우-->
			<c:if test="${listcount <1 }">
				<tr>
					<td class="nolist" colspan="4">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			<!--등록된 게시물이 없는경우-->
		</tbody>
		</table>
		</div>
		<!--테이블-->
		
		<!--페이징 -->
		<div class="paginate">
			<p>
				<a href="sboardList.brn?page=1" class="pre" title="맨앞">&lt;&lt;</a>
				<c:if test="${page > 1 }">
				<a href="sboardList.brn?page=${page-1}" class="pre" title="이전페이지">&lt;</a>
				</c:if>		
			
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page }">
						<a href="#">${a}</a>
					</c:if>
					<c:if test="${a != page }">
						<a href="sboardList.brn?page=${a}"><strong>${a}</strong></a>
					</c:if>
				</c:forEach>			
				
				<c:if test="${page < maxpage }">
					<a href="sboardList.brn?page=${page+1}">&gt;</a>
				</c:if>			
				
				<a href="sboardList.brn?page=${maxpage }" class="next" title="맨뒤">&gt;&gt;</a>
		
			</p>
		</div>
		<!-- //form --> 
		