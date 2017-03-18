<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <style>
      .btn_close{padding: 10}
   </style>
   <script>
      $(function(){
         // 모달창 닫기
         $(".btn_close").click(function(){
             $(".modal_bg").addClass("off");
             $(".modal_bg").animate({"opacity":"0"});
          })
      })
   </script>
   <div class="modal_area alertpage">
      <h1>알려드립니다<span class="btn_close">x</span></h1>
      <div class="layer_content">
         <div class="mt30">
            <h5>결과</h5>
            <strong>${dietbean.reduceKg} kg</strong>을 
            <strong>${dietbean.goalDay}일</strong> 안에 감량하려면 ?   <br/>
		            매일 칼로리 섭취에서 <strong>${dietbean.reduceKcal}kcal</strong> 줄이기 <br/>
		            <br/>
		            운동 <strong>${dietbean.exec_percent}%</strong><strong class="ml10">식사 ${dietbean.meal_percent}%</strong> <br/>
		            <br/>운동 : <strong>- ${dietbean.reduceExec}kcal</strong> <br/>
		            식사 : <strong>- ${dietbean.reduceMeal}kcal</strong> <br/>
         </div>
          <p class="modal_btn_area">
            <input type="reset" value="확인" class="btn_close" />
         </p>
      </div> 
   </div>