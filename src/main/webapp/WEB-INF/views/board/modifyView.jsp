<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>

<c:if test="${sessionScope.memId == null && sessionScope.hostId == null}">
<jsp:include page="../include/header.jsp"/>
</c:if>

<c:if test="${sessionScope.memId != null}">
<jsp:include page="../include/login_header.jsp"/>
</c:if>

<c:if test="${sessionScope.hostId == '관리자'}">
<jsp:include page="../include/host_header.jsp"/>
</c:if>

<c:if test="${selectCnt == 0}">
	<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다.");
	window.history.back();
	</script>
</c:if>

<c:if test="${selectCnt != 0}">
<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="home.do">홈</a></li>
        <li> > </li>
        <li><a href="board">Board</a></li>
    </ul>
	</div>
    <h4>글수정</h4>
    <div class="boardView">
    <form action="modifyProBoard" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
    <table align="center" border="1">
    	<tr>
			<th>글제목</th>
			<td colspan="3"><input type="text" name="subject" maxlength="80" value="${dto.subject}"></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td colspan="3">${dto.id}</td>			
		</tr>
		
		<tr>						
			<th>작성일</th>
			<td>
			<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.reg_date}" />
			
			<th>조회수</th>
			<td>${dto.readCnt}</td>
		</tr>
		
		<tr>
			<th class="detail">글내용</th>
			<td colspan="3" style="height:400px">
			<textarea rows="20" cols="140" name="content" style="resize:none" 
			word-break:break-all>${dto.content}</textarea>
			</td>
		</tr>		
	</table>
	<br>
	<input class="input" type="button" value="목록보기"
		onclick="window.location='board?pageNum=${pageNum}'">
	<input class="input" type="reset" value="초기화"> 
	<input class="input" type="submit" value="수정완료">		
    </form>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</c:if>
</body>
</html>