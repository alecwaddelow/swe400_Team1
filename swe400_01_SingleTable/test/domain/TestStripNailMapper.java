package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.StripNailMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * Test StripNailsMapper
 */
public class TestStripNailMapper 
{

	/**
	 * Tests creation of StripNailsMapper 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException
	{
		StripNailMapper snm = new StripNailMapper("101", 0, 0, 5.0, 1, "StripNailsMapper");
		assertEquals("101", snm.getUpc());
		assertEquals(0, snm.getManufacturerID());
		assertEquals(0, snm.getPrice());
		assertEquals(5.0, snm.getLength(), 0.001);
		assertEquals(1, snm.getNumberInStrip());
		assertEquals("StripNailsMapper", snm.getClassName());
	} 
	
	/**
	 * Tests setters of StripNailsMapper
	 */
	@Test
	public void testSetters()
	{
		StripNailMapper stripNailsMapper = new StripNailMapper();
		stripNailsMapper.setUpc("444");
		stripNailsMapper.setManufacturerID(21);
		stripNailsMapper.setPrice(40);
		stripNailsMapper.setLength(4.5);
		stripNailsMapper.setNumberInStrip(1);
		stripNailsMapper.setClassName("Mapper");
		
		assertEquals("444", stripNailsMapper.getUpc());
		assertEquals(21, stripNailsMapper.getManufacturerID());
		assertEquals(40, stripNailsMapper.getPrice());
		assertEquals(4.5, stripNailsMapper.getLength(), 0.001);
		assertEquals(1, stripNailsMapper.getNumberInStrip());
		assertEquals("Mapper", stripNailsMapper.getClassName());
	}
}