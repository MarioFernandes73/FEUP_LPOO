package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents the ogre's weapon, extends GameObject
 * The club representation symbol is '*'
 * A club is both passable and movable
 */
public class Club extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Club constructor, calls the GameObject constructor
	 * 
	 * @param coord club's position coordinates
	 */
	public Club(Point coord)
	{
		super(coord, '*', true, true);
	}
}
