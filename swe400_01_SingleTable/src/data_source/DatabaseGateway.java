package data_source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

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
			NailMapper nailMapper = new NailMapper(id, upc, manufacturerID, price, length, numberInBox, className);
			return nailMapper;
		}
		else
		{
			return null;
		}
	}
	
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
			ToolMapper toolMapper = new ToolMapper(id, upc, manufacturerID, price, description, className);
			return toolMapper;
		}
		else
		{
			return null;
		}
	}

	
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
			PowerToolMapper powerToolMapper = new PowerToolMapper(id, upc, manufacturerID, price, description, batteryPowered, className);
			return powerToolMapper;
		}
		else
		{
			ClassNotFoundException exception = new ClassNotFoundException("Could not find PowerTool with specified ID");
			exception.getMessage();
		}
		return null;
	}
	
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
			StripNailsMapper stripNailMapper = new StripNailsMapper(id, upc, manufacturerID, price, length, numberInStrip, className);
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
	
	public static String getItemByUPC(String upc) throws ClassNotFoundException, SQLException
	{
		String statement = "SELECT * FROM InventoryItem WHERE upc =" + "'" + upc + "'" + ";";
		PreparedStatement pst = DatabaseGateway.getConnection().prepareStatement(statement);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
		{
			InventoryItem item = matchClassAndConstruct(rs.getInt("id"), rs.getString("className"));
			String string = item.toString();
			return string;
		}
		else
		{
			return null;
		}		
		
	}
	
	public static InventoryItem retreiveItemByUpc(String upc) throws ClassNotFoundException, SQLException
	{
		String statement = "SELECT * FROM InventoryItem WHERE upc =" + "'" + upc + "'" + ";";
		PreparedStatement pst = DatabaseGateway.getConnection().prepareStatement(statement);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
		{
			InventoryItem item = matchClassAndConstruct(rs.getInt("id"), rs.getString("className"));
			return item;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Matches classes with their finder constructor, constructs an object, and returns it
	 *
	 * @param ID
	 * @param className
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItem matchClassAndConstruct(int ID, String className) throws ClassNotFoundException, SQLException
	{
		switch(className)
		{
		case "Tool":
			Tool tool = new Tool(ID);
			return tool;
		case "PowerTool":
			PowerTool powerTool= new PowerTool(ID);
			return powerTool;
		case "StripNail":
			StripNail stripNail = new StripNail(ID);
			return stripNail;
		case "Nail":
			Nail nail = new Nail(ID);
			return nail;
		default:
			throw new ClassNotFoundException();
		}
	}
	
	/**
	 * Builds the ArrayList of the objects
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<InventoryItem> createList() throws ClassNotFoundException, SQLException
	{
		ArrayList<InventoryItem> listOfObjects = new ArrayList<InventoryItem>(); 
		String sqlStatement = ("SELECT id,className FROM InventoryItem;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);

		for(int i = 0; rs.next(); i++)
		{
			int id  = rs.getRow();
			String className = rs.getString("className");
			listOfObjects.add(i, matchClassAndConstruct(id, className));
		}
		return listOfObjects;
	}
}
