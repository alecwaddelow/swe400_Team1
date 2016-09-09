package swe400_01_SingleTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Drew Rife and Alec Waddelow
 *
 */
public class DatabaseGateway
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";

	private static Connection con;

	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(hostName, user, password);
			con.setAutoCommit(false);

			return con;
		}
		else
		{
			return con;
		}
	}
	
	/**
	 * @author Alec Waddelow
	 *
	 * @param id
	 * @return resultSet
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static DBMapper queryDB(int id) throws SQLException, ClassNotFoundException
	{
		DBMapper rs1 = new DBMapper();
		Statement st = DatabaseGateway.getConnection().createStatement();
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
	public static void insertRow(DBMapper dbrs) throws ClassNotFoundException, SQLException
	{
		PreparedStatement pst;

		String sqlStatement = "INSERT INTO InventoryItem (id, upc, manufacturerID, price, description, batteryPowered, length, numberInStrip, numberInBox, className)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		pst = DatabaseGateway.getConnection().prepareStatement(sqlStatement);
		pst.setInt(1, dbrs.getId());
		pst.setString(2, dbrs.getUpc());
		pst.setInt(3, dbrs.getManufacturerID());
		pst.setInt(4, dbrs.getPrice());
		pst.setString(5, dbrs.getDescription());
		pst.setBoolean(6, dbrs.isBatteryPowered());
		pst.setDouble(7, dbrs.getLength());
		pst.setInt(8, dbrs.getNumberInStrip());
		pst.setInt(9, dbrs.getNumberInBox());
		pst.setString(10, dbrs.getClassName());
		pst.executeUpdate();
		pst.close();
	}
}


