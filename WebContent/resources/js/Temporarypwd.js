var check = false; //ajax data값 확인
function InsertCheck() {
	if ($("#email").val() == "") {
		alert("가입 당시 이메일를 입력해주세요.");
		$("#email").focus();
		return false;
	}else if($("#user_name").val() == ""){
		alert("가입 당시 성명를 입력해주세요.");
		$("#user_name").focus();
		return false;
	}else{
		//email, user_name 이 db에 있는게 맞는지 확인
		$.ajax({
			url : "/Temporarypwd/emailnmchk.do",
			type : "post",
			async:false,//ajax을 동기식 방식으로 전송하기 위해 사용 비동기 일경우 기본값이 true기 때문에 설정안해줘도 된다 
			dataType : "json",
			data : {
				"email" : $("#email").val(),
				"user_name" : $("#user_name").val()
			},
			success : function(data) {
				if (data == 0) {
					$("#alert-emailnmchk").show();
					$("#email").focus();
					return false;
				}else if(data ==1){
					check=true;
					return check;
				}
			}
		})
		if(check == true){
			return true;
		}else{
			return false;
		}
	}
}
	