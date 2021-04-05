<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${selectCnt == 1}"> <!-- 로그인 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("로그인에 성공하였습니다.");
		window.location="${pathA}/admin/stockList";      
	}, 1000);
	</script>
</c:if>

<c:if test="${selectCnt != 1}"> <!-- 로그인 실패 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.");
		window.location="host_login";      
	}, 1000);
	</script>
</c:if>
</body>
</html>