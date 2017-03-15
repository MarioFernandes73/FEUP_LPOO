package dkeep.logic;

import dkeep.logic.Character.State;

public class Character extends GameObject {

	public enum State {ALIVE, ARMED, DEAD};
	private State currentState;

	public Character(Point coord, char symbol, boolean passable, boolean movable)
	{
		super(coord, symbol, passable,movable);
		currentState = State.ALIVE;
	}
	
	public State getState()
	{
		return currentState;
	}
	
	public void setState (State state)
	{
		this.currentState = state;
	}
	
	public boolean isDead()
	{
		if (currentState == State.DEAD)
		{
			return true;
		}
		return false;
	}
	
	// constructs a random movement and makes it
	public String createMovement()
	{
		return "";
	}
	
	public Point movement(String movement)
	{
		int nextX = 0, nextY = 0;
		switch(movement)
		{
			case "w":
			{
				nextY = this.getCoord().getY() - 1;
				nextX = this.getCoord().getX();	
				break;
			}
			case "a":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() -1;
				break;
			}
			case "s":
			{
				nextY = this.getCoord().getY() + 1;
				nextX = this.getCoord().getX();
				break;
			}
			case "d":
			{
				nextY = this.getCoord().getY();
				nextX = this.getCoord().getX() + 1;
				break;
			}
		}
	
		return new Point(nextX,nextY);
	}
	
	public Point[] attack()
	{
		Point[] noAttack = null;
		return noAttack;
	}
	
	public Point[] weaponAttack()
	{
		Point[] noAttack = null;
		return noAttack;
	}
	
	public void died()
	{
		this.currentState = State.DEAD;
	}
	
	public Club getWeapon()
	{
		return null;
	}
}
