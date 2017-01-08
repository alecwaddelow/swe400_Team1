package data_source;

public class LinkTableDTO 
{
	private int powerToolID;
	private int stripNailID;
	
	public LinkTableDTO() {}
	
	/**
	 * @return powerToolID
	 */
	public int getPowerToolID()
	{
		return this.powerToolID;
	}
	
	/**
	 * @param powerToolID to be set
	 */
	public void setPowerToolID(int powerToolID)
	{
		this.powerToolID = powerToolID;
	}
	
	/**
	 * @return stripNailID
	 */
	public int getStripNailID()
	{
		return this.stripNailID;
	}
	
	/**
	 * @param stripNailID to be set
	 */
	public void setStripNailID(int stripNailID)
	{
		this.stripNailID = stripNailID;
	}
}
