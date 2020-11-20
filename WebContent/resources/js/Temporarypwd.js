var check = null; //ajax data값 확인
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
			dataType : "json",
			data : {
				"email" : $("#email").val(),
				"user_name" : $("#user_name").val()
			},
			success : function(data) {
				check=data;
				if (data == 0) {
					$("#alert-emailnmchk").show();
					$("#email").focus();
					return false;
				}
			}
		})
		if(check == 1){
			return true;
		}else{
			return false;
		}
	}
}
	