package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem that is a Fastener
 */
public class StripNail extends Fastener
{
	public int numberInStrip;

	/**
	 * Finder Constructor that calls queries the database for the specified strip nail by their ID
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public StripNail(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		DBMapper dbrs;
		dbrs = DatabaseGateway.queryDB(this.id);

		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.length = dbrs.getLength();
		this.numberInStrip = dbrs.getNumberInStrip();
	}

	/**
	 * Creation Constructor that creates the strip nail
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
	public StripNail(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.length = length;
		this.numberInStrip = numberInStrip;

		DBMapper dbrs = new DBMapper();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setLength(this.length);
		dbrs.setNumberInStrip(this.numberInStrip);
		DatabaseGateway.insertRow(dbrs);
	}
}
