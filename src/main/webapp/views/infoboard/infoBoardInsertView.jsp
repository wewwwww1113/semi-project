<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.insert-confirm{
margin-right:50px;
}
.infoboard-info input,infoboard-info textarea{
width:100%;
}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer" align="center">
	<form action="infoinsert.bo" method="post">
	<input type="hidden" name="userNo" value=${loginUser.userNo }>
	<table border="1" class="infoboard-info">
	<tr>
		<th>카테고리</th>
		<td>
			<select name="category" style="width:100px">
			<c:forEach items="${ftList}" var="ft">
				<option value="${ft.categoryNo}">${ft.categoryName}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="text" name="infoboardTitle" placeholder="제목을 입력하세요" required>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<textarea name="boardContent" cols="60" rows="20" style="resize:none"></textarea>
		</td>
	</tr>
	</table>
	<div align="center" class="insert-confirm">
	<button type=submit>작성하기</button>
	</div>
	</form>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>