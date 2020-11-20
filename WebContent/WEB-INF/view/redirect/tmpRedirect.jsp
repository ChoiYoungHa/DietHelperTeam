<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    String msg = (String)request.getAttribute("msg");
    String url = (String)request.getAttribute("url");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script type="text/javascript">
alert("<%=msg%>"+"\n"+"로그인 후 비밀번호를 변경해 주세요.");
location.href="<%=url%>";
</script>
</body>
</html>