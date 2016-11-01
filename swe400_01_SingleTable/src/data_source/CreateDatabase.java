package data_source;
import java.sql.SQLException;
import java.sql.Statement;
import domain_layer.*;
import enums.*;

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
	static String batteryPowered = "batteryPowered BOOL, ";
	static String length = "length DOUBLE, ";
	static String numberInStrip = "numberInStrip INT, ";
	static String numberInBox = "numberInBox INT, ";
	static String className = "className VARCHAR(25)";

	/**
	 * Creates the table for the database
	 * 
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
	 * Drops the table before creation if the table exists
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void dropTableBeforeCreation() throws ClassNotFoundException, SQLException
	{
		String dropTable = "DROP TABLE IF EXISTS InventoryItem;";
		Statement st = DatabaseGateway.getConnection().createStatement();
		st.execute(dropTable);
	}

	/**
	 * Inserting the enum nails into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Nails nail : Nails.values())
		{			
			new Nail(nail.getUpc(), nail.getManufacturerID(), nail.getPrice(), nail.getLength(), nail.getNumberInBox(), "Nail");	
		}
	}

	/**
	 * Inserting the enum tools into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Tools tool : Tools.values())
		{
			new Tool(tool.getUpc(), tool.getManufacturerID(), tool.getPrice(), tool.getDescription(), "Tool");
		}
	}

	/**
	 * Inserting the enum strip nails into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertStripNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(StripNails stripNail : StripNails.values())
		{			
			new StripNail(stripNail.getUpc(), stripNail.getManufacturerID(), stripNail.getPrice(), stripNail.getLength(), stripNail.getNumberInStrip(), "StripNail");
		}
	}

	/**
	 * Inserting the enum power tools into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(PowerTools powerTool : PowerTools.values())
		{
			new PowerTool(powerTool.getUpc(), powerTool.getManufacturerID(), powerTool.getPrice(), powerTool.getDescription(), powerTool.isBatteryPowered(), "PowerTool");
		}
	}
}
