package domain;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

import domain.Nail;
import enums.Nails;
import other.DBTest;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Test class for the Nail class
 */
public class TestNail extends DBTest
{

	/**
	 * Tests creating a new object Nail
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testCreationConstructor() throws ClassNotFoundException, SQLException
	{
		String upc = "101010101010";
		int manufacturerID = 12;
		int price = 20;
		double length = 10.5;
		int numberInBox = 0;
		
		Nail nail = new Nail(upc, manufacturerID, price, length, numberInBox, "Nail");
		assertEquals(upc, nail.getUpc());
		assertEquals(manufacturerID, nail.getManufacturerID());
		assertEquals(price, nail.getPrice());
		assertEquals(length, nail.getLength(), 0.001);
		assertEquals(numberInBox, nail.getNumberInBox());
		assertEquals("Nail", nail.getClassName());
	}
	
	/**
	 * Tests finder constructor 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testFinderConstructor() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(1);
		assertEquals(Nails.COMMON_10D.getUpc(), nail.getUpc());
		assertEquals(Nails.COMMON_10D.getManufacturerID(), nail.getManufacturerID());
		assertEquals(Nails.COMMON_10D.getPrice(), nail.getPrice());
		assertEquals(Nails.COMMON_10D.getLength(), nail.getLength(), 0.001);
		assertEquals(Nails.COMMON_10D.getNumberInBox(), nail.getNumberInBox());
		assertEquals("Nail", nail.getClassName());
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
		Nail nail = new Nail(null, 0, 0, 0, 0, null);
		
		nail.setUpc("101");
		nail.setManufacturerID(10);
		nail.setPrice(15);
		nail.setLength(10.5);
		nail.setNumberInBox(5);
		nail.setClassName("Nail");
		
		assertEquals("101", nail.getUpc());
		assertEquals(10, nail.getManufacturerID());
		assertEquals(15, nail.getPrice());
		assertEquals(10.5, nail.getLength(), 0.001);
		assertEquals(5, nail.getNumberInBox());
		assertEquals("Nail", nail.getClassName());
	}
	
	/**
	 * Tests catching ClassNotFoundException
	 * 
	 * @throws SQLException
	 */
	@Test 
	public void testNailNotFoundException() throws SQLException
	{
		try
		{
			new Nail(25);			
		} catch(Exception notFound)
		{
			assertEquals("Could not find nail with specified ID", notFound.getMessage());
		}
	}
	
	/**
	 * Tests toString()
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testToString() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(1);
		assertEquals("Nail [upc=5453432767, manufacturerID=15, price=1348, length=3.0, numberInBox=500]", nail.toString());
	}
	
}