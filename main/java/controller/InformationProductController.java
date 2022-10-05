package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import dao.ListProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 * servlet InformationProductController:
 * controller của chức năng xem thông tin chi tiết của một sản phẩm
 */
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		try {
			String id_str = request.getParameter("id");
			if(id_str == null) {
				request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
			} else {
				int id = Integer.parseInt(id_str);
				Product product = new ListProductDAO().getProductById(id);
				request.setAttribute("product", product );
				request.getRequestDispatcher("WEB-INF/infoProduct.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}

}
