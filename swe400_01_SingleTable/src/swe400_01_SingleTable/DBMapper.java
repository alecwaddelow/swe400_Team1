/**
 *
 */
package swe400_01_SingleTable;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * Encapsulates one row within the database table
 */
public class DBMapper
{
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
	protected String description;
	protected boolean batteryPowered;
	protected double length;
	protected int numberInStrip;
	protected int numberInBox;
	protected String className;

	/**
	 * Creates the DBMapper object
	 *
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 * @param batteryPowered
	 * @param length
	 * @param numberInStrip
	 * @param numberInBox
	 * @param className
	 */
	public DBMapper(int id, String upc, int manufacturerID, int price, String description,
			boolean batteryPowered, long length, int numberInStrip, int numberInBox, String className)
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		this.batteryPowered = batteryPowered;
		this.length = length;
		this.numberInStrip = numberInStrip;
		this.numberInBox = numberInBox;
		this.className = className;
	}

	public DBMapper()
	{
		super();
	}

	/**
	 * @return the item's ID
	 */
	protected int getId()
	{
		return id;
	}

	/**
	 * sets the item's ID
	 * @param id
	 */
	protected void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the item's UPC
	 */
	protected String getUpc()
	{
		return upc;
	}

	/**
	 * set the item's UPC
	 * @param upc
	 */
	protected void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * @return the item's manufacturer ID
	 */
	protected int getManufacturerID()
	{
		return manufacturerID;
	}

	/**
	 * set the item's manufacturer ID
	 * @param manufacturerID
	 */
	protected void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	/**
	 * @return the item's price
	 */
	protected int getPrice()
	{
		return price;
	}

	/**
	 * sets the item's price
	 * @param price
	 */
	protected void setPrice(int price)
	{
		this.price = price;
	}

	/**
	 * @return the item's description
	 */
	protected String getDescription()
	{
		return description;
	}

	/**
	 * sets the item's description
	 * @param description
	 */
	protected void setDescription(String description)
	{

		this.description = description;
	}

	/**
	 * @return true if the item is battery powered; false otherwise
	 */
	protected boolean isBatteryPowered()
	{
		return batteryPowered;
	}

	/**
	 * set true if the item is battery powered; false otherwise
	 * @param batteryPowered
	 */
	protected void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}

	/**
	 * @return the item's length
	 */
	protected double getLength()
	{
		return length;
	}

	/**
	 * set the item's length
	 * @param length2
	 */
	protected void setLength(double length)
	{
		this.length = length;
	}

	/**
	 * @return the item's number in strip
	 */
	protected int getNumberInStrip()
	{
		return numberInStrip;
	}

	/**
	 * set the item's number in the strip
	 * @param numberInStrip
	 */
	protected void setNumberInStrip(int numberInStrip)
	{
		this.numberInStrip = numberInStrip;
	}

	/**
	 * @return the number of item's in the box
	 */
	protected int getNumberInBox()
	{
		return numberInBox;
	}

	/**
	 * set the the number of item's in the box
	 * @param numberInBox
	 */
	protected void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}

	/**
	 * @return the item's class name
	 */
	protected String getClassName()
	{
		return className;
	}

	/**
	 * set the item's class name
	 * @param className
	 */
	protected void setClassName(String className)
	{
		this.className = className;
	}
}
