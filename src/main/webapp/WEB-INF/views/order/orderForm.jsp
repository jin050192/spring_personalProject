<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>

<jsp:include page="../include/login_header.jsp"/>
<div id="content">
	<div class="headcategory">
	<ul>
		<li><a href="${pathA}/home.do">홈</a></li>
        <li> > </li>
        <li><a href="${pathA}/user/orderForm">주문</a></li>
    </ul>
	</div>
    <h4>주문</h4>
    
    <div class="orderListArea">
    <h3>일반상품</h3>
		<table border="1" summary="">
		<thead><tr>
        	<th style="width:20%">이미지</th>
            <th style="width:30%">상품정보</th>
            <th style="width:10%">판매가</th>
            <th style="width:10%">수량</th>
            <th style="width:10%">합계</th>
        </tr></thead>
        
        <c:forEach var="i" items="${list}">
        <tr>
        <td><a href="${pathA}productInfo?num=${i.num}"><img class="image" src="/project/resources/images/${i.getImg()}"></a></td>
        <td><a href="${pathA}productInfo?num=${i.num}">${i.getName()}</a></td>
        <td><fmt:formatNumber value="${i.getPrice()}" pattern="#,###"/>원</td>
        <td>${i.count}</td>
        <td><b><fmt:formatNumber value="${i.price * i.count}" pattern="#,###"/>원</b></td>
        </tr>
        </c:forEach>
		</table>
	</div>
	
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
	</div>	
	<br><br><br><br>
	<h3>배송지정보</h3>
	<br>
	<table class="ordertable" border="1">
    <tr>
        <td style="width:10%">받으시는 분<a class="red">*</a></td>   
        <td style="width:70%"><input type="text" name="name" size="10" value="${memInfo.getName()}"></td>
    </tr>
    <tr>
        <td>주소<a class="red">*</a></td>   
        <td>   
            <input type="text" id="postcode" name="address1" size="20" placeholder="우편번호" value="${memInfo.getAddress1()}" readonly>
            <input type="button" value="우편번호 찾기" onclick="postCode()"> <br />
            <input type="text" id="address" name="address2" size="60" placeholder="주소" value="${memInfo.getAddress2()}" readonly> <br />
            <input type="text" id="detailAddress" name="address3" size="60" placeholder="상세주소" value="${memInfo.getAddress3()}"> <br />
        </td>
    </tr>
    <tr>
         <td>휴대전화<a class="red">*</a></td>
         <td>
         	<c:set var="hpArr" value="${fn:split(memInfo.getPhone(), '-')}" />
            <input type="text" name="phone1" size="10" value="${hpArr[0]}">
            <input type="text" name="phone2" size="10" value="${hpArr[1]}"> -
            <input type="text" name="phone3" size="10" value="${hpArr[2]}"> &nbsp;
         </td>
    </tr>
    </table>
    <br><br>
    
    <div class="total">
    <h4>
	<span> 최종결제 금액</span><br>
	<fmt:formatNumber value="${sum + 3000}" pattern="#,###"/>원</h4>
    <a id="payment" href="${pathA}/user/orderPro"><span class="button">상품결제진행</span></a>
    <a id="payment" href="${pathA}/user/deleteOrder"><span class="button">상품결제취소</span></a>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>