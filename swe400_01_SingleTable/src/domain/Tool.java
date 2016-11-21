package domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import data_source.DatabaseGateway;
import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An InventoryItem Tool
 */
public class Tool extends InventoryItem
{
	protected String description;	
	
	/**
	 * Finder Constructor that queries the database for the item specified by their ID
	 *
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Tool(int id) throws ClassNotFoundException, SQLException, ItemNotFoundException
	{
		super(id);
		ResultSet rs = null;
		try{
			rs = DatabaseGateway.queryTool(this.id);
			if(rs.next()) 
			{
				this.setUpc(rs.getString("upc"));
				this.setManufacturerID(rs.getInt("manufacturerID"));
				this.setPrice(rs.getInt("price"));
				this.setDescription(rs.getString("description"));
				this.setClassName(rs.getString("className"));			
			}
			else
			{
				ItemNotFoundException exception = new ItemNotFoundException("Could not find Tool with specified ID");
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
	 * @param description
	 * @param className
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Tool(String upc, int manufacturerID, int price, String description, String className) throws ClassNotFoundException, SQLException
	{
		super(upc, manufacturerID, price, className);
		this.description = description;
		this.className = "Tool";
		ToolMapper toolMapper = new ToolMapper(this.upc, this.manufacturerID, this.price, this.description, this.className);
		toolMapper.insertTool();
		setId(toolMapper.getId());
	}

	/**
	 * Get description
	 * 
	 * @return String description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Sets the description for the tool
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Tool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price
				+ ", description=" + this.description + "]";
	}

	/**
	 * Get className
	 * 
	 * @return String className
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/**
	 * Sets the className for the Tool Object
	 * 
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
}