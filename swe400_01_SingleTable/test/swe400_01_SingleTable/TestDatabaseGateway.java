package swe400_01_SingleTable;
import static org.junit.Assert.*;
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
	
}
