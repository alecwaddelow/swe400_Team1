/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class TestNail
{

	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";
//	/**
//	 * Test initializing a Nail Object
//	 * @throws SQLException
//	 * @throws ClassNotFoundException
//	 */
//	@Before
//	@Test
//	public void testInitialization() throws ClassNotFoundException, SQLException
//	{
//		Nail nail = new Nail(1);
//		assertEquals("123", nail.upc);
//		assertEquals(445, nail.manufacturerID);
//		assertEquals(10, nail.price);
//		assertEquals(10, nail.length);
//		assertEquals(10, nail.numberInBox);
//	}


	@Test
	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection(hostName, user, password);
		con.setAutoCommit(false);

		TestNail tn = new TestNail();
//		tn.testStartTransaction();
		Nail nail = new Nail(1, "333", 444, 5, "ThisJawn", false, 0, 0, 0, "Nail");
		PreparedStatement pst;
		String sqlStatement = "SELECT * FROM InventoryItem WHERE 'id' = 1;";
		pst = (PreparedStatement) con.prepareStatement(sqlStatement);
		ResultSet rs = pst.executeQuery(sqlStatement);
		con.commit();


		assertEquals("333", rs.getString("upc"));
		assertEquals(444, rs.getInt("manufacturerID"));
		assertEquals(5, rs.getInt("price"));
		assertEquals(0, rs.getInt("length"));
		assertEquals(0, rs.getInt("numberInBox"));
//		tn.testRollBack();

	}

//	@Test
//	public void testPullingFromDB() throws ClassNotFoundException, SQLException
//	{
//		Nail nail = new Nail(2);
//		assertEquals("333", nail.upc);
//
//	}
}