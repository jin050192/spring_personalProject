<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>
<c:if test="${insertCnt != 1}"> <!-- insert 실패 -->
	<script type="text/javascript">
	alert("회원가입에 실패했습니다.");
	window.location="home.do";
	</script>
</c:if>

<c:if test="${insertCnt == 1}"> <!-- insert 성공 -->
	<script type="text/javascript">
	successAlert(msg_insert);
	window.location="login.do"; 
	</script>
</c:if>
</body>
</html>