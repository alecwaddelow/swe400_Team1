package runner;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

import data_source.*;
import domain_layer.*;
import userInput.UserInput;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 * Runner queries the database to find keys and builds an array list of all the objects
 */
public class Runner
{
	static ArrayList<InventoryItem> listOfObjects = new ArrayList<InventoryItem>();
	
	/**
	 * Creates the table and calls for the insertion of the objects into the table
	 * 
	 * @param args
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
		CreateLinkTable.dropTableBeforeCreation();
		CreateDatabase.dropTableBeforeCreation();
		CreateDatabase.createTable();
		CreateDatabase.insertNailsIntoTable();
		CreateDatabase.insertToolsIntoTable();
		CreateDatabase.insertStripNailsIntoTable();
		CreateDatabase.insertPowerToolsIntoTable();
		CreateLinkTable.createTable();
		CreateLinkTable.createRelationships();
		initiateUserInput();
		
		/* program has finished so close the connection */
		LinkTableGateway.closeConnection();
		DatabaseGateway.closeConnection();
	}
	
	/**
	 * Begins user input prompts 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void initiateUserInput() throws ClassNotFoundException, SQLException
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
	 */
	public static void printDetailsOfItem(InventoryItem item) throws ClassNotFoundException, SQLException 
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
			ArrayList<StripNail> stripList = powerTool.getStripNailList();
			
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
			ArrayList<PowerTool> powerToolList = stripNail.getPowerToolList();
			
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
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<InventoryItem> createList() throws ClassNotFoundException, SQLException
	{
		try(ResultSet rSet = DatabaseGateway.createList())
		{
			for(int i = 0; rSet.next(); i++)
			{
				int id  = rSet.getRow();
				String className = rSet.getString("className");
				listOfObjects.add(i, InventoryItem.matchClassAndConstruct(id, className));
			}
			rSet.close();
			DatabaseGateway.closeStatements();
			return listOfObjects;
		}
		catch(MySQLDataException e)
		{
			e.getCause();
		}
		return null;
	}

	/**
	 * Getter for dynamic creation of list
	 *
	 * @return list of objects
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<InventoryItem> getList() throws ClassNotFoundException, SQLException
	{
		listOfObjects = createList();
		return listOfObjects;
	}
}