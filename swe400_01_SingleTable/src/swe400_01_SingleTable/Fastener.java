package swe400_01_SingleTable;

/**
 * @authors Drew Rife & Alec Waddelow
 *
 * An InventoryItem where anything that inherits Fastener will have a length
 */
public abstract class Fastener extends InventoryItem
{
	protected double length;
}