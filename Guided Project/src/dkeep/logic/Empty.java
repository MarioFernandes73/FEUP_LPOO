package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents an empty cell in a dungeon, extends GameObject
 * The empty cell representation symbol is ' '
 * An empty cell is passable but not movable
 */
public class Empty extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Empty cell constructor, call GameObject constructor
	 */
	public Empty()
	{
		super(null, ' ' ,true,false);
	}
}
