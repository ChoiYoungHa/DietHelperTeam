<%@page import="poly.util.CmmUtil"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.FoodDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%//@/page import="poly.util.Getapi"%>
<%@page import="poly.dto.DietDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
    <%
    FoodDTO bkDTO = (FoodDTO)request.getAttribute("bkDTO");
    String goal_kcal = CmmUtil.nvl((String)request.getAttribute("goal_kcal"));
    %>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>나의 식단 리스트!</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!------ jquery   ---------->
<script src="/resources/jquery-3.5.1.min.js"></script>

<!-- Table 사용 아이콘 -->
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js"
	crossorigin="anonymous"></script>

<!-- DietHelper 메인 로고 -->
	<link href="/resource/assets/img/healthlogo.JPG" rel="icon">

<!-- 구글 폰트 사용 -->
	<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
	<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css' rel='stylesheet' type='text/css'>
	
<!-- Table Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Varela+Round"
	rel="stylesheet" />
	<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

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
	<link rel="stylesheet" href="/resource/css/bootstrap.css">
	<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resource/css/style.css">
	<link rel="stylesheet" href="/resource/css/login.css">

<!-- table (foodList) css 사용 -->
	<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="resource/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="resource/css/icons.css" />
	<link rel="stylesheet" type="text/css" href="resource/css/component.css" />

<style>
.row {
font-family:Hanna; letter-spacing:3px;
}

</style>
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
		
<!-- 메뉴바를 제외한 음식 추천 메뉴 -->
	<main id="main">
		<section id="hero"
				class="d-flex flex-column justify-content-center align-items-center">
			

				
<!-- 나의 식단 추천 메뉴 테이블, 유지칼로리와 식단 선택에 따라서 알맞은 음식의 양을 정해 추천 -->	
		<div class="container">
			<div class="container-fluid">
		
				
			<h1 style="color:lightgray;text-align:center;font-size:3.0em;">
			<b><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"> <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>&nbsp;나의 식단&nbsp;</b><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
			</h1>
			
			<h1 style="color:pink;text-align:center;font-size:2.0em;">
			<%=(String)session.getAttribute("user_an")%>님의 목표 칼로리는 <%=goal_kcal%>Kcal 입니다.
			</h1>
			
			<br>
			<div class="row">
				
				<div class="col-4">
			<select id="bf_food"  class="custom-select custom-select-lg mb-3" style="background-color:lightskyblue; text-align:center;font-weight:bold;">
			<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
			<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
			<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
			<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
			<option value="현우올">현미밥, 우둔살, 올리브유</option>
			<option value="바계아">바나나, 계란, 아몬드</option>
			<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
			<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
			</select>
			<input type="button" name="bf" id="bf" value="아침 선택 (Click !)" style="background-color:#1a1a3a;letter-spacing:2px;color:yellow;font-weight:bold;padding:5px;width:100%;">
				</div>
				
				<div class="col-4">
			<select id="lf_food"  class="custom-select custom-select-lg mb-3" style="background-color:lightskyblue; text-align:center;font-weight:bold;">
				<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
				<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
				<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
				<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
				<option value="현우올">현미밥, 우둔살, 올리브유</option>
				<option value="바계아">바나나, 계란, 아몬드</option>
				<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
				<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
			</select>
				<input type="button" name="lf" id="lf" value="점심 선택 (Click !)" style="background-color:#1a1a3a;letter-spacing:2px;color:yellow;font-weight:bold;padding:5px;width:100%;">
				</div>
				
				<div class="col-4">
			<select id="df_food"  class="custom-select custom-select-lg mb-3" style="background-color:lightskyblue; text-align:center;font-weight:bold;">
				<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
				<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
				<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
				<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
				<option value="현우올">현미밥, 우둔살, 올리브유</option>
				<option value="바계아">바나나, 계란, 아몬드</option>
				<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
				<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
			</select>
				<div style="margin:auto;width:50%;">
				<input type="button" name="df" id="df" value="저녁 선택 (Click !)" style="background-color:#1a1a3a;letter-spacing:2px;color:yellow;font-weight:bold;padding:5px;width:100%;">
				</div>
				</div>
			</div>
				
			<div class="row" style="font-size:1.5em; color:lightskyblue;">
			<div class="col-4 text-center">
				<p style="color:hotpink; font-family:Hanna; letter-spacing:3px;"><b>나의 아침 메뉴 </b></p>
				<div id="bf_kcal" style="color:lightgray;"></div>
				<br>
				<div id="bf_tan">메뉴를 선택해주세요.</div>
				<div id="bf_dan"></div>
				<div id="bf_fat"></div>
			</div>
			
			<div class="col-4 text-center">
				<p style="color:hotpink;font-family:Hanna; letter-spacing:3px;"><b>나의 점심 메뉴</b></p>
				<div id="lf_kcal" style="color:lightgray;"></div>
				<br>
					<div id="lf_tan">메뉴를 선택해주세요.</div>
					<div id="lf_dan"></div>
					<div id="lf_fat"></div>
			</div>

			<div class="col-4 text-center">
				<p style="color:hotpink;font-family:Hanna; letter-spacing:3px;"><b>나의 저녁 메뉴</b></p>
				<div id="df_kcal" style="color:lightgray;"></div>
				<br>
				<div id="df_tan">메뉴를 선택해주세요.</div>
				<div id="df_dan"></div>
				<div id="df_fat"></div>
			</div>
		</div>
		
		<br>
		<br>
		
		<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
		<button type="button" class="btn btn-warning" onclick="location.href='Metabolism.do'"
		style="color:navy;font-weight:bold;padding:5px;width:100%;height:50px;font-family:Nanum Gothic;">프로필 다시 설정</button>
		</div>
		<div class="col-4"></div>
		</div>
	</div>
	</div>

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
<!-- 나의 식단 페이지, 운동 명언 끝 -->

<!-- 왼쪽 하단 @Copyright Footer, 화면 작아질 시 하단에 로고 표시 -->
	<footer id="footer">
		<div class="container">
			<div class="copyright" style="font-family:Nanum Gothic;">
				&copy; Copyright <strong><span>DietHelper</span></strong>
			</div>
		</div>
	</footer>
	
</body>

<!-- Vendor JS Files -->
		<script src="/resource/assets/vendor/jquery/jquery.min.js"></script>
		<script
			src="/resource/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script
			src="/resource/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
		<script src="/resource/assets/vendor/php-email-form/validate.js"></script>
		<script
			src="/resource/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
		<script src="/resource/assets/vendor/counterup/counterup.min.js"></script>
		<script
			src="/resource/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
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

<!-- TimeCheck JS 사용 -->
<script type="text/javascript" src="/resources/js/timeCheck.js"></script>
<script type="text/javascript" src="/resources/js/Metabolism.js"></script>
   
<!-- 메뉴 선택, 변경 -->
<script type="text/javascript">
    
    //아침 메뉴 변경
    var bf_food = "";
    	$(function(){
    		$('#bf').click(function(){
    			bf_food = $("#bf_food option:selected").val();
    			$.ajax({
    				type:"get",
    				url:"/getMorningList.do",
    				data: "bf_food=" + bf_food,
    				dataType: "JSON",
    				success:function(json){
    					console.log("result_tan", json.result_tan);
    					$("#bf_kcal").text("한끼 당 칼로리 : " + json.today_kcal  + "kcal");
    					$("#bf_tan").text(json.tan_name + " : " + json.result_tan +"g");
    					$("#bf_dan").text(json.dan_name + " : " + json.result_dan +"g");
    					$("#bf_fat").text(json.fat_name + " : " + json.result_fat +"g");
    			      },
    			   		 error:function(){
    			         alert("에러 발생 ");
    			   	  }
    			});
    		});
    	});
    	
    //점심 메뉴 변경
    var lf_food = "";
    	$(function(){
    		$('#lf').click(function(){
    			lf_food = $("#lf_food option:selected").val();
    			$.ajax({
    				type:"get",
    				url:"/getLunchList.do",
    				data: "lf_food=" + lf_food,
    				dataType: "JSON",
    				success:function(json){
    					console.log("result_tan", json.result_tan);
    					$("#lf_kcal").text("한끼 당 칼로리 : " + json.today_kcal + "kcal");
    					$("#lf_tan").text(json.tan_name + " : " + json.result_tan +"g");
    					$("#lf_dan").text(json.dan_name + " : " + json.result_dan +"g");
    					$("#lf_fat").text(json.fat_name + " : " + json.result_fat +"g");
    			      },
    			   		 error:function(){
    			         alert("에러 발생 ");
    			   	  }
    			}); 
    		});			
    	});
    
    //저녁 메뉴 변경
    var df_food = "";
    	$(function(){
    		$('#df').click(function(){
    			df_food = $("#df_food option:selected").val();
    			$.ajax({
    				type:"get",
    				url:"/getdinnerList.do",
    				data: "df_food=" + df_food,
    				dataType: "JSON",
    				success:function(json){
    					console.log("result_tan", json.result_tan);
    					$("#df_kcal").text("한끼 당 칼로리 : " + json.today_kcal + "kcal");
    					$("#df_tan").text(json.tan_name + " : " + json.result_tan +"g");
    					$("#df_dan").text(json.dan_name + " : " + json.result_dan +"g");
    					$("#df_fat").text(json.fat_name + " : " + json.result_fat +"g");
    			      },
    			   		 error:function(){
    			         alert("에러 발생 ");
    			 
    			   	  }
    			}); 
    		});			
    	});
    
    	
    </script>
   
</html>