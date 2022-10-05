package controller.product;

import java.io.IOException;
import java.io.PrintWriter;

import dao.AccountDAO;
import dao.ListProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ResultSQLProducts;
import model.User;

/**
 * servlet ListController: controller của chức năng hiển thị 
 * thông tin sản phẩm trong data source
 */
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// get parameter "action"
		String action = request.getParameter("action");
		String from = request.getParameter("from"); // where is this request from
		
		//get user from session
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		try {			
			
			if (action != null && action.equals("login")) {
				if(user == null) {
					request.setAttribute("from", from); // send from to page login
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}
			} else if (action != null && action.equals("register")) {
				if(user == null) {
					request.setAttribute("from", from); // send from to page register
					request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}
			} else if (action != null && action.equals("pay")) {
				// check if user login then get user's updated information from DB and send to session
				if(user != null) {
					AccountDAO dao = new AccountDAO();
					User userDB = dao.getUserByMail(user.getEmail());
					// create new user and hide password
					User userClient = new User(userDB.getEmail(), userDB.getName(), userDB.getPhone(), userDB.getAddress(), userDB.getRole());
					session.setAttribute("user", userClient);
				}
				request.getRequestDispatcher("WEB-INF/pay.jsp").forward(request, response);
			} else {
				int pageNum = 1; // default
				int pageSize = 6;
				String pageStr = request.getParameter("page");
				if(pageStr != null) {
					pageNum = Integer.parseInt(pageStr);
				}
				ListProductDAO dao = new ListProductDAO();
				ResultSQLProducts result = dao.getProducts(pageNum,pageSize);
				
				if(result != null) {
					request.setAttribute("products", result.getProducts());	
					request.setAttribute("pageNum", result.getPageNumber());
					request.setAttribute("maxPage", result.getMaxPage());
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			out.println(e);
		}
	}

}
