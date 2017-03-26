package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents a wall, extends GameObject
 * The wall representation symbol is 'X'
 * A wall is neither passable nor movable
 *
 */
public class Wall extends GameObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wall constructor, calls GameObject constructor
	 */
	public Wall()
	{
		super(null, 'X', false, false);
	}

}
