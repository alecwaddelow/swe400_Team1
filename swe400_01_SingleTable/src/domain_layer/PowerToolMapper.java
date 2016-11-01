package domain_layer;

import java.sql.SQLException;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

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
	}
	/**
	 * Empty constructor
	 */
	public PowerToolMapper() {}

	
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
}
