<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  
<html>
<head>
	
<%
	@SuppressWarnings("unchecked")							
	ArrayList<Item> cartList =(ArrayList<Item>)session.getAttribute("cartlist");
	if(cartList==null){
		
		response.sendRedirect(request.getContextPath()+"/views/market/cart.jsp");
	}
%>
  
<title>배송 정보</title>

</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="jumbotron">
		<div class="container">
                <h6 class="text-primary text-uppercase">Order</h6>
                <h1 class="text-uppercase">배송 정보</h1>
		</div>
	</div>
	<div class="container">
	<form action="./processShippingInfo.jsp" class="form-horizontal" method="post">
		
			<div class="d-flex flex-column justify-content-center">
			<div>
            <input type="hidden" name="cartId" value="<%=request.getParameter("cartId")%>" />
			
				<label>성명</label>
				<div>
					<input name="name" type="text" id="id" required>
				</div>
			</div>
			<div>
				<label>배송일</label>
				<div>
					<input name="shippingDate" type="date" id="currentDate" required>(yyyy/mm/dd)
				</div>
			</div>
			<div>
			<div>
				<label>우편번호</label>
				<div>
					<input name="postNumber" type="text" id="postN" required>
				</div>
			</div>
			<div>
				<label>주소</label>
				<div>
					<input name="address" type="text" id="adr" required>
				</div>
			</div>
			<br><br>
			<div>
				<a href="${contextPath }/views/market/cart.jsp?cartId=<%=request.getParameter("cartId") %>
				" class="btn btn-secondary" role="button"> 이전 </a>
				<input type="submit" class="btn btn-primary" value="등록" >
				<a href="checkOutCancelled.jsp" class="btn btn-primary" role="button">취소</a>
			
			</div>
			</div>
            </div>
        
          </form>
    	</div>
  
  <script>
 
	var today=new Date().toISOString().substring(0,10);
 	$('#currentDate').attr("min",today);
 	$('#currentDate').attr("value",today);
 	
 		
 	
 	
 	
  </script>
 <%@ include file="/views/common/footer.jsp" %>
</body>
</html>