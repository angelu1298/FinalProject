<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	Calendar cal = Calendar.getInstance();
	int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
	int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

	// 시작요일 확인
	// - Calendar MONTH는 0-11까지임
	cal.set(year, month, 1);
	int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

	// 다음/이전월 계산
	// - MONTH 계산시 표기월로 계산하기 때문에 +1을 한 상태에서 계산함
	int prevYear = year;
	int prevMonth = (month + 1) - 1;
	int nextYear = year;
	int nextMonth = (month  + 1) + 1;

	// 1월인 경우 이전년 12월로 지정
	if (prevMonth < 1) {
		prevYear--;
		prevMonth = 12;
	}

	// 12월인 경우 다음년 1월로 지정
	if (nextMonth > 12) {
		nextYear++;
		nextMonth = 1;
	}
%>
		<!-- 월선택 -->
		<div class="datebtnarea">
			<a href="./mypage_main.jsp?y=<%=prevYear%>&m=<%=prevMonth%>" title="이전달" class="calprv"> ◁ </a> 
			<strong><%=year%>년 <%=month+1%>월</strong>
			<a href="./mypage_main.jsp?y=<%=nextYear%>&m=<%=nextMonth%>" title="다음달" class="calnxt"> ▷ </a>
		</div>
		<!-- 달력 -->
		<div class="calendar">
			<!-- 테이블 헤더 -->
			<p>
				<span>월</span>
				<span>화</span>
				<span>수</span>
				<span>목</span>
				<span>금</span>
				<span>토</span>
				<span>일</span>
			</p>
			<!-- 테이블 바디 -->
			<ul>
			<%	// 시작요일까지 이동
				for (int i=1; i<bgnWeek; i++) out.println("<li></li>");
				// 첫날~마지막날까지 처리 - 날짜를 하루씩 이동하여 월이 바뀔때까지 그린다
				int a=1;
				while (cal.get(Calendar.MONTH) == month) {
					
					String datelist = "";
					/* if문으로 오늘만 체크할 수 있게!*/
					datelist += "<p class=\"goalcheck\">"
								+ "<span><input type=\"radio\" name=\"goal_ox" + a + "\" id=\"goal_high" + a + "\" ><label for=\"goal_high" + a + "\" >높음</label></span>"
								+ "<span><input type=\"radio\" name=\"goal_ox" + a + "\" id=\"goal_middle" + a + "\" ><label for=\"goal_middle" + a + "\" >중간</label></span>"
								+ "<span><input type=\"radio\" name=\"goal_ox" + a + "\" id=\"goal_low" + a + "\" ><label for=\"goal_low" + a + "\">낮음</label></span>"
								+ "</p>";
					out.println("<li><strong class=\"month_day\">" + cal.get(Calendar.DATE) + "</strong>" + datelist + "</li>");
			
					// 토요일인 경우 다음줄로 생성
					if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) out.println("");
			
					// 날짜 증가시키지
					cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);

					a++;
				}

				// 끝날부터 토요일까지 빈칸으로 처리
				for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) out.println("<li>&nbsp;</li>");
			%>
		</ul>
	 	
	 	<!-- 목표 -->
	 	<div>
			<input type="text" /><input type="button">				
		</div>
		
	</div>