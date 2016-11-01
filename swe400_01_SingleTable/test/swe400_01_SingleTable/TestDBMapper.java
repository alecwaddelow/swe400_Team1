/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domain_layer.DBMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * Test DBMapper 
 */
public class TestDBMapper 
{
	/**
	 * MockDBMapper for testing 
	 *
	 */
	public class MockDBMapper extends DBMapper
	{
		public MockDBMapper(String upc, int manufacturerID, int price, String className) 
		{
			super(upc,manufacturerID, price, className);
		}
		
	}
	
	/**
	 *  Tests creation of DBMapper 
	 */
	@Test
	public void testCreation() 
	{
		MockDBMapper mapper = new MockDBMapper(null, 0, 0, null);
		assertTrue(mapper instanceof DBMapper);
	}
	
	/**
	 * Tests setters in DBMapper
	 */
	@Test
	public void testSetters()
	{
		MockDBMapper mapper = new MockDBMapper(null, 0, 0, null);
		
		assertEquals(null, mapper.getUpc());
		assertEquals(0, mapper.getManufacturerID());
		assertEquals(0, mapper.getPrice());
		assertEquals(null, mapper.getClassName());
		
		mapper.setClassName("Tool");
		mapper.setManufacturerID(30);
		mapper.setPrice(2);
		mapper.setUpc("30303030");
		
		assertEquals("30303030", mapper.getUpc());
		assertEquals(30, mapper.getManufacturerID());
		assertEquals(2, mapper.getPrice());
		assertEquals("Tool", mapper.getClassName());
	}
}
