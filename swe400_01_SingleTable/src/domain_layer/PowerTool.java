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
		ResultSet rs = DatabaseGateway.queryPowerTool(this.id);
		
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
	}

	/**
	 * Creation Constructor that creates the PowerTool
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
	}
	
	/**
	 * Empty constructor for testing 
	 */
	public PowerTool()
	{
		super();
	}

	/**
	 * @return true if battery powered; false otherwise
	 */
	public boolean isBatteryPowered()
	{
		return this.batteryPowered;
	}

	/**
	 * sets true if the powertool is battery powered; false otherwise
	 * @param batteryPowered
	 */
	public void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}

	/**
	 * @return the description of the powertool
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * @param description the description of the powertool
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Getter for stripNail list, lazyloads the list
	 * 
	 * @return ArrayList<StripNail> 
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
	 * toString 
	 */
	@Override
	public String toString() 
	{
		return "PowerTool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", description=" + this.description + ", batteryPowered=" + this.batteryPowered + "]";
	}

	/** 
	 * Load method for that Lazy Loads StripNail
	 * @see domain_layer.LoadInterface#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, SQLException 
	{
		this.stripNailList = new ArrayList<StripNail>();
		ResultSet rs = LinkTableGateway.queryDBForStripNails(this.getId());

		while(rs.next())
		{
			int id = rs.getInt("stripNailID");
			this.addStripNailToList(new StripNail(id));
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
	 * sets the classname of the PowerTool object
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
	
	public static void update(Scanner sc, InventoryItem item) throws SQLException 
	{
		PowerTool powerTool = (PowerTool) item;
		
		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
		
		System.out.println("Plase enter the UPC:");
		String upc = sc.nextLine();
		
		System.out.println("Please enter the manufacturerID:");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter the price of the item:");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter the description");
		String description = sc.nextLine();
		
		System.out.println("If it's battery powered please type true, if not type false");
		String batteryPowered = sc.nextLine();
		
		powerTool.setUpc(upc);
		powerTool.setManufacturerID(manufacturerIDParse);
		powerTool.setPrice(priceParse);
		powerTool.setDescription(description);

		boolean isBatteryPowered;
		if(batteryPowered.equalsIgnoreCase("true"))
		{
			powerTool.setBatteryPowered(true);
			isBatteryPowered = true;
		}
		else
		{
			powerTool.setBatteryPowered(false);
			isBatteryPowered = false;
		}
		
		DatabaseGateway.updatePowerToolToDB(upc, manufacturerIDParse, priceParse, description, isBatteryPowered, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(powerTool.toString());
	}
}