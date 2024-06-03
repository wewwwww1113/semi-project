<%@page import="com.kh.market.model.service.MarketService"%>
<%@page import="com.kh.market.model.vo.Order"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%

	Date today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
	String cartId = session.getId()+date.format(today);
	
%>
<style>
	tr{
	width:100%;
	}
</style>
<title>장바구니</title>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<%ArrayList<Order> orlist= new MarketService().selectOrder(loginUser.getUserNo()); %>
	<%Order[] or=orlist.toArray(new Order[orlist.size()]); %>
	<div class="jumbotron" style="padding-top: 50px; padding-battom:50px">
		<div class="container">
			<h2>장바구니</h2>
		</div>
	</div>
	<div class="container">
		<div class="row" style="backgrond-color : black; border-radius:20px; height : 75px;">
			<table width="100%" style="margin : 10px;">
				<tr>
					<td style="padding-left: 10px;">

						</td>

					<td></td>
					<td align="right" style="padding-right:35px;">
					<a href="${contextPath }/views/market/deleteCart.jsp?cartId=<%=cartId %>"
					class="btn btn-danger">카트비우기</a>
					<a href="${contextPath }/views/market/shippingInfo.jsp?cartId=<%=cartId%>"
					class="btn btn-success">주문하기</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px">
			<table class="table table-hover">
				<tr>
					<th></th>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				<%
					int sum=0;
					@SuppressWarnings("unchecked")
					ArrayList<Item> cartList=(ArrayList<Item>)session.getAttribute("cartlist");
					if (cartList==null)
						cartList = new ArrayList<Item>();
					
					for (int i=0; i<cartList.size();i++){ //상품리스트 하나씩 출력하기
						Item item= cartList.get(i);
						int total= item.getTotalPrice()*item.getQuantity();
						sum=sum+total;
					
				%>
				<tr>
					<td></td>
					<td><a href="${contextPath }/detail.mk?itno=<%=item.getItemCode()%>"><%=item.getItemName() %></a> </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="<%=item.getTotalPrice() %>" /> 원</td>
					<td><%=item.getQuantity() %></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="<%=total %>" /> 원</td>
					<td>
					   <a href="${contextPath }/views/market/removeCart.jsp?id=<%=item.getItemCode()%>"
					   class="bi bi-backspace-fill">&nbsp;삭제</a>
					</td>
				</tr>
				<%} %>
				<tr>
					<th></th>
					<th>총액</th>
					<th><fmt:formatNumber type="number" maxFractionDigits="3" value="<%=sum %>" /> 원</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</table>
			<a href="${contextPath }/list.mk?sort=topSal" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
		<% if(orlist!=null){%>
		<%for(int i=0;i<or.length;i++){ %>
		<table class="table table-hover" style="width:100%">
			<tr>
				<th width="400px">주문번호</th>
				<th width="80px">수취인</th>
				<th width="120px">배송예정일</th>
				<th width="100px">우편번호</th>
				<th width="65px">주소</th>
				<th width="100px">물품목록</th>
				<th width="65px">가격</th>
			</tr>
		
			<tr>
				<td><%out.println(or[i].getOrderNo()); %></td>
				<td><%out.println(or[i].getName()); %></td>
				<td><%out.println(or[i].getShippingDate()); %></td>
				<td><%out.println(or[i].getPostNum()); %></td>
				<td><%out.println(or[i].getAddress()); %></td>
				<td><%out.println(or[i].getCartList()); %></td>
				<td><%out.println(or[i].getPrice()); %></td>
			
			</tr>
			
			
		</table>
		<hr>
		<%} %>
		<%} %>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>