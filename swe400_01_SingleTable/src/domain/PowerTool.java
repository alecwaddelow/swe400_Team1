package domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

import data_source.DatabaseGateway;
import data_source.LinkTableGateway;

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
	public PowerTool(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
		try(ResultSet rs = DatabaseGateway.queryPowerTool(this.id))
		{
			if(rs.next())
			{
				String upc = rs.getString("upc");
				int manufacturerID = rs.getInt("manufacturerID");
				int price = rs.getInt("price");
				String description = rs.getString("description");
				boolean batteryPowered = rs.getBoolean("batteryPowered");
				String className = rs.getString("className");
				PowerToolMapper powerToolMapper = new PowerToolMapper(upc, manufacturerID, price, description, batteryPowered, className);
				setUpc(powerToolMapper.getUpc());
				setManufacturerID(powerToolMapper.getManufacturerID());
				setPrice(powerToolMapper.getPrice());
				setDescription(powerToolMapper.getDescription());
				setBatteryPowered(powerToolMapper.isBatteryPowered());
				setClassName(powerToolMapper.getClassName());
			}
			else
			{
				ClassNotFoundException exception = new ClassNotFoundException("Could not find PowerTool with specified ID");
				exception.getMessage();
			}	
			rs.close();
			DatabaseGateway.closeStatements();
		}
		catch(MySQLDataException e)
		{
			e.getCause();
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
		PowerToolMapper mapper = new PowerToolMapper(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.className);
		mapper.insertPowerTool();
		setId(mapper.getId());
	}
	
	/**
	 * Empty constructor for testing 
	 */
	public PowerTool()
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
	 */
	public ArrayList<StripNail> getStripNailList() throws ClassNotFoundException, SQLException 
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
	 */
	public void addStripNailToList(StripNail nail) throws ClassNotFoundException, SQLException
	{
		if(this.stripNailList == null)
		{
			this.load();
			this.stripNailList.add(nail);			
		}
		else
		{
			this.stripNailList.add(nail);
		}
	}
	
	/**
	 * Remove a StripNail from the list 
	 * 
	 * @param nail
	 */
	public void removeStripNailFromList(StripNail nail)
	{
		this.stripNailList.remove(nail);
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
	 * Load method for that Lazy Loads StripNail
	 * 
	 * @see domain.LoadInterface#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, SQLException 
	{
		this.stripNailList = new ArrayList<StripNail>();
		try(ResultSet rs = LinkTableGateway.queryDBForStripNails(this.getId()))
		{
			while(rs.next())
			{
				int id = rs.getInt("stripNailID");
				this.addStripNailToList(new StripNail(id));
			}
			rs.close();
			DatabaseGateway.closeStatements();
		}
		catch (MySQLDataException e)
		{
			e.getCause();
		}
	}

	/**
	 * @return the name of the class
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/** 
	 * @see domain.InventoryItem#setClassName(java.lang.String)
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
}