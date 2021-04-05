<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<html>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

$(function() {
	// all_check 요소(=전체 선택용 체크박스)의 상태가 변한 경우
	// 전체 선택이 체크된 경우, 아래 4개 항목이 동시에  체크되고,
	// 전체 선택 체크가 해제된 경우, 아래 4개 항목이 동시에 체크가 해제된다.
	
	/* $("#all_check").click(function() {
		if($("#all_check").is(":checked")) {
			$(".hobby_check").prop("checked", true);
		} else {
			$(".hobby_check").prop("checked", false);
		}
	}); */
	$("#all_check").change(function() {
		var is_check = $("#all_check").is(":checked");
		
		$(".checkbox").prop("checked", is_check);
	});	
});
</script>
<body>
<jsp:include page="../include/host_header.jsp"/>

<div id="content">
    <h4>회원관리</h4>
    
<div class="title">
	<h3>회원 목록</h3>
</div>
	<div class="boardlist">
	<form action="${pathA}/admin/deleteMember">
    <table border="1" summary="">
		<tr>
			<th style="width:5%"><label for="all_check"><input type="checkbox" name="checkbox" id="all_check"></label></td>
			<th style="width:20%">아이디</th>
            <th style="width:15%">이름</th>
            <th style="width:20%">주소</th>
            <th style="width:20%">이메일</th>
            <th style="width:15%">가입날짜</th>
        </tr>
        <c:forEach var="i" items="${list}">
		<tr>
			<td><label><input type="checkbox" class ="checkbox" name="checkbox" value="${i.getId()}"></label></td>
			<td>${i.getId()}</td>
			<td>${i.getName()}</td>
			<td>${i.getAddress2()}</td>
			<td>${i.getEmail()}</td>
			<td>${i.getReg_date()}</td>
		</tr>
		</c:forEach>
	</table>
		<br>
		<button class="input" type="submit" value="회원탈퇴">회원탈퇴</button>
	</form>
	</div>
	
	<!-- 페이지 컨트롤 -->
	<table style="width:1200px" align="center">
		<tr>
			<th align="center">
				<!-- 게시글이 있는 경우 -->
				<c:if test="${cnt > 0}">
					
					<!-- 처음[◀◀] / 이전블록[◀] -->
					<c:if test="${startPage > pageBlock}">
						<a href="manageCus"> [◀◀] </a>
						<a href="manageCus?pageNum=${startPage - pageBlock}"> [◀] </a>
					</c:if>
					
					<!-- 블록내의 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == currentPage}">
						<span><b>[${i}]</b></span>
						</c:if>
						<c:if test="${i != currentPage}">
						<a href="manageCus?pageNum=${i}">[${i}]</a>
						</c:if>
					</c:forEach>
					<!-- 다음블록[▶] / 마지막[▶▶] -->
					<c:if test="${pageCount > endPage}">
						<a href="manageCus?pageNum=${startPage + pageBlock}"> [▶] </a>
						<a href="manageCus?pageNum=${pageCount}"> [▶▶] </a>
					</c:if>
				</c:if>
			</th>
		</tr>
	</table>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>