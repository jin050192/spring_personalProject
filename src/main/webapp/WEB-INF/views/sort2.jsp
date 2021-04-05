<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>

<c:if test="${sessionScope.memId == null}">
<jsp:include page="include/header.jsp"/>
</c:if>

<c:if test="${sessionScope.memId != null}">
<jsp:include page="include/login_header.jsp"/>
</c:if>

<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="home.do">홈</a></li>
        <li> > </li>
        <li><a href="sort2">머니플라워</a></li>
    </ul>
	</div>
    <h4>머니플라워</h4>
	<div class="list">		
		<ul>
			<c:forEach var="i" items="${list}">
			<li>				
			<div class='itembox'>
			<div class='imgarea'><a href="productInfo?num=${i.num}"><img src="/project/resources/images/${i.getImg()}" border=0 style='cursor:hand'></a></div>
			<div class='textarea'>
			<p class='title'><a href="productInfo?num=${i.num}" class='m' style="font-size:14px;color:#555555;">${i.getName()}</a></p>
			<hr>
			<p class='price' style="font-size:12px;color:#0082ba;">판매가 : <fmt:formatNumber value="${i.getPrice()}" pattern="#,###"/>원</p>
			<p class='deliver' style="font-size:12px;color:#555555;">배송비 : 3,000원</p>
			<p class='direct' style="font-size:12px;color:#555555;">당일 직배송 : 가능지역(서울,인천,경기)</p>
			</div>
			</div>
			</li>
			</c:forEach>
		</ul>
	</div>
	<br>
	<!-- 페이지 컨트롤 -->
	<table style="width:1200px" align="center">
		<tr>
			<th align="center">
				<!-- 게시글이 있는 경우 -->
				<c:if test="${cnt > 0}">
					
					<!-- 처음[◀◀] / 이전블록[◀] -->
					<c:if test="${startPage > pageBlock}">
						<a href="sort2"> [◀◀] </a>
						<a href="sort2?pageNum=${startPage - pageBlock}"> [◀] </a>
					</c:if>
					
					<!-- 블록내의 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
						<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
						<a href="sort2?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="sort2?pageNum=${startPage + pageBlock}"> [▶] </a>
						<a href="sort2?pageNum=${pageCount}"> [▶▶] </a>
					</c:if>
				</c:if>
			</th>
		</tr>
	</table>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>