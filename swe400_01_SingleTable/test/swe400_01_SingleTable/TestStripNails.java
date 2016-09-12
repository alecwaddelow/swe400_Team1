package swe400_01_SingleTable;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class TestStripNails extends DBTest
{
	/**
	 * Test inserting strip nails into DBfindPowerTool
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertStripNails() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		StripNail stripNail = new StripNail(1, "123456652562", 75612, 10, null,  false, 245.1002, 456, 0, "StripNail");

		DBMapper dm = DatabaseGateway.queryDB(1);

		assertEquals(stripNail.getId(), dm.getId());
		assertEquals(stripNail.getUpc(), dm.getUpc());
		assertEquals(stripNail.getManufacturerID(), dm.getManufacturerID());
		assertEquals(stripNail.getPrice(), dm.getPrice());
		assertEquals(stripNail.getLength(), dm.getLength(), 0.001);
		assertEquals(stripNail.getNumberInStrip(), dm.getNumberInStrip());
	}

	/**
	 * Test retrieving strip nails from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrieveStripNail() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		StripNail insertStripNail = new StripNail(1, "123456652562", 75612, 10, null,  false, 245.1002, 456, 0, "StripNail");
		StripNail findStripNail = new StripNail(1);

		assertEquals(insertStripNail.getId(), findStripNail.getId());
		assertEquals(insertStripNail.getUpc(), findStripNail.getUpc());
		assertEquals(insertStripNail.getManufacturerID(), findStripNail.getManufacturerID());
		assertEquals(insertStripNail.getPrice(), findStripNail.getPrice());
		assertEquals(insertStripNail.getLength(), findStripNail.getLength(), 0.001);
		assertEquals(insertStripNail.getNumberInStrip(), findStripNail.getNumberInStrip());
	}

}
