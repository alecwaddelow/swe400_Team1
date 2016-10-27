package swe400_01_SingleTable;
import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;
import domain_layer.ToolMapper;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class TestToolMapper 
{
	
	/**
	 * Tests creation of ToolMapper 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCreation() throws ClassNotFoundException, SQLException 
	{
		ToolMapper tm = new ToolMapper(0, null, 0, 0, null, null);
		assertEquals(0, tm.getId());
		assertEquals(null, tm.getUpc());
		assertEquals(0, tm.getManufacturerID());
		assertEquals(0, tm.getPrice());
		assertEquals(null, tm.getDescription());
		assertEquals(null, tm.getClassName());
	}

}
