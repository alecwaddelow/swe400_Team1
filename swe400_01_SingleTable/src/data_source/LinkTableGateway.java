package data_source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 *	Establishes a connection to the LinkTable created
 */
public class LinkTableGateway 
{
	private static final String hostName = "jdbc:mysql://db.cs.ship.edu/swe400-12?useSSL=false";
	private static final String user = "swe400_1";
	private static final String password = "pwd4swe400_1F16";

	private static Connection con = null;

	/**
	 * Empty constructor for testing 
	 */
	public LinkTableGateway(){}
	
	/**
	 * Gets connection to the Database and returns the connection 
	 * 
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(con == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(hostName, user, password);
			con.setAutoCommit(false);

			return con;
		}
		else
		{
			return con;
		}
	}
	
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
		Statement st = LinkTableGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM LinkTable WHERE powerToolID =" + "'" + id + "';");
		ResultSet rs = st.executeQuery(sqlStatement);
		return rs;
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
		Statement st = LinkTableGateway.getConnection().createStatement();
		String sqlStatement = ("SELECT * FROM LinkTable WHERE stripNailID =" + "'" + id + "';");
		ResultSet rs = st.executeQuery(sqlStatement);
		return rs;
	}
	
	/**
	 * Inserts an item into the table within the database based on a prepared statement created by respective concrete class Mappers 
	 * 
	 * @param dbrs
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertRow(PreparedStatement statement) throws ClassNotFoundException, SQLException
	{
		System.out.println(statement);
		statement.execute();
		LinkTableGateway.getConnection().commit();
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
			PreparedStatement statement = getConnection().prepareStatement("INSERT INTO LinkTable (powerToolID, stripNailID) VALUES (?,?)");
			statement.setInt(1, powerToolID);
			statement.setInt(2, stripNailID);
			insertRow(statement);			
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
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private static boolean containsDuplicates(int powerToolID, int stripNailID) throws ClassNotFoundException, SQLException 
	{
		String sqlStatement = "select * from LinkTable where powerToolID=" + "'" + powerToolID + "'" + " and stripNailID=" + "'" + stripNailID + "';";
		Statement statement = DatabaseGateway.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(sqlStatement);
		
		if(resultSet.next())
		{
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
	    PreparedStatement preparedStmt = DatabaseGateway.getConnection().prepareStatement(query);
	    preparedStmt.setInt(1, powerToolID);
	    preparedStmt.setInt(2, stripNailID);
	    preparedStmt.execute();
		
	}
}
