<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<% request.getSession().invalidate(); %>
	<script type="text/javascript">
	setTimeout(function() {
		alert("로그아웃 하였습니다.");
		window.location="${pathA}/home.do";      
	}, 1000);
	</script>
</body>
</html>