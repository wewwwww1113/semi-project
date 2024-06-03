package com.kh.market.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class KakaoPayController
 */
@WebServlet("/kakaopay.mk")
public class KakaoPayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoPayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
		
		
		URL address =new URL("https://open-api.kakaopay.com/online/v1/payment/ready");
		HttpURLConnection serverConn = (HttpURLConnection)address.openConnection();
		serverConn.setRequestMethod("POST");
		serverConn.setRequestProperty("Authorization","SECRET_KEY DEV4996F09AC415D4B6EBA132851F394E087142B");
		serverConn.setRequestProperty("Content-Type",  "application/json;charset=UTF-8");
		serverConn.setDoOutput(true);
		String parameter = "cid=TC0ONETIME&"
				+ "partner_order_id=partner_order_id&"
				+ "partner_user_id=partner_user_id&"
				+ "item_name=초코파이&"
				+ "quantity=1&"
				+ "total_amount=2200&"
				+ "vat_amount=200&"
				+ "tax_free_amount=0&"
				+ "approval_url=http://localhost:8888/HealthLife/views/market/cart.jsp&"
				+ "cancel_url=http://localhost:8888/HealthLife/views/market/cart.jsp&"
				+ "fail_url=http://localhost:8888/HealthLife/views/market/cart.jsp";
		
		OutputStream output = serverConn.getOutputStream();
		DataOutputStream dataOut=new DataOutputStream(output);
		dataOut.writeBytes(parameter);
		dataOut.close();
		
		int result=serverConn.getResponseCode();
		System.out.println(result);
		InputStream input;
		if (result==200) {
			input=serverConn.getInputStream();
		}else {
			input=serverConn.getErrorStream();
		}
		InputStreamReader reader=new InputStreamReader(input);
		BufferedReader changer= new BufferedReader(reader);
		
		
		response.setContentType("json/application;charset=UTF-8");
		
		response.getWriter().print(changer.readLine());
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
	}

}
