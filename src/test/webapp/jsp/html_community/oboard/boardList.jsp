<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/subHeader.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.board.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/list.js"></script>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

	<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->
	<%@ include file="../../inc/leftMenu05.jsp"%>

	<!-- 서브컨텐츠 영역 START -->
	<div class="subContent">

		<h3>자유게시판</h3>
		<h4>목록</h4>

		<!-- form -->
		<form action="boardView.jsp" method="get">
			<p class="srch_result">
				<span class="rs_txt"><strong>${listcount}</strong>건이 검색되었습니다.</span>
			</p>
			<!--테이블-->
			<div class="common_list_box">
				<table cellpadding="0" cellspacing="0" border="1"
					summary="운동게시판입니다. 제목,글쓴이,등록일,조회수,추천수를 확인할 수 있고, 등록일 기준으로 sorting 됩니다.">
					<caption>운동게시판</caption>
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
			<!--테이블-->
			<!--페이징 -->
			<div class="paginate">
				<!-- 수정 -->
				<p>
					<c:if test="${page <=1 }">
						<span>&lt;&lt;</span>
					</c:if>

					<c:if test="${page > 1 }">
						<a href="obs_list.brn?page=${1}&limit=${limit}" class="pre"
							title="맨앞">&lt;&lt;</a>
						<a href="obs_list.brn?page=${page-1}&limit=${limit}" class="pre"
							title="이전페이지">&lt;</a>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${page}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="obs_list.brn?page=${a}&limit=${limit}">${a}</a>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
							&gt; 
						</c:if>
					<c:if test="${page < maxpage }">
						<a href="obs_list.brn?page=${page+1}&limit=${limit}">&gt;</a>
						<a href="obs_list.brn?page=${maxpage}&limit=${limit}">&gt;&gt;</a>
					</c:if>
				</p>

			</div>
			<!--//페이징 -->
		</form>
		<!-- //form -->


		<!--//검색영역-->
		<select id="viewcount" name="limit">
			<option value="20">20줄보기</option>
			<option value="10">10줄보기</option>
			<option value="5">5줄보기</option>
		</select>
		<!--버튼영역-->
		<div class="btnB_area">
			<div class="fr">
				<a href="obs_write.brn" class="black">글쓰기</a>
			</div>
		</div>
		<!--//버튼영역-->


		<!--검색영역-->
		<form method="get" action="obs_find_ok.brn"
			onsubmit="return find_check()" name="search">
			<div class="borad_srch">
				<!--한줄-->
				<p class="col">
					<label for="srch_sel01" class="sc_txt">검색영역</label> <select
						class="w180 mr10" id="srch_sel01" name="find_field">
						<option value="">검색할 항목을 선택하세요</option>
						<option value="mem_id">작성자</option>
						<option value="o_sj">글제목</option>
						<option value="o_ct">글내용</option>
					</select> <label for="srch_txt" class="dnone"></label><input type="text"
						name="find_name" id="srch_txt" class="w280 mr10"
						value="검색어를 입력하세요" /> <input type="submit" class="btn_srch"
						value="검색" />


				</p>
				<!--한줄-->
			</div>
		</form>



	</div>
	<!-- 서브컨텐츠 영역 END -->
</section>
<!-- // container End -->
<script>

$("#viewcount").val("${limit}").prop("selected",true);

function find_check(){
	if($('#srch_sel01').val().trim()==''){
		alert('검색할 항목을 설정해주세요');
		return false;
	}
	
	if($('#srch_txt').val().trim()==''){
		alert('검색할 값을 입력해주세요');
		return false;
	}
	
	if($('#srch_txt').val().trim()=='검색어를 입력하세요'){
		alert('검색할 값을 입력해주세요');
		return false;
	}
	
		
}
</script>
<%@ include file="../../inc/footer.jsp"%>