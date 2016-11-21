package user_input;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import data_source.DatabaseGateway;
import domain.*;
import exceptions.ItemNotFoundException;
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
		try(Scanner sc = new Scanner(System.in))
		{
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
						itemUPC(sc);
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
		catch(Exception e)
		{
			e.getCause();
		}
	}
	
	/**
	 * Gets the number associated with the item  to see what they want
	 * 
	 * @param sc
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	private static void itemUPC(Scanner sc) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		
		boolean valid = false;
		while(!valid)
		{
			System.out.println("Which item were you thinking of? (Enter the number)");
			System.out.println("1. Nail \n2. Tool \n3. PowerTool \n4. StripNail");
			
			String input = sc.nextLine();
			
			int itemAssociation = Integer.parseInt(input);
			
			if(itemAssociation >= 1 && itemAssociation <= 4)
			{
				validUPCRequest(sc, itemAssociation);
				valid = true;
			}
			else
			{
				System.out.println("\nError: Not correct input\n");
			}
		}
	}

	/**
	 * Make sure the user entered a valid value for an option to either:
	 * 1. Search an item by upc
	 * 2. add
	 * 3. update
	 * 4. end the program
	 * 
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
			{
				validChoice = true;
			}
			else
			{
				System.out.println("\nError: The number you chose: " + integer + " is not a current option");
			}
		}
		else
		{
			System.out.println("\nError: You didn't enter a number from 1 to 3. Please try again.");
		}
		return validChoice;
	}
	
	/**
	 * Requests the user to enter the UPC of an item
	 * 
	 * @param sc
	 * @return true if the item is not null (exists); false otherwise
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static InventoryItem validUPCRequest(Scanner sc, int itemAssociation) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		boolean valid = false;
		InventoryItem item = null;
		
		while(!valid)
		{
			System.out.println("Please enter a UPC ");
			
			String upcCode = sc.nextLine();
			
			String className = null;
			
			switch(itemAssociation)
			{
				case 1:
					className = "Nail";
					break;
				case 2:
					className = "Tool";
					break;
				case 3:
					className = "PowerTool";
					break;
				case 4:
					className = "StripNail";
					break;
			}
		
			
			if(itemAssociation != 0)
			{
				item = InventoryItem.getDetails(upcCode, className);			

				if((item instanceof Nail && itemAssociation == 1)
				|| (item instanceof Tool && itemAssociation == 2)
				|| (item instanceof PowerTool && itemAssociation == 3)
				|| (item instanceof StripNail && itemAssociation == 4))
				{
					Runner.printDetailsOfItem(item);
					valid = true;
				}
				else if(item == null) 
				{
					System.out.println("Error: Not a valid UPC");
				}
			}
			else
			{
				if(item != null)
				{
					Runner.printDetailsOfItem(item);
					valid = true;
				}
				else
				{
					System.out.println("Error: Not a valid UPC");
				}
			}
		}		
		return item;
	}
	
	/**
	 * Adds a new item to the database based off of user input 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void addItemToDB(Scanner sc) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		boolean resume = false;
		
		while(!resume)
		{
			System.out.println("1. Nail \n2. Tool \n3. PowerTool\n4. StripNail");
			System.out.println("Please enter the number of item you would like to add");
			
			String input = sc.nextLine();
			int item = Integer.parseInt(input);
			
			switch(item)
			{
				case 1: 
					NailInput.createNail(sc);
					resume = true;
					break;
				case 2: 
					ToolInput.createTool(sc);
					resume = true;
					break;
				case 3: 
					PowerToolInput.createPowerTool(sc);
					resume = true;
					break;
				case 4:
					StripNailInput.createStripNail(sc);
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
	 * @throws ItemNotFoundException 
	 */
	private static void updatePrompt(Scanner sc) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		boolean resume = false;
		while(!resume)
		{
			System.out.println("1. Nail \n2. Tool \n3. PowerTool\n4. StripNail");
			System.out.println("Please enter the number of item you would like to update");
			String input = sc.nextLine();
			int item = Integer.parseInt(input);
			switch(item)
			{
				case 1: 
					NailInput.updateNail(sc, item);
					resume = true;
					break;
				case 2: 
					ToolInput.updateTool(sc, item);
					resume = true;
					break;
				case 3: 
					PowerToolInput.updatePowerTool(sc, item);
					resume = true;
					break;
				case 4:
					StripNailInput.updateStripNail(sc, item);
					resume = true;
					break;
				default: 
					System.out.println("\nInvalid input, please re-enter");
			}
		}
		
	}

	/**
	 * Adds the compatible stripnail to the PowerTool
	 * 
	 * @param sc
	 * @param item
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public static void addCompatibles(Scanner sc, InventoryItem item) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		ArrayList<InventoryItem> itemList = new ArrayList<InventoryItem>(); 
		ArrayList<String> inputtedValues = new ArrayList<String>(); 
		boolean run = true;
		String input = null;
		PowerTool powerTool = null;
		StripNail stripNail = null;
		
		while (run)
		{
			System.out.println("Please enter the number you would like to add");
			int i = 0;
			if(item instanceof PowerTool)
			{
				try(ResultSet rSet = DatabaseGateway.getStripNailUPCs())
				{
					while(rSet.next())
					{
						stripNail = new StripNail(rSet.getInt("id"));
						itemList.add(stripNail);
						i++;
						System.out.println(i + ". " + stripNail.toString());
					}
					rSet.close();
					DatabaseGateway.closeStatements();				
				}
				catch(SQLException notFound)
				{
					notFound.getMessage();
				}
			}
			else if(item instanceof StripNail)
			{
				try(ResultSet rSet = DatabaseGateway.getPowerToolUPCs())
				{
					while(rSet.next())
					{
						powerTool = new PowerTool(rSet.getInt("id"));
						itemList.add(powerTool);
						i++;
						System.out.println(i + ". " + powerTool.toString());
					}
					rSet.close();
					DatabaseGateway.closeStatements();
					
				}
				catch(SQLException notFound)
				{
					notFound.getMessage();
				}
			}
			input = sc.nextLine();
			
			if(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= i)
			{
				if(checkForDuplicates(input, inputtedValues))
				{
					inputtedValues.add(input);
					if(item.getClassName().equalsIgnoreCase("PowerTool"))
					{
						stripNail = (StripNail) itemList.get(Integer.parseInt(input)-1);
						LinkTableMapper.addRelation(item.getId(), stripNail.getId());
					}
					else
					{
						powerTool = (PowerTool) itemList.get(Integer.parseInt(input)-1); 
						LinkTableMapper.addRelation(powerTool.getId(), item.getId());
					}
				}
			}
			else
			{
				System.out.println("Error: you didn't enter in a valid number");
			}	
			
			
			boolean flag = false;
			while(!flag)
			{
				System.out.println("Would you like to add another relation? (Y/N)");
				input = sc.nextLine();
				
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
	 * Checks to make sure user isn't inputting the same value twice
	 * 
	 * @param inputtedValues
	 * @return boolean true if duplicates found; else false
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