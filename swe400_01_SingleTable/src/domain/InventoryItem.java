package domain;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

import data_source.DatabaseGateway;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 * Anything that inherits InventoryItem will have an id, upc, manufacturerID and a price
 */
public abstract class InventoryItem
{
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
	protected String className;
	
	/**
	 * Creation Constructor 
	 * 
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param className
	 */
	InventoryItem(String UPC, int ManufacturerID, int price, String className)
	{
		this.upc = UPC;
		this.manufacturerID = ManufacturerID;
		this.price = price;
		this.className = className;
	}
	
	/**
	 * Finder constructor 
	 * 
	 * @param id
	 */
	InventoryItem(int id)
	{
		this.id = id;
	
	}
	
	/**
	 * Empty Constructor for testing 
	 */
	InventoryItem(){}
	
	
	/**
	 * Set ID
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * @return ID
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * @return UPC
	 */
	public String getUpc()
	{
		return this.upc;
	}

	/**
	 * Set UPC
	 * 
	 * @param upc
	 */
	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * @return manufacturerID
	 */
	public int getManufacturerID()
	{
		return this.manufacturerID;
	}

	/**
	 * Sets the manufacturerID
	 * 
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	/**
	 * @return price
	 */
	public int getPrice()
	{
		return this.price;
	}

	/**
	 * Sets the price
	 * 
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}

	/**
	 * @return the className
	 */
	public String getClassName() 
	{
		return this.className;
	}
	
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) 
	{
		this.className = className;
	}
	
	/**
	 * Matches classes with their finder constructor, constructs an object, and returns it
	 *
	 * @param ID
	 * @param className
	 * @return InventoryItem
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItem matchClassAndConstruct(int ID, String className) throws ClassNotFoundException, SQLException
	{
		switch(className)
		{
		case "Tool":
			Tool tool = new Tool(ID);
			return tool;
		case "PowerTool":
			PowerTool powerTool= new PowerTool(ID);
			return powerTool;
		case "StripNail":
			StripNail stripNail = new StripNail(ID);
			return stripNail;
		case "Nail":
			Nail nail = new Nail(ID);
			return nail;
		default:
			throw new ClassNotFoundException();
		}
	}
	
	/**
	 * Retrieves details of requested item and returns string with details of item 
	 * 
	 * @param upc
	 * @return InventoryItem 
	 * @throws ClassNotFoundExceptiongetDetails
	 * @throws SQLException
	 */
	public static InventoryItem getDetails(String upc) throws ClassNotFoundException, SQLException
	{
		try(ResultSet rs = DatabaseGateway.retrieveUPC(upc))
		{
			InventoryItem item = null;
			if(rs != null)
			{
				item = InventoryItem.matchClassAndConstruct(rs.getInt("id"), rs.getString("className"));
			}
			rs.close();
			DatabaseGateway.closeStatements();
			return item;
		}
		catch(MySQLDataException e)
		{
			e.getCause();
		}
		return null;
	}
}