<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<h4>베스트 게시글</h4>

	
				<style>
					.bigbox{width:80%;height:300px;border:1px solid grey}
					.bigbox > .bestofbest{width:50%;height:250px;margin-top:25px;padding:10px;border-right:1px solid grey;display:inline-block}
					.bigbox > .twotofive{display:inline-block;width:50%;height:250px;margin-top:25px;padding:10px;}
					.twotofive > ul{list-style:none}
				</style>
				<div class="bigbox" >
				<c:if test="${!empty bestlist }">
						<div class="bestofbest">
							<a href="sboardcont.brn?s_no=${bestlist[0].s_no}&state=cont">
								<strong>${bestlist[0].s_sj }</strong>${bestlist[0].s_dt }<br/>
								${bestlist[1].s_ct }
							</a>
						</div>
					<div class="twotofive">
						<ul>
							<c:forEach items="${bestlist }" var="best2" begin="2">
									<li><a href="sboardcont.brn?s_no=${best2.s_no}&state=cont">
										<div class="title">${best2.s_sj }</div><div class="date">${best2.s_dt }</div>	
											${best2.s_ct }
										</a>
									</li>	
							</c:forEach> 
						</ul>
					</div>
				</c:if>
				<c:if test="${empty bestlist }">
					베스트 댓글이 없습니다.
				</c:if>
				</div>
			
