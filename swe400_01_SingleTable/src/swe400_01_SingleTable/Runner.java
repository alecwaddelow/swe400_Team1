package swe400_01_SingleTable;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *@authors Drew Rife & Alec Waddelow
 *
 *Runner queries the database to find keys and builds an array list of all the objects
 */
public class Runner
{
	/**
	 * Creates the table and calls for the insertion of the objects into the table
	 * @param args
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
		CreateDatabase.createTable();
		CreateDatabase.insertNailsIntoTable();
		CreateDatabase.insertToolsIntoTable();
		CreateDatabase.insertStripNailsIntoTable();
		CreateDatabase.insertPowerToolsIntoTable();

		ArrayList<Object> list = Runner.createList();
	}

	/**
	 * Builds the ArrayList of the objects
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Object> createList() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = ("SELECT id,className FROM InventoryItem;");
		Statement st = DatabaseGateway.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sqlStatement);
		ArrayList<Object> listOfObjects = new ArrayList<Object>();

		int i = 0;
		while(rs.next())
		{

			int id  = rs.getRow();
			String className = rs.getString("className");
			System.out.println(className);
			listOfObjects.add(i, matchClassAndConstruct(id, className));
			i++;
		}
		return listOfObjects;
	}

	/**
	 * Matches classes with their finder constructor, constructs an object, and returns it
	 *
	 * @param ID
	 * @param className
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Object matchClassAndConstruct(int ID, String className) throws ClassNotFoundException, SQLException
	{
		switch(className)
		{
		case "Tool":
			Tool tool = new Tool(ID);
			return tool;
		case "PowerTool":
			PowerTool powerTool= new PowerTool(ID);
			return powerTool;
		case "StripNail":
			StripNail stripNail = new StripNail(ID);
			return stripNail;
		case "Nail":
			Nail nail = new Nail(ID);
			return nail;
		default:
			throw new ClassNotFoundException();

		}
	}
}
