package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import data_source.*;
import domain_layer.*;

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
	 * @throws ClassNotFoundException 
	 */
	public static void updateStripNail(Scanner sc, int item) throws SQLException, ClassNotFoundException 
	{
		StripNail stripNail = (StripNail) UserInput.validUPCRequest(sc, item);
		
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
		
		/* updates the nail to the mapper and to the database */
		StripNailsMapper stripNailsMapper = new StripNailsMapper();
		stripNailsMapper.updateStripNail(stripNail);
		
		boolean valid = false;
		while(!valid)
		{
			System.out.println("Would you like to ");
			System.out.println("1. Add a compatible powertool?");
			System.out.println("2. Remove a compatible powertool?");
			System.out.println("3. Go back to main prompt");
			String input = sc.nextLine();
			switch(Integer.parseInt(input))
			{
			case 1:
				updateCompatibilities(sc, stripNail);
				valid = true;
				break;
			case 2:
				removeCompatibilities(sc, stripNail);
				valid = true;
				break;
			case 3:
				valid = true;
				break;
			default:
				System.out.println("Error: Not a valid option\n");
			}
		}
		
		System.out.println("\nItem updated:");
		System.out.println(stripNail.toString());
	}
	
	/**
	 * Removes compatibilities from the list
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private static void removeCompatibilities(Scanner sc, StripNail stripNail) throws ClassNotFoundException, SQLException
	{
		boolean done = false;
		while(!done)
		{
			System.out.print("Which one would you like to remove? (enter the UPC only)");
			ArrayList<PowerTool> powerToolList = stripNail.getPowerToolList();
			
			if(!powerToolList.isEmpty())
			{
				System.out.println("\nWorks with:");
				
				for(PowerTool powerTool : powerToolList)
				{
					System.out.println(powerTool.toString());
				}
				
				System.out.println("\n");
				String input = sc.nextLine();
				int powerToolID = DatabaseGateway.getID(input, "PowerTool");
				
				LinkTableMapper.removeRelation(powerToolID, stripNail.getId());
				stripNail.removePowerToolFromList(new PowerTool(powerToolID));
				
				System.out.println("Would you like to remove another relation? (Y/N)");
				input = sc.nextLine();
				
				if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
				{
					done = true;
				}
				else
				{
					System.out.println("There are no compatibilities\n");
				}
			}	
		}
	}

	/**
	 * Adds a PowerTool relation for the stripNail
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateCompatibilities(Scanner sc, StripNail stripNail) throws ClassNotFoundException, SQLException 
	{
		boolean done = false;
		while(!done)
		{
			ResultSet resultSet = DatabaseGateway.getPowerToolUPCs();
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("upc"));
			}
			
			System.out.println("Which one would you like to add :");
			String input = sc.nextLine();
			int powerToolID = DatabaseGateway.getID(input, "PowerTool");
			
			LinkTableMapper.addRelation(powerToolID, stripNail.getId());
			stripNail.addPowerToolToList(new PowerTool(powerToolID));
			
			System.out.println("Would you like to add another relation? (Y/N)");
			input = sc.nextLine();
			
			if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
			{
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
