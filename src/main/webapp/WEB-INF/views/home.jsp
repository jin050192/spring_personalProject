<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>

<c:if test="${sessionScope.hostId == '관리자'}">
	<script type="text/javascript">
	window.location="${pathA}/admin/stockList";
	</script>
</c:if>

<c:if test="${sessionScope.memId == null}">
<jsp:include page="include/header.jsp"/>
</c:if>

<c:if test="${sessionScope.memId != null}">
<jsp:include page="include/login_header.jsp"/>
</c:if>

<div id="content">
        <div class="section1">
        <img class="slide_img" src="/project/resources/images/slide1.jpg" border="0" draggable="true">
        </div>
    <h4>BEST ITEM</h4>    
    <!-- list -->
	<div class="list">		
				<ul>
					<c:forEach var="i" items="${best}">
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
	<h4>NEW ITEM</h4> 
	<div class="list2">		
				<ul>
					<c:forEach var="j" items="${newList}">
					<li>				
					<div class='itembox'>
						<div class='imgarea'><a href="productInfo?num=${j.num}"><img src="/project/resources/images/${j.getImg()}" border=0 style='cursor:hand'></a></div>
						<div class='textarea'>
							<p class='title'><a href="productInfo?num=${j.num}" class='m' style="font-size:14px;color:#555555;">${j.getName()}</a></p>
							<hr>
							<p class='price' style="font-size:12px;color:#0082ba;">판매가 : <fmt:formatNumber value="${j.getPrice()}" pattern="#,###"/>원</p>
							<p class='deliver' style="font-size:12px;color:#555555;">배송비 : 3,000원</p>
							<p class='direct' style="font-size:12px;color:#555555;">당일 직배송 : 가능지역(서울,인천,경기)</p>
						</div>
					</div>
					</li>
					</c:forEach>
				</ul>
			</div>
			<hr>		
</div>
<jsp:include page="include/footer.jsp"/>		
</body>
</html>