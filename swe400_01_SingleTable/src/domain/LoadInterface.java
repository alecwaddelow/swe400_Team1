package domain;
import java.sql.SQLException;

import exceptions.ItemNotFoundException;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * A LoadInterface that allows PowerTool and StripNails to load their relationships with each other in their own lists
 */
public interface LoadInterface 
{
	public abstract void load() throws ClassNotFoundException, SQLException, ItemNotFoundException;
}
