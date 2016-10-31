package domain_layer;
import java.sql.SQLException;
import data_source.DatabaseGateway;

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
	public Tool(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
		ToolMapper mapper = DatabaseGateway.queryTool(this.id);
		if(mapper == null)
		{
			ClassNotFoundException notFoundException = new ClassNotFoundException("Could not find tool with specified ID");
			notFoundException.getMessage();
		}
		else
		{
			super.setUpc(mapper.getUpc());
			super.setManufacturerID(mapper.getManufacturerID());
			super.setPrice(mapper.getPrice());
			this.description = mapper.getDescription();
			super.setClassName(mapper.getClassName());
		}
	}

	/**
	 * Creation Constructor that creates the tool
	 * 
	 * @param id
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
	}

	/**
	 * @return description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * sets the description for the tool
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Tool [upc=" + this.upc + ", manufacturerID=" + this.manufacturerID + ", price=" + this.price
				+ ", description=" + this.description + "]";
	}

	/**
	 * @return name of the class
	 */
	public String getClassName() 
	{
		return super.getClassName();
	}
	
	/**
	 * Sets the className for the Tool Object
	 * @param className
	 */
	public void setClassName(String className)
	{
		super.setClassName(className);
	}
}