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
    <h4>글쓰기</h4>
<div class="boardView">
	<form action="writePro">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="ref_step" value="${ref_step}">
	<input type="hidden" name="ref_level" value="${ref_level}">
	<c:if test="${sessionScope.memId != null}">
	<input type="hidden" name="id" value="${sessionScope.memId}">
	</c:if>
	<c:if test="${sessionScope.memId == null}">
	<input type="hidden" name="id" value="${sessionScope.hostId}">
	</c:if>	
	<table align="center" border="1">
    	<tr>
			<th>글제목</th>
			<td colspan="3"><input type="text" name="subject" maxlength="80"></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<c:if test="${sessionScope.memId != null}">
			<td colspan="3"><input type="text" name="id" value="${sessionScope.memId}" disabled></td>	
			</c:if>
			<c:if test="${sessionScope.memId == null}">
			<td colspan="3"><input type="text" name="id" value="${sessionScope.hostId}" disabled></td>
			</c:if>		
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
		onclick="window.location='board?pageNum=${pageNum}'">
	<input class="input" type="reset" value="초기화"> 
	<input class="input" type="submit" value="글쓰기완료">   
	</form>    
</div>    
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>