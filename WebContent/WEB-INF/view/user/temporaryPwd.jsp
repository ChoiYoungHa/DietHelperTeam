<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<form action="/Temporarypwd/tmppwd.do" method="post" onsubmit="return InsertCheck()">
<table>
<tr>
<td><input type="text" id="email" name="email">
<tr>
<td><input type="text" id="user_name" name="user_name">
<div id="alert-emailnmchk" style="display:none">이메일 혹은 성명이 일치하지 않습니다.</div>
</table>
<input type="submit">
<input type="reset">
</form>
<!-- 임시 비밀번호 발급 JS -->
<script type="text/javascript" src="/resources/js/Temporarypwd.js"></script>
</body>
</html>