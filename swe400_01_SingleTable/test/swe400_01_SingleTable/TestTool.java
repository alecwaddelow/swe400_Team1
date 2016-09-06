/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/**
 * Test Tool Class
 * @author Alec Waddelow
 *
 */
public class TestTool extends DBTest
{
	@Test
	public void testInitialization() throws ClassNotFoundException, SQLException
	{
		Tool tool = new Tool(3);
		assertEquals("1234", tool.upc);
		assertEquals(12345, tool.manufacturerID);
		assertEquals(15, tool.price);
		assertEquals("A simple tool", tool.description);
	}

//	@Test
//	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
//	{
//		Tool tool = new Tool(5, "1234", 12345, 20, "This is a tool", false, 0, 0, 0, "Tool");
//		assertEquals("1234", tool.upc);
//		assertEquals(12345, tool.manufacturerID);
//		assertEquals(20, tool.price);
//		assertEquals("This is a tool", tool.description);
//
//	}

	@Test
	public void testPullingFromDB() throws ClassNotFoundException, SQLException
	{
		Tool tool = new Tool(3);
		assertEquals("1234", tool.upc);
	}
}
