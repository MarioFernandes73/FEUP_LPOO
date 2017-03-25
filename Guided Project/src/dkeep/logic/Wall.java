package dkeep.logic;

/**
 * 
 * Represents a wall, extends GameObject
 * The wall representation symbol is 'X'
 * A wall is neither passable nor movable
 *
 */
public class Wall extends GameObject {
	/**
	 * Wall constructor, calls GameObject constructor
	 */
	public Wall()
	{
		super(null, 'X', false, false);
	}

}
