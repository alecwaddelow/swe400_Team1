package data_source;
import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.*;

import com.mysql.jdbc.Connection;

import sun.awt.image.ImageWatched.Link;

/**
 * @author Alec Waddelow & Drew Rife 
 *
 * Creates the separate the table that shows the relationships between StripNails and PowerTools
 */
public class CreateLinkTable 
{
	static String linkID = "linkID int PRIMARY KEY NOT NULL auto_increment, ";
	static String powerToolID = "powerToolID int, ";
	static String stripNailID = "stripNailID int, ";
	static String PTForeign = "FOREIGN KEY (powerToolID) REFERENCES InventoryItem(id), ";
	static String SNForeign = "FOREIGN KEY (stripNailID) REFERENCES InventoryItem(id) ";

	/**
	 * Creates the table for the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createTable() throws ClassNotFoundException, SQLException
	{
		String sqlStatement = "CREATE TABLE LinkTable (" + linkID + powerToolID + stripNailID + PTForeign + SNForeign +  ");";
		Statement st = LinkTableGateway.getConnection().createStatement();
		st.execute(sqlStatement);
	}

	/**
	 * Drops the table before creation if the table exists
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void dropTableBeforeCreation() throws ClassNotFoundException, SQLException
	{
		String dropTable = "DROP TABLE IF EXISTS LinkTable;";
		Statement st = LinkTableGateway.getConnection().createStatement();
		st.execute(dropTable);
	}

	/**
	 * Create static relationships in LinkTable
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void createRelationships() throws ClassNotFoundException, SQLException
	{		
		LinkTableGateway.addRelation(16, 11);
		LinkTableGateway.addRelation(17, 11);
		LinkTableGateway.addRelation(16, 12);
		LinkTableGateway.addRelation(17, 12);
		LinkTableGateway.addRelation(20, 14);
		LinkTableGateway.addRelation(21, 14);
		LinkTableGateway.addRelation(21, 15);
		PreparedStatement statement = LinkTableGateway.getConnection().prepareStatement("INSERT INTO LinkTable (powerToolID, stripNailID) VALUES (?,?)");
		statement.setNull(1, 0);
		statement.setInt(2, 13);
		LinkTableGateway.insertRow(statement);
	}
}
