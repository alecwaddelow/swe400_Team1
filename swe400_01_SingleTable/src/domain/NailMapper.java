package domain;
import java.sql.SQLException;
import data_source.DatabaseGateway;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * A mapper for Nail objects
 * 
 */
public class NailMapper extends DBMapper
{
	protected double length;
	protected int numberInBox;
	
	
	/**
	 * Constructor
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
	public NailMapper(String upc, int manufacturerID, int price, Double length, int numberInBox, String className) throws ClassNotFoundException, SQLException 
	{
		super(upc, manufacturerID, price, className);
		this.length = length;
		this.numberInBox = numberInBox;
	}
	
	/**
	 * Insert Nail
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertNail() throws ClassNotFoundException, SQLException
	{
		DatabaseGateway.insertNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.className);
		setId(this.upc);
	}
	
	/**
	 * Empty Constructor for NailMapper 
	 */
	public NailMapper() {}
	
	
	/** 
	 * @see domain.DBMapper#setId(java.lang.String)
	 */
	public void setId(String upc) throws ClassNotFoundException, SQLException
	{
		super.setId(this.upc);
	}

	/* 
	 * @see domain_layer.DBMapper#getId()
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
		return this.length;
	}

	/**
	 * Set length
	 * 
	 * @param length
	 */
	public void setLength(double length)
	{
		this.length = length;
	}
	
	/**
	 * @return int the number of item's in the box
	 */
	public int getNumberInBox()
	{
		return this.numberInBox;
	}

	/**
	 * Sets numberInBox
	 * 
	 * @param numberInBox
	 */
	public void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}
	
	/**
	 * Updates the nail
	 * 
	 * @param nail
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateNail(Nail nail) throws SQLException, ClassNotFoundException 
	{
		this.id =nail.getId();
		setUpc(nail.getUpc());
		setManufacturerID(nail.getManufacturerID());
		setPrice(nail.getPrice());
		setLength(nail.getLength());
		setNumberInBox(nail.getNumberInBox());
		DatabaseGateway.updateNailToDB(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.id);
	}
}