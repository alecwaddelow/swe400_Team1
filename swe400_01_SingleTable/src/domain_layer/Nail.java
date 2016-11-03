package domain_layer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.experimental.theories.Theories;

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
		ResultSet rs = DatabaseGateway.queryNail(this.id);
		
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
		else
		{
			ClassNotFoundException exception = new ClassNotFoundException("Could not find nail with specified ID");
			exception.getMessage();
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
		setId(nailMapper.getId());
	}
	
	/**
	 * sets the id of the object
	 */
	public void setId(int id)
	{
		super.setId(id);
	}
	
	/**
	 * returns the id of the object
	 */
	public int getId()
	{
		return super.getId();
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
	
	/**
	 * Updates the item  locally then sends the fields to the insert function in the gateway
	 * 
	 * @param sc
	 * @param item
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void update(Scanner sc, InventoryItem item) throws ClassNotFoundException, SQLException 
	{
		Nail nail = (Nail) item;
		
		System.out.println("\nWarning... You are about to update this item, if you don't want certain values to change, retype the same value");
		
		System.out.println("Plase enter the UPC:");
		String upc = sc.nextLine();
		
		System.out.println("Please enter the manufacturerID:");
		String manufacturerID = sc.nextLine();
		int manufacturerIDParse = Integer.parseInt(manufacturerID);
		
		System.out.println("Please enter the price of the item:");
		String price = sc.nextLine();
		int priceParse = Integer.parseInt(price);
		
		System.out.println("Please enter length \n");
		String length = sc.nextLine();
		double lengthParse = Double.parseDouble(length);
			
		System.out.println("Please enter Number in Box \n");
		String numberInBox = sc.nextLine();
		int numberInBoxParse = Integer.parseInt(numberInBox);
			
		nail.setUpc(upc);
		nail.setManufacturerID(manufacturerIDParse);
		nail.setPrice(priceParse);
		nail.setLength(lengthParse);
		nail.setNumberInBox(numberInBoxParse);
			
		DatabaseGateway.updateNailToDB(upc, manufacturerIDParse, priceParse, lengthParse, numberInBoxParse, item.getId());
		
		System.out.println("\nItem updated:");
		System.out.println(nail.toString());
	}
}
