/**
 * Drew Rife and Alec Waddelow
 * 
 * Test class that is to be run at the end for testing closing connections
 */
package data_source;

import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

import data_source.DatabaseGateway;
import data_source.LinkTableGateway;

public class TestCloseConnection 
{
	/**
	 * Tests closing the connection in LinkTableGateway
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testCloseLinkTableGatewayConnection() throws ClassNotFoundException, SQLException
	{
		Connection connection = LinkTableGateway.getConnection();
		LinkTableGateway.closeConnection();
		assertTrue(connection.isClosed());
	}
	
	/**
	 * Tests closing the connection in the DatabaseGateway
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testCloseDatabaseGatewayConnection() throws ClassNotFoundException, SQLException
	{
		Connection connection = DatabaseGateway.getConnection();
		DatabaseGateway.closeConnection();
		assertTrue(connection.isClosed());
	}
}
