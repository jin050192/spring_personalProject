<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body onload="init();">

<script language="JavaScript">
var sell_price;
var amount;

function confirmCount() {
	amount = document.form.count.value; // 선택수량
	amount *= 1;  // String형 숫자형으로 바꾸기
	var count = document.form.amount.value; // 재고수량
	count *= 1;
	if(amount > count) {
		alert("재고수량보다 많습니다.");
		return false;
	}
}

function addComma(num) {
	  var regexp = /\B(?=(\d{3})+(?!\d))/g;
	  return num.toString().replace(regexp, ',');
	}

function init () {
	sell_price = document.form.price.value;
	amount = document.form.count.value;
	var total = addComma(sell_price);
	document.getElementById("sum").innerHTML = total;
	document.getElementById("sum1").innerHTML = total;
	change();
}

function add () {
	hm = document.form.count;
	sum = document.form.sum;
	hm.value ++ ;

	document.getElementById("sum").innerHTML = parseInt(hm.value) * sell_price;
	document.getElementById("sum1").innerHTML = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.count;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			document.getElementById("sum").innerHTML = parseInt(hm.value) * sell_price;
			document.getElementById("sum1").innerHTML = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.count;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
		document.getElementById("sum").innerHTML = parseInt(hm.value) * sell_price;
		document.getElementById("sum1").innerHTML = parseInt(hm.value) * sell_price;
}  
</script>
<c:if test="${sessionScope.memId == null}">
<jsp:include page="include/header.jsp"/>
</c:if>

<c:if test="${sessionScope.memId != null}">
<jsp:include page="include/login_header.jsp"/>
</c:if>

<div id="content">

<div class="bodyArea">
<div>
        <!-- 이미지 영역 -->
        <div id="imgArea">
                <img src="/project/resources/images/${dto.img}" class="BigImage">
        </div>
        <div class="infoArea">
         <h2>${dto.name}</h2>
         <hr>
            <div>            
				<table>
                    <tbody>
						<tr>
							<th><span style="font-size:12px;color:#555555;">상품명</span></th>
                            <td><span style="font-size:12px;color:#555555;">${dto.name}</span></td>
                        </tr>
						<tr>
							<th><span style="font-size:12px;color:#0082ba;">판매가</span></th>
                            <td><span style="font-size:12px;color:#0082ba;"><strong id="span_product_price_text" style="text-decoration: line-through;"><fmt:formatNumber value="${dto.price + 3000}" pattern="#,###"/>원</strong><input id="product_price" name="product_price" value="" type="hidden"></span></td>
                        </tr>
						<tr>
						<th><span style="font-size:12px;color:#0513a6;">기본 할인</span></th>
                        <td><span><span style="font-size:12px;color:#0513a6;"><span id="span_product_price_sale"><fmt:formatNumber value="${dto.price}" pattern="#,###"/>원 </span></span></span></td>
                        </tr>
						<tr>
						<th><span style="font-size:12px;color:#555555;">배송비</span></th>
                        <td><span style="font-size:12px;color:#555555;"><span class="delv_price_B"><input id="delivery_cost_prepaid" name="delivery_cost_prepaid" value="P" type="hidden"><strong>3,000원</strong> (150,000원 이상 구매 시 무료)</span></span></td>
					</tbody>
				</table>
			</div>
			<hr>						          
			<div class="guideArea">				             
                <p class="info"><span style="font-size:12px;color:#0513a6;">재고 ${dto.count} EA</span></p>       
            </div>
            <hr>
            <form action="${pathA}/user/orderForm" name="form" method="post" onsubmit="return confirmCount();">
            <!-- 다음 화면 넘기기 위한 히든값 설정 -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="amount" value="${dto.count}">  
            <input type="hidden" name="num" value="${dto.num}">
            <input type="hidden" name="name" value="${dto.name}">            
            <div id="totalProducts" class="">           
                <table>
				<thead>
					<tr>
						<th scope="col">상품명</th>
                        <th scope="col">상품수</th>
                        <th scope="col">가격</th>
                    </tr></thead>
				<tbody class="displaynone">
					<tr>
						<td>
						<b>${dto.name}</b><br>
						</td>
                            <td>
                            <span class="quantity">
                            <input type=hidden name="price" value="${dto.price}">
                            <c:if test="${dto.count < 1}">
                            상품 재고가 없습니다.
                           	</c:if>
                           	<c:if test="${dto.count >= 1}">
                            <input type="text" name="count" value="1" size="3" onchange="change();">
							<input type="button" value=" + " onclick="add();">
							<input type="button" value=" - " onclick="del();">
							</c:if>
                            </span>
                            </td>
                            <td class="right">
							<span class="quantity_price"><b id="sum"></b>원</span>
							</td>
                    </tr>
                </tbody>
				<tfoot>
					<tr>
						<td id="tfoot" colspan="3">
						<strong>총 상품금액</strong> : <span class="total"><strong><b id="sum1"></b>원</strong></span>
						</td>
                    </tr>
                </tfoot>
				</table>
		</div>
		<br>			
			<div class="btnArea">
				<c:if test="${sessionScope.memId == null}">
				로그인 후 구매나 장바구니 이용이 가능합니다.
				</c:if>
				<c:if test="${sessionScope.memId != null}">
                <span><input id="detailbtnid1" type="submit" value="BUY"></span>
                <span><input id="detailbtnid2" type="submit" value="CART" formaction="${pathA}/user/cartAdd"></span>
                </c:if>
            </div>
        </form>    
    </div>
</div>
</div>
</div>

<jsp:include page="include/footer.jsp"/>
</body>
</html>