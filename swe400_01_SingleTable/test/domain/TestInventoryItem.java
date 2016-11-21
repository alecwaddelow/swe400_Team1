package domain;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import exceptions.ItemNotFoundException;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class TestInventoryItem 
{

	public class MockInventoryItem extends InventoryItem
	{
		public MockInventoryItem(String UPC, int ManufacturerID, int price, String className)
		{
			super(UPC, ManufacturerID, price, className);
		}
		
		public MockInventoryItem(int id)
		{
			super(id);
		}
		
		public MockInventoryItem()
		{
			super();
		}
	}
	
	/**
	 * Tests Creation Constructor 
	 */
	@Test
	public void testCreationConstructor()
	{
		MockInventoryItem item = new MockInventoryItem("101", 1, 10, "Item");
		assertTrue(item instanceof InventoryItem);
		assertEquals("101", item.getUpc());
		assertEquals(1, item.getManufacturerID());
		assertEquals(10, item.getPrice());
		assertEquals("Item", item.getClassName());
	}
	
	/**
	 * Tests Finder Constructor 
	 */
	@Test
	public void testFinderConstructor()
	{
		MockInventoryItem item = new MockInventoryItem(1);
		assertEquals(1, item.getId());
	}
	
	/**
	 * Tests empty constructor 
	 */
	@Test
	public void testEmptyConstructor()
	{
		MockInventoryItem item = new MockInventoryItem();
		assertTrue(item instanceof InventoryItem);
	}
	
	/**
	 * Tests getters and setters  
	 */
	@Test
	public void testGettersAndSetters()
	{
		MockInventoryItem item = new MockInventoryItem();
		item.setId(1);
		item.setUpc("101");
		item.setManufacturerID(1);
		item.setClassName("item");
		item.setPrice(1);
		
		assertEquals(1, item.getId());
		assertEquals("101", item.getUpc());
		assertEquals(1, item.getManufacturerID());
		assertEquals(1, item.getPrice());
		assertEquals("item", item.getClassName());
	}
	
	/**
	 * Tests matchClassAndConstruct
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testMatchClassAndConstruct() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		InventoryItem item = InventoryItem.matchClassAndConstruct(1, "Nail");
		assertTrue(item instanceof Nail);
		
		item = InventoryItem.matchClassAndConstruct(6, "Tool");
		assertTrue(item instanceof Tool);
		
		item = InventoryItem.matchClassAndConstruct(11, "StripNail");
		assertTrue(item instanceof StripNail);
		
		item = InventoryItem.matchClassAndConstruct(16, "PowerTool");
		assertTrue(item instanceof PowerTool);
	}
	
	/**
	 * Tests getDetails
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test 
	public void testGetDetails() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		InventoryItem item = InventoryItem.getDetails("5453432767", "Nail");
		assertTrue(item instanceof Nail);
		
		item = InventoryItem.getDetails("0121232234", "Tool");
		assertTrue(item instanceof Tool);
		
		item = InventoryItem.getDetails("5453432345", "StripNail");
		assertTrue(item instanceof StripNail);
		
		item = InventoryItem.getDetails("1231231234", "PowerTool");
	}
	
	/**
	 * Asserts that when a bad UPC is passed in, a null item is returned 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testGetDetailsBadUPC() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		assertNull(InventoryItem.getDetails("10101010133", "Nail"));
	}
	
	/**
	 * Asserts that a when invalid data passed to matchClassAndConstruct
	 * a ClassNotFoundException is thrown 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test(expected= ClassNotFoundException.class)
	public void testMatchClassAndConstructBadClassName() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		InventoryItem.matchClassAndConstruct(1, "");
	}
}
