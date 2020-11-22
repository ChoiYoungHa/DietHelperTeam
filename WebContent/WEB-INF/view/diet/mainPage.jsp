<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>DietHelper-메인 페이지</title>
<meta content="" name="description">
<meta content="" name="keywords">

 <!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!-- DietHelper 메인 로고 -->
<link href="/resource/assets/img/healthlogo.JPG" rel="icon">
<link href="/resource/assets/img/healthlogo.JPG" rel="apple-touch-icon">

<!-- 상단 DietHelper 로고 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
	
<!-- Vendor CSS Files -->
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

</head>

<body>

	<!-- 우측 상단 DietHelper 로고 -->
      <nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">

			<div class="collapse navbar-collapse" id="navbarResponsive">
<ul class="navbar-nav ml-auto">
										<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#" style="color:#778899;"><b>Diet<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
				viewBox="0 0 24 24" fill="none" stroke="currentColor"
				stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
				class="feather feather-activity">
				<polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>&nbsp;Helper</b></a></li>

				</ul>
			</div>
		</div>
	</nav>

<!-- 왼쪽 상단 DietHelper 메뉴(로그인 정보, 로그아웃, 메뉴) -->
   <header id="header">
    <div class="d-flex flex-column">
<!-- Header 메뉴의 프로필사진, 로그인 닉네임 표시 -->
<div class="profile">
        <img src="resource/assets/img/healthlogo.JPG" alt="" class="img-fluid rounded-circle">
        <h1 class="text-light">환영합니다!
       <br/> <a style="color:skyblue;"><%=(String)session.getAttribute("user_an")%>&nbsp;님</a>
        </h1>
				
				<div style="margin-left:100px;margin-top:10px;">
					<a style="color:pink;" href="/Logout.do"><b>로그아웃</b></a></div>
        </div>
      </div>

<!-- 메뉴바의 메뉴 네비게이션 -->
		<nav class="nav-menu">
			<ul>
				<li class="active"><a href="/MainPage.do"><i
						class="bx bx-home"></i> <span>메인 페이지</span></a></li>
				<li><a href="/MyPage.do"><i class="bx bx-user"></i> <span>회원
							정보</span></a></li>
				<li><a href="/Diet/MyMetabolism.do"><i class="bx bx-file-blank"></i>
						<span>나의 프로필</span></a></li>
				<li><a href="/MyFoodList.do"><i class="bx bx-book-content"></i>나의
						식단</a></li>
						
				<li><a href="#healthwise"><i class="bx bx-server"></i>다이어트
						팁</a></li>
			</ul>
		</nav>
	</header>

<!-- 메뉴바를 제외한 메인 페이지(나의 기초대사량 확인, 음식 추천 확인) -->
	<main id="main">
		<div>
			<section id="hero"
				class="d-flex flex-column justify-content-center align-items-center">
				<div class="mx-auto text-center">
					<p class="mx-auto my-0 text-uppercase" style="color: lightgray;">

						<b>Diet&nbsp;</b>
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="feather feather-activity">
				<polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
						<br /> Helper

				</div>
				<div class="hero-container" data-aos="fade-in">
					<h1>
						<a href="/Diet/MyMetabolism.do">나의 기초대사량</a>
						<a href="/MyFoodList.do">음식추천받기</a>
					</h1>
					<p>
						<a href="/ProFile.do"><span class="typed"
							data-typed-items="Click ME! ,DietHelper, 나에게 맞는 식단 세팅, DietHelper와 함께하세요!"></span></a>
					</p>

					<h1>
						<button type="button" onclick="location.href='/MyFoodList.do'"
							class="btn btn-success btn-lg btn-block" style="height: 80px;">
							나의 식단 리스트</button>
					</h1>
				</div>
			</section>
		</div>
		
<!-- 화면이 작아질 때 사라지는 메뉴바를 보일 수 있게 하는 버튼(우측 상단) -->
		<button type="button" class="mobile-nav-toggle d-xl-none"><i class="icofont-navigation-menu"></i></button>

<!-- ======= 로그인 후 모든 페이지에 공통으로 들어가는 Diet Tip 카테고리(메인 하단부 위치) ======= -->
		<section id="healthwise" class="testimonials section-bg">
			<div class="container">

				<div class="section-title">
					<h2>
						DIET
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="feather feather-activity">
				<polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
						TIP!
					</h2>
					<p>Magnam dolores commodi suscipit. Necessitatibus eius
						consequatur ex aliquid fuga eum quidem. Sit sint consectetur
						velit. Quisquam quos quisquam cupiditate. Et nemo qui impedit
						suscipit alias ea. Quia fugiat sit in iste officiis commodi quidem
						hic quas.</p>
				</div>

				<div class="owl-carousel testimonials-carousel">

					<div class="testimonial-item" data-aos="fade-up">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> Proin
							iaculis purus consequat sem cure digni ssim donec porttitora
							entum suscipit rhoncus. Accusantium quam, ultricies eget id,
							aliquam eget nibh et. Maecen aliquam, risus at semper. <i
								class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>Saul Goodman</h3>
						<h4>Ceo &amp; Founder</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="100">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> Export
							tempor illum tamen malis malis eram quae irure esse labore quem
							cillum quid cillum eram malis quorum velit fore eram velit sunt
							aliqua noster fugiat irure amet legam anim culpa. <i
								class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>Sara Wilsson</h3>
						<h4>Designer</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="200">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> Enim nisi
							quem export duis labore cillum quae magna enim sint quorum nulla
							quem veniam duis minim tempor labore quem eram duis noster aute
							amet eram fore quis sint minim. <i
								class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>Jena Karlis</h3>
						<h4>Store Owner</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="300">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> Fugiat enim
							eram quae cillum dolore dolor amet nulla culpa multos export
							minim fugiat minim velit minim dolor enim duis veniam ipsum anim
							magna sunt elit fore quem dolore labore illum veniam. <i
								class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>Matt Brandon</h3>
						<h4>Freelancer</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="400">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> Quis quorum
							aliqua sint quem legam fore sunt eram irure aliqua veniam tempor
							noster veniam enim culpa labore duis sunt culpa nulla illum
							cillum fugiat legam esse veniam culpa fore nisi cillum quid. <i
								class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>John Larson</h3>
						<h4>Entrepreneur</h4>
					</div>

				</div>

			</div>
		</section>
		<!-- End Testimonials Section -->

	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>DietHelper</span></strong>
			</div>
			<div class="credits">
				<!-- All the links in the footer should remain intact. -->
				<!-- You can delete the links only if you purchased the pro version. -->
				<!-- Licensing information: https://bootstrapmade.com/license/ -->
				<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/iportfolio-bootstrap-portfolio-websites-template/ -->
				Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</footer>
	<!-- End  Footer -->

	<a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

	<!-- Vendor JS Files -->
	<script
		src="/resource/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="/resource/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src="/resource/assets/vendor/php-email-form/validate.js"></script>
	<script src="/resource/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
	<script src="/resource/assets/vendor/counterup/counterup.min.js"></script>
	<script
		src="/resource/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="/resource/assets/vendor/venobox/venobox.min.js"></script>
	<script src="/resource/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
	<script src="/resource/assets/vendor/typed.js/typed.min.js"></script>
	<script src="/resource/assets/vendor/aos/aos.js"></script>

	<!-- JS 사용-->
	<script type="text/javascript" src="/resource/js/main.js"></script>

	<!-- timecheck js 사용 -->
	<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
	
	<!-- 부트스트랩 js 사용 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resource/js/bootstrap.js"></script>

</body>

</html>