package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * represents a hero, extends Character
 * The hero representation symbol is 'H', if it has attacking abilities is 'A', if it has got the key in level 2 is 'K'
 * A hero is both passable and movable
 */
public class Hero extends Character implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Set to true if the hero has caught the key, set to false otherwise
	 */
	private boolean key;
	
	/**
	 * Hero constructor, calls Character constructor
	 * hero always starts without key
	 * @param coord hero position coordinates
	 */
	public Hero(Point coord)
	{
		super(coord,'H',true,true);
		key = false;
	}
	
	/**
	 * 
	 * @return returns key variable value
	 */
	public boolean getKey()
	{
		return key;
	}
	
	/**
	 * @return returns a list with position coordinates affected by the attack
	 */
	public Point[] attack()
	{
		return this.getCoord().getAdjacentPos();
	}
	
	/**
	 * changes hero symbol and key variable value accordingly to getting key action
	 * @return returns always true
	 */
	public boolean carryKey()
	{
		this.setSymbol('K');
		key = true;
		return true;
	}
	
}