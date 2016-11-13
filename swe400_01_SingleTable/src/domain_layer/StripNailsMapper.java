package domain_layer;
import java.sql.SQLException;
import data_source.DatabaseGateway;

/**
 * @author Alec Waddelow and Drew Rife 
 * 
 * A mapper for the StripNail objects
 */
/**
 * @author alecw
 *
 */
/**
 * @author alecw
 *
 */
public class StripNailsMapper extends DBMapper 
{
	protected double length;
	protected int numberInStrip;
	
	/**
	 * Constructor
	 * 
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param length
	 * @param numberInStrip
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
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
		DatabaseGateway.insertStripNail(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.className);
		setId(this.upc);
	}

	/**
	 * Empty constructor
	 */
	public StripNailsMapper() {}
	

	/** 
	 * @see domain_layer.DBMapper#setId(java.lang.String)
	 */
	public void setId(String upc) throws ClassNotFoundException, SQLException
	{
		super.setId(this.upc);
	}
	
	
	/** 
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
	 * set the item's length
	 * 
	 * @param length
	 */
	public void setLength(double length)
	{
		this.length = length;
	}
	
	/**
	 * @return tint numberInStrip
	 */
	public int getNumberInStrip() 
	{
		return this.numberInStrip;
	}
	
	/**
	 * @param numberInStrip 
	 */
	public void setNumberInStrip(int numberInStrip) 
	{
		this.numberInStrip = numberInStrip;
	}
	
	/**
	 * Updates the StripNail
	 * 
	 * @param stripNail
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateStripNail(StripNail stripNail) throws SQLException, ClassNotFoundException 
	{
		this.id = stripNail.getId();
		setUpc(stripNail.getUpc());
		setManufacturerID(stripNail.getManufacturerID());
		setPrice(stripNail.getPrice());
		setLength(stripNail.getLength());
		setNumberInStrip(stripNail.getNumberInStrip());
		DatabaseGateway.updateStripNailToDB(this.upc, this.manufacturerID, this.price, this.length, this.numberInStrip, this.id);
	}
}