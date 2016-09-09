package swe400_01_SingleTable;

import java.sql.SQLException;
import java.sql.Statement;

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
		String sqlStatement = "CREATE TABLE InventoryItem (" + id + upc + manufacturerID + price + description + batteryPowered +
				length + numberInStrip + numberInBox + className + ");";

		Statement st = DatabaseGateway.getConnection().createStatement();
		st.execute(sqlStatement);
	}

	/**
	 * inserting the enum nails into the table
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(int i = 0;i < Nails.values().length; i++)
		{
			DBMapper nail = new DBMapper();
			nail.setId(uniqueID);
			nail.setUpc(Nails.values()[i].getUpc());
			nail.setManufacturerID(Nails.values()[i].getManufacturerID());
			nail.setPrice(Nails.values()[i].getPrice());
			nail.setLength(Nails.values()[i].getLength());
			nail.setNumberInBox(Nails.values()[i].getNumberInBox());
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
		for(int i = 0;i < Nails.values().length; i++)
		{
			DBMapper tool = new DBMapper();
			tool.setId(uniqueID);
			tool.setUpc(Tools.values()[i].getUpc());
			tool.setManufacturerID(Tools.values()[i].getManufacturerID());
			tool.setPrice(Tools.values()[i].getPrice());
			tool.setDescription(Tools.values()[i].getDescription());
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
		for(int i = 0;i < StripNails.values().length; i++)
		{
			DBMapper stripNail = new DBMapper();
			stripNail.setId(uniqueID);
			stripNail.setUpc(StripNails.values()[i].getUpc());
			stripNail.setManufacturerID(StripNails.values()[i].getManufacturerID());
			stripNail.setPrice(StripNails.values()[i].getPrice());
			stripNail.setLength(StripNails.values()[i].getLength());
			stripNail.setNumberInStrip(StripNails.values()[i].getNumberInStrip());
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
		for(int i = 0;i < PowerTools.values().length; i++)
		{
			DBMapper powerTool = new DBMapper();
			powerTool.setId(uniqueID);
			powerTool.setUpc(PowerTools.values()[i].getUpc());
			powerTool.setManufacturerID(PowerTools.values()[i].getManufacturerID());
			powerTool.setPrice(PowerTools.values()[i].getPrice());
			powerTool.setDescription(PowerTools.values()[i].getDescription());
			powerTool.setClassName("PowerTool");
			DatabaseGateway.insertRow(powerTool);
			uniqueID++;
		}
	}
}
