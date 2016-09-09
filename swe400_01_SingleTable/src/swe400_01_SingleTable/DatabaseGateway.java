package swe400_01_SingleTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Drew Rife and Alec Waddelow 
 *
 */
public class DatabaseGateway
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";

	private static Connection con;

	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(hostName, user, password);
			con.setAutoCommit(false);
		}
		else
		{
			return con;			
		}
	}
}
