package swe400_01_SingleTable;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 */
public abstract class InventoryItem
{
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
}













