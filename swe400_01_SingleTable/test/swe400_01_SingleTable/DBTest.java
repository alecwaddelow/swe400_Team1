package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Drew Rife
 *
 */
public abstract class DBTest
{

	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";

	static DatabaseGateway gateway;

	public DBTest() throws ClassNotFoundException, SQLException
	{
		gateway = new DatabaseGateway();
	}

	@Before
	public static void testStartTransaction() throws ClassNotFoundException, SQLException
	{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection(hostName, user, password);
		String sqlStatement = ("START TRANSACTION;");
		Statement st = gateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		//con.close();
	}

	@After
	public static void testRollBack() throws ClassNotFoundException, SQLException
	{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection(hostName, user, password);
		String sqlStatement = ("ROLLBACK;");
		Statement st = gateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
//		con.close();
	}





}
