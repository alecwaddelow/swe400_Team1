package runner;
import java.awt.event.ItemEvent;
import java.awt.geom.Dimension2D;
import java.io.NotActiveException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.naming.NamingException;
import data_source.*;
import domain_layer.*;

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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
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
		userInput();
	}

	/**
	 * Loops to handle user input for both retrieving details of items and adding items to the
	 * database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void userInput() throws ClassNotFoundException, SQLException
	{	
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		
		do
		{
			boolean validUPC = false;
			while(!validUPC)
			{
				if(requestUserToEnterUPC(sc))
					validUPC = true;				
			}			
			
			boolean flag = true;
			
			System.out.println("If you would like to add an item to the database please type (Y/N) ");
			String input = sc.nextLine();
			/* Handles primary user input commands  */
			while(flag)
			{
				
				
				if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("yes"))
				{
					addItemToDB(sc);
					flag = false;
				}
				else if(input.equalsIgnoreCase("N") || input.equalsIgnoreCase("no"))
				{
					flag = false;
				}
				else
				{
					System.out.println("Invalid input, please enter Y or N \n");
					input = sc.nextLine();
				}
				
			}
			
			System.out.println("Would you like to end the program (Y/N)?");
			input = sc.nextLine();
			flag = true;
			while(flag)
			{
				if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
				{
					flag = false;
					run = false;
					sc.close();
				}
				else if(input.equalsIgnoreCase("N") || input.equalsIgnoreCase("no"))
				{
					flag = false;
				}
				else
				{
					System.out.println("Invalid input, please enter Y or N \n");
					input = sc.nextLine();
				}
			}	
			
		}while(run);	
		sc.close();
	}

	/**
	 * Requests the user to enter the UPC of an item
	 * 
	 * @param sc
	 * @return true if the item is not null (exists); false otherwise
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Boolean requestUserToEnterUPC(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Please enter a upc code to see the details of the requested Inventory Item");
		
		String upcCode = sc.nextLine();
		
		InventoryItem item = getDetails(upcCode);
		
		if(item != null)
			printDetailsOfItem(item);
		else
			System.out.println("You did not enter in a valid UPC");
		
		return (item != null) ? true:false;
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
			
			for(PowerTool powerTool : powerToolList) 
			{
				System.out.println(powerTool.toString());
			}
		}
	}
	
	/**
	 * Adds a new item to the database based off of user input 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addItemToDB(Scanner sc) throws ClassNotFoundException, SQLException
	{
		System.out.println("1. Nail \n2. Tool \n3. PowerTool\n4. StripNail");
		System.out.println("Please enter the number of item you would like to add");
		
		String item1 = sc.nextLine();
		boolean resume = false;
		
		while(!resume)
		{
			switch(Integer.parseInt(item1))
			{
				case 1: 
					createNail(sc);
					resume = true;
					break;
				case 2: 
					createTool(sc);
					resume = true;
					break;
				case 3: 
					createPowerTool(sc);
					resume = true;
					break;
				case 4:
					createStripNail(sc);
					resume = true;
					break;
				default: 
					System.out.println("Invalid input, please re-enter");
					item1 = sc.nextLine();
			}
		}
	}
	
	/**
	 * Creates a new StripNail and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void createStripNail(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Please enter UPC code \n");
		String upc = sc.nextLine();
		
		System.out.println("Please enter Manufacturer ID  \n");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter Price \n");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter length \n");
		String length = sc.nextLine();
		double lengthParse = Double.parseDouble(length);
		
		System.out.println("Please enter number in strip \n");
		String numberInStrip = sc.nextLine();
		int numberInStripParse = Integer.parseInt(numberInStrip);
	
		StripNail stripNail = new StripNail(upc, manufacturerIDParse, priceParse, lengthParse, numberInStripParse, "StripNail");
	}

	/**
	 * Creates a new PowerTool and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void createPowerTool(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Please enter UPC code \n");
		String upc = sc.nextLine();
		
		System.out.println("Please enter Manufacturer ID  \n");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter Price \n");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter Description \n");
		String description = sc.nextLine();
		
		System.out.println("Please enter if the item is battery powered (true/false) \n");
		String batteryPowered = sc.nextLine();
		boolean batteryPoweredParse = Boolean.parseBoolean(batteryPowered);
		
		PowerTool powerTool = new PowerTool(upc, manufacturerIDParse, priceParse, description, batteryPoweredParse, "PowerTool");
		
	}
		
	/**
	 * Creates a new Tool and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void createTool(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Please enter UPC code \n");
		String upc = sc.nextLine();
		
		System.out.println("Please enter Manufacturer ID  \n");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter Price \n");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter Description \n");
		String description = sc.nextLine();
		
		Tool tool = new Tool(upc, manufacturerIDParse, priceParse, description, "Tool");
		
	}

	/**
	 * Creates a new Nail and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void createNail(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		System.out.println("Please enter UPC code \n");
		String upc = sc.nextLine();
		
		System.out.println("Please enter Manufacturer ID  \n");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter Price \n");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		
		System.out.println("Please enter length \n");
		String length = sc.nextLine();
		double lengthParse = Double.parseDouble(length);
		
		System.out.println("Please enter Number in Box \n");
		String numberInBox = sc.nextLine();
		int numberInBoxParse = Integer.parseInt(numberInBox);
		
		Nail nail = new Nail(upc, manufacturerIDParse, priceParse, lengthParse, numberInBoxParse, "Nail");
	}

	/**
	 * Retrieves details of requested itemei and returns string with details of item 
	 * 
	 * @param upc
	 * @return String 
	 * @throws ClassNotFoundExceptiongetDetails
	 * @throws SQLException
	 */
	public static InventoryItem getDetails(String upc) throws ClassNotFoundException, SQLException
	{
		InventoryItem item = DatabaseGateway.retreiveItemByUpc(upc);
		return item;
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
		return listOfObjects = DatabaseGateway.createList();
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