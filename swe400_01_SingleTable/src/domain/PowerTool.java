package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data_source.*;
import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * A PowerTool 
 */
public class PowerTool extends InventoryItem implements LoadInterface
{
	protected boolean batteryPowered;
	protected String description;
	protected ArrayList <StripNail> stripNailList;
	
	/**
	 * Finder Constructor that calls queries the database for the specified PowerTool by their ID
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PowerTool(int id) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		super(id);
		InventoryItemDTO dto = InventoryItemGateway.queryPowerTool(this.id);
		if(dto != null)
		{
			this.upc = dto.getUpc();
			this.manufacturerID = dto.getManufacturerID();
			this.price = dto.getPrice();
			this.description = dto.getDescription();
			this.batteryPowered = dto.isBatteryPowered();
			this.className = "PowerTool";
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
	 * @param batteryPowered
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PowerTool(String upc, int manufacturerID, int price, String description, boolean batteryPowered, String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, className);
		this.description = description;
		this.batteryPowered = batteryPowered;
		this.className = className;
		InventoryItemGateway.insertPowerTool(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.className);
		this.id = InventoryItemGateway.getID(this.upc, this.className);
	}
	
	/**
	 * Empty constructor for testing 
	 */
	public PowerTool()
	{
		super();
	}

	/**
	 * Get isBatteryPowered
	 * 
	 * @return boolean true if battery powered; false otherwise
	 */
	public boolean isBatteryPowered()
	{
		return this.batteryPowered;
	}

	/**
	 * Sets whether item is batteryPowered
	 * 
	 * @param batteryPowered
	 */
	public void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
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
	 * Sets description
	 * 
	 * @param description 
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Getter for stripNail list, Lazy Instantiation of list 
	 * 
	 * @return ArrayList<StripNail>  stripNailList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public ArrayList<StripNail> getStripNailList() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		if(this.stripNailList == null)
		{
			this.load();
			return this.stripNailList;
		}
		else
		{
			return this.stripNailList;			
		}
	}
		
	/**
	 * Add a single StripNail to the list 
	 * 
	 * @param nail
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public void addStripNailToList(StripNail stripNail) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		if(this.stripNailList == null)
		{
			this.load();
			this.stripNailList.add(stripNail);			
		}
		else
		{
			this.stripNailList.add(stripNail);
		}
	}
	
	/**
	 * Remove a StripNail from the list 
	 * 
	 * @param nail
	 */
	public void removeStripNailFromList(StripNail stripNail)
	{
		ArrayList<Object> toRemove = new ArrayList<>();
		for(StripNail sNail : this.stripNailList)
		{
			if(compareStripNails(sNail, stripNail))
			{
				toRemove.add(sNail);
			}
		}
		this.stripNailList.removeAll(toRemove);
	}

	/**
	 * Compares two stripnail objects 
	 * 
	 * @param pTool
	 * @param powerTool
	 * @return
	 */
	private static boolean compareStripNails(StripNail sNail, StripNail stripNail) 
	{
		if(sNail.getUpc().equalsIgnoreCase(stripNail.getUpc())
		&& sNail.getManufacturerID() == stripNail.getManufacturerID()
		&& sNail.getPrice() == stripNail.getPrice()
		&& sNail.getLength() == stripNail.getLength()
		&& sNail.getNumberInStrip() == stripNail.getNumberInStrip())
		{
			return true;
		}
		else
		{
			return false;
		}		
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "PowerTool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", description=" + this.description + ", batteryPowered=" + this.batteryPowered + "]";
	}

	/** 
	 * Load method that Lazy Loads StripNail
	 * @throws ItemNotFoundException 
	 * 
	 * @see domain.LoadInterface#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		this.stripNailList = new ArrayList<StripNail>();
		ResultSet rs = LinkTableGateway.queryDBForStripNails(this.id);
		while(rs.next())
		{
			int id = rs.getInt("stripNailID");
			StripNail stripNail = new StripNail(id);
			if(!this.stripNailList.contains(stripNail))
			{
				this.addStripNailToList(stripNail);					
			}
		}
		rs.close();
		InventoryItemGateway.closeStatements();
	}

	public void update(String upc, int manufacturerIDParse, int priceParse, String description, boolean batteryPowered) throws ClassNotFoundException, SQLException 
	{
		this.upc = upc;
		this.manufacturerID = manufacturerIDParse;
		this.price = priceParse;
		this.description = description;
		this.batteryPowered = batteryPowered;
		
		InventoryItemGateway.updatePowerToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.id);
	}
}