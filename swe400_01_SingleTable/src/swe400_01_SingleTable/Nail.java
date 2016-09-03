package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class Nail extends Fastener
{
	public int numberBox;

	/* Finder Constructor */
	public Nail(int id)
	{
		this.id = id;
	}

	/* Creation Constructor */
	public Nail(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		Runner runner = new Runner();
		DBReturnSet dbrs;

		dbrs = runner.queryDB(id);

	}
}
