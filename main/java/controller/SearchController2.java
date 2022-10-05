package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.ListProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ResultSQLProducts;

/**
 * Lớp SearchController: controller của chức năng tìm kiếm product 
 */
public class SearchController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String search = request.getParameter("search");
		String category = request.getParameter("category");

		try {
			int pageNum = 1; // default
			int pageSize = 6;
			String pageStr = request.getParameter("page");
			if(pageStr != null) {
				pageNum = Integer.parseInt(pageStr);
			}
			ListProductDAO dao = new ListProductDAO();
			ResultSQLProducts result = dao.searchProduct(search,category,pageNum,pageSize);
			
			if(result != null) {
				request.setAttribute("products", result.getProducts());	
				request.setAttribute("pageNum", result.getPageNumber());
				request.setAttribute("maxPage", result.getMaxPage());
				request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
			}
		} catch (Exception e) {
			out.println(e);
		}
	}
}
