package domain;
import java.sql.SQLException;
import data_source.InventoryItemGateway;

/**
 * @author Alec Waddelow & Drew Rife
 * 
 * Tool Mapper class 
 */
public class ToolMapper extends DBMapper 
{
	protected String description;
	
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
		InventoryItemGateway.insertTool(this.upc, this.manufacturerID, this.price, this.description, this.className);
		setId();
	}

	/**
	 * Empty constructor 
	 */
	public ToolMapper() {}
	
	
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
		InventoryItemGateway.updateToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.id);
	}
}