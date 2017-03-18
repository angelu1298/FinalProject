<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
         
   <%@ include file="../inc/subHeader.jsp" %>
   
   <!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
   <section class="sub_container">
   
      
      <!-- 서브컨텐츠 영역 START -->
      <div class="subContent">

	      <!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->   
	    <!--   <script>
	         $(function(){
	            $(".side_myinfo").load("my_info.brn");
	         })
	      </script> -->
	      
	      <div class="side_myinfo">
	         
	      </div>
	      

		<h3>WORKOUT</h3>
		<h4>Diet Calculator</h4>
         <script>
         $(function() { 
        	 
            $('.cal_diet_button').each(function(index, item) {
            	
               $(this).on('click', function() {
                  var now_weight = $('.now_weight').val();
                  var goal_weight = $('.goal_weight').val();
                  var goalDay = $('.goalDay').val();
                  var select_per = $('.per:checked').val();
                  alert(select_per);
                  $.ajax({
                     data : {
                        "now_weight" : now_weight,
                        "goal_weight" : goal_weight,
                        "goalDay" : goalDay,
                        "select_per" : select_per
                     
                     },
                     type : "post",
                     url : "calculator_diet_ok.brn",
                     success : function(data) {
                         $('.modal_bg').load('cal_diet_result.brn');
                         $('.modal_bg').removeClass("off");
                         $('.modal_bg').animate({"opacity":"1"});
                     },
                     error : function(data, status) {
                        alert('결과 출력 실패');
                     }
                  })
               })
            
            });
         });
         </script>
         <div class="cal_diet">
      
               <div class="result">
                  
               </div>
                     
            <div class="w">
            
               <!-- 체중계 첫번쨰 -->
               <p>
                  <input type="text" name="goal_weight" class="goal_weight" />
                  <span id="w1">Kg</span>
               </p>
            
               <!-- 체중계 두번쨰 -->
               <div class="group">
                   
                   <ul>
					<li>
                        <input type="radio" name="gender" value="남" id="gender_m" > 
                        <label for="gender_m"><img src="resources/img/cal/man.png" id="man" /></label>
                        <input type="radio" name="gender"  value="여" id="gender_w" >
                        <label for="gender_w"><img src="resources/img/cal/woman.png" id="woman" /></label>
					</li>
					<li>
						 <p><strong>현재체중</strong><input type="text" name="now_weight" class="now_weight" /><span>kg</span></p>
                      	 <p><strong>목표날짜</strong><input type="text" name="goalDay" class="goalDay" /><span>일</span></p>
                     </li>
                  
                     <li>
                          <ol>
                             <li><input type="radio" class="per" name="select_per" id="select_per1" value="100" /><label for="select_per1">운동 100%</label></li>
                             <li><input type="radio" class="per" name="select_per" id="select_per2" value="80" /><label for="select_per2">운동 80%, 식사 20%</label></li>
                             <li><input type="radio" class="per" name="select_per" id="select_per3" value="60" /><label for="select_per3">운동 60%, 식사 40%</label></li>
                             <li><input type="radio" class="per" name="select_per" id="select_per4" value="50" /><label for="select_per4">운동 50%, 식사 50%</label></li>
                             <li><input type="radio" class="per" name="select_per" id="select_per5" value="40" /><label for="select_per5">운동 40%, 식사 60%</label></li>
                             <li><input type="radio" class="per" name="select_per" id="select_per6" value="20" /><label for="select_per6">운동 20%, 식사 80%</label></li>
                         </ol>
                     </li>
                     
                  </ul>
                   <button class="cal_diet_button">결과보기</button> 
               </div>
         </div> 
      
      </div>      
      <!-- 서브컨텐츠 영역 END -->
   
   </section>
   <!-- // container End -->
   
   <%@ include file="../inc/footer.jsp" %>