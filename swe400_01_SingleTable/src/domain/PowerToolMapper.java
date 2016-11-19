package domain;
import java.sql.SQLException;
import data_source.DatabaseGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * A mapper for PowerTool Objects
 */
public class PowerToolMapper extends DBMapper
{
	protected String description;
	protected boolean batteryPowered;
	
	/**
	 * Constructor 
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
	public PowerToolMapper(String upc, int manufacturerID, int price, String description, boolean batteryPowered, String className) throws ClassNotFoundException, SQLException 
	{
		super(upc, manufacturerID, price, className);
		this.batteryPowered = batteryPowered;
		this.description = description;
	}

	/**
	 * Insert PowerTool
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertPowerTool() throws ClassNotFoundException, SQLException
	{
		DatabaseGateway.insertPowerTool(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.className);
		setId();		
	}
	
	/**
	 * Empty constructor
	 */
	public PowerToolMapper() {}
	
	/** 
	 * @see domain.DBMapper#setId(java.lang.String)
	 */
	public void setId() throws ClassNotFoundException, SQLException
	{
		super.setId();
	}
	

	/** 
	 * @see domain.DBMapper#getId()
	 */
	public int getId()
	{
		return super.getId();
	}
	
	/**
	 * @return boolean true if the item is battery powered; false otherwise
	 */
	public boolean isBatteryPowered()
	{
		return this.batteryPowered;
	}

	/**
	 * Set true if the item is battery powered; false otherwise
	 * 
	 * @param batteryPowered
	 */
	public void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}
	
	/**
	 * @return String item description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Sets the item's description
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Updates powertool
	 * 
	 * @param powerTool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updatePowerTool(PowerTool powerTool) throws SQLException, ClassNotFoundException 
	{
		this.id = powerTool.getId();
		setUpc(powerTool.getUpc());
		setManufacturerID(powerTool.getManufacturerID());
		setPrice(powerTool.getPrice());
		setDescription(powerTool.getDescription());
		setBatteryPowered(powerTool.isBatteryPowered());
		setClassName(powerTool.getClassName());
		DatabaseGateway.updatePowerToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.id);
	}
}