package com.kh.market.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Category;
import com.kh.common.MyFileRenamePolicy;
import com.kh.market.model.service.MarketService;
import com.kh.market.model.vo.Component;
import com.kh.market.model.vo.Item;
import com.kh.market.model.vo.ItemAttachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MarketUpdateController
 */
@WebServlet("/update.mk")
public class MarketUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int itemCode= Integer.parseInt(request.getParameter("itno"));
		
		MarketService ms = new MarketService();
		
		Item i = ms.selectItem(itemCode);
		ArrayList<ItemAttachment> itList = ms.selectAttachmentList(itemCode);
		Component c = ms.selectComponent(itemCode);
		ArrayList<Category> cList =new MarketService().selectCategory();
		
		Category[] category = cList.toArray(new Category[cList.size()]);
		
		
		request.setAttribute("cList", category);
		
		request.setAttribute("i", i);
		request.setAttribute("itList", itList);
		request.setAttribute("c", c);
		request.setAttribute("listLength", itList.size());
		
		request.getRequestDispatcher("views/market/marketUpdate.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=10*1024*1024;
			String savePath= request.getSession()
									.getServletContext()
									.getRealPath("/resources/marketImg/");
			MultipartRequest multiRequest =new MultipartRequest(request,
																savePath,
																maxSize,
																"UTF-8",
																new MyFileRenamePolicy());
			
			int itCode=Integer.parseInt(multiRequest.getParameter("itno"));
			String productName=multiRequest.getParameter("productName");
			int category=Integer.parseInt(multiRequest.getParameter("category"));
			int price=Integer.parseInt(multiRequest.getParameter("price"));
			int discount=Integer.parseInt(multiRequest.getParameter("discount"));
			String storageMethod=multiRequest.getParameter("storageMethod");
			
			HttpSession session=request.getSession();
		
			int fileCount = Integer.parseInt(multiRequest.getParameter("count"))-1;
			
			Item i= new Item();
			
			i.setItemName(productName);
			i.setCategory(category);
			i.setPrice(price);
			i.setStorageMethod(storageMethod);
			i.setDiscount(discount);
		
			
			ArrayList<ItemAttachment> itList=new ArrayList<>();
			int fileLev=0;
			for(int j=1; j<=fileCount; j++) {
				String key="itemImg"+j;
				if(multiRequest.getOriginalFileName(key)!=null) {
					fileLev=j;
					new MarketService().deleteItemAttachment(fileLev,itCode);
					
						ItemAttachment it=new ItemAttachment();
						it.setOriginName(multiRequest.getOriginalFileName(key));
						it.setChangeName(multiRequest.getFilesystemName(key));
						it.setFilePath("/resources/marketImg/");
						it.setFileLev(j);
						
						
						itList.add(it);
					
					
					
					
				}
			}
			
			double calorie= Double.parseDouble(multiRequest.getParameter("calorie"));
			double protin= Double.parseDouble(multiRequest.getParameter("protin"));
			double salt= Double.parseDouble(multiRequest.getParameter("salt"));
			double carbo= Double.parseDouble(multiRequest.getParameter("carbo"));
			double fat= Double.parseDouble(multiRequest.getParameter("fat"));
			double transFat= Double.parseDouble(multiRequest.getParameter("transFat"));
			double saturatedFat= Double.parseDouble(multiRequest.getParameter("saturatedFat"));
			double chol= Double.parseDouble(multiRequest.getParameter("chol"));
			double sugar= Double.parseDouble(multiRequest.getParameter("sugar"));

			Component c = new Component();
			
			c.setCalorie(calorie);
			c.setProtin(protin);
			c.setSalt(salt);
			c.setCarbo(carbo);
			c.setFat(fat);
			c.setTransFat(transFat);
			c.setSaturatedFat(saturatedFat);
			c.setChol(chol);
			c.setSugar(sugar);
			
			int result=new MarketService().updateItem(i,itList,category,c,itCode);
			
			
			String msg="";
			if(result>0) {
				msg="상품정보 갱신완료";
			}else {
				msg="갱신실패";
			}
			
			session.setAttribute("alertMsg", msg);
			response.sendRedirect(request.getContextPath()+"/list.mk");
		}
	}

}
