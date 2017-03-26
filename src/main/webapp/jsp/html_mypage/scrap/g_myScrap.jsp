<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
		
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
									<span class="author">${b.gbbs_userid}</span>
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
					<a href="g_sc_view.brn?page=1" class="pre" title="맨앞">&lt;&lt;</a>
					<c:if test="${page <=1 }">
						<a href="#none" class="pre" title="이전페이지">&lt;</a>
					</c:if>
					
					<c:if test="${page > 1 }">
						<a href="g_sc_view.brn?page=${page-1}&limit=${limit}" class="pre" title="이전페이지">&lt;</a>
					</c:if>			
		
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<strong>${a}</strong>
						</c:if>
						<c:if test="${a != page }">
							<a href="g_sc_view.brn?page=${a}&limit=${limit}" >${a}</a>&nbsp;
						</c:if>
					</c:forEach>			
						<c:if test="${page >= maxpage }">
							<a href="#none" class="next" title="다음페이지">&gt;</a>
						</c:if>
						<c:if test="${page < maxpage }">
						<a href="g_sc_view.brn?page=${page+1}&limit=${limit}" class="next" title="다음페이지">&gt;</a>
						</c:if>
					<a href="g_sc_view.brn?page=${maxpage}" class="pre" title="맨뒤">&gt;&gt;</a>
						
					</p>
				</div>
				<!-- // 페이징 -->				 
					 