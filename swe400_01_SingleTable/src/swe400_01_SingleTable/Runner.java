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

	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");

		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		System.out.println("Success");

		Statement st = (Statement) con.createStatement();
		String sql = ("SELECT * FROM InventoryItem");
		ResultSet rs = st.executeQuery(sql);

		if(rs.next())
		{
			int id = rs.getInt("id");
			String str1 = rs.getString("description");

			System.out.println("This is the ID: " + id);
			System.out.print("This is the description: " + str1);

		}
		con.close();
	}
}
