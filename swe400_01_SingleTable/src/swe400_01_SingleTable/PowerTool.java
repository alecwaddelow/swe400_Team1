package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem
 */
public class PowerTool extends InventoryItem
{
	protected boolean batteryPowered;
	protected String description;

	/**
	 * Finder Constructor that calls queries the database for the specified PowerTool by their ID
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected PowerTool(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		DBMapper dbrs;
		dbrs = DatabaseGateway.queryDB(this.id);

		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.description = dbrs.getDescription();
		this.batteryPowered = dbrs.isBatteryPowered();
	}

	/**
	 * Creation Constructor that creates the PowerTool
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
	protected PowerTool (int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, double length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.batteryPowered = batteryPowered;

		DBMapper dbrs = new DBMapper();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setBatteryPowered(this.batteryPowered);
		DatabaseGateway.insertRow(dbrs);
	}

	/**
	 * @return true if battery powered; false otherwise
	 */
	protected boolean isBatteryPowered()
	{
		return batteryPowered;
	}

	/**
	 * sets true if the powertool is battery powered; false otherwise
	 * @param batteryPowered
	 */
	protected void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}

	/**
	 * @return the description of the powertool
	 */
	protected String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description of the powertool
	 */
	protected void setDescription(String description)
	{
		this.description = description;
	}
}
