package enums;

/**
 * A list of (non-power) tools for testing purposes
 * @author Merlin
 *
 */
public enum Tools
{
	/**
	 *
	 */
	HAMMER("0121232234", 32, 899, "Ball Peen Hammer"),
	/**
	 *
	 */
	SCREW_DRIVER_SET("1232343345", 42, 3999, "Ten piece screwdriver set"),
	/**
			 *
			 */
	WRENCH_SET("1111111111", 42, 5999, "Thirty piece wrench set (English)"),
	/**
			 *
			 */
	LUMBER_LOK("23423423232", 33, 1997, "Lumber Lok Vise"),
	/**
			 *
			 */
	STEEL_VISE("23424444232", 33, 1997, "4 in 1 Steel Vise");

	private String upc;

	private int manufacturerID;
	private int price;
	private String description;
	private Tools(String upc, int manufacturerID, int price, String description)
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
	}

	/**
	 * @return the description of this tool
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the unique ID of the manufacturer of this tool
	 */
	public int getManufacturerID()
	{
		return manufacturerID;
	}

	/**
	 * @return the price (in cents) of this tool
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * @return the UPC code for this tool
	 */
	public String getUpc()
	{
		return upc;
	}
}
