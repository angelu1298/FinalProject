<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	body{margin:0;padding:0;border:0}
	#mainframe{position:fixed;width:100%; height:100%;display:block;border:0}
	#talkframe{ position:fixed; bottom:30px; left:0; width:230px; height:380px;z-index:99; display:none; background:none; border:none;}
	.macaotalkview{position:fixed;bottom:0px;background: url(resources/img/macaotalkbutton.png) no-repeat;width:96px;height:29px;border:none}
</style>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script>
$(function(){
	
	$('.macaotalkview').on('click',function(){
		$("#talkframe").toggle();
		
	})
})
</script>	
<iframe src="./Main.brn" id="mainframe"></iframe>
<iframe src="http://112.154.136.153:3000" id="talkframe"></iframe>
<input type="button" class="macaotalkview" />
