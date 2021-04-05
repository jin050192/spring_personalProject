<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<jsp:include page="../include/host_header.jsp"/>
<div id="content">
    <h4>글수정</h4>
    <div class="boardView">
    <form action="${pathA}/admin/modifyProNotice" method="post">
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
			<td colspan="3">${dto.writer}</td>			
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
		onclick="window.location='${pathA}/admin/new&event?pageNum=${pageNum}'">
	<input class="input" type="reset" value="초기화"> 
	<input class="input" type="submit" value="수정완료">		
    </form>
    </div>
</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>