<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="poly.dto.DietDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.EncryptUtil"%>
    <%   List<DietDTO> rList = (List<DietDTO>) request.getAttribute("rList"); 
    if(rList==null){
    	rList = new ArrayList<DietDTO>();
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!-- 메인 로고 노란색 아이콘(DietHelper) -->
<link href="/resource/assets/img/healthlogo.JPG" rel="icon">

<!-- 상단 DietHelper 로고 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- 회원관리 페이지에 사용되는 폰트 -->
<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css' rel='stylesheet' type='text/css'>
	
<!-- Vendor CSS Files -->
<link href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css' rel='stylesheet' type='text/css'>
<link href="/resource/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/resource/assets/vendor/icofont/icofont.min.css"
	rel="stylesheet">
<link href="/resource/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="/resource/assets/vendor/venobox/venobox.css"
	rel="stylesheet">
<link
	href="/resource/assets/vendor/owl.carousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="/resource/assets/vendor/aos/aos.css" rel="stylesheet">

<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet" href="/resource/css/bootstrap.css">
<link rel="stylesheet" href="/resource/css/style.css">

<title>관리자 페이지</title>
<Style>
.col-lg-2{
color:lightgray;
font-weight:bold;
}
.col-lg-3{
color:lightgray;
font-weight:bold;
}
.col-lg-4{
color:lightgray;
font-weight:bold;
}
</Style>
</head>
<body>
<!-- 우측 상단 DietHelper 로고 -->
      <nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Logout.do" style="color:#ffc838;font-family:Hanna; letter-spacing:3px;"><b>
						로그아웃</b></a></li>
				</ul>
			</div>
		</div>
	</nav>

<!--  관리자 회원관리 목록 표시 구간 -->
<div>
<section id="hero" class="d-flex flex-column justify-content-center align-items-center">
<div class="mx-auto text-center">		
					<div class="mx-auto text-center">
					<h1 class="mx-auto my-0 text-uppercase" style="color: lightgray;font-family:Nanum Gothic;">
						<b><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>&nbsp;
							회원 관리</b>
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
						<br>
					</h1>
				</div>
			</div>
			<br>

<div class="container">
<div class="container-fluid" style="border:1px solid yellow;">
<div class="row" style="color:yellow;font-size:2em;">
<div class="col-lg-2 text-center" style="color:yellow;"><b>회원 번호</b></div>
<div class="col-lg-2 text-center" style="color:yellow;"><b>회원 이름</b></div>
<div class="col-lg-4 text-center" style="color:yellow;"><b>회원 아이디</b></div>
<div class="col-lg-2 text-center" style="color:yellow;"><b>가입일</b></div>
<div class="col-lg-2 text-center" style="color:yellow;"><b>회원 삭제</b></div>
<hr style="background-color:yellow;">
</div>
<%int i = 1;
for(DietDTO rDTO : rList){%>
	<br>
	<div class="row">
	<div class="col-lg-2 text-center"><%=rDTO.getUser_no() %></div>
	<div class="col-lg-2 text-center"><%=rDTO.getUser_name() %> </div>
	<div class="col-lg-4 text-center"><%=EncryptUtil.decAES128CBC(rDTO.getEmail())%></div>
	<div class="col-lg-2 text-center"><% String[] Reg_dtarry = rDTO.getReg_dt().split(" ", 2); out.print(Reg_dtarry[0]); %></div>
	<div class="col-lg-2 mb-3 text-center"><button type="button" class="btn btn-danger" id="user_no" value="<%=rDTO.getUser_no() %>" onclick="userdelete();">삭제</button></div>
	</div>
<%i++;}%>
</div>
</div>
<script type="text/javascript">
function userdelete() {
	console.log("vlaue :"+$("#user_no").val())
	$.ajax({
		url : "/userdelete.do",
		type : "post",
		dataType : "json",
		data : {
			"user_no" : $("#user_no").val()
		},
		success : function(data) {
			if (data > 0) {
				alert("삭제에 성공했습니다.");
				window.location.reload()
				return false;
			}else {
				alert("삭제에 실패했습니다.");
				window.location.reload()
				return true;
			}
		}
	})
};</script>
<!-- 부트스트랩 js 사용 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resource/js/main.js"></script>

<!-- timecheck js 사용 -->
	<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
	
	
</section>
</div>
<!-- 관리자 페이지 구간 종료 -->
</body>
</html>