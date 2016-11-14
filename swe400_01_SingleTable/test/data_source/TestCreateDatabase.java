package data_source;
import static org.junit.Assert.*;
import org.junit.Test;
import data_source.CreateDatabase;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 * Test CreateDatabase 
 */
public class TestCreateDatabase 
{
	/**
	 * Tests creation of CreateDatabase 
	 */
	@Test
	public void testCreation() 
	{
		CreateDatabase cd = new CreateDatabase();
		assertTrue(cd instanceof CreateDatabase);
	}
}