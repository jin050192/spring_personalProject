<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body onload="passwdFocus();">
<jsp:include page="../include/login_header.jsp"/>
<div class="path">
	<div id="toptitle"><h2><span>회원수정</span></h2></div>
	<form name ="passwdform" class="hostLogin" action="${pathA}/user/modifyView.do" method="post" onsubmit="return passwdCheck();">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="pwdLabel"><label class="hostPwd" title="비밀번호"><input id="host_pwd" name="pwd" autocomplete="off" type="password" placeholder="비밀번호를 입력하세요.">
				</label></div>	
				<button type="submit" value="회원수정">회원수정</button>
				<button type="reset" value="수정취소" onclick="window.history.back()">수정취소</button>
	</form>	
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>