package data_source;
import static org.junit.Assert.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import data_source.LinkTableGateway;
import other.DBTest;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * Test LinkTableGateway
 *
 */
public class TestLinkTableGateway extends DBTest
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
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testRemoveRelation() throws ClassNotFoundException, SQLException
	{
		LinkTableGateway.removeRelation(20, 14);
		
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForPowerTools(20);
		assertTrue(listLinkTableDTO.isEmpty());
	}
	
	/**
	 * Test query for StripNails
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testQueryDBForStripNails() throws ClassNotFoundException, SQLException
	{
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForStripNails(16);
		assertEquals(11, listLinkTableDTO.get(0).getStripNailID());
		assertEquals(12, listLinkTableDTO.get(1).getStripNailID());
	}
	
	/**
	 * Test query for PowerTools
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testQueryDBForPowerTools() throws ClassNotFoundException, SQLException
	{
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForPowerTools(11);
		assertEquals(16, listLinkTableDTO.get(0).getPowerToolID());
		assertEquals(17, listLinkTableDTO.get(1).getPowerToolID());
	}
	
	/**
	 * Tests InsertRow
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertRow() throws ClassNotFoundException, SQLException
	{
		PreparedStatement statement = null;
		String sqlStatement = "select * from LinkTable";
		statement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		LinkTableGateway.insertRow(statement);
		assertTrue(statement.isClosed());
	}
	
	/**
	 * tests adding a relation
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testAddRelation() throws ClassNotFoundException, SQLException
	{
		LinkTableGateway.addRelation(21, 12);
		int rowCount = 0;
		PreparedStatement statement = null;
		String sqlStatement = "select * from LinkTable";
		statement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			rowCount++;
		}
		assertEquals(9, rowCount); 
	}
	
	/**
	 * test adding duplicate relations 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testAddRelationDuplicate() throws ClassNotFoundException, SQLException
	{
		LinkTableGateway.addRelation(16, 11);
		int rowCount = 0;
		PreparedStatement statement = null;
		String sqlStatement = "select * from LinkTable";
		statement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			rowCount++;
		}
		assertEquals(8, rowCount);
	}
}