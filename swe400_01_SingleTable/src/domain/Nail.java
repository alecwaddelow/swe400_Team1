package domain;
import java.sql.ResultSet;
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
		
		try(ResultSet rs = DatabaseGateway.queryNail(this.id))
		{
			if(rs.next())
			{
				String upc = rs.getString("upc");
				int manufacturerID = rs.getInt("manufacturerID");
				int price = rs.getInt("price");
				double length = rs.getDouble("length");
				int numberInBox = rs.getInt("numberInBox");
				String className = rs.getString("className");
				NailMapper nailMapper = new NailMapper(upc, manufacturerID, price, length, numberInBox, className);
				setUpc(nailMapper.getUpc());
				setManufacturerID(nailMapper.getManufacturerID());
				setPrice(nailMapper.getPrice());
				setLength(nailMapper.getLength());
				setNumberInBox(nailMapper.getNumberInBox());
				setClassName(nailMapper.getClassName());
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.getCause();
		}
		DatabaseGateway.closeStatements();
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
		NailMapper nailMapper = new NailMapper(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.className);
		nailMapper.insertNail();
		setId(nailMapper.getId());
	}
	
	/** 
	 * @see domain.InventoryItem#setId(int)
	 */
	public void setId(int id)
	{
		super.setId(id);
	}
	
	/** 
	 * @see domain.InventoryItem#getId()
	 */
	public int getId()
	{
		return super.getId();
	}
	
	/**
	 * @return double length
	 */
	public double getLength() 
	{
		return super.getLength();
	}

	/** 
	 * @see domain.Fastener#setLength(double)
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
	 * @see domain.InventoryItem#getClassName()
	 */
	public String getClassName()
	{
		return super.getClassName();
	}

	/** 
	 * @see domain.InventoryItem#setClassName(java.lang.String)
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
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