<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%@ include file="../../inc/subHeader.jsp" %>
	
	<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
	<section class="sub_container">
	
		<div class="side_myinfo">
			
		</div>
		
		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
		
			<h3>마이페이지</h3>
			<h4>운동</h4>
			<!-- 처음~ -->
			
			<%-- class="운동타입 ${a.erc_ty}" --%>
			<div class="cal_content">	
				<div class="cal_contentarea">
				
					 <script>
					$(function(){
						$(".side_myinfo").load("my_info.brn");
							$(".type").click(function(){
								var erc_ty = $(this).val();
								$.ajax({
									type : "post",
									url : "cal_exer_ok.brn",
									data : {
										"erc_ty" : erc_ty
									},
									success : function(data){
										$("#step2").empty();
										 $("#step2").append(data);
										
									},
									error : function(data, status) {
										alert('에러가 발생했습니다. 다시 실행해주세요');
									}
								});// ajax
					
							});
					});
					</script> 
					<c:forEach var="a" items="${erc_ty}" >
						<input type="button" class="type" name="${a.erc_ty}" value="${a.erc_ty}" />
					</c:forEach>
			
					<div class="step2 " id="step2"></div> 
				</div>
			</div>
			
		</div>		
		<!-- 서브컨텐츠 영역 END -->
	
	</section>
	<!-- // container End -->
	
	<%@ include file="../../inc/footer.jsp" %>