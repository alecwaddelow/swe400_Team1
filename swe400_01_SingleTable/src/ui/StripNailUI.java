package ui;

import java.sql.SQLException;
import java.util.Scanner;
import data_source.DatabaseGateway;
import domain_layer.InventoryItem;
import domain_layer.StripNail;

public class StripNailUI 
{
	/**
	 * Creates a new StripNail and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createStripNail(Scanner sc) throws ClassNotFoundException, SQLException 
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
		
		stripNailRelationPrompt(sc, stripNail);
		
		System.out.println("Item added");
		System.out.println(stripNail.toString());
	}
	
	/**
	 * Updates a stripnail object
	 * 
	 * @param sc
	 * @param item
	 * @throws SQLException
	 */
	public static void updateStripNail(Scanner sc, InventoryItem item) throws SQLException 
	{
		StripNail stripNail = (StripNail) item;
		
		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
		
		System.out.println("Plase enter the UPC:");
		String upc = sc.nextLine();
		
		System.out.println("Please enter the manufacturerID:");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter the price of the item:");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter length \n");
		String length = sc.nextLine();
		double lengthParse = Double.parseDouble(length);
		
		System.out.println("Please enter Number in Strip \n");
		String numberInStrip = sc.nextLine();
		int numberInStripParse = Integer.parseInt(numberInStrip);
		
		stripNail.setUpc(upc);
		stripNail.setManufacturerID(manufacturerIDParse);
		stripNail.setPrice(priceParse);
		stripNail.setLength(lengthParse);
		stripNail.setNumberInStrip(numberInStripParse);	
		
		DatabaseGateway.updateStripNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInStripParse, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(stripNail.toString());
	}
	
	/**
	 * Sets the relationship for the created PowerTool
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void stripNailRelationPrompt(Scanner sc, StripNail stripNail) throws ClassNotFoundException, SQLException 
	{
		boolean run = true;
		InventoryItem item = stripNail;
		
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
