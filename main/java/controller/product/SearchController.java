package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.util.List;

import dao.ListProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 * Lớp SearchController: controller của chức năng tìm kiếm product 
 * dành cho quản trị hệ thống
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String search = request.getParameter("search");
		String category = request.getParameter("category");

		try {			
			
		} catch (Exception e) {
			out.println(e);
		}
	}
}
