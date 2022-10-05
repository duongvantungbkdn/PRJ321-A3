package controller;

import java.io.IOException;
import java.util.List;

import dao.AccountDAO;
import dao.OrdersDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Orders;
import model.User;

/**
 * UserController: phục vu cho việc login, logout
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		String from = request.getParameter("from");
		if(action != null && action.equals("dologin")) {
			login(request,response,from);
		} else if(action != null && action.equals("dologout")) {
			logout(request,response);
		} else if(action != null && action.equals("doregister")) {
			register(request,response,from);
		} else if(action != null && action.equals("showorders")) {
			showorders(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response, String from) 
			throws IOException, ServletException {
		try {
			// string validate email and password
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regexPassword = "[a-zA-Z0-9_!@#$%^&*]+";

			// read username and password from form submit
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String remember = (String) request.getParameter("remember");

			// validate username (email) and password is correct format
			if (!username.matches(regexMail) || !password.matches(regexPassword)) {
				request.setAttribute("errormessage", "Email or password is incorrect format");
				request.setAttribute("from", from); // send from back to remember where command start
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			} else {
				// find user has email=username from database
				AccountDAO accDAO = new AccountDAO();
				User userDB = accDAO.getUserByMail(username);

				// if userDB is existing in DB, check password is matched
				if (userDB != null && userDB.getPass().equals(password)) {
					// get cart from old session
					HttpSession oldSession = request.getSession(false);
					Cart cart = (Cart) oldSession.getAttribute("cart");
					
					// destroy old session
					request.getSession(true).invalidate();
					// create a new session
					HttpSession newSession = request.getSession(true);
					// add cart to new session
					newSession.setAttribute("cart", cart);
					
					// add to session
					User user = new User(userDB.getEmail(), userDB.getName(), userDB.getPhone(), userDB.getAddress(), userDB.getRole());
					newSession.setAttribute("user", user);
					
					// create cookies
					Cookie ckuser = new Cookie("ckuser", username);
					Cookie ckrem = new Cookie("ckrem", remember);
					
					if(remember != null) {
						// if remember me is checked
						ckuser.setMaxAge(60*60*24*7); // lifespan is 7 days
						ckrem.setMaxAge(60*60*24*7); // lifespan is 7 days
					} else {
						// if remember me is not checked
						ckuser.setMaxAge(0); // delete cookie
						ckrem.setMaxAge(0); // delete cookie
					}
					
					// add cookies to response
					response.addCookie(ckuser);
					response.addCookie(ckrem);
					
					// redirect page
					if(from != null && from.equals("pay")) {
						// if login from pay.jsp then come back pay.jsp
						request.getRequestDispatcher("WEB-INF/pay.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("ListController").forward(request, response);
					}
					
				} else {
					request.setAttribute("errormessage", "Username or password is wrong");
					request.setAttribute("from", from); // send from back to remember where command start
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
				}
			}
		} catch (NullPointerException e) {
			request.setAttribute("from", from); // send from back to remember where command start
			RequestDispatcher rd = request.getRequestDispatcher("ListController?action=login");
			response.getWriter().println("<font color='red'>Username or password is invalid</font>");
			rd.include(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response, String from) 
			throws IOException, ServletException {
		try {
			// string validate email and password
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regexPassword = "[a-zA-Z0-9_!@#$%^&*]+";

			// read username and password from form submit
			String email = (String) request.getParameter("email");
			String pass = (String) request.getParameter("pass");
			String name = (String) request.getParameter("name");
			String phone = (String) request.getParameter("phone");
			String address = (String) request.getParameter("address");

			// validate email and password is correct format
			if (!email.matches(regexMail) || !pass.matches(regexPassword)) {
				request.setAttribute("errormessage", "Email or password is incorrect format");
				request.setAttribute("from", from); // send from back to remember where command start
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			} else {
				// get cart from old session
				HttpSession oldSession = request.getSession(false);
				Cart cart = (Cart) oldSession.getAttribute("cart");
				
				// destroy old session
				request.getSession(true).invalidate();
				// create a new session
				HttpSession newSession = request.getSession(true);
				// add cart to new session
				newSession.setAttribute("cart", cart);
				
				// check email is exist in DB
				AccountDAO accDao = new AccountDAO();
				User userDB = accDao.getUserByMail(email);
				
				// if user do not exist in DB
				if(userDB == null) {
					// create new User from param data and insert to DB
					User user = new User(email, name, phone, address, pass, 2);					
					accDao.insertUser(user);
					
					// send user to session
					User user_sesion = new User(email, name, phone, address, user.getRole());
					newSession.setAttribute("user", user_sesion);
					
					if(from != null && from.equals("pay")) {
						request.getRequestDispatcher("WEB-INF/pay.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("ListController").forward(request, response);
					}
					
				} else {
					request.setAttribute("errormessage", "Email is existting");
					request.setAttribute("from", from); // send from back to remember command start
					request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
				}			
			}
		} catch (NullPointerException e) {
			request.setAttribute("from", from); // send from back to remember where command start
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
			response.getWriter().println("<font color='red'>Username or password is invalid</font>");
			rd.include(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}
	
	protected void showorders(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		try {
			// get user from session
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			
			if(user != null) {
				OrdersDAO odDAO = new OrdersDAO();
				List<Orders> listOrders = odDAO.getOrderByMail(user);
				session.setAttribute("listOrders", listOrders);
				
				request.getRequestDispatcher("WEB-INF/listorders.jsp").forward(request, response);
			}  else {
				request.getRequestDispatcher("ListController").forward(request, response);
			}
		} catch (NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("ListController");
			rd.include(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// destroy all current session
		request.getSession(true).invalidate();
		response.sendRedirect("ListController");
	}

}
