package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import domain.PowerTool;
import domain.StripNail;
import enums.StripNails;
import exceptions.ItemNotFoundException;
import other.DBTest;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Test class for StripNail class
 */
public class TestStripNail extends DBTest
{
	int loadCounter = 0;
	
	/**
	 * Mock stripNail class for testing 
	 *
	 */
	public class MockStripNail extends StripNail
	{
		public MockStripNail(int i) throws ClassNotFoundException, SQLException, ItemNotFoundException 
		{
			super(i);
		}
		
		public MockStripNail() 
		{
			super();
		}

		@Override
		public void load() throws ClassNotFoundException, SQLException, ItemNotFoundException
		{
			TestStripNail.this.loadCounter++;
			super.load();
		}
	}
	
	/**
	 * Tests creating a new object StripNail
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testCreationConstructor() throws ClassNotFoundException, SQLException 
	{
		String upc = "1324567890";
		int manufacturerID = 32020;
		int price = 30;
		double length = 20.325;
		int numberInStrip = 24;
		
		StripNail stripNail = new StripNail(upc, manufacturerID, price, length, numberInStrip, "StripNail");
		assertEquals(upc, stripNail.getUpc());
		assertEquals(manufacturerID, stripNail.getManufacturerID());
		assertEquals(price, stripNail.getPrice());
		assertEquals(length, stripNail.getLength(), 0.001);
		assertEquals(numberInStrip, stripNail.getNumberInStrip());
		assertEquals("StripNail", stripNail.getClassName());
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
		StripNail stripNail = new StripNail(11);
		assertEquals(StripNails.ROUND_HEAD_NAIL_STRIP.getUpc(), stripNail.getUpc());
		assertEquals(StripNails.ROUND_HEAD_NAIL_STRIP.getManufacturerID(), stripNail.getManufacturerID());
		assertEquals(StripNails.ROUND_HEAD_NAIL_STRIP.getPrice(), stripNail.getPrice());
		assertEquals(StripNails.ROUND_HEAD_NAIL_STRIP.getLength(), stripNail.getLength(), 0.001);
		assertEquals(StripNails.ROUND_HEAD_NAIL_STRIP.getNumberInStrip(), stripNail.getNumberInStrip());
		assertEquals("StripNail", stripNail.getClassName());
	}
	
	/**
	 * Tests setters 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSetters() throws ClassNotFoundException, SQLException
	{
		StripNail stripNail = new StripNail(null, 0, 0, 0, 0, null);
		stripNail.setUpc("101");
		stripNail.setManufacturerID(10);
		stripNail.setPrice(15);
		stripNail.setLength(10);
		stripNail.setNumberInStrip(10);
		stripNail.setClassName("StripNail");
		
		assertEquals("101", stripNail.getUpc());
		assertEquals(10, stripNail.getManufacturerID());
		assertEquals(15, stripNail.getPrice());
		assertEquals(10.0, stripNail.getLength(), 0.001);
		assertEquals(10, stripNail.getNumberInStrip());
		assertEquals("StripNail", stripNail.getClassName());
	}
	
	/**
	 * Tests catching ClassNotFoundException
	 * 
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	@Test 
	public void testStripNailNotFoundException() throws SQLException, ItemNotFoundException, ClassNotFoundException
	{
		try
		{
			new StripNail(30);
		} 
		catch(ItemNotFoundException notFound)
		{
			assertEquals("Could not find StripNail with specified ID", notFound.getMessage());
		}
	}
	
	/**
	 * Tests toString 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testToString() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		StripNail stripNail = new StripNail(11);
		assertEquals("StripNail [upc=5453432345, manufacturerID=13, price=1099, length=2.5, numberInStrip=50]", stripNail.toString());
	}
	
	/**
	 * Tests getList lazy load method 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testGetList() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		MockStripNail stripNail = new MockStripNail(11);
		ArrayList<PowerTool> myList = stripNail.getPowerToolList();
		
		/*Asserts that load method is called upon invoking getStripNailList()  */
		assertEquals(1, this.loadCounter);			
		
		ArrayList<PowerTool> ptList = new ArrayList<PowerTool>();
		ptList.add(new PowerTool(16));
		ptList.add(new PowerTool(17));
		
		for(int i = 0; i < myList.size(); i++)
		{
			assertEquals(myList.get(i).getUpc(), ptList.get(i).getUpc());
			assertEquals(myList.get(i).getManufacturerID(), ptList.get(i).getManufacturerID());
			assertEquals(myList.get(i).getPrice(), ptList.get(i).getPrice());
			assertEquals(myList.get(i).getDescription(), ptList.get(i).getDescription());
			assertFalse(myList.get(i).isBatteryPowered());
			assertEquals(myList.get(i).getClassName(), ptList.get(i).getClassName());
		}
	}
	
	/**
	 * Tests adding a powerTool to list
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void addPowerToolToList() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		MockStripNail stripNail = new MockStripNail(11);
		PowerTool ptAdd = new PowerTool(16);
		
		stripNail.addPowerToolToList(ptAdd);
		ArrayList<PowerTool> myList = stripNail.getPowerToolList();
		
		PowerTool pt = new PowerTool(16);
		PowerTool pt2 = new PowerTool(17);
		
		ArrayList<PowerTool> toolList = new ArrayList<PowerTool>();
		toolList.add(pt);
		toolList.add(pt2);
		toolList.add(ptAdd);
	
		for(int i = 0; i < myList.size(); i++)
		{
			assertEquals(myList.get(i).getUpc(), toolList.get(i).getUpc());
			assertEquals(myList.get(i).getManufacturerID(), toolList.get(i).getManufacturerID());
			assertEquals(myList.get(i).getPrice(), toolList.get(i).getPrice());
			assertEquals(myList.get(i).getDescription(), toolList.get(i).getDescription());
			assertFalse(myList.get(i).isBatteryPowered());
			assertEquals("PowerTool", myList.get(i).getClassName());
		}
	}
	
	/**
	 * Tests empty constructor 
	 */
	@Test
	public void testEmptySuper()
	{
		MockStripNail test = new MockStripNail();
		assertTrue(test instanceof StripNail);
	}
	
	/**
	 * Tests removing a powerTool
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testRemovePowerTool() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		StripNail stripNail = new StripNail(11);
		ArrayList<PowerTool> powerTools = stripNail.getPowerToolList();
		assertEquals(2, powerTools.size());
		
		PowerTool powerTool = powerTools.get(1);
		stripNail.removePowerToolFromList(powerTool);
		
		powerTools = stripNail.getPowerToolList();
		assertEquals(1, powerTools.size());
	}
	
}