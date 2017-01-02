package other;
import java.sql.*;
import org.junit.After;
import org.junit.Before;

import data_source.ConnectionManager;
import data_source.DatabaseGateway;
import data_source.LinkTableGateway;

/**
 * @author Drew Rife and Alec Waddelow
 *
 * Creates Start Transaction statement before tests and rolls back when done testing
 */
public abstract class DBTest
{

	/**
	 * Add a Start Transaction statement before the tests
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Before
	public void testStartTransaction() throws ClassNotFoundException, SQLException
	{		
		ConnectionManager.getConnection().setAutoCommit(false);
	}

	/**
	 * Rollback when we are done with our tests so changes don't get committed to the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@After
	public void testRollBack() throws ClassNotFoundException, SQLException
	{		
		ConnectionManager.getConnection().rollback();
		
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement("ALTER TABLE InventoryItem AUTO_INCREMENT = 1");
		preparedStatement.execute();
		preparedStatement.close();
	}
}