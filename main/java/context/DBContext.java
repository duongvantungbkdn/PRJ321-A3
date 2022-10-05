package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private final String serverName = "localhost";
	private final String dbName = "ShoppingDB";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String pass = "123456";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName +":" 
				+ portNumber + "\\" + instance + ";databaseName=" + dbName 
				+ ";encrypt=true;trustServerCertificate=true";
		if(instance == null || instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName +":" 
					+ portNumber + ";databaseName=" + dbName
					+ ";encrypt=true;trustServerCertificate=true";
		}
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		return DriverManager.getConnection(url,userID,pass);
	}
	
}
