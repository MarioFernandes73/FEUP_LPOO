package logic;

public class Character extends GameObjects {

	public enum State {ALIVE, HASKEY, DEAD};
	private State currentState;
	
	public Character(int x, int y, int identifier)
	{
		super(x,y,identifier);
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
		
		if(nextY < 0 || nextX < 0 || nextY >=dungeon.getWidth() || nextX >= dungeon.getHeight())
		{
			return 0;
		}
		final int validMove = auxMovement(nextX, nextY, dungeon);//by default its 1, which means it is a valid movement without any consequences in the game state
		
		if (validMove != 0)
		{
			super.getCoord().setLocation(nextX, nextY);
		}
			return validMove;
	}
	
	// returns possible if the current movement is a valid one or false if it's invalid
	public int auxMovement(int nextX,int nextY, Dungeon dungeon)
	{
		final int nextTile = dungeon.getDungeon()[nextY][nextX];
		if(nextTile == 0)
		{
			return 1;
		}
		else if(nextTile == 1 || nextTile == 4 || nextTile == 6 || (nextX < 0 || nextY < 0 || nextX >= dungeon.getWidth() || nextY >= dungeon.getHeight()))
		{
			return 0;
		}
		else if(nextTile == 8 || nextTile == 9)
		{
			return 2;
		}
		
		return 1;
	}
}
