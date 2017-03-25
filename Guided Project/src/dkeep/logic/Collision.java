package dkeep.logic;

/**
 * 
 * Represents an overlap between a key and an ogre, or between a key and a club
 * The collision representation symbol is '$'
 * A collision is passable, but not movable
 * Extends GameObject
 */
public class Collision extends GameObject{

	/**
	 * collision constructor, calls the GameObject constructor
	 * @param coord collision coordinates
	 */
	public Collision(Point coord)
	{
		super(coord, '$', true, false);
	}
}
