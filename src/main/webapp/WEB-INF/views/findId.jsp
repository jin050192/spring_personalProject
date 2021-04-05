<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>
	<h2>아이디 찾기 페이지</h2>
<form action="findIdPro.do" method="post" name="findId">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<table>
		<tr>
			<th> 이름 : </th>
			<td>
				<input id="input" type="text" name="name" maxlength="20" style="width:150px" autofocus required>
			</td>
		</tr>
		<tr>
			<th> 주민번호 : </th>
			<td>
				<input id="input" type="text" name="jumin1" maxlength="6" style="width:100px" required> - 
				<input id="input" type="text" name="jumin2" maxlength="7" style="width:100px" required>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input id="button" type="submit" value="찾기">
				<input id="button" type="reset" value="취소" onclick="self.close();">
			</th>
		</tr>
</table>
</form>
</body>
</html>