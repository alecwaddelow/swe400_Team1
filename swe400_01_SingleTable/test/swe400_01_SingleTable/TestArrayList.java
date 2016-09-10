/**
z
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import enums.Nails;

/**
 * @author Alec Waddelow & Drew Rife
 *	Tests for creating a new ArrayList of objects pulled from the database
 */
public class TestArrayList
{
	private static int uniqueTestID = 1;
	/**
	 * Tests creation of the ArrayList from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrieveEnumNail() throws ClassNotFoundException, SQLException
	{
		ArrayList<Object> returnSet = new ArrayList<Object>();
		returnSet = Runner.createList();

		for(int i = 0; i < Nails.values().length; i++)
		{
			Nail n = Nail.class.cast(returnSet.get(i));
			assertEquals(uniqueTestID, n.getId());
			assertEquals(Nails.values()[i].getUpc(), n.getUpc());
			assertEquals(Nails.values()[i].getManufacturerID(), n.getManufacturerID());
			assertEquals(Nails.values()[i].getLength(), n.getLength(), 0.001);
			assertEquals(Nails.values()[i].getPrice(), n.getPrice());
			assertEquals(Nails.values()[i].getNumberInBox(), n.getNumberInBox());
			uniqueTestID++;
		}
	}

}
