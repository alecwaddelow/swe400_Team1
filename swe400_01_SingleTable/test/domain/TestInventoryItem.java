package domain;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class TestInventoryItem 
{

	/**
	 * Asserts that when a bad UPC is passed in, a null item is returned 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGetDetailsBadUPC() throws ClassNotFoundException, SQLException 
	{
		assertNull(InventoryItem.getDetails("10101010133", "Nail"));
	}
	
	/**
	 * Asserts that a when invalid data passed to matchClassAndConstruct
	 * a ClassNotFoundException is thrown 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test(expected= ClassNotFoundException.class)
	public void testMatchClassAndConstruct() throws ClassNotFoundException, SQLException
	{
		InventoryItem.matchClassAndConstruct(1, "");
	}
}
