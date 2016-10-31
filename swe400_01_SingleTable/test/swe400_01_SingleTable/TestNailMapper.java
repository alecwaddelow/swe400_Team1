/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domain_layer.NailMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * Test NailMapper 
 *
 */
public class TestNailMapper 
{

	/**
	 * Test creation of NailMapper
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException 
	{
		NailMapper nm = new NailMapper("101", 0, 0, 5.0, 0, "NailMapper");
		assertEquals("101", nm.getUpc());
		assertEquals(0, nm.getManufacturerID());
		assertEquals(0, nm.getPrice());
		assertEquals(5.0, nm.getLength(), 0.001);
		assertEquals(0, nm.getNumberInBox());
		assertEquals("NailMapper", nm.getClassName());
	}
}
