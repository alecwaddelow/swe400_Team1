package data_source;

/**
 * @author Alec Waddelow and Drew Rife 
 *
 */
public class DataTransferObject 
{
	private int id;
	private String upc; 
	private int manufacturerID;
	private int price;
	private String description;
	private boolean batteryPowered;
	private double length;
	private int numberInStrip;
	private int numberInBox;
	private String className;

	public DataTransferObject()
	{
		//empty, only getters and setters used 
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the upc
	 */
	public String getUpc() {
		return this.upc;
	}
	/**
	 * @param upc the upc to set
	 */
	public void setUpc(String upc) {
		this.upc = upc;
	}
	/**
	 * @return the manufacturerID
	 */
	public int getManufacturerID() {
		return this.manufacturerID;
	}
	/**
	 * @param manufacturerID the manufacturerID to set
	 */
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return this.price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the batteryPowered
	 */
	public boolean isBatteryPowered() {
		return this.batteryPowered;
	}
	/**
	 * @param batteryPowered the batteryPowered to set
	 */
	public void setBatteryPowered(boolean batteryPowered) {
		this.batteryPowered = batteryPowered;
	}
	/**
	 * @return the length
	 */
	public double getLength() {
		return this.length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}
	/**
	 * @return the numberInStrip
	 */
	public int getNumberInStrip() {
		return this.numberInStrip;
	}
	/**
	 * @param numberInStrip the numberInStrip to set
	 */
	public void setNumberInStrip(int numberInStrip) {
		this.numberInStrip = numberInStrip;
	}
	/**
	 * @return the numberInBox
	 */
	public int getNumberInBox() {
		return this.numberInBox;
	}
	/**
	 * @param numberInBox the numberInBox to set
	 */
	public void setNumberInBox(int numberInBox) {
		this.numberInBox = numberInBox;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
}
