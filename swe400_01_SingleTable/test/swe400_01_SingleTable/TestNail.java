/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class TestNail
{
	static DatabaseGateway gateway;

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

//	@Before
//	public void doStuffBefore() throws ClassNotFoundException, SQLException
//	{
//		DBTest.testStartTransaction();
//
//	}
//
//	@After
//	public void doStuffAfter() throws ClassNotFoundException, SQLException{
//		DBTest.testRollBack();
//	}

	@Test
	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
	{
		Nail nail = new Nail(2, "333", 444, 5, "ThisJawn", false, 0, 0, 0, "Nail");

		//Connection conn = DriverManager.getConnection("jdbc:mysql://db.cs.ship.edu/swe400-12", "swe400_1", "pwd4swe400_1F16");

		PreparedStatement pst;
		String sqlStatement = "SELECT * FROM InventoryItem WHERE 'id' = 2;";
		pst = gateway.getConnection().prepareStatement(sqlStatement);
		ResultSet rs = pst.executeQuery(sqlStatement);



		assertEquals("333", rs.getString("upc"));
		assertEquals(444, rs.getInt("manufacturerID"));
		assertEquals(5, rs.getInt("price"));
		assertEquals(0, rs.getInt("length"));
		assertEquals(0, rs.getInt("numberInBox"));
	}

//	@Test
//	public void testPullingFromDB() throws ClassNotFoundException, SQLException
//	{
//		Nail nail = new Nail(2);
//		assertEquals("333", nail.upc);
//
//	}
}