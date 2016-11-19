
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import SimulatedUserInput.TestSimulatedInput;
import data_source.*;
import domain.*;
import other.*;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Runs all tests with simulated user 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			TestArrayList.class,
			TestNail.class,
			TestPowerTool.class,
			TestStripNail.class,
			TestTool.class,
			TestDBMapper.class,
			TestToolMapper.class,
			TestPowerToolMapper.class,
			TestStripNailMapper.class,
			TestNailMapper.class,
			TestLinkTableMapper.class,
			TestLinkTableGateway.class,
			TestDatabaseGateway.class,
			TestCreateLinkTable.class,
			TestCreateDatabase.class,
			TestRunner.class,
			TestSimulatedInput.class,
			TestInventoryItem.class,
			TestCloseConnection.class
		})

public class RunAllTests  {}