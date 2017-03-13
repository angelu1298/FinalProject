<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자료실 답변</title>
<link rel="stylesheet" type="text/css" href="./resources/css/bbs.css" />
<link rel="stylesheet" type="text/css" href="./resources/css/admin.css" />
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/bbs.js"></script>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">자료실 답변</h2>
		<form method="post" action="gbbs_reply_ok.brn" onsubmit="return check()">
			<input type="hidden" name="gbbs_num" value="${gbbsbean.gbbs_num}" />
			<input type="hidden" name="gbbs_re_ref" value="${gbbsbean.gbbs_re_ref}" />
			<input type="hidden" name="gbbs_re_lev" value="${gbbsbean.gbbs_re_lev}" />
			<input type="hidden" name="gbbs_re_seq" value="${gbbsbean.gbbs_re_seq}" />
			<input type="hidden" name="page" value="${page}" />

			<table id="bbswrite_t">
				<tr>
					<th>글쓴이</th>
					<td><input type="text" name="gbbs_name" id="gbbs_name" size="14" class="input_box" /></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="gbbs_pass" id="bbs_pass" size="14" class="input_box" /></td>
				</tr>

				<tr>
					<th>글제목</th>
					<td><input type="text" name="gbbs_subject" id="gbbs_subject" size="40" class="input_box" value="Re:${gbbsbean.bbs_subject}" /></td>
				</tr>

				<tr>
					<th>글내용</th>
					<td><textarea name="gbbs_content" id="gbbs_content" rows="8" cols="50" class="input_box"></textarea></td>
				</tr>
			</table>

			<div id="bbswrite_menu">
				<input type="submit" value="답변" class="input_button" />
				<input type="reset" value="취소" class="input_button" onclick="$('#gbbs_name').focus();" />
			</div>
		</form>
	</div>
</body>
</html>