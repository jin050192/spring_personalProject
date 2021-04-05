<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>
<jsp:include page="../include/host_header.jsp"/>
<div id="content">
    <h4>재고관리</h4>
    	<input type="button" class="input" value="수정/삭제" onclick="window.open('${pathA}/admin/edit', '재고수정', 'resizable=no width=700, height=800'); return false;">
    	&nbsp;&nbsp;
		<input type="button" class="input" value="상품추가" onclick="window.open('${pathA}/admin/add', '재고추가', 'resizable=no width=700, height=800'); return false;">		
    <div class="order">
	<div class="title">
        <h3>상품 재고 목록</h3>
    </div>
    <table border="1" summary="">
		<tr>
				<th style="width:10%">번호</th>
				<th style="width:10%">분류</th>
                <th style="width:15%">이미지</th>
                <th style="width:40%">상품정보</th>
                <th style="width:15%">상품금액</th>
                <th style="width:10%">수량</th>
        </tr>
		<c:forEach var="i" items="${list}">        
		<tr>
			<td>${i.getNum()}</td>
			<td>${i.getSort()}</td>
			<td><img class="image" src="/project/resources/images/${i.getImg()}"></td>
			<td>${i.getName()}</td>
			<td><fmt:formatNumber value="${i.getPrice()}" pattern="#,###"/>원</td>
			<td>${i.getCount()}</td>
		</tr>
		</c:forEach>
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
						<a href="stockList"> [◀◀] </a>
						<a href="stockList?pageNum=${startPage - pageBlock}"> [◀] </a>
					</c:if>
					
					<!-- 블록내의 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
						<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
						<a href="stockList?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="stockList?pageNum=${startPage + pageBlock}"> [▶] </a>
						<a href="stockList?pageNum=${pageCount}"> [▶▶] </a>
					</c:if>
				</c:if>
			</th>
		</tr>
	</table>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>