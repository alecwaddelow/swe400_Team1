package domain_layer;

import java.sql.SQLException;

import org.junit.experimental.theories.Theories;

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
	protected DatabaseGateway gateway;
	
	/**
	 * Constructor for PowerToolMapper
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param className
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PowerToolMapper(String upc, int manufacturerID, int price, String description, boolean batteryPowered, String className) throws ClassNotFoundException, SQLException 
	{
		super(upc, manufacturerID, price, className);
		this.batteryPowered = batteryPowered;
		this.description = description;
	}

	public void insertPowerTool() throws ClassNotFoundException, SQLException
	{
		gateway.insertPowerTool(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.className);
		setId(this.upc);		
	}
	
	/**
	 * Empty constructor
	 */
	public PowerToolMapper() {}
	
	/**
	 * sets the id of the mapper
	 */
	public void setId(String upc) throws ClassNotFoundException, SQLException
	{
		super.setId(this.upc);
	}
	
	/**
	 * returns the id of the mapper
	 */
	public int getId()
	{
		return super.getId();
	}
	
	/**
	 * @return true if the item is battery powered; false otherwise
	 */
	public boolean isBatteryPowered()
	{
		return this.batteryPowered;
	}

	/**
	 * set true if the item is battery powered; false otherwise
	 * @param batteryPowered
	 */
	public void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}
	
	/**
	 * @return the item's description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * sets the item's description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * updates the powertool
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
		
		gateway.updatePowerToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.batteryPowered, this.id);
	}
}
