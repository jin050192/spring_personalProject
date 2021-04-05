<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<body>
<header>
        <div class="gnb">
            <ul>
                <li><a href="${pathA}/user/logout.do">Logout</a></li>
                <li><a href="${pathA}/user/cartList">Cart</a></li>
                <li><a href="${pathA}/user/orderList">Order</a></li>
                <li><a href="${pathA}/user/mypage.do">Mypage</a></li>
                <li><a href="${pathA}/new&event">New & Event</a></li>
                <li><a href="${pathA}/board">게시판</a></li>
            </ul>
        </div>
	<a href="${pathA}/home.do">
	<img class="mainsrc" src="/project/include/images/모든날.png" width="280"></a>
<!-- 카테고리 -->
	<div class="category">
		<ul>
			<li><a href="${pathA}/sort1">대형꽃다발</a></li>
        	<li><a href="${pathA}/sort2">머니플라워</a></li>
        	<li><a href="${pathA}/sort3">생화</a></li>
			<li><a href="${pathA}/sort4">장미 100송이</a></li>
			<li><a href="${pathA}/sort5">시들지않는꽃</a></li>
		</ul>
	</div>
</header>
</body>
</html>