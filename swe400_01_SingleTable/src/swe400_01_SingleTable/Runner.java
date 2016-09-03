package swe400_01_SingleTable;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 *
 * @authors Drew Rife & Alec Waddelow
 *
 */
public class Runner
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";

	private int id;
	private String upc;
	private int manufacturerID;
	private int price;
	private String description;
	private boolean batteryPowered;
	private long length;
	private int numberInStrip;
	private int numberInBox;
	private String className;

	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
//		Runner r = new Runner();
//		r.queryDB(1);
	}

	/**
	 * @author Alec Waddelow
	 * @param id
	 * @return resultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBReturnSet queryDB(int id) throws SQLException, ClassNotFoundException
	{
		DBReturnSet rs1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		Statement st = (Statement) con.createStatement();


		String sqlStatement = ("SELECT * FROM InventoryItem WHERE id =" + "'" + id + "';");
		ResultSet rs = st.executeQuery(sqlStatement);

		while(rs.next())
		{
			 id = rs.getInt("id");
			 upc = rs.getString("upc");
			 manufacturerID = rs.getInt("manufacturerID");
			 price = rs.getInt("price");
			 description = rs.getString("description");
			 batteryPowered = rs.getBoolean("batteryPowered");
			 length = rs.getLong("length");
			 numberInStrip = rs.getInt("numberInStrip");
			 numberInBox = rs.getInt("numberInBox");
			 className = rs.getString("className");
		}

		rs1 = new DBReturnSet(id, upc, manufacturerID, price, description, batteryPowered, length, numberInStrip, numberInBox, className);

		return rs1;
	}








}
