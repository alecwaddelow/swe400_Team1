package data_source;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 *	Establishes a connection to the LinkTable created
 */
public class LinkTableGateway 
{
	/**
	 * Queries the database for stripNails in Link table 
	 * 
	 * @param id
	 * @return resultSet of StripNail ID's 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<LinkTableDTO> queryDBForStripNails(int id) throws SQLException, ClassNotFoundException
	{
		String sqlStatement = ("SELECT * FROM LinkTable WHERE powerToolID = ?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LinkTableDTO> listLinkTableDTO = new ArrayList<LinkTableDTO>();
		
		while(resultSet.next())
		{
			LinkTableDTO ltDTO = new LinkTableDTO();
			ltDTO.setStripNailID(resultSet.getInt("stripNailID"));
			listLinkTableDTO.add(ltDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listLinkTableDTO;
	} 
	
	/**
	 * Queries the database for PowerTools in Link table 
	 * 
	 * @param id
	 * @return ResultSet of powerTool ID's
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<LinkTableDTO> queryDBForPowerTools(int id) throws SQLException, ClassNotFoundException
	{
		String sqlStatement = ("SELECT * FROM LinkTable WHERE stripNailID=?");
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery(); 
		List<LinkTableDTO> listLinkTableDTO = new ArrayList<LinkTableDTO>();
		
		while(resultSet.next())
		{
			LinkTableDTO ltDTO = new LinkTableDTO();
			ltDTO.setPowerToolID(resultSet.getInt("powerToolID"));
			listLinkTableDTO.add(ltDTO);
		}
		
		resultSet.close();
		preparedStatement.close();
		return listLinkTableDTO;
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
			PreparedStatement preparedStatemet = ConnectionManager.getConnection().prepareStatement("INSERT INTO LinkTable (powerToolID, stripNailID) VALUES (?,?)");
			preparedStatemet.setInt(1, powerToolID);
			preparedStatemet.setInt(2, stripNailID);
			insertRow(preparedStatemet);
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
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlStatement);
		preparedStatement.setInt(1, powerToolID);
		preparedStatement.setInt(2, stripNailID);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		boolean containsDuplicates = false;
		if(resultSet.next())
		{
			containsDuplicates = true;
		}
		
		resultSet.close();
		preparedStatement.close();
		return containsDuplicates;
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
		PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
	    preparedStatement.setInt(1, powerToolID);
	    preparedStatement.setInt(2, stripNailID);
	    preparedStatement.execute();
		preparedStatement.close();
	}
}