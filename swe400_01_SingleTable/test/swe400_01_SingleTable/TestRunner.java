package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import org.junit.Test;
import domain_layer.InventoryItem;
import domain_layer.Nail;
import enums.Nails;
import runner.Runner;

/**
 * @author Alec Waddelow & Drew Rife 
 *
 * Tests Runner 
 */
public class TestRunner extends DBTest
{

	/**
	 * Tests creation of Runner  
	 */
	@Test
	public void testCreation() 
	{
		Runner run = new Runner();
		assertTrue(run instanceof Runner);
	}

	
	/**
	 * Tests getter of arraylist of inventory items 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@Test
	public void testGetList() throws ClassNotFoundException, SQLException, NamingException
	{
		Runner run = new Runner();
		ArrayList<InventoryItem> myList = run.getList();
	}
	
	/**
	 * Tests the getDetails method 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetDetails() throws ClassNotFoundException, SQLException
	{
		Runner run = new Runner();
		Nail nail = new Nail(1);
		Nail nailGD = (Nail) run.getDetails(Nails.COMMON_10D.getUpc());

		assertEquals(nail.getUpc(), nailGD.getUpc());
		assertEquals(nail.getManufacturerID(), nailGD.getManufacturerID());
		assertEquals(nail.getPrice(), nailGD.getPrice());
		assertEquals(nail.getLength(), nailGD.getLength(), 0.001);
		assertEquals(nail.getNumberInBox(), nailGD.getNumberInBox());
		assertEquals(nail.getClassName(), nailGD.getClassName());
	}
}