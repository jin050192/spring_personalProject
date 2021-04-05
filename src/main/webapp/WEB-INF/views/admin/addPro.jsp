<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>

<c:if test="${insertCnt == 0}"> <!-- insert 실패 -->
	<script type="text/javascript">
		alert("상품 추가가 실패했습니다.");
		window.history.back();
	</script>
</c:if>

<c:if test="${insertCnt != 0}"> <!-- insert 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("상품이 추가되었습니다.!!");
		self.close();      
	}, 1000);
	</script>
</c:if>

</body>
</html>