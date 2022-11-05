package loginsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static String url = "jdbc:mysql://localhost:3306/login";    
    private static String username = "user";   
    private static String password = "qweo2123qowiuZ";
    private static Connection conn;
    
    private ConnectionManager() {}
    
    public static Connection getConnection() {
    	try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful!");
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
        return conn;
    }
    
}
