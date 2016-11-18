package domain;
import java.sql.SQLException;
import data_source.DatabaseGateway;

/**
 * @author Alec Waddelow & Drew Rife
 * 
 * Tool Mapper class 
 */
public class ToolMapper extends DBMapper 
{
	protected String description;
	
	/**
	 * 
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
	public ToolMapper(String upc, int manufacturerID, int price, String description, String className) throws ClassNotFoundException, SQLException 
	{
		super(upc, manufacturerID, price, className);
		this.description = description;
	}
	
	/**
	 * Insert tool 
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertTool() throws ClassNotFoundException, SQLException
	{
		DatabaseGateway.insertTool(this.upc, this.manufacturerID, this.price, this.description, this.className);
		setId();
	}

	/**
	 * Empty constructor 
	 */
	public ToolMapper() {}
	
	/**
	 * Sets the id of the mapper
	 */
	public void setId() throws ClassNotFoundException, SQLException
	{
		super.setId();
	}
	
	/**
	 * Returns the id of the mapper
	 */
	public int getId()
	{
		return super.getId();
	}
	
	/**
	 * @return the item's description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * sets the item's description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Updates the tool
	 * 
	 * @param tool
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateTool(Tool tool) throws SQLException, ClassNotFoundException 
	{
		this.id = tool.getId();
		setUpc(tool.getUpc());
		setManufacturerID(tool.getManufacturerID());
		setPrice(tool.getPrice());
		setDescription(tool.getDescription());
		
		DatabaseGateway.updateToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.id);
	}
}