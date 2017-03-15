<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <html>
    <head>
<script>
	$(function(){
		$('#search').click(function(){
			
		})
		$("#end").click(function(){
			var post = $("#post").val();
			$(window.opener.document).find("#zipcode").val(post);
			window.self.close();
		})
	})
</script>
</head>
<body>
	지번/도로명 검색 : <input type='text' size='45'/>
	<button id="search">검색</button><br/>
	정확한 주소를 모르시는 경우 시/군/구 + 도로명, 동명 또는 건물명<br/>
	예) 동해시 중앙로, 여수 중앙동, 대전 현대아파트<br/>
	정확한 주소를 아시는 경우 도로명 + 건물번호 <br/>
	예) 도움5로 19읍/면/동/리 + 지번 예) 어진동 307-19 <br/>
	선택한 주소의 우편번호<input type="text"  size="5" id="post"/>
	<input type="button" id="end" value="확인"/>
 	
</body>
</html>