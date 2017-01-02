package data_source;
import static org.junit.Assert.*;
import org.junit.Test;
import data_source.CreateInventoryItemTable;

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
		CreateInventoryItemTable cd = new CreateInventoryItemTable();
		assertTrue(cd instanceof CreateInventoryItemTable);
	}
}