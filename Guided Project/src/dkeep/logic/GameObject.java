package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents an object present in the game
 *
 */
public class GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Symbol that represents the object when displayed on screen
	 */
	private char symbol;
	
	/**
	 * Current coordinates of the object position
	 */
	private Point coordinates;
	
	/**
	 * True if other objects can move to the same position as this, false otherwise
	 */
	private boolean passable;
	
	/**
	 * True if the object moves, false otherwise
	 */
	private boolean movable;
	
	/**
	 * Object constructor
	 * 
	 * @param coordinates coordinates of the object position
	 * 
	 * @param symbol symbol that represents the object when shown on screen
	 * 
	 * @param passable determines passable variable value
	 * 
	 * @param movable determines movable variable value
	 */
	public GameObject(Point coordinates, char symbol, boolean passable, boolean movable)
	{
		this.symbol = symbol;
		this.coordinates = coordinates;
		this.passable = passable;
		this.movable=movable;
	}
	
	/**
	 * @return returns movable variable value
	 */
	public boolean canMove()
	{
		return movable;
	}
	
	/**
	 * @return returns passable variable value
	 */
	public boolean isPassable()
	{
		return passable;
	}
	
	/**
	 * @param passable new passable variable value
	 */
	public void setPassable(boolean passable)
	{
		this.passable=passable;
	}
	
	/**
	 * @param symbol new object symbol
	 */
	public void setSymbol(char symbol)
	{
		this.symbol = symbol;
	}
	
	/**
	 * @param p new object position coordinates
	 */
	public void move(Point p)
	{
		this.coordinates = p;
	}
	
	/**
	 * @return returns the current object position coordinates
	 */
	public Point getCoord()
	{
		return coordinates;
	}
	
	/**
	 * Two objects are equal if they have the same symbol and coordinates
	 * @param object
	 * @return
	 */
	public boolean equals(GameObject object)
	{
		return (this.getCoord() == object.getCoord() && this.symbol == object.symbol);
	}
	
	/**
	 * Converts an object to a string, which will be equal to its symbol
	 */
	@Override
	public String toString()
	{
		return this.symbol+"";
	}
}
