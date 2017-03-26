   <%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
         <ul>
         <script>
         
         $(function(){
         
            var m = ${month+1};
            var y = ${year};
            var d;
            $(".month_day").click(function(){
               d = parseInt($(this).text());
                alert(y+"/"+m+"/"+d)
               
                location.href="goweekly.brn?y="+y+"&m="+m+"&d="+d;
               //location.href="mypage_main_weekly.jsp";
            })            
            
         })
         </script>
         <c:forEach begin="1" end="${bgnWeek-1 }" step="1">
         	<li></li>
         </c:forEach>
         <c:if test="${calMonth==month }">
         <c:forEach var="a" begin="1" end="${lastDay }" step="1">
           		<li><strong class="month_day"> ${a } </strong>
           		  	<p class="goalcheck">
		              <span><input type="radio" name="goal_ox${a }"  id="goal_high${a }"><label for="goal_high${a }" >높음</label></span>
		              <span><input type="radio" name="goal_ox${a }" id="goal_middle${a }" ><label for="goal_middle${a }" >중간</label></span>
		              <span><input type="radio" name="goal_ox${a }" id="goal_low${a }"  ><label for="goal_low${a }">낮음</label></span>
              		</p>
           		</li>
           		<c:if test="${a%7==6 }">
           		</c:if>
            </c:forEach>
         </c:if>          
          <c:forEach begin="${a%7 }" end="7" step="1">
         	<li>&nbsp;</li>
         </c:forEach>    

      </ul>
       
       <!-- 목표 -->
       <div>
         <input type="text" /><input type="button">            
      </div>
      
   </div>