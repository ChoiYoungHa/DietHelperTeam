<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.dto.MapDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%   List<MapDTO> rList = (List<MapDTO>) request.getAttribute("rList"); %>

<!DOCTYPE html>
<html>
<head>
<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>집 주변 체육시설 추천</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
  
<!-- DietHelper 메인 로고 -->
<link href="/resource/assets/img/healthlogo.JPG" rel="icon">

<!-- 상단 DietHelper 로고 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
	
<!-- Vendor CSS Files -->
  <link href="/resource/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resource/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="/resource/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="/resource/assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="/resource/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
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
						href="/MainPage.do" style="color:#778899;"><b>Diet
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>&nbsp;
						Helper</b></a></li>
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
				<li><a href="/map.do"><i class="bx bx-book-content"></i>집주변 체육시설</a></li>		
				<li><a href="#healthwise"><i class="bx bx-server"></i>다이어트
						팁</a></li>
				<li><a href="javascript:window.history.back();"><i class="bx bx-server"></i>뒤로가기</a></li>
						
			</ul>
		</nav>
	</header>
	
<!-- 메뉴바를 제외한 지도를 통한 주변 운동시설 추천, 다이어트 팁 추천 -->
  <main id="main">
  <section id="hero" class="d-flex flex-column justify-content-center align-items-center">
  
  <div class="mx-auto text-center">		
					<div class="mx-auto text-center">
					<h1 class="mx-auto my-0 text-uppercase" style="color: lightgray;">

						<b><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>&nbsp;
							집 주변 체육시설</b>
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
						<br>
					</h1>
				</div>
			</div>
			
<p style="margin-top:-12px">
    <em class="link">
       <!-- <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
        </a>  --> 
    </em>
</p>
<div id="map" style="width:70%;height:650px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e79d5d26187c22c88032f2fea023427"></script>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.552295,126.842843), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  


var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 

// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
var positions = [
<%for (int i=0;i<rList.size();i++) {
	MapDTO eDTO = rList.get(i);
	if (eDTO == null) {
		eDTO = new MapDTO();
	}
	else if(i !=rList.size()-1 ) {%>
	
    {
        content: '<div><%=eDTO.getMap_name()%></div>', 
        latlng: new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>)
    },
	

<%}
else{%>
{
    content: '<div><%=eDTO.getMap_name()%></div>', 
    latlng: new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>)
}];
var coords = new kakao.maps.LatLng(<%=eDTO.getMap_pointy()%>, <%=eDTO.getMap_pointx()%>);
<%}
}%>
//지도의 중심을 결과값으로 받은 위치로 이동시킵니다
map.setCenter(coords);

for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng // 마커의 위치
    });

    // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content // 인포윈도우에 표시할 내용
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}
</script>
  </section>
 <!-- 화면이 작아질 때 사라지는 메뉴바를 보일 수 있게 하는 버튼(우측 상단) -->
		<button type="button" class="mobile-nav-toggle d-xl-none"><i class="icofont-navigation-menu"></i></button>

<!-- ======= 로그인 후 모든 페이지에 공통으로 들어가는 Diet Tip 카테고리(메인 하단부 위치) ======= -->
		<section id="healthwise" class="testimonials section-bg">
			<div class="container">

				<div class="section-title">
					<h2>
						DIET
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
						TIP!
					</h2>
					<p>DietHelper 서비스 이용과 함께 유용한 Diet Tip을 참고하세요!</p>
				</div>

				<div class="owl-carousel testimonials-carousel">

					<div class="testimonial-item" data-aos="fade-up">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i> 
							다이어트 시에는, <br>
							마이크로 뉴트리션 <br>(비타민, 마그네슘 등)이 <br>
							결핍되기에 보충제를 <br>섭취하는 것이 좋다.
							<i class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<br>
						<img src="/resource/assets/img/tip04.jpg" class="testimonial-img"
							alt="">
						<h3>Tip 1!</h3>
						<h4>보충제 섭취</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="100">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i>
							몸무게는 매일 아침 <br>
							공복 상태에서 측정하고, <br>
							1주일 동안 기록한 몸무게의 <br>
							 평균이 실제 몸무게이다.<br>
							 1주일 평균을 꼭 내볼 것!
							<i class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<br>
						<img src="/resource/assets/img/tip02.jpg" class="testimonial-img"
							alt="">
						<h3>Tip 2!</h3>
						<h4>정확한 몸무게 재기</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="200">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i>
							다이어트 시, 정말 배고프다면<br>
							곤약젤리, 방울토마토, 냉동과일을 <br>
							적당량 먹으면 허기가 <br> 조금은 해소된다. <br>
							저칼로리 식품 이용할 것!
							<i class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<br>
						<img src="/resource/assets/img/tip03.jpg" class="testimonial-img"
							alt="">
						<h3>Tip 3!</h3>
						<h4>허기질 때 해소법</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="300">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i>
							과학적으로 효과가 입증된<br>
							보충제의 종류에는 <br>
							멀티비타민, 오메가3, 비타민D3, <br>
							크레아틴, 카페인이 있다. <br>
							입증된 보충제를 이용하자!
							<i class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<br>
						<img src="/resource/assets/img/anolde.JPG" class="testimonial-img"
							alt="">
						<h3>Tip 4!</h3>
						<h4>보충제 추천</h4>
					</div>

					<div class="testimonial-item" data-aos="fade-up"
						data-aos-delay="400">
						<p>
							<i class="bx bxs-quote-alt-left quote-icon-left"></i>
							땅콩버터, 통밀빵은 최대한 <br>
							설탕이 들어가지 않는 <br>
							것을 고르는것이 좋다. <br>
							정확한 칼로리 카운팅을 위해 <br>
							주방용 전자저울을 사용한다.
							<i class="bx bxs-quote-alt-right quote-icon-right"></i>
						</p>
						<br>
						<img src="/resource/assets/img/tip05.jpg" class="testimonial-img"
							alt="">
						<h3>Tip 5!</h3>
						<h4>추천식단 이용 시 주의점</h4>
					</div>

				</div>

			</div>
		</section>
	</main>
<!-- 메인 페이지 화면, 운동 명언 끝 -->

<!-- 왼쪽 하단 @Copyright Footer, 화면 작아질 시 하단에 로고 표시 -->
	<footer id="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>DietHelper</span></strong>
			</div>
		</div>
	</footer>

<!-- Vendor JS Files -->
		<script src="/resource/assets/vendor/jquery/jquery.min.js"></script>
		<script src="/resource/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="/resource/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
		<script src="/resource/assets/vendor/php-email-form/validate.js"></script>
		<script src="/resource/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
		<script src="/resource/assets/vendor/counterup/counterup.min.js"></script>
		<script src="/resource/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
		<script src="/resource/assets/vendor/venobox/venobox.min.js"></script>
		<script src="/resource/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
		<script src="/resource/assets/vendor/typed.js/typed.min.js"></script>
		<script src="/resource/assets/vendor/aos/aos.js"></script>

		<!-- JS 사용-->
		<script type="text/javascript" src="/resource/js/main.js"></script>

		<!-- 부트스트랩 js 사용 -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
		
		<!-- timecheck js 사용 -->
	<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
		
</body>

</html>