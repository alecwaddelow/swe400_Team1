package runner;

import java.sql.SQLException;
import java.util.Scanner;
import data_source.DatabaseGateway;
import domain_layer.*;

public class UserInput 
{
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
			String input = null;
			boolean validChoice = false;
			
			/* the beginning prompt */
			while(!validChoice)
			{
				System.out.println("\nPlease enter the number of which task you would like to do:");
				System.out.println("1:Search an item by UPC");
				System.out.println("2:Add an item");
				System.out.println("3:Update an item");
				System.out.println("4:End the program");
				input = sc.nextLine();
				
				validChoice = validateSearchAddUpdateOrEnd(input);
			}
			
			/* based off the user's input, decide what to do next */
			switch(Integer.parseInt(input))
			{
				case 1:
					validUPCRequest(sc);
					break;
				case 2:
					addItemToDB(sc);
					break;
				case 3:
					updatePrompt(sc);
					break;
				case 4:
					run = false;
					break;
			}

		}while(run);	
		sc.close();
	}
	
	/**
	 * Prompts for the user to update an item to the database 
	 * 
	 * @param sc
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void updatePrompt(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		boolean flag = true;
		while(flag)
		{
			InventoryItem item = validUPCRequest(sc);
			
			System.out.println("\nIs this the item you'd like to edit? (Y/N)");
			String input = sc.nextLine();
			
			/* if y or yes then update that item to the database */
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
			{
				Runner.printDetailsOfItem(item);
				
				switch(item.getClassName())
				{
					case "Nail":
						Nail.update(sc, item);
						break;
					case "Tool":
						Tool.update(sc, item);
						break;
					case "StripNail":
						StripNail.update(sc, item);
						break;
					case "PowerTool":
						PowerTool.update(sc, item);
						break;
				}
				
				flag = false;
			}
			/* if not see if they want to go back to the main prompt */
			else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
			{
				System.out.println("Would you like to go back to the main prompt? (Y/N)");
				input = sc.nextLine();
				
				/* if they enter anything other than y or yes then just re-ask the question */
				if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
				{
					flag = false;
				}
			}
		}		
	}

//	private static void updatePowerTool(Scanner sc, InventoryItem item) throws SQLException 
//	{
//		PowerTool powerTool = (PowerTool) item;
//		
//		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
//		
//		System.out.println("Plase enter the UPC:");
//		String upc = sc.nextLine();
//		
//		System.out.println("Please enter the manufacturerID:");
//		String manufacturerID = sc.nextLine();
//		int manufacturerIDParse = Integer.parseInt(manufacturerID);
//		
//		System.out.println("Please enter the price of the item:");
//		String price = sc.nextLine();
//		int priceParse = Integer.parseInt(price);
//		
//		System.out.println("Please enter the description");
//		String description = sc.nextLine();
//		
//		System.out.println("If it's battery powered please type true, if not type false");
//		String batteryPowered = sc.nextLine();
//		
//		powerTool.setUpc(upc);
//		powerTool.setManufacturerID(manufacturerIDParse);
//		powerTool.setPrice(priceParse);
//		powerTool.setDescription(description);
//
//		boolean isBatteryPowered;
//		if(batteryPowered.equalsIgnoreCase("true"))
//		{
//			powerTool.setBatteryPowered(true);
//			isBatteryPowered = true;
//		}
//		else
//		{
//			powerTool.setBatteryPowered(false);
//			isBatteryPowered = false;
//		}
//		
//		DatabaseGateway.updatePowerToolToDB(upc, manufacturerIDParse, priceParse, description, isBatteryPowered, item.getId());
//		
//		System.out.println("\nItem updated:");
//		System.out.println(powerTool.toString());
//	}

//	private static void updateStripNail(Scanner sc, InventoryItem item) throws SQLException 
//	{
//		StripNail stripNail = (StripNail) item;
//		
//		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
//		
//		System.out.println("Plase enter the UPC:");
//		String upc = sc.nextLine();
//		
//		System.out.println("Please enter the manufacturerID:");
//		String manufacturerID = sc.nextLine();
//		int manufacturerIDParse = Integer.parseInt(manufacturerID);
//		
//		System.out.println("Please enter the price of the item:");
//		String price = sc.nextLine();
//		int priceParse = Integer.parseInt(price);
//		
//		System.out.println("Please enter length \n");
//		String length = sc.nextLine();
//		double lengthParse = Double.parseDouble(length);
//		
//		System.out.println("Please enter Number in Strip \n");
//		String numberInStrip = sc.nextLine();
//		int numberInStripParse = Integer.parseInt(numberInStrip);
//		
//		stripNail.setUpc(upc);
//		stripNail.setManufacturerID(manufacturerIDParse);
//		stripNail.setPrice(priceParse);
//		stripNail.setLength(lengthParse);
//		stripNail.setNumberInStrip(numberInStripParse);	
//		
//		DatabaseGateway.updateStripNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInStripParse, item.getId());
//		
//		System.out.println("\nItem updated:");
//		System.out.println(stripNail.toString());
//	}

//	private static void updateTool(Scanner sc, InventoryItem item) throws SQLException 
//	{
//		Tool tool = (Tool)item;
//		
//		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
//		
//		System.out.println("Plase enter the UPC:");
//		String upc = sc.nextLine();
//		
//		System.out.println("Please enter the manufacturerID:");
//		String manufacturerID = sc.nextLine();
//		int manufacturerIDParse = Integer.parseInt(manufacturerID);
//		
//		System.out.println("Please enter the price of the item:");
//		String price = sc.nextLine();
//		int priceParse = Integer.parseInt(price);
//		
//		System.out.println("Please enter the description");
//		String description = sc.nextLine();
//		
//		tool.setUpc(upc);
//		tool.setManufacturerID(manufacturerIDParse);
//		tool.setPrice(priceParse);
//		tool.setDescription(description);
//		
//		DatabaseGateway.updateToolToDB(upc, manufacturerIDParse, priceParse, description, item.getId());
//		
//		System.out.println("\nItem updated:");
//		System.out.println(tool.toString());
//	}

//	/**
//	 * Updates the item  locally then sends the fields to the insert function in the gateway
//	 * 
//	 * @param sc
//	 * @param item
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	private static void updateNail(Scanner sc, InventoryItem item) throws ClassNotFoundException, SQLException 
//	{
//		Nail nail = (Nail) item;
//		
//		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
//		
//		System.out.println("Plase enter the UPC:");
//		String upc = sc.nextLine();
//		
//		System.out.println("Please enter the manufacturerID:");
//		String manufacturerID = sc.nextLine();
//		int manufacturerIDParse = Integer.parseInt(manufacturerID);
//		
//		System.out.println("Please enter the price of the item:");
//		String price = sc.nextLine();
//		int priceParse = Integer.parseInt(price);
//		
//		System.out.println("Please enter length \n");
//		String length = sc.nextLine();
//		double lengthParse = Double.parseDouble(length);
//			
//		System.out.println("Please enter Number in Box \n");
//		String numberInBox = sc.nextLine();
//		int numberInBoxParse = Integer.parseInt(numberInBox);
//			
//		nail.setUpc(upc);
//		nail.setManufacturerID(manufacturerIDParse);
//		nail.setPrice(priceParse);
//		nail.setLength(lengthParse);
//		nail.setNumberInBox(numberInBoxParse);
//			
//		DatabaseGateway.updateNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInBoxParse, item.getId());
//		
//		System.out.println("\nItem updated:");
//		System.out.println(nail.toString());
//	}

	/**
	 * Make sure the user entered a valid value for an option to either:
	 * 1. Search an item by upc
	 * 2. add
	 * 3. update
	 * 4. end the program
	 * @param input
	 * @return true if valid input; false otherwise
	 */
	private static boolean validateSearchAddUpdateOrEnd(String input) 
	{
		boolean validChoice = false;
		/* checks to see if the input is an integer */
		if(input.matches("-?\\d+"))
		{
			int integer = Integer.parseInt(input);
			
			if(integer >= 1 && integer <= 4)
				validChoice = true;
			else
				System.out.println("\nError: The number you chose: " + integer + " is not a current option");
		}
		else
			System.out.println("\nError: You din't enter a number from 1 to 3. Please try again.");
		
		return validChoice;
	}

	/**
	 * Requests the user to enter the UPC of an item
	 * 
	 * @param sc
	 * @return true if the item is not null (exists); false otherwise
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItem validUPCRequest(Scanner sc) throws ClassNotFoundException, SQLException 
	{
		boolean valid = false;
		InventoryItem item = null;
		
		while(!valid)
		{
			System.out.println("Please enter a UPC ");
			
			String upcCode = sc.nextLine();
			
			item = InventoryItem.getDetails(upcCode);
			
			if(item != null)
			{
				Runner.printDetailsOfItem(item);
				valid = true;
			}
			else
			{
				System.out.println("Error: you must enter a valid UPC");
			}			
		}		
		return item;
	}
	
	/**
	 * Adds a new item to the database based off of user input 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addItemToDB(Scanner sc) throws ClassNotFoundException, SQLException
	{
		boolean resume = false;
		
		while(!resume)
		{
			System.out.println("1. Nail \n2. Tool \n3. PowerTool\n4. StripNail");
			System.out.println("Please enter the number of item you would like to add");
			
			String item1 = sc.nextLine();
			
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
					System.out.println("\nInvalid input, please re-enter");
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
		
		System.out.println("Item added");
		System.out.println(stripNail.toString());
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
		
		System.out.println("Item added");
		System.out.println(powerTool.toString());
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
		
		System.out.println("Item added");
		System.out.println(tool.toString());
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
		
		System.out.println("Item added");
		System.out.println(nail.toString());
	}
}
