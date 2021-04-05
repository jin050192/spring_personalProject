<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
//중복확인 버튼 클릭시 서브창 open
function confirmId() {
	// id값 미입력시
	if(!document.join.id.value) {
		alert(msg_id);
		document.join.id.focus();
		return false;
	}
	
	var url = "confirmId.do?id=" + document.join.id.value;
	window.open(url, "confirm", "menubar=no, width=350, height=300");
}
</script>
<body>
<jsp:include page="include/header.jsp"/>
<form id="join" name="join" action="signInPro.do" method="post" onsubmit="return signInCheck();">
   <legend>기본정보</legend>
   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
   <input type="hidden" name="hiddenId" value="0">
   <table>
      <tr class="border1">
         <td>아이디<a class="red">*</a></td>
         <td>
            <input type="text" name="id" size="20"> &nbsp;
            <input type="button" name="dupChk" value="아이디 중복확인" onclick="confirmId();"> &nbsp;
            (영문 소문자/숫자, 4~12자)
         </td>
      </tr>
      <tr>
         <td>비밀번호<a class="red">*</a></td>   
         <td>   
            <input type="password" name="pwd" size="20"> &nbsp;
            (영문자/숫자, 4~12자)
         </td>
      </tr>
      <tr>
         <td>비밀번호 확인<a class="red">*</a></td>   
         <td>   
            <input type="password" name="pwdcheck" size="20">
         </td>
      </tr>
	  <tr>
         <td>주민번호<a class="red">*</a></td>
         <td>
            <input type="text" name="jumin1" size="6"> -
            <input type="text" name="jumin2" size="7">
         </td>
      </tr>
      <tr>
         <td>이름<a class="red">*</a></td>   
         <td>   
            <input type="text" name="name" size="20">
         </td>
      </tr>
      <tr>
         <td>주소<a class="red">*</a></td>   
         <td>   
            <input type="text" id="postcode" name="address1" size="20" placeholder="우편번호" readonly>
            <input type="button" value="우편번호 찾기" onclick="postCode()"> <br />
            <input type="text" id="address" name="address2" size="60" placeholder="주소" readonly> <br />
            <input type="text" id="detailAddress" name="address3" size="60" placeholder="상세주소"> <br />
         </td>
      </tr>
      <tr>
         <td>휴대전화<a class="red">*</a></td>
         <td>
            <input type="text" name="phone1" size="10">
            <input type="text" name="phone2" size="10"> -
            <input type="text" name="phone3" size="10"> &nbsp;
            <input type="radio" name="phoneOk" value="수신함" checked>
            수신함
            <input type="radio" name="phoneOk" value="수신안함">
            수신안함
         </td>
      </tr>
      <tr>
         <td>이메일<a class="red">*</a></td>
         <td>
            <input type="text" name="email1" size="20"> @
            <input type="text" name="email2" size="20"> &nbsp;
            <select name="email3" onchange="selectEmail();">
               <option value="0">직접입력</option>
               <option value="naver.com">naver.com</option>
               <option value="daum.net">daum.net</option>
               <option value="gmail.com">gmail.com</option>
            </select>         
         </td>
      </tr>
   </table>
   <br>
   		<input type="submit" name="insert" value="회원가입">
   		<input type="reset" name="cancle" value="취소">
</form>
<jsp:include page="include/footer.jsp"/>
</body>
</html>