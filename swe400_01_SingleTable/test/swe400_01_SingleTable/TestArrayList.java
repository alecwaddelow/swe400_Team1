/**
z
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import enums.Nails;
import enums.Tools;

/**
 * @author Alec Waddelow & Drew Rife
 *	Tests for creating a new ArrayList of objects pulled from the database
 */
public class TestArrayList
{
	private static int uniqueTestID = 1;
	private static int indexOfArrayList = 0;

	/**
	 * Tests getting the nail objects from the array list
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrieveNailsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<Object> returnSet = new ArrayList<Object>();
		returnSet = Runner.createList();

		for(int i = 0; i < Nails.values().length; i++)
		{
			Nail n = Nail.class.cast(returnSet.get(indexOfArrayList));
			assertEquals(uniqueTestID, n.getId());
			assertEquals(Nails.values()[indexOfArrayList].getUpc(), n.getUpc());
			assertEquals(Nails.values()[indexOfArrayList].getManufacturerID(), n.getManufacturerID());
			assertEquals(Nails.values()[indexOfArrayList].getLength(), n.getLength(), 0.001);
			assertEquals(Nails.values()[indexOfArrayList].getPrice(), n.getPrice());
			assertEquals(Nails.values()[indexOfArrayList].getNumberInBox(), n.getNumberInBox());
			indexOfArrayList++;
			uniqueTestID++;
		}
	}

	@Test
	public void testRetrieveToolsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<Object> returnSet = new ArrayList<Object>();
		returnSet = Runner.createList();

		for(int i = 0; i < Tools.values().length; i++)
		{
			Tool t = Tool.class.cast(returnSet.get(indexOfArrayList));
			assertEquals(uniqueTestID, t.getId());

		}
	}







}
