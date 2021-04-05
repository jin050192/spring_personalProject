<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${deleteCnt == 0}">
	<script type="text/javascript">
		alert("해당 상품(들) 삭제가 실패하였습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${deleteCnt != 0}">
	<script type="text/javascript">
		alert("장바구니에 해당 상품(들)이 삭제되었습니다.!!");
		window.location='${pathA}/user/cartList';
	</script>
</c:if>
</body>
</html>