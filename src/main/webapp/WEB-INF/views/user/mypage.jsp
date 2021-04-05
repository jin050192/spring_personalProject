<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<body>

<jsp:include page="../include/login_header.jsp"/>

<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="${pathA}/home.do">홈</a></li>
        <li> > </li>
        <li><a href="${pathA}/user/mypage.do">마이페이지</a></li>
    </ul>
	</div>
    <h4>MYPAGE</h4>
    
    <div class="infoWrap">
        <p class="myThumb"><img src="//img.echosting.cafe24.com/skin/base_ko_KR/member/img_member_default.gif"></p>
        <div class="myInfo">
            <strong class="id"><span>${sessionScope.memId}</span></strong>님 저희 쇼핑몰을 이용해 주셔서 감사합니다.
            <p>원하시는 메뉴를 선택해주세요.</p>
        </div>
    </div>
    
    <br><br>
     
    <div class="shopMain_left">   
    <div class="shopMain">
        <h3><a href="${pathA}/user/cartList"><strong>CARTLIST</strong> 장바구니 상품</a></h3>
        <p><a href="${pathA}/user/cartList">고객님께서 장바구니로 등록하신 상품의 목록을 보여드립니다.</a></p>
    </div>  
    
    <div class="shopMain">
        <h3><a href="${pathA}/user/orderList"><strong>ORDER</strong> 주문내역 조회</a></h3>
        <p><a href="${pathA}/user/orderList">고객님께서 주문하신 상품의 주문내역을 확인하실 수 있습니다.</a></p>
    </div>
    
    <div class="shopMain">
        <h3><a href="${pathA}/user/deleteForm.do"><strong>Membership Withdrawal</strong> 회원탈퇴</a></h3>
        <p><a href="${pathA}/user/deleteForm.do">고객님께서 가입하셨던 ID 및 이름 등의 정보가 모두 사라집니다. </a></p>
    </div>    
    </div>
       
    <div class="shopMain_right">
    <div class="shopMain">
        <h3><a href="${pathA}/user/modifyForm.do"><strong>PROFILE</strong> 회원 정보</a></h3>
        <p><a href="${pathA}/user/modifyForm.do">회원이신 고객님의 개인정보를 관리하는 공간입니다.<br>개인정보를 최신 정보로 유지하시면 보다 간편히 쇼핑을 즐기실 수 있습니다.</a></p>
    </div> 
    
    <div class="shopMain">
        <h3><a href="${pathA}/user/myPage_board"><strong>BOARD</strong> 게시판 관리</a></h3>
        <p><a href="${pathA}/user/myPage_board">고객님께서 작성하신 게시물을 관리하는 공간입니다.<br>고객님께서 작성하신 게시물을 한눈에 확인할 수 있습니다.</a></p>
    </div>   
    </div>
    
</div>

<jsp:include page="../include/footer.jsp"/>

</body>
</html>