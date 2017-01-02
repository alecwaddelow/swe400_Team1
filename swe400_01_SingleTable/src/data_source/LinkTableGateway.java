package data_source;
import java.sql.*;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 *	Establishes a connection to the LinkTable created
 */
public class LinkTableGateway 
{
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	/**
	 * Queries the database for stripNails in Link table 
	 * 
	 * @param id
	 * @return resultSet of StripNail ID's 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet queryDBForStripNails(int id) throws SQLException, ClassNotFoundException
	{
		String sqlStatement = ("SELECT * FROM LinkTable WHERE powerToolID =?");
		preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	} 
	
	/**
	 * Queries the database for PowerTools in Link table 
	 * 
	 * @param id
	 * @return ResultSet of powerTool ID's
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet queryDBForPowerTools(int id) throws SQLException, ClassNotFoundException
	{
		String sqlStatement = ("SELECT * FROM LinkTable WHERE stripNailID=?");
		preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery(); 
		return resultSet;
	}
	
	/**
	 * Inserts an item into the table within the database based on a prepared statement 
	 * 
	 * @param PreparedStatement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertRow(PreparedStatement statement) throws ClassNotFoundException, SQLException
	{
		statement.execute();
		statement.close();
	}

	/**
	 * Adds relation to LinkTable 
	 * 
	 * @param powerToolID
	 * @param stripNailID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void addRelation(int powerToolID, int stripNailID) throws SQLException, ClassNotFoundException 
	{
		if(!containsDuplicates(powerToolID, stripNailID))
		{
			PreparedStatement statement = ConnectionManager.getConnection().prepareStatement("INSERT INTO LinkTable (powerToolID, stripNailID) VALUES (?,?)");
			statement.setInt(1, powerToolID);
			statement.setInt(2, stripNailID);
			insertRow(statement);
			closeStatements();
		}
		else
		{
			System.out.println("Already exists within the Database");
		}
	}

	/**
	 * Checks input values with pre-existing values
	 * 
	 * @param powerToolID
	 * @param stripNailID
	 * @return boolean of whether or not duplicate(s) were found 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private static boolean containsDuplicates(int powerToolID, int stripNailID) throws ClassNotFoundException, SQLException 
	{
		String sqlStatement = "select * from LinkTable where powerToolID=? and stripNailID=?";
		preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, powerToolID);
		preparedStatement.setInt(2, stripNailID);
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next())
		{
			closeStatements();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Removes the relation from the LinkTable
	 * 
	 * @param powerToolID
	 * @param stripNailID
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void removeRelation(int powerToolID, int stripNailID) throws ClassNotFoundException, SQLException 
	{
		String query = "delete from LinkTable where powerToolID=? and stripNailID=?";
	    preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
	    preparedStatement.setInt(1, powerToolID);
	    preparedStatement.setInt(2, stripNailID);
	    preparedStatement.execute();
		closeStatements();
	}
	
	/**
	 * Closes the statements and result sets when done
	 * 
	 * @throws SQLException
	 */
	public static void closeStatements() throws SQLException
	{
		if(resultSet != null)
		{
			if(!resultSet.isClosed())
			{
				resultSet.close();
				resultSet = null;
			}
		}
		
		if(preparedStatement != null)
		{
			if(!preparedStatement.isClosed())
			{
				preparedStatement.close();
				preparedStatement = null;
			}			
		}
	}
}