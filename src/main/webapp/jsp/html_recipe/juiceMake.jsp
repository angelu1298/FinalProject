<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.food.juice.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@ include file="../inc/subHeader.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/juice.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-custom.js"></script> 
 
		<script>
		
      		$(function () {
      			
                $('ul.vegitablelist > li').draggable({
                    helper: 'clone', zIndex: 100, 	scroll: false
                });
                
                $('ul.fruitlist > li').draggable({
                    helper: 'clone', zIndex: 100, 	scroll: false
                });
  
                $('.in_blender > ul').droppable({
                    accept: '.juiceitemlist > ul > li',
                    start: function (event, ui) {
                   	 $(ui.draggable).fadeOut(function () {
                   		 alert("");
                        	$(this).css({"width":"100px"});
                   	 });
                   }, drop: function (event, ui) {
                        $(ui.draggable).fadeOut(function () {
                        	$(this).appendTo('.in_blender > ul').fadeIn();
                        });
                    }
                });

                $('body').droppable({
                    accept: '.in_blender > ul > li',
                    start: function (event, ui) {
                      	 $(ui.draggable).fadeOut(function () {
                       		 alert("");
                           	$(this).css({"width":"100px"});
                      	 });
                    }, drop: function (event, ui) {
                        $(ui.draggable).fadeOut(function () {
                        	if($(this).hasClass("itemf")){
	                            $(this).appendTo('.juiceitemlist > ul.fruitlist').fadeIn();
                        	} else {
	                            $(this).appendTo('.juiceitemlist > ul.vegitablelist').fadeIn();
                        	}
                        });
                    }
                });

    			// 삭제버튼 모달창으로 페이지 load
    			$(".btn_make").click(function(){
    				
    				$.ajax({
    					data : $("form").serialize(),
    					type : "get",
    					url : "juiceMake02.brn",
    					success : function(data) { 
	    					$('.modal_bg').removeClass("off");
	    					$('.modal_bg').load("juiceMake02.brn");
    					}, error : function(data, status) {
    						
    					}, complete : function(){
    						
    					}
    				}) 
    				
    			})  
            });
      		
	   		</script>

    	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="../inc/leftMenu01.jsp" %>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent"> 
		
			<h3>나의 주스 만들기</h3>
			<h4>나의 주스 만들기</h4>
    		
				<div class="juicemake">
					<h5 class="tittip">원하는 과일이나 채소를 오른쪽 믹서기에 끌어서 놓아주세요</h5>
					<div class="juiceitemlist">
				    	<ul class="fruitlist">
							<c:forEach var="jf" items="${JuiceFList}">
								<li class="ui-widget-content ui-corner-all item itemf">
									<span>${jf.juc_tt}</span>
									<input name="frulist" type="hidden" id="fru${jf.juc_no}" value="${jf.juc_tt}">
								</li>
							</c:forEach>
						</ul>
				    	<ul class="vegitablelist">
							<c:forEach var="jv" items="${JuiceVList}">
								<li class="ui-widget-content ui-corner-all item itemv">
									<span>${jv.juc_tt}</span>
									<input name="veglist" type="hidden" id="veg${jv.juc_no}" value="${jv.juc_tt}">
								</li>
							</c:forEach>
						</ul>
						
				    </div>
					
					<form action="juiceMake02.brn" method="get">
					    <div class="in_blender ui-state-error">
					    	<ul> 
					    	
					    	</ul>
					    </div>
					
						<p class="makebtn">
							<input type="submit" class="btn_make" value="주스 만들기" />
						</p>
					 </form> 
					
				</div>
			
		</div>
		<!-- 서브컨텐츠 영역 END -->
	</section>
	<!-- // container End -->

<%@ include file="../inc/footer.jsp"%>
