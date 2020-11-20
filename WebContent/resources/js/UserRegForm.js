//이메일 형식 저장
var expEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
//비밀번호 형식 저장 8글자 이상 16자 이하 영어,숫자, 특수문자 1글자이상 포함해야됨
var expPassword = /(?=.*\d{1,18})(?=.*[~`!@#$%\^&*()-+=]{1,18})(?=.*[a-zA-Z]{1,18}).{12,20}$/;
	//회원가입 정보의 유효성 체크하기
	function InsertCheck() {
			if ($("#email").val() == "") {
				alert("이메일를 입력해주세요.");
				$("#email").focus();
				return false;
			}else if (expEmail.test($("#email").val())==false) {
				alert("이메일형식이 올바르지 않습니다.");
				$("#email").focus();
				return false;
			}else{
				//이메일 재 검사 다 입력 후 이메일만 변경해서 회원가입 할 수 있기 때문에 
				$.ajax({
					url : "/UserRegForm/EmailCheck.do",
					type : "post",
					dataType : "json",
					data : {
						"email" : $("#email").val()
					},
					success : function(data) {
						if (data == 1) {
							alert("중복된 아이디입니다.");
							$("#email").focus();
							return false;
						}
					}
				})
			}
			if ($("#user_name").val() == "") {
				alert("성명를 입력해주세요.");
				$("#user_name").focus();
				return false;
			}else if ($("#password_1").val() == "") {
				alert("비밀번호를 입력해주세요.");
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
				$("#password_2").focus();
				return false;
			}else if ($("#user_an").val() == "") {
				alert("닉네임을 입력해주세요.");
				$("#user_an").focus();
				return false;
			}else {
				return true;
			}
		};
	
	//DB에 저장되어 있는 Email들과 중복 체크(중복버튼 클릭시)
	function EmailChk() {
		if (expEmail.test($("#email").val())==false) {
			alert("이메일형식이 올바르지 않습니다.");
			return false;
		}else{
			$.ajax({
				url : "/UserRegForm/EmailCheck.do",
				type : "post",
				dataType : "json",
				data : {
					"email" : $("#email").val()
				},
				success : function(data) {
					if (data == 1) {
						alert("중복된 아이디입니다.");
						return false;
					} else if (data == 0) {
						alert("사용가능한 아이디입니다.");
					}
				}
			})
		}
	}
	
	//비밀번호, 비밀번호 확인 값 일치 확인
	$(function() {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("#alert-insert").hide();
		$("#alert-check").hide();
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