<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${updateCnt == 0}"> <!-- update 실패 -->
	<script type="text/javascript">
		alert("배송완료 처리가 실패했습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${updateCnt != 0}"> <!-- update 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("배송완료 처리가 완료되었습니다.!!");
		window.location='${pathA}/admin/orderList2';      
	}, 1000);
	</script>
</c:if>
</body>
</html>