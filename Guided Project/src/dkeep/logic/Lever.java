package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents a lever, extends GameObject
 * The lever representation symbol is 'k'
 * A lever is passable but not movable
 * A lever is in all equal to the key, except that it opens all doors in the current level
 */
public class Lever extends GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lever(Point coord)
	{
		super(coord, 'k', true, false);
	}
}
