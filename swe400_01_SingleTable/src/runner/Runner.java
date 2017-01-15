package runner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import data_source.*;
import domain.*;
import enums.InsertEnumData;
import exceptions.ItemNotFoundException;
import user_input.UserInput;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 * Runner queries the database to find keys and builds an array list of all the objects
 */
public class Runner
{
	static List<InventoryItem> listOfObjects = new ArrayList<InventoryItem>();
	
	/**
	 * Creates the table and calls for the insertion of the objects into the table
	 * 
	 * @param args
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ItemNotFoundException 
	 */
	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException, ItemNotFoundException
	{
		CreateLinkTable.dropTableBeforeCreation();
		CreateInventoryItemTable.dropTableBeforeCreation();
		CreateInventoryItemTable.createTable();
		InsertEnumData.insertNailsIntoTable();
		InsertEnumData.insertToolsIntoTable();
		InsertEnumData.insertStripNailsIntoTable();
		InsertEnumData.insertPowerToolsIntoTable();
		createList();
		CreateLinkTable.createTable();
		CreateLinkTable.createRelationships();
		initiateUserInput();
		
		/* close the connection when finished */
		ConnectionManager.closeConnection();
	}
	
	/**
	 * Begins user input prompts 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void initiateUserInput() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		UserInput.userInput();
	}

	/**
	 * Calls toString methods on item object after converting to the correct object type
	 * to access the lazy load methods in PowerTool and StripNail
	 * 
	 * @param item
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void printDetailsOfItem(InventoryItem item) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		if(item.getClassName().equals("Nail"))
		{
			System.out.println(item.toString());;
		}
		else if(item.getClassName().equals("Tool"))
		{
			System.out.println(item.toString());
		}
		else if(item.getClassName().equals("PowerTool"))
		{
			PowerTool powerTool = (PowerTool) item;
			System.out.println(powerTool.toString());
			List<StripNail> stripList = powerTool.getStripNailList();
			
			if(!stripList.isEmpty())
			{
				System.out.println("\nWorks with:");
			}
			
			for(StripNail stripNail : stripList) 
			{
				System.out.println(stripNail.toString());
			}
		}
		else if(item.getClassName().equals("StripNail"))
		{
			StripNail stripNail = (StripNail) item;
			System.out.println(stripNail.toString());
			List<PowerTool> powerToolList = stripNail.getPowerToolList();
			
			if(!powerToolList.isEmpty())
			{
				System.out.println("\nWorks with:");
			}
			
			for(PowerTool powerTool : powerToolList) 
			{
				System.out.println(powerTool.toString());
			}
		}
	}
	
	/**
	 * Builds the ArrayList of the objects
	 * 
	 * @return listOfObjects ArrayList<InventoryItem> 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void createList() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		List<InventoryItemDTO> dtoList = InventoryItemGateway.createList();
		
		for(InventoryItemDTO dto : dtoList)
		{
			listOfObjects.add(InventoryItem.matchClassAndConstruct(dto.getId(), dto.getClassName()));
		}
	}

	/**
	 * Getter for dynamic creation of list
	 *
	 * @return list of objects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItem> getList() throws ClassNotFoundException, SQLException
	{
		return listOfObjects;
	}
}