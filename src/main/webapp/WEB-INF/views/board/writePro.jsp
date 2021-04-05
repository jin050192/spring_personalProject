<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${insertCnt == 0}"> <!-- 글쓰기 실패 -->
	<script type="text/javascript">
		alert("글쓰기 실패하였습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${insertCnt != 0}">
	<script type="text/javascript">
		alert("글이 작성되었습니다.!!");
		window.location='board.bo?pageNum=${pageNum}';
	</script>
</c:if>
</body>
</html>