
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import SimulatedUserInput.TestSimulatedInput;
import data_source.*;
import domain.*;
import swe400_01_SingleTable.*;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Runs all tests with simulated user input
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
			TestStripNailsMapper.class,
			TestNailMapper.class,
			TestLinkTableMapper.class,
			TestLinkTableGateway.class,
			TestDatabaseGateway.class,
			TestCreateLinkTable.class,
			TestCreateDatabase.class,
			TestRunner.class,
			TestSimulatedInput.class,
			TestCloseConnection.class
		})

public class RunAllTests  {}