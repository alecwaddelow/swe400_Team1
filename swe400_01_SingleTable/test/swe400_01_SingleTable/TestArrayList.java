/**
z
 */
package swe400_01_SingleTable;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Alec Waddelow
 *	Tests for creating a new ArrayList of objects pulled from the database
 */
public class TestArrayList
{
	/**
	 * Tests creation of the ArrayList from the database
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testArrayListCreation() throws ClassNotFoundException, SQLException
	{
		ArrayList<Object> returnSet = new ArrayList<Object>();		//
		returnSet = Runner.createList();


	}

}
