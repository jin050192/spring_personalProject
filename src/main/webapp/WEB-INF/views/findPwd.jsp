<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/setting.jsp" %>
<html>
<body>
	<h2>비번찾기 페이지</h2>
	
<form action="findPwdPro.do" method="get" name="findPwd">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<table>
		<tr>
			<th> 아이디 : </th>
			<td>
				<input id="input" type="text" name="id" maxlength="20" style="width:150px" autofocus required>
			</td>
		</tr>
		
		<tr>
			<th> 이름 : </th>
			<td>
				<input id="input" type="text" name="name" maxlength="20" style="width:150px" required>
			</td>
		</tr>
		
		<tr>
			<th> 주민번호 : </th>
			<td>
				<input id="input" type="text" name="jumin1" maxlength="6" style="width:100px" required>
				 - <input id="input" type="text" name="jumin2" maxlength="7" style="width:100px" required>
			</td>
		</tr>
		
		<tr>
			<th> 이메일주소 : </th>
			<td>
				<input id="input" type="text" name="email" maxlength="30" style="width:200px" required>
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