package user_input;
import java.sql.SQLException;
import java.util.Scanner;

import domain.Tool;
import domain.ToolMapper;
import exceptions.ItemNotFoundException;

public class ToolInput 
{
	/**
	 * Creates a new Tool and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createTool(Scanner sc) throws ClassNotFoundException, SQLException 
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
	 * Updates a tool object
	 * 
	 * @param sc
	 * @param item
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public static void updateTool(Scanner sc, int item) throws SQLException, ClassNotFoundException, ItemNotFoundException 
	{
		Tool tool = (Tool) UserInput.validUPCRequest(sc, item);
		
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
		
		tool.setUpc(upc);
		tool.setManufacturerID(manufacturerIDParse);
		tool.setPrice(priceParse);
		tool.setDescription(description);
		
		/* updates the tool to the mapper and to the database */
		ToolMapper toolMapper = new ToolMapper();
		toolMapper.updateTool(tool);
		
		System.out.println("\nItem updated:");
		System.out.println(tool.toString());
	}
}