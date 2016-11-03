package ui;

import java.sql.SQLException;
import java.util.Scanner;

import data_source.DatabaseGateway;
import domain_layer.InventoryItem;
import domain_layer.Nail;

public class NailUI 
{

	/**
	 * Creates a new Nail and adds to the Database 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createNail(Scanner sc) throws ClassNotFoundException, SQLException 
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

	public static void updateNail(Scanner sc, InventoryItem item)  throws ClassNotFoundException, SQLException 
	{
		Nail nail = (Nail) item;
		
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
			
		System.out.println("Please enter Number in Box \n");
		String numberInBox = sc.nextLine();
		int numberInBoxParse = Integer.parseInt(numberInBox);
			
		nail.setUpc(upc);
		nail.setManufacturerID(manufacturerIDParse);
		nail.setPrice(priceParse);
		nail.setLength(lengthParse);
		nail.setNumberInBox(numberInBoxParse);
			
		DatabaseGateway.updateNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInBoxParse, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(nail.toString());
	}	
}