<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${deleteCnt == 0}"> <!-- delete 실패 -->
	<script type="text/javascript">
		alert("회원 삭제가 실패했습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${deleteCnt != 0}"> <!-- delete 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("해당 회원(들)이 삭제되었습니다.!!");
		window.history.back();     
	}, 1000);
	</script>
</c:if>
</body>
</html>