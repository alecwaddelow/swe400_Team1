package domain;
import java.sql.SQLException;
import data_source.DataTransferObject;
import data_source.InventoryItemGateway;
import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem that is a Fastener
 */
public class Nail extends Fastener
{
	public int numberInBox;

	/**
	 * Finder Constructor that queries the database for the specified nail by their ID
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Nail(int id) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		super(id);
		DataTransferObject dto = InventoryItemGateway.queryNail(this.id);
		if(dto != null)
		{
			this.manufacturerID = dto.getManufacturerID();
			this.price = dto.getPrice();
			this.length = dto.getLength();
			this.numberInBox = dto.getNumberInBox();
			this.className = "Nail";
		}
		else
		{
			ItemNotFoundException exception = new ItemNotFoundException("Could not find Nail with specified ID");
			exception.getMessage();
		}
	}

	/**
	 * Creation Constructor
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Nail(String upc, int manufacturerID, int price, double length, int numberInBox, String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, length, className);
		this.numberInBox = numberInBox;
		this.className = "Nail";
		InventoryItemGateway.insertNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.className);
		this.id = InventoryItemGateway.getID(this.upc, this.className);
	}

	/**
	 * @return numberInBox
	 */
	public int getNumberInBox()
	{
		return this.numberInBox;
	}

	/**
	 * Set numberInBox
	 * @param numberInBox
	 */
	public void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}
	
	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Nail [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", length=" + this.length + ", numberInBox=" + this.numberInBox + "]";
	}
}