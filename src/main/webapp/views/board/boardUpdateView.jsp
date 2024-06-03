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
	<form action="update.bo" method="post">
	<input type="hidden" name="userNo" value="${b.boardWriter}">
	<input type="hidden" name="boardNo" value="${b.boardNo}">
	<table border="1" class="board-info">
	<tr>
		<th>카테고리</th>
		<td>
			<select name="category" style="width:100px">
				<c:forEach items="${ctList}" var="ct">
				<c:choose>
				<c:when test="${ct.categoryName eq b.category}">
				<option value="${ct.categoryNo}" selected>${ct.categoryName}</option>
				</c:when>
				<c:otherwise>
				<option value="${ct.categoryNo}">${ct.categoryName }</option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="text" name="boardTitle" value=${b.boardTitle } required>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<textarea name="boardContent" cols="60" rows="20" style="resize:none">${b.boardContent }</textarea>
		</td>
	</tr>
	</table>
	<br>
	<div align="center" class="insert-confirm">
	<button type=submit>수정하기</button>
	<button type="button" onclick="deleteBoard();">삭제하기</button>
	</div>
	</form>
	</div>
	<script>
	function deleteBoard(){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="${contextPath}/delete.bo?bno=${b.boardNo}";
		}
	}
	</script>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>