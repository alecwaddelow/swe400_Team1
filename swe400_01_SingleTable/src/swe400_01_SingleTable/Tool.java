package swe400_01_SingleTable;
import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem Tool
 */
public class Tool extends InventoryItem
{
	protected String description;

	/**
	 * Finder Constructor that queries the database for the item specified by their ID
	 *
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Tool(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		DBMapper dbrs;
		dbrs = DatabaseGateway.queryDB(this.id);

		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.description = dbrs.getDescription();
	}

	/**
	 * Creation Constructor that creates the tool
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
	protected Tool(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, double length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;

		DBMapper dbrs = new DBMapper();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setDescription(this.description);
		DatabaseGateway.insertRow(dbrs);
	}

	/**
	 * @return description
	 */
	protected String getDescription()
	{
		return description;
	}

	/**
	 * sets the description for the tool
	 * @param description
	 */
	protected void setDescription(String description)
	{
		this.description = description;
	}
}