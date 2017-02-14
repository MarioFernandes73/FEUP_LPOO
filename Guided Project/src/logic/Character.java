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
		
		int validMove = 1;//by default its 1, which means it is a valid movement without any consequences in the game state
		
		if(nextX >= 0 && nextY >= 0 && nextX < dungeon.getWidth() && nextY < dungeon.getHeight())
			validMove = auxMovement(nextX, nextY, dungeon);//this function is called if the movement keeps the character on screen
		else //if the movement puts the character OUT of screen, then the validMove will return 1, however without updating its position on screen
			return validMove;
		
		if (validMove != 0)
		{
			super.getCoord().setLocation(nextX, nextY);
		}
			return validMove;
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
