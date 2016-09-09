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
public class TestNail extends DBTest
{
	/**
	 * Test inserting nail into DB
	 * 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
	{

		Nail nail = new Nail(1, "333", 444, 5, "ThisJawn", false, 0, 0, 0, "Nail");

		PreparedStatement pst;
		String sqlStatement = "SELECT * FROM InventoryItem WHERE 'id' = 1;";
		pst = DatabaseGateway.getConnection().prepareStatement(sqlStatement);
		ResultSet rs = pst.executeQuery(sqlStatement);

		while(rs.next())
		{
			assertEquals("333", rs.getString("upc"));
			assertEquals(444, rs.getInt("manufacturerID"));
			assertEquals(5, rs.getInt("price"));
			assertEquals(0, rs.getInt("length"));
			assertEquals(0, rs.getInt("numberInBox"));
		}
	}
}






