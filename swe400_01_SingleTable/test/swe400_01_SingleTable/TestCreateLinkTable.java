/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import org.junit.Test;

import data_source.CreateLinkTable;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * Test CreateLinkTable 
 */
public class TestCreateLinkTable 
{

	/**
	 * Tests creation of LinkTable 
	 */
	@Test
	public void testCreation() 
	{
		CreateLinkTable clt = new CreateLinkTable();
		assertTrue(clt instanceof CreateLinkTable);
	}

}
