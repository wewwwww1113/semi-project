<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String shipping_cartId="";
	String shipping_name="";
	String shipping_shippingDate="";
	String shipping_postNumber="";
	String shipping_address="";
	
	Cookie[] cookies=request.getCookies();
	
	if(cookies !=null){
		for(int i = 0; i<cookies.length;i++){
			Cookie thisCookie= cookies[i];
			String n = thisCookie.getName();
		if(n.equals("Shipping_cartId"))
			shipping_cartId=thisCookie.getValue();
		if(n.equals("Shipping_name"))
			shipping_name=thisCookie.getValue();
		if(n.equals("Shipping_shippingDate"))
			shipping_shippingDate=thisCookie.getValue();
		if(n.equals("Shipping_postNumber"))
			shipping_postNumber=thisCookie.getValue();
		if(n.equals("Shipping_address"))
			shipping_address=thisCookie.getValue();
		}
		
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>장바구니</title>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1>주문 정보</h1>
		</div>
	</div>
	
	<div class="container">
		<div align="left">
			<strong>배송 주소</strong> <br>
			성명 : <%= shipping_name %> <br>
			우편번호 : <%= shipping_postNumber %> <br>
			주소 : <%= shipping_address %> <br>
		</div>
		<div>
			<table style="text-align: center;">
			<tr>
				<th>항목</th>
				<th>수량</th>
				<th>가격</th>
				<th>소계</th>
			</tr>
			<%
				int sum=0;
				String allItemName="";
			@SuppressWarnings("unchecked")
			ArrayList<Item> cartList= (ArrayList<Item>) session.getAttribute("cartlist");
			if(cartList==null)
				cartList = new ArrayList<Item>();
			for (int i=0; i<cartList.size();i++){
				Item item = cartList.get(i);
				int total = item.getTotalPrice()*item.getQuantity();
				sum = sum+total;
				allItemName+=item.getItemName()+" ";
				
			%>
			<tr>
				<td><em><%=item.getItemName() %></em></td>
				<td><%=item.getQuantity() %></td>
				<td><%=item.getTotalPrice() %>원</td>
				<td><%=total %>원</td>
			</tr>
			<%} 
			
			session.setAttribute("allItemName", allItemName);
			session.setAttribute("sum", sum);
			%>
			<tr>
				<td></td>
				<td></td>
				<td style="text-align:right;"><strong>총액 : </strong></td>
				<td style="text-align:center;" ><strong><%=sum %></strong></td>
				
			</tr>
			</table>
			
			<a href="${contextPath }/views/market/shippingInfo.jsp?cartId=<%=shipping_cartId%>"
			class="btn btn-secondary" role="button"> 이전 </a>
			<a href="${contextPath }/views/market/confirm.mk" 
 			class="btn btn-success" role="button"> 주문완료 </a> 
<!-- 			<button class="btn btn-success" id="kakaopay"> 주문완료 </button> -->
			<a href="${contextPath }/views/market/checkOutCancelled.jsp"
			class="btn btn-secondary" role="button"> 주문취소 </a>
		</div>
	</div>
	<!-- <script>
		$(function(){
			$('#kakaopay').click(function(){
				$.ajax({
					url : '/HealthLife/kakaopay.mk',
					dataType:'json',
					success:function(data){
						console.log(JSON.stringify(data))
						var box = data.next_redirect_pc_url;
						window.open(box);
					},
					error:function(error){
						alert(JSON.stringify(error));
						console.log('오류');
					}
					
				});	
			});
		});
	</script> -->
	<%@ include file="/views/common/footer.jsp" %>
	</body>
</html>