<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.burn.fat.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
	<div class="cal_content">
	
		<div class="cal_contentarea">
            <div class="group2">
            	<ul>
					<li>
						<input type="radio"  value="1" id="radioBtn1" name="erc_sx" > 
						<label for="radioBtn1"><img src="resources/img/cal/man.png" /></label>
						<input type="radio" value="2" id="radioBtn2"  name="erc_sx" class="ml10">
						<label for="radioBtn2"><img src="resources/img/cal/woman.png"/></label>
					</li>
					<li>
						 <p><strong>키</strong><input type="text" id="ki" /><span>cm</span></p>
                   		 <p><strong>몸무게</strong><input type="text" id="mugae" /><span>kg</span></p>
					</li>
				</ul>
			</div>
	
			<div class="bring_type"> 
				<p class="stip">
					본인의 신체정보를 입력한 후, 아래의 운동 종류를 선택해주세요
				</p>
				
				<p class="btn_list">
					<c:forEach var="exer" items="${exerciselist }">
						<input type="button" value="${exer.erc_ty}" id="erctyBtn_${exer.erc_ty}" class="erc_ty" >
					</c:forEach>
				</p>
					
				<div id="erc_nm_loc">
					<p class="nolist">현재, 선택된 내용이 없습니다.</p>
				</div>
			</div>
		</div>
	
		<script type="text/javascript">
		
			$(".erc_ty").click(function(){	
			
				var radioBtn1 = $("#radioBtn1");
				$(".erc_ty").removeClass("on");
				$(this).addClass("on");
				
				if($(":input:radio[name=erc_sx]:checked").length < 1){
					alert('성별을 선택해주세요!');						
					radioBtn1.focus();
					return false;
				}
				if($.trim($('#ki').val())==''){
					$('#ki').focus();
					alert("키를 입력해주세요!");
					return false;
				}
				if($.trim($('#mugae').val())==''){
					$('#mugae').focus();
					alert("몸무게를 입력해주세요!");
					return false;
				}
	
				var btnValue = $(this).val();
				
				var erc_sx = $(":input:radio[name=erc_sx]:checked").val();
				
				$.ajax({
					type : "post",
					data :  {
								"a" : btnValue,
								"erc_sx": erc_sx,
							},
					url : "bringname.brn",
					success : function(data) {
						$("#erc_nm_loc").html(data);
					}
				});
			}); 
		
		</script>
