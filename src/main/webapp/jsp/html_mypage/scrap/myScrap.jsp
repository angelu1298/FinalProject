<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../inc/subHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->

	<!-- 서브컨텐츠 영역 START -->
	
		<h3>마이페이지</h3>
		<h4>Scrap</h4>

		<div class="tabarea">
			<ul>
				<li><input type="button" value="운동게시판" id="ebo_scrap"/></li>
				<li><input type="button" value="식품게시판" id="fbo_scrap"/></li>
				<li><input type="button" value="자유게시판" id="obo_scrap"/></li>
				<li><input type="button" value="후기게시판" id="sbo_scrap"/></li>
				<li><input type="button" value="이미지게시판" id="gbo_scrap"/></li>
			</ul>
		</div>
		
		<script>
			$(function(){

				$("#obo_scrap").parent().addClass("on");
				$("#scrapview").load("./o_sc_view.brn");
				$("#obo_scrap").click(function() {
					$(".tabarea ul li").removeClass("on");
					$(this).parent().addClass("on");
					$("#scrapview").load("./o_sc_view.brn");
				});
				
				$("#ebo_scrap").click(function() {
					$(".tabarea ul li").removeClass("on");
					$(this).parent().addClass("on");
					$("#scrapview").load("./e_sc_view.brn");
				});

				$("#sbo_scrap").click(function() {
					$(".tabarea ul li").removeClass("on");
					$(this).parent().addClass("on");
					$("#scrapview").load("./s_sc_view.brn");
				});
				
				$("#fbo_scrap").click(function() {
					$(".tabarea ul li").removeClass("on");
					$(this).parent().addClass("on");
					$("#scrapview").load("./f_sc_view.brn");
				});
				
				$("#gbo_scrap").click(function() {
					$(".tabarea ul li").removeClass("on");
					$(this).parent().addClass("on");
					$("#scrapview").load("./g_sc_view.brn");
				});
			})
		</script>
		
		<div id="scrapview">
		
		</div>
		
	
	<!-- 서브컨텐츠 영역 END -->

<!-- // container End -->
<%@ include file="../../inc/footer.jsp"%>