
package exceptions;

/**
 * @author Drew Rife & Alec Waddelow
 *
 * An exception class for when an item is not found in the database
 */
public class ItemNotFoundException extends Exception
{
	private static final long serialVersionUID = -8819171092941379260L;

	public ItemNotFoundException() 
	 {
		super();
	 }

	 public ItemNotFoundException(String message)
	 {
	        super(message);
	 }
}
