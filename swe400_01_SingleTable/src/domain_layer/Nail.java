package domain_layer;
import java.sql.SQLException;
import data_source.DatabaseGateway;

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
	public Nail(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
		NailMapper mapper = DatabaseGateway.queryNail(this.id);
		
		if(mapper == null)
		{
			ClassNotFoundException exception = new ClassNotFoundException("Could not find nail with specified ID");
			exception.getMessage();
		}
		else
		{
			super.setUpc(mapper.getUpc());
			super.setManufacturerID(mapper.getManufacturerID());
			super.setPrice(mapper.getPrice());
			super.setLength(mapper.getLength());
			this.numberInBox = mapper.getNumberInBox();
			super.setClassName(mapper.getClassName());
		}
	}

	/**
	 * Creation Constructor that creates the nail
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
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
		NailMapper nailMapper = new NailMapper(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.className);
		nailMapper.insertNail();
	}
	
	/**
	 * @return the length
	 */
	public double getLength() 
	{
		return super.getLength();
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) 
	{
		super.setLength(length);
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
	 * @return className
 	 */
	public String getClassName()
	{
		return super.getClassName();
	}

	/**
	 * Set className
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Nail [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price 
				+ ", length=" + this.length + ", numberInBox=" + this.numberInBox + "]";
	}
}
