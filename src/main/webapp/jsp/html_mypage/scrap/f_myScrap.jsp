<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- form -->
<p class="srch_result">
	<span class="rs_txt"><strong>자유게시판 스크랩</strong>은 <strong>${listcount}</strong>건이
		검색되었습니다.</span>
</p>
<!--테이블-->
<div class="common_list_box">
	<table cellpadding="0" cellspacing="0" border="1" summary="자유게시판 스크랩">
		<caption>자유게시판 스크랩</caption>
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

			<c:if test="${listcount<1}">

				<!--등록된 게시물이 없는경우-->
				<tr>
					<td class="nolist" colspan="6">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>


			<c:if test="${listcount>0}">
				<!--등록된 게시물이 있는경우-->


				<!-- 화면 출력 번호  변수 정의 -->
				<c:set var="num" value="${listcount-(page-1)*limit}" />

				<!-- 반복문 시작 -->
				<c:forEach var="o" items="${obslist}">

					<tr align="center" bordercolor="#333333"
						onmouseover="this.style.background='#FFF8F8'"
						onmouseout="this.style.background=''">
						<td height="23" class="ten">
							<!-- 번호 출력 부분 --> <c:out value="${num}" /> <c:set var="num"
								value="${num-1}" />
						</td>

						<td class="ten">
							<div style="text-align: left">


								<!-- 제목 출력 부분 -->
								<a href="obs_view.brn?num=${o.o_no}&page=${page}&state=cont">
									${o.o_sj} </a>

								<c:if test="${o.o_fl !=null}">
									<a style="color: red">첨부파일 있음</a>
								</c:if>
							</div>
						</td>
						<td class="ten">
							<div>${o.mem_id}</div>
						</td>
						<td class="ten">
							<div>${o.o_dt}</div>
						</td>
						<td class="ten">
							<div>${o.o_rc}</div>
						</td>
						<td class="ten">
							<div>${o.o_lk}</div>
						</td>
					</tr>

				</c:forEach>
				<!-- 반복문 끝 -->
			</c:if>
		</tbody>
	</table>
</div>
<div class="paginate">
				<p>
					<a href="o_sc_view?page=1" class="pre" title="맨앞">&lt;&lt;</a>
					<c:if test="${page <=1}">
						<a href="#none" class="pre" title="이전페이지">&lt;</a>
					</c:if>
					<c:if test="${page > 1}">
						<a href="o_sc_view?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;</a>
					</c:if>
					<!-- 페이지가 1,2,3 이 있음 현재페이지가 2 라고 치면 
						1은 2랑 다르니까 1에는 링크가 걸리고 2는 2랑 같으니까 링크가 안걸려야함 현재페이지니까 -->
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<span>${a}</span>
						</c:if>
						<c:if test="${a != page }">
							<a href="o_sc_view?page=${a}&limit=${limit}" ><span>${a}</span></a>&nbsp;
						</c:if>
					</c:forEach>
					<!--  -->
					<a href="o_sc_view?page=${maxpage}" class="pre" title="맨뒤">&gt;&gt;</a>
					<c:if test="${page >= maxpage }">
						<a href="#none" class="next" title="다음페이지">&gt;</a>
					</c:if>
					<c:if test="${page < maxpage }">
						<a href="o_sc_view?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
					</c:if>	
				</p>
					

				</div>
				<!--//페이징 -->

</form>