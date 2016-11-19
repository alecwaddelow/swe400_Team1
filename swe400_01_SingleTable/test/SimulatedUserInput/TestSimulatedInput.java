package SimulatedUserInput;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;

import org.junit.Test;
import data_source.LinkTableGateway;
import domain.*;
import other.DBTest;
import runner.Runner;
import user_input.*;

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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
			Runner.initiateUserInput();
			
			Tool tool = new Tool(22);
			assertEquals("2013", tool.getUpc());
			assertEquals(10, tool.getManufacturerID());
			assertEquals(20, tool.getPrice());
			assertEquals("tool created from simulated input", tool.getDescription());
			assertEquals("Tool", tool.getClassName());
			
			System.setIn(System.in);
		}
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
			Runner.initiateUserInput();
			
			Tool tool = new Tool(7);
			assertEquals("222222222", tool.getUpc());
			assertEquals(230, tool.getManufacturerID());
			assertEquals(3, tool.getPrice());
			assertEquals("Tool created from simulated input", tool.getDescription());
			assertEquals("Tool", tool.getClassName());
			
			System.setIn(System.in);
		}
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		File file = new File("SimulatedInput/StripNail/SimulateUpdatingStripNail.txt");
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
			Runner.initiateUserInput();
			
			StripNail stripNail = new StripNail(11);
			assertEquals("3030303", stripNail.getUpc());
			assertEquals(20, stripNail.getManufacturerID());
			assertEquals(15, stripNail.getPrice());
			assertEquals(30.056, stripNail.getLength(), 0.001);
			assertEquals(99, stripNail.getNumberInStrip());
			assertEquals("StripNail", stripNail.getClassName());
			
			
			try {
				simulation.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	public void testUpdatingCompatiblePowerToolForStripNail() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/StripNail/SimulateUpdatingCompatiblePowerToolForStripNail.txt");
		InputStream simulation = new FileInputStream(file);
				
		PowerTool powerTool = new PowerTool(21);
		if(file.exists())
		{
			System.setIn(simulation);
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
	 * Simulates adding a compatible stripnail to powertool
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testSimulateAddCompatiblePowerToolToStripNail() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/StripNail/SimulateAddingCompatiblePowerToolForStripNail.txt");
		InputStream simulation = new FileInputStream(file);
		
		StripNail stripNail = new StripNail(11);
		if(file.exists())
		{
			System.setIn(simulation);
			Scanner scanner = new Scanner(System.in);
			
			UserInput.addCompatibles(scanner, stripNail);
			
			System.setIn(System.in);
			
			PowerTool powerTool = stripNail.getPowerToolList().get(2);
			
			assertEquals(3, stripNail.getPowerToolList().size());
			assertEquals(21, powerTool.getId());
			assertEquals("7784452828", powerTool.getUpc());
			assertEquals(13, powerTool.getManufacturerID());
			assertEquals(15758, powerTool.getPrice());
			assertEquals("Brad nailer", powerTool.getDescription());
			assertFalse(powerTool.isBatteryPowered());
			assertEquals("PowerTool", powerTool.getClassName());
		}
	}
	
	/**
	 * Simulates adding a compatible stripnail to powertool
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	@Test
	public void testSimulateAddCompatibleStripNailToPowerTool() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateAddingCompatibleStripNailForPowerTool.txt");
		InputStream simulation = new FileInputStream(file);
		
		PowerTool powerTool = new PowerTool(20);
		if(file.exists())
		{
			System.setIn(simulation);
			Scanner scanner = new Scanner(System.in);
			
			UserInput.addCompatibles(scanner, powerTool);
			
			System.setIn(System.in);
			
			StripNail stripNail = powerTool.getStripNailList().get(1);
			
			assertEquals(2, powerTool.getStripNailList().size());
			assertEquals(13, stripNail.getId());
			assertEquals("9876784727", stripNail.getUpc());
			assertEquals(13, stripNail.getManufacturerID());
			assertEquals(2099, stripNail.getPrice());
			assertEquals(2.5, stripNail.getLength(), 0.001);
			assertEquals(50, stripNail.getNumberInStrip());
			assertEquals("StripNail", stripNail.getClassName());
		}

		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void testUpdatingCompatibleStripNailForPowerTool() throws ClassNotFoundException, SQLException, FileNotFoundException
	{
		File file = new File("SimulatedInput/PowerTool/SimulateUpdatingCompatibleStripNailForPowerTool.txt");
		InputStream simulation = new FileInputStream(file);
		
		StripNail stripNail = new StripNail(12);
		if(file.exists())
		{
			System.setIn(simulation);
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
		
		try {
			simulation.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests simulating removing a compatible stripnail from a powertool.
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRemoveCompatibilityForPowerTool() throws FileNotFoundException, ClassNotFoundException, SQLException 
	{
		File file = new File("SimulatedInput/PowerTool/SimulateRemovingCompatibleStripNail.txt");
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
			Scanner sc = new Scanner(System.in);
			
			PowerTool powerTool = new PowerTool(20);
			PowerToolInput.removeCompatibilities(sc, powerTool); 
			
			ResultSet resultSet = LinkTableGateway.queryDBForPowerTools(powerTool.getId());
			assertFalse(resultSet.next());
			
			resultSet.close();
			LinkTableGateway.closeStatements();
			
			try {
				simulation.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Tests simulating removing a compatible powertool from stripnail
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void testRemoveCompatibilityForStripNail() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		File file = new File("SimulatedInput/StripNail/SimulateRemovingCompatiblePowerTool.txt"); 
		InputStream simulation = new FileInputStream(file);
		
		if(file.exists())
		{
			System.setIn(simulation);
			Scanner sc = new Scanner(System.in);
			
			StripNail stripNail = new StripNail(15);
			StripNailInput.removeCompatibilities(sc, stripNail);
			
			ResultSet resultSet = LinkTableGateway.queryDBForStripNails(stripNail.getId());
			assertFalse(resultSet.next());
			
			resultSet.close();
			LinkTableGateway.closeStatements();
			
			try {
				simulation.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setIn(System.in);
		}
	}
	
	/**
	 * Simulates looking for a nail by searching by a upc.
	 *
	 * Since SearchByUPC is only input and output, the only way to test
	 * is to save the output and test that the output is correct
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException 
	 */
	@Test
	public void testNailUPCRequest() throws ClassNotFoundException, SQLException, IOException
	{
		File inputFile = new File("SimulatedInput/UPC_Request/Input/Nail_UPC.txt");
		File outputFile = new File("SimulatedInput/UPC_Request/Output/outputNail.txt");
		
		/**
		 * delete the file used from the previous ran testing
		 */
		if(!outputFile.exists())
		{
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		InputStream simulation = new FileInputStream(inputFile);
		PrintStream stdout = System.out;
		PrintStream output = new PrintStream(new FileOutputStream(outputFile, false));
		
		/* both files should be present before simulation */
		if(inputFile.exists() && outputFile.exists())
		{
			System.setIn(simulation);
			System.setOut(output);
			UserInput.userInput();
			
			List<String> lines = Files.readAllLines(new File("SimulatedInput/UPC_Request/Output/outputNail.txt").toPath(), Charset.defaultCharset()); 
			
			/* user entered that they wanted to search an item by UPC */
			assertTrue(lines.contains("Which item were you thinking of? (Enter the number)"));
			
			/* user didn't enter a valid input */
			assertTrue(lines.contains("Error: Not correct input"));
			
			/* user entered a valid nail upc */
			assertTrue(lines.contains("Nail [upc=5453432767, manufacturerID=15, price=1348, length=3.0, numberInBox=500]"));
		}
		
		simulation.close();
		output.close();
		System.setOut(stdout);
	}
	
	/**
	 * test instantiation of NailInput class
	 */
	@Test
	public void testNailInputInstantiation()
	{
		NailInput nailInput = new NailInput();
		assertTrue(nailInput instanceof NailInput);
	}
	
	/**
	 * test instantiation of ToolInput class
	 */
	@Test
	public void testToolInputInstantiation()
	{
		ToolInput toolInput = new ToolInput();
		assertTrue(toolInput instanceof ToolInput);
	}
	
	/**
	 * test instantiation of PowerToolInput class
	 */
	@Test
	public void testPowerToolInputInstantiation()
	{
		PowerToolInput powerToolInput = new PowerToolInput();
		assertTrue(powerToolInput instanceof PowerToolInput);
	}
	
	/**
	 * test instantiation of StripNailInput class
	 */
	@Test
	public void testStripNailInputInstantiation()
	{
		StripNailInput stripNailInput = new StripNailInput();
		assertTrue(stripNailInput instanceof StripNailInput);
	}
}