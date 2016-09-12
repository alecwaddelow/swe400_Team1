/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class TestPowerTool extends DBTest
{
	/**
	 * Test inserting power tool into DBfindPowerTool
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertPowerTool() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		PowerTool powerTool = new PowerTool(1, "55353", 45, 35, "This is a tool",  true, 0.0, 0, 0, "PowerTool");

		DBMapper dm = DatabaseGateway.queryDB(1);

		assertEquals(powerTool.getId(), dm.getId());
		assertEquals(powerTool.getUpc(), dm.getUpc());
		assertEquals(powerTool.getManufacturerID(), dm.getManufacturerID());
		assertEquals(powerTool.getPrice(), dm.getPrice());
		assertEquals(powerTool.getDescription(), dm.getDescription());
		assertEquals(powerTool.isBatteryPowered(), dm.isBatteryPowered());
	}

	/**
	 * Test retrieving power tool from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrievePowerTool() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		PowerTool insertPowerTool = new PowerTool(1, "55353", 45, 35, "This is a tool",  true, 0.0, 0, 0, "PowerTool");
		PowerTool findPowerTool = new PowerTool(1);

		assertEquals(insertPowerTool.getId(), findPowerTool.getId());
		assertEquals(insertPowerTool.getUpc(), findPowerTool.getUpc());
		assertEquals(insertPowerTool.getManufacturerID(), findPowerTool.getManufacturerID());
		assertEquals(insertPowerTool.getPrice(), findPowerTool.getPrice());
		assertEquals(insertPowerTool.getDescription(), findPowerTool.getDescription());
		assertEquals(insertPowerTool.isBatteryPowered(), findPowerTool.isBatteryPowered());
	}

}
