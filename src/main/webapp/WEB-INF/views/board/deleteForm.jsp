<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>

<c:if test="${sessionScope.memId == null && sessionScope.hostId == null}">
<jsp:include page="../include/header.jsp"/>
</c:if>

<c:if test="${sessionScope.memId != null}">
<jsp:include page="../include/login_header.jsp"/>
</c:if>

<c:if test="${sessionScope.hostId == '관리자'}">
<jsp:include page="../include/host_header.jsp"/>
</c:if>

<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="home.do">홈</a></li>
        <li> > </li>
        <li><a href="board">Board</a></li>
    </ul>
	</div>
    <h4>비밀번호 확인</h4>
    
<div class="path">
	<form class="hostLogin" action="deleteBoardPro" method="post" onsubmit="return passwdCheck();">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<div class="pwdLabel"><label class="hostPwd" title="비밀번호"><input id="host_pwd" name="pwd" autocomplete="off" type="password" placeholder="비밀번호를 입력하세요.">
		</label></div>	
		<button class="input" type="submit" value="게시글삭제">게시글삭제</button>
	</form>	
</div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>