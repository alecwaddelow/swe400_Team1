package other;
import static org.junit.Assert.*;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.junit.Test;
import domain.InventoryItem;
import domain.Nail;
import enums.Nails;
import exceptions.ItemNotFoundException;
import runner.Runner;

/**
 * @author Alec Waddelow & Drew Rife 
 *
 * Tests Runner 
 */
public class TestRunner extends DBTest
{

	/**
	 * Tests creation of Runner  
	 */
	@Test
	public void testCreation() 
	{
		Runner run = new Runner();
		assertTrue(run instanceof Runner);
	}

	
	/**
	 * Tests getter of arraylist of inventory items 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	@Test
	public void testGetList() throws ClassNotFoundException, SQLException, NamingException
	{	
		assertEquals(21, Runner.getList().size());
	}
	
	/**
	 * Tests the getDetails method 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	@Test
	public void testGetDetails() throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		Nail nail = new Nail(1);
		Nail nailGD = (Nail) InventoryItem.getDetails(Nails.COMMON_10D.getUpc(), "Nail");  
	
		assertEquals(nail.getUpc(), nailGD.getUpc());
		assertEquals(nail.getManufacturerID(), nailGD.getManufacturerID());
		assertEquals(nail.getPrice(), nailGD.getPrice());
		assertEquals(nail.getLength(), nailGD.getLength(), 0.001);
		assertEquals(nail.getNumberInBox(), nailGD.getNumberInBox());
		assertEquals(nail.getClassName(), nailGD.getClassName());
	}
}