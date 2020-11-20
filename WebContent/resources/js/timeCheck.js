var onecheck = 0;
//세션 남은 시간 가져오기
$(window).on("load",function() {
	doRequestURL();
	setInterval("doRequestURL();",5000);
});
function doRequestURL() {
	$.ajax({
		url : "/timecheck.do",
		type : "get",
		dataType : "JSON",
		success : function(data){ //ajax에 if는 무조껀 같이
			console.log("time :"+ data); 
			if(data <= 0){
				console.log("go Login.do");
				window.location.href = '/Logout.do';
				console.log("go Login.do error");
			} else if(onecheck == 0){
				if(data < 300){
					var timeout = confirm('현재 로그인 종료시간이 5분 남았습니다'+ '\n' + '로그인 유지 시간을 늘리시겠습니까?');
					if(timeout){
						$.ajax({
							url : "/timeupdate.do",
							type : "get",
							dataType : "JSON",
							success : function(data){
								if(data == true){
									console.log("세션및 redis 15분으로 설정");
								}
							},
							error:  function(){console.log("Error")}
						})
					}else{
						onecheck=1;
					}
				}
			}
		},
		error:  function(){console.log("Error"),clearInterval("doRequestURL();")}
	})
}