/**
 * 
 */
package swe400_01_SingleTable;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author Alec Waddelow 
 *
 */
public class TestPowerTool 
{

	@Test
	public void testInitialization() throws ClassNotFoundException, SQLException 
	{
		PowerTool pt = new PowerTool(6);
		assertEquals("6", pt.upc);
		assertEquals(6, pt.manufacturerID);
		assertEquals(6, pt.price);
		assertEquals(true,pt.batteryPowered);
	}
	
//	@Test
//	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
//	{
//		PowerTool pt = new PowerTool(7, "7", 7, 7, "", false, 0, 0, 0, "PowerTool");
//		assertEquals("7", pt.upc);
//		assertEquals(7, pt.manufacturerID);
//		assertEquals(7, pt.price);
//	} 
	
}
