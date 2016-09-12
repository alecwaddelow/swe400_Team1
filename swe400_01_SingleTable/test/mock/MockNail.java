package mock;

import java.sql.SQLException;
import swe400_01_SingleTable.Nail;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Mock class for Nail
 */
public class MockNail extends Nail
{

	/**
	 * Calls the Nail finder constructor
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MockNail(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
	}

	/**
	 * Calls the Nail creation constructor
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param length
	 * @param numberInStrip
	 * @param numberInBox
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MockNail(int id, String upc, int manufacturerID, int price, String description, boolean batteryPowered,
			double length, int numberInStrip, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		super(id, upc, manufacturerID, price, description, batteryPowered, length, numberInStrip, numberInBox, className);
	}

	/**
	 * gets the numberInBox
	 */
	public int getNumberInBox()
	{
		return super.getNumberInBox();
	}

	/**
	 * sets the numberInBox
	 */
	public void setNumberInBox(int numberInBox)
	{
		super.setNumberInBox(numberInBox);
	}

	public String getClassName()
	{
		return super.getClassName();
	}

	/**
	 * sets the className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}

	/**
	 * gets the descriptions
	 */
	public String getDescription()
	{
		return super.getDescription();
	}

	/**
	 * sets the description
	 */
	public void setDescription(String description)
	{
		super.setDescription(description);
	}
}
