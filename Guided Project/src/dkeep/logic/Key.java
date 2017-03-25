package dkeep.logic;

/**
 * 
 * Represents a key, extends GameObject
 * The key representation symbol is 'k'
 * A key is passable but not movable
 */
public class Key extends GameObject {

	/**
	 * Key constructor, calls GameObject constructor
	 * @param coord key position coordinates
	 */
	public Key(Point coord)
	{
		super(coord, 'k',true,false);
	}
	
}