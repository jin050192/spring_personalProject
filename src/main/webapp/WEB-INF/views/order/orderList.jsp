<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

/* 전체 체크 */
$(function() {
	$("#all_check").change(function() {
		var is_check = $("#all_check").is(":checked");
		
		$(".checkbox").prop("checked", is_check);
	});	
});

</script>
<body>

<jsp:include page="../include/login_header.jsp"/>
<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="${pathA}/home.do">홈</a></li>
        <li> > </li>
        <li><a href="${pathA}/user/orderList">주문</a></li>
    </ul>
	</div>
    <h4>주문</h4>
    
    <div class="orderListArea">
    <h3>일반상품</h3>
    	<form action="${pathA}/user/cancelOrder">    	
		<table border="1" summary="">
		<thead><tr>
		<th style="width:5%" class="check" scope="col"><input type="checkbox" id="all_check" name="checkbox"></th>
			<th style="width:15%">주문날짜{주문번호}</th>
        	<th style="width:20%">이미지</th>
            <th style="width:25%">상품정보</th>
            <th style="width:10%">판매가</th>
            <th style="width:5%">수량</th>
            <th style="width:10%">합계</th>
            <th style="width:10%">주문상태</th>
        </tr></thead>
        
        <c:forEach var="i" items="${list}">
        <tr>
        <td><input type="checkbox" class ="checkbox" name="orderNum" value="${i.orderNum}"></td>        
        <td>
        <a href="">${i.getOrderNum()}</a>
        </td>
        <td><a href="${pathA}productInfo?num=${i.num}"><img class="image" src="/project/resources/images/${i.getImg()}"></a></td>
        <td><a href="${pathA}productInfo?num=${i.num}">${i.getName()}</a></td>
        <td><fmt:formatNumber value="${i.getPrice()}" pattern="#,###"/>원</td>
        <td>${i.getCount()}</td>
        <td><b><fmt:formatNumber value="${i.getMoney()}" pattern="#,###"/>원</b></td>
        <td>
        <c:choose>
		<c:when test="${i.status == 1}">
			주문대기			
		</c:when>
		<c:when test="${i.status == 2}">
			주문승인
		</c:when>
		<c:when test="${i.status == 3}">
			배송완료
		</c:when>
		<c:when test="${i.status == 4}">
			취소요청중
		</c:when>
		<c:when test="${i.status == 5}">
			환불요청중
		</c:when>
		<c:when test="${i.status == 6}">
			환불거부됨
		</c:when>
		<c:otherwise>
			환불완료				
		</c:otherwise>
		</c:choose>
        </td>
        </tr>
        </c:forEach>
		</table>
		<input type="submit" class="button" value="주문취소">
		<input type="submit" class="button" value="환불요청" formaction="${pathA}/user/refundPro">
		</form>
	</div>
	
	<!-- 페이지 컨트롤 -->
	<table style="width:1200px" align="center">
		<tr>
			<th align="center">
				<!-- 게시글이 있는 경우 -->
				<c:if test="${cnt > 0}">
					
					<!-- 처음[◀◀] / 이전블록[◀] -->
					<c:if test="${startPage > pageBlock}">
						<a href="orderList"> [◀◀] </a>
						<a href="orderList?pageNum=${startPage - pageBlock}"> [◀] </a>
					</c:if>
					
					<!-- 블록내의 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
						<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
						<a href="orderList?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="orderList?pageNum=${startPage + pageBlock}"> [▶] </a>
						<a href="orderList?pageNum=${pageCount}"> [▶▶] </a>
					</c:if>
				</c:if>
			</th>
		</tr>
	</table>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>