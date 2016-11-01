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
}