package dkeep.logic;

/**
 * 
 * Represents an empty cell in a dungeon, extends GameObject
 * The empty cell representation symbol is ' '
 * An empty cell is passable but not movable
 */
public class Empty extends GameObject {

	/**
	 * Empty cell constructor, call GameObject constructor
	 */
	public Empty()
	{
		super(null, ' ' ,true,false);
	}
}
