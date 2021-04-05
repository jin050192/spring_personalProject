<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
<jsp:include page="../include/login_header.jsp"/>
<c:if test="${selectCnt == 1}">	
<form id="join" name="modifyForm" action="modifyPro.do" method="post" onsubmit="return modifyCheck();">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
   <legend>기본정보</legend>
   <table>
      <tr class="border1">
         <td>아이디<a class="red">*</a></td>
         <td>
         	${vo.getId()}
         </td>
      </tr>
      <tr>
         <td>비밀번호<a class="red">*</a></td>   
         <td>   
            <input type="password" name="pwd" size="20" required> &nbsp;
            (영문자/숫자, 4~12자)
         </td>
      </tr>
      <tr>
         <td>비밀번호 확인<a class="red">*</a></td>   
         <td>   
            <input type="password" name="pwdcheck" size="20" required>
         </td>
      </tr>
	  <tr>
         <td>주민번호<a class="red">*</a></td>
         <td>
            ${vo.getJumin1()} - ${vo.getJumin2()}
         </td>
      </tr>
      <tr>
         <td>이름<a class="red">*</a></td>   
         <td>   
            ${vo.getName()}
         </td>
      </tr>
      <tr>
         <td>주소<a class="red">*</a></td>   
         <td>   
            <input type="text" id="postcode" name="address1" size="20" placeholder="우편번호" value="${vo.getAddress1()}" readonly>
            <input type="button" value="우편번호 찾기" onclick="postCode()"> <br />
            <input type="text" id="address" name="address2" size="60" placeholder="주소" value="${vo.getAddress2()}" readonly> <br />
            <input type="text" id="detailAddress" name="address3" size="60" placeholder="상세주소" value="${vo.getAddress3()}"> <br />
         </td>
      </tr>
      <tr>
         <td>휴대전화<a class="red">*</a></td>
         <td>
         	<c:set var="hpArr" value="${fn:split(vo.getPhone(), '-')}" />
            <input type="text" name="phone1" size="10" value="${hpArr[0]}">
            <input type="text" name="phone2" size="10" value="${hpArr[1]}"> -
            <input type="text" name="phone3" size="10" value="${hpArr[2]}"> &nbsp;
         </td>
      </tr>
      <tr>
         <td>이메일<a class="red">*</a></td>
         <td>
         	<c:set var="emailArr" value="${fn:split(vo.getEmail(), '@')}" />
            <input type="text" name="email1" size="20" value="${emailArr[0]}"> @
            <input type="text" name="email2" size="20" value="${emailArr[1]}"> &nbsp;
            <select name="email3" onchange="selectEmail();">
               <option value="0">직접입력</option>
               <option value="naver.com">naver.com</option>
               <option value="daum.net">daum.net</option>
               <option value="gmail.com">gmail.com</option>
            </select>         
         </td>
      </tr>
      <tr>
	  	 <td>가입일자</td>
		 <td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${vo.getReg_date()}" /></td>
	  </tr>
   </table>
   <br>
   		<input id="button" type="submit" name="insert" value="수정완료">
   		<input id="button" type="reset" name="cancle" value="초기화">
   		<input id="button" type="button" value="수정취소" onclick="window.location='home.do'">
</form>
</c:if>

<c:if test="${selectCnt != 1}">
		<script type="text/javascript">
			errorAlert(passwdError);
		</script>
</c:if>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>