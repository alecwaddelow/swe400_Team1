/**
 * 
 */
package swe400_01_SingleTable;

/**
 * @author alecw
 *
 */
public class NailMapper 
{
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
		return upc;
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
		return manufacturerID;
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
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}
	/**
	 * @return the numberInBox
	 */
	public int getNumberInBox() {
		return numberInBox;
	}
	/**
	 * @param numberInBox the numberInBox to set
	 */
	public void setNumberInBox(int numberInBox) {
		this.numberInBox = numberInBox;
	}
	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
	protected double length;
	protected int numberInBox;
}
