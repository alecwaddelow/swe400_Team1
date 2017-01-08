package domain;

import java.sql.SQLException;
import data_source.*;
import exceptions.ItemNotFoundException;

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
	public Tool(int id) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		super(id);
		InventoryItemDTO dto = InventoryItemGateway.queryTool(this.id);
		if(dto != null)
		{
			this.upc = dto.getUpc();
			this.manufacturerID = dto.getManufacturerID();
			this.price = dto.getPrice();
			this.description = dto.getDescription();
			this.className = "Tool";
		}
		else
		{
			ItemNotFoundException exception = new ItemNotFoundException("Could not find Nail with specified ID");
			exception.getMessage();
		}
		
	}
		
	/**
	 * Creation Constructor
	 * 
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
		InventoryItemGateway.insertTool(this.upc, this.manufacturerID, this.price, this.description, this.className);
		this.id = InventoryItemGateway.getID(this.upc, this.className);
	}

	/**
	 * Get description
	 * 
	 * @return String description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Sets the description for the tool
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Tool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price
				+ ", description=" + this.description + "]";
	}

	public void update(String upc, int manufacturerIDParse, int priceParse, String description) throws ClassNotFoundException, SQLException 
	{
		this.upc = upc;
		this.manufacturerID = manufacturerIDParse;
		this.price = priceParse;
		this.description = description;
		
		InventoryItemGateway.updateToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.id);
	}

}