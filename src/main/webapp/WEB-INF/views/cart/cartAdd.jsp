<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${insertCnt == 0}">
	<script type="text/javascript">
		alert("상품 추가가 실패하였습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${insertCnt != 0}">
	<script type="text/javascript">
		alert("장바구니에 상품이 추가되었습니다.!!");
		window.location='${pathA}/user/cartList.ca';
	</script>
</c:if>
</body>
</html>