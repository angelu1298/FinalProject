<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.emo_tt {
	background-image: url("./resources/img/emo/emo_tt.png");
}

.emo_smile {
	background-image: url("./resources/img/emo/emo_smile.png");
}

.emo_wink {
	background-image: url("./resources/img/emo/emo_wink.png");
}
</style>
<script src="./resources/js/jquery.min.js"></script>
<!-- 월선택 -->
<div class="datebtnarea">
	<a href="./my_view.brn?y=${prevYear }&m=${prevMonth }&d=1" title="이전달" class="calprv"> ◁ </a> 
	<strong>${year }년 ${month+1 }월</strong> 
	<a href="./my_view.brn?y=${nextYear }&m=${nextMonth }&d=1" title="다음달" class="calnxt"> ▷ </a>
</div>
<!-- 달력 -->
<div class="calendar">
	<!-- 테이블 헤더 -->
	<p>
		<span>일</span> 
		<span>월</span> 
		<span>화</span> 
		<span>수</span> 
		<span>목</span>
		<span>금</span> 
		<span>토</span>
	</p>
	<!-- 테이블 바디 -->
		<script>
         
         $(function(){
         
            var m = ${month+1};
            var y = ${year};
            var d;
            $(".month_day").click(function(){
               d = parseInt($(this).text());
               
                location.href="goweekly.brn?y="+y+"&m="+m+"&d="+d;
               //location.href="mypage_main_weekly.jsp";
            })            
            
         })
         </script>
		<ul>


		<c:forEach begin="1" end="${bgnWeek-1 }" step="1">
			<li></li>
		</c:forEach>



		<c:if test="${calMonth==month }">
			<c:forEach var="a" begin="1" end="${lastDay }" step="1">
				<li><strong class="month_day"> ${a } </strong> <!-- --------------------------- -->
					<p class="goalcheck">
						<c:forEach var="cal" items="${calendarList}">

							<!-- 년도~~ -->
							<c:if test="${year==cal.y}">
								<!-- 월~~~ -->
								<c:if test="${month+1==cal.m}">
									<!-- 일~~ -->
									<c:if test="${a==cal.d }">
										<!-- 평가 -->
										<c:if test="${cal.cal_eval == 3 }">
											<span><input type="radio" name="goal_ox${a }"
												class="emo_wink" id="goal_high${a }"><label
												for="goal_high${a }">높음</label></span>
												111
										</c:if>
										<c:if test="${cal.cal_eval == 2 }">
											<span><input type="radio" name="goal_ox${a }"
												class="emo_smile" id="goal_middle${a }"><label
												for="goal_middle${a }">중간</label></span>
										</c:if>
										<c:if test="${cal.cal_eval == 1 }">
											<span><input type="radio" name="goal_ox${a }"
												class="emo_tt" id="goal_low${a }"><label
												for="goal_low${a }">낮음</label></span>
										</c:if>
										<!-- 평가 end -->

									</c:if>
									<!-- a=cal.d end -->
								</c:if>
								<!-- month+1=cal.m end -->
							</c:if>
							<!-- year=cal.y end -->
						</c:forEach>
						<!-- calendarList end -->
					</p> <!-- --------------------------- --></li>
			</c:forEach>

		</c:if>
		<!-- calMonth end -->
		<!--                  </li> -->
		<c:if test="${lastDate%7==7 }">
		</c:if>
		
		<c:if test="${lastDate%7!=7 }">
			<c:forEach begin="${lastDate%7 }" end="6" step="1">
				<li>&nbsp;</li>
			</c:forEach>
		</c:if>
	</ul>

	<!-- 목표 -->
	<div>
		<input type="text" /><input type="button">
	</div>

</div>