<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="poly.dto.DietDTO"%>
<% DietDTO rDTO = (DietDTO) request.getAttribute("rDTO"); %>
<!DOCTYPE html>
<html>
<head>
<!------ jquery ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>MyMetabolism</title>
</head>
<body>
<p>설정된 기초 대사량
<table>
<tr>
	<td>나이
	<td><%=rDTO.getAge()%>
<tr>
	<td>성별
	<td><%=rDTO.getSex()%>
<tr>
	<td>키
	<td><%=rDTO.getTall()%>
<tr>
	<td>몸무게
	<td><%=rDTO.getWeight()%>
<tr>
	<td>기초대사량
	<td><%=rDTO.getBasic()%>
<tr>
	<td>유지칼로리
	<td><%=rDTO.getKeep_kcal()%>
<tr>
	<td>목표칼로리
	<td><%=rDTO.getGoal_kcal()%>
</table>
<button onclick="location.href ='/Metabolism.do'">설정</button>
<button onclick="history.back()">뒤로가기</button>
<button onclick="location.href ='/MyFoodList.do'">음식추천받기</button>
<!-- 데이터종료 시간 js 사용 -->
<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
</body>
</html>