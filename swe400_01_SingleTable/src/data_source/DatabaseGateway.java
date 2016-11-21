package data_source;
import java.sql.*;

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
	private static Connection con = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

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
	 * Query for Nail
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet queryNail(int id) throws ClassNotFoundException, SQLException
	{		
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
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
		String sqlStatement = ("SELECT * FROM InventoryItem where id=?");
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
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
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
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
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
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
	 * Insert Nail
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertNail(String upc, int manufacturerID, int price, double length, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInBox, className) VALUES(?,?,?,?,?,?)";
		preparedStatement = getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5,  numberInBox);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
		closeStatements();
	}
	
	/**
	 * Insert Tool 
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param className
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertTool(String upc, int manufacturerID, int price, String description, String className) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, className) VALUES(?,?,?,?,?)";
		preparedStatement  = getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setString(5, className);
		insertRow(preparedStatement);
		closeStatements();
	}
	
	/**
	 * Insert PowerTool
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param className
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerTool(String upc, int manufacturerID, int price, String description, boolean batteryPowered, String className) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, batteryPowered, className) VALUES(?,?,?,?,?,?)";
		preparedStatement = getConnection().prepareStatement(statement);
		preparedStatement.setString(1,  upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4,  description);
		preparedStatement.setBoolean(5, batteryPowered);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
		closeStatements();
	}
	
	/**
	 * Insert StripNail
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertStripNail(String upc, int manufacturerID, int price, double length, int numberInStrip, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInStrip, className) VALUES(?,?,?,?,?,?)";
		preparedStatement  = getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5,  numberInStrip);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
		closeStatements();
	}
	
	/**
	 * Retrieves item by UPC and ClassName
	 * 
	 * @param upc
	 * @param className
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet retrieveUPC(String upc, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "SELECT * FROM InventoryItem WHERE upc=? and className=?";
		preparedStatement = getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setString(2, className);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}
	
	/**
	 * Builds the ArrayList of the objects
	 * 
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet createList() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT id,className FROM InventoryItem");
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		resultSet = preparedStatement.executeQuery();
		return resultSet; 
	}

	/**
	 * Updates a single nail in the DB
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @param id
	 * @throws SQLException
	 */
	public static void updateNailToDB(String upc, int manufacturerID, int price, double length, int numberInBox, int id) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=?"
				+ ", manufacturerID=?"
				+ ", price=?"
				+ ", length=?"
				+ ", numberInBox=?"
				+ " where id=?";
		
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5, numberInBox);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		closeStatements();
	}

	/**
	 * Updates a single Tool in the DB
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updateToolToDB(String upc, int manufacturerID, int price, String description, int id) throws SQLException, ClassNotFoundException 
	{
		String query = "update InventoryItem set"
				+ " upc=?"
				+ ", manufacturerID=?"
				+ ", price=?"
				+ ", description=?"
				+ " where id=?";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setInt(5, id);
		preparedStatement.executeUpdate();	
		closeStatements();
	}

	/**
	 * Updates a single StripNail in the DB
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updateStripNailToDB(String upc, int manufacturerID, int price, double length, int numberInStrip, int id) throws SQLException, ClassNotFoundException 
	{
		String query = "update InventoryItem set"
				+ " upc=?"
				+ ", manufacturerID=?"
				+ ", price=?"
				+ ", length=?"
				+ ", numberInStrip=?"
				+ " where id=?";
		
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5, numberInStrip);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		closeStatements();
	}

	/**
	 * Updates a single PowerTool to the DB
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updatePowerToolToDB(String upc, int manufacturerID, int price, String description, boolean batteryPowered, int id) throws SQLException, ClassNotFoundException
	{
		String query = "update InventoryItem set"
				+ " upc=?"
				+ ", manufacturerID=?"
				+ ", price=?"
				+ ", description=?"
				+ ", batteryPowered=?"
				+ " where id=?";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setBoolean(5, batteryPowered);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		closeStatements();
	}

	/**
	 * Returns the ID of the Item that matches the UPC and className
	 * 
	 * @param upc
	 * @param className
	 * @return id of InventoryItem
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getID(String upc, String className) throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select id from InventoryItem where upc=? and className=?";
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, upc);
		preparedStatement.setString(2, className);
		resultSet = preparedStatement.executeQuery();
		
		int id = 0;
		if(resultSet.next())
		{
			id = resultSet.getInt("id");
			closeStatements();
		}
		return id;
	}
	
	/**
	 * Returns stripNail UPC's 
	 * 
	 * @return ResultSet for all StripNails
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet getStripNailUPCs() throws ClassNotFoundException, SQLException 
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "StripNail");
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	/**
	 * Returns PowerTool UPC's
	 * 
	 * @return ResultSet all powertools
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet getPowerToolUPCs() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		preparedStatement = getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "PowerTool");
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}
	
	/**
	 * Closes the statements when done
	 * 
	 * @throws SQLException
	 */
	public static void closeStatements() throws SQLException
	{
		if(resultSet != null)
		{
			if(!resultSet.isClosed())
			{
				resultSet.close();
				resultSet = null;
			}
		}
		
		if(preparedStatement != null)
		{
			if(!preparedStatement.isClosed())
			{
				preparedStatement.close();
				preparedStatement = null;
			}			
		}
	}
	
	/**
	 * Closes the connection when finished 
	 * 
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException
	{
		closeStatements();
		
		if(con != null)
		{
			if(!con.isClosed())
			{
				con.close();
			}
		}
	}
}