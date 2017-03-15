<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="./resources/js/jquery.js"></script>	 
	<div class="side_myinfo">
			
		<form>
	
		<input type="hidden" name="mem_no" id="mem_no" value="${obsbean_memo.mem_no}" />
		<input type="hidden" name="mem_id" id="mem_id" value="${obsbean_memo.mem_id}" />	
		
		<dl>
			<dt>MYPAGE</dt>
			<dd class="goalweight">- 50kg</dd>
			<dd class="photozone">
				<input type="file" />
				<img src="/img/main/mainface01.png"/>
			</dd>
			<dd class="goalzone">
				<input type="text" name="my_memo" id="my_memo" value="${obsbean_memo.my_memo}" class="txtgoal">
				<!-- 버튼 클릭시, input disabled 해제 -->
				<input type="button"  class="btn_goal_edit" />
			</dd>
			<dd><strong>ID</strong><span>${obsbean.mem_id }</span></dd>
			
			<dd><strong>현재 몸무게</strong><span>${obsbean.mem_w }</span></dd>
			<dd><strong>현재 키</strong><span>${obsbean.mem_h }</span></dd>
						
			<dd><strong>목표 몸무게</strong><span>100kg</span></dd>
			<dd>어제는 총 <c:if test="${!empty goalMemPercent }" ><em>${goalMemPercent }</em></c:if>의 회원이 목표를 달성!</dd>
			<dd>
				<strong>목표달성</strong>
				<p class="goalcheck">
					<span><input type="radio" name="goal_ox" id="goal_high"><label for="goal_high">높음</label></span>
					<span><input type="radio" name="goal_ox" id="goal_middle"><label for="goal_middle">중간</label></span>
					<span><input type="radio" name="goal_ox" id="goal_low"><label for="goal_low">낮음</label></span>
				</p>
			</dd>
			<dd class="info_list">
				<a href="myScrap.jsp">스크랩목록</a>
				<a href="myBio.jsp">바이오리듬</a>
				<a href="myBmi.jsp">BMI</a>
				<a href="myStatic.jsp">통계</a>
			</dd>
			
		</dl>
		<p>
			<input type="submit" value="등록하기" />
		</p>
		</form>
	</div>
	
	
	<script>
		$(".btn_goal_edit").click(function() {
			if (confirm("수정 하시겠습니까??") == true){
				var mem_no = $("#mem_no").val();
				var my_memo = $("#my_memo").val();
				$.ajax({
					data:{"mem_no" : mem_no,
							"my_memo" : my_memo },
					type : "post",
					url : "memo_edit.brn",
					success : function(data){
						alert("성공");
					},
					error : function(data,status){
						alert('게시물 수정에 실패했습니다.');
					}
				})
			}else{  
				
			}
		})
	</script>