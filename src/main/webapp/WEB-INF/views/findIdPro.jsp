<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>
<!-- 해당 아이디가 있는 경우 -->
<c:if test="${id != null}"> 
고객님의 ID는 <b> ${id} </b> 입니다.
</c:if>

<!-- 해당 아이디가 없는 경우 -->
<c:if test="${id == null}"> 
고객님의 정보를 찾을 수 없습니다. 회원가입 해주시기 바랍니다.
</c:if>
</body>
</html>