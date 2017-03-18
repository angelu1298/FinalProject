<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
    $(function(){
    	
   //---- 아코디언 ------------------------------------------------------------------------
        
      $('.tablelist ul li div').css({"display":"none"});
      $('.tablelist ul li p').click(function(){
         $('.tablelist ul li div').css({"display":"none"});
         $(this).next("div").css({"display":""});
      })
      
            var date = new Date();
           var year  = date.getFullYear();
           var month = date.getMonth() + 1; // 0부터 시작하므로 1더함 더함
           var day   = date.getDate();
           
           
           if (("" + month).length == 1) { 
              month = "0" + month; 
              }
           if (("" + day).length   == 1) {
              day   = "0" + day;  
              }
           
           var today = year + "년" + month + "월" + day;
           
           
           $('.today').append(today);
      		
           
          $('.result').click(function(){
        	  
          });
          
        
//----- 아침,점심,저녁 눌렀을 때 로드 --------------------------------------------------------------------         
          
          //아침,점심,저녁 로드 함수
          function load_data(b, wholeDay, brn, divclass) {
         	   
         	 var y = $('.year_'+b).val();
           	 var m = $('.month_'+b).val(); 
           	 var d = $('.day_'+b).val();
           	 $.ajax({
    				data : {
    					"y" : y,
    					"m" : m,
    					"d" : d,
    					"wholeDay" : wholeDay
    				
    				},
    				type : "post",
    				url : brn,
    				success : function(data) {
    					$(divclass).empty();
    					$(divclass).append(data);
    				},
    				error : function(data, status) {
//     					alert('아침 식품 출력 실패');
    				}
    			});
    			
           };
           
           
 //----- GROCERY --------------------------------------------------------------------
        // 식품버튼 클릭 함수
	       function click_gro(n,wholeDay) {
	    	   var y = $('.year_'+n).val();
	           var m = $('.month_'+n).val(); 
	           var d = $('.day_'+n).val();
	           
	           location.href="groceryList.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d;
		}
	     
	     //아침 식품버튼클릭
	       $('.maelist li .m_groclick').click(function(){
	    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
	         	click_gro(grade,'morning');
	         });  
	       
	    //점심 식품버튼클릭
	       $('.maelist li .l_groclick').click(function(){
	    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
	    	   click_gro(grade,'lunch');
	        });
	       
	    //저녁 식품버튼클릭
	       $('.maelist li .d_groclick').click(function(){
	    	    var grade = $(this).parents("li").attr("id").replace("wd_","");
	    	    click_gro(grade,'dinner');
	        }); 
	     
	    
        
//----- CUISINE --------------------------------------------------------------------
			
			// 음식버튼 클릭 함수
		       function click_cui(n,wholeDay) {
		    	   var y = $('.year_'+n).val();
		           var m = $('.month_'+n).val(); 
		           var d = $('.day_'+n).val();
		           location.href="cuisineList.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d;
			}
		     
		     //아침 음식버튼클릭
		       $('.maelist li .m_cuiclick').click(function(){
		    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
		         	click_cui(grade,'morning');
		         });  
		       
		    //점심 음식버튼클릭
		       $('.maelist li .l_cuiclick').click(function(){
		    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
		    	   click_cui(grade,'lunch');
		        });
		       
		    //저녁 음식버튼클릭
		       $('.maelist li .d_cuiclick').click(function(){
		    	   
		    	    var grade = $(this).parents("li").attr("id").replace("wd_","");
		       		click_cui(grade,'dinner');
		        }); 
           
		    
	          
//----- EXERCISE --------------------------------------------------------------------		    
       
    // 운동버튼 클릭 함수
    function click_exer(n,wholeDay) {
    	 var y = $('.year_'+n).val();
         var m = $('.month_'+n).val(); 
         var d = $('.day_'+n).val();
           
         location.href="cal_exer_start.brn?wholeDay="+wholeDay+"&y="+y+"&m="+m+"&d="+d;
		
	}
     
     //아침 운동버튼클릭
       $('.maelist li .m_exerclick').click(function(){
    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
         	click_exer(grade,'morning');
         });  
       
    //점심 운동버튼클릭
       $('.maelist li .l_exerclick').click(function(){
    	   var grade = $(this).parents("li").attr("id").replace("wd_","");
        	click_exer(grade,'lunch');
        });
       
    //저녁 운동버튼클릭
       $('.maelist li .d_exerclick').click(function(){
    	    var grade = $(this).parents("li").attr("id").replace("wd_","");
       		click_exer(grade,'dinner');
        }); 
    
//--- 아침,점심,저녁 로드 ---------------------------------------------------------------------------------
    
     //아침을 클릭했을 때
          $(".maelist li .mclick").click(function(){
        	  var grade = $(this).parents("li").attr("id").replace("wd_","");
         		
         		var wholeDay = 'morning';
		    	
         		/* 운동 로드 */
	     	    var brn1 = 'goweekly_m_click.brn';
	     	    var divclass1 = '.morning_result';
                load_data(grade, wholeDay, brn1, divclass1);
         		
				/* 음식 로드 */
				var brn2 = 'goweekly_m_food.brn';
		    	var divclass2 = '.morning_food';
		    	load_data(grade, wholeDay, brn2, divclass2);
         		
         		/* 식품 로드 */
        	    var brn3 = 'goweekly_m_gro.brn';
        	    var divclass3 = '.morning_grocery';
        	    load_data(grade, wholeDay, brn3, divclass3);
         		
          });
            
    //점심을 클릭했을 때
	     $(".maelist li .lclick").click(function(){
	    	 var grade = $(this).parents("li").attr("id").replace("wd_","");
	     	   var wholeDay = 'lunch';
      		
	     	   /* 운동 로드 */
	     	   var brn1 = 'goweekly_l_click.brn';
		       var divclass1 = '.lunch_result';
	           load_data(grade, wholeDay, brn1, divclass1);
         		
	     	  	/* 음식 로드 */
		        var brn2 = 'goweekly_l_food.brn';
		        var divclass2 = '.lunch_food';
		        load_data(grade, wholeDay, brn2, divclass2);
         		 
         		/* 식품 로드 */
                var brn3 = 'goweekly_l_gro.brn';
                var divclass3 = '.lunch_grocery';
                load_data(grade, wholeDay, brn3, divclass3);
                
	       });
     
    //저녁을 클릭했을 때
		$(".maelist li .dclick").click(function(){
			var grade = $(this).parents("li").attr("id").replace("wd_","");
      		
      			var wholeDay = 'dinner';
      		
      			/* 운동 로드 */
      			var brn1 = 'goweekly_d_click.brn';
      		    var divclass1 = '.dinner_result';
      		  	load_data(grade, wholeDay, brn1, divclass1);
      	          
      			/* 음식 로드 */
		    	var brn2 = 'goweekly_d_food.brn';
		    	var divclass2 = '.dinner_food';
		    	load_data(grade, wholeDay, brn2, divclass2);
      			
      			/* 식품 로드 */
      	        var brn3 = 'goweekly_d_gro.brn';
      	        var divclass3 = '.dinner_grocery';
      	      	load_data(grade, wholeDay, brn3, divclass3);
      	          
	       });	
    
    
    //평가-----------------------------------------------------------------------
    
    //우울이모티콘 = 1점
      $('.emo_tt').click(function(){
    	  emo_eval($(this));
      });
    //보통이모티콘 = 2점
      $('.emo_smile').click(function(){
    	  emo_eval($(this));
      });
    //윙크이모티콘 = 3점
      $('.emo_wink').click(function(){
    	  emo_eval($(this));
      });
    
    //평가
    	
          
   });
    function emo_eval(emo_name){
  	  
   	var grade = $(emo_name).parents("li").attr("id").replace("wd_","");
    	 
   	 var y = $('.year_'+grade).val();
   	 var m = $('.month_'+grade).val(); 
   	 var d = $('.day_'+grade).val();
   	 
   	 var emo = $(emo_name).val();

   	 $.ajax({
   		 data : {
   	  		"y" : y,
   	  		"m" : m,
   	  		"d" : d,
   	  		"emo_eval" : emo		
   	  		},
   	  		type : "post",
   	  		url : "cal_eval.brn",
   	  		success : function(data) {
   	  			if(data=='1')
					alert("평가 등록 성공!");  
				else
					alert('이미 평가 하셨습니다.');
   	  		},
   	  		error : function(data, status) {
   	  			alert('평가 등록 실패'+data+status);
   	  		}
   	  });
   	  			
      }; 

</script>

<form>
	<input type="hidden" value="${y}" id="y"/>
	<input type="hidden" value="${m}" id="m"/>
	<input type="hidden" value="${d}" id="d"/>
	<input type="hidden" value="${sessionScope.mem_no}" id="mem_no"/> 

   <p class="date_area">
      <!--가운데 정렬 해야해 -->
   <a href="./week_minus.brn?y=${y}&m=${m}&d=${d}">이전주</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
   <strong id="addju">${y}년 ${m}월 ${d}일 </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
   <a href="./week_plus.brn?y=${y}&m=${m}&d=${d}">다음주</a>

   <p class="goal_area">
      <strong>목표</strong> 
   </p>

   <div class="tablelist weekdiary">
      <!-- 테이블 헤더 -->
      <p>
         <!-- 날짜 표시   "w_0"=일 "w_1"=월.... -->
         <span id="w_0"></span> <span id="w_1"></span> <span id="w_2"></span>
         <span id="w_3"></span> <span id="w_4"></span> <span id="w_5"></span>
         <span id="w_6"></span>
      </p>
  <!-- 본문내용 -->
  <ul class="maelist">
      
<c:forEach var="k" begin="0" end="6" step="1" >
<li id="wd_${k}">
<!-- DB에 값이 있을경우 -->
	<c:if test="${!empty calendarList}">
               <p class="mclick morning_click${k}">아침</p>
               <div class="acco">
	               <dl>
	                  <dt><button class="m_exerclick" id="exer_${k}" style="color:red">운동</button></dt>
	                  <dd class="morning_result" >
	                  <!-- 운동칼로리 -->
	                  </dd>
	               </dl>
	               <dl class="list">
	                  <dt><button class="m_cuiclick" id="cui_${k}" style="color:red">음식목록</button></dt>
	                 <dd class="morning_food">
	                 <!-- 음식목록 -->
	                 </dd>
	                  <dt><button class="m_groclick" id="gro_${k}" style="color:red">식품목록</button></dt>
	                  <dd class="morning_grocery">
	                 <!-- 식품목록 -->
	                  </dd>
	               </dl> 
	               <input type="hidden" class="year_${k}"/>
	               <input type="hidden" class="month_${k}"/>
	               <input type="hidden" class="day_${k}" />
            	</div>
            
	            <p class="lclick lunch_click${k}">점심</p>
	            <div class="acco">
	               <dl>
					<dt><button class="l_exerclick" id="exer_${k}" style="color:red">운동</button></dt>
	                  <dd class="lunch_result">
	                  </dd>
	               </dl>
	               <dl class="list">
	                  <dt><button class="l_cuiclick" id="cui_${k}" style="color:red">음식목록</button></dt>
	                  <dd class="lunch_food">
	                  </dd>
	                   
	                  <dt><button class="l_groclick" id="gro_${k}" style="color:red">식품목록</button></dt>
	                  <dd class="lunch_grocery">
	                  </dd>
	           	   
	               </dl> 
	               	<input type="hidden" class="year_${k}"/>
	            	<input type="hidden" class="month_${k}"/>
	            	<input type="hidden" class="day_${k}" />
	            </div>
	            
	            <p class="dclick dinner_click${k}">저녁</p>
	            <div class="acco">
	            <dl>
	               	<dt><button class="d_exerclick" id="exer_${k}" style="color:red">운동</button></dt>
	                  <dd class="dinner_result">
	                  </dd>
	            </dl>
	            <dl class="list">
	               <dt><button class="d_cuiclick" id="cui_${k}" style="color:red">음식목록</button></dt>
	               <dd class="dinner_food">
	               </dd>
	               
	               <dt><button class="d_groclick" id="gro_${k}" style="color:red">식품목록</button></dt>
	               <dd class="dinner_grocery">
	               </dd>
	           </dl> 
	            <input type="hidden" class="year_${k}"/>
	            <input type="hidden" class="month_${k}"/>
	            <input type="hidden" class="day_${k}" />
        	</div>
        	

            <input type="button" class="saveDB emo_tt" value='1' >
            <input type="button" class="saveDB emo_smile" value='2'>
            <input type="button" class="saveDB emo_wink" value='3'>
         	
	</c:if>

<!-- DB에 값이 없을 경우 -->
<c:if test="${empty calendarList}">
   <p>아침</p>
               <div class="acco">
               <dl>
                	<dt><button class="m_exerclick" id="exer_${k}">운동</button></dt>
               </dl>
               <dl class="list">
                  <dt><button class="m_cuiclick" id="cui_${k}">음식목록</button></dt>
               
               	  <dt><button class="m_groclick" id="gro_${k}">식품목록</button></dt>
               </dl> 
               	<input type="hidden" class="year_${k}"/>
            	<input type="hidden" class="month_${k}"/>
            	<input type="hidden" class="day_${k}" />
            </div>
            
            <p>점심</p>
            <div class="acco">
               <dl>
					<dt><button class="l_exerclick" id="exer_${k}">운동</button></dt>
               </dl>
               <dl class="list">
                  <dt><button class="l_cuiclick" id="cui_${k}">음식목록</button></dt>
               
               	  <dt><button class="l_groclick" id="gro_${k}">식품목록</button></dt>
               </dl> 
               	<input type="hidden" class="year_${k}"/>
            	<input type="hidden" class="month_${k}"/>
           		<input type="hidden" class="day_${k}" />
            </div>
            <p>저녁</p>
            <div class="acco">
            <dl>
            	<dt><button class="d_exerclick" id="exer_${k}">운동</button></dt>
            </dl>
            <dl class="list">
               <dt><button class="d_cuiclick" id="cui_${k}">음식목록</button></dt>
               
               <dt><button class="d_groclick" id="gro_${k}">식품목록</button></dt>
            </dl> 
             <input type="hidden" class="year_${k}"/>
             <input type="hidden" class="month_${k}"/>
             <input type="hidden" class="day_${k}" />
            </div>
</c:if> 
</li> 
 </c:forEach>        
<!-- 오늘기준 미래 시점은 데이터를 표시하지 않음. -->

 </ul><!-- end -->
      
 </div>
</form>



<script>
   $(document).ready(function() {

      var today = gettoday();

      if ("${m}" < 10) {
         var m = "0" + "${m}";

         if ("${d}" < 10) {
            var d = "0" + "${d}";
            var total = "${y}" + m + d;
            haha(total, today);
         }

         else if ("${d}" >= 10) {
            var total = "${y}" + m + "${d}";
            haha(total, today);

         }

      } else if ("${m}" >= 10) {
         if ("${d}" < 10) {
            var d = "0" + "${d}";
            var total = "${y}" + "${m}" + d;
            haha(total, today);
         }

         else if ("${d}" >= 10) {

            var total = "${y}" + "${m}" + "${d}";
            haha(total, today);

         }
      }
      $(".no").html("<p class=\"nodata\">해당 날짜의 데이터는 아직 입력할 수 없습니다.</p>");

   });

   function haha(total, today) {

      var intDayCnt1 = 0;
      var intDayCnt2 = 0;

      var total = String(total);

      var year = total.substring(0, 4);
      var month = total.substring(4, 6);
      var day = total.substring(6, 8);

      var week = new Array("일", "월", "화", "수", "목", "금", "토");

      var arrday = new Date(year, month - 1, day);
      var i = arrday.getDay(); //0:일요일, 1:월요일, 2:화요일, 3:수요일, 4:목요일, 5:금요일, 6:토요일 

      intDayCnt1 = 1 - i;
      intDayCnt2 = 7 - i;
      for (var a = 0; a < 7; a++) {

         //기준일의 주의 월요일의 날짜와 토요일의 날짜
         var Cal_st = new Date(arrday.getFullYear(), arrday.getMonth(),
               ((arrday.getDate() - 1) + a) + intDayCnt1);

         
         var st_day = (Cal_st.getFullYear()) + "년 "
               + (Cal_st.getMonth() + 1) + "월 " + (Cal_st.getDate())
               + "일 " + week[(Cal_st.getDay())] + "요일";

         if (("" + (Cal_st.getMonth() + 1)).length == 1) {
            var Mon = "0" + (Cal_st.getMonth() + 1);
         } else
            var Mon = (Cal_st.getMonth() + 1);

         if (("" + (Cal_st.getDate())).length == 1) {
            var Day = "0" + (Cal_st.getDate());
         } else
            var Day = (Cal_st.getDate());

         var prepto = String((Cal_st.getFullYear())) + Mon + Day;
		          
         
         ////////////////////////////////////////////////////
         var year = '.year_' + a;
         $(year).attr("value", String((Cal_st.getFullYear())));
         
         var month = '.month_' + a;
         $(month).attr("value",Mon);
        
         var day = '.day_' + a;
         $(day).attr("value",Day);
         
         ////////////////////////////////////////////////////
         var w = '#w_' + a;
         $(w).append(st_day);
         

         if (today < prepto) {

            var wd = '#wd_' + a;
            $(wd).addClass("no");
         }

      }

      var ju = getWeekNo(arrday);

      $("#addju").append('<span>' + ju + '째 주</span>');

   };

   function getWeekNo(date) {

      var day = date.getDate()

      day +=  7 - date.getDay() + 1;
      
      
      return Math.ceil(parseFloat(day) / 7);
   }
   
   function gettoday() {

      var date = new Date();
      var ty = date.getFullYear();
      var tm = date.getMonth() + 1;
      var td = date.getDate();

      if (("" + tm).length == 1) {
         tm = "0" + tm;
      }
      if (("" + ty).length == 1) {
         ty = "0" + ty;
      }

      var today = ty + tm + td;

      return today;

   }
</script>