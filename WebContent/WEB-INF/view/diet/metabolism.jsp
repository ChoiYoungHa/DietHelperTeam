<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>기초대사량 구하기</title>
</head>
<body>

	<h1>기초대사량 계산기</h1>
	<br />
	<hr/>
	<form id="formvalue" method="post" action="/MetabolismPost.do" onsubmit="return InsertCheck()">
			<table border="1">
			<tr>
				<td>나이를 입력하세요 : </td>
				<td><input type="text" name="age" id="age" style="width: 100px" /> 세</td>
			</tr>
			<tr>
				<td>성별</td>
				<td><label><input type="radio" name="sex" id="sex" value="1" />남자</label>
					<label><input type="radio" name="sex" value="2" />여자</label></td>
			</tr>
			<tr>
				<td>키를 입력하세요 : </td>
				<td><input type="text" name="tall" id="tall" style="width: 100px" /> cm</td>
			</tr>
			<tr>
				<td>몸무게를 입력하세요 : </td>
				<td><input type="text" name="weight" id="weight" style="width: 100px"/> kg</td>
			</tr>
			<tr>
				<td>나의 활동량</td>
					<tr><td><label><input type="radio" name="activity" value="1.2">거의 없다.(거의 좌식 생활하고, 운동 안 함)</label></td></tr>
					<tr><td><label><input type="radio" name="activity" value="1.375">조금 있다.(활동량이 보통이거나 주에 1-3회 운동)</label></td></tr>
					<tr><td><label><input type="radio" name="activity" value="1.55">보통(활동량이 다소 많거나 주에 3-5회 운동)</label></td></tr>
					<tr><td><label><input type="radio" name="activity" value="1.725">꽤 있다.(활동량이 많거나 주에 6-7회 정도 운동)</label></td></tr>
					<tr><td><label><input type="radio" name="activity" value="1.9">아주 많다.(활동량이 매우 많거나, 거의 매일 하루 2번 운동)</label></td></tr>
			<tr>
				<td>운동 목적</td>
				<td>
					<label><input type="radio" name="how_exer" style="width: 50px" value="1"/>체중 감량</label>
					<label><input type="radio" name="how_exer" style="width: 50px" value="2"/>체중 증량</label> 
					<label><input type="radio" name="how_exer" style="width: 50px" value="3"/>체중 유지</label>
				</td>
			</tr>
			
			</table>
	<br/>
			<input type="hidden" name="basic" id="basic" value="1"  />
			<input type="hidden" name="keep_kcal" id="keep_kcal" value="1"/>
			<input type="hidden" name="goal_kcal" id="goal_kcal" value="1"/>
		<p>
			<input type="submit" value="입력">
			<input type="button" value="비밀번호 수정" onclick="location.href='/pwdchange.do'">
			<input type="reset" value="다시 작성">
			<button onclick="history.back()">뒤로가기</button>
		</p>
		</form>
<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
<script type="text/javascript" src="/resources/js/Metabolism.js"></script>
</body>
</html>