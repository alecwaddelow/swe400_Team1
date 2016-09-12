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

import mock.MockNail;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
public class TestNail extends DBTest
{
	/**
	 * Test inserting nail into DB	 *
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testInsertNail() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		Nail nail = new Nail(1, "446", 12, 13, null,  false, 13.01, 0, 0, "Nail");

		DBMapper dm = DatabaseGateway.queryDB(1);

		assertEquals(nail.getId(), dm.getId());
		assertEquals(nail.getUpc(), dm.getUpc());
		assertEquals(nail.getManufacturerID(), dm.getManufacturerID());
		assertEquals(nail.getPrice(), dm.getPrice());
		assertEquals(nail.getDescription(), dm.getDescription());
		assertEquals(nail.getLength(), dm.getLength(), 0.001);
		assertEquals(nail.getNumberInBox(), dm.getNumberInBox());
		assertEquals(nail.getClassName(), dm.getClassName());
	}

	/**
	 * Test retrieving nail from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRetrieveNail() throws ClassNotFoundException, SQLException
	{
		CreateDatabase.createTable();
		Nail insertNail = new Nail(1, "446", 12, 13, null,  false, 13.01, 0, 0, "Nail");
		Nail findNail = new Nail(1);

		assertEquals(insertNail.getId(), findNail.getId());
		assertEquals(insertNail.getUpc(), findNail.getUpc());
		assertEquals(insertNail.getManufacturerID(), findNail.getManufacturerID());
		assertEquals(insertNail.getPrice(), findNail.getPrice());
		assertEquals(insertNail.getLength(), findNail.getLength(), 0.001);
		assertEquals(insertNail.getNumberInBox(), findNail.getNumberInBox());
	}
}
