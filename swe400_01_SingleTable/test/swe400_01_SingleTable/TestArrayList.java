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
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRetrieveNailsFromArrayList() throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
		ArrayList<InventoryItem> returnSet = new ArrayList<InventoryItem>();
		returnSet = Runner.createList();

		for(int i = 0; i < Nails.values().length; i++)
		{
			InventoryItem item = returnSet.get(indexOfArrayList);

			assertEquals(uniqueTestID, item.getId());
			assertEquals(Nails.values()[i].getUpc(), item.getUpc());
			assertEquals(Nails.values()[i].getManufacturerID(), item.getManufacturerID());
			assertEquals(Nails.values()[i].getLength(), item.getClass().getField("length").getDouble(item) ,0.001);
			assertEquals(Nails.values()[i].getPrice(), item.getPrice());
			assertEquals(Nails.values()[indexOfArrayList].getNumberInBox(), item.getClass().getField("numberInBox").getInt(item));

			indexOfArrayList++;
			uniqueTestID++;
		}
	}

//	@Test
//	public void testRetrieveToolsFromArrayList() throws ClassNotFoundException, SQLException
//	{
//		ArrayList<Object> returnSet = new ArrayList<Object>();
//		returnSet = Runner.createList();
//
//		for(int i = 0; i < Tools.values().length; i++)
//		{
//			Tool t = Tool.class.cast(returnSet.get(indexOfArrayList));
//			assertEquals(uniqueTestID, t.getId());
//
//		}
//	}







}
