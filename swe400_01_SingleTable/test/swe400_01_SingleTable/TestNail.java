/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

}
