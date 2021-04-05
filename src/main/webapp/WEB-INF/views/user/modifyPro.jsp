<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${modifyCnt == 0}"> <!-- modify 실패 -->
	<script type="text/javascript">
		errorAlert(updateError);
	</script>
</c:if>

<c:if test="${modifyCnt != 0}"> <!-- modify 성공 -->
	<script type="text/javascript">
	setTimeout(function() {
		alert("수정처리되었습니다.!!");
		window.location="${pathA}/user/mypage.do";      
	}, 1000);
	</script>
</c:if>
</body>
</html>