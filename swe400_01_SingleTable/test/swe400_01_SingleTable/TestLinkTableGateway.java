package swe400_01_SingleTable;
import static org.junit.Assert.*;
import org.junit.Test;
import data_source.LinkTableGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * Test LinkTableGateway
 *
 */
public class TestLinkTableGateway 
{
	/**
	 * Tests creation of LinkTableGateway 
	 */
	@Test
	public void testCreation() 
	{
		LinkTableGateway ltg = new LinkTableGateway();
		assertTrue(ltg instanceof LinkTableGateway);
	}
}
