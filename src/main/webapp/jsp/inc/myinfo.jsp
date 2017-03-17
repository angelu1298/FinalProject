<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ page import="com.burn.fat.member.mypage.myinfo.model.*" %>


 		<input type="hidden" name="mem_no" id="mem_no" value="${myinfobean.mem_no}" />
		<input type="hidden" name="mem_id" id="mem_id" value="${myinfobean.mem_id}" />	
		<c:set var="member_bmi" value="${ myinfobean.mem_w / ((myinfobean.mem_h/100) * (myinfobean.mem_h/100)) }"/>
		<c:set var="w_value" value="${(myinfobean.mem_w - myinfobean.goal_w)}"/>
		
		<dl>
			<dt>MYPAGE</dt>
			<c:if test="${w_value<0}">
				<dd class="goalweight">+ ${myinfobean.goal_w - myinfobean.mem_w} kg</dd>
			</c:if>
			<c:if test="${w_value>0}">
				<dd class="goalweight"> ${(myinfobean.goal_w - myinfobean.mem_w)} kg</dd>
			</c:if>
			<dd class="photozone" >
             <c:if test="${empty myinfobean.mem_wb }">
                        <img id="profileImg" src="./resources/upload/default.png" >
                 </c:if> 
                 <c:if test="${!empty myinfobean.mem_wb }">
                  <img id="profileImg" src="./resources/upload/${myinfobean.mem_wb }" height="100px;"/>
             </c:if>
         </dd>
			<dd class="goalzone">
				<input type="text" name="my_memo" id="my_memo" value="${myinfobean.my_memo}" class="txtgoal">
				<input type="button"  class="btn_goal_edit" id=btn_goal_edit""/>
			</dd>
			<dd><strong>ID</strong><span>${myinfobean.mem_id }</span></dd>
			
			<dd><strong>현재 몸무게</strong><span>${myinfobean.mem_w }</span></dd>
			<dd><strong>현재 키</strong><span>${myinfobean.mem_h }</span></dd>
			<dd class="btn_bmi"><strong>bmi</strong><span><fmt:formatNumber value="${member_bmi}" pattern=".0"/></span></dd>		
			<dd class="goalzone">
				<strong>목표 몸무게</strong>
				<input type="text" name="goal_w" id="goal_w"  value="${myinfobean.goal_w}" class="txtgoal">
				<input type="button"  class="btn_goal_edit" id="btn_goalww_edit"/>
			</dd>
			<dd>어제는 총 <em><fmt:formatNumber value="${avrgPerM/3*100 }" pattern=".00"/></em>%의 회원이 목표를 달성!</dd>
			<dd class="info_list">
				<a href="./sc_view.brn">스크랩목록</a>
				<a href="./Statistics.brn">통계</a>
			</dd>
			
		</dl>
		
		
		<script>

			/* 해당 리스트 삭제모달창 불러오기  */
			$(".btn_bmi").click(function(){
				$(".modal_bg").removeClass("off"); 
				$(".modal_bg").animate({"opacity":"1"});
				$(".modal_bg").load("/fat/jsp/html_mypage/bmi/myBmi.jsp"); 
			})
			 
		
			$("#btn_goal_edit").click(function() {
				if (confirm("수정 하시겠습니까??") == true){
					var mem_no = $("#mem_no").val();
					var my_memo = $("#my_memo").val();
					
					$.ajax({
						data:{"mem_no" : mem_no,
								"my_memo" : my_memo },
						type : "post",
						url : "memo_edit.brn",
						success : function(data){
							location.href="my_view.brn";
						},
						error : function(request,status,error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					})
				}else{  
					
				}
			});
			
			
			$("#btn_goalww_edit").click(function() {
				if (confirm("수정 하시겠습니까??") == true){
					var mem_no = $("#mem_no").val();
					var goal_w = $("#goal_w").val();
					
					$.ajax({
						data:{
								"mem_no" : mem_no,
								"goal_w" : goal_w
						},
						type : "post",
						url : "goalw_edit.brn",
						success : function(data){
							location.href="my_view.brn";
						},
						error : function(request,status,error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					})
				}else{  
					
				}
			});
		</script>