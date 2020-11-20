//비밀번호 형식 저장 8글자 이상 16자 이하 영어,숫자, 특수문자 1글자이상 포함해야됨
var expPassword = /(?=.*\d{1,18})(?=.*[~`!@#$%\^&*()-+=]{1,18})(?=.*[a-zA-Z]{1,18}).{12,20}$/;
//빈값 확인하고 없으면 입력
function InsertCheck() {
	if ($("#originpwd").val() == "") {
		alert("현재 비밀번호를 입력해주세요.");
		$("#originpwd").focus();
		return false;
	}else{
		//현재 비밀번호 재 검사 다입력 후 현 비밀번호만 바꿔서 변경 할 수 있어서
		$.ajax({
			url : "/pwdchange/pwdchk.do",
			type : "post",
			dataType : "json",
			data : {
				"originpwd" : $("#originpwd").val()
			},
			success : function(data) {
				if (data == 0) {
					alert("현비밀번호가 일치하지 않습니다.");
					$("#originpwd").focus();
					return false;
				}
			}
		})
	}
	if ($("#password_1").val() == "") {
		alert("변경할 비밀번호를 입력해주세요.");
		$("#password_1").focus();
		return false;
	}else if (expPassword.test($("#password_1").val())==false) {
		$("#alert-check").show();
		$("#password_1").focus();
		return false;
	}else if ($("#password_2").val() == "") {
		$("#alert-check").hide();
		alert("비밀번호 확인을 입력해주세요.");
		$("#password_2").focus();
		return false;
	}else if ($("#password_1").val() !== $("#password_2").val()) {
		$("#alert-check").hide();
		alert("비밀번호가 일치하지 않습니다.");
		alert($("#password_1").val());
		alert($("#password_2").val());
		$("#password_2").focus();
		return false;
	}else {
		$("#alert-check").hide();
		return true;
	}
}

//DB에 저장되어 있는 password와 중복 체크(originpwd 입력창에서 벗어나면 실행)
function PwdCheck() {
	$.ajax({
		url : "/pwdchange/pwdchk.do",
		type : "post",
		dataType : "json",
		data : {
			"originpwd" : $("#originpwd").val()
		},
		success : function(data) {
			if (data == 1) {
				$("#originpwd-danger").hide();
				$("#originpwd-insert").hide();
				$("#originpwd-success").show();
				return false;
			} else if (data == 0) {
				$("#originpwd-danger").show();
				$("#originpwd-insert").hide();
				$("#originpwd-success").hide();
				return false;
			}else {
				$("#originpwd").focus();
				$("#originpwd-danger").hide();
				$("#originpwd-insert").show();
				$("#originpwd-success").hide();
				return false;
			}
		}
	})
};

//비밀번호, 비밀번호 확인 값 일치 확인
$(function() {
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("#alert-insert").hide();
	$("#alert-check").hide();
	$("#originpwd-danger").hide();
	$("#originpwd-insert").hide();
	$("#originpwd-success").hide();
	$("input").keyup(function() {
		var pwd1 = $("#password_1").val();
		var pwd2 = $("#password_2").val();
		if (pwd1 != "") {
			if(expPassword.test($("#password_1").val())==false){
				$("#alert-check").show();
			}else{
				$("#alert-check").hide();
				if( pwd2 != ""){
					$("#alert-insert").hide();
					if (pwd1 == pwd2) {
						$("#alert-success").show();
						$("#alert-danger").hide();
						$("#alert-insert").hide();
						$("#alert-check").hide();
					} else if (pwd1 != pwd2) {
						$("#alert-success").hide();
						$("#alert-danger").show();
						$("#alert-insert").hide();
						$("#alert-check").hide();
					}else{
						$("#alert-success").hide();
						$("#alert-danger").hide();
						$("#alert-insert").hide();
						$("#alert-check").hide();
					}
				}else{
					$("#alert-insert").show();
					$("#alert-success").hide();
					$("#alert-danger").hide();
				}
			}
		}
	});
});