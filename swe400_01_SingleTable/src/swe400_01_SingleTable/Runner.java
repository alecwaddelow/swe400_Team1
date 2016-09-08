package swe400_01_SingleTable;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 *
 * @authors Drew Rife & Alec Waddelow
 *
 */
public class Runner
{

	static DatabaseGateway gateway;
//	public static Connection con;
//
//	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
//	private static final String user = "swe400_1";
//	private static final String password = "pwd4swe400_1F16";

	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
		Runner run = new Runner();

		ArrayList<Object> list = new ArrayList<Object>();

		list =  run.createList();
	}

	public ArrayList<Object> createList() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT id,className FROM InventoryItem;");
		Statement st = gateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		ArrayList<Object> listOfObjects = new ArrayList<Object>();

		int i = 0;
		while(rs.next())
		{

			int id  = rs.getRow();
			String className = rs.getString("className");
			System.out.println(className);
			listOfObjects.add(i, matchClassAndConstruct(id, className));
			i++;
		}
		return null;
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
	public Object matchClassAndConstruct(int ID, String className) throws ClassNotFoundException, SQLException
	{
		if (className == null)
		{
			throw new  ClassNotFoundException();
		}
		else if (className.contains("Tool") || className.contains("tool"))
		{
			Tool tool = new Tool(ID);
			return tool;
		}
		else if (className.contains("PowerTool") || className.contains("powertool"))
		{		TestNail tn = new TestNail();
			PowerTool pt = new PowerTool(ID);
			return pt;
		}
		else if (className.contains("StripNails") || className.contains("stripnails"))
		{
			StripNails sn = new StripNails(ID);
			return sn;
		}
		else if (className.contains("Nail") || className.contains("nail"))
		{
			Nail nail = new Nail(ID);
			return nail;
		}
		return null;

	}

	/**
	 * @author Alec Waddelow
	 *
	 * @param id
	 * @return resultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("null")
	public DBReturnSet queryDB(int id) throws SQLException, ClassNotFoundException
	{
		DBReturnSet rs1 = new DBReturnSet();
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		Statement st = gateway.getConnection().createStatement();


		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = st.executeQuery(sqlStatement);

		while(rs.next())
		{
			 rs1.setId(rs.getInt("id"));
			 rs1.setUpc(rs.getString("upc"));
			 rs1.setManufacturerID(rs.getInt("manufacturerID"));
			 rs1.setPrice(rs.getInt("price"));
			 rs1.setDescription(rs.getString("description"));
			 rs1.setBatteryPowered(rs.getBoolean("batteryPowered"));
			 rs1.setLength(rs.getLong("length"));
			 rs1.setNumberInStrip(rs.getInt("numberInStrip"));
			 rs1.setNumberInBox(rs.getInt("numberInBox"));
			 rs1.setClassName(rs.getString("className"));
		}
//		con.close();

		return rs1;
	}

	/**
	 * @author Alec Waddelow
	 *
	 *
	 * @param dbrs
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertRow(DBReturnSet dbrs) throws ClassNotFoundException, SQLException
	{

//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		gateway = new DatabaseGateway();
		PreparedStatement pst;

		String sqlStatement = "INSERT INTO InventoryItem (id, upc, manufacturerID, price, description, batteryPowered, length, numberInStrip, numberInBox, className)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		pst = (PreparedStatement) gateway.getConnection().prepareStatement(sqlStatement);
		pst.setInt(1, dbrs.getId());
		pst.setString(2, dbrs.getUpc());
		pst.setInt(3, dbrs.getManufacturerID());
		pst.setInt(4, dbrs.getPrice());
		pst.setString(5, dbrs.getDescription());
		pst.setBoolean(6, dbrs.isBatteryPowered());
		pst.setLong(7, dbrs.getLength());
		pst.setInt(8, dbrs.getNumberInStrip());
		pst.setInt(9, dbrs.getNumberInBox());
		pst.setString(10, dbrs.getClassName());
		pst.executeUpdate();
		pst.close();
//		con.close();

	}

}
