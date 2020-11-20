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
	var logchk = confirm('<%=msg%>'+'\n'+'기존 접속을 종료하시겠습니까?')
	if(logchk){
		location.href="/Logout.do";
	}else{
		location.href="<%=url%>";
	}
	</script>
</body>
</html>