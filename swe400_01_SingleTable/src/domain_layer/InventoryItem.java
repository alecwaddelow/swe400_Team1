package domain_layer;


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
	protected String className;
	
	/**
	 * Creation constructor
	 * 
	 * @param id
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 */
	InventoryItem(String UPC, int ManufacturerID, int price)
	{
		this.upc = UPC;
		this.manufacturerID = ManufacturerID;
		this.price = price;
	}
	
	/**
	 * Finder constructor 
	 * 
	 * @param id
	 */
	InventoryItem(int id)
	{
		this.id = id;
	
	}
	
	/**
	 * Empty Constructor for testing 
	 */
	InventoryItem(){}
	
	/**
	 * @return ID
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * @return UPC
	 */
	public String getUpc()
	{
		return this.upc;
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
		return this.manufacturerID;
	}

	/**
	 * Sets the manufacturerID
	 * 
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
		return this.price;
	}

	/**
	 * Sets the price
	 * 
	 * @param price
	 */
	public void setPrice(int price)
	{
		this.price = price;
	}

	/**
	 * @return the className
	 */
	public String getClassName() 
	{
		return this.className;
	}
	
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) 
	{
		this.className = className;
	}
}