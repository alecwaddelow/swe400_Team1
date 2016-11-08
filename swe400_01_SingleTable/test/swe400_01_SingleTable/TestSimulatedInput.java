
package swe400_01_SingleTable;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.Test;

import domain_layer.*;
import runner.Runner;

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
		File file = new File("SimulatedInput/SimulateInputForNail.txt");
		
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
		File file = new File("SimulatedInput/SimulateInputForTool.txt");
		
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
		File file = new File("SimulatedInput/SimulateInputAddingRelationForPowerTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			PowerTool powerTool = new PowerTool(22);
			assertEquals("2014", powerTool.getUpc());
			assertEquals(2020202, powerTool.getManufacturerID());
			assertEquals(60, powerTool.getPrice());
			assertEquals("powertool created from simulated input", powerTool.getDescription());
			assertTrue(powerTool.isBatteryPowered());
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
		File file = new File("SimulatedInput/SimulateInputForStripNail.txt");
		
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
		File file = new File("SimulatedInput/SimulateUpdatingNail.txt");
		
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
		File file = new File("SimulatedInput/SimulateUpdatingTool.txt");
		
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
		File file = new File("SimulatedInput/simUpdatePowerToolAddRelation.txt");
		
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
		File file = new File("SimulatedInput/SimulateUpdatingStripNail.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.initiateUserInput();
			
			StripNail stripNail = new StripNail(13);
			assertEquals("3030030330", stripNail.getUpc());
			assertEquals(15, stripNail.getManufacturerID());
			assertEquals(1347, stripNail.getPrice());
			assertEquals(3.1, stripNail.getLength(), 0.001);
			assertEquals(23, stripNail.getNumberInStrip());
			assertEquals("StripNail", stripNail.getClassName());
		}
	}
	
}
