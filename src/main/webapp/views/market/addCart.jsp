<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="com.kh.market.model.service.MarketService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String id= request.getParameter("id");
 	int intId =Integer.parseInt(id);
 	if(id==null || id.trim().equals("")){
 		response.sendRedirect("index.jsp");
 		return;
 		}
 	
 		Item item = new MarketService().selectItem(Integer.parseInt(id));
		if(item==null){
			response.sendRedirect("exceptionNoItem.jsp");
		}
		
		ArrayList<Item> itemList = new MarketService().selectItemList("topSal");
		Item items= new Item();
		for (int i=0; i<itemList.size();i++){
			items=itemList.get(i);
			if(items.getItemCode()==intId){
				break;
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<Item> list= (ArrayList<Item>)session.getAttribute("cartlist");
		if(list==null){
			list= new ArrayList<Item>();
			session.setAttribute("cartlist",list);
		}
		
		
		
		int cnt=0;
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		Item itemQnt = new Item();
		for (int i=0; i< list.size(); i++ ){
			itemQnt = list.get(i);
			if(itemQnt.getItemCode()==intId){
				cnt++;
				int orderQuantity = itemQnt.getQuantity()+quantity;
				itemQnt.setQuantity(orderQuantity);
			}
		}
		if(cnt==0){
			items.setQuantity(quantity);
			list.add(items);
		}
		
		response.sendRedirect(request.getContextPath()+"/detail.mk?itno="+intId);
		
 		%>
