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

<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="home.do">홈</a></li>
        <li> > </li>
        <li><a href="board">Board</a></li>
    </ul>
	</div>
    <h4>글내용</h4>
    
    <div class="boardView">
    	<table align="center" border="1">
    	<tr>
			<th>글제목</th>
			<td colspan="3">${dto.subject}</td>
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
			<td colspan="3" style="height:400px" word-break:break-all>${dto.content}</td>
		</tr>		
	</table>       
    </div>
    <!-- 세션id와 글쓴이가 같을때만 글수정, 글삭제 가능 -->
    <c:if test="${sessionScope.memId == dto.id || sessionScope.hostId == dto.id}">
    <input class="input" type="button" value="글수정" 
	onclick="window.location='modifyBoard?num=${dto.num}&pageNum=${pageNum}'">
	<input class="input" type="button" value="글삭제"
	onclick="window.location='deleteBoardForm?num=${dto.num}&pageNum=${pageNum}'">
	</c:if>
	<!-- 로그인 상태에서만 답글쓰기 가능 -->
	<c:if test="${sessionScope.memId != null || sessionScope.hostId == '관리자'}">
	<input class="input" type="button" value="답글쓰기"
	onclick="window.location='writeForm?num=${dto.num}&pageNum=${pageNum}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
	</c:if>
	<input class="input" type="button" value="목록보기" 
	onclick="window.location='board?pageNum=${pageNum}'">   
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>