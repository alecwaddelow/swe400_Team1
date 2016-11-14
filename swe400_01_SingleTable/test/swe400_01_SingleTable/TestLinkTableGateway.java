package swe400_01_SingleTable;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import data_source.LinkTableGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * Test LinkTableGateway
 *
 */
public class TestLinkTableGateway 
{
	/**
	 * Tests instantiation of LinkTableGateway 
	 */
	@Test
	public void testInstantiation() 
	{
		LinkTableGateway ltg = new LinkTableGateway();
		assertTrue(ltg instanceof LinkTableGateway);
	}
	
	/**
	 * test remove relation in LinkTable Gateway
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testRemoveRelation() throws ClassNotFoundException, SQLException
	{
		LinkTableGateway.removeRelation(20, 14);
		ResultSet rSet = LinkTableGateway.queryDBForPowerTools(20);
		assertFalse(rSet.next());
	}
}