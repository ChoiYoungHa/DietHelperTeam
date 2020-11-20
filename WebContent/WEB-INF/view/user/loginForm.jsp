<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력</title>
</head>
<body>
<form action="/LoginPost.do" method="Post">
<table border="1">
<thead>
<tr>
<td>아이디 : </td>
<td><input type="email" name="email" width=100px;></td>
</tr>
<tr>
<td>비밀번호 : </td>
<td><input type="password" name="password" width=100px;></td>
</tr>
</thead>
<tbody>
</tbody>
</table>
<div style="width:100%;text-align:left;margin-top:5px;">
<input type="submit" value="로그인">
<button type=button onclick="location.href='/Login/UserRegForm.do'">회원가입</button></div>
</form>
</body>
</html>