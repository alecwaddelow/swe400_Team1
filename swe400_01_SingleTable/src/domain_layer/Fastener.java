package domain_layer;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 * An InventoryItem where anything that inherits Fastener will have a length
 */
public abstract class Fastener extends InventoryItem
{
	
	protected double length;
	
	/**
	 * Creation constructor that calls InventoryItem's creation constructor
	 * 
	 * @param UPC
	 * @param ManufacturerID
	 * @param price
	 * @param length
	 */
	Fastener(String UPC, int ManufacturerID, int price, double length) 
	{
		super(UPC, ManufacturerID, price);
		this.length = length;
	}
	
	/**
	 * Finder constructor that calls Inventory Item's finder constructor
	 * 
	 * @param id
	 */
	Fastener(int id)
	{
		super(id);
	}


	/**
	 * Constructor with call to super for testing  
	 */
	public Fastener() 
	{
		super();
	}

	/**
	 * @return length 
	 */
	public double getLength()
	{
		return this.length;
	}

	/**
	 * @param length - sets the length of the fastener object
	 */
	public void setLength(double length)
	{
		this.length = length;
	}
}