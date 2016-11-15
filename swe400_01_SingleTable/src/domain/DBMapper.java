package domain;
import java.sql.SQLException;
import data_source.DatabaseGateway;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Fields that all data mappers will share 
 */
public abstract class DBMapper
{
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
	protected String className;

	
	/**
	 * Constructor
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DBMapper(String upc, int manufacturerID, int price, String className) throws ClassNotFoundException, SQLException
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.className = className;
	}

	/**
	 * Constructor that calls the generic Object.class's constructor
	 */
	public DBMapper()
	{
		super();
	}
	
	/**
	 * Set ID
	 * 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void setId(String upc) throws ClassNotFoundException, SQLException
	{
		this.id = DatabaseGateway.getID(this.upc, this.className);
	}
	
	/**
	 * @return id
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * @return the item's UPC
	 */
	public String getUpc()
	{
		return this.upc;
	}

	/**
	 * Set UPC
	 * 
	 * @param upc
	 */
	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * @return the item's manufacturer ID
	 */
	public int getManufacturerID()
	{
		return this.manufacturerID;
	}

	/**
	 * Set manufacturerID
	 * 
	 * @param manufacturerID
	 */
	public void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	/**
	 * @return the item's price
	 */
	public int getPrice()
	{
		return this.price;
	}

	/**
	 * Set price 
	 * 
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}

	/**
	 * @return className
	 */
	public String getClassName()
	{
		return this.className;
	}

	/**
	 * Set className
	 * 
	 * @param className
	 */
	public void setClassName(String className)
	{
		this.className = className;
	}
}