package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class Nail extends Fastener
{
	public int numberInBox;
	public String className;
	public String description;

	/* Finder Constructor */
	public Nail(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;

		Runner runner = new Runner();
		DBReturnSet dbrs;

		dbrs = runner.queryDB(id);
		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.length = dbrs.getLength();
		this.numberInBox = dbrs.getNumberInBox();
	}

	/* Creation Constructor */
	public Nail(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		this.length = length;
		this.numberInBox = numberInBox;
		this.className = className;

		DBReturnSet dbrs = new DBReturnSet();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setDescription(this.description);
		dbrs.setLength(this.length);
		dbrs.setNumberInBox(this.numberInBox);
		dbrs.setClassName(this.className);
		Runner runner = new Runner();
		runner.insertRow(dbrs);
	}
}
