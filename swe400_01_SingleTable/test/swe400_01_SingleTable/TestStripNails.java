package swe400_01_SingleTable;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author Alec Waddelow
 *
 */
public class TestStripNails
{

	@Test
	public void testInitialization() throws ClassNotFoundException, SQLException
	{
		StripNails sn = new StripNails(8);
		assertEquals("8", sn.upc);
		assertEquals(8, sn.manufacturerID);
		assertEquals(8, sn.price);
		assertEquals(0, sn.length);
		assertEquals(2, sn.numberInStrip);

	}

//	@Test
//	public void testInsertingObjectIntoRow() throws ClassNotFoundException, SQLException
//	{
//		StripNails sn = new StripNails(9, "9", 9, 9, "", false, 0, 9, 0, "StripNails");
//		assertEquals("9", sn.upc);
//		assertEquals(9, sn.manufacturerID);
//		assertEquals(9, sn.price);
//	}
}
