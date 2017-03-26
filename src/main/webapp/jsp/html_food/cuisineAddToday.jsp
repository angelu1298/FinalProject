<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
   
      <script src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
      <%-- <script src="${pageContext.request.contextPath}/resources/js/angular_dataCtrl.js"></script> --%>
               
         <script>
         
            function dataCtrl($scope){
               $scope.cus_tt = "흠";
               $scope.cus_cal = ${param.cus_cal};
               $scope.day = "day";
               $scope.quantity = 1.0;
            }
         
         </script>
         
         <script>
         
            $(function(){

               $(".col strong input").val($(".cus_cal").val()); 
               console.log("이값은... " + $(".col strong input").val());
               
                $(".btn_submit").click(function(){
                   $("#addok").submit();
                });
                
                $(".btn_close").click(function(){
                   $(".modal_bg").html();
                   $(".modal_bg").addClass("off");
                   $(".modal_bg").animate({"opacity":"0"});
                })
                
            })
            
         </script>
         
         <!-- 추가 -->
         
		<%
			request.setCharacterEncoding("UTF-8");
			String cus_tt = request.getParameter("cus_tt");
			String decodeResult = URLDecoder.decode(cus_tt, "UTF-8");
		%>
		
      <div class="modal_area size_medium">
      <h1>오늘의 섭취 목록에 추가<span class="btn_close">x</span></h1>
      
      <div class="layer_content" ng-controller="dataCtrl">
        <%--  <h2 class="mt20"><strong class="txtred">&lt; ${param.cus_tt} &gt;</strong>을(를)<br/>오늘 식단에 추가하시겠습니까?</h2> --%>
         <h2 class="mt20"><strong class="txtred">&lt; <%=decodeResult%> &gt;</strong>을(를)<br/>오늘 식단에 추가하시겠습니까?</h2>
         <form method="get" action="cuisineAddTodayOk.brn" class="style_corfrim" id="addok">
         
         <input type="hidden" name="cus_tt" value="${param.cus_tt}"/>
            <!-- 식사 선택 -->
            <span class="select_meal_time">
               <!-- name수정, value속성 추가 -->
               <input type="radio" id="meal_morning" name="wholeDay" value="morning" /><label for="meal_morning">아침</label>
               <input type="radio" id="meal_lunch"   name="wholeDay" value="lunch" /><label for="meal_lunch">점심</label>
               <input type="radio" id="meal_dinner"  name="wholeDay" value="dinner" /><label for="meal_dinner">저녁</label>
            </span>
            
            <p class="col">
               1회 제공량 기준입니다.
               <!-- 몇회(인분) 제공량 표시 -->
               <input type="number" name="quantity" ng-model="quantity" class="quantity" step="0.5" min="0" max="5" /><span>회 섭취</span>
               <p class="smalltip">1회 이하도 입력이 가능합니다. 적게 섭취하신 경우, 참고하여 입력해주세요</p>
               <!-- 칼로리 표시 -->
               <p class="txt_rslt">
                  <!-- 칼로리 결과값 : name값 cus_kcal로 바꿨어용 !!!!!! -->
                  총 {{ quantity }} 회 섭취 : 
                  <input type="text" class="total_calory" name="cus_kcal" value="{{ quantity * cus_cal }}" readOnly /> 
                  칼로리를 추가 하시겠습니까?
               </p>
            </p>
            
             <p class="modal_btn_area">
               <input type="submit" value="추가하기" class="btn_submit" />
               <input type="reset" value="취소" class="btn_close" />
            </p>
            
         </form>
      </div> 
   </div>