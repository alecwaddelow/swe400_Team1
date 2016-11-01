/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domain_layer.PowerTool;
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
	
	/**
	 * Tests setters of PowerToolMapper
	 */
	@Test
	public void testSetters()
	{
		PowerToolMapper powerToolMapper = new PowerToolMapper();
		
		powerToolMapper.setUpc("568");
		powerToolMapper.setManufacturerID(15);
		powerToolMapper.setPrice(30);
		powerToolMapper.setDescription("description");
		powerToolMapper.setBatteryPowered(true);
		powerToolMapper.setClassName("PowerTool");
		
		assertEquals("568", powerToolMapper.getUpc());
		assertEquals(15, powerToolMapper.getManufacturerID());
		assertEquals(30, powerToolMapper.getPrice());
		assertEquals("description", powerToolMapper.getDescription());
		assertTrue(powerToolMapper.isBatteryPowered());
		assertEquals("PowerTool", powerToolMapper.getClassName());
	}
}