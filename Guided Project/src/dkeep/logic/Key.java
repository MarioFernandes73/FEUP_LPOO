package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents a key, extends GameObject
 * The key representation symbol is 'k'
 * A key is passable but not movable
 */
public class Key extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Key constructor, calls GameObject constructor
	 * @param coord key position coordinates
	 */
	public Key(Point coord)
	{
		super(coord, 'k',true,false);
	}
	
}