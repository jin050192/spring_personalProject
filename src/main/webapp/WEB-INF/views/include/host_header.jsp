<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<style>
/* header */
header {
	width:100%; 
	background-color: white;
}

header a{
	color: black; 
	text-decoration: none;	
}

header a:hover {
	text-shadow: 0px 1px 1px #000;
}

.mainsrc { /* 메인 사진 */
		text-align: center;
    	border: none;
    	vertical-align: top;
    	display: block; 
    	margin: 0px auto;
}

header .category ul {
	overflow:hidden; 
	width:1200px; 
	height:40px; 
	line-height:30px; 
	margin:0 auto;
	text-align: center;
	border-top:1px dashed #cccccc;
	border-bottom:1px solid #cccccc;
	padding: 5px 0px 20px 0px;
}

header .category li {
	margin:5px;
	font: 0.75em Verdana,Dotum,AppleGothic,sans-serif;
	font-family: 'Roboto','NanumGothic',sans-serif;
	list-style: none;
	display: block;                                     /* 리스트를 일자로 나열 */
	padding: 12px;
	color: #666666;
	font-size: 14px;
	font-weight: 400;
	text-decoration: none;
	opacity: 1.0;                                     /* 투명도 : 1.0 */
	float: left;
	margin-left:50px;		
}

header .category a {
	color:#282828;
	font-size:1.1em;
	padding:10px 30px;
}

.depth2 {display:none; z-index:1010; padding-left:0;}
.category li:hover .depth2 {display:block; position:absolute; margin-right:50px; width:180px; height:150px; background:#fff; border:0px solid #302424; text-align:left; color:#000; transition:all .2s;}
.category li:hover .depth2 a {display:block; padding:10px 80px 0px 0px; height:inherit; width:90px; font-size:14px; font-weight:300; line-height:200%; color:#000;}

</style>
<html>
<body>
<header>
	<!-- 카테고리 -->
	<a href="stockList">
	<img class="mainsrc" src="/project/include/images/모든날.png" width="280"></a>

	<div class="category">
	<ul>
		<li><a href="stockList">재고관리</a></li>
		<li><a href="manageCus">회원관리</a></li>
		<li>
			<a href="board">게시판관리</a>
				<ul class="depth2">
					<li style="padding-left:0;"><a href="${pathA}/admin/board">게시판관리</a></li>
					<li style="padding-left:0;"><a href="${pathA}/admin/new&event">공지사항관리</a></li>
				</ul>
		</li>
        <li>
        	<a href="${pathA}/admin/orderList1">주문/환불</a>
        </li>
        	
		<li><a href="${pathA}/admin/closingAccount">결산</a></li>
		<li><a href="${pathA}/admin/logout">Logout</a></li>
	</ul>
	</div>
</header>
</body>
</html>