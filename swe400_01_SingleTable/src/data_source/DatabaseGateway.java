package data_source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import domain_layer.*;

/**
 * @author Drew Rife and Alec Waddelow
 *
 * Establishes a connection to the database and contains common database functions
 */
public class DatabaseGateway
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12?useSSL=false";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";
	private static Connection con;

	/**
	 * Gets connection to the Database and returns the connection 
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(hostName, user, password);
			return con;
		}
		else
		{
			return con;
		}
	}

	/**
	 * Query nail function
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet queryNail(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;
	}
	
	/**
	 * Query for Tool 
	 * 
	 * @param id
	 * @return ResultSet 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet queryTool(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;
	}

	
	/**
	 * Query for PowerTool
	 * 
	 * @param id
	 * @return ResultSet 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet queryPowerTool(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;
	}
	
	/**
	 * Query for StripNail
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet queryStripNail(int id) throws SQLException, ClassNotFoundException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		return rs;
	}
	
	/**
	 * Inserts an item into the table within the database based on a prepared statement created by respective concrete class Mappers 
	 * 
	 * @param PreparedStatement statement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertRow(PreparedStatement statement) throws ClassNotFoundException, SQLException
	{
		statement.execute();
		statement.close();
	}
	
	/**
	 * Inserts a nail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertNail(String upc, int manufacturerID, int price, double length, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInBox, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1, upc);
		pst.setInt(2, manufacturerID);
		pst.setInt(3, price);
		pst.setDouble(4, length);
		pst.setInt(5,  numberInBox);
		pst.setString(6,  className);
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Inserts a Tool 
	 * 
	 * @param tool
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertTool(String upc, int manufacturerID, int price, String description, String className) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, className) VALUES(?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1, upc);
		pst.setInt(2, manufacturerID);
		pst.setInt(3, price);
		pst.setString(4, description);
		pst.setString(5, className);
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Insert a powerTool 
	 * 
	 * @param powerTool
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerTool(String upc, int manufacturerID, int price, String description, boolean batteryPowered, String className) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, batteryPowered, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1,  upc);
		pst.setInt(2, manufacturerID);
		pst.setInt(3, price);
		pst.setString(4,  description);
		pst.setBoolean(5, batteryPowered);
		pst.setString(6,  className);
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Insert a StripNail
	 * 
	 * @param stripNail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertStripNail(String upc, int manufacturerID, int price, double length, int numberInStrip, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInStrip, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1, upc);
		pst.setInt(2, manufacturerID);
		pst.setInt(3, price);
		pst.setDouble(4, length);
		pst.setInt(5,  numberInStrip);
		pst.setString(6,  className);
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Retrieves item by UPC 
	 * 
	 * @param upc
	 * @return InventoryItem
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet retrieveUPC(String upc) throws ClassNotFoundException, SQLException
	{
		String statement = "SELECT * FROM InventoryItem WHERE upc =" + "'" + upc + "'" + ";";
		PreparedStatement pst = DatabaseGateway.getConnection().prepareStatement(statement);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
		{
			return rs;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Builds the ArrayList of the objects
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet createList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> listOfObjects = new ArrayList<InventoryItem>(); 
		String sqlStatement = ("SELECT id,className FROM InventoryItem;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);

		return rs;
	}

	/**
	 * updates the fields of the nail in the db
	 * 
	 * @param nail
	 * @throws SQLException
	 */
	public static void updateNailToDB(String upc, int manufacturerID, int price, double length, int numberInBox, int id) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + upc
				+ ", manufacturerID=" + manufacturerID
				+ ", price=" + price
				+ ", length=" + length
				+ ", numberInBox=" + numberInBox
				+ " where id=" + id + ";";
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();		
	}

	/**
	 * updates the fields of the tool in the db
	 * 
	 * @param tool
	 * @throws SQLException
	 */
	public static void updateToolToDB(String upc, int manufacturerID, int price, String description, int id) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + upc
				+ ", manufacturerID=" + manufacturerID
				+ ", price=" + price
				+ ", description=" + "'" + description + "'"
				+ " where id=" + id + ";";

		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();		
	}

	/**
	 * updates the fields of the stripNail in the db
	 * 
	 * @param stripNail
	 * @throws SQLException
	 */
	public static void updateStripNailToDB(String upc, int manufacturerID, int price, double length, int numberInStrip, int id) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + upc
				+ ", manufacturerID=" + manufacturerID
				+ ", price=" + price
				+ ", length=" + length
				+ ", numberInStrip=" + numberInStrip
				+ " where id=" + id;
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();
	}

	/**
	 * updates the fields of the powerTool in the db
	 * 
	 * @param powerTool
	 * @throws SQLException
	 */
	public static void updatePowerToolToDB(String upc, int manufacturerID, int price, String description, boolean batteryPowered, int id) throws SQLException
	{
		String query = "update InventoryItem set"
				+ " upc=" + upc
				+ ", manufacturerID=" + manufacturerID
				+ ", price=" + price
				+ ", description=" + "'" + description + "'"
				+ ", batteryPowered=" + batteryPowered
				+ " where id=" + id + ";";
		System.out.println(query);
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();
		
	}

	
	
	/**
	 * returns the id of the item
	 * 
	 * @param upc
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getID(String upc) throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select id from InventoryItem where upc=" + "'" + upc + "'";
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rSet = st.executeQuery(sqlStatement);
		if(rSet.next())
		{
			return rSet.getInt("id");
		}
		else
		{
			return 0;
		}			
	}
	
	/**
	 * @return the resultset of querying for all StripNails
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet getStripNailUPCs() throws ClassNotFoundException, SQLException 
	{
		String sqlStatement = "select * from InventoryItem where className=" + "'" + "StripNail" + "'";
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		return rs;
	}

	/**
	 * @return the resultset of querying for all powertools
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet getPowerToolUPCs() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select * from InventoryItem where className=" + "'" + "PowerTool" + "'";
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		return rs;
	}
}
