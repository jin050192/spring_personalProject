<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
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
			window.location='${pathA}/admin/new&event?pageNum=${pageNum}';
		</script>
	</c:if>
	
</body>
</html>