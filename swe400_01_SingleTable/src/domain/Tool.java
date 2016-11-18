package domain;
import java.sql.ResultSet;
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
		try(ResultSet rs = DatabaseGateway.queryTool(this.id))
		{
			if(rs.next()) 
			{
				String upc = rs.getString("upc");
				int manufacturerID = rs.getInt("manufacturerID");
				int price = rs.getInt("price");
				String description = rs.getString("description");
				String className = rs.getString("className");
				ToolMapper toolMapper = new ToolMapper(upc, manufacturerID, price, description, className);
				this.setUpc(toolMapper.getUpc());
				this.setManufacturerID(toolMapper.getManufacturerID());
				this.setPrice(toolMapper.getPrice());
				this.setDescription(toolMapper.getDescription());
				this.setClassName(toolMapper.getClassName());			
			}
			else
			{
				ClassNotFoundException notFoundException = new ClassNotFoundException("Could not find tool with specified ID");
				notFoundException.getMessage();
			}
			rs.close();
		}
		catch(SQLException e)
		{
			e.getErrorCode();
		}
		DatabaseGateway.closeStatements();
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