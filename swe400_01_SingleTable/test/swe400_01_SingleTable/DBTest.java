package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author Drew Rife
 *
 */
public abstract class DBTest
{

	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";


	@Before
	@Test
	public void testStartTransaction() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		String sqlStatement = ("START TRANSACTION;");
		Statement st = (Statement) con.createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		con.close();
	}

	@After
	@Test
	public void testRollBack() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		String sqlStatement = ("ROLLBACK;");
		Statement st = (Statement) con.createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		con.close();
	}





}
