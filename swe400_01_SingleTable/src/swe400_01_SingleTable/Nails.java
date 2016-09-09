package swe400_01_SingleTable;

/**
 * The list of nail boxes that should be in your db. NOTICE: the type of the
 * variable "length" should be double - NOT Long as it was in the project spec
 *
 * @author Merlin
 *
 */
public enum Nails
{
	/**
	 *
	 */
	COMMON_10D("5453432767", 15, 1348, 3, 500),
	/**
	 *
	 */
	BRIGHT_10D("4343412343", 27, 1899, 3, 750),
	/**
	 *
	 */
	COMMON_8D("9876711127", 15, 1212, 2.5, 500),
	/**
	 *
	 */
	BRIGHT_8D("6562229876", 27, 1988, 2.5, 750),
	/**
	 *
	 */
	BRIGHT_FINISH_BRAD_STRIP("4343432345", 13, 1576, 1.5, 150);

	private String upc;
	private int manufacturerID;
	private int price;
	private double length;
	private int numberInBox;

	private Nails(String upc, int manufacturerID, int price,
			double length, int numberInBox)
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.length = length;
		this.numberInBox = numberInBox;
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
	 * @return the number of nails in a box
	 */
	public int getNumberInBox()
	{
		return numberInBox;
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
