package controller;

import java.io.IOException;

import dao.ListProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Product;

/**
 * servlet AddToCartController: Controller của chức năng 
 * thêm một sản phẩm vào giỏ hàng
 */
public class AddToCartController extends HttpServlet {
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
		
		try {
			HttpSession session = request.getSession(true);
						
			// if cart don't exist in session, create cart
			if(session.getAttribute("cart") == null) {
				session.setAttribute("cart", new Cart());
			}
			// take cart from session and add product into cart
			Cart c = (Cart) session.getAttribute("cart");			
			
			String id_str = request.getParameter("id");
			if(id_str != null) {
				int id = Integer.parseInt(id_str); //parse id_str to int
				String action = request.getParameter("action");
				
				if(action != null && action.equalsIgnoreCase("add")) {								
					// get Product has id from DB
					Product p = new ListProductDAO().getProductById(id);				
					c.add(new Product(p.getId(),p.getName(),p.getDescription(),
							p.getSrc(),p.getType(),p.getBrand(),1,p.getPrice()));
				} else if (action != null && action.equalsIgnoreCase("remove")) {
					c.remove(id);
				} else if (action != null && action.equalsIgnoreCase("descrease")) {
					c.descreaseOne(id);
				}
			}
			response.sendRedirect("ListController");
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}

}
