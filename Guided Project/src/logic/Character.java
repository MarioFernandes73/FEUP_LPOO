package logic;

public class Character extends GameObjects {

	public enum State {ALIVE, HASKEY, DEAD};
	private State currentState;
	private boolean vulnerable;
	
	public Character(int x, int y, int identifier)
	{
		super(x,y,identifier);
		currentState = State.ALIVE;
		vulnerable = true;
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
	
	public boolean attack(Dungeon dungeon)
	{
		return false;
	}
	
	public void died()
	{
		this.currentState = State.DEAD;
	}

	public boolean isVulnerable()
	{
		return vulnerable;
	}
	
	public void setVulnerable(boolean vulnerable)
	{
		this.vulnerable=vulnerable;
	}
	
	public boolean closedDoorInteraction()
	{
		return false;
	}
	
	public boolean canFinishLevel()
	{
		return false;
	}
	
	public boolean leverInteraction()
	{
		return false;
	}
	
	public void carryKey()
	{
		
	}
}
