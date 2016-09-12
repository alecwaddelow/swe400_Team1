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
 * Anything that inherits InventoryItem will have an id, upc, manufacturerID and a price
 */
public abstract class InventoryItem
{
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;

	/**
	 * @return ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * sets the ID
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return UPC
	 */
	public String getUpc()
	{
		return upc;
	}

	/**
	 * sets the upc
	 * @param upc
	 */
	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * @return manufacturerID
	 */
	public int getManufacturerID()
	{
		return manufacturerID;
	}

	/**
	 * sets the manufacturerID
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	/**
	 * @return price
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * sets the price
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}
}