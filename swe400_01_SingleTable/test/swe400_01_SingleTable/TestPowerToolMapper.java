/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domain_layer.PowerToolMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 *Tests PowerToolMapper 
 */
public class TestPowerToolMapper 
{
	/**
	 * Tests creation of powerToolMapper  
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException 
	{
		PowerToolMapper ptm = new PowerToolMapper(null, 0, 0, null, false, null);
		assertEquals(null, ptm.getUpc());
		assertEquals(0, ptm.getManufacturerID());
		assertEquals(0, ptm.getPrice());
		assertEquals(null, ptm.getDescription());
		assertFalse(ptm.isBatteryPowered());
		assertNull(ptm.getClassName());
	}
}