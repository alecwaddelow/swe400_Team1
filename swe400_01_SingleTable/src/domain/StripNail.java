package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		DataTransferObject dto = InventoryItemGateway.queryNail(this.id);
		if(dto != null)
		{
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
		ResultSet rs = LinkTableGateway.queryDBForPowerTools(this.getId());
		while(rs.next())
		{
			int id = rs.getInt("powerToolID");
			PowerTool powerTool = new PowerTool(id);
			if(!this.powerToolList.contains(powerTool))
			{
				this.addPowerToolToList(powerTool);
			}
		}
		rs.close(); 
		InventoryItemGateway.closeStatements();
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
}