
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/logHeader.jsp"%>

<!-- container Start : 헤더와 푸터를 제외한 실제 영역-->
<section class="log_container">

		<!-- 서브메뉴에 따라 Side Navigation을 다르게 부여해준다. -->	
		<%@ include file="/jsp/inc/leftMenu_log.jsp" %>

		<!-- 서브컨텐츠 영역 START -->
		<div class="subContent">
			  <h4>문의메일 보내기</h4>
			  <form action="inquiry_ok.brn" method="post">   
			  				
						<div class="common_write_box">
							<table>
							<colgroup>
							<col width="148px">
							<col width="100%"> 
							</colgroup>
							<tbody>
							
							<tr>
								<th>메일</th>
								<td>
									<input type="text" id="mail" name="mail" size="120" style="width:100%" placeholder="메일을 입력해주세요" class="form-control" >
						      </td>
							</tr>
							<tr>
								<th>제목</th>
								<td>
									<input type="text" id="title" name="title" size="120" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea id="content" name="content" cols="120" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
			   					</td>
							</tr>
						</tbody>
					</table>
					</div>
					
					<div class="btnL_area mt25">
							<input type="reset" value="다시 작성하기">
							<input type="submit" value="확인">
					</div>
			  </form>
		</div>
		
</section>
<!-- // container End -->
	
<%@ include file="/jsp/inc/footer.jsp" %>

