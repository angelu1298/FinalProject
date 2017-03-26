<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<h4>베스트 게시글</h4>

	
<style>
				
.bigbox{ height:200px; padding-top:30px; font-size:13px; border-bottom:1px solid #a0a0a0;  border-top:1px solid #a0a0a0; }
.bigbox > .bestofbest{float:left; width:55%; margin-right:2%; padding:10px; display:inline-block}
.bestofbest a{ display:inline-block; width:100%; color:#787878;}
             
.bigbox > .twotofive{ float:left; display:inline-block; width:40%; margin-top:25px; }
.twotofive > ul{list-style:none; display:inline-block; width:100%; border-left:1px solid #a0a0a0; }
.twotofive > ul li{ position:relative; width:100%; height:20px; line-height:20px;  clear:both; display:block; margin:5px 15px 10px 15px; padding-right:100px; word-break:keep-all; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; color:#787887; }
.twotofive > ul li .date{ position:absolute; top:0; right:0; display:block; width:80px; height:20px; line-height:20px; font-size:11px;  }
.bestofbest a p {  width:100%; height:80px; display:inline-block; }
.bestofbest a p strong{ font-size:21px; clear:both; width:100%; display:block; margin-bottom:20px;     word-break:keep-all; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; color:#565656;  }
.bestofbest a p span{ float:left; margin-right:10px; }
.bestofbest a .removetag{    display:block; line-height:20px;  height:40px; overflow:hidden;  color:#787878}
.bestofbest a:hover p strong{ color:#232323;}
.bestofbest a:hover .removetag{ color:#232323;}
.twotofive > ul li:hover { color:#232323;}
</style>
				<div class="bigbox" >
				<c:if test="${!empty bestlist }">
						<div class="bestofbest">
							<a href="sboardcont.brn?s_no=${bestlist[0].s_no}&state=cont">
							<p>
								<strong>${bestlist[0].s_sj }</strong>
								<span class="date">${bestlist[0].s_dt }</span>
								<span class="writer">${bestlist[0].mem_id }</span>
							</p>
							<div class="removetag">${bestlist[0].s_ct }</div>
							</a>
						</div>
					<div class="twotofive">
						<ul>
							<c:forEach items="${bestlist }" var="best2" begin="2">
									<li><a href="sboardcont.brn?s_no=${best2.s_no}&state=cont">
										<div class="title">${best2.s_sj }</div><div class="date">${best2.s_dt }</div>${best2.mem_id }	
											<div class="removetag">${best2.s_ct }</div>
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
			
