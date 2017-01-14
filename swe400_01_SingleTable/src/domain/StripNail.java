package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_source.*;
import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An StripNail that is a Fastener
 */
public class StripNail extends Fastener implements LoadInterface
{
	protected int numberInStrip;
	protected ArrayList <PowerTool> powerToolList;

	/**
	 * Finder Constructor that calls queries the database for the specified strip nail by their ID
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public StripNail(int id) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		super(id);
		InventoryItemDTO dto = InventoryItemGateway.queryStripNail(this.id);
		if(dto != null)
		{
			this.upc = dto.getUpc();
			this.manufacturerID = dto.getManufacturerID();
			this.price = dto.getPrice();
			this.length = dto.getLength();
			this.numberInStrip = dto.getNumberInStrip();
			this.className = "StripNail";
		}
		else
		{
			ItemNotFoundException exception = new ItemNotFoundException("Could not find StripNail with specified ID");
			exception.getMessage();
		}
	}

	/**
	 * Creation Constructor
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public StripNail(String upc, int manufacturerID, int price, double length, int numberInStrip,  String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, length, className);
		this.numberInStrip = numberInStrip;
		this.className = className;
		InventoryItemGateway.insertStripNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.className);
		this.id = InventoryItemGateway.getID(this.upc, this.className);
	}

	/**
	 * Empty Constructor 
	 */
	public StripNail() 
	{
		super();
	}

	/**
	 * Get numberInStrip
	 * 
	 * @return numberInStrip
	 */
	public int getNumberInStrip()
	{
		return this.numberInStrip;
	}

	/**
	 * Set numberInStrip
	 * 
	 * @param numberInStrip 
	 */
	public void setNumberInStrip(int numberInStrip)
	{
		this.numberInStrip = numberInStrip;
	}
	
	/**
	 * Getter for PowerTool list, Lazy Instantiation of list  
	 * 
	 * @return ArrayList<PowerTool> powerToolList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public ArrayList<PowerTool> getPowerToolList() throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		if(this.powerToolList == null)
		{
			this.load();
			return this.powerToolList;
		}
		else
		{
			return this.powerToolList;
		}
	}
	
	/**
	 * Add single powerTool to the list 
	 * 
	 * @param PowerTool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public void addPowerToolToList(PowerTool tool) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		if(this.powerToolList == null)
		{
			this.load();
		}
		else
		{
			this.powerToolList.add(tool);
		}
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "StripNail [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", length=" + this.length + ", numberInStrip=" + this.numberInStrip + "]";
	}

	/**
	 * Load Implementation for StripNail
	 * @throws ItemNotFoundException 
	 * 
	 * @see domain.LoadInterface#load()
	 */
	@Override
	public void load() throws SQLException, ClassNotFoundException, ItemNotFoundException 
	{
		this.powerToolList = new ArrayList<PowerTool>();
		List<LinkTableDTO> listLinkTableDTO = LinkTableGateway.queryDBForPowerTools(this.getId());
		for(LinkTableDTO ltDTO : listLinkTableDTO)
		{
			PowerTool powerTool = new PowerTool(ltDTO.getPowerToolID());
			if(!this.powerToolList.contains(powerTool))
			{
				this.addPowerToolToList(powerTool);
			}
		}
	}

	/**
	 * Removes powertool from the list
	 * 
	 * @param powerTool
	 */
	public void removePowerToolFromList(PowerTool powerTool) 
	{
		ArrayList<Object> toRemove = new ArrayList<>();
		for(PowerTool pTool : this.powerToolList)
		{
			if(comparePowerTools(pTool, powerTool))
			{
				toRemove.add(pTool);
			}
		}	
		this.powerToolList.removeAll(toRemove);
	}

	/**
	 * Compares two PowerTool objects 
	 * 
	 * @param pTool
	 * @param powerTool
	 * @return boolean
	 */
	private static boolean comparePowerTools(PowerTool pTool, PowerTool powerTool) 
	{
		if(pTool.getUpc().equalsIgnoreCase(powerTool.getUpc())
		&& pTool.getManufacturerID() == powerTool.getManufacturerID()
		&& pTool.getPrice() == powerTool.getPrice()
		&& pTool.getDescription().equalsIgnoreCase(powerTool.getDescription())
		&& pTool.isBatteryPowered() == powerTool.isBatteryPowered())
		{
			return true;
		}
		else 
		{
			return false;
		}		
	}

	/**
	 * updates all the fields of the item locally and to the DB
	 * 
	 * @param upc
	 * @param manufacturerIDParse
	 * @param priceParse
	 * @param lengthParse
	 * @param numberInStripParse
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void update(String upc, int manufacturerIDParse, int priceParse, double lengthParse, int numberInStripParse) throws ClassNotFoundException, SQLException 
	{
		this.upc = upc;
		this.manufacturerID = manufacturerIDParse;
		this.price = priceParse;
		this.length = lengthParse;
		this.numberInStrip = numberInStripParse;
		
		InventoryItemGateway.updateStripNailToDB(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.id);
	}

	/**
	 * removes the stripnail from the table
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void removeFromTable() throws ClassNotFoundException, SQLException 
	{
		InventoryItemGateway.removeItem(this.id);
	}

	/**
	 * removes a compatible powertool
	 * 
	 * @param powerToolID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public void removeCompatiblePowerTool(int powerToolID) throws ClassNotFoundException, SQLException, ItemNotFoundException 
	{
		LinkTableGateway.removeRelation(powerToolID, this.id);
		removePowerToolFromList(new PowerTool(powerToolID));
	}
	
	/**
	 * adds a compatible powertool to the database and to the list
	 * 
	 * @param powerToolID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ItemNotFoundException 
	 */
	public void addCompatiblePowerTool(int powerToolID) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		LinkTableGateway.addRelation(powerToolID, this.id);
		addPowerToolToList(new PowerTool(powerToolID));
	}
}