package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Cart;
import model.Orders;
import model.Product;
import model.ProductOrders;
import model.User;

//OrdersDAO: các phương thức làm việc với bảng Orders
public class OrdersDAO {
	
	
	// insert information of Order to data source, 
	// that including list of products in cart (c) and information of buyer in Orders o
	public void insertOrder(Orders o, Cart c, boolean isLogin) throws Exception {
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		if(con == null) {
			System.out.println("connect to DB FAILED on insertOrder of class OrdersDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on insertOrder of class OrdersDAO");
			String sqlInsertOrders = 
					"insert into Orders(user_mail, order_status, order_date, order_discount_code, order_address) values (?, ?, ?, ?, ?)";
			String sqlInsertOrders_detail = 
					"insert into Orders_detail(order_id, product_id, amount_product, price_product)"
					+ "values (?, ?, ?, ?)";
			String updateAccountByEmail = "update Account set user_address = ?, user_phone = ? WHERE user_mail = ?";
			
			con.setAutoCommit(false); // begin transaction
			
			// prepare to get auto-generated id
			String generatedColumns[] = { "order_id" };
			PreparedStatement stmt1 = con.prepareStatement(sqlInsertOrders, generatedColumns);
			stmt1.setString(1, o.getUserMail());
			stmt1.setInt(2, o.getStatus());
			stmt1.setDate(3, new Date(System.currentTimeMillis()));
			stmt1.setString(4, o.getDiscount());
			stmt1.setString(5, o.getAddress());
			int affectedRows = stmt1.executeUpdate();
			
			int curId = 1;
			if(affectedRows ==0 ) {
				throw new SQLException("Creating user failed, no rows affected.");
			} else {
				// get current id of Orders table
				ResultSet rs1 = stmt1.getGeneratedKeys();
				while(rs1.next()) {
					curId = rs1.getInt(1);
				}
			}			
			
			for(Product p : c.getItems()) {
				System.out.println(p);
				PreparedStatement stmt2 = con.prepareStatement(sqlInsertOrders_detail);
				stmt2.setInt(1, curId); // set order_id = curId
				stmt2.setInt(2, p.getId());
				stmt2.setInt(3, p.getNumber());				
				stmt2.setFloat(4, p.getPrice());
				
				stmt2.execute();
			}	
			
			if(isLogin) {
				PreparedStatement stmt3 = con.prepareStatement(updateAccountByEmail);
				stmt3.setString(1, o.getAddress());
				stmt3.setString(2, o.getPhoneNumber());
				stmt3.setString(3, o.getUserMail());
				stmt3.executeUpdate();
			}
			
			con.commit(); // end transaction
		}
		
		con.close(); // close connection
	}
	
	
	public List<Orders> getOrderByMail(User user) throws Exception {
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		List<Orders> listOrders = new ArrayList<>(); // create a list of orders
		
		if(con == null) {
			System.out.println("connect to DB FAILED on getOrderByMail of class OrdersDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on getOrderByMail of class OrdersDAO");		
			
			String sqlGetOrders = "select * from Orders where user_mail = ?";
			String sqlGetOrders_detail = "select * from Orders_detail where order_id = ?";
			String sqlGetProducts = "select product_name from Products where product_id = ?";
			String sqlGetAccount = "select user_phone from Account where user_mail = ?";
			
			con.setAutoCommit(false); // begin transaction
			
			// get user's phone number from Account by user_mail
			PreparedStatement stmt = con.prepareStatement(sqlGetAccount);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			String userPhone = "";
			while (rs.next()) {
				userPhone = rs.getString("user_phone");
			}
			
			// get information from Orders by user_mail
			PreparedStatement stmt1 = con.prepareStatement(sqlGetOrders);
			stmt1.setString(1, user.getEmail());
			ResultSet rs1 = stmt1.executeQuery();
			
			while(rs1.next()) {
				int orderId = rs1.getInt("order_id");
				int orderStatus = rs1.getInt("order_status");
				java.sql.Date dateSql = rs1.getDate("order_date");
				java.util.Date orderDate = new java.util.Date(dateSql.getTime());
				String orderDiscount = rs1.getString("order_discount_code");
				String orderAddress = rs1.getString("order_address");
				
				// get information from Orders_detail by order_id
				PreparedStatement stmt2 = con.prepareStatement(sqlGetOrders_detail);
				stmt2.setInt(1, orderId);
				ResultSet rs2 = stmt2.executeQuery();
				List<ProductOrders> listPO = new ArrayList<>();
				while(rs2.next()) {
					int productId = rs2.getInt("product_id");
					int productAmount = rs2.getInt("amount_product");
					int productPrice = rs2.getInt("price_product");
					
					// get product_name from Products by product_id
					PreparedStatement stmt3 = con.prepareStatement(sqlGetProducts);
					stmt3.setInt(1, productId);
					ResultSet rs3 = stmt3.executeQuery();
					
					String productName = "";
					while (rs3.next()) {
						productName = rs3.getString("product_name");
					}
					
					//create new ProductOrders
					ProductOrders po = new ProductOrders(orderId, productId, productAmount, productName, productPrice);
					listPO.add(po);
				}
				
				// create an order
				Orders order = new Orders(orderId, 0, orderStatus, orderDate, 
						orderAddress, userPhone, user.getEmail(), listPO, null);
				order.setDiscount(orderDiscount);
				listOrders.add(order);
			}					
			con.commit(); // end transaction
		}
		
		con.close(); // close connection
		return listOrders;
	}
}
