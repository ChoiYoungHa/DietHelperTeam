<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>DietHelper에 회원가입하세요.</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet" href="/resource/css/bootstrap.css">
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">

<!--  회원가입(=로그인폼 수정) css 사용 -->
<link rel="stylesheet" href="/resource/css/login.css">

<!-- DietHelper 홈페이지 아이콘 -->
<link href="/resource/assets/img/healthlogo.JPG" rel="icon">

<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
</head>

<!-- 회원가입 창 왼쪽 상단 DietHelper 로고 네비게이션 바 -->
  <body class="align">
	<!-- Navigation-->
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

<!-- 회원가입 화면 Diet Helper 글씨 -->
<div class="mx-auto text-center">
		<h1 class="mx-auto my-0 text-uppercase">
			<b>Diet</b>
			<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
		</h1>
		<br>
			<h1 class="mx-auto my-0 text-uppercase">
			<b>Helper</b>
			</h1>
        </div>
		<br>


<!-- 회원가입을 위한 회원정보 입력 폼(유효성 체크 자동) -->
	<div class="grid">

		<form action="/insertMember.do" method="POST" class="form login"
			onsubmit="return InsertCheck()">

			<div class="form__field">
				<label for="email"><svg class="icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use></svg><span class="hidden"></span></label> 
				<input id="email" type="email" name="email" class="form__input" placeholder="이메일을 입력하세요" style="padding: 0px 0px 0px 12px; color: black;" onfocusout="EmailChk()" autocomplete="off">
			</div>

			<div class="form__field">
				<label for="username"><svg class="icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use></svg><span class="hidden"></span></label> 
				<input id="name" type="text" name="user_name" size="6" class="form__input" placeholder="&nbsp;이름을 입력하세요" autocomplete="off">
			</div>

			<div class="form__field">
				<label for="login__password"><svg class="icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#lock"></use></svg><span class="hidden">Password</span></label> 
				<input id="password_1" type="password" name="password" size="15" class="form__input" placeholder="&nbsp; 영문, 숫자, 특수문자 포함 비밀번호" >
			</div>
			<div  class="alert alert-danger" id="alert-check">영문,숫자,특수문자를 포함한 12~20자로 설정해주세요.</div>

			<div class="form__field">
				<label for="login__password1"><svg class="icon"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#lock"></use></svg><span class="hidden">Password</span></label> 
				<input id="password_2"
					type="password" name="password2" size="15" class="form__input"
					placeholder="&nbsp;비밀번호 확인을 입력하세요">
			</div>

<!-- 비밀번호 입력 시 자동으로 비밀번호와 확인 부분이 일치하는지 alert -->
			<div class="alert alert-success" id="alert-success">비밀번호가
				일치합니다.</div>
			<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지
				않습니다.</div>

			<div class="form__field">
				<label for="login__password1"><svg class="icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#user"></use></svg><span class="hidden"></span></label> 
				<input id="login__password" type="text" name="user_an" class="form__input" placeholder="&nbsp;닉네임을 입력하세요" autocomplete="off">
			</div>
			
			<div class="form__field"> 
				 <input type="text" id="sample2_postcode" placeholder="우편번호" autocomplete="off">
				 <input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기" class="btn btn-primary disabled" required="required" style="width:118px;margin:0px 5px 0px 0px;">
			</div>
			
			<div class="form__field">
                 <input type="text" class="form-control form-control-user" name="addr" id="addr" placeholder="주소를 등록해 주세요." required="required" autocomplete="off" style="color:white;border-width: 0 0;">
            </div> 


<!-- 입력 완료 후 분홍색 회원가입 버튼을 눌러 유효성 체크에 이상 없을 시, 회원가입 -->
			<div class="form__field">
				<input type="submit" value="회원가입">
			</div>

		</form>

		<p class="text--center">
			&nbsp;&nbsp;이미 DietHelper 회원이신가요? <a href="/Login.do">&nbsp;로그인</a>
			<svg class="icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="assets/images/icons.svg#arrow-right"></use></svg>
		</p>
	</div>

<!-- 이메일, 비밀번호 입력할 때 입력란 옆에 사용되는 아이콘 -->
	  <svg xmlns="http://www.w3.org/2000/svg" class="icons"><symbol id="arrow-right" viewBox="0 0 1792 1792"><path d="M1600 960q0 54-37 91l-651 651q-39 37-91 37-51 0-90-37l-75-75q-38-38-38-91t38-91l293-293H245q-52 0-84.5-37.5T128 1024V896q0-53 32.5-90.5T245 768h704L656 474q-38-36-38-90t38-90l75-75q38-38 90-38 53 0 91 38l651 651q37 35 37 90z"/></symbol><symbol id="lock" viewBox="0 0 1792 1792"><path d="M640 768h512V576q0-106-75-181t-181-75-181 75-75 181v192zm832 96v576q0 40-28 68t-68 28H416q-40 0-68-28t-28-68V864q0-40 28-68t68-28h32V576q0-184 132-316t316-132 316 132 132 316v192h32q40 0 68 28t28 68z"/></symbol><symbol id="user" viewBox="0 0 1792 1792"><path d="M1600 1405q0 120-73 189.5t-194 69.5H459q-121 0-194-69.5T192 1405q0-53 3.5-103.5t14-109T236 1084t43-97.5 62-81 85.5-53.5T538 832q9 0 42 21.5t74.5 48 108 48T896 971t133.5-21.5 108-48 74.5-48 42-21.5q61 0 111.5 20t85.5 53.5 62 81 43 97.5 26.5 108.5 14 109 3.5 103.5zm-320-893q0 159-112.5 271.5T896 896 624.5 783.5 512 512t112.5-271.5T896 128t271.5 112.5T1280 512z"/></symbol></svg>

<!-- 회원가입 유효성 체크를 위한 UserRegForm js 사용 -->
	<script type="text/javascript" src="/resources/js/UserRegForm.js"></script>

<!-- 부트스트랩 js 사용 -->
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>

 <!--  다음 주소 API -->
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   
   <div id="layer"
      style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
     
      <img src="//t1.daumcdn.net/postcode/resource/images/close.png"
         id="btnCloseLayer"
         style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
         onclick="closeDaumPostcode()" alt="닫기 버튼">
         
   </div>
   
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script type="text/javascript" src="/resources/js/daumAPI.js"></script>

</body>
</html>