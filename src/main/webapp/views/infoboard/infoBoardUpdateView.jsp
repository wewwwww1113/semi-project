<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="outer" align="center">
	<form action="infoupdate.bo" method="post">
	<input type="hidden" name="userNo" value="${ib.boardWriter}">
	<input type="hidden" name="boardNo" value="${ib.boardNo}">
	<table border="1" class="board-info">
	<tr>
		<th>카테고리</th>
		<td>
			<select name="category" style="width:100px">
				<c:forEach items="${ftList}" var="ft">
				<c:choose>
				<c:when test="${ft.categoryName eq ib.category}">
				<option value="${ft.categoryNo}" selected>${ft.categoryName}</option>
				</c:when>
				<c:otherwise>
				<option value="${ft.categoryNo}">${ft.categoryName }</option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="text" name="infoboardTitle" value=${ib.boardTitle } required>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<textarea name="infoboardContent" cols="60" rows="20" style="resize:none">${ib.boardContent }</textarea>
		</td>
	</tr>
	</table>
	<br>
	<div align="center" class="insert-confirm">
	<button type=submit>수정하기</button>
	<button type="button" onclick="deleteInfoBoard();">삭제하기</button>
	</div>
	</form>
	</div>
	<script>
	function deleteInfoBoard(){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="${contextPath}/infodelete.bo?bno=${f.boardNo}";
		}
	}
	</script>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>