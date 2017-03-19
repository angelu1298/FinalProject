<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.burn.fat.board.gboard.model.*"%>
<%@ page import="com.burn.fat.board.sboard.model.*"%>
<%@ page import="com.burn.fat.board.eboard.model.*"%>
<%@ page import="com.burn.fat.board.fboard.model.*"%>
<%@ page import="com.burn.fat.board.oboard.model.*"%> 

<%@ include file="../inc/mainHeader.jsp"%>
 
		<!-- 메인영역 -->
		<script>
			$(function(){
				$(".centerbox > ul > li > dl > dt").click(function(){
					$(".centerbox > ul > li").removeClass("on")
					$(this).parents("li").addClass("on");
				})
				parent.document.getElementById('talkframe').src = "http://127.0.0.1:3000?mem_id=${sessionScope.mem_id}";
			})
		</script>
			
		<section class="mainarea">
			<div class="mainbox">
			
				<div class="leftbox">
					<div class="box box01">
						<h4>Calculator</h4> 
						<a href="./cal_diet.brn">diet</a>
						<a href="./bringtype.brn">exercise</a>
					</div>
					<div class="box box02">
						<h4>My Park Serach</h4>
						<form action="./parkList.brn" method="get">
							<input type="text"   name="findname" />
							<input type="submit" value="검색하기" class="btn_srch" />
						</form> 
					</div>
				</div>
				
				<div class="centerbox">	
				
					<div class="c_tit">
						<h2>My DIET Story</h2>
						<p>다이어트 관련 이야기나 사진등을 공유합니다.</p>
					</div>
					
					<ul>	
						<!-- 운동게시판 -->
						<li class="on">
							<dl>
								<dt>운동</dt>
								<dd>
									<c:if test="${!empty elist}">
									<c:forEach items="${elist}"  var="e">
										<a href="./eboardView.brn?num=${e.e_no}&page=1&state=cont">
											<strong>${e.e_sj}</strong>
											<span class="date">${e.e_dt}</span>
										</a>
									</c:forEach>
									</c:if>
									<c:if test="${empty elist}">
										<p class="nolist">등록된 게시물이 없습니다.
									</c:if>
								</dd>	
							</dl>
						</li>
						
						<!-- 식품게시판 -->
						<li>
							<dl>
								<dt>식품</dt>
								<dd>
									<c:if test="${!empty olist}">
									<c:forEach items="${olist}" var="o">
										<a href="./obs_view.brn?num=${o.o_no}&page=1&state=cont">
											<strong>${o.o_sj}</strong>
											<span class="date">${o.o_dt}</span>
										</a>
									</c:forEach>
									</c:if>
									<c:if test="${empty olist}">
										<p class="nolist">등록된 게시물이 없습니다.
									</c:if>
								</dd>
							</dl>
						</li>
						
						<!-- 자유게시판 -->
						<li>
							<dl>
								<dt>자유</dt>
								<dd>
									<c:if test="${!empty flist}">
										<c:forEach items="${flist}" var="f">
											<a href="./fbs_list.brn?num=${f.f_no}&page=1&state=cont">
												<strong>${f.f_sj}</strong>
												<span class="date">${f.f_dt}</span>
											</a>
										</c:forEach>
									</c:if>
									<c:if test="${empty flist}">
										<p class="nolist">등록된 게시물이 없습니다.
									</c:if>
								</dd>
							</dl>
						</li>
						
						<!-- 후기게시판 -->
						<li>
							<dl>
								<dt>후기</dt>
								<dd>
									<c:if test="${!empty slist}">
									<c:forEach items="${slist}"  var="s">
										<a href="./sboardcont.brn?s_no=${s.s_no}&page=1&state=cont">
											<strong>${s.s_sj}</strong>
											<span class="date">${s.s_dt}</span>
										</a>
									</c:forEach>
									</c:if>
									<c:if test="${empty slist}">
										<p class="nolist">등록된 게시물이 없습니다.
									</c:if>
								</dd>
							</dl>
						</li>
					</ul>
				
				</div>
				<div class="rightbox">
					<div class="box box03">
						<h4>Calory Serach</h4>
						<form action="./cuisineList.brn" method="get">
							<input type="text"   name="findname" />
							<input type="submit" value="검색하기" class="btn_srch" />
						</form>
					</div>
					<div class="box box04">
						<h4>Chatting</h4>
						<input type="button" value="입장하기" class="btn_enter macaotalkview" />
					</div>
				</div>
				
			</div>
			
			<div class="pinkarea">
				<div>
				
					<dl class="pink01">
						<dt><a href="./juiceMake.brn">당신의 건강주스 칼로리를 계산해드립니다</a></dt>
						<dd><a href="./juiceMake.brn">My DIET Recipe</dd>
					</dl>
					
					<dl class="pink02">
						<dt><a href="">효율적인 몸매관리를 도와드립니다</a></dt>
						<dd><a href="">My DIET Diary</a></dd>
					</dl>
					
					<dl class="pink03">
						<dt><a href="./obs_list.brn">다이어트에 관련된 이야기를 공유합니다</a></dt>
						<dd><a href="./obs_list.brn">My DIET Story</a></dd>
					</dl>
				
				</div>
			</div>
			
			<div class="bottomarea">
				<div>
					<ul>
						<li class="btn_Exercise"><a href="./bringtype.brn">Exercise</a></li>
						<li class="btn_Cuisine"><a href="./cuisineList.brn">Cuisine</a></li>
						<li class="btn_Grocery"><a href="./groceryList.brn">Grocery</a></li>
						<li class="btn_Diet"><a href="./cal_diet.brn">Diet</a></li>
					</ul>
				</div>
			</div>
			
		</section>
		
		
<%@ include file="../inc/footer.jsp"%>
		
		
		
		
		
		
		
		
		
		
		
		