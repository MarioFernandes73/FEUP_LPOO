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
	
	public boolean movement(String movement, Dungeon dungeon)
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
		final boolean validMove = auxMovement(nextX, nextY, dungeon);
		
		if (validMove)
		{
			super.getCoord().setLocation(nextX, nextY);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// returns possible if the current movement is a valid one or false if it's invalid
	public boolean auxMovement(int nextX,int nextY, Dungeon dungeon)
	{
		final char nextTile = dungeon.getDungeon()[nextY][nextX];
		if(nextTile == ' ')
		{
			return true;
		}
		else if(nextTile == 'X' || nextTile == 'I')
		{
			return false;
		}
		
		return true;
	}
}
