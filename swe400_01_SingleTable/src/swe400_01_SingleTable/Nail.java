package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem that is a Fastener
 */
public class Nail extends Fastener
{
	public int numberInBox;
	public String className;
	public String description;


	protected int getNumberInBox()
	{
		return numberInBox;
	}

	protected void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}

	protected String getClassName()
	{
		return className;
	}

	protected void setClassName(String className)
	{
		this.className = className;
	}

	protected String getDescription()
	{
		return this.description;
	}

	protected void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Finder Constructor that calls queries the database for the specified nail by their ID
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Nail(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		DBMapper dbrs;

		dbrs = DatabaseGateway.queryDB(id);
		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.length = dbrs.getLength();
		this.numberInBox = dbrs.getNumberInBox();
	}

	/**
	 * Creation Constructor that creates the nail
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
	protected Nail(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, double length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		this.length = length;
		this.numberInBox = numberInBox;
		this.className = className;

		DBMapper dbrs = new DBMapper();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setDescription(this.description);
		dbrs.setLength(this.length);
		dbrs.setNumberInBox(this.numberInBox);
		dbrs.setClassName(this.className);
		DatabaseGateway.insertRow(dbrs);
	}
}
