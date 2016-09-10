package swe400_01_SingleTable;

import java.sql.SQLException;
import java.sql.Statement;

import enums.Nails;
import enums.PowerTools;
import enums.StripNails;
import enums.Tools;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Creates the table within the Database and inserts the enum objects into it
 */
public class CreateDatabase
{

	static String id = "id INT PRIMARY KEY NOT NULL auto_increment, ";
	static String upc = "upc VARCHAR(25), ";
	static String manufacturerID = "manufacturerID INT, ";
	static String price = "price INT, ";
	static String description = "description VARCHAR(100), ";
	static String batteryPowered = "batteryPowered bool, ";
	static String length = "length DOUBLE, ";
	static String numberInStrip = "numberInStrip INT, ";
	static String numberInBox = "numberInBox INT, ";
	static String className = "className VARCHAR(25)";
	private static int uniqueID = 1;

	/**
	 * Creates the table for the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createTable() throws ClassNotFoundException, SQLException
	{
		dropTableBeforeCreation();

		String sqlStatement = "CREATE TABLE InventoryItem (" + id + upc + manufacturerID + price + description + batteryPowered +
				length + numberInStrip + numberInBox + className + ");";

		Statement st = DatabaseGateway.getConnection().createStatement();
		st.execute(sqlStatement);
	}

	/**
	 * Drops the table before creation if the table exists
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void dropTableBeforeCreation() throws ClassNotFoundException, SQLException
	{
		String dropTable = "DROP TABLE IF EXISTS InventoryItem;";
		Statement st = DatabaseGateway.getConnection().createStatement();
		st.execute(dropTable);
	}

	/**
	 * inserting the enum nails into the table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Nails n : Nails.values())
		{
			DBMapper nail = new DBMapper();
			nail.setId(uniqueID);
			nail.setUpc(n.getUpc());
			nail.setManufacturerID(n.getManufacturerID());
			nail.setPrice(n.getPrice());
			nail.setLength(n.getLength());
			nail.setNumberInBox(n.getNumberInBox());
			nail.setClassName("Nail");
			DatabaseGateway.insertRow(nail);
			uniqueID++;
		}
	}

	/**
	 * inserting the enum tools into the table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Tools t : Tools.values())
		{
			DBMapper tool = new DBMapper();
			tool.setId(uniqueID);
			tool.setUpc(t.getUpc());
			tool.setManufacturerID(t.getManufacturerID());
			tool.setPrice(t.getPrice());
			tool.setDescription(t.getDescription());
			tool.setClassName("Tool");
			DatabaseGateway.insertRow(tool);
			uniqueID++;
		}
	}

	/**
	 * inserting the enum strip nails into the table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertStripNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(StripNails sn : StripNails.values())
		{
			DBMapper stripNail = new DBMapper();
			stripNail.setId(uniqueID);
			stripNail.setUpc(sn.getUpc());
			stripNail.setManufacturerID(sn.getManufacturerID());
			stripNail.setPrice(sn.getPrice());
			stripNail.setLength(sn.getLength());
			stripNail.setNumberInStrip(sn.getNumberInStrip());
			stripNail.setClassName("StripNail");
			DatabaseGateway.insertRow(stripNail);
			uniqueID++;
		}
	}

	/**
	 * inserting the enum power tools into the table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(PowerTools pt : PowerTools.values())
		{
			DBMapper powerTool = new DBMapper();
			powerTool.setId(uniqueID);
			powerTool.setUpc(pt.getUpc());
			powerTool.setManufacturerID(pt.getManufacturerID());
			powerTool.setPrice(pt.getPrice());
			powerTool.setDescription(pt.getDescription());
			powerTool.setClassName("PowerTool");
			DatabaseGateway.insertRow(powerTool);
			uniqueID++;
		}
	}
}
