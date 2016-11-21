package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.Tool;
import enums.Tools;
import exceptions.ItemNotFoundException;
import other.DBTest;

/**
 * @author Drew Rife & Alec Waddelow
 * 
 * Test class for Tool class
 */
public class TestTool extends DBTest
{
	/**
	 * Tests creating a new object PowerTool
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testCreationConstructor() throws ClassNotFoundException, SQLException 
	{
		String upc = "1414141414";
		int manufacturerID = 59;
		int price = 20;
		String description = "This is a test Tool";
		
		Tool tool = new Tool(upc, manufacturerID, price, description, "Tool");
		assertEquals(upc, tool.getUpc());
		assertEquals(manufacturerID, tool.getManufacturerID());
		assertEquals(price, tool.getPrice());
		assertEquals(description, tool.getDescription());
		assertEquals("Tool", tool.getClassName());
	}
	
	/**
	 * Tests finder constructor 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testFinderConstructor() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		Tool tool = new Tool(6);
		assertEquals(Tools.HAMMER.getUpc(), tool.getUpc());
		assertEquals(Tools.HAMMER.getManufacturerID(), tool.getManufacturerID());
		assertEquals(Tools.HAMMER.getPrice(), tool.getPrice());
		assertEquals(Tools.HAMMER.getDescription(), tool.getDescription());
		assertEquals("Tool", tool.getClassName());
	}
	
	/**
	 * Tests setters 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSettersAndGetters() throws ClassNotFoundException, SQLException
	{
		Tool tool = new Tool(null, 0, 0, null, null);
		tool.setUpc("101");
		tool.setManufacturerID(10);
		tool.setPrice(15);
		tool.setDescription("test");
		tool.setClassName("Tool");
		
		assertEquals("101", tool.getUpc());
		assertEquals(10, tool.getManufacturerID());
		assertEquals(15, tool.getPrice());
		assertEquals("test", tool.getDescription());
		assertEquals("Tool", tool.getClassName());
	}
	
	/**
	 * Tests catching the ClassNotFoundException
	 * 
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	@Test 
	public void testToolNotFoundException() throws SQLException, ItemNotFoundException, ClassNotFoundException
	{
		try
		{
			new Tool(25);
			
		} catch(ItemNotFoundException notFound)
		{
			assertEquals("Could not find tool with specified ID", notFound.getMessage() );
		}
	}
	
	/**
	 * Tests the toString() method
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testToString() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		Tool tool = new Tool(6);		
		assertEquals("Tool [upc=0121232234, manufacturerID=32, price=899, description=Ball Peen Hammer]", tool.toString());
	}
}