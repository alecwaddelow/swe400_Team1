package domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import data_source.DatabaseGateway;
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
		ResultSet rs = null;
		try{
			rs = DatabaseGateway.queryNail(this.id);
			if(rs.next())
			{
				setUpc(rs.getString("upc"));
				setManufacturerID(rs.getInt("manufacturerID"));
				setPrice(rs.getInt("price"));
				setLength(rs.getDouble("length"));
				setNumberInBox(rs.getInt("numberInBox"));
				setClassName(rs.getString("className"));
			}
			else
			{
				ItemNotFoundException exception = new ItemNotFoundException("Could not find Nail with specified ID");
				exception.getMessage();
			}
			rs.close();
			DatabaseGateway.closeStatements();
		}
		catch(SQLException notFound)
		{
			notFound.getMessage();
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