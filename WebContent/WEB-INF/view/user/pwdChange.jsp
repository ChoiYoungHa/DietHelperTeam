<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
</head>
<body>
<form action="/pwdchgPost.do" method="post" id="PwdForm" onsubmit="return InsertCheck()">
<table border="1">
<tr>
	<tr>
		<td>현재비밀번호</td>
		<td><input type="password" name="originpwd" id="originpwd" onfocusout="PwdCheck()" placeholder="현비밀번호" style="width: 150px" />
		<div id="originpwd-insert">현재 비밀번호을 입력해주세요</div>
		<div id="originpwd-success">비밀번호가 일치합니다.</div>
		<div id="originpwd-danger">현재 비밀번호가 다름니다.</div></td>
	</tr>
	<tr>
		<td>변경할 비밀번호</td>
		<td><input type="password" name="password" id="password_1" placeholder="변경할 비밀번호" style="width: 150px" />
		<div id="alert-check">영문,숫자,특수문자를 포함한 12~20자로 설정해주세요.</div></td>
	</tr>
	<tr>
	<td>변경할 비밀번호 확인</td>
		<td><input type="password" name="password" id="password_2" placeholder="변경할 비밀번호 확인" style="width: 150px" />
			<div id="alert-insert">비밀번호 확인을 입력해주세요</div>
			<div id="alert-success">비밀번호가 일치합니다.</div>
			<div id="alert-danger">비밀번호가 일치하지 않습니다.</div>
		</td>
	</tr>
</table>
<input type="submit" value="비밀번호수정">
<input type="reset" value="다시 작성">
</form>
<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
<script type="text/javascript" src="/resources/js/pwdchange.js"></script>
</body>
</html>