package dkeep.logic;

public class Character extends GameObject {

	public enum State {ALIVE, DEAD};
	private State currentState;

	public Character(Point coord, int identifier, boolean passable, boolean movable)
	{
		super(coord, identifier, passable,movable);
		currentState = State.ALIVE;
	}
	
	public State getState()
	{
		return currentState;
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
		final Point[] noAttack = null;
		return noAttack;
	}
	
	public Point[] weaponAttack()
	{
		final Point[] noAttack = null;
		return noAttack;
	}
	
	public void died()
	{
		this.currentState = State.DEAD;
	}
	
	public boolean carryKey()
	{
		return false;
	}
}
