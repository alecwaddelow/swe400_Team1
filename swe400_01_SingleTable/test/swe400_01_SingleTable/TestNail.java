/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author drew
 *
 */
public class TestNail
{
	/**
	 * Test initializing a Nail Object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Before
	@Test
	public void testInitialization() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(1);
		assertEquals("123", nail.upc);
		assertEquals(445, nail.manufacturerID);
		assertEquals(10, nail.price);
		assertEquals(10, nail.length);
		assertEquals(10, nail.numberInBox);
	}

	//@Before
	@Test
	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(2, "333", 444, 5, "ThisJawn", false, 0, 0, 0, "Nail");
		assertEquals("333", nail.upc);
		assertEquals(444, nail.manufacturerID);
		assertEquals(5, nail.price);
		assertEquals(0, nail.length);
		assertEquals(0, nail.numberInBox);
	}

	@Test
	public void testPullingFromDB() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(2);
		assertEquals("333", nail.upc);

	}


















}
