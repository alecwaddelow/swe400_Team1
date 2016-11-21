package data_source;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private static PreparedStatement preparedStatement = null;

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

		preparedStatement = DatabaseGateway.getConnection().prepareStatement(sqlStatement);
		preparedStatement.execute(sqlStatement);
		preparedStatement.close();
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
		preparedStatement = DatabaseGateway.getConnection().prepareStatement(dropTable);
		preparedStatement.execute();
		preparedStatement.close();
	}
}