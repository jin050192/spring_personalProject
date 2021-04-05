<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<style>
	fieldset {
		margin: 15px 10px;   /* margin : 상하여백 좌우여백; */
		max-width: 600px;    
	}
	
	fieldset legend {
		font-size: 14px;     
		font-weight: bold;  
		color: black;        
	}
	
	label {
		font-size: 14px;    
		font-weight: bold;   
		width: 120px;      	
	}
	
	textarea {
		resize: none;
	}		
</style>

<script type="text/javascript">
function load() {
	var no = document.getElementById("num").value;
	window.location='${pathA}/admin/edit?num=' + no;
}

window.onload = function(){
	//파일을 선택할 때
	document.getElementById('file').onchange = function(){
		readImage();
	};
};

function readImage() {
	var file = document.getElementById('file');
	if(file.files && file.files[0]) {
		var reader = new FileReader();
		
		//이미지 읽기
		reader.readAsDataURL(file.files[0]);
		
		//이미지 전부 읽어들였으면 호출
		reader.onload = function(){
			var image = document.getElementById('image');
			image.src = reader.result;
			//img 태그 노출
			image.style.display = '';
		};
	}
};
</script>
<body>
<form id="edit" action="${pathA}/admin/editPro" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<fieldset>
	<legend>상품번호검색</legend>
	<table>
		<tr>
			<td><input type="search" id="num"></td>
			<td><input type="button" value="검색" onclick="load();"></td>
			<td><input type="file" id="file" name="img" accept="image/*"></td>
		</tr>
	</table>
	</fieldset>
	
    <fieldset>
	<legend>재고수정하기</legend>
	<table>
		<tr>
			<td><label class="reg" for="num">상품번호</label></td>
			<td><input type="text" name="num" value="${dto.getNum()}"></td>			
			<td rowspan="5"><img id="image" width="80" height="90" alt="Image Preview" src="/project/resources/images/${dto.getImg()}"></td>
		</tr>
		<tr>
			<td><label class="reg" for="name">상품이름</label></td>
			<td><input type="text" name="name" size="20" value="${dto.getName()}"></td>
		</tr>
		<tr>
			<td><label class="reg" for="price">상품가격</label></td>
			<td><input type="text" name="price" size="20" value="${dto.getPrice()}"></td>
		</tr>
		<tr>
			<td><label class="reg" for="count">상품수량</label></td>
			<td><input type="number" name="count" value="${dto.getCount()}"></td>
		</tr>
		<tr>
			<td><label class="reg" for="kind">상품분류</label><br>(체크필수)</td>
			<td>
				<select size="5" name="sort" multiple>
					<optgroup label="상품분류"> 
						<option value="1">대형꽃다발</option>
						<option value="2">머니플라워</option>
						<option value="3">생화</option>
						<option value="4">장미100송이</option>
						<option value="5">시들지않는꽃</option>
					</optgroup>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="reg" for="describe">상세설명</label></td>
			<td><textarea name="content" cols = "60" rows = "30"></textarea></td>
		</tr>
		<tr>
			<td colspan="3">
				<br>
					<input id="button" type="submit" value="수정하기">
					<input id="button" type="button" value="삭제하기" onclick="window.location='${pathA}/admin/deleteProduct?num=${dto.getNum()}'">
			</td>
		</tr>
	</table>
	</fieldset>
</form>
</body>
</html>