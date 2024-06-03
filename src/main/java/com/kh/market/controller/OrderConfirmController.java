package com.kh.market.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.market.model.service.MarketService;
import com.kh.market.model.vo.Item;
import com.kh.market.model.vo.Order;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class OrderConfirmController
 */
@WebServlet("/views/market/confirm.mk")
public class OrderConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Item> cartList= (ArrayList<Item>) session.getAttribute("cartlist");
		for (int i=0; i<cartList.size();i++){
			Item item = cartList.get(i);
			int orderQun=item.getSalesVol();
			item.setSalesVol(orderQun+=item.getQuantity());
			int result=new MarketService().updateSalVol(item);
			if(result==0) {
				session.setAttribute("alertMsg", "상품구매에 실패하였습니다. (정보 갱신오류)");
				response.sendRedirect(request.getContextPath()+"/views/market/cart.jsp");
				return;
			}
		}
		String shipping_cartId="";
		String shipping_name="";
		Date shipping_shippingDate=null;
		String shipping_postNumber="";
		String shipping_address="";
		int price=(int)session.getAttribute("sum");
		String allItmeName=(String)session.getAttribute("allItemName");
		Member loginUser = (Member)session.getAttribute("loginUser");
		int userNo =loginUser.getUserNo();
	
		Cookie[] cookies=request.getCookies();
		
		
		
		if(cookies !=null){
			for(int i = 0; i<cookies.length;i++){
				Cookie thisCookie= cookies[i];
				String n = thisCookie.getName();
			if(n.equals("Shipping_cartId")) {
				shipping_cartId=thisCookie.getValue();}
			if(n.equals("Shipping_name")) {
				shipping_name=thisCookie.getValue();}
			if(n.equals("Shipping_shippingDate")) {
				shipping_shippingDate=Date.valueOf(thisCookie.getValue());}
			if(n.equals("Shipping_postNumber")) {
				shipping_postNumber=thisCookie.getValue();}
			if(n.equals("Shipping_address")) {
				shipping_address=thisCookie.getValue();}
			}
			
		}
		
		Order o= new Order(
				shipping_cartId,
				shipping_name,
				shipping_shippingDate,
				shipping_postNumber,
				shipping_address,
				allItmeName,
				price,
				userNo
				);
		
		int result = new MarketService().insertOrder(o);
		
		
		
		if (result>0) {
			response.sendRedirect(request.getContextPath()+"/views/market/thanksCustomer.jsp");
			session.setAttribute("alertMsg", "상품구매에 성공하였습니다.");
		}else {
			session.setAttribute("alertMsg", "상품구매에 실패하였습니다.");
			response.sendRedirect(request.getContextPath()+"/views/market/shippingInfo.jsp?cartId="+shipping_cartId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
