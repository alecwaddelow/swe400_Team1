/**
z
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import enums.Nails;
import enums.PowerTools;
import enums.StripNails;
import enums.Tools;

/**
 * @author Alec Waddelow & Drew Rife
 *	Tests for creating a new ArrayList of objects pulled from the database
 */
public class TestArrayList
{
	private static int uniqueTestID = 1;
	private static int indexOfArrayList = 0;

	@Test
	public void testInventoryItemsInArrayList() throws ClassNotFoundException, SQLException, NamingException
	{
		Runner.main(null);
		testRetrieveNailsFromArrayList();
		testRetrieveToolsFromArrayList();
		testRetrieveStripNailsFromArrayList();
		testRetrievePowerToolsFromArrayList();
	}

	/**
	 * Tests getting the nail objects from the array list
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testRetrieveNailsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> returnSet = new ArrayList<InventoryItem>();
		returnSet = Runner.createList();

		for(int i = 0; i < Nails.values().length; i++)
		{
			InventoryItem item = returnSet.get(indexOfArrayList);

			assertEquals(uniqueTestID, item.getId());
			assertEquals(Nails.values()[i].getUpc(), item.getUpc());
			assertEquals(Nails.values()[i].getManufacturerID(), item.getManufacturerID());
			assertEquals(Nails.values()[i].getPrice(), item.getPrice());
			indexOfArrayList++;
			uniqueTestID++;
		}
	}

	/**
	 * Tests getting the Tools objects from the array list
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testRetrieveToolsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> returnSet = new ArrayList<InventoryItem>();
		returnSet = Runner.createList();

		for(int i = 0; i < Tools.values().length; i++)
		{
			InventoryItem item = returnSet.get(indexOfArrayList);

			assertEquals(uniqueTestID, item.getId());
			assertEquals(Tools.values()[i].getUpc(), item.getUpc());
			assertEquals(Tools.values()[i].getManufacturerID(), item.getManufacturerID());
			assertEquals(Tools.values()[i].getPrice(), item.getPrice());
			indexOfArrayList++;
			uniqueTestID++;
		}
	}


	/**
	 * Tests getting the Strip Nails objects from the array list
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testRetrieveStripNailsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> returnSet = new ArrayList<InventoryItem>();
		returnSet = Runner.createList();

		for(int i = 0; i < StripNails.values().length; i++)
		{
			InventoryItem item = returnSet.get(indexOfArrayList);

			assertEquals(uniqueTestID, item.getId());
			assertEquals(StripNails.values()[i].getUpc(), item.getUpc());
			assertEquals(StripNails.values()[i].getManufacturerID(), item.getManufacturerID());
			assertEquals(StripNails.values()[i].getPrice(), item.getPrice());
			indexOfArrayList++;
			uniqueTestID++;
		}
	}

	/**
	 * Tests getting the Strip Nails objects from the array list
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testRetrievePowerToolsFromArrayList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> returnSet = new ArrayList<InventoryItem>();
		returnSet = Runner.createList();

		for(int i = 0; i < PowerTools.values().length; i++)
		{
			InventoryItem item = returnSet.get(indexOfArrayList);

			assertEquals(uniqueTestID, item.getId());
			assertEquals(PowerTools.values()[i].getUpc(), item.getUpc());
			assertEquals(PowerTools.values()[i].getManufacturerID(), item.getManufacturerID());
			assertEquals(PowerTools.values()[i].getPrice(), item.getPrice());
			indexOfArrayList++;
			uniqueTestID++;
		}
	}
}
