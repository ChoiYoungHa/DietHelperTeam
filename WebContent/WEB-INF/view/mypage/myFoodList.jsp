<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.FoodDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.util.Getapi"%>
<%@page import="poly.dto.DietDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    FoodDTO bkDTO = (FoodDTO)request.getAttribute("bkDTO");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div>
<p>
<select id="bf_food">
	<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
	<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
	<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
	<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
	<option value="현우올">현미밥, 우둔살, 올리브유</option>
	<option value="바계아">바나나, 계란, 아몬드</option>
	<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
	<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
</select>
<input type="button" name="bf" id="bf" value="아침선택">
</p>
<div>
<p>아침 메뉴  </p>
<div id="bf_kcal"></div>
<div id="bf_tan">메뉴를 선택해주세요.</div>
<div id="bf_dan"></div>
<div id="bf_fat"></div>
</div>
</div>

<div>
<p>
<select id="lf_food">
	<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
	<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
	<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
	<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
	<option value="현우올">현미밥, 우둔살, 올리브유</option>
	<option value="바계아">바나나, 계란, 아몬드</option>
	<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
	<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
</select>
<input type="button" name="lf" id="lf" value="점심선택">
</p>
<div>
<p>점심 메뉴</p>
<div id="lf_kcal"></div>
<div id="lf_tan">메뉴를 선택해주세요.</div>
<div id="lf_dan"></div>
<div id="lf_fat"></div>
</div>
</div>

<div>
<p>
<select id="df_food">
	<option value="고닭아">고구마, 닭가슴살, 아몬드</option>
	<option value="잡홍올">잡곡밥, 홍두께살, 올리브유</option>
	<option value="통훈땅">통밀식빵, 훈제연어, 땅콩버터</option>
	<option value="흰계아">흰쌀밥, 계란, 아보카도</option>
	<option value="현우올">현미밥, 우둔살, 올리브유</option>
	<option value="바계아">바나나, 계란, 아몬드</option>
	<option value="현돼스">현미밥, 돼지안심, 스트링치즈</option>
	<option value="듀새바">듀럼밀면, 새우, 바질페스토</option>
</select>
<input type="button" name="df" id="df" value="저녁선택">
</p>
<div>
<p>저녁 메뉴</p>
<div id="df_kcal"></div>
<div id="df_tan">메뉴를 선택해주세요.</div>
<div id="df_dan"></div>
<div id="df_fat"></div>
</div>
</div>

</body>

<script src="/resources/jquery-3.5.1.min.js"></script>
    <script>
    
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
    					$("#bf_kcal").text("한끼 당 칼로리 : " + json.today_kcal );
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