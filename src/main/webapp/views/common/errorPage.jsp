<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<%@include file="/views/common/menubar.jsp" %> --%>

	<h1 align="center" style="color:pink"><%=errorMsg %></h1>
	<%-- <a href="<%=contextPath %>/login.me">로그아웃</a> --%>
	<h3 align="center"><a href="javascript:history.back();">로그인화면으로</a> </h3>
	
</body>
</html>