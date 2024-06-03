package com.kh.memorials.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.memorials.model.service.MemorialsService;
import com.kh.memorials.model.vo.Memorials;
import com.kh.memorials.model.vo.MemorialsAttachment;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class RecordInsertController
 */
@WebServlet("/memorialsInsert.me")
public class MemorialsInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemorialsInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			
			String memorialsDate = multiRequest.getParameter("memorialsDate");
			String memorialsTime = multiRequest.getParameter("memorialsTime");
			String[] memorialsParts = multiRequest.getParameterValues("memorialsParts");
			String memorialsContent = multiRequest.getParameter("memorialsContent");
			int memorialsSelfScore = Integer.parseInt(multiRequest.getParameter("MemorialsSelfScore"));
			String mUserId = multiRequest.getParameter("mUserId");
			Memorials m = new Memorials();
			
			m.setMemorialsDate(memorialsDate);
			m.setMemorialsTime(memorialsTime);
			m.setMemorialsParts(memorialsParts);
			m.setMemorialsContent(memorialsContent);
			m.setMemorialsSelfScore(memorialsSelfScore);
			m.setmUserId(mUserId);
			MemorialsAttachment at = null;
			
			if(multiRequest.getOriginalFileName("memorialsImg") != null) {
				
				at = new MemorialsAttachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("memorialsImg"));
				at.setChangeName(multiRequest.getFilesystemName("memorialsImg"));
				at.setFilePath("/resources/uploadFiles/");
			}
			int result = new MemorialsService().insertMemorials(m,at);
			
			HttpSession session = request.getSession();
			
			if(result>0) {
				
				session.setAttribute("alertMsg", "등록완료!");
				response.sendRedirect(request.getContextPath()/* +"/views/memorials/memorials.jsp" */);
				
			}else {
				
				if(at!=null) {
					new File(savePath+at.getChangeName()).delete();
				}
				session.setAttribute("alertMsg", "등록 실패 ㅠㅠ");
				response.sendRedirect(request.getContextPath()/*+"/views/memorials/memorials.jsp"*/);
			}
		}
	}

}
