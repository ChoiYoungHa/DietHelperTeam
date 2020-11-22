<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>DietHelper</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />

<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!-- 상단 메뉴바 폰트 링크 -->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>

<!-- 구글 폰트-->
        <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
    	  
<!-- 부트스트랩 css 사용 -->
		<link rel="stylesheet" href="/resource/css/bootstrap.css">
		<link rel="stylesheet" href="/resource/css/styles.css">
		
  <link href="/resource/assets/img/healthlogo.JPG" rel="icon">
  <link href="/resource/assets/img/healthlogo.JPG" rel="apple-touch-icon">
  </head>
  
  
    <body id="page-top">
<!-- 상단 DietHelper 로고와 로그인, 회원가입 네비게이션 표시 -->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="#page-top"><b>Diet 
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
                Helper</b></a>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="/Login.do">Log In</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="/Login/UserRegForm.do">Sign Up</a></li>
                    </ul>
                </div>
            </div>
        </nav>

<!-- Index 페이지 배경화면과 함께, 로그인하고 사이트 이용할 것 표시 -->
        <header class="masthead">
            <div class="container d-flex h-100 align-items-center">
                <div class="mx-auto text-center">
                    <h1 class="mx-auto my-0 text-uppercase" ><b>Diet<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg><p>Helper</p></b></h1>
                    <h2 class="text-white-50 mx-auto mt-2 mb-5">개인 식단 맞춤 추천 서비스</h2>
                   <a class="btn btn-primary js-scroll-trigger" href="/Login.do">로그인하고 DietHelper를 시작하세요!</a>
                </div>
            </div>
        </header>
        <!-- About-->
        <section class="about-section text-center" id="about">
               
        </section>

<!-- 회원가입하고 DietHelper를 이용할 것을 알림, Sign Up 버튼을 누르면 회원가입 페이지로 이동 -->
        <section class="signup-section" id="signup">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-lg-8 mx-auto text-center">
                        <i class="far fa-paper-plane fa-2x mb-2 text-white"></i>
                        <h2 class="text-white mb-5">Join With Diet Helper?</h2>
                        <form class="form-inline d-flex">
                            <input class="form-control flex-fill mr-0 mr-sm-2 mb-3 mb-sm-0" id="inputEmail" type="email" placeholder="Email로 회원가입하세요!" />
                            <button type="button" class="btn btn-primary mx-auto" onclick="location.href='/Login/UserRegForm.do'">Sign Up</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>

<!-- 프로젝트 개발에  참여한 팀원 연락처 정보(contact) 표시 -->
        <section class="contact-section bg-black">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fas fa-map-marked-alt text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Address</h4>
                                <hr class="my-4" />
                                <div class="small text-black-50">서울특별시 강서구 우장산로 10길 112, 
                                	<p>한국폴리텍 서울강서캠퍼스 데이터분석과</p></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fas fa-envelope text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Email</h4>
                                <hr class="my-4" />
                                <div class="small text-black-50">
                                	<p>askilofgod159@naver.com</p>
                                	<p>2changjon@naver.com</p>
                                	<p>cherish3_@naver.com</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fas fa-mobile-alt text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Phone</h4>
                                <hr class="my-4" />
                                <div class="small text-black-50"><p>+82 10 4085 1646</p>
                                	<p>+82 10 3713 3092</p>
                                	<p>+82 10 2100 1298</p>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="social d-flex justify-content-center">
                    <a class="mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                    <a class="mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                    <a class="mx-2" href="#!"><i class="fab fa-github"></i></a>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="footer bg-black small text-center text-white-50"><div class="container">Copyright © DietHelper</div></footer>
               <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
       
       <!-- 부트스트랩 js 사용 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
    </body>
</html>

