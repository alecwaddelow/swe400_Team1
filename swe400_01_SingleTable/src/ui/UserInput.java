package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import data_source.DatabaseGateway;
import data_source.LinkTableGateway;
import domain_layer.*;
import runner.Runner;

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
					NailUI.createNail(sc);
					resume = true;
					break;
				case 2: 
					ToolUI.createTool(sc);
					resume = true;
					break;
				case 3: 
					PowerToolUI.createPowerTool(sc);
					resume = true;
					break;
				case 4:
					StripNailUI.createStripNail(sc);
					resume = true;
					break;
				default: 
					System.out.println("\nInvalid input, please re-enter");
			}
		}
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
						NailUI.updateNail(sc, item);
						break;
					case "Tool":
						ToolUI.updateTool(sc, item);
						break;
					case "PowerTool":
						PowerToolUI.updatePowerTool(sc, item);
						break;
					case "StripNail":
						StripNailUI.updateStripNail(sc, item);
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

	/**
	 * Adds the compatible stripnail to the PowerTool
	 * 
	 * @param sc
	 * @param powerTool
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addCompatibles(Scanner sc, InventoryItem item) throws ClassNotFoundException, SQLException 
	{
		ArrayList<InventoryItem> itemList = new ArrayList<InventoryItem>(); 
		ArrayList<String> inputtedValues = new ArrayList<String>(); 
		
		boolean run = true;
		String input = null;
		
		PowerTool powerTool = null;
		StripNail stripNail = null;
		while (run)
		{
			int i = 1;
			if(item.getClassName().equalsIgnoreCase("PowerTool"))
			{
				ResultSet rSet = DatabaseGateway.getStripNailUPCs();
				while(rSet.next())
				{
					stripNail = new StripNail(rSet.getInt("id"));
					itemList.add(stripNail);
					System.out.print(i + ". ");
					System.out.println(stripNail.toString());
					i++;
				}
			}
			else if(item.getClassName().equalsIgnoreCase("StripNail"))
			{
				ResultSet rSet = DatabaseGateway.getPowerToolUPCs();
				while(rSet.next())
				{
					powerTool = new PowerTool(rSet.getInt("id"));
					itemList.add(powerTool);
					System.out.println(i);
					System.out.println(powerTool.toString());
					i++;
				}
			}
				
			
			System.out.println("Please enter the number you would like to add");
			input = sc.nextLine();
			
			
			if(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= i && checkForDuplicates(input, inputtedValues))
			{
				inputtedValues.add(input);
				if(item.getClassName().equalsIgnoreCase("PowerTool"))
				{
					stripNail = (StripNail) itemList.get(Integer.parseInt(input)-1);
					LinkTableGateway.addRelation(item.getId(), stripNail.getId());
				}
				else
				{
					powerTool = (PowerTool) itemList.get(Integer.parseInt(input)-1); 
					LinkTableGateway.addRelation(powerTool.getId(), item.getId());
				}
			}
			else
			{
				System.out.println("Error: you didn't enter in a valid number");
			}	
			
			System.out.println("Would you like to add another relation? (Y/N)");
			input = sc.nextLine();
			boolean flag = false;
			
			while(!flag)
			{
				if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
				{
					flag = true;
				}
				else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
				{
					flag = true;
					run = false;
				}
				else 
				{
					System.out.println("Error: invalid input");
				}				
			}
		}		
	}

	/**
	 * checks to make sure user isn't inputting the same value twice
	 * 
	 * @param inputtedValues
	 * @return
	 */
	private static boolean checkForDuplicates(String input, ArrayList<String> inputtedValues) 
	{
		boolean noDuplicates = true;
		
		if(inputtedValues != null)
		{
			for(String value : inputtedValues)
			{
				if(Integer.parseInt(input) == Integer.parseInt(value))
				{
					System.out.println("Error: You are not allowed to enter a duplicate relation");
					noDuplicates = false;
				}
			}
		}
		return noDuplicates;
	}	
}