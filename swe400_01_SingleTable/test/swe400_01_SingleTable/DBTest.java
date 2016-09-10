package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Drew Rife and Alec Waddelow
 *
 */
public abstract class DBTest
{

	/**
	 * Add a Start Transaction statement before the tests
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Before
	public void testStartTransaction() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("START TRANSACTION;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
	}

	/**
	 * Rollback when we are done with our tests so changes don't get committed to the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@After
	public void testRollBack() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("ROLLBACK;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
	}
}