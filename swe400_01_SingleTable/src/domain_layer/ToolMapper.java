package domain_layer;

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
	protected DatabaseGateway gateway;

	/**
	 * Creation Constructor 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
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
		gateway.insertTool(this.upc, this.manufacturerID, this.price, this.description, this.className);
		setId(this.upc);
	}

	/**
	 * Empty constructor 
	 */
	public ToolMapper() {}
	
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
	 * updates the tool
	 * @param tool
	 * @throws SQLException 
	 */
	public void updateTool(Tool tool) throws SQLException 
	{
		this.id = tool.getId();
		setUpc(tool.getUpc());
		setManufacturerID(tool.getManufacturerID());
		setPrice(tool.getPrice());
		setDescription(tool.getDescription());
		
		gateway.updateToolToDB(this.upc, this.manufacturerID, this.price, this.description, this.id);
	}
}