<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body onload="confirmIdFocus();">
	<h2>중복확인 페이지</h2>

<form action="confirmId.do" method="post" name="confirmform" id="confirm"
		onsubmit="confirmIdCheck();">
<c:if test="${selectCnt == 1}">
	<table>
		<tr>
			<th colspan="2">
				<span>${id}</span>는 사용할 수 없습니다.
		</tr>
		<tr>
			<th> 아이디 : </th>
			<td>
				<input class="input" type="text" name="id" maxlength="20" style="width:150px" autofocus required>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input id="button" type="submit" value="사용">
				<input id="button" type="reset" value="취소" onclick="self.close();">
			</th>
		</tr>
	</table>
</c:if>
<c:if test="${selectCnt != 1}">
	<table>
		<tr>
			<td align="center">
				<span>${id}</span>는 사용할 수 있습니다.
			</td>
		</tr>
		<tr>
			<th>
				<input class="inputButton" type="button" value="사용하기" onclick="setId('${id}');">
			</th>
		</tr>
	</table>
</c:if>
</form>
</body>
</html>