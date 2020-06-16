/**
 * formSubmit() 회원가입 필수항목 검사
 * checkid(id) 회원가입 아이디 중복검사
 */
	var check = false;

	function formSubmit() {
		var form = document.joinForm;
		if ('' == form.member_id.value || !check) {
			alert('아이디를 확인해주세요.');
			form.member_id.focus();
			return false;

		} else if ('' == form.member_pw.value) {
			alert('비밀번호를 입력해주세요.');
			form.member_pw.focus();
			return false;

		} else if ('' == form.member_name.value) {
			alert('이름을 입력해주세요.');
			form.member_name.focus();
			return false;

		} else if ('' == form.member_age.value) {
			alert('나이를 입력해주세요.');
			form.member_age.focus();
			return false;

		} else if ('' == form.member_email.value) {
			alert('이메일를 입력해주세요.');
			form.member_email.focus();
			return false;
		}

		form.submit();
	}
	
	function checkid(id){
		check = false;
		if(id == ""){
			$("#idCheck_text").text("아이디를 작성해주세요.");
		}else{
			$.ajax({
				url: contextPath + "/member/MemberCheckIdOk.me?id="+id,
				type: 'get',
				dataType: 'text',
				success: function(data){
					if(data.trim() == 'ok'){
						$("#idCheck_text").text("사용할 수 있는 아이디입니다.");
						check= true;
					}else{
						$("#idCheck_text").text("중복된 아이디입니다.");
					}
				},
				error: function(){
					console.log("오류");
				}
			})
		}
	}
	
	$("input[name='member_id']").keyup(function(event){
		var id = $("input[name='member_id']").val();
		checkid(id);
	})
	
	
	
	
	
	
	
	
	
	
	
	