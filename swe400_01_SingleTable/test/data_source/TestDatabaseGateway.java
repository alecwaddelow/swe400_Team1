package data_source;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import data_source.DatabaseGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * Test DatabaseGateway
 */
public class TestDatabaseGateway 
{

	/**
	 * Test Creation of DatabaseGateway 
	 */
	@Test
	public void testCreation() 
	{
		DatabaseGateway dg = new DatabaseGateway();
		assertTrue(dg instanceof DatabaseGateway);
	}
	
	/**
	 * Tests UPCs of PowerTools
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGettingPowerToolUPCs() throws ClassNotFoundException, SQLException
	{
		ResultSet rSet = DatabaseGateway.getPowerToolUPCs();
		
		String[] upcArray = {"1231231234", "4445553333", "7657896543", "9993458585", "7654564848", "7784452828"};
	
		int i = 0;
		while(rSet.next())
		{	 
			assertEquals(upcArray[i], rSet.getString("upc"));
			i++;
		}		
	}
	
	/**
	 * Tests UPCS of StripNails
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testGettingStripNailUPCs() throws ClassNotFoundException, SQLException
	{
		ResultSet rSet = DatabaseGateway.getStripNailUPCs();
		String[] upcArray = {"5453432345", "4343434543", "9876784727", "6565459876", "4343432345"};
	
		int i = 0;
		while(rSet.next())
		{	 
			assertEquals(upcArray[i], rSet.getString("upc"));
			i++;
		}		
	}
	
	/**
	 * Tests for inpuuting a bad UPC that isn't in the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testBadUPCInput() throws ClassNotFoundException, SQLException
	{
		assertNull(DatabaseGateway.retrieveUPC("000000000000000"));
	}
}