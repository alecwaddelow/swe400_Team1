/**
 * 
 */

/**
 * @author alecw
 *
 */
public class PowerToolMapper
{

	protected int id;
	protected String upc;
	protected int manufacturerID;
	protected int price;
	protected String description;
	protected boolean batteryPowered;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isBatteryPowered() {
		return batteryPowered;
	}
	public void setBatteryPowered(boolean batteryPowered) {
		this.batteryPowered = batteryPowered;
	}
	
}
