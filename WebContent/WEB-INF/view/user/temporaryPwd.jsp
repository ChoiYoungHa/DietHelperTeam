<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet" href="/resource/css/bootstrap.css">
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">

<!--  비밀번호 찾기- css 사용 -->
<link rel="stylesheet" href="/resource/css/login.css">

<!-- DietHelper 아이콘 -->
<link href="/resource/assets/img/healthlogo.JPG" rel="icon">
<link href="/resource/assets/img/healthlogo.JPG" rel="apple-touch-icon">

<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!------ 폰트 ---------->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
</head>

<!-- 왼쪽 상단 DietHelper 로고 내비게이션 -->
<body class="align">

	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="/index.do"
				style="color: #778899;">
				<b>Diet 
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
				Helper
			</b></a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Login.do" style="color: #778899;">Log In</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Login/UserRegForm.do" style="color: #778899;">Sign Up</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 비밀번호 찾기 문구 입력 구간 -->
	<div class="mx-auto text-center">
		<h1 class="mx-auto my-0 text-uppercase">
			<b>Find</b>
			<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
			</h1>
			<br>
		<h1 class="mx-auto my- text-uppercase">
			<b>PassWord</b>
		</h1>
		</div>
		<br>

<!-- 임시 비밀번호 발급을 받아 비밀번호를 찾기 위한 정보 입력 폼 -->
	<div class="grid">

		<form action="/Temporarypwd/tmppwd.do" method="POST"
			class="form login" onsubmit="return InsertCheck()">

<!-- 비밀번호를 찾기 위한 사용자 email 입력 -->
			<div class="form__field">
				<label for="email"><svg class="icon">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use></svg><span
					class="hidden"></span></label> 
				<input id="email" type="email"
					name="email" class="form__input" placeholder="&nbsp;이메일을 입력하세요"
					style="color: black; padding: 0px 0px 0px 12px" required>
			</div>
			
<!-- 비밀번호를 찾기 위한 사용자 이름 입력 -->
			<div class="form__field">
				<label for="email"><svg class="icon">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use></svg><span
					class="hidden"></span></label> 
				<input id="user_name" type="text"
					name="user_name" class="form__input" placeholder="&nbsp;이름을 입력하세요"
					style="color: black; padding: 0px 0px 0px 12px" required>
			</div>
			<div id="alert-emailnmchk" style="display:none">이메일 혹은 성명이 일치하지 않습니다.</div>
			
<!-- 입력 완료 후 제출 혹은 다시 작성 -->
				<div class="row">
				<div class="col-6">
					<input type="submit" 
						value="입력 완료" style="width:160px; height: 52px; margin:0px 0px 0px 14px;">
				</div>
				<div class="col-6">
					<input type="reset" class="btn btn-info" value="다시 작성"
						style="width:145px; height: 52px; margin:0px 0px 0px 0px;">
				</div>
				</div>
		</form>

		<p class="text--center">
			아직 DietHelper 회원이 아니신가요? <a href="/Login/UserRegForm.do">&nbsp;회원가입</a>
			<svg class="icon">
				<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="/assets/images/icons.svg#arrow-right"></use></svg>
			<br class="text--center">로그인을 원하시나요? <a
				href="/Login.do">&nbsp;로그인</a>
			<svg class="icon">
				<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="/assets/images/icons.svg#arrow-right"></use></svg>
		</p>
	</div>

<!-- 이메일, 이름 입력 구간의 사람 표시 아이콘 -->
	<svg xmlns="http://www.w3.org/2000/svg" class="icons"><symbol id="arrow-right" viewBox="0 0 1792 1792"><path d="M1600 960q0 54-37 91l-651 651q-39 37-91 37-51 0-90-37l-75-75q-38-38-38-91t38-91l293-293H245q-52 0-84.5-37.5T128 1024V896q0-53 32.5-90.5T245 768h704L656 474q-38-36-38-90t38-90l75-75q38-38 90-38 53 0 91 38l651 651q37 35 37 90z" /></symbol><symbol id="lock" viewBox="0 0 1792 1792"><path d="M640 768h512V576q0-106-75-181t-181-75-181 75-75 181v192zm832 96v576q0 40-28 68t-68 28H416q-40 0-68-28t-28-68V864q0-40 28-68t68-28h32V576q0-184 132-316t316-132 316 132 132 316v192h32q40 0 68 28t28 68z" /></symbol><symbol id="user" viewBox="0 0 1792 1792"><path d="M1600 1405q0 120-73 189.5t-194 69.5H459q-121 0-194-69.5T192 1405q0-53 3.5-103.5t14-109T236 1084t43-97.5 62-81 85.5-53.5T538 832q9 0 42 21.5t74.5 48 108 48T896 971t133.5-21.5 108-48 74.5-48 42-21.5q61 0 111.5 20t85.5 53.5 62 81 43 97.5 26.5 108.5 14 109 3.5 103.5zm-320-893q0 159-112.5 271.5T896 896 624.5 783.5 512 512t112.5-271.5T896 128t271.5 112.5T1280 512z" /></symbol></svg>

<!-- 임시 비밀번호 발급 JS -->
<script type="text/javascript" src="/resources/js/Temporarypwd.js"></script>

<!-- 부트스트랩 js 사용 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</body>
</html>