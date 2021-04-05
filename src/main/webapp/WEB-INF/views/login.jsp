<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body onload="mainFocus();">

<jsp:include page="include/header.jsp"/>
<div class="path">
    <div id="toptitle"><h2><span>LOGIN</span></h2></div>
	<form name ="Login" class="hostLogin" action="${pathA}/user/login_check.do" method="post" onsubmit="return inputCheck();">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<fieldset>
			<legend>로그인 </legend>
				<div class="idLabel"><label class="hostId" title="아이디"><input id="host_id" name="id" placeholder="아이디" type="text" style="ime-mode:disabled;">
				</label></div>
				<div class="pwdLabel"><label class="hostPwd" title="비밀번호"><input id="host_pwd" name="pwd" autocomplete="off" type="password" placeholder="비밀번호">
				</label></div>	
				<button type="submit" value="로그인">로그인</button>
				<hr>
				<a href="signInForm.do" class="signIn">회원가입</a>
				<a href="login.do" class="signIn" onclick="findId();">아이디 찾기</a>
				<a href="login.do" class="signIn" onclick="findPwd();">비밀번호 찾기</a>
		</fieldset>	
	</form>	
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>