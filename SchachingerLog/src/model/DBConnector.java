package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class DBConnector {

	private static Connection con = null;
	private static String dbHost = "localhost";
	private static String dbPort = "3306";      
	private static String dbName = "schachinger_log"; 
	private static String dbUser = "root"; 
	private static String dbPass = "wkA7*ucE#wY#";
	private Logger logger = (Logger) LoggerFactory.getLogger("model.DBConnector");
	 
	public Connection openConnection() {
		try {
	        Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber f�r JDBC Schnittstellen laden.
	 
	        con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);
	    } catch (ClassNotFoundException e) {
	        System.out.println("Treiber nicht gefunden");
	    } catch (SQLException e) {
	        System.out.println("Verbindung nicht moglich");
	        System.out.println("SQLException: " + e.getMessage());
	        System.out.println("SQLState: " + e.getSQLState());
	        System.out.println("VendorError: " + e.getErrorCode());
	        con=null;
	    }
		return con;
	}
	
	public void closeConnection(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
            logger.info("Couldnt close DB-Connection with error: " + e.getMessage());
       }
		}
	
}