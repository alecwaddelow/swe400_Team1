package data_source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager 
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12?useSSL=false";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	/**
	 * established a connection to the database and returns it
	 * 
	 * @return connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(connection == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(hostName, user, password);
			return connection;
		}
		else
		{
			return connection;
		}
	}
	
	/**
	 * Closes the connection at the end of the program
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException
	{
		if(connection != null)
		{
			if(!connection.isClosed())
			{
				connection.close();
			}
		}
	}
}
