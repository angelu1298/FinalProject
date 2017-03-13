<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.board.gboard.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../../inc/boardHeader.jsp"%>
 

	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../../inc/leftMenu05.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>갤러리</h3>
			<h4>목록</h4>
		
			<!-- 전체리스트-->
			<div class="photolist">
				<!-- 리스트 갯수 -->
				<p class="srch_result">
					<span class="rs_txt"><strong>${listcount}</strong>건이 검색되었습니다.</span>
				</p>
				
				<!-- 리스트 -->
				
				<!--등록된 게시물이 있을경우-->
				<c:if test="${listcount != 0}">
				<ul>
						
					<!-- 화면 출력 번호  변수 정의 -->
					<c:set var="num" value="${listcount-(page-1)*limit}" />
						
					<!-- 반복문 시작 items의 Gbbsgall은 GbbsAction의 --> 
					<c:forEach var="b" items="${Gbbsgall}">
					
						<li>
							<a href="gbbs_cont.brn?num=${b.gbbs_num}&page=${page}&rownum=${b.grnum}&state=cont&listcount=${listcount}">
								<span class="thumb" id="thumb${b.gbbs_num}">
									<span class="slayer"></span>
									<img src="${pageContext.request.contextPath}/resources/img/emo/emow_tt.png"/>
    							</span>
								<strong>${b.gbbs_subject}<span class="cnt">(${b.gbbs_comm})</span></strong>
								<p>
									<span class="date"><fmt:formatDate value="${b.gbbs_date}" pattern="yyyy.MM.dd" /></span>
									<span class="click">${b.gbbs_readcount}</span>
									<span class="like">${b.gbbs_like}</span>
									<span class="author">${b.gbbs_author}</span>
								</p>
							</a>
						</li>
 
						<script type="text/javascript">
						
							$(function(){
								var filelists = "${b.gbbs_file}"; ;
								console.log(filelists);
								filelists = filelists.split(';');
								if(filelists.length!=1){
									var a = filelists[1].replace("gbbs","smgbbs") ;
									var path = "${pageContext.request.contextPath}/resources/upload/" + a;
									$(".photolist > ul > li:eq(${listcount-num}) > a > .thumb").html("<span class=\"slayer\">자세히보기</span><img src=\""+path+"\">");
								}	
							})
							
						</script>
						
						
						<c:set var="num" value="${num-1}" />
						
					</c:forEach>
					<!-- 반복문 끝 -->
				</ul>
				</c:if>
				<!--// 등록된 게시물이 있을경우-->	
	
				<!--등록된 게시물이 없을경우-->
				<c:if test="${listcount == 0}">
					<div class="nolist">등록된 게시물이 없습니다.</div>
				</c:if>
				<!--등록된 게시물이 없을경우-->
					
			</div>
			<!--// 전체리스트-->
	
				
			<!-- 페이징 -->				
			<div class="paginate">
				<p>
					<c:if test="${page <=1 }">
						<span>&lt;&lt;</span>
					</c:if>
					
					<c:if test="${page > 1 }">
						<a href="gbbs_gall.brn?page=${page-1}">&lt;</a>
					</c:if>			
		
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${a}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="gbbs_gall.brn?page=${a}">${a}</a>
						</c:if>
					</c:forEach>			
						
						<c:if test="${page >= maxpage }">
							<span>&gt;</span>
						</c:if>
						<c:if test="${page < maxpage }">
							<a href="gbbs_gall.brn?page=${page+1}">&gt;&gt;</a>
						</c:if>
					</p>
				</div>
				<!-- // 페이징 -->				 
					
				<!--버튼영역-->
				<div class="btnB_area"> 
					<div class="fr">
						<a class="black" href="gbbs_write.brn">글쓰기</a>
					</div>
				</div>
				<!--//버튼영역-->
				
				<!--검색영역 스크립트--> 
				<!--검색영역 form -->
				<div class="borad_srch">
					<!-- form -->
					
					<form method="get" action="gbbs_find_ok.brn" onsubmit="return find_check()">
						
						<span class="fl ml20">
							<select id="viewcount fl" name="limit">
								<option value="20">20개보기</option>
								<option value="50">50개보기</option>
								<option value="100">100개보기</option>
							</select> 
						</span>
						
						<!--한줄-->
						<p class="col"> 
							<p class="fr mr20">
								<label for="srch_sel01" class="sc_txt">검색영역</label>
								<select class="w180 mr10" id="gfind_field" name="gfind_field">
									<option value="">전체검색</option>
									<option value="gbbs_author">작성자</option>
									<option value="gbbs_subject">글제목</option>
									<option value="gbbs_content">글내용</option>
								</select>
								<label for="srch_txt" class="dnone"></label>
								<input type="text" name="gfind_name" id="gfind_name" class="w280 mr10" placeholder="검색어를 입력하세요"/>
								<input type="submit" class="btn_srch" value="검색" />
							</p>
						</p>
						<!--한줄-->
			
					</form>
					<!-- //form -->
				</div>
				<!--//검색영역-->
					
			
		</div>
		<!-- 서브컨텐츠 영역 END -->
	</section>
	<!-- // container End -->

<%@ include file="../../inc/footer.jsp"%>
