package com.kh.memorials.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.memorials.model.service.MemorialsService;
import com.kh.memorials.model.vo.Memorials;
import com.kh.memorials.model.vo.MemorialsAttachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MemorialsUpdateController
 */
@WebServlet("/MemorialsUpdateController.me")
public class MemorialsUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemorialsUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memorialsNo = Integer.parseInt(request.getParameter("memorialsNo"));
		
		
		
		Memorials me = new MemorialsService().selectMemorials(memorialsNo);
		
		MemorialsAttachment at = new MemorialsService().selectAttachment(memorialsNo);
	
		request.setAttribute("me", me);
		request.setAttribute("at", at);
		String mas = String.join(",", me.getMemorialsParts());
		request.setAttribute("mas", mas);
		request.getRequestDispatcher("views/memorials/memorialsUpdate.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		request.setCharacterEncoding("UTF-8");
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10* 1024 * 1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// DB에 저장할 데이터 추가
			String memorialsDate = multiRequest.getParameter("memorialsDate");
			String memorialsTime = multiRequest.getParameter("memorialsTime");
			String[] memorialsParts = multiRequest.getParameterValues("memorialsParts");
			String memorialsContent = multiRequest.getParameter("memorialsContent");
			int memorialsSelfScore = Integer.parseInt(multiRequest.getParameter("MemorialsSelfScore"));
			String mUserId = multiRequest.getParameter("mUserId");
			int memorialsNo = Integer.parseInt(multiRequest.getParameter("memorialsNo"));
			Memorials m = new Memorials();
			
			m.setMemorialsDate(memorialsDate);
			m.setMemorialsTime(memorialsTime);
			m.setMemorialsParts(memorialsParts);
			m.setMemorialsContent(memorialsContent);
			m.setMemorialsSelfScore(memorialsSelfScore);
			m.setmUserId(mUserId);
			m.setMemorialsNo(memorialsNo);
			
			
			MemorialsAttachment at = null;
			
			if(multiRequest.getOriginalFileName("memorialsImg") != null) {
				at = new MemorialsAttachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("memorialsImg"));
				at.setChangeName(multiRequest.getFilesystemName("memorialsImg"));
				at.setFilePath("/resources/uploadFiles/");
				
				if(multiRequest.getParameter("originFileNo")!= null) {
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
				}else { 
					at.setRefMno(memorialsNo); 
				}
			}
			int result = new MemorialsService().UpdateMemorials(m,at);
			
			HttpSession session = request.getSession();
			
			if(result>0) {
				
				session.setAttribute("alertMsg", "수정완료!");
				response.sendRedirect(request.getContextPath()/* +"/views/memorials/memorials.jsp" */);
				
			}else {
				
				if(at!=null&& at.getFileNo()!=0) {
					new File(savePath+at.getChangeName()).delete();
				}
				session.setAttribute("alertMsg", "수정 실패 ㅠㅠ");
				response.sendRedirect(request.getContextPath()/* +"/views/memorials/memorials.jsp" */);
				
				
			}
    
		}
	}

}
