package domain_layer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_source.DatabaseGateway;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem Tool
 */
public class Tool extends InventoryItem
{
	protected String description;	
	/**
	 * Finder Constructor that queries the database for the item specified by their ID
	 *
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Tool(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
		ResultSet rs = DatabaseGateway.queryTool(this.id);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			String description = rs.getString("description");
			String className = rs.getString("className");
			ToolMapper toolMapper = new ToolMapper(upc, manufacturerID, price, description, className);
			
			setUpc(toolMapper.getUpc());
			setManufacturerID(toolMapper.getManufacturerID());
			setPrice(toolMapper.getPrice());
			setDescription(toolMapper.getDescription());
			setClassName(toolMapper.getClassName());			
		}
		else
		{
			ClassNotFoundException notFoundException = new ClassNotFoundException("Could not find tool with specified ID");
			notFoundException.getMessage();
		}
	}

	/**
	 * Creation Constructor that creates the tool
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Tool(String upc, int manufacturerID, int price, String description, String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, className);
		this.description = description;
		this.className = "Tool";
		ToolMapper toolMapper = new ToolMapper(this.upc, this.manufacturerID, this.price, this.description, this.className);
		toolMapper.insertTool();
	}

	/**
	 * @return description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * sets the description for the tool
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Tool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price
				+ ", description=" + this.description + "]";
	}

	/**
	 * @return name of the class
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/**
	 * Sets the className for the Tool Object
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
	
	public static void update(Scanner sc, InventoryItem item) throws SQLException 
	{
		Tool tool = (Tool)item;
		
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
		
		DatabaseGateway.updateToolToDB(upc, manufacturerIDParse, priceParse, description, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(tool.toString());
	}
}