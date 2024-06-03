<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="com.kh.market.model.service.MarketService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String id= request.getParameter("id");
	if(id==null || id.trim().equals("")){
		response.sendRedirect(contextPath+"/list.mk");
		return;
	}
	int intId =Integer.parseInt(id);
	
	MarketService ms=new MarketService();
	
	Item item= ms.selectItem(intId);
	if(item==null){
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	@SuppressWarnings("unchecked")
	ArrayList<Item> cartList=(ArrayList<Item>)session.getAttribute("cartlist");
	
	Item itemQnt=new Item();
	
	for(int i=0; i<cartList.size();i++){ // 상품리스트 하나씩 출력하기
		itemQnt=cartList.get(i);
		if(itemQnt.getItemCode()==intId){
			cartList.remove(itemQnt);
		}
	}
	
	response.sendRedirect("cart.jsp");
	
	%>
