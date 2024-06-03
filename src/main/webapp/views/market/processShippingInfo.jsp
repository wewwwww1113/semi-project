<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cartId= new Cookie("Shipping_cartId",
		request.getParameter("cartId"));
	Cookie name= new Cookie("Shipping_name",
			request.getParameter("name"));
	Cookie shippingDate= new Cookie("Shipping_shippingDate",
			request.getParameter("shippingDate"));
	Cookie postNumber= new Cookie("Shipping_postNumber",
			request.getParameter("postNumber"));
	Cookie address= new Cookie("Shipping_address",
			request.getParameter("address"));
	
	cartId.setMaxAge(24*60*60);
	name.setMaxAge(24*60*60);
	shippingDate.setMaxAge(24*60*60);
	postNumber.setMaxAge(24*60*60);
	address.setMaxAge(24*60*60);
	
	response.addCookie(cartId);
	response.addCookie(name);
	response.addCookie(shippingDate);
	response.addCookie(postNumber);
	response.addCookie(address);
	
	response.sendRedirect("orderConfirmation.jsp");
%>