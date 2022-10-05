package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.User;

// lớp AccountDAO chứa các phương thức làm việc vơi bảng Account
public class AccountDAO {
	
	//return the list of User by email
	public List<User> searchUserByMail(String search) throws Exception {
		// create a list contain result
		List<User> list = new ArrayList<>();
		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		if(con == null) {
			System.out.println("connect to DB FAILED on searchUserByMail of class AccountDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on searchUserByMail of class AccountDAO");
			String sql = "select * from Account where user_mail like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + search + "%");
			ResultSet rs = stmt.executeQuery();
			
			//process result
			while(rs.next()) {
				String mail = rs.getString("user_mail");
				String pass = rs.getString("password");
				String name = rs.getString("user_name");
				int role = rs.getInt("account_role");
				String address = rs.getString("user_address");
				String phone = rs.getString("user_phone");
				
				User user = new User(mail, name, phone, address, pass, role);
				list.add(user); 
			}
		}
		
		con.close(); // close connection
		
		return list; // return result
	}
	
	//return User by email
	public User getUserByMail(String mail) throws Exception {		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		if(con == null) {
			System.out.println("connect to DB FAILED on getUserByMail of class AccountDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on getUserByMail of class AccountDAO");
			String sql = "select * from Account where user_mail = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mail);
			ResultSet rs = stmt.executeQuery();
			
			//process result
			while(rs.next()) {
				String pass = rs.getString("password");
				String name = rs.getString("user_name");
				int role = rs.getInt("account_role");
				String address = rs.getString("user_address");
				String phone = rs.getString("user_phone");		
				
				User user = new User(mail, name, phone, address, pass, role);
				
				return user; 
			}
		}
		
		con.close(); // close connection
		
		return null; // return null if not found
	}
	
	//insert account to DB
	public void insertUser(User user) throws Exception {		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		if(con == null) {
			System.out.println("connect to DB FAILED on insertUser of class AccountDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on insertUser of class AccountDAO");
			String sql = "insert into Account(user_mail, password, account_role, user_name, user_address, user_phone)"
					+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPass());
			stmt.setInt(3, user.getRole());
			stmt.setString(4, user.getName());
			stmt.setString(5, user.getAddress());
			stmt.setString(6, user.getPhone());
			
			stmt.execute();
		}
		
		con.close(); // close connection
	}
}
