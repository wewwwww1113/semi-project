<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 취소</title>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="jumbotron">
	 <div class="container">
	 	<h1>주문 취소</h1>
	 </div>
		
	</div>
	<div class="container">
		<h2 class="alert alert-danger">주문이 취소 되었습니다.</h2>
	</div>
	<div class="container">
		<p> <a href="${contextPath }" 
		class="btn btn-secondary"> &laquo; 메인으로</a>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>