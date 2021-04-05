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
        <li><a href="${pathA}/user/cartList">장바구니</a></li>
    </ul>
	</div>
    <h4>장바구니</h4>
    <form action="${pathA}/user/cartToOrder" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="orderListArea">
    <h3>일반상품</h3>   
		<table border="1" summary="">
		<thead><tr>
		<th style="width:5%" class="check" scope="col"><input type="checkbox" id="all_check" name="checkbox"></th>
        	<th style="width:20%">이미지</th>
            <th style="width:30%">상품정보</th>
            <th style="width:10%">판매가</th>
            <th style="width:10%">수량</th>
            <th style="width:10%">합계</th>
        </tr></thead>        
        <c:forEach var="i" items="${list}" varStatus="status">
        <tr>
        <td><input type="checkbox" class ="checkbox" name="cartN" value="${i.cartNum}"></td>
        <td><a href="${pathA}productInfo?num=${i.num}"><img class="image" src="/project/resources/images/${i.getImg()}"></a></td>
        <td><a href="${pathA}productInfo?num=${i.num}">${i.getName()}</a></td>
        <td><fmt:formatNumber value="${i.getPrice()}" pattern="#,###"/>원</td>
        <td>
        	<input type="hidden" name="cartNum" value="${i.cartNum}">
        	<input type="hidden" name="num" value="${i.num}">
        	<input class="count" name="count" type="number" value="${i.getCount()}">
        	<input type="submit" value="변경" formaction="${pathA}/user/modifyCart">
        </td>
        <td><b><fmt:formatNumber value="${i.getMoney()}" pattern="#,###"/>원</b></td>
        </tr>
        </c:forEach>
		</table>
	</div>
	<input type="submit" class="button" value="선택된 상품 삭제" formaction="${pathA}/user/deleteCart">
	<br><br><br><br>
	
	<div class="total">
	<table border="1" summary="">            
	<thead><tr>
		<th scope="col"><span>총 상품금액</span></th>
        <th scope="col">총 배송비</th>	
        <th scope="col">결제예정금액</th>
        </tr></thead>
		<tbody><tr>
		<td class="price"><strong><fmt:formatNumber value="${sum}" pattern="#,###"/></strong>원 <span class="tail displaynone"></span></td>
        <td class="option"><strong>+</strong><strong>3,000</strong>원 <span class="tail displaynone"></span></td>
        <td class="total"><strong>=</strong><strong><fmt:formatNumber value="${sum + 3000}" pattern="#,###"/></strong>원 <span class="tail displaynone"></span></td>
        </tr>
        </tbody>
	</table>
	<br>
	<input type="submit" value="전체상품주문" class="button">
	</div>
	</form>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>