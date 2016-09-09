package swe400_01_SingleTable;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @authors Drew Rife & Alec Waddelow
 *
 */
public class Runner
{
	public static void main(String[] args) throws NamingException, SQLException, ClassNotFoundException
	{
		Runner run = new Runner();

		ArrayList<Object> list = new ArrayList<Object>();

		list =  run.createList();
	}

	public ArrayList<Object> createList() throws ClassNotFoundException, SQLException
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
		return null;
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
	public Object matchClassAndConstruct(int ID, String className) throws ClassNotFoundException, SQLException
	{
		if (className == null)
		{
			throw new  ClassNotFoundException();
		}
		else if (className.contains("Tool") || className.contains("tool"))
		{
			Tool tool = new Tool(ID);
			return tool;
		}
		else if (className.contains("PowerTool") || className.contains("powertool"))
		{		TestNail tn = new TestNail();
			PowerTool pt = new PowerTool(ID);
			return pt;
		}
		else if (className.contains("StripNails") || className.contains("stripnails"))
		{
			StripNail sn = new StripNail(ID);
			return sn;
		}
		else if (className.contains("Nail") || className.contains("nail"))
		{
			Nail nail = new Nail(ID);
			return nail;
		}
		return null;

	}
}




