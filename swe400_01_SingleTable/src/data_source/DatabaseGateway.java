package data_source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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
	 * Query nail function, returns a nailmapper 
	 * 
	 * @param id
	 * @return NailMapper 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static NailMapper queryNail(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			double length = rs.getDouble("length");
			int numberInBox = rs.getInt("numberInBox");
			String className = rs.getString("className");
			NailMapper nailMapper = new NailMapper(upc, manufacturerID, price, length, numberInBox, className);
			return nailMapper;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Query for Tool 
	 * 
	 * @param id
	 * @return ToolMapper 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ToolMapper queryTool(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			String description = rs.getString("description");
			String className = rs.getString("className");
			ToolMapper toolMapper = new ToolMapper(upc, manufacturerID, price, description, className);
			return toolMapper;
		}
		else
		{
			return null;
		}
	}

	
	/**
	 * Query for PowerTool
	 * 
	 * @param id
	 * @return PowerToolMapper 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static PowerToolMapper queryPowerTool(int id) throws ClassNotFoundException, SQLException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			String description = rs.getString("description");
			boolean batteryPowered = rs.getBoolean("batteryPowered");
			String className = rs.getString("className");
			PowerToolMapper powerToolMapper = new PowerToolMapper(upc, manufacturerID, price, description, batteryPowered, className);
			return powerToolMapper;
		}
		else
		{
			ClassNotFoundException exception = new ClassNotFoundException("Could not find PowerTool with specified ID");
			exception.getMessage();
		}
		return null;
	}
	
	/**
	 * Query for StripNail
	 * 
	 * @param id
	 * @return StripNailsMapper
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static StripNailsMapper queryStripNail(int id) throws SQLException, ClassNotFoundException
	{
		Statement statement = DatabaseGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = statement.executeQuery(sqlStatement);
		
		if(rs.next())
		{
			String upc = rs.getString("upc");
			int manufacturerID = rs.getInt("manufacturerID");
			int price = rs.getInt("price");
			double length = rs.getDouble("length");
			int numberInStrip = rs.getInt("numberInStrip");
			String className = rs.getString("className");
			StripNailsMapper stripNailMapper = new StripNailsMapper(upc, manufacturerID, price, length, numberInStrip, className);
			return stripNailMapper;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Inserts an item into the table within the database based on a prepared statement created by respective concrete class Mappers 
	 * 
	 * @param dbrs
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
	 * 
	 * @param nail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertNail(NailMapper nail) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInBox, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1, nail.getUpc());
		pst.setInt(2, nail.getManufacturerID());
		pst.setInt(3, nail.getPrice());
		pst.setDouble(4, nail.getLength());
		pst.setInt(5,  nail.getNumberInBox());
		pst.setString(6,  nail.getClassName());
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Inserts a Tool 
	 * 
	 * @param tool
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertTool(ToolMapper tool) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, className) VALUES(?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1, tool.getUpc());
		pst.setInt(2, tool.getManufacturerID());
		pst.setInt(3, tool.getPrice());
		pst.setString(4, tool.getDescription());
		pst.setString(5, tool.getClassName());
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Insert a powerTool 
	 * 
	 * @param powerTool
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerTool(PowerToolMapper powerTool) throws SQLException, ClassNotFoundException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, description, batteryPowered, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		pst.setString(1,  powerTool.getUpc());
		pst.setInt(2, powerTool.getManufacturerID());
		pst.setInt(3, powerTool.getPrice());
		pst.setString(4,  powerTool.getDescription());
		pst.setBoolean(5, powerTool.isBatteryPowered());
		pst.setString(6,  powerTool.getClassName());
		DatabaseGateway.insertRow(pst);
	}
	
	/**
	 * Insert a StripNail
	 * 
	 * @param stripNail
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertStripNail(StripNailsMapper stripNail) throws ClassNotFoundException, SQLException
	{
		String statement = "INSERT INTO InventoryItem (upc, manufacturerID, price, length, numberInStrip, className) VALUES(?,?,?,?,?,?)";
		PreparedStatement pst  = DatabaseGateway.getConnection().prepareStatement(statement);
		
		pst.setString(1, stripNail.getUpc());
		pst.setInt(2, stripNail.getManufacturerID());
		pst.setInt(3, stripNail.getPrice());
		pst.setDouble(4, stripNail.getLength());
		pst.setInt(5, stripNail.getNumberInStrip());
		pst.setString(6, stripNail.getClassName());
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
	public static void updateNailToDB(Nail nail) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + nail.getUpc()
				+ ", manufacturerID=" + nail.getManufacturerID()
				+ ", price=" + nail.getPrice()
				+ ", length=" + nail.getLength()
				+ ", numberInBox=" + nail.getNumberInBox()
				+ " where id=" + nail.getId();
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();		
	}

	/**
	 * updates the fields of the tool in the db
	 * 
	 * @param tool
	 * @throws SQLException
	 */
	public static void updateToolToDB(Tool tool) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + tool.getUpc()
				+ ", manufacturerID=" + tool.getManufacturerID()
				+ ", price=" + tool.getPrice()
				+ ", description=" + "'" + tool.getDescription() + "'"
				+ " where id=" + tool.getId();
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();		
	}

	/**
	 * updates the fields of the stripNail in the db
	 * 
	 * @param stripNail
	 * @throws SQLException
	 */
	public static void updateStripNailToDB(StripNail stripNail) throws SQLException 
	{
		String query = "update InventoryItem set"
				+ " upc=" + stripNail.getUpc()
				+ ", manufacturerID=" + stripNail.getManufacturerID()
				+ ", price=" + stripNail.getPrice()
				+ ", length=" + stripNail.getLength()
				+ ", numberInStrip=" + stripNail.getNumberInStrip()
				+ " where id=" + stripNail.getId();
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();
	}

	/**
	 * updates the fields of the powerTool in the db
	 * 
	 * @param powerTool
	 * @throws SQLException
	 */
	public static void updatePowerToolToDB(PowerTool powerTool) throws SQLException
	{
		String query = "update InventoryItem set"
				+ " upc=" + powerTool.getUpc()
				+ ", manufacturerID=" + powerTool.getManufacturerID()
				+ ", price=" + powerTool.getPrice()
				+ ", description=" + "'" + powerTool.getDescription() + "'"
				+ ", batteryPowered=" + powerTool.isBatteryPowered()
				+ " where id=" + powerTool.getId();
		
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.executeUpdate();
		
	}
}
