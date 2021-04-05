var msg_id = "아이디를 입력하세요.!!";
var msg_pwd = "비밀번호를 입력하세요.!!";
var msg_rePwd = "비밀번호를 확인을 체크하세요.!!";
var msg_pwdChk = "비밀번호가 일치하지 않습니다.!!";
var msg_name = "이름을 입력하세요.!!";	
var msg_address = "주소를 입력하세요.!!";
var msg_phone = "휴대전화번호를 입력하세요.!!";
var msg_jumin1 = "주민번호를 입력하세요.!!";
var msg_jumin2 = "주민번호 뒷자리를 입력하세요.!!";
var msg_email1 = "이메일을 입력하세요.!!";
var msg_emailChk = "이메일형식에 일치하지 않습니다.!!";
var msg_confirmId = "중복확인을 해주세요.!!";

var insertError = "회원가입에 실패했습니다.\n확인후 다시 시도하세요.!!";
var updateError = "회원정보수정에 실패했습니다.\n확인후 다시 시도하세요.!!";
var deleteError = "회원탈퇴에 실패했습니다.\n확인후 다시 시도하세요.!!";
var passwdError = "비밀번호가 일치하지 않습니다.\n확인후 다시 시도하세요.!!";

var msg_insert = "회원가입을 축하드립니다. 로그인하세요.!!";
//에러 메시지
function errorAlert(errorMsg) {
	alert(errorMsg);
	// 이전 페이지로 이동
	window.history.back();
}

function successAlert(msg_insert) {
	alert(msg_insert);
}
//-------- 로그인페이지 ---------
function mainFocus() {
	document.Login.id.focus();
}

function inputCheck() {
	if(!document.Login.id.value) {
		alert(msg_id);
		document.Login.id.focus();
		return false;
	}
	
	if(!document.Login.pwd.value) {
		alert(msg_pwd);
		document.Login.pwd.focus();
		return false;
	}
}

// 아이디 찾기 클릭했을때 새창
function findId() {
	var url = "findId.do";
	window.open(url, "findId", "menubar=no, width=350, height=300");
}

// 비밀번호 찾기 클릭했을때 새창
function findPwd() {
	var url = "findPwd.do"
	window.open(url, "findPwd", "menubar=no, width=350, height=300");
}
//-------- 회원가입페이지 --------
function signInCheck() {	
	if(!document.join.id.value) {
		alert(msg_id);
		document.join.id.focus();
		return false;
	}
	
	if(!document.join.pwd.value) {
		alert(msg_pwd);
		document.join.pwd.focus();
		return false;
	}
	
	if(!document.join.pwdcheck.value) {
		alert(msg_rePwd);
		document.join.pwdcheck.focus();
		return false;
	}
	
	if(document.join.pwd.value != document.join.pwdcheck.value) {
		alert(msg_pwdChk);
		document.join.pwd.value = "";
		document.join.pwdcheck.value = "";
		document.join.pwd.focus();
		return false;
	}
	
	if(!document.join.name.value) {
		alert(msg_name);
		document.join.name.focus();
		return false;
	}
	
	if(!document.join.address1.value) {
		alert(msg_address);
		return false;
	}	
	
	if(!document.join.address3.value) {
		alert(msg_address);
		document.join.address3.focus();
		return false;
	}
	
	if(!document.join.phone1.value) {
		alert(msg_phone);
		document.join.phone1.focus();
		return false;
	}
	
	if(!document.join.phone2.value) {
		alert(msg_phone);
		document.join.phone2.focus();
		return false;
	}
	
	if(!document.join.phone3.value) {
		alert(msg_phone);
		document.join.phone3.focus();
		return false;
	}
	
	if(!document.join.email1.value) {
		alert(msg_email1);
		document.join.email1.focus();
		return false;
	}
	
	if(!document.join.email2.value) {
		alert(msg_email1);
		document.join.email2.focus();
		return false;
	}
	
	// 중복확인 버튼을 클릭하지 않는 경우
	// join - hiddenId : 중복확인 버튼 클릭여부 체크(0:클릭안함, 1:클릭함)
	if(document.join.hiddenId.value == "0") {
		alert(msg_confirmId);
		document.join.dupChk.focus();
		return false;
	}
	
	// 아이디 및 비밀번호 및 이메일아이디 : 숫자와 문자 포함 형태의 4~12자리 이내의 암호 정규식
	var reg1 = /^[A-Za-z0-9]{4,12}$/;	
	var id = document.join.id;
	var pwd = document.join.pwd;	

	var result1 = reg1.test(id.value);
	var result2 = reg1.test(pwd.value);
	
	// 이름 : 입력시작부터 입력끝까지 한글 2~5글자까지 일치하는 패턴으로 정규표현 객체를 생성
	var reg2 = /^[가-힣]{2,5}$/;	
	var name = document.join.name;	

	var result3 = reg2.test(name.value);
	
	// 핸드폰 : 처음 3자리, 가운데 3~4자리, 끝 4자리 숫자
	// 마지막 숫자는 숫자 4개가 일치하는 패턴으로 정규표현 객체를 생성
	var reg3 = /^\d{3}$/;
	var reg4 = /^\d{3,4}$/;
	var reg5 = /^\d{4}$/;	

	var phone1 = document.join.phone2;
	var phone2 = document.join.phone3;	

	var result4 = reg3.test(phone1.value);
	var result5 = reg4.test(phone2.value);
	var result6 = reg5.test(phone3.value);
	
	// 주민번호 : jumin1은 6자리 숫자, jumin2는 7자리 숫자
	var reg6 = /^\d{6}$/;
	var reg7 = /^\d{7}$/;
	
	var jumin1 = document.join.jumin1;
	var jumin2 = document.join.jumin2;
	
	var result7 = reg6.test(jumin1.value);
	var result8 = reg7.test(jumin2.value);
	
	if(!result1) {
		alert("ID는 숫자와 문자 포함 형태의 4~12자리 이내로 입력해주세요.");
		id.value = "";
		document.join.id.focus();
		return false;
	} else if(!result2) {
		alert("비밀번호는 숫자와 문자 포함 형태의 4~12자리 이내로 입력해주세요.");
		pwd.value = "";
		document.join.pwd.focus();
		return false;
	} else if(!result3) {
		alert("이름은 한글 2~5글자입니다.");
		name.value = "";
		document.join.name.focus();
		return false;
	} else if(!result4) {
		alert("휴대폰의 처음 숫자는 3자입니다.");
		name.value = "";
		document.join.phone1.focus();
		return false;
	} else if(!result5) {
		alert("휴대폰의 중간 숫자는 3~4자입니다.");
		name.value = "";
		document.join.phone2.focus();
		return false;
	} else if(!result6) {
		alert("휴대폰의 마지막 숫자는 4자입니다.");
		name.value = "";
		document.join.phone3.focus();
		return false;
	} else if(!result7) {
		alert("주민번호의 첫번째 숫자는 6자입니다.");
		name.value = "";
		document.join.jumin1.focus();
		return false;
	} else if(!result8) {
		alert("주민번호의 마지막 숫자는 7자입니다.");
		name.value = "";
		document.join.jumin2.focus();
		return false;
	}
}

//----------- 중복확인 페이지 comfirmId.do -------
//중복확인창 포커스
function confirmIdFocus() {
	document.confirmform.id.focus();
}

//중복확인창에서 id입력 여부
function confirmIdCheck() {
	if(!document.confirmform.id.value) {
		alert(msg_id);
		document.confirmform.id.focus();
		return false;
	}
}

//hiddenId : 중복확인 버튼 클릭여부 체크(0:클릭안함, 1:클릭함)
function setId(id) {
	opener.document.join.id.value=id;
	opener.document.join.hiddenId.value=1;
	
	self.close();
}

// 주소 선택
function postCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("postcode").value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

// 이메일 선택
function selectEmail() {
	if(document.join.email3.value == 0) {
		document.join.email2.value = "";
		document.join.email2.focus();
	} else {
		// 직접입력이 아닌 경우 select box의 값(email3)을 email2의 값으로 설정
		document.join.email2.value = document.join.email3.value;
	}
}

// 수정, 탈퇴 비밀번호 확인 페이지
function passwdFocus() {
	document.passwdform.pwd.focus();
}

function passwdCheck() {
	if(!document.passwdform.pwd.value) {
		alert(msg_pwd);
		document.passwdform.pwd.focus();
		return false;
	}
}

// 회원수정 페이지    modifyForm
function modifyCheck() {
	if(!document.modifyForm.pwd.value) {
		alert(msg_pwd);
		document.modifyForm.pwd.focus();
		return false;
	}
	
	if(!document.modifyForm.pwdcheck.value) {
		alert(msg_rePwd);
		document.modifyForm.pwdcheck.focus();
		return false;
	}
	
	if(document.modifyForm.pwd.value != document.modifyForm.pwdcheck.value) {
		alert(msg_pwdChk);
		document.modifyForm.pwd.value = "";
		document.modifyForm.pwdcheck.value = "";
		document.modifyForm.pwd.focus();
		return false;
	}
	
	if(!document.modifyForm.address1.value) {
		alert(msg_address);
		return false;
	}	
	
	if(!document.modifyForm.address3.value) {
		alert(msg_address);
		document.modifyForm.address3.focus();
		return false;
	}
	
	if(!document.modifyForm.phone1.value) {
		alert(msg_phone);
		document.modifyForm.phone1.focus();
		return false;
	}
	
	if(!document.modifyForm.phone2.value) {
		alert(msg_phone);
		document.modifyForm.phone2.focus();
		return false;
	}
	
	if(!document.modifyForm.phone3.value) {
		alert(msg_phone);
		document.modifyForm.phone3.focus();
		return false;
	}
	
	if(!document.modifyForm.email1.value) {
		alert(msg_email1);
		document.modifyForm.email1.focus();
		return false;
	}
	
	if(!document.modifyForm.email2.value) {
		alert(msg_email1);
		document.modifyForm.email2.focus();
		return false;
	}
	
	// 아이디 및 비밀번호 및 이메일아이디 : 숫자와 문자 포함 형태의 4~12자리 이내의 암호 정규식
	var reg1 = /^[A-Za-z0-9]{4,12}$/;	
	var pwd = document.modifyForm.pwd;	

	var result2 = reg1.test(pwd.value);
	
	// 핸드폰 : 처음 3자리, 가운데 3~4자리, 끝 4자리 숫자
	// 마지막 숫자는 숫자 4개가 일치하는 패턴으로 정규표현 객체를 생성
	var reg3 = /^\d{3}$/;
	var reg4 = /^\d{3,4}$/;
	var reg5 = /^\d{4}$/;	

	var phone1 = document.modifyForm.phone2;
	var phone2 = document.modifyForm.phone3;	

	var result4 = reg3.test(phone1.value);
	var result5 = reg4.test(phone2.value);
	var result6 = reg5.test(phone3.value);
	
	if(!result2) {
		alert("비밀번호는 숫자와 문자 포함 형태의 4~12자리 이내로 입력해주세요.");
		pwd.value = "";
		document.modifyForm.pwd.focus();
		return false;
	} else if(!result4) {
		alert("휴대폰의 처음 숫자는 3자입니다.");
		name.value = "";
		document.modifyForm.phone1.focus();
		return false;
	} else if(!result5) {
		alert("휴대폰의 중간 숫자는 3~4자입니다.");
		name.value = "";
		document.modifyForm.phone2.focus();
		return false;
	} else if(!result6) {
		alert("휴대폰의 마지막 숫자는 4자입니다.");
		name.value = "";
		document.modifyForm.phone3.focus();
		return false;
	}
}