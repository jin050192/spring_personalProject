<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${updateCnt == 0}"> <!-- update 실패 -->
	<script type="text/javascript">
		alert("주문취소가 실패했습니다. 배송전 상품만 취소 가능합니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${updateCnt != 0}"> <!-- update 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("주문취소가 요청되었습니다.!!");
		window.location='${pathA}/user/orderList';      
	}, 1000);
	</script>
</c:if>
</body>
</html>