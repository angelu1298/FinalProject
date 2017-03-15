<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="./resources/js/jquery.min.js"></script>
<script>
 	 $(document).ready(function(){
		$(".ecommRe").hide();
		$(".ecommRebut").click(function(){
			$(".ecommRe").hide();// show되어있는 같은 클래스를 갖고잇는 다른 것들을 닫아줌
			$(this).parents("tr").next(".ecommRe").show();
		});
	});  
</script>
<!-- 답글달기 -->
  
  <style>
  	.comment {}
  	.comment td{ padding:10px;border:1px solid #a0a0a0; }
  	.comment td input[type="text"]{padding:0 5px; width:200px; border:1px solid #a0a0a0; height:20px; line-height:20px; }
  	.comment td input[type="button"],
  	.comment td input[type="submit"]{padding:0 10px;border:1px solid #a0a0a0; height:20px; line-height:20px; }
  </style>
<table class="comment">
	<thead>
		<tr>
			<th><h4>코멘트 남기기</h4></th>
		</tr>
	</thead>
	<tbody>
		<!--등록된 코멘트가 없는경우-->
		<c:if test="${empty ecommlist }">
			<tr>
				<td class="nolist" colspan="6"><b>코멘트가 없습니다.</b></td>
			</tr>
		</c:if>
		<!--등록된 코멘트가 있는 경우-->
		<c:if test="${!empty ecommlist}"> 
			<c:set var="numm" value="${elistcount}" />
			<c:forEach var="co" items="${ecommlist}">
			<form method="post" action="ebo_reply.brn">
<!-- 			<input type="hidden" class="ecomm_no" name="ecomm_no" -->
<%-- 					value="${co.ecomm_no }" /> --%>
			<tr>
				
<%--  						아이디:${getcId} &nbsp; --%>
				<td>아이디:${getcommId}&nbsp;&nbsp;&nbsp;</td>
				<c:if test="${co.ecomm_re_lev == 0}">
					<td>내용:${co.ecomm_ct}</td>
				</c:if>
				<p>
					<!-- 답글인 경우 -->
					<c:if test="${co.ecomm_re_lev != 0}">
					<td>
						<c:forEach var="k" begin="1" end="${co.ecomm_re_lev}">
								→→→		
						</c:forEach>
						${co.ecomm_rct}&nbsp;
					</td>
					</c:if>	
				</p>
				<td>날짜:${co.ecomm_dt}</td>
					
				<td><input type="button" value="답글" class="ecommRebut" id="ecommRebut_${numm}"/></td>
<!-- 				<td><input type="button" value="답글" class="ecommRebut"/></td> -->
<!-- 				<td><input type="text" value="답글" name="ecommRe_ct" class="ecommRe"/></td> -->
					<!-- 답글인 경우 -->
				<td><a href="ebo_reply_del.brn?ecomm_no=${co.ecomm_no}&e_no=${co.e_no}&state=cont">삭제</a></td>
			</tr>
			<tr class="ecommRe">
				<td colspan="3"><input type="text" value="답글" name="ecommRe_ct"  id="ecommRe_${numm}"/></td>
				<td>
					<p>
							  <input type="hidden" name="e_no" value="${co.e_no}" />
							  <input type="hidden" name="ecomm_no" value="${co.ecomm_no}" />
							  
							  <input type="hidden" name="ecomm_re_ref" value="${co.ecomm_re_ref}" />
							  <input type="hidden" name="ecomm_re_lev" value="${co.ecomm_re_lev}" />
							  <input type="hidden" name="ecomm_re_seq" value="${co.ecomm_re_seq}" />
					<input type="submit" value="답글달기" />
<%-- 					<c:if test="${sessionScope.id == getcommId }"> --%>
<!-- 						<span class="deletecomm">[삭제]</span> -->
<%-- 					</c:if> --%>
					</p>
				</td>
			   
			
			</tr>
			</form>
			<c:set var="num" value="${numm-1}" />
			</c:forEach>
		</c:if>
	</tbody>
	
</table> 
<!--  -->
<form method="post" action="ebo_comm.brn">
<table>
		<tr>
			<td>
				<input type="hidden" value="${ebobean.e_no}" name="e_no"/>
				<input type="text" id="ecomm" name="ecomm"/>
			</td>
			<td>
				<input type="submit" value="코멘트" id="ebutton"/>
			</td>
		</tr>
</table>
</form>
			
				<%-- <p>
					<c:if test="${sessionScope.id == getcommId }">
						<span class="deletecomm">[삭제]</span>
					</c:if>
					<c:if test="${sessionScope.id != getcommId }">
						<span></span>
					</c:if>
					<span class="reply">[답글]</span>
				</p> --%>
			