package logic;

public class Character extends GameObjects {

	public enum State {ALIVE, DEAD};
	private State currentState;
	
	public Character(int x, int y, char symbol)
	{
		super(x,y,symbol);
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
	
	public int movement(String movement, Dungeon dungeon)
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
		final int validMove = auxMovement(nextX, nextY, dungeon);
		
		if (validMove != 0)
		{
			super.getCoord().setLocation(nextX, nextY);
			return validMove;
		}
		else
		{
			return 0;
		}
	}
	
	// returns possible if the current movement is a valid one or false if it's invalid
	public int auxMovement(int nextX,int nextY, Dungeon dungeon)
	{
		final char nextTile = dungeon.getDungeon()[nextY][nextX];
		if(nextTile == ' ')
		{
			return 1;
		}
		else if(nextTile == 'X' || nextTile == 'I')
		{
			return 0;
		}
		else if(nextTile == 'k')
		{
			return 2;
		}
		
		return 1;
	}
}
