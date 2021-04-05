<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<jsp:include page="../include/host_header.jsp"/>

<div id="content">
<h4>글쓰기</h4>
<div class="boardView">
	<form action="${pathA}/admin/writeProNotice">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="writer" value="${sessionScope.hostId}">	
	<table align="center" border="1">
    	<tr>
			<th>글제목</th>
			<td colspan="3"><input type="text" name="subject" maxlength="80"></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td colspan="3"><input type="text" name="writer" value="${sessionScope.hostId}" disabled></td>			
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td colspan="3"><input type="password" name="pwd"></td>			
		</tr>
		
		<tr>
			<th class="detail">글내용</th>
			<td colspan="3" style="height:400px">
			<textarea rows="20" cols="140" name="content" style="resize:none" 
			word-break:break-all></textarea>
			</td>
		</tr>		
	</table>
	<br>
	<input class="input" type="button" value="목록보기"
		onclick="window.location='${pathA}/admin/new&event?pageNum=${pageNum}'">
	<input class="input" type="reset" value="초기화"> 
	<input class="input" type="submit" value="글쓰기완료">   
	</form>    
</div>    
</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>