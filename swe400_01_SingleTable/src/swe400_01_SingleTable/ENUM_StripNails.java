package swe400_01_SingleTable;
/**
 * The list of strip nails that should be in your db. NOTICE: the type of the
 * variable "length" should be double - NOT Long as it was in the project spec
 *
 * @author Merlin
 *
 */
public enum ENUM_StripNails
{
	/**
	 * Works with Hitachi's pneumatic nailer and the framing nailer
	 */
	ROUND_HEAD_NAIL_STRIP("5453432345", 13, 1099, 2.5, 50),
	/**
	 * Works with Hitachi's pneumatic nailer and the framing nailer
	 */
	QUALITY_ROUND_HEAD_NAIL_STRIP("4343434543", 24, 1299, 3, 75),
	/**
	 * Doesn't work with any of the nailers we sell
	 */
	NOTCHED_HEAD_NAIL_STRIP("9876784727", 13, 2099, 2.5, 50),
	/**
	 * Works with both brad nailers
	 */
	BRAD_STRIP("6565459876", 24, 1988, 1.5, 150),
	/**
	 * works with both brad nailers
	 */
	BRIGHT_FINISH_BRAD_STRIP("4343432345", 13, 1576, 1.5, 150);

	private String upc;
	private int manufacturerID;
	private int price;
	private double length;
	private int numberInStrip;

	private StripNails(String upc, int manufacturerID, int price,
			double length, int numberInStrip)
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.length = length;
		this.numberInStrip = numberInStrip;
	}

	/**
	 * @return the length in inches of the nails in the strip
	 */
	public double getLength()
	{
		return length;
	}

	/**
	 * @return the unique ID of the manufacturer of these nails
	 */
	public int getManufacturerID()
	{
		return manufacturerID;
	}

	/**
	 * @return the number of nails in a strip
	 */
	public int getNumberInStrip()
	{
		return numberInStrip;
	}

	/**
	 * @return the price of one strip of nails
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * @return this product's UPC code
	 */
	public String getUpc()
	{
		return upc;
	}
}
