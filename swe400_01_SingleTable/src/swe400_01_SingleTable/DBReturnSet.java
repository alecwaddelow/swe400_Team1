/**
 *
 */
package swe400_01_SingleTable;

/**
 * @author drew
 *
 */
public class DBReturnSet
{
	private int id;
	private String upc;
	private int manufacturerID;
	private int price;
	private String description;
	private boolean batteryPowered;
	private long length;
	private int numberInStrip;
	private int numberInBox;
	private String className;

	public DBReturnSet(int id2, String upc2, int manufacturerID2, int price2, String description2,
			boolean batteryPowered2, long length2, int numberInStrip2, int numberInBox2, String className2) {
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

	private int getId()
	{
		return id;
	}

	private void setId(int id)
	{
		this.id = id;
	}

	private String getUpc()
	{
		return upc;
	}

	private void setUpc(String upc)
	{
		this.upc = upc;
	}

	private int getManufacturerID()
	{
		return manufacturerID;
	}

	private void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	private int getPrice()
	{
		return price;
	}

	private void setPrice(int price)
	{
		this.price = price;
	}

	private String getDescription()
	{
		return description;
	}

	private void setDescription(String description)
	{

		this.description = description;
	}

	private boolean isBatteryPowered()
	{
		return batteryPowered;
	}

	private void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}

	private long getLength()
	{
		return length;
	}

	private void setLength(long length)
	{
		this.length = length;
	}

	private int getNumberInStrip()
	{
		return numberInStrip;
	}

	private void setNumberInStrip(int numberInStrip)
	{
		this.numberInStrip = numberInStrip;
	}

	private int getNumberInBox()
	{
		return numberInBox;
	}

	private void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}

	private String getClassName()
	{
		return className;
	}

	private void setClassName(String className)
	{
		this.className = className;
	}


}
