package domain_layer;

import java.sql.SQLException;

import data_source.DatabaseGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * A mapper for the StripNail objects
 */
public class StripNailsMapper extends DBMapper 
{
	protected double length;
	protected int numberInStrip;
	protected DatabaseGateway gateway;

	/**
	 * Constructor for StripNailsMapper
	 * 
	 * @param id
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @param className
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public StripNailsMapper(String upc, int manufacturerID, int price, Double length, int numberInStrip, String className) throws ClassNotFoundException, SQLException 
	{
		super(upc, manufacturerID, price, className);
		this.length = length;
		this.numberInStrip = numberInStrip;
		
	}
	
	/**
	 * Insert StripNail
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertStripNail() throws ClassNotFoundException, SQLException
	{
		gateway.insertStripNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.className);
	}

	/**
	 * Empty constructor
	 */
	public StripNailsMapper() {}

	/**
	 * @return the item's length
	 */
	public double getLength()
	{
		return this.length;
	}
	
	/**
	 * set the item's length
	 * @param length2
	 */
	public void setLength(double length)
	{
		this.length = length;
	}
	
	/**
	 * @return the numberInStrip
	 */
	public int getNumberInStrip() 
	{
		return this.numberInStrip;
	}
	
	/**
	 * @param numberInStrip the numberInStrip to set
	 */
	public void setNumberInStrip(int numberInStrip) 
	{
		this.numberInStrip = numberInStrip;
	}
}