package swe400_01_SingleTable;
import java.sql.SQLException;

/**
 * @author Drew Rife & Alec Waddelow
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
	
	/**
	 * Creation Constructor 
	 * @author Alec Waddelow 
	 * 
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
	public Tool(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		
		DBReturnSet dbrs = new DBReturnSet();
		dbrs.setId(this.id);
		dbrs.setUpc(this.upc);
		dbrs.setManufacturerID(this.manufacturerID);
		dbrs.setPrice(this.price);
		dbrs.setDescription(this.description);
		Runner runner = new Runner();
		runner.insertRow(dbrs);
	}
}