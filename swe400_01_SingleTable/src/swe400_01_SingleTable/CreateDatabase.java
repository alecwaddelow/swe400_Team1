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
			nail.setDescription("this is a nail boi");
			nail.setNumberInBox(Nails.values()[i].getNumberInBox());
			nail.setBatteryPowered(false);
			nail.setNumberInStrip(0);
			nail.setClassName("Nail");
			DatabaseGateway.insertRow(nail);
			uniqueID++;
		}
	}

}
