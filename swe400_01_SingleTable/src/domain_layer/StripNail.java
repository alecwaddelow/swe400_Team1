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
	}

	/**
	 * Empty Constructor 
	 */
	public StripNail() 
	{
		super();
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
	
	public static void update(Scanner sc, InventoryItem item) throws SQLException 
	{
		StripNail stripNail = (StripNail) item;
		
		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
		
		System.out.println("Plase enter the UPC:");
		String upc = sc.nextLine();
		
		System.out.println("Please enter the manufacturerID:");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter the price of the item:");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter length \n");
		String length = sc.nextLine();
		double lengthParse = Double.parseDouble(length);
		
		System.out.println("Please enter Number in Strip \n");
		String numberInStrip = sc.nextLine();
		int numberInStripParse = Integer.parseInt(numberInStrip);
		
		stripNail.setUpc(upc);
		stripNail.setManufacturerID(manufacturerIDParse);
		stripNail.setPrice(priceParse);
		stripNail.setLength(lengthParse);
		stripNail.setNumberInStrip(numberInStripParse);	
		
		DatabaseGateway.updateStripNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInStripParse, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(stripNail.toString());
	}
}