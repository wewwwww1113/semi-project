<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
<%
		String shipping_cartId="";
		String shipping_name="";
		String shipping_shippingDate="";
		String shipping_postNumber="";
		String shipping_address="";
		
		Cookie[] cookies = request.getCookies();

		if(cookies !=null)
			for(int i = 0; i<cookies.length;i++){
				Cookie thisCookie= cookies[i];
				String n = thisCookie.getName();
			if(n.equals("Shipping_cartId"))
				shipping_cartId=URLDecoder.decode((thisCookie.getValue()),"utf-8");
			if(n.equals("shipping_shippingDate"))
				shipping_shippingDate=URLDecoder.decode((thisCookie.getValue()),"utf-8");
			
			
		}
	%>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="jumbotron" style="padding-top: 80px; padding-bottom:80px; width:700px " align="center" >
		<h6 class="text-dark text-uppercase">Order Completed</h6>
		<h1 class="text-uppercase">주문완료</h1>
		
	</div>
	<div class="container">
		<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
	</div>
	<div class="container">
		<p> <b></b></p>
		<p> <b> 주문번호 : <% out.println(shipping_cartId); %></b></p>
		<a href="${contextPath }/list.mk?sort=topSal"> &laquo; 쇼핑하기 </a>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>
<%
		session.removeAttribute("cartlist");
		session.removeAttribute("sum");
		session.removeAttribute("allItemName");
		
	for(int i =0; i< cookies.length; i++ ){
		Cookie thisCookie=cookies[i];
		String n= thisCookie.getName();
		if(n.equals("Shipping_cartId"))
			thisCookie.setMaxAge(0);
		if(n.equals("shipping_name"))
			thisCookie.setMaxAge(0);
		if(n.equals("shipping_shippingDate"))
			thisCookie.setMaxAge(0);
		if(n.equals("shipping_postNumber"))
			thisCookie.setMaxAge(0);
		if(n.equals("shipping_address"))
			thisCookie.setMaxAge(0);
		
		response.addCookie(thisCookie);
	}
%>