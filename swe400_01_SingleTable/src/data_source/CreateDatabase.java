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
		for(Nails n : Nails.values())
		{
			NailMapper nail = new NailMapper();
			nail.setUpc(n.getUpc());
			nail.setManufacturerID(n.getManufacturerID());
			nail.setPrice(n.getPrice());
			nail.setLength(n.getLength());
			nail.setNumberInBox(n.getNumberInBox());
			nail.setClassName("Nail");
			
			Nail nails = new Nail(nail.getUpc(), nail.getManufacturerID(), nail.getPrice(), nail.getLength(), nail.getNumberInBox(), nail.getClassName());	
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
		for(Tools t : Tools.values())
		{
			ToolMapper tool = new ToolMapper();
			
			tool.setUpc(t.getUpc());
			tool.setManufacturerID(t.getManufacturerID());
			tool.setPrice(t.getPrice());
			tool.setDescription(t.getDescription());
			tool.setClassName("Tool");
			
			Tool tools = new Tool(tool.getUpc(), tool.getManufacturerID(), tool.getPrice(), tool.getDescription(), tool.getClassName());
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
		for(StripNails sn : StripNails.values())
		{
			StripNailsMapper stripNail = new StripNailsMapper();
		
			stripNail.setUpc(sn.getUpc());
			stripNail.setManufacturerID(sn.getManufacturerID());
			stripNail.setPrice(sn.getPrice());
			stripNail.setLength(sn.getLength());
			stripNail.setNumberInStrip(sn.getNumberInStrip());
			stripNail.setClassName("StripNail");
			
			StripNail stripNails = new StripNail(stripNail.getUpc(), stripNail.getManufacturerID(), stripNail.getPrice(), stripNail.getLength(), stripNail.getNumberInStrip(), stripNail.getClassName());
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
		for(PowerTools pt : PowerTools.values())
		{
			PowerToolMapper powerTool = new PowerToolMapper();
			
			powerTool.setUpc(pt.getUpc());
			powerTool.setManufacturerID(pt.getManufacturerID());
			powerTool.setPrice(pt.getPrice());
			powerTool.setDescription(pt.getDescription());
			powerTool.setClassName("PowerTool");
			powerTool.setBatteryPowered(pt.isBatteryPowered());
			
			PowerTool powerTools = new PowerTool(powerTool.getUpc(), powerTool.getManufacturerID(), powerTool.getPrice(), powerTool.getDescription(), powerTool.isBatteryPowered(), powerTool.getClassName());
		}
	}
}
