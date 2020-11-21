<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>회원가입 화면</title>
</head>
<body>

	<h1>회원가입</h1>
	<hr/>
	<form method="post" action="/insertMember.do" onsubmit="return InsertCheck()">
		<table border="1">
			<col width="150px">
			<col width="150px">
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" id="email" onfocusout="EmailChk()" style="width: 150px" /></td>

			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="user_name" id="user_name"
					style="width: 150px" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" id="password_1" placeholder="비밀번호" style="width: 150px" />
				<div id="alert-check">영문,숫자,특수문자를 포함한 12~20자로 설정해주세요.</div></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="password" id="password_2" placeholder="비밀번호 확인" style="width: 150px" />
					<div id="alert-insert">비밀번호 확인을 입력해주세요</div>
					<div id="alert-success">비밀번호가 일치합니다.</div>
					<div id="alert-danger">비밀번호가 일치하지 않습니다.</div>
					</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="user_an" style="width: 150px" /></td>
			</tr>
		</table>
		<p>
			<input type="submit" value="회원가입">
			<input type="reset" value="다시 작성">
		</p>
	</form>
	<hr/>
<script type="text/javascript" src="/resources/js/UserRegForm.js"></script>
</body>
</html>