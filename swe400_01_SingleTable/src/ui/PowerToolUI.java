package ui;

import java.sql.SQLException;
import java.util.Scanner;

import data_source.DatabaseGateway;
import domain_layer.InventoryItem;
import domain_layer.PowerTool;

public class PowerToolUI 
{
	/**
	 * Creates a new PowerTool and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createPowerTool(Scanner sc) throws ClassNotFoundException, SQLException 
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
	 */
	public static void updatePowerTool(Scanner sc, InventoryItem item) throws SQLException 
	{
		PowerTool powerTool = (PowerTool) item;
		
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
		String batteryPowered = sc.nextLine();
		
		powerTool.setUpc(upc);
		powerTool.setManufacturerID(manufacturerIDParse);
		powerTool.setPrice(priceParse);
		powerTool.setDescription(description);

		boolean isBatteryPowered;
		if(batteryPowered.equalsIgnoreCase("true"))
		{
			powerTool.setBatteryPowered(true);
			isBatteryPowered = true;
		}
		else
		{
			powerTool.setBatteryPowered(false);
			isBatteryPowered = false;
		}
		/**
		 * Send update to the database 
		 */
		DatabaseGateway.updatePowerToolToDB(upc, manufacturerIDParse, priceParse, description, isBatteryPowered, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(powerTool.toString());
	}
	
	/**
	 * Sets the relationship for the created PowerTool
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void powerToolRelationPrompt(Scanner sc, PowerTool powerTool) throws ClassNotFoundException, SQLException 
	{
		boolean run = true;
		InventoryItem item = powerTool;
		
		while(run)
		{
			System.out.println("Would you like to add compatible strip nails (Y/N)");
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
