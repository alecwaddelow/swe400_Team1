
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
	 * Simulates a user adding a nail to the database
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
			Runner.userInput();
			
			Nail nail = new Nail(22);
			assertEquals("2016", nail.getUpc());
			assertEquals(10, nail.getManufacturerID());
			assertEquals(30, nail.getPrice());
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
			Runner.userInput();
			
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
		File file = new File("SimulatedInput/SimulateInputForPowerTool.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.userInput();
			
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
			Runner.userInput();
			
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
	 * Simulates the worst user ever, one that is indecisive and makes mistakes along the way but ends creating a nail in the end
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testSimulateIndecisiveClumsyUser() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/SimulateIndecisiveUser.txt");
		
		if(file.exists())
		{
			System.setIn(new FileInputStream(file));
			Runner.userInput();
			
			Nail nail = new Nail(22);
			assertEquals("2016", nail.getUpc());
			assertEquals(10, nail.getManufacturerID());
			assertEquals(30, nail.getPrice());
			assertEquals(30.03, nail.getLength(), 0.001);
			assertEquals(20, nail.getNumberInBox());
			assertEquals("Nail", nail.getClassName());
			
			System.setIn(System.in);
		}
	}
}
