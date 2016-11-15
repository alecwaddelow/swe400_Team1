package domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data_source.DatabaseGateway;
import data_source.LinkTableGateway;

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
	public StripNail(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
		ResultSet rs = null;
		rs = DatabaseGateway.queryStripNail(this.id);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			double length = rs.getDouble("length");
			int numberInStrip = rs.getInt("numberInStrip");
			String className = rs.getString("className");
			StripNailMapper stripNailMapper = new StripNailMapper(upc, manufacturerID, price, length, numberInStrip, className);
			setUpc(stripNailMapper.getUpc());
			setManufacturerID(stripNailMapper.getManufacturerID());
			setPrice(stripNailMapper.getPrice());
			setLength(stripNailMapper.getLength());
			setNumberInStrip(stripNailMapper.getNumberInStrip());
			setClassName(stripNailMapper.getClassName());
		}
		
		rs.close();
		DatabaseGateway.closeStatements();
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
		StripNailMapper mapper = new StripNailMapper(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.className);
		mapper.insertStripNail();
		setId(mapper.getId());
	}

	/**
	 * Empty Constructor 
	 */
	public StripNail() 
	{
		super();
	}
	

	/** 
	 * @see domain.InventoryItem#setId(int)
	 */
	public void setId(int id)
	{
		super.setId(id);
	}
	
	/** 
	 * @see domain.InventoryItem#getId()
	 */
	public int getId()
	{
		return super.getId();
	}


	/**
	 * @return int numberInStrip
	 */
	public int getNumberInStrip()
	{
		return this.numberInStrip;
	}

	/**
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
	 */
	public ArrayList<PowerTool> getPowerToolList() throws ClassNotFoundException, SQLException 
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
	 */
	public void addPowerToolToList(PowerTool tool) throws ClassNotFoundException, SQLException
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
	 * @see domain.LoadInterface#load()
	 */
	@Override
	public void load() throws SQLException, ClassNotFoundException 
	{
		this.powerToolList = new ArrayList<PowerTool>();
		
		ResultSet rs = null;
		rs = LinkTableGateway.queryDBForPowerTools(this.getId());
		
		while(rs.next())
		{
			int id = rs.getInt("powerToolID");
			this.addPowerToolToList(new PowerTool(id));
		}
		rs.close();
		DatabaseGateway.closeStatements();
	}

	/**
	 * @return String className
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/**
	 * Sets className
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}

	/**
	 * Removes a powertool from the list
	 * @param powerTool
	 */
	public void removePowerToolFromList(PowerTool powerTool) 
	{
		ArrayList<Object> toRemove = new ArrayList<>();
		for(PowerTool pTool : powerToolList)
		{
			if(comparePowerTools(pTool, powerTool))
			{
				toRemove.add(pTool);
			}
		}	
		
		this.powerToolList.removeAll(toRemove);
	}

	/**
	 * Compares two powertool objects 
	 * 
	 * @param pTool
	 * @param powerTool
	 * @return
	 */
	private boolean comparePowerTools(PowerTool pTool, PowerTool powerTool) 
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