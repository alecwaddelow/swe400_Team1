package other;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import org.junit.Test;
import data_source.CreateInventoryItemTable;
import data_source.CreateLinkTable;
import domain.InventoryItem;
import runner.*;
import enums.InsertEnumData;
import enums.Nails;
import enums.PowerTools;
import enums.StripNails;
import enums.Tools;
import exceptions.ItemNotFoundException;

/**
 * @author Alec Waddelow & Drew Rife
 * 
 *	Tests for creating a new ArrayList of objects pulled from the database
 */
public class TestArrayList
{
	private static int uniqueTestID = 1;
	private static int indexOfArrayList = 0;

	/**
	 * Tests retrieving InventoryItems from the arraylist
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testInventoryItemsInArrayList() throws ClassNotFoundException, SQLException, NamingException, ItemNotFoundException
	{
		CreateLinkTable.dropTableBeforeCreation();
		CreateInventoryItemTable.dropTableBeforeCreation();
		CreateInventoryItemTable.createTable();
		InsertEnumData.insertNailsIntoTable();
		InsertEnumData.insertToolsIntoTable();
		InsertEnumData.insertStripNailsIntoTable();
		InsertEnumData.insertPowerToolsIntoTable();
		Runner.createList();
		CreateLinkTable.createTable();
		CreateLinkTable.createRelationships();
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
		List<InventoryItem> returnSet = Runner.getList();

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
		List<InventoryItem> returnSet = Runner.getList();

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
		List<InventoryItem> returnSet = Runner.getList();

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
		List<InventoryItem> returnSet = Runner.getList();

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