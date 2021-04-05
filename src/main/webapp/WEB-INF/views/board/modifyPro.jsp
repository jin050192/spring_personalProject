<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
	<c:if test="${updateCnt == 0}">
		<script type="text/javascript">
			alert("글수정이 되지 않았습니다.");
			window.history.back();
		</script>
	</c:if>
	
	<c:if test="${updateCnt != 0}">
		<script type="text/javascript">
		alert("글이 수정되었습니다.");
		window.location='board?pageNum=${pageNum}';
		</script>
	</c:if>
</body>
</html>