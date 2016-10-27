package swe400_01_SingleTable;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Runs all tests
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
			TestSimulatedInput.class
		})

public class RunAllTests  {}