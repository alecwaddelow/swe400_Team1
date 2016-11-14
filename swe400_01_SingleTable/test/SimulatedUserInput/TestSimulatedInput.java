package SimulatedUserInput;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.junit.Test;
import data_source.LinkTableGateway;
import domain.*;
import other.DBTest;
import runner.Runner;
import userInput.PowerToolInput;
import userInput.StripNailInput;

/**
 * @author Drew Rife & Alec Waddelow
 * 
 * Tests simulating user input by redirecting System input
 */
public class TestSimulatedInput extends DBTest
{
	/**
	 * Simulates a user add a nail to the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSimulateCreateNail() throws FileNotFoundException, ClassNotFoundException, SQLException 
	{
		File file = new File("SimulatedInput/Nail/SimulateCreateNail.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			Nail nail = new Nail(22);
			assertEquals("3030303", nail.getUpc());
			assertEquals(20, nail.getManufacturerID());
			assertEquals(10, nail.getPrice());
			assertEquals(30.03, nail.getLength(), 0.001);
			assertEquals(20, nail.getNumberInBox());
			assertEquals("Nail", nail.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates a user adding a powertool to the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSimulateCreateTool() throws FileNotFoundException, ClassNotFoundException, SQLException 
	{
		File file = new File("SimulatedInput/Tool/SimulateCreateTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			Tool tool = new Tool(22);
			assertEquals("2013", tool.getUpc());
			assertEquals(10, tool.getManufacturerID());
			assertEquals(20, tool.getPrice());
			assertEquals("tool created from simulated input", tool.getDescription());
			assertEquals("Tool", tool.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates a user adding a power tool to the database
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSimulateCreatePowerTool() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateCreatePowerTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			PowerTool powerTool = new PowerTool(22);
			assertEquals("303030303", powerTool.getUpc());
			assertEquals(30, powerTool.getManufacturerID());
			assertEquals(30, powerTool.getPrice());
			assertEquals("powertool created from simulated input", powerTool.getDescription());
			assertFalse(powerTool.isBatteryPowered());
			assertEquals("PowerTool", powerTool.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates a user adding a strip nail to the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSimulateCreateStripNail() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/StripNail/SimulateCreateStripNail.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			StripNail stripNail = new StripNail(22);
			assertEquals("2015", stripNail.getUpc());
			assertEquals(202, stripNail.getManufacturerID());
			assertEquals(10, stripNail.getPrice());
			assertEquals(5.01, stripNail.getLength(), 0.001);
			assertEquals(36, stripNail.getNumberInStrip());
			assertEquals("StripNail", stripNail.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates updating a Nail in the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	@Test 
	public void testSimulateUpdateNail() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/Nail/SimulateUpdatingNail.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			Nail nail = new Nail(1);
			assertEquals("3030030330", nail.getUpc());
			assertEquals(15, nail.getManufacturerID());
			assertEquals(1347, nail.getPrice());
			assertEquals(3.1, nail.getLength(), 0.001);
			assertEquals(499, nail.getNumberInBox());
			assertEquals("Nail", nail.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates updating a Tool in the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test 
	public void testSimulateUpdateTool() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/Tool/SimulateUpdatingTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			Tool tool = new Tool(7);
			assertEquals("222222222", tool.getUpc());
			assertEquals(230, tool.getManufacturerID());
			assertEquals(3, tool.getPrice());
			assertEquals("Tool created from simulated input", tool.getDescription());
			assertEquals("Tool", tool.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates updating a PowerTool in the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test 
	public void testSimulateUpdatePowerToolWithAddedRelation() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateUpdatingPowerTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			PowerTool powerTool = new PowerTool(21);
			assertEquals("222222222", powerTool.getUpc());
			assertEquals(230, powerTool.getManufacturerID());
			assertEquals(3, powerTool.getPrice());
			assertEquals("PowerTool created from simulated input", powerTool.getDescription());
			assertTrue(powerTool.isBatteryPowered());
			assertEquals("PowerTool", powerTool.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates updating a StripNail in the database
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test 
	public void testSimulateUpdateStripNail() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateUpdatingStripNail.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			StripNail stripNail = new StripNail(11);
			assertEquals("3030303", stripNail.getUpc());
			assertEquals(20, stripNail.getManufacturerID());
			assertEquals(15, stripNail.getPrice());
			assertEquals(30.056, stripNail.getLength(), 0.001);
			assertEquals(99, stripNail.getNumberInStrip());
			assertEquals("StripNail", stripNail.getClassName());
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates adding a compatible stripNail
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testAddingCompatibleStripNail() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/StripNail/SimulateAddingCompatibleStripNail.txt");
		PowerTool powerTool = new PowerTool(21);
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Scanner sc = new Scanner(System.in);
			
			PowerToolInput.updateCompatibilities(sc, powerTool);
			
			System.setIn(System.in);
			
			/* strip nail in the text file for simulation that has been added to the relation */
			StripNail stripNailAdded = new StripNail(11);
			ResultSet rSet = LinkTableGateway.queryDBForPowerTools(stripNailAdded.getId());
			
			boolean hasAddedRelation = false;
			while(rSet.next())
			{
				if(rSet.getInt("stripNailID") == 11)
				{
					hasAddedRelation = true;
				}
			}
			
			/* the relation has been added to the table */
			assertTrue(hasAddedRelation);
		}
	}
	
	/**
	 * Tests for adding compatible powerTools to StripNails
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testCompatiblePowerTool() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateAddingCompatiblePowerTool.txt");
		StripNail stripNail = new StripNail(12);
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Scanner sc = new Scanner(System.in);
			
			StripNailInput.updateCompatibilities(sc, stripNail);
			
			System.setIn(System.in);
			
			/* powertool in the text file for simulation that has been added to the relation */
			PowerTool powerToolAdded1 = new PowerTool(16);
			PowerTool powerToolAdded2 = new PowerTool(17);
			ResultSet rSet1 = LinkTableGateway.queryDBForStripNails(powerToolAdded1.getId());
			ResultSet rSet2 = LinkTableGateway.queryDBForStripNails(powerToolAdded2.getId());
			
			boolean hasPowerTool = false;
			
			while(rSet1.next())
			{
				if(rSet1.getInt("powerToolID") == 16)
				{
					hasPowerTool = true;
				}
			}
			
			assertTrue(hasPowerTool);
			
			hasPowerTool = false;
			while(rSet2.next())
			{
				if(rSet2.getInt("powerToolID") == 17)
				{
					hasPowerTool = true;
				}
			}
			
			assertTrue(hasPowerTool);
		}
	}
}