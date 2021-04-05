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
		<li><a href="${pathA}/home.do">홈</a></li>
        <li> > </li>
        <li><a href="${pathA}/new&event">New&Event</a></li>
    </ul>
	</div>
    <h4>공지사항</h4>
    <div class="boardlist">
    <table>
    	<tr>
			<td colspan="5" align="right" style="height:25">
				글목록(글갯수 : ${cnt}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 관리자만 글쓰기 가능 -->
				<c:if test="${sessionScope.hostId == '관리자'}">
				<a href="${pathA}/admin/writeFormNotice?pageNum=${pageNum}"><b>글쓰기</b></a>
				</c:if>
			</td>
		</tr>
    
    	<tr>
    		<th style="width:10%">번호</th>
    		<th style="width:30%">제목</th>
    		<th style="width:15%">작성자</th>
    		<th style="width:15%">작성일</th>
    		<th style="width:10%">조회수</th>
    	</tr>
    	   	
    	<!-- 게시글이 있으면 -->
		<c:if test="${cnt > 0}">			
				<c:forEach var="i" items="${list}">
				<tr>
					<td>					
					${number}
					<c:set var="number" value="${number - 1}" />					
					</td>
					<td style="text-align:left">				
					<a href="${pathA}/notice_content?num=${i.num}&pageNum=${pageNum}&number=${number+1}">${i.getSubject()}</a>
					</td>
					<td>${i.getWriter()}</td>
					<td>
					<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${i.getReg_date()}" />
					</td>
					<td>${i.getReadCnt()}</td>
				</tr>
				</c:forEach>			
		</c:if>
		<!-- 게시글이 없으면 -->
		<c:if test="${cnt == 0}">
			<tr>
				<td colspan="5" align="center">
					게시글이 없습니다. 글을 작성해주세요.!!
				</td>
			</tr>
		</c:if>  	
    </table>    
    </div>
    
    <!-- 페이지 컨트롤 -->
	<table style="width:1200px" align="center">
		<tr>
			<th align="center">
				<!-- 게시글이 있는 경우 -->
				<c:if test="${cnt > 0}">
					
					<!-- 처음[◀◀] / 이전블록[◀] -->
					<c:if test="${startPage > pageBlock}">
						<a href="new&event"> [◀◀] </a>
						<a href="new&event?pageNum=${startPage - pageBlock}"> [◀] </a>
					</c:if>
					
					<!-- 블록내의 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
						<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
						<a href="new&event?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="new&event?pageNum=${startPage + pageBlock}"> [▶] </a>
						<a href="new&event?pageNum=${pageCount}"> [▶▶] </a>
					</c:if>
				</c:if>
			</th>
		</tr>
	</table>
</div>
<jsp:include page="../include/footer.jsp"/>

</body>
</html>