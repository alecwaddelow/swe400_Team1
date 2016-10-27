/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import org.junit.Test;

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
}
