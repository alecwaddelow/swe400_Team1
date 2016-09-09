package swe400_01_SingleTable;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class CreateDatabase
{

	static String id = "id INT PRIMARY KEY NOT NULL auto_increment, ";
	static String upc = "upc VARCHAR(25), ";
	static String manufacturerID = "manufacturerID INT, ";
	static String price = "price INT, ";
	static String description = "description VARCHAR(25), ";
	static String batteryPowered = "batteryPowered bool, ";
	static String length = "length DOUBLE, ";
	static String numberInStrip = "numberInStrip INT, ";
	static String numberInBox = "numberInBox INT, ";
	static String className = "className VARCHAR(25)";

	/**
	 * Creates the table for the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createTable() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "CREATE TABLE Inventoryitem (" + id + upc + manufacturerID + price + description + batteryPowered +
				length + numberInStrip + numberInBox + className + ");";

		Statement st = DatabaseGateway.getConnection().createStatement();
		st.execute(sqlStatement);
	}

	/**
	 * inserting the enum nails into the table
	 */
//	public static void insertNailsIntoTable()
//	{
//		int uniqueID = 1;
//		for(int i = 0; Nails.values()[i+1] != null; i++)
//		{
//			DBMapper nail = new DBMapper();
//			Nails.values()[i].
//		}
//	}

}
