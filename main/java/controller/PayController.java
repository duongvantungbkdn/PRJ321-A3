package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import dao.OrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Orders;

/**
 * servlet PayController: controller của chức năng 
 * lưu thông tin chi tiết đơn hàng và khách hàng vào datasource
 */
public class PayController extends HttpServlet {
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
			HttpSession session = request.getSession(true);
			if(session.getAttribute("cart") != null) {				
				Cart c = (Cart) session.getAttribute("cart");
				String email = request.getParameter("email");
				String discount = request.getParameter("discount");
				String address = request.getParameter("address");
				String phone = request.getParameter("phone");
				
				// check if user login then update phone and address to Account
				boolean isLogin = false;
				if(session.getAttribute("user") != null) {
					isLogin = true;
				}
				
				Orders od = new Orders(2, address, phone, email, discount, null);					
				OrdersDAO dao = new OrdersDAO();
				dao.insertOrder(od, c, isLogin);
				
				// send cart and order to request scope and delete cart on session
				request.setAttribute("cart", c);
				request.setAttribute("order", od);
				session.setAttribute("cart", null);
				request.getRequestDispatcher("WEB-INF/paySuccess.jsp").forward(request, response);
			} else {
				response.sendRedirect("ListController");	
			}			
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}
