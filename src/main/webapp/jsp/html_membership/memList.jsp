<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/boardHeader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./resources/js/managelist.js"></script>
 <script>
        $("#viewcount").val("${limit}").prop("selected", true);
 </script>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="sub_container">

	<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->
	<%@ include file="/jsp/inc/leftMenu_log.jsp"%>

	<!-- 서브컨텐츠 영역 START -->
	<div class="subContent">


		<h4>회원 목록</h4>

		<!-- form -->
		<form action="manageModify.jsp" method="get">
			<p class="srch_result">
				<span class="rs_txt"><strong>${listcount}</strong>건이 검색되었습니다.</span>
				<span class="rs_txt">가입상태 "0"은 가입, "1"은 탈퇴</span>
			</p>
			<!--테이블-->
			<div class="common_list_box">
				<table cellpadding="0" cellspacing="0" border="1"
					summary="회원목록입니다.">
					<caption>회원목록</caption>
					<colgroup>
						<col width="50px">
						<col width="50px">
						<col width="100px">
						<col width="100px">
						<col width="50px">
						<col width="50px">
						<col width="50px">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>가입날짜</th>
							<th>가입상태</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<!--등록된 게시물이 없는경우-->
						<tr>
						<c:if test="${member}=null">
						
							<td class="nolist" colspan="6">등록된 회원이 없습니다.</td>
						</c:if>
						</tr>
						<!--등록된 게시물이 있는 경우-->
						
						<!-- 화면 출력 번호  변수 정의 -->
						<c:set var="num" value="${listcount-(page-1)*limit}" /> 

						<!-- 반복문 시작 -->
						<c:forEach var="b" items="${member}">

							<tr align="center" valign="middle" bordercolor="#333333"
								onmouseover="this.style.backgroundColor='F8F8F8'"
								onmouseout="this.style.backgroundColor=''">
								<td height="23" class="ten">
									<!-- 번호 출력 부분 -->
									<%-- <input type="hidden" name="mem_no" id="mem_no" value="${b.mem_no}"> 
									${b.mem_no}  --%>
									<c:out value="${b.mem_no}" />
								</td>

								<td class="ten">
									<div style="text-align: left">
									<a href="mem_cont.brn?num=${b.mem_no}&page=${page}">
										<c:out value="${b.mem_id}" />
										<%-- ${b.mem_id} --%></a>
									</div>
								</td>


								<td class="ten">
									<div>${b.mem_nm}</div>
								</td>
								<td class="ten">
									<div>${b.mem_ma}</div>
								</td>
								<td class="ten">
									<div>${b.mem_jd}</div>
								</td>
								<td class="ten">
									<div>
									<input type="hidden" name="mem_st" id="mem_st" value="${b.mem_st}">
									${b.mem_st}
									<%-- ${b.mem_st} --%></div>
								</td>
								<td class="ten">
								<%-- 	<input type="button" name="modify2" id="modify2" value="수정" onclick="location.href='manage_edit.brn?mem_id=${b.mem_id}'"> --%>
									<input type="button" name="delete2" id="delete2" value="삭제" onclick="location.href='manage_delete_ok.brn?mem_id=${b.mem_id}'">
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
					<a href="mem_cont.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;&lt;</a>
				</c:if>	
				
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					<strong><span>${a}</span></strong>
				</c:if>
				<c:if test="${a != page }">
					<a href="mem_cont.brn?page=${a}&limit=${limit}" ><strong><span>${a}</span></strong></a>&nbsp;
				</c:if>
				</c:forEach>
					
				<c:if test="${page >= maxpage }">
					<a href="#none" class="next" title="다음페이지">&gt;</a>
				</c:if>
				<c:if test="${page < maxpage }">
					<a href="mem_cont.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
				</c:if>		
				</p>
			</div>
		</form>
			<!--//페이징 -->
			<!--검색영역-->
			<div class="borad_srch">
			<form method="get" action="mem_find_ok.brn"
		  	onsubmit="return find_check()">
				<!--한줄-->
				<p class="col">
					<label for="srch_sel01" class="sc_txt">검색영역</label> 
					<select class="w180 mr10" name="find_field" id="find_field" >
						<option value="mem_id">아이디</option>
		       			<option value="mem_nm">이름</option>
		       			<option value="mem_st">가입상태</option>
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

<%@ include file="/jsp/inc/footer.jsp"%>

