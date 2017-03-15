<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %> 
	
	<div class="side_myinfo">
			
		<form>
		<dl>
			<dt>MYPAGE</dt>
			<dd class="goalweight">- 50kg</dd>
			<dd class="photozone">
				<input type="file" />
				<img src="/img/main/mainface01.png"/>
			</dd>
			<dd class="goalzone">
				<input type="text" value="올 여름은 비키니몸매" class="txtgoal">
				<!-- 버튼 클릭시, input disabled 해제 -->
				<input type="button"  class="btn_goal_edit" />
			</dd>
			<dd><strong>ID</strong><span>라이언</span></dd>
			<dd><strong>목표 몸무게</strong><span>100kg</span></dd>
			<dd>어제는 총<em>${goalMemPercent }%</em>의 회원이 목표를 달성!</dd>
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
				<a href="./Statistics.brn">통계</a>
			</dd>
			
		</dl>
		<p>
			<input type="submit" value="등록하기" />
		</p>
		</form>
	</div>