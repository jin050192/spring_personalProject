<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<!-- 비밀번호 일치 -->
<c:if test="${selectCnt == 1}">
	<!-- 삭제 실패 -->
	<c:if test="${deleteCnt == 0}">
		<script type="text/javascript">
			alert("삭제가 실패하였습니다.");
			window.history.back();
		</script>
	</c:if>
	
	<!-- 삭제 성공 -->
	<c:if test="${deleteCnt != 0}">
		<script type="text/javascript">
			alert("글이 삭제되었습니다.");
			window.location='board?pageNum=${pageNum}';
		</script>
	</c:if>
</c:if>
<!-- 비밀번호 불일치 -->
<c:if test="${selectCnt != 1}">
	<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다.");
	window.history.back();
	</script>
</c:if>
</body>
</html>