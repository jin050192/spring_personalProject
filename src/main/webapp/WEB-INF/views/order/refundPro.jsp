<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${updateCnt == 0}"> <!-- update 실패 -->
	<script type="text/javascript">
		alert("환불요청이 실패했습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${updateCnt != 0}"> <!-- update 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("환불이 요청되었습니다.!!");
		alert("주문 이후 일주일 경과, 상품 훼손이 있을 경우 환불이 거부될 수 있습니다.");
		window.location='${pathA}/user/orderList';      
	}, 1000);
	</script>
</c:if>
</body>
</html>