/**
 *
 */
package swe400_01_SingleTable;

/**
 * @author Drew Rife & Alec Waddelow
 *
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

	public DBMapper(int id2, String upc2, int manufacturerID2, int price2, String description2,
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

	public DBMapper()
	{
		super();
	}

	protected int getId()
	{
		return id;
	}

	protected void setId(int id)
	{
		this.id = id;
	}

	protected String getUpc()
	{
		return upc;
	}

	protected void setUpc(String upc)
	{
		this.upc = upc;
	}

	protected int getManufacturerID()
	{
		return manufacturerID;
	}

	protected void setManufacturerID(int manufacturerID)
	{
		this.manufacturerID = manufacturerID;
	}

	protected int getPrice()
	{
		return price;
	}

	protected void setPrice(int price)
	{
		this.price = price;
	}

	protected String getDescription()
	{
		return description;
	}

	protected void setDescription(String description)
	{

		this.description = description;
	}

	protected boolean isBatteryPowered()
	{
		return batteryPowered;
	}

	protected void setBatteryPowered(boolean batteryPowered)
	{
		this.batteryPowered = batteryPowered;
	}

	protected double getLength()
	{
		return length;
	}

	protected void setLength(double length2)
	{
		this.length = length2;
	}

	protected int getNumberInStrip()
	{
		return numberInStrip;
	}

	protected void setNumberInStrip(int numberInStrip)
	{
		this.numberInStrip = numberInStrip;
	}

	protected int getNumberInBox()
	{
		return numberInBox;
	}

	protected void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}

	protected String getClassName()
	{
		return className;
	}

	protected void setClassName(String className)
	{
		this.className = className;
	}


}
