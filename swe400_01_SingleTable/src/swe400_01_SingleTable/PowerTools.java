package swe400_01_SingleTable;

/**
 * The details of the power tools for your test data
 *
 * @author Merlin
 *
 */
public enum PowerTools
{
	/**
	 *
	 */
	HITACHI_PNEUMATIC_NAILER("1231231234", 13, 39900, "Pheumatic Nail Gun",
			false),
	/**
	 *
	 */
	FRAMING_NAILER("4445553333", 24, 42999, "Framing Nail Gun", false),
	/**
	 *
	 */
	TEN_INCH_TABLE_SAW("7657896543", 24, 39654, "Standard 10 inch table saw",
			false),
	/**
	 *
	 */
	TEN_INCH_PORTABLE_TABLE_SAW("9993458585", 13, 55555,
			"Portable 10 inch table saw", false),
	/**
	 *
	 */
	BRAD_CORDLESS_NAILER("7654564848", 24, 33424, "Cordless brad nailer", true),
	/**
	 *
	 */
	BRAD_NAILER("7784452828", 13, 15758, "Brad nailer", false), ;

	private String upc;
	private int manufacturerID;
	private int price;
	private String description;
	private boolean batteryPowered;

	private PowerTools(String upc, int manufacturerID, int price,
			String description, boolean batteryPowered)
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		this.batteryPowered = batteryPowered;
	}

	/**
	 * @return the description of this tool
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the unique ID of the manufacturer who made this tool
	 */
	public int getManufacturerID()
	{
		return manufacturerID;
	}

	/**
	 * @return this tool's price
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * @return this tool's UPC code
	 */
	public String getUpc()
	{
		return upc;
	}

	/**
	 * @return true if this tool runs off a battery
	 */
	public boolean isBatteryPowered()
	{
		return batteryPowered;
	}
}
