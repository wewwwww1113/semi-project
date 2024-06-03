<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.board-area{
	width:1200px;
	}
.board-area th{
	text-align:center;
	}
.board-area>tbody .info:hover{
	background-color:lightgrey;
	cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="board">
	<h2 align="center">${keyword}에 대한 검색 결과입니다.</h2>
	<table border="1" class="board-area" align="center">
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
	<c:when test="${empty bList}">
	<tr>
	<td colspan="7" style="text-align:center">조회 결과가 없습니다.</td>
	</tr>
	</c:when>
	<c:otherwise>
	<c:forEach items="${bList}" var="b" end="19">
	<tr class="info">
		<td>${b.boardNo}</td>
		<td>${b.category}</td>
		<td>${b.boardTitle}</td>
		<td>${b.boardWriter}</td>
		<td>${b.count}</td>
		<td>${b.recommend}</td>
		<td>${b.uploadDate}</td>
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
			location.href="detail.bo?bno="+bno;
		});
	});
	</script>
	<br>
	</div>
	<div align="center">
	<h4 align="center">검색결과가 너무 많을경우 20개까지만 표시됩니다.</h4>
	<c:if test="${page ne 1}">
	<button onclick="location.href='search.bo?searchCategory=${category}&search=${keyword}&page=${page-1}'">이전 검색</button>
	</c:if>
	<c:if test="${!empty bList}">
	<button onclick="location.href='search.bo?searchCategory=${category}&search=${keyword}&page=${page+1}'">다음 검색</button>
	</c:if>
	<form action="search.bo">
	<select name="searchCategory">
	<option value="title">글제목</option>
	<option value="content">글내용</option>
	<option value="writer">작성자</option>
	</select>
	<input type="text" name="search" style="width:350px">
	<input type="hidden" name="page" value="1">
	<button type="submit">검색</button>
	</form>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>