package swe400_01_SingleTable;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import data_source.LinkTableGateway;
import domain_layer.LinkTableMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 *Test LinkTableMapper 
 */
public class TestLinkTableMapper 
{

	/**
	 * Tests creation of LinkTableMapper
	 */
	@Test
	public void testCreation() 
	{
		LinkTableMapper ltm = new LinkTableMapper();
		
		ltm.setLinkID(1);
		ltm.setPowerToolID(2);
		ltm.setStripNailID(3);
		assertTrue(ltm instanceof LinkTableMapper);
		assertEquals(1, ltm.getLinkID());
		assertEquals(2, ltm.getPowerToolID());
		assertEquals(3, ltm.getStripNailID());
	}
	
	/**
	 * Tests removing a relation from LinkTableMapper
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void removeRelation() throws ClassNotFoundException, SQLException
	{
		LinkTableMapper.removeRelation(20, 14);
		assertFalse(LinkTableGateway.queryDBForPowerTools(20).next());
	}
}