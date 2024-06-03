<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.infoboard-area{
	width:1200px;
	}
.infoboard-area th{
	text-align:center;
	}
.infoboard-area>tbody .info:hover{
	background-color:lightgrey;
	cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="infoboard">
	<h2 align="center">${keyword}에 대한 검색 결과입니다.</h2>
	<table border="1" class="infoboard-area" align="center">
	<thead>
	<tr>
		<th width="50px">글번호</th>
		<th width="50px">카테고리</th>
		<th width="300px">제목</th>
		<th width="80px">작성자</th>
		<th width="50px">조회수</th>
		<th width="50px">추천수</th>
		<th width="100px">작성일</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	<c:when test="${empty fList}">
	<tr>
	<td colspan="7" style="text-align:center">조회 결과가 없습니다.</td>
	</tr>
	</c:when>
	<c:otherwise>
	<c:forEach items="${fList}" var="f" end="19">
	<tr class="info">
		<td>${f.boardNo}</td>
		<td>${f.category}</td>
		<td>${f.boardTitle}</td>
		<td>${f.boardWriter}</td>
		<td>${f.count}</td>
		<td>${f.recommend}</td>
		<td>${f.uploadDate}</td>
	</tr>
	</c:forEach>
	</c:otherwise>
	</c:choose>
	</tbody>
	</table>
	<script>
	$(function(){
		$(".info").click(function(){
			var bno=$(this).children().eq(0).text();
			location.href="infodetail.bo?bno="+bno;
		});
	});
	</script>
	<br>
	</div>
	<div align="center">
	<h4 align="center">검색결과가 너무 많을경우 최신글 기준 20개까지만 표시됩니다.</h4>
	<form action="infosearch.bo">
	<select name="infosearchCategory">
	<option value="title">글제목</option>
	<option value="content">글내용</option>
	<option value="writer">작성자</option>
	</select>
	<input type="text" name="search" style="width:350px">
	<button type="submit">검색</button>
	</form>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>