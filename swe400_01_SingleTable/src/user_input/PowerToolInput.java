package user_input;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data_source.*;
import domain.*;
import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife and Alec Waddelow 
 * 
 * PowerTool input class 
 *
 */
public class PowerToolInput 
{
	/**
	 * Creates a new PowerTool and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void createPowerTool(Scanner sc) throws ClassNotFoundException, SQLException, ItemNotFoundException 
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
				
		powerToolRelationPrompt(sc, powerTool);
		
		System.out.println("Item added");
		System.out.println(powerTool.toString());
	}
	
	/**
	 * Updates a powertool object 
	 * 
	 * @param sc
	 * @param item
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public static void updatePowerTool(Scanner sc, int item) throws SQLException, ClassNotFoundException, ItemNotFoundException 
	{
		PowerTool powerTool = (PowerTool) UserInput.validUPCRequest(sc, item);
		
		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
		
		System.out.println("Plase enter the UPC:");
		String upc = sc.nextLine();
		
		System.out.println("Please enter the manufacturerID:");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter the price of the item:");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter the description");
		String description = sc.nextLine();
		
		System.out.println("If it's battery powered please type true, if not type false");
		String isBatteryPowered = sc.nextLine();
		boolean batteryPowered = isBatteryPowered.equalsIgnoreCase("true");
		
		powerTool.update(upc, manufacturerIDParse, priceParse, description, batteryPowered);
		
		boolean valid = false;
		while(!valid)
		{
			System.out.println("Would you like to:");
			System.out.println("1. Add a compatible stripnail");
			System.out.println("2. Remove a compatible stripnail");
			System.out.println("3. Go back to main prompt");
			String input = sc.nextLine();
			switch(Integer.parseInt(input))
			{
				case 1:
					UserInput.addCompatibles(sc, powerTool);
					valid = true;
					break;
				case 2:
					removeCompatibilities(sc, powerTool);
					valid = true;
					break;
				case 3:
					valid = true;
					break;
				default:
					System.out.println("Error: Not a valid option");
			}
		}
		System.out.println("\nItem updated:");
		System.out.println(powerTool.toString());
	}

	/**
	 * Adds a StripNail relation for the powertool
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void updateCompatibilities(Scanner sc, PowerTool powerTool) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		boolean done = false;
		while(!done)
		{
			List<InventoryItemDTO> dtoList = InventoryItemGateway.getAllStripNails();
			for(InventoryItemDTO dto : dtoList)
			{
				System.out.println(dto.getUpc());
			}
			
			System.out.println("Which one would you like to add :");
			String input = sc.nextLine();
			int stripNailID = InventoryItemGateway.getID(input, "StripNail");
			
			powerTool.addCompatibleStripNail(stripNailID);
			
			System.out.println("Would you like to add another relation? (Y/N)");
			input = sc.nextLine();
			
			if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
			{
				done = true;
			}
		}
	}
	
	/**
	 * Removes compatibilities from the list
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public static void removeCompatibilities(Scanner sc, PowerTool powerTool) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		boolean done = false;
		while(!done)
		{
			System.out.println("Which one would you like to remove? (enter the UPC only):");
			ArrayList<StripNail> stripList = powerTool.getStripNailList();
			
			if(!stripList.isEmpty())
			{
				System.out.println("\nWorks with:");
				
				for(StripNail stripNail : stripList) 
				{
					System.out.println(stripNail.toString());
				}
				
				System.out.println("\n");
				String input = sc.nextLine();
				int stripNailID = InventoryItemGateway.getID(input, "StripNail");
				
				powerTool.removeCompatibleStripNail(stripNailID);
				
				System.out.println("Would you like to remove another relation? (Y/N)");
				input = sc.nextLine();
				
				if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
				{
					done = true;
				}
			}
			else
			{
				System.out.println("There are no compatibilities\n");
				done = true;
			}			
		}		
	}

	/**
	 * Sets the relationship for the created PowerTool
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void powerToolRelationPrompt(Scanner sc, PowerTool powerTool) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		boolean run = true;
		InventoryItem item = powerTool;
		
		while(run)
		{
			System.out.println("Would you like to add compatible stripnails (Y/N)");
			String input = sc.nextLine();
			
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
			{
				UserInput.addCompatibles(sc, item);
				run = false;
			}
			else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
			{
				run = false;
			}
			else 
			{
				System.out.println("Error: invalid input");
			}
		}		
	}
}