package swe400_01_SingleTable;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Drew Rife & Alec Waddelow
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			TestArrayList.class,
			TestNail.class,
			TestPowerTool.class,
			TestStripNails.class,
			TestTool.class
		})

public class RunAllTests  {}