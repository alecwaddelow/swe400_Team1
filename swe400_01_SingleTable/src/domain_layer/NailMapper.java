package domain_layer;

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
	protected DatabaseGateway gateway;
	
	/**
	 * Constructor for NailMapper 
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInBox
	 * @param className
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
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
		gateway.insertNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.className);
		setId(this.upc);
	}
	
	/**
	 * Empty Constructor for NailMapper 
	 */
	public NailMapper() {}
	
	/**
	 * sets the id of the mapper
	 */
	public void setId(String upc) throws ClassNotFoundException, SQLException
	{
		super.setId(this.upc);
	}
	
	/**
	 * returns the id of the mapper
	 */
	public int getId()
	{
		return super.getId();
	}
	
	
	/**
	 * @return the item's length
	 */
	public double getLength()
	{
		return this.length;
	}

	/**
	 * set the item's length
	 * @param length
	 */
	public void setLength(double length)
	{
		this.length = length;
	}
	
	/**
	 * @return the number of item's in the box
	 */
	public int getNumberInBox()
	{
		return this.numberInBox;
	}

	/**
	 * set the the number of item's in the box
	 * @param numberInBox
	 */
	public void setNumberInBox(int numberInBox)
	{
		this.numberInBox = numberInBox;
	}
	
	/**
	 * updates the nail
	 * @param nail
	 * @throws SQLException 
	 */
	public void updateNail(Nail nail) throws SQLException 
	{
		setUpc(nail.getUpc());
		setManufacturerID(nail.getManufacturerID());
		setPrice(nail.getPrice());
		setLength(nail.getLength());
		setNumberInBox(nail.getNumberInBox());
		
		gateway.updateNailToDB(this.upc, this.manufacturerID, this.price, this.length, this.numberInBox, this.id);
	}
}