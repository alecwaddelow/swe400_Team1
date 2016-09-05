package swe400_01_SingleTable;

import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class Tool extends InventoryItem
{
	public String description;

	/**
	 * Finder Constructor
	 * @author Alec Waddelow
	 *
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/* Finder Constructor */
	public Tool(int id) throws ClassNotFoundException, SQLException
	{
		this.id = id;

		Runner runner = new Runner();
		DBReturnSet dbrs;
		dbrs = runner.queryDB(this.id);

		this.upc = dbrs.getUpc();
		this.manufacturerID = dbrs.getManufacturerID();
		this.price = dbrs.getPrice();
		this.description = dbrs.getDescription();
	}
}
