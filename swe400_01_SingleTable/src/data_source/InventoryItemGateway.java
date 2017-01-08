package data_source;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Drew Rife and Alec Waddelow
 *
 * Establishes a connection to the database and contains common database functions
 */
public class InventoryItemGateway
{
	/**
	 * Query for Nail
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItemDTO queryNail(int id) throws ClassNotFoundException, SQLException
	{		
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		InventoryItemDTO iiDTO = null;
		
		if(resultSet.next())
		{
			iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setLength(resultSet.getDouble("length"));
			iiDTO.setNumberInBox(resultSet.getInt("numberInBox"));
			iiDTO.setClassName("Nail");
		}
		
		resultSet.close();
		preparedStatement.close();
		return iiDTO;
	}
	
	/**
	 * Query for Tool 
	 * 
	 * @param id
	 * @return ResultSet 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItemDTO queryTool(int id) throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT * FROM InventoryItem where id=?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		InventoryItemDTO iiDTO = null;
		
		if(resultSet.next())
		{
			iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setDescription(resultSet.getString("description"));
			iiDTO.setClassName("Tool");
		}
		
		resultSet.close();
		preparedStatement.close();
		return iiDTO;
	}

	/**
	 * Query for PowerTool
	 * 
	 * @param id
	 * @return ResultSet 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static InventoryItemDTO queryPowerTool(int id) throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		InventoryItemDTO iiDTO = null;
		
		if(resultSet.next())
		{
			iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setDescription(resultSet.getString("description"));
			iiDTO.setBatteryPowered(resultSet.getBoolean("batteryPowered"));
			iiDTO.setClassName("PowerTool");
		}
		
		resultSet.close();
		preparedStatement.close();
		return iiDTO;
	}
	
	/**
	 * Query for StripNail
	 * 
	 * @param id
	 * @return ResultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static InventoryItemDTO queryStripNail(int id) throws SQLException, ClassNotFoundException
	{
		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id=?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		InventoryItemDTO iiDTO = null;
		
		if(resultSet.next())
		{
			iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setLength(resultSet.getDouble("length"));
			iiDTO.setNumberInStrip(resultSet.getInt("numberInStrip"));
			iiDTO.setClassName("StripNail");
		}
		
		resultSet.close();
		preparedStatement.close();
		return iiDTO;
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
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5,  numberInBox);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
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
		PreparedStatement preparedStatement   = ConnectionManager.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setString(5, className);
		insertRow(preparedStatement);
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
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(statement);
		preparedStatement.setString(1,  upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4,  description);
		preparedStatement.setBoolean(5, batteryPowered);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
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
		PreparedStatement preparedStatement  = ConnectionManager.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5,  numberInStrip);
		preparedStatement.setString(6,  className);
		insertRow(preparedStatement);
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
	public static InventoryItemDTO retrieveItemByUPC(String upc, String className) throws ClassNotFoundException, SQLException
	{
		String statement = "SELECT * FROM InventoryItem WHERE upc=? and className=?";
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(statement);
		preparedStatement.setString(1, upc);
		preparedStatement.setString(2, className);
		ResultSet resultSet = preparedStatement.executeQuery();
		InventoryItemDTO iiDTO = null;
		
		if(resultSet.next())
		{
			iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			
			switch(className)
			{
			case "Nail":
				iiDTO.setLength(resultSet.getDouble("length"));
				iiDTO.setNumberInBox(resultSet.getInt("numberInBox"));
				iiDTO.setClassName("Nail");
				break;
			case "Tool":
				iiDTO.setDescription(resultSet.getString("description"));
				iiDTO.setClassName("Tool");
				break;
			case "PowerTool":
				iiDTO.setDescription(resultSet.getString("description"));
				iiDTO.setBatteryPowered(resultSet.getBoolean("batteryPowered"));
				iiDTO.setClassName("PowerTool");
				break;
			case "StripNail":
				iiDTO.setLength(resultSet.getDouble("length"));
				iiDTO.setNumberInStrip(resultSet.getInt("numberInStrip"));
				iiDTO.setClassName("StripNail");
				break;				
			}
		}
		
		resultSet.close();
		preparedStatement.close();
		return iiDTO;
	}
	
	/**
	 * Builds the List of the objects
	 * 
	 * @return ResultSet
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItemDTO> createList() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT id,className FROM InventoryItem");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<InventoryItemDTO> listInventoryItemDTO = new ArrayList<InventoryItemDTO>();
		
		while(resultSet.next())
		{
			InventoryItemDTO iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setClassName(resultSet.getString("className"));
			listInventoryItemDTO.add(iiDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listInventoryItemDTO; 
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
	 * @throws ClassNotFoundException 
	 */
	public static void updateNailToDB(String upc, int manufacturerID, int price, double length, int numberInBox, int id) throws SQLException, ClassNotFoundException 
	{
		String query = "update InventoryItem set"
				+ " upc=?"
				+ ", manufacturerID=?"
				+ ", price=?"
				+ ", length=?"
				+ ", numberInBox=?"
				+ " where id=?";
		
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5, numberInBox);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		preparedStatement.close();
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

		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setInt(5, id);
		preparedStatement.executeUpdate();	
		preparedStatement.close();
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
		
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setDouble(4, length);
		preparedStatement.setInt(5, numberInStrip);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		preparedStatement.close();
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

		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
		preparedStatement.setString(1, upc);
		preparedStatement.setInt(2, manufacturerID);
		preparedStatement.setInt(3, price);
		preparedStatement.setString(4, description);
		preparedStatement.setBoolean(5, batteryPowered);
		preparedStatement.setInt(6, id);
		preparedStatement.executeUpdate();	
		preparedStatement.close();
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
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, upc);
		preparedStatement.setString(2, className);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		int id = 0;
		if(resultSet.next())
		{
			id = resultSet.getInt("id");
			resultSet.close();
			preparedStatement.close();
		}
		return id;
	}
	
	/**
	 * Returns stripNail UPC's 
	 * 
	 * @return ResultSet of all StripNails
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItemDTO> getAllStripNails() throws ClassNotFoundException, SQLException 
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "StripNail");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<InventoryItemDTO> listInventoryItemDTO = new ArrayList<InventoryItemDTO>();
		
		while(resultSet.next())
		{
			InventoryItemDTO iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setLength(resultSet.getDouble("length"));
			iiDTO.setNumberInStrip(resultSet.getInt("numberInStrip"));
			iiDTO.setClassName("StripNail");
			listInventoryItemDTO.add(iiDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listInventoryItemDTO;
	}

	/**
	 * Returns PowerTool UPC's
	 * 
	 * @return ResultSet of all powertools
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItemDTO> getAllPowerTools() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "PowerTool");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<InventoryItemDTO> listInventoryItemDTO = new ArrayList<InventoryItemDTO>();
		
		while(resultSet.next())
		{
			InventoryItemDTO iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setDescription(resultSet.getString("description"));
			iiDTO.setBatteryPowered(resultSet.getBoolean("batteryPowered"));
			iiDTO.setClassName("StripNail");
			listInventoryItemDTO.add(iiDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listInventoryItemDTO;
	}

	/**
	 * retrieves all nails 
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItemDTO> getAllNails() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "Nail");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<InventoryItemDTO> listInventoryItemDTO = new ArrayList<InventoryItemDTO>();
		
		while(resultSet.next())
		{
			InventoryItemDTO iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setLength(resultSet.getDouble("length"));
			iiDTO.setNumberInBox(resultSet.getInt("numberInBox"));
			iiDTO.setClassName("Nail");
			listInventoryItemDTO.add(iiDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listInventoryItemDTO;
	}
	
	/**
	 * Returns all tools
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<InventoryItemDTO> getAllTools() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "select * from InventoryItem where className=?";
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setString(1, "Tool");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<InventoryItemDTO> listInventoryItemDTO = new ArrayList<InventoryItemDTO>();
		
		while(resultSet.next())
		{
			InventoryItemDTO iiDTO = new InventoryItemDTO();
			iiDTO.setId(resultSet.getInt("id"));
			iiDTO.setUpc(resultSet.getString("upc"));
			iiDTO.setManufacturerID(resultSet.getInt("manufacturerID"));
			iiDTO.setPrice(resultSet.getInt("price"));
			iiDTO.setDescription(resultSet.getString("description"));
			iiDTO.setClassName("Tool");
			listInventoryItemDTO.add(iiDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listInventoryItemDTO;
	}
}