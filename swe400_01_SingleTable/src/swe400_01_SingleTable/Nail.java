package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class Nail extends Fastener
{
	public int numberInBox;

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

//	/* Creation Constructor */
//	public Nail(int id, String upc, int manufacturerID, int price, String description,
//			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
//	{
//
//	}
}
