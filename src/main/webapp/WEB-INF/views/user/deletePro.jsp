<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<c:if test="${deleteCnt == 0}"> <!-- delete 실패 -->
	<script type="text/javascript">
	errorAlert(deleteError);
	</script>
</c:if>
<c:if test="${deleteCnt != 0}"> <!-- delete 성공 - 세션삭제, selectCnt=2로 주고, main.jsp로 이동 -->
	<% request.getSession().invalidate(); %>
	<script type="text/javascript">
		setTimeout(function() {
		alert("탈퇴처리되었습니다.!!");
		window.location="home.do"; // selectCnt = 2       
		}, 1000);
	</script>
</c:if>

</body>
</html>