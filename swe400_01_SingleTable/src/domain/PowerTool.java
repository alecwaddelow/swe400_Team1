package domain;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	protected List<StripNail> stripNailList;
	
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
	public List<StripNail> getStripNailList() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		if(this.stripNailList == null)
		{
			this.load();
		}
		
		return this.stripNailList;
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
		}
		
		this.stripNailList.add(stripNail);
	}
	
	/**
	 * Remove a StripNail from the list 
	 * 
	 * @param nail
	 * @throws ItemNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeStripNailFromList(StripNail stripNail) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		if(this.stripNailList == null)
		{
			this.load();
		}
		
		List<Object> toRemove = new ArrayList<>();
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
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForStripNails(this.id);
		for(LinkTableDTO ltDTO : listLinkTableDTO)
		{
			StripNail stripNail = new StripNail(ltDTO.getStripNailID());
			if(!this.stripNailList.contains(stripNail))
			{
				this.addStripNailToList(stripNail);
			}
		}
	}

	/**
	 * Updates this powertool and to the table
	 * 
	 * @param upc
	 * @param manufacturerIDParse
	 * @param priceParse
	 * @param description
	 * @param batteryPowered
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void update(String upc, int manufacturerIDParse, int priceParse, String description, boolean batteryPowered) throws ClassNotFoundException, SQLException 
	{
		this.upc = upc;
		this.manufacturerID = manufacturerIDParse;
		this.price = priceParse;
		this.description = description;
		this.batteryPowered = batteryPowered;
		
		InventoryItemGateway.updatePowerToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.id);
	}

	/**
	 * removes this powertool from the the InventoryItem table and LinkTable table
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeFromTable() throws ClassNotFoundException, SQLException 
	{
		InventoryItemGateway.removeItem(this.id);
	}

	/**
	 * removes the stripNail associated with it
	 * 
	 * @param stripNailID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public void removeCompatibleStripNail(int stripNailID) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		LinkTableGateway.removeRelation(this.id, stripNailID);
		removeStripNailFromList(new StripNail(stripNailID));
	}
	
	/**
	 * adds a stripnail as a compatible item to the database and to the list
	 * 
	 * @param stripNailID
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public void addCompatibleStripNail(int stripNailID) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		LinkTableGateway.addRelation(this.id, stripNailID);
		addStripNailToList(new StripNail(stripNailID));
	}
}