package domain_layer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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
		ResultSet rs = DatabaseGateway.queryStripNail(this.id);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			double length = rs.getDouble("length");
			int numberInStrip = rs.getInt("numberInStrip");
			String className = rs.getString("className");
			StripNailsMapper stripNailMapper = new StripNailsMapper(upc, manufacturerID, price, length, numberInStrip, className);
			
			setUpc(stripNailMapper.getUpc());
			setManufacturerID(stripNailMapper.getManufacturerID());
			setPrice(stripNailMapper.getPrice());
			setLength(stripNailMapper.getLength());
			setNumberInStrip(stripNailMapper.getNumberInStrip());
			setClassName(stripNailMapper.getClassName());
		}
		else
		{
			ClassNotFoundException exception = new ClassNotFoundException("Could not find stripnail with specified ID");
			exception.getMessage();
		}
	}

	/**
	 * Creation Constructor that creates the strip nail
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param length
	 * @param numberInStrip
	 * @param numberInBox
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public StripNail(String upc, int manufacturerID, int price, double length, int numberInStrip,  String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, length, className);
		this.numberInStrip = numberInStrip;
		this.className = className;

		StripNailsMapper mapper = new StripNailsMapper(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.className);
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
	 * sets the id of the object
	 */
	public void setId(int id)
	{
		super.setId(id);
	}
	
	/**
	 * gets the id of the object
	 */
	public int getId()
	{
		return super.getId();
	}

	/**
	 * @return the numberInStrip
	 */
	public int getNumberInStrip()
	{
		return this.numberInStrip;
	}

	/**
	 * @param numberInStrip the numberInStrip for StripNails
	 */
	public void setNumberInStrip(int numberInStrip)
	{
		this.numberInStrip = numberInStrip;
	}
	
	/**
	 * Getter for PowerTool list, lazyloads the list 
	 * 
	 * @return the powerToolList
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
	 * Add  single powerTool to the list 
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "StripNail [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", length=" + this.length + ", numberInStrip=" + this.numberInStrip + "]";
	}

	/** Load Implementation for StripNail
	 * @return 
	 * @see domain_layer.LoadInterface#load()
	 */
	@Override
	public void load() throws SQLException, ClassNotFoundException 
	{
		this.powerToolList = new ArrayList<PowerTool>();
		ResultSet rs = LinkTableGateway.queryDBForPowerTools(this.getId());
		
		while(rs.next())
		{
			int id = rs.getInt("powerToolID");
			this.addPowerToolToList(new PowerTool(id));
		}
	}

	/**
	 * @return classname of object
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/**
	 * sets the className
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
}