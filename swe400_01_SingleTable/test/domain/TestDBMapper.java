package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.DBMapper;

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
		public MockDBMapper(String upc, int manufacturerID, int price, String className) throws ClassNotFoundException, SQLException 
		{
			super(upc,manufacturerID, price, className);
		}
		public MockDBMapper()
		{
			
		}
	}
	
	/**
	 * Tests creation of DBMapper 
	 *  
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreationWithRichConstructor() throws ClassNotFoundException, SQLException 
	{
		MockDBMapper mapper = new MockDBMapper(null, 0, 0, null);
		assertTrue(mapper instanceof DBMapper);
		assertEquals(null, mapper.getUpc());
		assertEquals(0, mapper.getManufacturerID());
		assertEquals(0, mapper.getPrice());
		assertEquals(null, mapper.getClassName());
	}
	
	@Test
	public void testEmptyConstructor()
	{
		MockDBMapper mapper = new MockDBMapper();
		assertTrue(mapper instanceof DBMapper);
	}
	
	/**
	 * Tests setters in DBMapper
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetters() throws ClassNotFoundException, SQLException
	{
		MockDBMapper mapper = new MockDBMapper(null, 0, 0, null);
		
		assertEquals(null, mapper.getUpc());
		assertEquals(0, mapper.getManufacturerID());
		assertEquals(0, mapper.getPrice());
		assertEquals(null, mapper.getClassName());
		
		mapper.setClassName("Tool");
		mapper.setManufacturerID(30);
		mapper.setPrice(2);
		mapper.setUpc("0121232234");
		mapper.setId();
		
		assertEquals("0121232234", mapper.getUpc());
		assertEquals(30, mapper.getManufacturerID());
		assertEquals(2, mapper.getPrice());
		assertEquals("Tool", mapper.getClassName());
		assertEquals(6, mapper.getId());
	}
}