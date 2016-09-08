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

	@Before
	public void testStartTransaction() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("START TRANSACTION;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
	}

	@After
	public void testRollBack() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("ROLLBACK;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
	}
}