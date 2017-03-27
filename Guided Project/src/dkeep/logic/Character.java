package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * Represents a game character, derives from GameObject
 *
 */
public class Character extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Used to express a character state in the game
	 *
	 */
	public enum State {ALIVE, DEAD};
	
	/**
	 * 
	 * Indicates if the character is currently dead or alive
	 *
	 */
	private State currentState;

	/**
	 * Character constructor, calls the GameObject constructor
	 * All characters are alive when created
	 * 
	 * @param coord character coordinates
	 * @param symbol character symbol
	 * @param passable character passable ability
	 * @param movable character movable ability
	 */
	public Character(Point coord, char symbol, boolean passable, boolean movable)
	{
		super(coord, symbol, passable,movable);
		currentState = State.ALIVE;
	}
	
	/**
	 * 
	 * @return returns the character state
	 */
	public State getState()
	{
		return currentState;
	}
	
	/**
	 * 
	 * @param state character's new state
	 */
	public void setState (State state)
	{
		this.currentState = state;
	}
	
	/**
	 * 
	 * @return returns true is a character is dead, false if it's alive
	 */
	public boolean isDead()
	{
		if (currentState == State.DEAD)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This function only exists for further override on derived classes
	 * 
	 * @return
	 */
	public String createMovement()
	{
		return "";
	}
	
	/**
	 * Gives the new character position according to the movement received
	 * @param movement string which indicates the movement direction
	 * @return coordinates of the position to where the movement is headed
	 */
	public Point movement(String movement)
	{
		int nextX = 0, nextY = 0;
		switch(movement){
			case "w":nextY = this.getCoord().getY() - 1;
				nextX = this.getCoord().getX();	
				break;
			case "a":nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() -1;
				break;
			case "s":nextY = this.getCoord().getY() + 1;
				nextX = this.getCoord().getX();
				break;
			case "d":nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() + 1;
				break;
		}
		return new Point(nextX,nextY);
	}
	
	/**
	 * This function only exists for further override on derived classes
	 * 
	 * @return
	 */
	public Point[] attack()
	{
		Point[] noAttack = null;
		return noAttack;
	}
	
	/**
	 * This function only exists for further override on derived classes
	 * 
	 * @return
	 */
	public Point[] weaponAttack()
	{
		Point[] noAttack = null;
		return noAttack;
	}
	
	/**
	 * Causes the character's death, changing his state accordingly
	 */
	public void died()
	{
		this.currentState = State.DEAD;
	}
	
	/**
	 * This function only exists for further override on derived classes
	 * 
	 * @return
	 */
	public Club getWeapon()
	{
		return null;
	}
}
