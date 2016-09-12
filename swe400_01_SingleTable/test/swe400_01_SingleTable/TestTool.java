/**
 *
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * tests adding and retrieving tools
 */
public class TestTool extends DBTest
{
	/**
	 * Test inserting tool into DB	 *
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertTool() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		Tool tool = new Tool(1, "55353", 45, 35, "This is a tool",  false, 0.0, 0, 0, "Tool");

		DBMapper dm = DatabaseGateway.queryDB(1);

		assertEquals(tool.getId(), dm.getId());
		assertEquals(tool.getManufacturerID(), dm.getManufacturerID());
		assertEquals(tool.getPrice(), dm.getPrice());
		assertEquals(tool.getDescription(), dm.getDescription());
	}

	/**
	 * Test retrieving tool from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrieveTool() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		Tool insertTool = new Tool(1, "446", 12, 13, null,  false, 13.01, 0, 0, "Nail");
		Tool findTool = new Tool(1);

		assertEquals(insertTool.getId(), findTool.getId());
		assertEquals(insertTool.getUpc(), findTool.getUpc());
		assertEquals(insertTool.getManufacturerID(), findTool.getManufacturerID());
		assertEquals(insertTool.getPrice(), findTool.getPrice());
		assertEquals(insertTool.getDescription(), findTool.getDescription());
	}
}
