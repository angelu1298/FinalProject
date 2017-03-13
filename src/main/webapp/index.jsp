<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	body{margin:0;padding:0;border:0}
	#mainframe{position:fixed;width:100%; height:100%;display:block;border:0}
	#talkframe{ position:fixed; bottom:0px; left:0; width:230px; height:380px;z-index:9999; display:block; background:none; border:none;}
</style>

<iframe src="./Main.brn" id="mainframe"></iframe>
<iframe src="http://127.0.0.1:3000" id="talkframe"></iframe>
