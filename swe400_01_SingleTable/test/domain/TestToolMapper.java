package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.ToolMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class TestToolMapper 
{
	
	/**
	 * Tests creation of ToolMapper 
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException 
	{
		ToolMapper tm = new ToolMapper(null, 0, 0, null, null);
		assertEquals(null, tm.getUpc());
		assertEquals(0, tm.getManufacturerID());
		assertEquals(0, tm.getPrice());
		assertEquals(null, tm.getDescription());
		assertEquals(null, tm.getClassName());
	}
	
	/**
	 * Tests the setters in tool mapper
	 */
	@Test
	public void testSetters()
	{
		ToolMapper tMapper = new ToolMapper();
		tMapper.setUpc("30");
		tMapper.setManufacturerID(40);
		tMapper.setPrice(30);
		tMapper.setDescription("test");
		tMapper.setClassName("className");
		
		assertEquals("30", tMapper.getUpc());
		assertEquals(40, tMapper.getManufacturerID());
		assertEquals(30, tMapper.getPrice());
		assertEquals("test", tMapper.getDescription());
		assertEquals("className", tMapper.getClassName());
	}
	
	@Test
	public void testUpdateTool() throws ClassNotFoundException, SQLException
	{
		Tool tool = new Tool(null, 0, 0, null, null);
		ToolMapper tm = new ToolMapper();
		tm.updateTool(tool);
		
		assertEquals(null, tm.getClassName());
		assertEquals(0, tm.getManufacturerID());
		assertEquals(0, tm.getPrice());
		assertEquals(null, tm.getDescription());
		assertEquals(null, tm.getUpc());
	}
}