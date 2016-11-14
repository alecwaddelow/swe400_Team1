package enums;

import java.sql.SQLException;

import domain.Nail;
import domain.PowerTool;
import domain.StripNail;
import domain.Tool;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class InsertEnumData 
{
	/**
	 * Inserting the enum nails into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Nails nail : Nails.values())
		{			
			new Nail(nail.getUpc(), nail.getManufacturerID(), nail.getPrice(), nail.getLength(), nail.getNumberInBox(), "Nail");	
		}
	}

	/**
	 * Inserting the enum tools into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(Tools tool : Tools.values())
		{
			new Tool(tool.getUpc(), tool.getManufacturerID(), tool.getPrice(), tool.getDescription(), "Tool");
		}
	}

	/**
	 * Inserting the enum strip nails into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertStripNailsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(StripNails stripNail : StripNails.values())
		{			
			new StripNail(stripNail.getUpc(), stripNail.getManufacturerID(), stripNail.getPrice(), stripNail.getLength(), stripNail.getNumberInStrip(), "StripNail");
		}
	}

	/**
	 * Inserting the enum power tools into the table
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPowerToolsIntoTable() throws ClassNotFoundException, SQLException
	{
		for(PowerTools powerTool : PowerTools.values())
		{
			new PowerTool(powerTool.getUpc(), powerTool.getManufacturerID(), powerTool.getPrice(), powerTool.getDescription(), powerTool.isBatteryPowered(), "PowerTool");
		}
	}
}